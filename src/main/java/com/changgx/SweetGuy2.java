package com.changgx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by changgx on 2017/9/25.
 */
public class SweetGuy2 {
    public static void main(String[] args) throws Exception {
        String startpage="http://manganel.com/chapter/drug_candy/chapter_";
        for (int n = 1; n <=45; n++) {
            String tmpurl=startpage+n;
            try {
                String html = HttpClientUtils.httpGet(tmpurl);
                Document doc = Jsoup.parse(html);
                Element element=doc.getElementById("vungdoc");
                Elements elements=element.getElementsByAttribute("src");
                for (int i = 0; i < elements.size(); i++) {
                   String url=elements.get(i).attr("src").replace("http","https");
                  //  String url=elements.get(i).attr("src");
                    Utils.download(url,"E:\\comic\\drug_candy");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ImagesUtil.cutImages("E:\\comic\\drug_candy","E:\\comic\\drug_candy_cut\\");

    }
}
