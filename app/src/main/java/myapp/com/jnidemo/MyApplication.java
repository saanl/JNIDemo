package myapp.com.jnidemo;


import android.app.Application;

import com.bugsee.library.Bugsee;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugsee.launch(this, "a5676d19-1ab7-416e-961a-2266388cf21b");
    }
}