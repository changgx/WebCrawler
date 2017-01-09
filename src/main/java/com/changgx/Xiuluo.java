package com.changgx;

import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by changgx on 2017/1/7.
 */
public class Xiuluo {
    public static void main(String[] args) throws IOException {
        String startpage="http://www.u17.com/comic/19952.html";

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
                if(!html.contains("buy\\_chapter\\_choice.php")){
                    String imglist=html.split("image_list:")[1].split("image_pages:")[0];
                    imglist=  imglist.replaceAll("\\$\\.evalJSON\\(\\'","").replaceAll("\\'\\),","");
                    JSONObject json=JSONObject.fromObject(imglist);
                    System.out.println(json);
                    for (int i = 1; i <=json.keySet().size(); i++) {
                        String imgurl=json.getJSONObject(i+"").getString("");
                        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
                        imgurl = new String(decoder.decodeBuffer(imgurl));
                        Utils.download(imgurl,"E://comic//ÐÞÂÞ");
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Download("http://img6.u17i.com/10/02/3166/2786_1265127869_OAHwXW5Lb5Ez.83841_svol.jpg");
    }
}
