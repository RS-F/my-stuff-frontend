package de.telekom.sea.mystuff.frontend.android;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import lombok.Getter;
import timber.log.Timber;

public class MyStuffApplication extends Application {

    private static MyStuffApplication instance;

    @Getter
    private MyStuffContext myStuffContext;

    public static MyStuffApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("onCreate: Enter");
        instance = this;
        myStuffContext = new MyStuffContext();
        myStuffContext.initWithApplication(this);

        initTimber();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Timber.d("onConfigurationChanged: Enter");
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }}
