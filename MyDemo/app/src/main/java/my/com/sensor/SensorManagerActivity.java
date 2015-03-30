package my.com.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/26.
 */
public class SensorManagerActivity extends Activity implements View.OnClickListener {

    private Button higlitBtn;
    private TextView higlitTv;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener ;

    private Button accelerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.sensor);
        higlitTv = (TextView) findViewById(R.id.sensor_higilt_text);
        higlitBtn = (Button) findViewById(R.id.sensor_higilt_btn);
        higlitBtn.setOnClickListener(this);

        accelerBtn = (Button) findViewById(R.id.sensor_acceler_btn);
        accelerBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        if( sensorManager != null){
            sensorManager.unregisterListener(sensorEventListener);
        }
        super.onDestroy();
    }

    //绑定光线传感器

    /**
     *  共有SENSOR_DELAY_UI、 SENSOR_DELAY_NORMAL、
     SENSOR_DELAY_GAME 和 SENSOR_DELAY_FASTEST这四种值可选， 它们的更新速率是
     依次递增的
     */
    private void bindHiglit(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorEventListener = new MyHiglitSensorEventListener();
        sensorManager.registerListener(sensorEventListener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * 绑定加速度传感器
     */
    private void bindAcceler(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new AccelerSensorEventListener();
        sensorManager.registerListener(sensorEventListener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sensor_higilt_btn :
                bindHiglit();
                break;
            case R.id.sensor_acceler_btn :
                bindAcceler();
                break;
            default:
                break;
        }
    }

    class MyHiglitSensorEventListener implements  SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] vals = event.values;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < vals.length; i++) {
                sb.append("第" + (i+1) + "个参数值为:" + vals[i]);
                Log.e("SensorManagerActivity", "value : " + vals[i]);
            }
            // values数组中第一个下标的值就是当前的光照强度
            higlitTv.setText(sb.toString());
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    class AccelerSensorEventListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 加速度可能会是负值，所以要取它们的绝对值
            float xValue = Math.abs(event.values[0]);
            float yValue = Math.abs(event.values[1]);
            float zValue = Math.abs(event.values[2]);
            if (xValue > 15 || yValue > 15 || zValue > 15) {
            // 认为用户摇动了手机，触发摇一摇逻辑
                Toast.makeText(SensorManagerActivity.this, "摇一摇",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
