package com.changgx;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 长歌行
 * changgx2014@163.com
 * 2017/1/5 17:55
 */
public class Youyaoqi {
    public static void main(String[] args) throws IOException {
        String url="http://www.u17.com/chapter/9963.html#image_id=83276";
        Connection.Response res=Jsoup.connect(url).timeout(5000).execute();
        Document doc=res.parse();
        Element element=doc.getElementsByClass("comic_read_img").get(0);
        Elements imgs=element.select("img");
        String imgid=element.attr("object_id");
        System.out.println(imgid);
        for(int i=0;i<imgs.size();i++){
            String tmpid=imgs.get(i).attr("src").split("_")[2];
            System.out.println(tmpid);
            System.out.println(imgs.get(i).attr("src"));
            if(tmpid==imgid){
                System.out.println("==================");
                System.out.println("==="+imgs.get(i).attr("src"));
                System.out.println("==================");
            }


        }
//        Download(imgurl);
    }
    private static void Download(String url) throws IOException {
        System.out.println("img:"+url );
        try {
            URL uri = new URL(url);
            InputStream in = uri.openStream();

            FileOutputStream fo = new FileOutputStream(new File("./1.jpg"));
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            in.close();
            fo.close();

        } catch (Exception e) {
            Download(url);
            e.printStackTrace();
        }


    }
}
