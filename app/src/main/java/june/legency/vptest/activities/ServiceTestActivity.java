package june.legency.vptest.activities;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;

import com.apkfuns.logutils.LogUtils;

import java.util.List;

import io.paperdb.Paper;
import june.legency.vptest.R;

public class ServiceTestActivity extends AppCompatActivity {

    private ConnectivityManager connectivityManager;
    private WifiManager wifiManager;
    private TelephonyManager telephonyManager;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        initService();
        Paper.init(this);
        List<ScanResult> s = wifiManager.getScanResults();
        LogUtils.d(s);
        Paper.book("new").write("hello", s);
        s = Paper.book("new").read("hello");
        LogUtils.d(s);
    }

    //        // connectivity interceptor
//        interceptorHooks.add(new Interceptor_GetActiveNetworkInfo());

    //        //location interceptor
//        interceptorHooks.add(new Interceptor_RemoveUpdates());
//        interceptorHooks.add(new Interceptor_RequestLocationUpdates());
//
//        // telephony interceptor
//        interceptorHooks.add(new Interceptor_GetActivePhoneTypeForSubscriber());
//        interceptorHooks.add(new Interceptor_GetAllCellInfo());
//        interceptorHooks.add(new Interceptor_GetAllCellInfoUsingSubId());
//        interceptorHooks.add(new Interceptor_GetCellLocation());
//        interceptorHooks.add(new Interceptor_GetNeighboringCellInfo());
//
//        // wifi interceptor
//        interceptorHooks.add(new Interceptor_GetConnectionInfo());
//        interceptorHooks.add(new Interceptor_GetScanResults());
//        interceptorHooks.add(new Interceptor_GetWifiEnabledState());
    void initService() {
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

    }

    public void testMethod(View view) {
        LogUtils.tag(CONNECTIVITY_SERVICE).d(connectivityManager.getActiveNetworkInfo());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            LogUtils.tag(TELEPHONY_SERVICE).d(telephonyManager.getAllCellInfo());
        }
        LogUtils.tag(TELEPHONY_SERVICE).d(telephonyManager.getCellLocation());
        LogUtils.tag(TELEPHONY_SERVICE).d(telephonyManager.getNeighboringCellInfo());
        LogUtils.tag(WIFI_SERVICE).d(wifiManager.getConnectionInfo());
        LogUtils.tag(WIFI_SERVICE).d(wifiManager.getScanResults());
        LogUtils.tag(WIFI_SERVICE).d(wifiManager.getWifiState());
    }
}
