package cn.panda.test01;

import cn.panda.entity.FiveEightHouses;
import cn.panda.utils.GetIdFromLink;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class M58GetPhoneNum implements PageProcessor {



    private static String[] returnData = new String[3];

    private Site site = Site.me().
            setRetryTimes(3).
            setTimeOut(3000).
            setSleepTime(1000).
            setCharset("utf-8").
            setUserAgent("Mozilla/5.0(iPhone;U;CPUiPhoneOS4_0likeMacOSX;en-us)AppleWebKit/532.9(KHTML,likeGecko)Version/4.0.5Mobile/8A293Safari/6531.22.7");


    public void process(Page page) {

        //手机号
        String phone = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-infor whitebg mt15']//div[@class='infor-contact mtlr15']//ul[@class='contact']//li[@class='yellow']/text()").toString();

        //面积

        String area = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-infor whitebg mt15']//ul[@class='infor-price']//li[3]//i/text()").toString();

        //单价

        String mprice = page.getHtml().xpath("//div[@class='body_div']//section[1]//div[@class='area-infor whitebg mt15']//ul[@class='infor-other two-row']//li[1]//i[@class='black']//span[@id='unitprice']/text()").toString();


        returnData[0] = phone;
        returnData[1] = area;
        returnData[2] = mprice;


    }

    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        System.out.println(getPhoneNum("http://m.58.com/ta/ershoufang/26152405006150x.shtml"));

    }


    public static String[] getPhoneNum(String targetLink){

        Spider.create(new M58GetPhoneNum()).addUrl(targetLink).thread(1).run();

        return returnData;

    }


}
