package cn.panda.utils;

/**
 * Created by Administrator on 2016/5/31 0031.
 */

import java.io.*;

/**
 * 处理储存爬取链接的txt文件，将房源列表页的链接全部删除
 */
public class RefreshLinks {

    /**
     * 删除房源列表页的链接
     * @return
     */
    public static String deletePageLinks(String filePathAndName,String linkRegex) throws IOException {

            try{

                File file = new File(filePathAndName);
                if(!file.exists()){
                    file.createNewFile();
                }
                //读取器
                Reader reader = new FileReader(file);
                //带缓冲区的读取器
                BufferedReader bufferedReader = new BufferedReader(reader);
                //临时字符串
                String tempString = null;
                //新StringBuffer
                StringBuffer stringBuffer = new StringBuffer();
                //开始读取
                while((tempString = bufferedReader.readLine())!=null){
                    stringBuffer.append(tempString+"\n");
                }

                //stringbuffer转string
                String txtString = stringBuffer.toString();

                //对字符串进行处理
                txtString = txtString.replaceAll(linkRegex+"\n","");

                //BufferedWriter
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                if(file.exists()){
                    file.delete();
                    file.createNewFile();
                    bufferedWriter.write(txtString);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
                return "刷新链接txt成功";

            }catch (Exception e){
                throw new RuntimeException(e);
            }

        }

}
