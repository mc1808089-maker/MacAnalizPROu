package com.macanaliz.pro;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.JavascriptInterface;
import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {
    private ExecutorService pool = Executors.newFixedThreadPool(3);
    private Handler main = new Handler(Looper.getMainLooper());

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        WebView w = new WebView(this);
        WebSettings s = w.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        w.addJavascriptInterface(new ApiBridge(w), "AndroidApi");
        w.loadUrl("file:///android_asset/index.html");
        setContentView(w);
    }

    public class ApiBridge {
        private WebView web;
        ApiBridge(WebView w){web=w;}

        @JavascriptInterface
        public void get(String url, String key, String callbackId) {
            pool.execute(() -> {
                String result;
                try {
                    HttpURLConnection c=(HttpURLConnection)new URL(url).openConnection();
                    c.setRequestMethod("GET");
                    c.setRequestProperty("x-apisports-key", key);
                    c.setRequestProperty("Accept","application/json");
                    c.setConnectTimeout(15000);
                    c.setReadTimeout(20000);
                    int code=c.getResponseCode();
                    BufferedReader br=new BufferedReader(new InputStreamReader(
                        code>=200 && code<400 ? c.getInputStream() : c.getErrorStream(),"UTF-8"));
                    StringBuilder sb=new StringBuilder(); String line;
                    while((line=br.readLine())!=null) sb.append(line);
                    br.close(); c.disconnect();
                    result="{\"ok\":"+(code>=200&&code<400)+",\"status\":"+code+",\"body\":"+jsonQuote(sb.toString())+"}";
                } catch(Exception e) {
                    result="{\"ok\":false,\"status\":0,\"body\":"+jsonQuote(e.toString())+"}";
                }
                String js="window.__nativeApiDone("+jsonQuote(callbackId)+","+result+")";
                main.post(() -> web.evaluateJavascript(js,null));
            });
        }
        private String jsonQuote(String x){
            if(x==null)return "\"\"";
            return "\""+x.replace("\\","\\\\").replace("\"","\\\"").replace("\r","\\r").replace("\n","\\n").replace("\t","\\t")+"\"";
        }
    }
}