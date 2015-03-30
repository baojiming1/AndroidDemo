package my.com.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 鲍建明 on 2015/3/18.
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "注册了一个开机就广播的广播器", Toast.LENGTH_LONG).show();
    }
}
