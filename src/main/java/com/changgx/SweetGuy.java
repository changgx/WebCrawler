package com.changgx;

import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by changgx on 2017/9/25.
 */
public class SweetGuy {
    public static void main(String[] args) throws Exception {
//        String startpage="http://manganel.com/chapter/household_affairs/chapter_";
//        for (int n = 1; n <=72; n++) {
//            String tmpurl=startpage+n;
//            try {
//                String html = HttpClientUtils.httpGet(tmpurl);
//                Document doc = Jsoup.parse(html);
//                Element element=doc.getElementById("vungdoc");
//                Elements elements=element.getElementsByAttribute("src");
//                for (int i = 0; i < elements.size(); i++) {
////                    String url=elements.get(i).attr("src").replace("http","https");
//                    String url=elements.get(i).attr("src");
//                    Utils.download(url,"E:\\comic\\household_affairs");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        ImagesUtil.cutImages("E:\\comic\\seductive_uniform","E:\\comic\\seductive_uniform_cut\\");

    }
}
