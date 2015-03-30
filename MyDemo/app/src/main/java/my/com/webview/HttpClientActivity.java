package my.com.webview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/26.
 */
public class HttpClientActivity extends Activity {

    private Handler handler = new Handler(new Handler.Callback(){

        @Override
        public boolean handleMessage(Message msg) {
            if(msg != null){
                StringBuffer sb = (StringBuffer) msg.obj;
                tv.setText(sb.toString());
                return true;
            }
            return false;
        }
    });

    private TextView tv;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.http_view);

        sendBtn = (Button) findViewById(R.id.http_send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {               //主线上不能直接变更UI
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendHttpClient();
                    }
                }).start();

            }
        });

        tv = (TextView) findViewById(R.id.http_baidu_text);

    }

    /**
     * 使用httpClient发送请求
     */
    private void sendHttpClient(){
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://www.baidu.com");
        try {
            HttpResponse response = client.execute(get);
            if( response.getStatusLine().getStatusCode() == 200 ){
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity, "UTF-8");
                StringBuffer sb = new StringBuffer(str);
                Message msg = new Message();
                msg.obj = sb;
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 通过HttpURLConnection
     * 发送http请求
     */
    private void sendHttp(){
        HttpURLConnection http = null;
        try {
            URL url = new URL("http://www.baidu.com");
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setConnectTimeout(3000);
            http.setReadTimeout(3000);
            InputStream in = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuffer sb = new StringBuffer();
            String line = null;
            while( (line = reader.readLine()) != null){
                Log.e("HttpClientActivity", line);
                sb.append(line);
            }

            Message msg = new Message();
            msg.obj = sb;
            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if( http != null){
                http.disconnect();
            }

        }

    }
}
