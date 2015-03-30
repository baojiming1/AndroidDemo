package my.com.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/25.
 */
public class WebViewActivity extends Activity {


    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.web_view);
        wv = (WebView) findViewById(R.id.web_view);
        wv.getSettings().setJavaScriptEnabled(true);                //让 WebView支持 JavaScript脚本
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) { //这就
                //表明当需要从一个网页跳转到另一个网页时，我们希望目标网页仍然在当前 WebView 中显
                //示，而不是打开系统浏览器。
                Log.e("WebViewActivity", "url:" + url);
                view.loadUrl(url);     // 根据传入的参数再去加载新的网页
                return true;         // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
            }
        });
        wv.loadUrl("http://www.baidu.com");
    }
}
