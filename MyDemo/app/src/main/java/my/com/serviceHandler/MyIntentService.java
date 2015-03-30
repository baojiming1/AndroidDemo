package my.com.serviceHandler;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 鲍建明 on 2015/3/24.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {      //这个类会在完成任务自动关闭线程，不需要我们手动去关闭
        Log.e("MyIntentService", "this Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyIntentService", "onDestroy................");
    }
}
