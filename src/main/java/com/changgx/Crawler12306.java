package com.changgx;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 长歌行
 * changgx2014@163.com
 * 2016/12/16 10:22
 */
public class Crawler12306 {
    /**
     * 登陆url,post请求
     */
    private static String loginURL = "https://kyfw.12306.cn/otn/login/loginAysnSuggest";

    /**
     * 验证码url
     */
    private static String codeURL = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&0.22435648282706255";

    /**
     * 登陆的用户名的key
     */
    private static String usernameKey = "loginUserDTO.user_name";
    /**
     * 登陆的密码的key
     */
    private static String passwordKey = "userDTO.password";
    /**
     * 登陆的验证码的key
     */
    private static String codeKey = "randCode";

    public static void main(String[] args) throws Exception {
        HashMap<String, String> map = new HashMap();
        map.put(codeKey, "256,123");
        map.put(passwordKey, "fight2016");
        map.put(usernameKey, "2210840161@qq.com");
        while (true) {
            String data = sendSSLPostRequest(loginURL, map);
            System.out.println(data);
            String msg = JSONObject.fromObject(data).getString("messages");
            if (!"[\"系统繁忙，请稍后重试！\"]".equals(msg)) {
                System.out.println(data);
                break;
            }
        }

//        while (true){
//            String msg=func();
//            if(""){
//
//            }
//        }
    }

    //    public static String func() throws Exception{
//        CloseableHttpClient closeableHttpClient = HttpClients.custom()
//                .setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom()
//                                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
//                                .build()
//                        )
//                ).build();
//
//        HttpPost httppost = new HttpPost("https://kyfw.12306.cn/otn/login/loginAysnSuggest");
//
//        // 创建参数队列
//        List<org.apache.http.NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
//        formParams.add(new BasicNameValuePair(usernameKey, "2210840161@qq.com"));
//        formParams.add(new BasicNameValuePair(passwordKey, "fight2016."));
//        formParams.add(new BasicNameValuePair(codeKey, "256,123"));
//        CloseableHttpResponse response = closeableHttpClient.execute(httppost);
//        JSONObject json=JSONObject.fromObject(response);
//        return json.getString("messages");
//
//    }
//    public CloseableHttpClient getIgnoreSslCertificateHttpClient() {
//
//        SSLContext sslContext = null;
//        try {
//            SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
//            sslContextBuilder.loadTrustMaterial(null, new TrustStrategy() {
//
//
//                public boolean isTrusted(final X509Certificate[] arg0, final String arg1)
//                        throws CertificateException {
//
//                    return true;
//                }
//            });
//            sslContextBuilder.build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
//                new NoopHostnameVerifier());
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
//                .<ConnectionSocketFactory> create()
//                .register("http", PlainConnectionSocketFactory.getSocketFactory())
//                .register("https", sslSocketFactory).build();
//        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(
//                socketFactoryRegistry);
//        return HttpClientBuilder.create().setSslcontext(sslContext).setConnectionManager(connMgr)
//                .build();
//    }
    public static String sendSSLPostRequest(String reqURL, Map<String, String> params) {
        long responseLength = 0;                         //响应长度
        String responseContent = null;                   //响应内容
        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例

        X509TrustManager xtm = new X509TrustManager() {   //创建TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        try {
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[]{xtm}, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));

            HttpPost httpPost = new HttpPost(reqURL);                        //创建HttpPost
            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //构建POST请求的表单参数
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));

            HttpResponse response = httpClient.execute(httpPost); //执行POST请求
            HttpEntity entity = response.getEntity();             //获取响应实体

            if (null != entity) {
                responseLength = entity.getContentLength();
                responseContent = EntityUtils.toString(entity, "UTF-8");
                //EntityUtils.consume(entity); //Consume response content
            }
//            System.out.println("请求地址: " + httpPost.getURI());
//            System.out.println("响应状态: " + response.getStatusLine());
//            System.out.println("响应长度: " + responseLength);
//            System.out.println("响应内容: " + responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源
            return responseContent;
        }
    }
}
