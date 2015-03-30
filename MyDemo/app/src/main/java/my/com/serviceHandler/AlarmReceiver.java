package my.com.serviceHandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 鲍建明 on 2015/3/24.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("AlarmReceiver", "AlarmReceiver.onReceive.........");
        Intent i = new Intent(context, LongRuntimeService.class);
        context.startActivity(i);
    }
}
