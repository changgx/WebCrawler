package com.changgx;
/**
 * Created by Administrator on 2016/10/26.
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;

/**
 * Administrator 2016/10/26
 */
public class DownloadComic {
    public static String savePath="/Users/changgexing/娱乐/comic/Bastards_dead_man/";


    public static void main(String[] args) throws Exception {
//        ImagesUtil.cutImages("/Users/changgexing/娱乐/comic/DownloadComic",
//                "/Users/changgexing/娱乐/comic/ScandaloftheWitch_cut/");
        String start = "http://manganelo.com/chapter/bastards_dead_man/chapter_";
        for (int i = 0; i < 19; i++) {
            String tmpurl = start + i;
            Connection.Response res = null;
            try {
                res = getResponse(tmpurl);
                Document doc = res.parse();
                Elements elements = doc.getElementsByClass("vung-doc").select("img");
                for (Element el :
                        elements) {
                    writeFile(el.attr("src"));
//                    getNextAndImg(el.attr("src"));
                }
//                int page = tmp.select("li").size();
//                for (int j = 1; j <= page; j++) {
//                    getNextAndImg(start + i + "/" + j);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }
    public static Connection.Response getResponse(String url){
        Connection.Response res = null;
        System.out.println("response:"+url);
        try {
            res = Jsoup.connect(url)
                    .method(Connection.Method.POST).timeout(10000)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .execute();
            return res;
        } catch (IOException e) {
            System.out.println(url);
            e.printStackTrace();
           return getResponse(url);
        }
    }

    public static void getNextAndImg(String url) {
//        Connection.Response res = null;
        try {
//            res = Jsoup.connect(url)
//                    .method(Connection.Method.POST).timeout(10000)
//                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
//                    .execute();
//            Document doc = res.parse();
//            Element element = doc.getElementById("arf-reader-img");
//            String imgurl = element.select("img").attr("src");
//            System.out.println(imgurl);
////            writeFile(imgurl);
            Utils.download(url,savePath);
        } catch (Exception e) {
            System.out.println(url);
            e.printStackTrace();
//            getNextAndImg(url);
            Utils.download(url,savePath);
        }


    }
    public static void writeFile(String url){
        try {
            PrintStream ps=new PrintStream(new File("./url.txt"));
            ps.append(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
