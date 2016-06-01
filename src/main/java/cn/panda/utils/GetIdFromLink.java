package cn.panda.utils;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class GetIdFromLink {


            public static String getId(String link,String replaceString){

                String id = link.replace(replaceString,"").replace(".shtml","");

                return id;

            }


}
