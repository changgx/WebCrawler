package com.changgx;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by changgx on 2017/1/6.
 */
public class MissMystic {
    public static void main(String[] args) throws IOException {
        String url = "http://mangapark.me/manga/miss-mystic/s1/c";

        for (int i = 1; i < 2; i++) {
            String startpage = url + i + "/1";
            Connection.Response response = Utils.getResponse(startpage);
            Document doc = response.parse();
            Element element = doc.getElementsByClass("img-num").get(0);
            String tmp = element.text();
            int page = Integer.parseInt(tmp.split("\\/")[1].replace(" ",""));
            for (int j = 1; j <= page; j++) {
                startpage = url + i + "/" + j;
                response = Utils.getResponse(startpage);
                doc = response.parse();
                Element div=doc.getElementById("img-1");
                String imgurl=div.attr("src");
                Utils.download(imgurl,"E://comic//MissMystic");
            }
        }

    }
}
