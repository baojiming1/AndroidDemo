package my.com.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import my.com.R;

/**
 * Created by Administrator on 2015/3/9.
 */
public class MenuActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(this, "你点击了添加按钮", Toast.LENGTH_LONG).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "你点击了删除按钮", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {                      //这个地方方法传入的是物理建的menu
        getMenuInflater().inflate(R.menu.simple, menu);                      //在物理建菜单中创建一个自定义的菜单布局
        return true;
    }
}
