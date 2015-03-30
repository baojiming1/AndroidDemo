package my.com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import my.com.R;
import my.com.broadcast.NetWorkChangeActivity;
import my.com.chat.ChatActivity;
import my.com.contentProvider.MyContentProviderActivity;
import my.com.database.DataBaseActivity;
import my.com.fist.IntentActivity;
import my.com.fragment.MainFragmentActivity;
import my.com.lbs.LBSActivity;
import my.com.life.LifeCycleActivity;
import my.com.listView.ArrayListViewActivity;
import my.com.mydemo.MenuActivity;
import my.com.news.NewsMainActivity;
import my.com.other.OtherActivity;
import my.com.sensor.SensorManagerActivity;
import my.com.serviceHandler.ServiceHandlerActivity;
import my.com.webview.HttpClientActivity;
import my.com.webview.WebViewActivity;

/**
 * Created by Administrator on 2015/3/9.
 */
public class MainActivity extends Activity{

    private ListView lv;
  /*  private Button intentV;
    private Button menuV;
    private Button lifiV;

    private Button listV;

    private Button chatV;
    private Button fragmentV;

    private Button fragmentNews;
    private Button broadCastBtn;
    private Button databaseBtn;

    private Button cpBtn;
    private Button otherBtn;

    private Button serverBtn;

    private Button WebViewBtn;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main);

        List<Object> list = new ArrayList<Object>();
        list.add("123");
        lv = (ListView) findViewById(R.id.main_list);
        lv.setAdapter(new MainListViewAdapter(this, R.layout.main_list, list));


      /*  //Intent 实例
        intentV = (Button) findViewById(R.id.IntentDemo);
        menuV = (Button) findViewById(R.id.MenuDemo);

        //Menu实例
        intentV.setOnClickListener(new MyClickListener(this, IntentActivity.class));
        menuV.setOnClickListener(new MyClickListener(this, MenuActivity.class));

        //活动生命周期实例
        lifiV = (Button) findViewById(R.id.LifiCycle);
        lifiV.setOnClickListener(new MyClickListener(this, LifeCycleActivity.class));

        //ListView实例
        listV = (Button) findViewById(R.id.listview_demo);
        listV.setOnClickListener(new MyClickListener(this, ArrayListViewActivity.class));

        chatV = (Button) findViewById(R.id.chatView);
        chatV.setOnClickListener(new MyClickListener(this, ChatActivity.class));

        fragmentV = (Button) findViewById(R.id.fragmentView);
        fragmentV.setOnClickListener(new MyClickListener(this, MainFragmentActivity.class));

        fragmentNews = (Button) findViewById(R.id.fragment_news);
        fragmentNews.setOnClickListener(new MyClickListener(this, NewsMainActivity.class));

        broadCastBtn = (Button) findViewById(R.id.broadCast_main);
        broadCastBtn.setOnClickListener(new MyClickListener(this, NetWorkChangeActivity.class) );

        databaseBtn = (Button) findViewById(R.id.database_demo);
        databaseBtn.setOnClickListener(new MyClickListener(this, DataBaseActivity.class));

        cpBtn = (Button) findViewById(R.id.content_provider_demo);
        cpBtn.setOnClickListener(new MyClickListener(this, MyContentProviderActivity.class));

        otherBtn = (Button) findViewById(R.id.other_demo);
        otherBtn.setOnClickListener(new MyClickListener(this, OtherActivity.class) );

        serverBtn = (Button) findViewById(R.id.server_handler_demo);
        serverBtn.setOnClickListener(new MyClickListener(this, ServiceHandlerActivity.class));

        WebViewBtn = (Button) findViewById(R.id.web_view_demo);
        WebViewBtn.setOnClickListener(new MyClickListener(this, WebViewActivity.class));*/
    }

    public void jump(View v){
        switch (v.getId()){
            case R.id.IntentDemo :
                    j(IntentActivity.class);
                break;
            case R.id.MenuDemo :
                j(MenuActivity.class);
                break;
            case R.id.LifiCycle :
                j(LifeCycleActivity.class);
                break;
            case R.id.listview_demo :
                j(ArrayListViewActivity.class);
                break;
            case R.id.chatView :
                j(ChatActivity.class);
                break;
            case R.id.fragmentView :
                j(MainFragmentActivity.class);
                break;
            case R.id.broadCast_main :
                j(NetWorkChangeActivity.class);
                break;
            case R.id.database_demo :
                j(DataBaseActivity.class);
                break;
            case R.id.content_provider_demo :
                j(MyContentProviderActivity.class);
                break;
            case R.id.other_demo :
                j(OtherActivity.class);
                break;
            case R.id.server_handler_demo :
                j(ServiceHandlerActivity.class);
                break;
            case R.id.web_view_demo :
                j(WebViewActivity.class);
                break;
            case R.id.HttpURLConnection_demo :
                j(HttpClientActivity.class);
                break;
            case R.id.lbs_demo :
                j(LBSActivity.class);
                break;
            case R.id.sensor_demo :
                j(SensorManagerActivity.class);
                break;
            default:
                break;
        }

    }


    public void j(Class<?> clazz){
        Intent i = new Intent(this, clazz);
        startActivity(i);
    }

    /**
     * 用于界面的跳转
     */
    class MyClickListener implements View.OnClickListener{
        private Context _this;
        private Class<?> clazz;

        public MyClickListener(Context c, Class<?> clazz){
            this._this = c;
            this.clazz = clazz;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(_this, clazz);
            startActivity(i);
        }
    }


    class MainListViewAdapter extends ArrayAdapter<Object>{

        private int resource;


        public MainListViewAdapter(Context context, int resource, List<Object> list) {
            super(context, resource, list);
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if( convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(this.resource, null);
            }

            return convertView;
        }
    }

}
