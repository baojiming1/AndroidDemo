package my.com.fist;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URI;

import my.com.R;


/**
 * Created by Administrator on 2015/3/9.
 */
public class IntentActivity extends Activity {

    private Button b;
    private Button ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.intent);
        b = (Button) findViewById(R.id.Intent);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.baidu.com"));
                i.putExtra("data", "数据传递");
                startActivity(i);
            }
        });

        ar = (Button) findViewById(R.id.ActivityResult1);
        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntentActivity.this, Intent2Activity.class);
                startActivityForResult(i, 1);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = "requestCode:" + requestCode + ", resultCode:" + resultCode + ", Intent:" + (data == null ? "null" : data.getStringExtra("data") ) ;
        Toast.makeText(IntentActivity.this, result, Toast.LENGTH_LONG).show();
    }
}
