package my.com.serviceHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/20.
 */
public class ServiceHandlerActivity extends Activity implements View.OnClickListener {

    private Handler handler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.obj != null){
                btn.setText(msg.obj.toString());
            }
            return true;
        }
    });

    private Button btn;

    private Button taskBtn;

    private Button serviceDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.service_handler);
        btn = (Button) findViewById(R.id.service_handler_btn);
        btn.setOnClickListener(this);
        taskBtn = (Button) findViewById(R.id.service_task_down_btn);
        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadTask task = new DownloadTask(ServiceHandlerActivity.this);
                task.execute();
            }
        });

        serviceDemo = (Button) findViewById(R.id.my_service_demo);
        serviceDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceHandlerActivity.this, ServiceActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_handler_btn:
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.obj = "另一条线程改变了我";
                        handler.sendMessage(msg);
                    }
                });
                t.start();

                break;
            default:
                break;
        }
    }
}
