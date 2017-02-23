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
        System.out.println(getRealURL("http://www.u17.com/chapter/102843.html"));
       // System.out.println(Jsoup.connect("http://www.u17.com/buy_chapter_choice.php?chapter_id=103884#image_id=810882").timeout(5000).execute().parse());
        //download("http://cdn.lezhin.com/v2/comics/5119625127002112/episodes/5402470634487808/contents/scrolls/1?access_token=bb104bb9-3488-4065-8db3-5e7edec9ca42&purchased=false&q=30&updated=1477903509073", "./");
    }
    public static void download(String imgurl,String path){
        URLConnection uc;
        StringBuilder parsedContentFromUrl = new StringBuilder();
        System.out.println("Getting content for URl : " + imgurl);
        try {
            URL url = new URL(imgurl);
            uc = url.openConnection();
            uc.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            uc.connect();
            uc.getInputStream();
            BufferedInputStream in = new BufferedInputStream(uc.getInputStream());
            String tmp = "" + count;
            String name = tmp;
            for (int i = 0; i < 5 - tmp.length(); i++) {
                name = "0" + name;
            }
            File file=new File(path + "//" + name + ".jpg");
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
            fo.close();
            System.out.println(name + ".jpg download end");
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
                    .method(Connection.Method.GET).timeout(5000)
                    .execute();
            return res;
        } catch (IOException e) {
            System.out.println(url);
            e.printStackTrace();
            return getResponse(url);
        }
    }

    public static String getRealURL(String src) throws IOException {
        URL url = new URL(src);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.getResponseCode();
        String realUrl=conn.getURL().toString();
        conn.disconnect();
        return realUrl;
    }



}