package my.com.other;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/20.
 */
public class CameraActivity  extends Activity {


    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    private Button btn;
    private ImageView iv;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.camera_main);
        iv = (ImageView) findViewById(R.id.camera_img);
        btn = (Button) findViewById(R.id.camera_open_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file =  new File(Environment.getDataDirectory(), "temp.jpg");
                if(file.exists()){
                    file.delete();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(file);
                Intent i = new Intent("android.media.action.IMAGE_CAPTURE");           //隐式启动相机
                i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(i, TAKE_PHOTO );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode){
                case TAKE_PHOTO :
                    if(resultCode == RESULT_OK){
                        Intent intent = new Intent("com.android.camera.action.CROP");
                        intent.setDataAndType(imageUri, "image/*");
                        intent.putExtra("scale", true);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                    }


                    break;

                case CROP_PHOTO:
                    if (resultCode == RESULT_OK) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream
                                    (getContentResolver()
                                            .openInputStream(imageUri));
                            iv.setImageBitmap(bitmap); // 将裁剪后的照片显示出来
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                default:
                    break;
            }
    }
}
