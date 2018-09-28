package sxz.drhj.com.myfirstapplication.application;

import android.app.Application;
import android.content.res.Configuration;

import com.lzy.okgo.OkGo;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
