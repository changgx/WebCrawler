package com.changgx;

import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by changgx on 2017/1/5.
 */
public class Zhenhunjie {
    public static void main(String[] args) throws IOException {
        String startpage="http://www.u17.com/comic/3166.html";

        try {
            Connection.Response res= Utils.getResponse(startpage);
            Document document=res.parse();
            Element element=document.getElementById("chapter");
            Elements elements=element.select("li");
            for (int n = 0; n <elements.size() ; n++) {
                String chapter=elements.get(n).select("a").attr("href");
                res= Utils.getResponse(chapter);
                document=res.parse();
                String html=document.toString();
                String imglist=html.split("image_list:")[1].split("image_pages:")[0];
                imglist=  imglist.replaceAll("\\$\\.evalJSON\\(\\'","").replaceAll("\\'\\),","");
                JSONObject json=JSONObject.fromObject(imglist);
                System.out.println(json);
                for (int i = 1; i <=json.keySet().size(); i++) {
                    String imgurl=json.getJSONObject(i+"").getString("");
                    sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
                    imgurl = new String(decoder.decodeBuffer(imgurl));
//                    Utils.download(imgurl,"E://comic//镇魂街");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
       // Download("http://img6.u17i.com/10/02/3166/2786_1265127869_OAHwXW5Lb5Ez.83841_svol.jpg");
    }
}
