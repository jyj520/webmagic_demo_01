package cn.panda.utils;

import java.io.*;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class SqlString {


    public static void main(String[] args) throws IOException {

        String[] addresses = {"泰安三合御都","安居上上城","巨菱枫景园","国华时代","三里小区","乐园小区","灵芝小区","苹果园小区","恒大城小区","泰山公馆","东岳小区","普照家园","王庄小区","长城小区","华易青年城","御景龙城","东湖小区","泰山华侨城","圣地公寓","膏城花园","清真寺小区","清华苑","鲁郡嘉源","嘉和新城","九州家园","三联小区","五环小区","城中城","科大东院","华新新城","天外村","长城东区","新华城南郡","圣源美郡","灵芝街小区","灵山佳苑","尚座国际","名仕尚座","龙城国际","华侨城","花园洲","宝龙城市广场","武警佳苑","普照小区","青山乐园","奥林匹克花园","山景尚宅","糖茶站宿舍","南关小区","东岳鑫城","世纪康城","彩虹花园","交巡警宿舍","记者村","山景叠院","龙泉小区","华新和园","擂鼓石花园","煤田家苑","迎春社区"};
        Double[] mprice = { 1.2,        0.55,           0.58,       0.9,        0.58,   0.68,       0.55,   0.65,       0.52,       0.53,       0.55,       1.25,0.45,      0.54,       0.53,       0.51,       0.69,   0.52,       0.71,   0.42,       0.82,       0.5,    0.75,       0.4,    0.78,       0.75,   0.71,       0.52,    1.1,      0.54,    1.42,    0.74,       0.5,       1.38,       0.7,        0.6,    0.7,    0.58,        0.4,      0.5,   0.7,      1.15,         0.66,    1.3,       0.63,      0.6,         0.68,      0.8,       0.56,     0.48,        0.43,   0.9,        0.62,     0.5,       1.2,   0.43,     1.2,       1.3,        1.0,        0.56};


        File file = new File("d:\\sql.txt");
        if(!file.exists()){
            file.createNewFile();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            for(int i =0 ;i<addresses.length;i++){

                System.out.println("地址>>>>"+addresses[i]);
                System.out.println("单价>>>>"+mprice[i]);

                String address = addresses[i];
                Double price = mprice[i];


                String sql1 = "delete from fc_trademanage where houseAdress = '"+address+"' and mprice < "+(price-0.025)+";";
                String sql2 = "delete from fc_trademanage where houseAdress = '"+address+"' and mprice > "+(price+0.025)+";";

                bufferedWriter.write(sql1);
                bufferedWriter.newLine();
                bufferedWriter.write(sql2);
                bufferedWriter.newLine();
            }

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
