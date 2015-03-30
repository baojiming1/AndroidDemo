package my.com.other;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import my.com.R;

/**
 * 通知
 * Created by 鲍建明 on 2015/3/18.
 */
public class OtherActivity extends Activity {

    private Button notificationBtn;
    private Button cameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.other_main);
        notificationBtn = (Button) findViewById(R.id.other_notification_btn);
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification n = new Notification(R.drawable.gs2, "来自宁波的一条信息", System.currentTimeMillis());
               /* Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent,  通知被点击时会触发的事情
                        PendingIntent.FLAG_CANCEL_CURRENT);*/
                n.setLatestEventInfo(OtherActivity.this, "你在干嘛", "你在干嘛，吃了没有", null);
                manager.notify(1, n);
            }
        });

        cameraBtn = (Button) findViewById(R.id.other_camera);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OtherActivity.this, CameraActivity.class);
                startActivity(i);
            }
        });
    }






}
