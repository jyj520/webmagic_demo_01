package cn.panda.test01;

/**
 * Created by Administrator on 2016/7/19.
 */
import cn.panda.dao.FiveEightHousesDao;
import cn.panda.entity.FiveEightHouses;
import cn.panda.utils.GetIdFromLink;
import cn.panda.utils.RefreshLinks;


import com.google.common.collect.Sets;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import org.apache.http.protocol.HttpContext;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2016/7/2.
 * test one day in one city house heat with specify strict or key name
 */
public class FiveEightSecondHourse implements PageProcessor{ 
	private String fiveEightLink = "http://gz.58.com/ershoufang/0/pn\\d+";

    private String houseLink = "http://gz.58.com/ershoufang/\\w+.shtml";

    private String businessUrl = "http://gz.58.com/ershoufang/";

    //匹配js的统计计算脚本请求
    private String jsCountHead = "counter?infoid=";
    private String jsCountEnd = "&userid=0&uname=&sid=0&lid=0&px=0&cfpath=";

    private FiveEightHouses fiveEightHouses = new FiveEightHouses();
    private FiveEightHousesDao fiveEightHousesDao = new FiveEightHousesDao();

    private int i = 1;

    private Site site = Site.me().
            setCharset("utf-8").
            //setDomain("http://gz.58.com/").
            setRetryTimes(3).
            setSleepTime(200).
            setTimeOut(3000).
            addHeader("Referer","http://gz.58.com/ershoufang/").
            setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");

    private long hightPriority = 100000;       

    public static void main(String[] args) {

        CookieSpecProvider easySpecProvider = new CookieSpecProvider() {

            public CookieSpec create(HttpContext context) {

                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin)
                            throws MalformedCookieException {
                        // Oh, I am easy
                    }
                };
            }

        };
        Registry<CookieSpecProvider> reg = RegistryBuilder.<CookieSpecProvider>create()
                .register(CookieSpecs.BEST_MATCH,
                        new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY,
                        new BrowserCompatSpecFactory())
                .register("mySpec", easySpecProvider)
                .build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec("mySpec")
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieSpecRegistry(reg)
                .setDefaultRequestConfig(requestConfig)
                .build();

        Spider.create(new FiveEightSecondHourse()).addUrl("http://gz.58.com/ershoufang/0/").
                setScheduler(new FileCacheQueueScheduler("D:\\58houses")).
                thread(1).run();

    }

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        //匹配网页链接
        page.addTargetRequests(page.getHtml().regex(fiveEightLink).all());

        //匹配房源
        page.addTargetRequests(page.getHtml().regex(houseLink).all());

        String[] arr = page.getRawText().split(";");
        //String[] arr = page.getHtml().split(";");
        List list = Arrays.asList(arr);
        // for(String string : list){
        // if(string.contains("Counter58.total"))
        // }
        int iest;
        if (list.size()<3)
            iest = 100;
        if (page.getUrl().regex(houseLink).match()) {
            /*
            HttpHost proxyHost = null;
            Proxy proxy = null;
            CloseableHttpResponse httpResponse = null;
            Map<String, String> headers = null;
            String charset = null;
            int statusCode=0;

            if (site.getHttpProxyPool() != null && site.getHttpProxyPool().isEnable()) {
                proxy = site.getHttpProxyFromPool();
                proxyHost = proxy.getHttpHost();
            } else if(site.getHttpProxy()!= null){
                proxyHost = site.getHttpProxy();
            }

            HttpUriRequest httpUriRequest = getHttpUriRequest(request, site, headers, proxyHost);
            httpResponse = getHttpClient(site, proxy).execute(httpUriRequest);
            */

            //原始链接
            String originalLink = page.getUrl().toString();
            //取页的ID，如http://gz.58.com/ershoufang/26585753641919x的26585753641919x
            String houseLinkId = originalLink.substring(businessUrl.length(),originalLink.length());
            //onlyId
            String onlyId = GetIdFromLink.getId(originalLink,"http://ta.58.com/ershoufang/");

            //M版58链接

            String mlink = "http://m.58.com/ta/ershoufang/"+onlyId+".shtml";

            //获取returnData

            String[] data = M58GetPhoneNum.getPhoneNum(mlink);

            //获取phoneNum
            String phoneNum = data[0];

            //面积
            String area = data[1];

            //单价
            String mprice = data[2];

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
            String villageName = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[5]//text()").toString().replace("-","").trim();

            //地址

            String houseAdress1= page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[5]//div[@class='su_con su_gbconwidth']/text()").toString();
            String houseAdress2= page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[6]//div[@class='su_con su_gbconwidth']/text()").toString();

            String houseAdress = null;
            if(houseAdress1==null && houseAdress2 != null){
                 houseAdress = houseAdress2;
            }else if(houseAdress1 != null && houseAdress2 == null){
                 houseAdress = houseAdress1;
            }

            //联系人
            String contactName = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[@class='liv0']//div[@class='su_con']//span//a/text()").toString();

            //手机号imgUrl
            String phone1 = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[9]//div[@class='su_con']//div[@id='movebar']//ul//li[@class='ico_tel']//div[@class='su_phone']//span[@id='t_phone']//img/@src").toString();
            String phone2 = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[8]//div[@class='su_con']//div[@id='movebar']//ul//li[@class='ico_tel']//div[@class='su_phone']//span[@id='t_phone']//img/@src").toString();
            String phone3 = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[7]//div[@class='su_con']//div[@id='movebar']//ul//li[@class='ico_tel']//div[@class='su_phone']//span[@id='t_phone']//img/@src").toString();



            String phone =  null;
            if(phone1 !=null){
                phone= phone1;
            }else if(phone2 != null){
                phone = phone2;
            }else if(phone3 != null){
                phone = phone3;
            }

 			//点击率
            //String totalClick = page.getHtml().xpath("//section[@class='main']//div[@class='col detailPrimary mb15']//div[@class='mainTitle']//div[@class='mtit_con c_999 f12 clearfix']//ul[@class='mtit_con_left fl']//li[2]//text()").toString();
            //String totalClick = page.getHtml().xpath("//section[@class='main']//div[@class='col detailPrimary mb15']//div[@class='mainTitle']//div[@class='mtit_con c_999 f12 clearfix']//ul[@class='mtit_con_left fl']//em//li[2]//text()").toString();
            //String totalClick = page.getHtml().xpath("//div[@class='AD_401010']//div[@class='content']//section[@class='main']//div[@class='col detailPrimary mb15']//div[@class='mainTitle']//div[@class='mtit_con c_999 f12 clearfix']//ul[@class='mtit_con_left fl']//li[2]//text()").toString();
            //String totalClick = page.getHtml().xpath("//div[@class='AD_401010']//div[@class='content']//section[@class='main']//div[@class='col detailPrimary mb15']//div[@class='mainTitle']//div[@class='mtit_con c_999 f12 clearfix']//ul[@class='mtit_con_left fl']//em//li[2]//text()").toString();
            String totalClick = page.getHtml().xpath("//div[@class='col detailPrimary mb15']//div[@class='mainTitle']//div[@class='mtit_con c_999 f12 clearfix']//ul[@class='mtit_con_left fl']//li[@class='count']//em//text()").toString().trim();

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
            if(descriptions.size()>0){
                for(int x= 0;x<descriptions.size();x++){
                    description = description+" , "+descriptions.get(x).replace("联系我时，请说是在58同城上看到的，谢谢！","").replace(",  , ","");
                }
            }


            //图片链接
            List<String> imgUlrs = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div[@class='descriptionImg']//img/@src").all();
            String   imgUlrString = "";
            if(imgUlrs.size()>0){
                for(int y = 0;y<imgUlrs.size();y++){
                    imgUlrString = imgUlrString+" , "+imgUlrs.get(y);
                }
            }
            //建筑结构
            String buildStructure = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[2]//ul//li[4]//text()").toString();


            //房屋类型
            String houseSellingType = page.getHtml().xpath("//div[@class='col_sub description']//section[@class='des_con']//div[@class='cur']//div//ul//li[2]//ul//li[2]//text()").toString();
            fiveEightHouses.setOriginalLink(originalLink);
            fiveEightHouses.setOnlyId(onlyId);
            fiveEightHouses.setTitle(title);
            fiveEightHouses.setPublishDate(publishDate);
            fiveEightHouses.setPrice(price);
            fiveEightHouses.setHouseType(houseType);
            fiveEightHouses.setQuyu(quyu);
            fiveEightHouses.setJiedao(jiedao);
            fiveEightHouses.setVillageName(villageName);
            fiveEightHouses.setContactName(contactName);
            fiveEightHouses.setHouseAdress(houseAdress);
            fiveEightHouses.setPhone(phoneNum);

            //面积
            fiveEightHouses.setArea(area);

            //单价
            fiveEightHouses.setMprice(mprice);

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
            //fiveEightHouses.setNowClickNum(totalClick);

            System.out.println(fiveEightHouses.toString());
           try {
                if(fiveEightHousesDao.housesExist(fiveEightHouses))
                {
				System.out.println("更新房源热度业务  "+fiveEightHouses.getOriginalLink());
                }
                else
                {
                //fiveEightHousesDao.upGradeClickHeart(fiveEightHouses);
                fiveEightHousesDao.add(fiveEightHouses);
                System.out.println("新增房源：    "+fiveEightHouses.getOriginalLink());
                }
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (SQLException e) {
               e.printStackTrace();
           }

            System.out.println("第"+(i++)+"条");

            Request jsRequest = new Request(jsCountHead+houseLinkId+jsCountEnd);
            jsRequest.setPriority(hightPriority);
            page.addTargetRequest(jsRequest);
            List<Request> requestList= page.getTargetRequests();
            int j = 1;
            for(Request quest : requestList)
                System.out.println("第"+i+"的"+"第"+(j++)+"条 URL"+quest.getUrl());

        }

        // 部分三：从页面发现后续的url地址来抓取
    }


    //去重

    public Site getSite() {
        return site;
    }
}