package com.changgx;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class Utils {
    public static int count=1;

    public static void main(String[] args) throws Exception {
//        File old=new File("E://1.txt");
//        old.renameTo(new File("E://2.txt"));
////        System.out.println(getRealURL("http://www.u17.com/chapter/102843.html"));
       // System.out.println(Jsoup.connect("http://www.u17.com/buy_chapter_choice.php?chapter_id=103884#image_id=810882").timeout(5000).execute().parse());
        download("http://2.bp.blogspot.com/-K9e1EWvngkE/V81C1-UphtI/AAAAAABThmo/RVzsnQGKegU/w1900/MR-361620-798758-1.jpg", "./");
    }
    public static void download(String imgurl,String path){
        URLConnection uc;
        System.out.println("Getting content for URl : " + imgurl);
        try {
            URL url = new URL(imgurl);
            uc = url.openConnection();
            uc.setConnectTimeout(10*1000);
//            uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");
            uc.connect();
            uc.getInputStream();
            BufferedInputStream in = new BufferedInputStream(uc.getInputStream());
            String tmp = "" + count;
            String name = tmp;
            for (int i = 0; i < 5 - tmp.length(); i++) {
                name = "0" + name;
            }
            File file=new File(path + "/" + name + ".jpg");
            if(!file.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                System.out.println("目标文件所在目录不存在，准备创建它！");
                if(!file.getParentFile().mkdirs()) {
                    System.out.println("创建目标文件所在目录失败！");
                }
            }
            FileOutputStream fo = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            in.close();
            fo.flush();
            fo.close();
            System.out.println(name + ".jpg download end. "+new Date());
            count++;
        } catch (Exception e) {
            e.printStackTrace();
            download(imgurl, path);
        }

    }
    public static Connection.Response getResponse(String url){
        Connection.Response res = null;
        System.out.println("response:"+url);
        try {
            res = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();
            return res;
        } catch (IOException e) {
            System.out.println(url);
            e.printStackTrace();
            return getResponse(url);
        }
    }




}