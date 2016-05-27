package cn.panda.daotest;

import cn.panda.dao.TradeManageDao;
import cn.panda.entity.TradeManage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class TradeManageInsert {


    public static void main(String[] args) throws InterruptedException, ParseException, SQLException, ClassNotFoundException {


        TradeManageDao tradeManageDao = new TradeManageDao();

        DecimalFormat df = new DecimalFormat("0.0");


        int i = 1;
        for(int y = 1;y<30;y++){

            Double area = getArea();
            Double totalPrice = getTotalPrice();
            Double mprice = totalPrice/area;
            Date chargeTime = getDate();

            //new
            TradeManage tradeManage = new TradeManage();

            tradeManage.setSn(String.valueOf(i++));
            tradeManage.setBuyName(getName());
            tradeManage.setBuyPhone(getPhone());
            tradeManage.setSellName(getName());
            tradeManage.setSellPhone(getPhone());
            tradeManage.setHouseAdress(getAdress());
            tradeManage.setOtherAndDec(getDec());
            tradeManage.setArea(area);
            tradeManage.setMprice(mprice);
            tradeManage.setTotalPrice(totalPrice);
            tradeManage.setIsLoan(getIsLoan());
            tradeManage.setServiceFeeType("三项服务");
            tradeManage.setChargedMoney(3000.0);
            tradeManage.setChargeTime(chargeTime);
            tradeManage.setHouseAgent(getHouseAgent());
            tradeManage.setServicePerson(getServiceMan());
            tradeManage.setCustomerServiceIn("无");
            tradeManage.setDoneTime(chargeTime);
            tradeManage.setTracking("无");
            tradeManage.setAddTime(chargeTime);
            tradeManage.setIsDelete(0);
            tradeManage.setTotalServiceFeeCharge(3000.0);

            tradeManageDao.add(tradeManage);

            System.out.println("第"+i+"条已插入！");

        }





/*
        while(true){

            System.out.println(getIsLoan());

        }
*/




    }

        //获取名字
        public static String getName(){
            String[] familyName = {"赵","赵","赵","朱","孙","孙","孙","钱","李","李","李","李","李","梁","白","王","王","王","王","陈","孔","郑","周","吴","刘","魏","代","牛","杨","葛","马","范","张","张","张","张","张","何","贾","胡","袁","国","时","戚","洒","田","靳"};
            String[] middleName = {"雯","海","鹏","丽","国","君","悦","凯","桂","运","其","开","真","传","爱","德","兴","智","东","浩","诗","志","新","玉","梦","德","琦","晨","辰","颖","庆","玉"};
            String[] lastName = {"","","","","","","东","鹏","思","乐","强","晓","荣","健","胜","雨","树","环","山","桃","涛","源","华","芝","伟","蓝","泉","宇","恒","民","洁","英","河","飞","顺","平","峰","勇","楚","娟","明","翠","慧","美","梅","芳","东","希","楠","齐","琦","健","锋"};

            String family =  familyName[(int)(Math.random()*familyName.length)];
            String middle = middleName[(int)(Math.random()*middleName.length)];
            String last = lastName[(int)(Math.random()*lastName.length)];
            String name = (family+middle+last).trim();

            return name;
        }


        public static String getPhone(){

            String[] phoneHead = {"1305384","1304400","1301177","1305389","1314538","1318180","1337102","1337538","1351538","1358383","1367548","1373443","1379211","1385482","1395384","1509477","1528892","1526548","1558857","1566659","1585383","1596315","1826389","1875382"};

            String preHead = phoneHead[(int)(Math.random()*phoneHead.length)];

            String x1 = String.valueOf((int)(Math.random()*10));
            String  x2 = String.valueOf((int)(Math.random()*10));
            String x3 = String.valueOf((int)(Math.random()*10));
            String x4 = String.valueOf((int)(Math.random()*10));

            String xx = x1+x2+x3+x4;
            String phone = preHead+xx;
            return phone;
        }


        public static String getAdress(){

            String[] addresses = {"泰安三合御都","安居上上城","巨菱枫景园","国华时代","三里小区","乐园小区","灵芝小区","苹果园小区","恒大城小区","泰山公馆","东岳小区","普照家园","王庄小区","长城小区","华易青年城","御景龙城","东湖小区","泰山华侨城","圣地公寓","膏城花园","清真寺小区","清华苑","鲁郡嘉源","嘉和新城","九州家园","三联小区","五环小区","城中城","科大东院","华新新城","天外村","长城东区","新华城南郡","圣源美郡","灵芝街小区","灵山佳苑","尚座国际","名仕尚座","龙城国际","华侨城","花园洲","宝龙城市广场","武警佳苑","普照小区","青山乐园","奥林匹克花园","山景尚宅","糖茶站宿舍","南关小区","东岳鑫城","世纪康城","彩虹花园","交巡警宿舍","记者村","山景叠院","龙泉小区","华新和园","擂鼓石花园","煤田家苑","迎春社区"};

            String address = addresses[(int)(Math.random()*addresses.length)];

            return address;

        }


        public static Date getDate() throws ParseException {

            String[] years = {"2015","2016"};

            String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};

            String[] days = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};


          /*  String year = years[(int)(Math.random()*years.length)];*/
            String year = "2016";
            String month = "05";

/*            if(year=="2015" || year.equals("2015")){
                 month = months[(int)(Math.random()*8+4)];
            }else{

                 month = months[(int)(Math.random()*5)];
            }*/


            String day = days[(int)(Math.random()*days.length)];

            String dateString = year+"-"+month+"-"+day;

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");

            Date date  = sdf.parse(dateString);

            return date;
        }


        public static String getDec(){

            String[] decs = {"精装修","简装","毛坯","豪华装修","中档装修"};
            String dec = decs[(int)(Math.random()*decs.length)];

            return dec;
        }


        public static Double getArea(){

            Double d1 = (Math.random()*90+70);

            BigDecimal bigDecimal = new BigDecimal(d1);

            double d2 = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
            return d2;
        }


    public static Double getTotalPrice(){

        Double d1 = (Math.random()*185+32);

        BigDecimal bigDecimal = new BigDecimal(d1);

        double d2 = bigDecimal.setScale(1,BigDecimal.ROUND_HALF_DOWN).doubleValue();
        return d2;
    }

    public static String  getHouseAgent(){

        String[] agents = {"赵宇","郑进东","耿志兴","陈信洋","白建涛","李子锟","张强","孔德胜","张仁彬","韩竟","宋筱","朱振","刘青","时晓","薛丹丹","武大钊","李健鹏","李峰","刘甲","任延荣","周玲"};

        String agent = agents[(int)(Math.random()*agents.length)];

         return agent;
    }


    public static String getIsLoan(){

        String[] isLoans = {"商业贷款","公积金贷款","一次性付款"};

        String isLoan = isLoans[(int)(Math.random()*isLoans.length)];

        return isLoan;
    }

    public static String getServiceMan(){

        String[] serviceMans = {"陈信洋","孔德胜"};

        String serviceMan = serviceMans[(int)(Math.random()*serviceMans.length)];

        return  serviceMan;

    }

}
