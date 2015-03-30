package my.com.fist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import my.com.R;

/**
 * Created by Administrator on 2015/3/9.
 */
public class Intent2Activity extends Activity {

    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.intent2);
        b = (Button) findViewById(R.id.ActivityResult2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("data", "数据返回来了");
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}
