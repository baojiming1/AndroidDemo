package my.com.lbs;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/26.
 */
public class LBSActivity extends Activity {

    private TextView tv;

    private LocationManager locationManager;

    private String provider;

    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lbs);
        tv = (TextView) findViewById(R.id.lbs_text);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        List<String> providers =  locationManager.getProviders(true);

        if( providers.contains(LocationManager.GPS_PROVIDER)){      //如果GPS 开启的情况下取GPS
            provider = LocationManager.GPS_PROVIDER;
        }else if( providers.contains(LocationManager.NETWORK_PROVIDER)){    //如果GPS关着，网络开着取网络定位
            provider = LocationManager.NETWORK_PROVIDER;
        }else{
            Toast.makeText(this, "请开启您的GPS或者开启网络", Toast.LENGTH_LONG).show();
            return ;
        }
        Location location = locationManager.getLastKnownLocation(provider);         //获取最后一次的GPS信息
        if( location != null){
            showLocation(location);
        }
        myLocationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(provider, 5000, 1,  myLocationListener); //最小5秒，最小1米 更新GPS信息

    }

    private void showLocation(Location location){
        String str = "latitude :" + location.getLatitude() + ", longitude : " + location.getLongitude();
        tv.setText(str);

    }

    @Override
    protected void onDestroy() {
        if(myLocationListener != null){
            locationManager.removeUpdates(myLocationListener);          //销毁时，移除该监听器
        }
        super.onDestroy();

    }

    class MyLocationListener implements LocationListener{


        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
