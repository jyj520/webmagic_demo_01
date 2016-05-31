package cn.panda.test;

import cn.panda.test01.FiveEightSpiderTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class TimerTest01 {

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.schedule(new Mytask(),1000,60*1000);

    }


}


class Mytask extends TimerTask{

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run() {

        //时间
        System.out.println(simpleDateFormat.format(new Date()));

        //运行爬虫
        try {
            FiveEightSpiderTest.fiveeightSpider();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}