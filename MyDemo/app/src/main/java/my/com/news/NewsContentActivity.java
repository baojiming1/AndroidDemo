package my.com.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/18.
 */
public class NewsContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.news_content_main);
        NewsContentFragment newF = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_main); //获取碎片
        //获取传过来的条目参数
        Intent i = getIntent();
        String newTitle = i.getStringExtra("newTitle");
        String newContent = i.getStringExtra("newContent");

        //刷新碎片
        newF.refresh(newTitle, newContent);

    }

    //往这个活动跳转带参数
    public static void actionStart(Context context, String newTitle, String newContent){
        Intent i = new Intent(context, NewsContentActivity.class);
        i.putExtra("newTitle", newTitle);
        i.putExtra("newContent", newContent);
        context.startActivity(i);
    }

}
