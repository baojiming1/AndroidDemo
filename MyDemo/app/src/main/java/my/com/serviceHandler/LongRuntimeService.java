package my.com.serviceHandler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by 鲍建明 on 2015/3/24.
 */
public class LongRuntimeService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("LongRuntimeService", "Thread id is " + Thread.currentThread().getId());
            }
        }).start();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Long time = SystemClock.elapsedRealtime() + TimeUnit.MINUTES.toMillis(2);
        Intent i =  new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
