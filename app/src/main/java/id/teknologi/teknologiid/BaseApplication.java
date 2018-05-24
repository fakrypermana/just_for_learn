package id.teknologi.teknologiid;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;
import com.orhanobut.hawk.Hawk;

import io.fabric.sdk.android.Fabric;

/**
 * Created by galihgasur on 7/4/17.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        initHawk();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    private void initHawk(){
        Hawk.init(this).build();
    }
}