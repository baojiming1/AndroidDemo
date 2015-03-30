package my.com.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/18.
 */
public class DataBaseActivity extends Activity {

    private Button btn;
    private MyDatabaseHelper myDatabaseHelper;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.database_main);
        btn = (Button) findViewById(R.id.database_book_create);
        myDatabaseHelper = new MyDatabaseHelper(this, "Book.db", null , 1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
        addBtn = (Button) findViewById(R.id.database_book_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("author", "是一本书哦");
                values.put("name" , "一本书");
                database.insert("book", null, values );

                //后面值指的是条件和条件值
                //database.update("book", values, "name = ?", new String[] { "The DaVinci Code" });
                database.delete("book",  "name = ?", new String[] { "The DaVinci Code" });
                //database.query()
            }
        });
    }
}
