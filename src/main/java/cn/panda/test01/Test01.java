package cn.panda.test01;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class Test01 implements PageProcessor {
    public static int i = 0;
    private static final String FOCUS_BEGIN_STR = "</button> ";
    private static final String FOCUS_END_STR = " 人关注该问题";

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5)
            .setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3").setCharset("UTF-8");

    private static Map<String, String> eatMap = new HashMap<String, String>();

    public void process(Page page) {
        page.addTargetRequests(
                page.getHtml().links().regex("(https://www.zhihu.com/topic/19551556/top-answers\\?page=\\d+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://www.zhihu.com/question/\\d+)").all());
        System.out.println(i++);
        if (page.getUrl().regex("(https://www.zhihu.com/question/\\d+)").match()) {
            List<String> playCountList = page.getHtml()
                    .xpath("//div[@class='zm-side-section-inner zg-gray-normal']/html()").all();
            if (playCountList.size() == 1) {
                String focusStr = playCountList.get(0);
                long focus = Long.parseLong(focusStr.substring(
                        focusStr.indexOf(FOCUS_BEGIN_STR) + FOCUS_BEGIN_STR.length(), focusStr.indexOf(FOCUS_END_STR)));
                if (focus > 30000) {
                    List<String> eatList = page.getHtml()
                            .xpath("//div[@class='zm-item-rich-text js-collapse-body']/html()").regex("吃").all();
                    List<String> titleList = page.getHtml().xpath("//title/html()").all();
                    if (eatList.size() > 5) {
                        System.out.println(page.getUrl()+">>>>>>>>>>"+titleList.get(0));
                        eatMap.put(page.getUrl().toString(), titleList.get(0));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Spider.create(new Test01()).addUrl("https://www.zhihu.com/topic/19551556/top-answers").thread(5)
                .run();
        System.out.println("====================================total===================================");
        for (String s : eatMap.keySet()) {
            System.out.println("title:" + eatMap.get(s));
            System.out.println("href:" + s);
        }
    }

    public Site getSite() {
        return site;
    }
}
