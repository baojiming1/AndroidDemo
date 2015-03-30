package my.com.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import my.com.R;

/**
 * 生命周期说明：
 *  1.当LifeCycleActivity被打开的时候：
 *      执行顺序：
 *          1.onCreate
 *          2.onStart
 *          3.onResume
 *  2. 点击打开NormalActivity的时候：
 *      执行顺序：
 *          1.onPause
 *          2.onStop
 *  3.(按物理键回退)关闭NormalActivity的时候：
 *      执行顺序：
 *          1.onRestart
 *          2.onStart
 *          3.onResume
 *  4.点击打开DialogActivity的时候
 *      执行顺序：
 *          1.onPause
 *
 *  5.(按物理键回退)关闭 DialogActivity的时候：
 *      执行顺序：
 *          1.onResume
 *
 *  6.(按物理键回退)关闭 LifeCycleActivity的时候：
 *      执行顺序：
 *          1.onResume
 *          2.onStart
 *
 *
 * Created by Administrator on 2015/3/9.
 */
public class LifeCycleActivity extends Activity {

    private String TAG = "LifeCycleActivity";
    private Button no;
    private Button dialog;



    /********************
     *
     *
     * 完整生存期
     活动在 onCreate()方法和 onDestroy()方法之间所经历的，就是完整生存期。一般情
     况下，一个活动会在 onCreate()方法中完成各种初始化操作，而在 onDestroy()方法中完
     成释放内存的操作。
     *
     *
     * ***********************/


    @Override       //创建时触发
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建时，先恢复之前该活动留下的临时数据
        if( savedInstanceState != null){
            String str = savedInstanceState.getString("onSaveInstanceState");
            if( str != null && str.trim().length() != 0){
                Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            }
        }


        Log.d(TAG, "onCreate()....");
        super.setContentView(R.layout.lifecycle);

        no = (Button) findViewById(R.id.life_normal);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(LifeCycleActivity.this, NormalActivity.class);
                startActivity(i);
            }
        });
        dialog = (Button) findViewById(R.id.life_dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LifeCycleActivity.this, DialogActivity.class);
                startActivity(i);
            }
        });
    }

    //销毁时触发
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()....");
    }


    /**********************
     *
     * 见生存期
     活动在 onStart()方法和 onStop()方法之间所经历的，就是可见生存期。在可见生存
     期内，活动对于用户总是可见的，即便有可能无法和用户进行交互。我们可以通过这两
     个方法，合理地管理那些对用户可见的资源。比如在 onStart()方法中对资源进行加载，
     而在 onStop()方法中对资源进行释放， 从而保证处于停止状态的活动不会占用过多内存。
     *
     *
     *
     *
     * *********************/

    //应用从不可见变为可见时，触发
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()......");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()....");
    }


    /*********************
     *
     * 前台生存期
     活动在 onResume()方法和 onPause()方法之间所经历的，就是前台生存期。在前台
     生存期内，活动总是处于运行状态的，此时的活动是可以和用户进行相互的，我们平时
     看到和接触最多的也这个状态下的活动。
     *
     * *********************/


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()........");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()........");
    }

    /***********************
     *  onRestart()
     这个方法在活动由停止状态变为运行状态之前调用，也就是活动被重新启动了。
     * **************************/

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()............");
    }


    /**************** 当这个活动被系统回收时被调用。为了保存活动中的临时数据，用户再次回到该活动时，能够重新获取该临时数据  *********************/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState().....");
        outState.putString("onSaveInstanceState", "活动被回收了，需要被保存的参数需要重新重置");
    }
}
