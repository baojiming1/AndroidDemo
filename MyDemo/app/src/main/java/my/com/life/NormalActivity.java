package my.com.life;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import my.com.R;

/**
 * Created by Administrator on 2015/3/9.
 */
public class NormalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.life_nomal);
    }
}
