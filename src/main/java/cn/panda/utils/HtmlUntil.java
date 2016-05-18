package cn.panda.utils;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class HtmlUntil {

    public static String splitAndFilterString(String input) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "").replaceAll("[(/>)<]", "");
        return str;
    }
}
