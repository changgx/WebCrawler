package com.kunteng.crawler;/**
 * Created by Administrator on 2016/10/26.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.*;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Administrator 2016/10/26
 */
public class Ehentai {
    public static int count=1;
//    public static void main(String[] args) throws Exception{
//        String start="http://hentaihere.com/m/S16102/";
//        for (int i = 1; i <2 ; i++) {
//            String tmpurl=start+i+"/1";
//            Connection.Response res = Jsoup.connect(tmpurl)
//                    .method(Connection.Method.POST)
//                    .execute();
//            Document doc = res.parse();
//            Element element= doc.getElementById("arf-reader-img");
//            Element tmp= doc.getElementsByClass("list-inline").get(0);
//            int page=tmp.select("li").size();
//            for (int j=1;j<=page;j++){
//                getNextAndImg(start+i+"/"+j);
//            }
//
//
//        }
//
//
//    }
    public static void getNextAndImg(String url) throws Exception{
        Connection.Response res = Jsoup.connect(url)
                .method(Connection.Method.POST)
                .execute();
        Document doc = res.parse();
        Element element= doc.getElementById("arf-reader-img");
        String imgurl=element.select("img").attr("src");
        Download(imgurl);

    }

    public static void main(String[] args) throws Exception{
        String url="http://hentaicdn.com/hentai/16102/1/hcdn0001.jpg";
        try {
            String imageName = url.substring(url.lastIndexOf("/") + 1,
                    url.length());

            URL uri = new URL(url);
            InputStream in = uri.openStream();
            FileOutputStream fo = new FileOutputStream(new File("2.jpg"));
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            in.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void  Download(String url) throws IOException {
        Connection.Response resultImageResponse = Jsoup.connect(url).ignoreContentType(true).execute();

        FileOutputStream out = (new FileOutputStream(new java.io.File("E://ScandaloftheWitch//"+count+".jpg")));
        out.write(resultImageResponse.bodyAsBytes());


    }



}
