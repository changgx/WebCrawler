package com.changgx;/**
 * Created by Administrator on 2016/10/26.
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.URL;

/**
 * Administrator 2016/10/26
 */
public class ScandaloftheWitch {
    public static int count = 1;

    public static void main(String[] args) {
        String start = "http://hentaihere.com/m/S16102/";
        for (int i = 1; i < 25; i++) {
            String tmpurl = start + i + "/1";
            Connection.Response res = null;
            try {
                res = getResponse(tmpurl);
                Document doc = res.parse();
                Element tmp = doc.getElementsByClass("list-inline").get(0);
                int page = tmp.select("li").size();
                for (int j = 1; j <= page; j++) {
                    getNextAndImg(start + i + "/" + j);
                }
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
                    .execute();
            return res;
        } catch (IOException e) {
            System.out.println(url);
            e.printStackTrace();
           return getResponse(url);
        }
    }

    public static void getNextAndImg(String url) {
        Connection.Response res = null;
        try {
            res = Jsoup.connect(url)
                    .method(Connection.Method.POST)
                    .execute();
            Document doc = res.parse();
            Element element = doc.getElementById("arf-reader-img");
            String imgurl = element.select("img").attr("src");
//            System.out.println(imgurl);
//            writeFile(imgurl);
            Utils.download(imgurl,"E:\\comic\\ScandaloftheWitch");
        } catch (Exception e) {
            System.out.println(url);
            e.printStackTrace();
            getNextAndImg(url);
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
