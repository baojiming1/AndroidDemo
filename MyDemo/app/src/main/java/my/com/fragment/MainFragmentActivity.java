package my.com.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/17.
 */
public class MainFragmentActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.fragment_main);
        Button b = (Button) findViewById(R.id.left_fragment_btn);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_fragment_btn :
                Autoragment autoragment = new Autoragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction tx = manager.beginTransaction();
                tx.replace(R.id.fragment_layout_right, autoragment);
                tx.addToBackStack(null);
                tx.commit();

                break;
            default:
                break;
        }
    }
}
