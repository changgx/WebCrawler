package com.changgx;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by changgx on 2017/1/6.
 */
public class MissMystic2 {
    public static void main(String[] args) throws IOException {
        String url = "https://raws.yomanga.co/read/miss_mystic/ko/0/";

        for (int i = 1; i <= 27; i++) {
            String startpage = url + i + "/page/1";
            String html = HttpClientUtils.httpGet(startpage);
            html = html.split("var pages \\= 1\\;")[1].split("var next_chapter")[0].split("\\=")[1].split("\\;")[0];
//            html=html.substring(0,html.length()-2);
            JSONArray info = JSONArray.fromObject(html);
            String[] urlarray = new String[info.size()];
            for (int j = 0; j < info.size(); j++) {
                String tmp = info.getJSONObject(j).getString("url");
                urlarray[j] = tmp;
            }
            Arrays.sort(urlarray);
            for (int j = 0; j < urlarray.length; j++) {
                Utils.download(urlarray[j],"E://comic//MissMystic");
            }
        }

    }
}
