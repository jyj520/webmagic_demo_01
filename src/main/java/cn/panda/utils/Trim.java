package cn.panda.utils;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class Trim {


    public static void main(String[] args) {


        String xxx = "1.2,        0.55,           0.58,       0.9,        0.58,   0.68,       0.55,   0.65,       0.52,       0.53,       0.55,       1.25,0.45,      0.54,       0.53,       0.51,       0.69,   0.52,       0.71,   0.42,       0.82,       0.5,    0.75,       0.4,    0.78,       0.75,   0.71,       0.52,    1.1,      0.54,    1.42,    0.74,       0.5,       1.38,       0.7,        0.6,    0.7,    0.58,        0.4,      0.5,   0.7,      1.15,         0.66,    1.3,       0.63,      0.6,         0.68,      0.8,       0.56,     0.48,        0.43,   0.9,        0.62,     0.5,       1.2,   0.43,     1.2,       1.3,        1.0,        0.56";

        xxx.replaceAll("    ", "");
        System.out.println(xxx.contains("   "));
        System.out.println(xxx);
    }
}
