package com.changgx;

/**
 * Created by changgx on 2017/1/7.
 */
public class Sexknights {
    public static void main(String[] args) {
        String html=HttpClientUtils.httpGet("http://lofi.e-hentai.org/s/6b635f492f/1013629-28?nl=1-412144");
        System.out.println(html);
    }
}
