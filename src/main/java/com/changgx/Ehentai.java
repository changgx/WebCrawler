package com.changgx;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by changgx on 2017/1/8.
 */
public class Ehentai {

    public static void main(String[] args) {
        try{
            URL url = new URL("http://lofi.e-hentai.org/s/73f851131b/1013629-49");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Language", "en-US");
//            connection.setRequestProperty("X-EXAMPLE-LOGIN", "XXXXXXXX");
//            connection.setRequestProperty("X-EXAMPLE-PASSWORD", "XXXXXX");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(99999999);
            connection.setReadTimeout(99999999);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //DataInputStream input = new DataInputStream(connection.getInputStream());
            String  ret = "";
       /*   if(in!=null){
            for( int c = input.read(); c != -1; c = input.read() ) {
                ret = ret + String.valueOf((char)c);
                if(input==null || connection==null)
                    break;
            }
       }*/
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                ret = ret + inputLine;
            }
            if(in!=null)
                in.close();
            if(connection!=null)
                connection.disconnect();
            if(ret!=null && ret.length()>0){
//                return ret;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }



}
