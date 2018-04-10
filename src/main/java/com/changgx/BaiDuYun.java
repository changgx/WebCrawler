package com.changgx;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * 长歌行
 * changgx2014@163.com
 * 2017/9/13 17:26
 */
public class BaiDuYun {
    public static void main(String[] args) throws IOException {
        String url="https://pan.baidu.com/s/1boV8n2B";
        Connection.Response response = Jsoup.connect(url)
                .method(Connection.Method.POST).timeout(10000)
                .execute();

    }
}
