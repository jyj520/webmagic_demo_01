package cn.panda.test01;

import cn.panda.dao.ZhiHuUserDao;
import cn.panda.entity.ZhiHuUser;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class Test05 implements PageProcessor {


    ZhiHuUserDao zhiHuUserDao = new ZhiHuUserDao();

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2")
            .setDomain("https://www.zhihu.com")
            .addHeader("Refer", "https://www.zhihu.com");

    public static void main(String args[]) {
        String startUrl = "https://www.zhihu.com/people/kaifulee/";

        Spider.create(new Test05())
                .addUrl(startUrl)
                .addPipeline(new ConsolePipeline())
                //.addPipeline(new JsonFilePipeline("zhihu-people.json"))
                .addPipeline(new FilePipeline("D:\\webmagic\\hello.text"))
                .thread(1)
                .run();
    }

    public void process(Page page) {


        //target url
        page.addTargetRequests(page.getHtml()
                .links()
                .regex("https://www\\.zhihu\\.com/people/\\w+")
                .all());

        ZhiHuUser zhiHuUser = new ZhiHuUser();

        Long st = System.currentTimeMillis();
        Selectable followees = page.getHtml().xpath("//div[@class=\"zu-main-sidebar\"]/div[@class=\"zm-profile-side-following zg-clear\"]/a[@class=\"item\"][1]/strong/text()");
        Selectable followers = page.getHtml().xpath("//div[@class=\"zu-main-sidebar\"]/div[@class=\"zm-profile-side-following zg-clear\"]/a[@class=\"item\"][2]/strong/text()");
        Selectable name = page.getHtml().xpath("//div[@class=\"zm-profile-header-main\"]/div[@class=\"top\"]/div[@class=\"title-section ellipsis\"]/span[@class=\"name\"]/text()");
        Selectable bio = page.getHtml().xpath("//div[@class=\"zm-profile-header-main\"]/div[@class=\"top\"]/div[@class=\"title-section ellipsis\"]/span[@class=\"bio\"]/text()");
        Selectable avatar = page.getHtml().xpath("//img[@class=\"avatar avatar--l\"]/@src");
        Selectable weibo = page.getHtml().xpath("//div[@class=\"zm-profile-header-main\"]/div[@class=\"top\"]/div[@class=\"weibo-wrap\"]/a[@class=\"zm-profile-header-user-weibo\"]/@href");
        Selectable agree = page.getHtml().xpath("//div[@class=\"zm-profile-header-info-list\"]/span[@class=\"zm-profile-header-user-agree\"]/strong/text()");
        Selectable thanks = page.getHtml().xpath("//div[@class=\"zm-profile-header-info-list\"]/span[@class=\"zm-profile-header-user-thanks\"]/strong/text()");


        //转格式
        Integer followees1 = Integer.valueOf(followees.toString());
        Integer followers1 = Integer.valueOf(followers.toString());
        String name1 = name.toString();
        String bio1 = bio.toString();
        String avatar1 = avatar.toString();
        String weibo1 = weibo.toString();
        Integer agree1 = Integer.valueOf(agree.toString());
        Integer thanks1 = Integer.valueOf(thanks.toString());

        //存到entiry

        zhiHuUser.setSt(st);
        zhiHuUser.setFollowees(followees1);
        zhiHuUser.setFollowers(followers1);
        zhiHuUser.setName(name1);
        zhiHuUser.setBio(bio1);
        zhiHuUser.setAvatar(avatar1);
        zhiHuUser.setWeibo(weibo1);
        zhiHuUser.setAgree(agree1);
        zhiHuUser.setThanks(thanks1);

        try {
            zhiHuUserDao.addZhiHuUser(zhiHuUser);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        page.putField("st", System.currentTimeMillis());
        page.putField("followees", page.getHtml().xpath("//div[@class=\"zu-main-sidebar\"]/div[@class=\"zm-profile-side-following zg-clear\"]/a[@class=\"item\"][1]/strong/text()"));
        page.putField("followers", page.getHtml().xpath("//div[@class=\"zu-main-sidebar\"]/div[@class=\"zm-profile-side-following zg-clear\"]/a[@class=\"item\"][2]/strong/text()"));
        page.putField("name", page.getHtml().xpath("//div[@class=\"zm-profile-header-main\"]/div[@class=\"top\"]/div[@class=\"title-section ellipsis\"]/span[@class=\"name\"]/text()"));
        page.putField("bio", page.getHtml().xpath("//div[@class=\"zm-profile-header-main\"]/div[@class=\"top\"]/div[@class=\"title-section ellipsis\"]/span[@class=\"bio\"]/text()"));
        page.putField("avatar", page.getHtml().xpath("//img[@class=\"avatar avatar-l\"]/@src"));
        page.putField("weibo", page.getHtml().xpath("//div[@class=\"zm-profile-header-main\"]/div[@class=\"top\"]/div[@class=\"weibo-wrap\"]/a[@class=\"zm-profile-header-user-weibo\"]/@href"));
        page.putField("agree", page.getHtml().xpath("//div[@class=\"zm-profile-header-info-list\"]/span[@class=\"zm-profile-header-user-agree\"]/strong/text()"));
        page.putField("thanks", page.getHtml().xpath("//div[@class=\"zm-profile-header-info-list\"]/span[@class=\"zm-profile-header-user-thanks\"]/strong/text()"));
    }

    public Site getSite() {
        return site;
    }
}