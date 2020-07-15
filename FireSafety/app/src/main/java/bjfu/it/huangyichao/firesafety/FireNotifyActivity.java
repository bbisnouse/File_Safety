package bjfu.it.huangyichao.firesafety;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FireNotifyActivity extends AppCompatActivity {

    private EditText edit;
    private EditText con;
    private Button bt;
    private EditText lati;
    private EditText longi;
/*    //位置
    private LocationManager locManager;
    private LocationListener locListener;
    //获取纬度
    Double latitude;
    //获取经度
    Double longitude;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locManager != null && locListener != null) {
            locManager.removeUpdates(locListener);
        }
        locManager = null;
        locListener = null;
    }*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_notify);

/*        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //位置提供器，也就是实际上来定位的对象，这里选择的是GPS定位
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        //开始定位,获取包含上次记录的位置数据的位置对象
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                ==PackageManager.PERMISSION_GRANTED) {
            Location location = locManager.getLastKnownLocation(locationProvider);
            //获取纬度
            latitude = location.getLatitude();
            //获取经度
            longitude = location.getLongitude();
        }
        Log.e("Latitude", String.valueOf(latitude));
        Log.e("Longitude", String.valueOf(longitude));*/
/*        String provider = locManager.getBestProvider(new Criteria(), true);
        locListener = new LocationListener() {
            private Location lastLocation;
            @Override
            public void onLocationChanged(Location location) {
                if(lastLocation == null){
                    lastLocation = location;
                }
                latitude = location.getLatitude();
                longitude = location.getLongitude();
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
        };
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                ==PackageManager.PERMISSION_GRANTED) {
            locManager.requestLocationUpdates(provider, 1000, 1, locListener);
        }
        Log.e("Latitude", String.valueOf(latitude));
        Log.e("Longitude", String.valueOf(longitude));*/
/*        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
        ==PackageManager.PERMISSION_GRANTED) {
            Log.e("eee","我是黄奕超");
            //开始定位,获取包含上次记录的位置数据的位置对象
            Location location = locManager.getLastKnownLocation(provider);
            //获取纬度
            Double latitude = location.getLatitude();
            //获取经度
            Double longitude = location.getLongitude();
            Log.e("Latitude", String.valueOf(latitude));
            Log.e("Longitude", String.valueOf(longitude));
        }*/
        bt=(Button) findViewById(R.id.sendbutton);
        bt.setOnClickListener(new Click());
        /*TextView textView = findViewById(R.id.mobile);
        textView.setText("19990524");*/

    }

    class Click implements OnClickListener
    {
        public void onClick(View v) {
            edit=(EditText) findViewById(R.id.mobile);
            con=(EditText) findViewById(R.id.content);
            lati=(EditText) findViewById(R.id.jingdu);
            longi=(EditText) findViewById(R.id.weidu);
            // TODO Auto-generated method stub
            String number = edit.getText().toString();
            //String content = con.getText().toString();
            String content = "The latitude is: "+lati.getText().toString()+",\n"+
                    "the longitude is: "+longi.getText().toString()+",\n"+
                    "the append information is: "+con.getText().toString()+".";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(
                    number,// 收件人
                    null, // 短信中心号码
                    content, // 内容
                    null,
                    null);
            Toast.makeText(FireNotifyActivity.this, "信息已发送", Toast.LENGTH_SHORT).show();
        }
    }

}
