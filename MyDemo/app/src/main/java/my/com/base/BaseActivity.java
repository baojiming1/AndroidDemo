package my.com.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/9.
 */
public class BaseActivity extends Activity {

    private static String TAG = "BaseActivity";

    protected List<Activity> activityList = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, this.getClass().getSimpleName());
    }


    //添加一个活动
    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    //移除一个活动
    public void remove(Activity activity){
        activityList.remove(activity);
    }

    //直接退出应用程序
    public void finishingAll(){
        for (Activity activity : activityList){
            if( activity.isFinishing() ){
                activity.finish();
            }
        }
    }


}
