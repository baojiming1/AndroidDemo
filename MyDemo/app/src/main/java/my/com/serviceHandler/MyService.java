package my.com.serviceHandler;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/24.
 */
public class MyService extends Service {

    private DownloadBinder downloadBinder = new DownloadBinder();

    public static final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand....");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {

        Notification no = new Notification(R.drawable.gs2, "前台服务通知", System.currentTimeMillis());
        Intent i = new Intent(this, ServiceActivity.class);
        PendingIntent p = PendingIntent.getActivity(this, 0, i, 0);
        no.setLatestEventInfo(this, "前台服务", "前台服务由通知来实现", p);
        startForeground(100, no);

        Log.e(TAG, "onCreate....");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy....");
        super.onDestroy();
    }

    class DownloadBinder extends Binder {


        public void startDownLoad(){
            Log.e(TAG, "startDownLoad..............");
        }

        public int getProgress(){
            Log.e(TAG, "getProgress...........");
            return 0;
        }
    }
}
