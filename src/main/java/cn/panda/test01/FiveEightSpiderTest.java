package cn.panda.test01;

import cn.panda.dao.FiveEightHousesDao;
import cn.panda.entity.FiveEightHouses;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27 0027.
 */

/**
 * 58房源爬虫测试
 */
public class FiveEightSpiderTest implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等

    //第一部分关于爬虫的配置，包括编码、抓取间隔、超时时间、重试次数等，
    // 也包括一些模拟的参数，例如User Agent、cookie，以及代理的设置，
    // 我们会在第5章-“爬虫的配置”里进行介绍。
    // 在这里我们先简单设置一下：重试次数为3次，抓取间隔为一秒。

    private String fiveEightLink = "http://ta.58.com/ershoufang/0/pn\\d+";

    private String houseLink = "http://ta.58.com/ershoufang/\\w+.shtml";

    private FiveEightHousesDao fiveEightHousesDao = new FiveEightHousesDao();


    private int i = 1;

    private Site site = Site.me().
            setCharset("utf-8").
            setRetryTimes(3).
            setSleepTime(1000).
            setTimeOut(3000).
            addHeader("Referer", "http://ta.58.com/ershoufang").
            setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");


    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        //匹配网页链接
        page.addTargetRequests(page.getHtml().regex(fiveEightLink).all());

        //匹配房源
        page.addTargetRequests(page.getHtml().regex(houseLink).all());

        if (page.getUrl().regex(houseLink).match()) {

            FiveEightHouses fiveEightHouses = new FiveEightHouses();

            //原始链接
            String originalLink = page.getUrl().toString();
            //标题
            String title = page.getHtml().xpath("//div[@class='col detailPrimary mb15']//div[@class='mainTitle']//div[@class='bigtitle']//h1/text()").toString();
            //发布时间
            String publishDate = page.getHtml().xpath("//div[@class='col detailPrimary mb15']//div[@class='mainTitle']//div[@class='mtit_con c_999 f12 clearfix']//ul[@class='mtit_con_left fl']//li[@class='time']/text()").toString().trim();
            //售价
            String price = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[1]//div[@class='su_con']//span[@class='bigpri arial']/text()").toString().trim();
            //户型
            String houseType = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[4]//div[@class='su_con']/text()").toString().trim();
            //区域
            String quyu = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[5]//a[1]/text()").toString();
            //街道
            String jiedao = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[5]//a[2]/text()").toString();
            //小区名
            String villageName = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[5]//text()").toString().replace("-", "").trim();

            //地址

            String houseAdress1 = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[5]//div[@class='su_con su_gbconwidth']/text()").toString();
            String houseAdress2 = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[6]//div[@class='su_con su_gbconwidth']/text()").toString();

            String houseAdress = null;
            if (houseAdress1 == null && houseAdress2 != null) {
                houseAdress = houseAdress2;
            } else if (houseAdress1 != null && houseAdress2 == null) {
                houseAdress = houseAdress1;
            }

            //联系人
            String contactName = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[@class='liv0']//div[@class='su_con']//span//a/text()").toString();

            //手机号imgUrl
            String phone1 = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[9]//div[@class='su_con']//div[@id='movebar']//ul//li[@class='ico_tel']//div[@class='su_phone']//span[@id='t_phone']//img/@src").toString();
            String phone2 = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[8]//div[@class='su_con']//div[@id='movebar']//ul//li[@class='ico_tel']//div[@class='su_phone']//span[@id='t_phone']//img/@src").toString();
            String phone3 = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[7]//div[@class='su_con']//div[@id='movebar']//ul//li[@class='ico_tel']//div[@class='su_phone']//span[@id='t_phone']//img/@src").toString();


            String phone = null;
            if (phone1 != null) {
                phone = phone1;
            } else if (phone2 != null) {
                phone = phone2;
            } else if (phone3 != null) {
                phone = phone3;
            }


            //装修类型
            String decType = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[1]//ul//li[4]//text()").toString();
            //住宅类别
            String houseCategory = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[1]//ul//li[2]//text()").toString();
            //产权年限
            String keepYear = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[4]//ul//li[2]//text()").toString();
            //楼层
            String floor = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[3]//ul//li[4]//text()").toString();
            //建造年代
            String buildYear = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[3]//ul//li[2]//text()").toString();
            //朝向
            String orientation = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[4]//ul//li[4]//text()").toString();
            //描述
            List<String> descriptions = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div[@class='descriptionBox']//article[@class='description_con ']//p/text()").all();
            String description = "";
            if (descriptions.size() > 0) {
                for (int x = 0; x < descriptions.size(); x++) {
                    description = description + " , " + descriptions.get(x).replace("联系我时，请说是在58同城上看到的，谢谢！", "").replace(",  , ", "");
                }
            }


            //图片链接
            List<String> imgUlrs = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div[@class='descriptionImg']//img/@src").all();
            String imgUlrString = "";
            if (imgUlrs.size() > 0) {
                for (int y = 0; y < imgUlrs.size(); y++) {
                    imgUlrString = imgUlrString + " , " + imgUlrs.get(y);
                }
            }
            //建筑结构
            String buildStructure = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[2]//ul//li[4]//text()").toString();


            //房屋类型
            String houseSellingType = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[2]//ul//li[2]//text()").toString();

            fiveEightHouses.setOriginalLink(originalLink);
            fiveEightHouses.setTitle(title);
            fiveEightHouses.setPublishDate(publishDate);
            fiveEightHouses.setPrice(price);
            fiveEightHouses.setHouseType(houseType);
            fiveEightHouses.setQuyu(quyu);
            fiveEightHouses.setJiedao(jiedao);
            fiveEightHouses.setVillageName(villageName);
            fiveEightHouses.setContactName(contactName);
            fiveEightHouses.setHouseAdress(houseAdress);
            fiveEightHouses.setPhone(phone);
            fiveEightHouses.setDecType(decType);
            fiveEightHouses.setHouseCategory(houseCategory);
            fiveEightHouses.setKeepYear(keepYear);
            fiveEightHouses.setFloor(floor);
            fiveEightHouses.setBuildYear(buildYear);
            fiveEightHouses.setOrientation(orientation);
            fiveEightHouses.setDescription(description);
            fiveEightHouses.setImgUlr(imgUlrString);
            fiveEightHouses.setBuildStructure(buildStructure);
            fiveEightHouses.setHouseSellingType(houseSellingType);
            // fiveEightHouses.setAddDate(new Date());
            fiveEightHouses.setIsDelete(0);
            fiveEightHouses.setIsUsed(0);

            System.out.println(fiveEightHouses);

            try {
                fiveEightHousesDao.add(fiveEightHouses);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("第" + (i++) + "条");
        }


        // 部分三：从页面发现后续的url地址来抓取
    }


    //去重

    public Site getSite() {
        return site;
    }

    //程序主入口
    public static void main(String[] args) throws IOException {
        //文件路径
        String filePathAndName = "d:\\58houses\\ta.58.com.urls.txt";
        //StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        //Reader
        Reader reader = null;
        //BufferReader
        BufferedReader bufferedReader = null;
        //File
        File file = new File(filePathAndName);
        if(!file.exists()){
            file.createNewFile();
        }

        try {
            reader = new FileReader(filePathAndName);

            bufferedReader = new BufferedReader(reader);

            String txtline = null;

            while ((txtline = bufferedReader.readLine()) != null) {
                stringBuffer.append(txtline + "\n");
            }

            String txtString = stringBuffer.toString();
            txtString = txtString.replaceAll("http://ta.58.com/ershoufang/0/\\w*\n", "");


            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));


            if (file.exists()) {
                file.delete();
                file.createNewFile();
                bufferedWriter.write(txtString);
                bufferedWriter.flush();
                bufferedWriter.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //运行spider
        Spider.create(new FiveEightSpiderTest()).addUrl("http://ta.58.com/ershoufang/0/").
                setScheduler(new FileCacheQueueScheduler("D:\\58houses")).
                thread(5).run();

    }
}
