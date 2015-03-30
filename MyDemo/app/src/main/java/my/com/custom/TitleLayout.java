package my.com.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/10.
 */
public class TitleLayout extends LinearLayout {

    private Button back;

    private Button menu;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_titltlayout, this);
        final Context _this = context;
        back = (Button) findViewById(R.id.title_back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_this, "通用头部返回键被触发", Toast.LENGTH_LONG).show();
            }
        });

        menu = (Button) findViewById(R.id.title_menu);
        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_this, "通用头部菜单键被触发", Toast.LENGTH_LONG).show();
            }
        });
    }
}
