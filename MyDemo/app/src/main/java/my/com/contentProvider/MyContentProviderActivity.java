package my.com.contentProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/18.
 */
public class MyContentProviderActivity extends Activity {

    private Button btn;
    private ContentResolver resolver;

    private TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.content_provider);
        btn = (Button) findViewById(R.id.content_provider_btn);
        tv = (TextView) findViewById(R.id.content_provider_text);
        resolver = getContentResolver();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           //获取通讯录
                Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                while (cursor.moveToNext()) {
// 获取联系人姓名
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
// 获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    tv.setText(displayName + number);
                    break;
                }
                cursor.close();                 //必须关闭流，这里简单了。
            }
        });
    }
}
