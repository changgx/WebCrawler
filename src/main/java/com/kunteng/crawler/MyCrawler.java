package com.kunteng.crawler;/**
 * Created by Administrator on 2016/10/26.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.*;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * Administrator 2016/10/26
 */
public class MyCrawler {
    private final static DefaultHttpClient client = new DefaultHttpClient();
    //包含超链接的节点
    private static String[] nodeNames = {"link", "img", "script", "iframe", "a"};

    static {
        //设置cookie
        CookieStore cookieStore = new BasicCookieStore();
        //Bind custom cookie store to the local context
        client.setCookieStore(cookieStore);
        CookieSpecFactory csf = new CookieSpecFactory() {
            public CookieSpec newInstance(HttpParams params) {
                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin)
                            throws MalformedCookieException {
                    }
                };
            }
        };
        client.getCookieSpecs().register("oschina", csf);
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY, "oschina");
        client.getParams().setParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, true);
    }

    public static void closeHttpClient() {
        client.getConnectionManager().shutdown();
    }

    /**
     * @param startURL 入口url
     */
    public static Set<String> getValidURL(String startURL) throws UnsupportedEncodingException {
        String html = getHtmlByUrl(startURL);
        Set<String> set = new HashSet();
        Set<String> addSet = new HashSet<String>();
        if (html != null && !"".equals(html)) {
            Document doc = Jsoup.parse(html.replaceAll("\\<\\!\\-\\-", "").
                    replaceAll("\\-\\-\\>", "").replaceAll("\\-\\-\\\\\\>", ""));
            //通过F12查看网页，发现需要的模块都在该标签下
            Elements elements = doc.select("div#layout-content");
            doc = Jsoup.parse(elements.html());
            elements = doc.select("a");
            for (Element el :
                    elements) {
                //获取href的属性值
                String href = el.attr("href");
                if (checkURL(href)) {
                    set.add(el.attr("href"));
                }

            }
            System.out.println("首页 url:" + set.size());
            for (String url :
                    set) {
                String responseHtml = getHtmlByUrl(url);
                if (responseHtml != null && !"".equals(responseHtml)) {
                    Document tmpDoc = Jsoup.parse(responseHtml);
                    Elements tmpElements = new Elements();
                    for (int i = 0; i < nodeNames.length; i++) {
                        tmpElements.addAll(tmpDoc.select(nodeNames[i]));
                    }
                    for (Element ele : tmpElements) {
                        String tmpurl = ele.attr("href");
                        if (tmpurl == null || "".equals(tmpurl)) {
                            tmpurl = ele.attr("src");
                        }
                        if (checkURL(tmpurl)) {
                            addSet.add(tmpurl);

                        }
                    }
                }


            }
            System.out.println("二级 url:" + addSet.size());
            set.addAll(addSet);
            System.out.println("整合 url:" + addSet.size());
        }
        closeHttpClient();
        return set;
    }

    /**
     * 去掉脏数据
     * @param url
     * @return
     */
    public static boolean checkURL(String url) {
        if (url == null) {
            return false;
        }
        if (url.equals("")) {
            return false;
        }
        if (url.split(" ").length == 0) {
            return false;
        }
        if (url.split("#").length == 0) {
            return false;
        }
        if (url.equals("javacript:void(0);")) {
            return false;
        }
        return true;
    }

    public static String getHtmlByUrl(String url) throws UnsupportedEncodingException {
        String html = null;
        //以get方式请求该URL
        HttpGet httpget = new HttpGet(url.replaceAll(" ", "%20"));
        try {
            //得到responce对象
            HttpResponse responce = client.execute(httpget);
            //返回码
            int resStatu = responce.getStatusLine().getStatusCode();
            //200正常 302重定向 也行
            if (resStatu == HttpStatus.SC_OK || resStatu == HttpStatus.SC_MOVED_TEMPORARILY) {
                //获得相应实体
                HttpEntity entity = responce.getEntity();
                if (entity != null) {
                    //获得html源代码
                    html = EntityUtils.toString(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return html;
        }

    }

    public static Set<String> filterURL(Set<String> set) {
        Set<String> resultSet = new HashSet<String>();
        for (String url :
                set) {
            String s = url;
            if (url.startsWith("http")) {
                if(url.split("\\/\\/").length>1){
                    url = url.split("\\/\\/")[1];
                }
                if (url.indexOf("/") > 0) {
                    url = url.substring(0, url.indexOf("/"));
                }
                if (url.indexOf("?") > 0) {
                    url = url.substring(0, url.indexOf("?"));
                }
                if (url.indexOf("#") > 0) {
                    url = url.substring(0, url.indexOf("#"));
                }
                if (url.indexOf(":") > 0) {
                    url = url.substring(0, url.indexOf(":"));
                }
                if (url.split("\\.").length > 2) {
                    url = url.substring(url.indexOf(".") + 1, url.length());
                }
                if ((url.endsWith("com.cn")
                        || url.endsWith("net.cn")
                        || url.endsWith("gov.cn")
                        || url.endsWith("org.cn")
                )) {
                    if (url.split("\\.").length > 3) {
                        String[] tmp = url.split("\\.");
                        url = tmp[tmp.length - 3] + "." + tmp[tmp.length - 2] + "." + tmp[tmp.length - 1];
                    }
                } else if ((url.endsWith("top")
                        || url.endsWith("com")
                        || url.endsWith("net")
                        || url.endsWith("org")
                        || url.endsWith("edu")
                        || url.endsWith("gov")
                        || url.endsWith("int")
                        || url.endsWith("mil")
                        || url.endsWith("tel")
                        || url.endsWith("biz")
                        || url.endsWith("cc")
                        || url.endsWith("tv")
                        || url.endsWith("info")
                        || url.endsWith("name")
                        || url.endsWith("hk")
                        || url.endsWith("cc")
                        || url.endsWith("fm")

                )) {
                    if (url.split("\\.").length > 2) {
                        String[] tmp = url.split("\\.");
                        url = tmp[tmp.length - 2] + "." + tmp[tmp.length - 1];
                    }
                }
                resultSet.add(url);
            }

        }
        System.out.println("过滤后 ：" + resultSet.size());
        return resultSet;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getHtmlByUrl("http://lofi.e-hentai.org/"));
//        Set<String> set = getValidURL("https://www.hao123.com");
//        Set<String> resultSet = filterURL(set);
//        for (String url :
//                resultSet) {
//            Util.stringToFile(url, "F://url.txt");
//        }
    }


}
