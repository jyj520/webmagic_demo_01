package cn.panda.test01;

import cn.panda.dao.BlogDao;
import cn.panda.entity.Blog;
import cn.panda.utils.HtmlUntil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.sql.SQLException;


public class Test02 implements PageProcessor {

    // public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

    public static final String URL_LIST_01 = "http://blog\\.sina\\.com\\.cn/s/articlelist_1242301364_0_\\d+\\.html";

    public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    int i = 0;

    BlogDao blogDao = new BlogDao();

    private Site site = Site
            .me()
            .setDomain("blog.sina.com.cn")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    public static void main(String[] args) {
        Spider.create(new Test02()).addUrl("http://blog.sina.com.cn/s/articlelist_1242301364_0_1.html")
                .run();
    }

    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST_01).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST_01).all());
            //文章页
        } else {
            Blog blog = new Blog();

            Selectable title = page.getHtml().xpath("//div[@class='articalTitle']/h2");
            Selectable content = page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']");
            Selectable date = page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']");

            String title1 = title.toString();
            String content1 = content.toString();
            String date1 = date.toString();

            String title2 = HtmlUntil.splitAndFilterString(title1);
            String content2 = HtmlUntil.splitAndFilterString(content1);
            String date2 = HtmlUntil.splitAndFilterString(date1);

            blog.setTitle(title2);
            blog.setContent(content2);
            blog.setDate(date2);

            try {
                blogDao.addBlog(blog);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            System.out.println(i + ">>>" + title2 + ">>>" + content2 + ">>>" + date2);
            /*
            page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
            page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
            page.putField("date",
                    page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
                    */
        }
    }

    public Site getSite() {
        return site;
    }
}
