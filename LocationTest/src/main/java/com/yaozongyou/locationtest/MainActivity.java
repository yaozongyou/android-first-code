package com.yaozongyou.locationtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;
import android.content.Context;
import android.widget.Toast;
import java.util.List;
import android.os.Handler;
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import android.os.Message;

public class MainActivity extends Activity {
    private TextView positionTextView;
    private LocationManager locationManager;
    private String provider;
    public static final int SHOW_LOCATION = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        positionTextView = (TextView)findViewById(R.id.position_text_view);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
            return;
        }

        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            showLocation(location);               
        }
        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            
        }

        @Override
        public void onProviderEnabled(String provider) {
            
        }

        @Override
        public void onProviderDisabled(String provider) {
        
        }

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }
    };

    private void showLocation(final Location location) {
        /*
        String currentPosition = "latitude is " + location.getLatitude() + "\n" +
        "longitude is " + location.getLongitude();
        positionTextView.setText(currentPosition);
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder url = new StringBuilder();

                    url.append("http://maps.googleapis.com/maps/api/geocode/json?latlang=");
                    url.append(location.getLatitude()).append(",");
                    url.append(location.getLongitude());
                    url.append("&sensor=false");
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(url.toString());
                    httpGet.addHeader("Accept-Language", "zh-CN");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray resultArray = jsonObject.getJSONArray("results");
                        if (resultArray.length() > 0) {
                            JSONObject subObject = resultArray.getJSONObject(0);
                            String address = subObject.getString("formatted_address");
                            Message message = new Message();
                            message.what = SHOW_LOCATION;
                            message.obj = address;
                            handler.sendMessage(message);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case SHOW_LOCATION:
                String currentPosition = (String)msg.obj;
                positionTextView.setText(currentPosition);
                break;
            default:
                break;
            }
        }
    };
}
