package my.com.serviceHandler;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/24.
 */
public class ServiceActivity extends Activity implements View.OnClickListener{

    private static String TAG = "ServiceActivity";

    private MyService.DownloadBinder  downloadBinder;

    //这两个方法分别会在活动与服务成功绑定以及解除绑定的时候调用
    private ServiceConnection serviceConnection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownLoad();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    private Button startBtn;
    private Button stopBtn;
    private Button bindBtn;
    private Button unbindBtn;
    private Button aysncBtn;
    private Button longruntimeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.my_service);
        startBtn = (Button) findViewById(R.id.service_start_btn);
        startBtn.setOnClickListener(this);
        stopBtn = (Button) findViewById(R.id.service_stop_btn);
        stopBtn.setOnClickListener(this);
        bindBtn = (Button) findViewById(R.id.service_bind_btn);
        bindBtn.setOnClickListener(this);
        unbindBtn = (Button) findViewById(R.id.service_unbind_btn);
        unbindBtn.setOnClickListener(this);

        aysncBtn = (Button) findViewById(R.id.service_aysnc_btn);
        aysncBtn.setOnClickListener(this);

        longruntimeBtn = (Button) findViewById(R.id.long_runtime_service_btn);
        longruntimeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_start_btn:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.service_stop_btn:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.service_bind_btn:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);      //BIND_AUTO_CREATE 表示在活动和服务进行绑定后自动创建服务
                break;
            case R.id.service_unbind_btn:
                Intent unbindIntent = new Intent(this, MyService.class);
                unbindService(serviceConnection);//如果没有注册过，会直接报错
                break;
            case R.id.service_aysnc_btn:
                Log.e("MyIntentService", "主线程ID" + Thread.currentThread().getId());
                Intent aysncIntent = new Intent(this, MyIntentService.class);
                startService(aysncIntent);
                break;
            case R.id.long_runtime_service_btn:
                Intent longRuntimeIntent = new Intent(this, LongRuntimeService.class);
                startService(longRuntimeIntent);
                break;
            default:
                break;
        }
    }



}
