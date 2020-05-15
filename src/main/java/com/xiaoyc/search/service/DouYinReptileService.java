package com.xiaoyc.search.service;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoyc
 * @date 2020/5/15 0015 13:38
 */
@Service
public class DouYinReptileService {

    static final String DYAPI = "https://aweme.snssdk.com/web/api/v2/aweme/iteminfo/?item_ids=ITEM_IDS&dytk=DYTK";

    /**
     * tips:空串判断
     *
     * @Param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    /**
     * tips:是否有中文字符
     *
     * @param str
     * @return
     */

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * tips:处理文字
     *
     * @param var
     * @return
     */
    public static final String urlProcess(String var) {
        if (var.contains("douyin") || var.contains("iesdouyin")) {
            if (!isContainChinese(var)) return var;
            int start = var.indexOf("http");
            int end = var.lastIndexOf("/");
            var = var.substring(start, end);
            return var;
        } else return "";
    }

    public static String getURI(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpContext httpContext = new BasicHttpContext();
        HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        try {
            httpClient.execute(httpGet, httpContext);
            return clientContext.getTargetHost() + ((HttpUriRequest) clientContext.getRequest()).getURI().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String dyDecode(String var) {
        Document doc = null;
        try {
            doc = Jsoup.connect(var).cookie("cookie", "tt_webid=6711334817457341965; _ga=GA1.2.611157811.1562604418; _gid=GA1.2.1578330356.1562604418; _ba=BA0.2-20190709-51")
                    .header("user-agent", "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                    .timeout(12138).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 解析网页标签
        Elements elem = doc.getElementsByTag("script");
        String url1 = elem.toString();
        //正则
        String aweme_id = "itemId: \"([0-9]+)\"";
        String dytk = "dytk: \"(.*)\"";
        Pattern r = Pattern.compile(aweme_id);
        Matcher m = r.matcher(url1);
        while (m.find()) {
            aweme_id = m.group().replaceAll("itemId: ", "").replaceAll("\"", "");
        }
        Pattern r1 = Pattern.compile(dytk);
        Matcher m1 = r1.matcher(url1);
        while (m1.find()) {
            dytk = m1.group().replaceAll("dytk: ", "").replaceAll("\"", "");
        }
        try {
            String result2 = HttpRequest.get(DYAPI.replaceAll("ITEM_IDS", aweme_id).replaceAll("DYTK", dytk))
                    .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                    .timeout(12138)
                    .execute().body();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(result2).getAsJsonObject();
            return getURI(jsonObject.get("item_list").getAsJsonArray().get(0).getAsJsonObject().get("video").getAsJsonObject().get("play_addr").getAsJsonObject().get("url_list").getAsJsonArray().get(1).getAsString());
        } catch (Exception e) {
            return "";
        }
    }

    public String gettt(String url) {
        return dyDecode(urlProcess(url));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("请输入需要解析的视频:");
            Scanner sc = new Scanner(System.in);
            String next = sc.nextLine();
            System.out.println(next);
            if (isEmpty(next)) {
                System.out.println("输入有误请重新输入");
            } else {
                try {
                    String text = dyDecode(urlProcess(next));
                    System.out.println(isEmpty(text) ? "解析失败" : "解析地址为:" + text);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("报错信息为:" + e.getMessage());
                }
            }
        }
    }
}
