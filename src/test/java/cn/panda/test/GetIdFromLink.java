package cn.panda.test;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class GetIdFromLink {





    public static void main(String[] args) {
        String link= "http://m.58.com/ta/ershoufang/25547323373379x.shtml";

        //link = link.replaceAll("http\\:\\/\\/m\\.58\\.com\\/ta\\/ershoufang\\/","").replaceAll("\\.shtml","");

        link = link.replace("http://m.58.com/ta/ershoufang/","").replace(".shtml","");

        System.out.println(link);

    }


}
