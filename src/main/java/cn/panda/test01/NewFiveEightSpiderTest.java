package cn.panda.test01;

import cn.panda.dao.FiveEightHousesDao;
import cn.panda.entity.FiveEightHouses;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class NewFiveEightSpiderTest implements PageProcessor {



    //准备dao
    FiveEightHousesDao fiveEightHousesDao = new FiveEightHousesDao();

    //详情页链接
    String detailPageUrl = "http://m.58.com/ta/ershoufang/\\w+.shtml";

    //计数准备

    private int i = 1;

    //设置site
    private Site site = Site.me().
            setRetryTimes(3).
            setTimeOut(3000).
            setSleepTime(1000).
            setCharset("utf-8").
            setUserAgent("Mozilla/5.0(iPhone;U;CPUiPhoneOS4_0likeMacOSX;en-us)AppleWebKit/532.9(KHTML,likeGecko)Version/4.0.5Mobile/8A293Safari/6531.22.7");


    //核心逻辑
    public void process(Page page) {

            page.addTargetRequests(page.getHtml().regex(detailPageUrl).all());

            if(page.getUrl().regex(detailPageUrl).match()){
                FiveEightHouses fiveEightHouses = new FiveEightHouses();

                //原始链接
                String originalLink = page.getUrl().toString();


                //标题

                String title = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-name whitebg']//div[@class='tit_area']//h1/text()").toString();

                        //发布时间
                String publishDate = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-name whitebg']//dl[@class='name-info']//dd[1]/text()").toString();

                        //售价
                String price = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-infor whitebg mt15']//ul[@class='infor-price']//li[1]//i/text()").toString();

                        //单价
                String mprice = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-infor whitebg mt15']//ul[@class='infor-other two-row']//li[1]//i[@class='black']//span[@id='unitprice']/text()").toString();

                        //面积
                String area = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-infor whitebg mt15']//ul[@class='infor-price']//li[3]//i/text()").toString();

                        //联系人
                String contactName = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-infor whitebg mt15']//div[@class='infor-contact mtlr15']//ul[@class='contact']//li[@class='black']/text()").toString();

                        //手机号
                String phone = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-infor whitebg mt15']//div[@class='infor-contact mtlr15']//ul[@class='contact']//li[@class='yellow']/text()").toString();


                fiveEightHouses.setOriginalLink(originalLink);
                fiveEightHouses.setTitle(title);
                fiveEightHouses.setPublishDate(publishDate);
                fiveEightHouses.setPrice(price);
                fiveEightHouses.setMprice(mprice);
                fiveEightHouses.setArea(area);
                fiveEightHouses.setContactName(contactName);
                fiveEightHouses.setPhone(phone);


                System.out.println("第"+(i++)+"条房源：>>>>>>>>>>>"+fiveEightHouses);

                try {
                    fiveEightHousesDao.madd(fiveEightHouses);
                    System.out.println("存入第"+(i++)+"条");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }



    }

    public Site getSite() {
        return site;
    }




    //主程序
    public static void main(String[] args) {


        Spider.create(new NewFiveEightSpiderTest()).addUrl("http://m.58.com/ta/ershoufang/0/").
                setScheduler(new FileCacheQueueScheduler("d:\\m58houses")).
                thread(5).run();



    }



}
