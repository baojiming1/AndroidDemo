package my.com.serviceHandler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * Created by 鲍建明 on 2015/3/24.
 */
public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

    private ProgressDialog progressDialog;

    private Context context;

    public DownloadTask(Context context){
        this.progressDialog = new ProgressDialog(context);
        this.context = context;
    }

    /**
     * 这个方法中的所有代码都会在子线程中运行， 我们应该在这里去处理所有的耗时任
     务。任务一旦完成就可以通过 return 语句来将任务的执行结果返回，如果 AsyncTask的
     第三个泛型参数指定的是 Void，就可以不返回任务执行结果。注意，在这个方法中是不
     可以进行 UI操作的，如果需要更新 UI元素，比如说反馈当前任务的执行进度，可以调
     用 publishProgress(Progress...)方法来完成。
     * @param params
     * @return
     */
    @Override
    protected Boolean doInBackground(Void... params) {

        //处理下载逻辑代码
        for (int i = 0; i < 100 ; i++){
            try {
                publishProgress(i);
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    /**
     * 这个方法会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作，比
     如显示一个进度条对话框等。
     */
    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    /**
     * 当在后台任务中调用了 publishProgress(Progress...)方法后，这个方法就会很快被调
     用，方法中携带的参数就是在后台任务中传递过来的。在这个方法中可以对 UI 进行操
     作，利用参数中的数值就可以对界面元素进行相应地更新。
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setMessage("DownLoad..."  +  values[0]);
    }

    /**
     * 当后台任务执行完毕并通过 return语句进行返回时，这个方法就很快会被调用。返
     回的数据会作为参数传递到此方法中，可以利用返回的数据来进行一些 UI 操作，比如
     说提醒任务执行的结果，以及关闭掉进度条对话框等。
     * @param aBoolean
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        progressDialog.dismiss();
        if(aBoolean){
            Toast.makeText(context, "Down Success", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Down Faild", Toast.LENGTH_LONG).show();
        }
    }
}
