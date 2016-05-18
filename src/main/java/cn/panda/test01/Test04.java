package cn.panda.test01;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class Test04 implements PageProcessor {


    public static final String PEOPLEURL = "https://www\\.zhihu\\.com/people/w+";

    public int i = 0;

    private Site site = Site.me().
            setRetryTimes(5).setSleepTime(1000).
            setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    public void process(Page page) {


            String name = page.getHtml().xpath("//div[@class='title-section ellipsis']//span[@class = 'name']").toString();
            String signature = page.getHtml().xpath("//div[@class='title-section ellipsis']//span[@class= 'bio']").toString();
            String agrees = page.getHtml().xpath("//div[@class='zm-profile-header-info-list']//span[@class= 'zm-profile-header-user-agree']//strong").toString();
            String thanks = page.getHtml().xpath("//div[@class='zm-profile-header-info-list']//span[@class= 'zm-profile-header-user-thanks']//strong").toString();

            System.out.println(">>>"+i+">>>"+name+">>>"+signature+">>>"+agrees+">>>"+thanks);



    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new Test04()).addUrl("https://www.zhihu.com/people/Panda-Me").run();

    }


}
