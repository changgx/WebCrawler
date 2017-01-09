package com.changgx;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientUtils {
    public static void main(String[] args) throws Exception{
        httpGet("http://lofi.e-hentai.org/s/e4776e0f42/1013629-2");
    }

    /**
     * get方法
     */
    public static String httpGet(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response=null;
        HttpHost proxy = new HttpHost("127.0.0.1",2901, null);
        httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
        HttpEntity entity=null;
        String html="";
        try {
            // 创建httpget.    
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.    
            response = httpclient.execute(httpget);
            // 获取响应实体
            entity = response.getEntity();
             html=EntityUtils.toString(entity);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return html;
        }
    }

    /**
     * post方法(表单提交)
     */
    public void httpPostForm() {
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost    
        HttpPost httppost = new HttpPost("http://…………");

        // 创建参数队列
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("username", "admin"));
        formParams.add(new BasicNameValuePair("password", "123456"));
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源    
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void getRedirectInfo(){
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet("http://www.u17.com/chapter/102843.html");
        try {
            //将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
            HttpResponse response = httpClient.execute(httpGet, httpContext);
            //获取重定向之后的主机地址信息,即"http://127.0.0.1:8088"
            HttpHost targetHost = (HttpHost)httpContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            //获取实际的请求对象的URI,即重定向之后的"/blog/admin/login.jsp"
            HttpUriRequest realRequest = (HttpUriRequest)httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
            System.out.println("主机地址:" + targetHost);
            System.out.println("URI信息:" + realRequest.getURI());
            HttpEntity entity = response.getEntity();
            if(null != entity){
                System.out.println("响应内容:" + EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset()));
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
    }
}