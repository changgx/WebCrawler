package com.kunteng.crawler.p12306;

import com.kunteng.crawler.HttpClientUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 长歌行
 * changgx2014@163.com
 * 2016/12/21 16:46
 */
public class CookieUtils {
    /**
     * 请求方式 get
     */
    private static String initURL="https://kyfw.12306.cn/otn/login/init";



    public static void main(String[] args) {
        HttpClientUtils.httpGet(initURL);
    }
}
