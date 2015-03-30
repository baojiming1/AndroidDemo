package my.com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 鲍建明 on 2015/3/18.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String CREATE_BOOK = "create table book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    /**
     * 第一个参数是 Context，这个没什么好说的，必须要有
     它才能对数据库进行操作。第二个参数是数据库名，创建数据库时使用的就是这里指定的名
     称。第三个参数允许我们在查询数据的时候返回一个自定义的 Cursor，一般都是传入 null。
     第四个参数表示当前数据库的版本号
     * @param context
     * @param name
     * @param factory
     * @param version 系统根据版本号有相应操作：相同：不更新数据结构，不相同：更新数据库结构
     */
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(context, "create book success...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //可以根据每次传入的不通版本号做不通的事情，比如不重复创建数据库
    }




}
