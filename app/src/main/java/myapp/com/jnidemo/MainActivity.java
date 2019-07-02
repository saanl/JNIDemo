package myapp.com.jnidemo;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;
import net.hockeyapp.android.LoginManager;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.UpdateManagerListener;
import net.hockeyapp.android.metrics.MetricsManager;

import org.json.JSONArray;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("hellondk");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);

        tv.setText(stringFromJNI());

        init();

        initAppCenter();
    }



    private void init() {

        CrashManager.register(this,new CrashManagerListener(){
            @Override
            public boolean ignoreDefaultHandler() {
                return super.ignoreDefaultHandler();
            }
        });
        UpdateManager.register(this);
        UpdateManager.register(this, new UpdateManagerListener() {
            @Override
            public void onUpdateAvailable(JSONArray data, String url) {
                super.onUpdateAvailable(data, url);
                Log.e("UpdateJSON",String.format("JSONArray:%s",data.toString()));
            }
        });

//        LoginManager.register(this,"01ad005bdffbcd7e436bdf28156dd93a",LoginManager.LOGIN_MODE_EMAIL_ONLY);
//        LoginManager.verifyLogin(this,getIntent());

        MetricsManager.register(getApplication());

        HashMap<String, String> properties = new HashMap<>();
        properties.put("Model", android.os.Build.MODEL);
        properties.put("Brand", android.os.Build.BRAND);
        properties.put("OS", android.os.Build.VERSION.RELEASE);

        HashMap<String, Double> measurements = new HashMap<>();
        measurements.put("Measurement1", 1.0);

        MetricsManager.trackEvent("APP_METRICS", properties, measurements);
    }

    public void initAppCenter(){
        AppCenter.start(getApplication(), "35168800-1920-4855-8ac0-7f32d7942e58",
                Analytics.class, Crashes.class);
        AppCenter.start(getApplication(), "35168800-1920-4855-8ac0-7f32d7942e58", Analytics.class, Crashes.class);

        HashMap<String, String> properties = new HashMap<>();
        properties.put("Model", android.os.Build.MODEL);
        properties.put("Brand", android.os.Build.BRAND);
        properties.put("OS", android.os.Build.VERSION.RELEASE);

        Analytics.trackEvent("APP_METRICS", properties);

    }

    public void click(View view){
        HelloCmake helloCmake = new HelloCmake();
        Toast.makeText(this,helloCmake.getString(),Toast.LENGTH_SHORT).show();
    }
    public void click1(View view){

        int[] arr = {1,2,3,4,5,6};
        String s = null;
        Log.e("***************",""+arr[8]);
        Log.e("***************",""+s.toLowerCase());

    }

    public void click2(View view){
        Intent intent = new Intent(this,WebViewActivity.class);
        startActivity(intent);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UpdateManager.unregister();
    }
}
