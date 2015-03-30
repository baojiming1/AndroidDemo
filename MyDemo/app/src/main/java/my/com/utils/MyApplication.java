package my.com.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by 鲍建明 on 2015/3/27.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context  = super.getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
