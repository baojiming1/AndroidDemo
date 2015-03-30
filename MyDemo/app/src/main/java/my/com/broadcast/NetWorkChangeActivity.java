package my.com.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import my.com.R;

/**
 *
 * 现在运行一下程序。首先你会在注册完成的时候收到
 一条广播，然后按下 Home 键回到主界面（注意不能按 Back 键，否则 onDestroy()方法会执
 行） ，接着按下 Menu 键→System settings→Data usage进入到数据使用详情界面，然后尝试着
 开关 Mobile Data 来启动和禁用网络，你就会看到有 Toast提醒你网络发生了变化。
 *
 * Created by 鲍建明 on 2015/3/18.
 */
public class NetWorkChangeActivity extends Activity {

    private IntentFilter filter;
    private NetWorkchangeBroadCast network;
    private LocalBroadcastManager manger;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.networkchange_main);

        //注册全部广播
        filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");           //监听Intent
         network = new NetWorkchangeBroadCast();
        registerReceiver(network, filter);                                          //将自定义的广播器注册到这个IntentFilter链中

        Button btn = (Button) findViewById(R.id.MyBroad_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.my.broadcast.MY_BROADCAST");
                sendBroadcast(i);
            }
        });

        //注册本地广播
        manger = LocalBroadcastManager.getInstance(this);
        Button b = (Button) findViewById(R.id.Local_broad_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.my.broadcast.LOCALBROADCAST");
                manger.sendBroadcast(i);
            }
        });

        IntentFilter filter2 = new IntentFilter();
        filter2.addAction("com.my.broadcast.LOCALBROADCAST");           //监听Intent
        localReceiver = new LocalReceiver();
        manger.registerReceiver(localReceiver, filter2);
    }

    @Override
    protected void onDestroy() {                    //最后在销毁的时候，取消掉该广播器
        super.onDestroy();
        unregisterReceiver(network);
        manger.unregisterReceiver(localReceiver);

    }

    //本地广播
    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "LocalReceiver is  ...", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 网络广播器
     */
    class NetWorkchangeBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager)             //获取系统网络服务，需要权限
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();              //获取当前使用的网络

            if( info != null &&  info.isAvailable() ){
                Toast.makeText(context, "NetworkInfo is Available ...", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "NetworkInfo is not Available ...", Toast.LENGTH_SHORT).show();
            }


        }
    }

}
