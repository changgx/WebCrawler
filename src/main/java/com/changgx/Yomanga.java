package com.changgx;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.Arrays;

/**
 * 长歌行
 * changgx2014@163.com
 * 2017/2/22 15:06
 */
public class Yomanga {
    public static void main(String[] args) throws IOException {
//        download("scandal_of_the_witch",42);//下载到37，38章需要站好密码
//        download("");
    }

    public static void download(String name,int page){
        String url = "https://raws.yomanga.co/read/"+name+"/ko/0/";
        for (int i = 1; i <= page; i++) {
            String startpage = url + i + "/page/1";
            String html = HttpClientUtils.httpGet(startpage);
            html = html.split("var pages \\= 1\\;")[1].split("var next_chapter")[0].split("\\=")[1].split("\\;")[0];
            JSONArray info = JSONArray.fromObject(html);
            String[] urlarray = new String[info.size()];
            for (int j = 0; j < info.size(); j++) {
                String tmp = info.getJSONObject(j).getString("url");
                urlarray[j] = tmp;
            }
            Arrays.sort(urlarray);
            for (int j = 0; j < urlarray.length; j++) {
//                Utils.download(urlarray[j],"E://comic//"+name);
            }
        }

    }
}
