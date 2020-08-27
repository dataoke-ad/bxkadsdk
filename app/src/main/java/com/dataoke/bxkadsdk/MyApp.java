package com.dataoke.bxkadsdk;

import android.app.Application;

import com.dataoke.bxkadsdklib.util.Bxk;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bxk.init("eebf41de","6a4188fa009807a30b9dc4e0df3d2a05");
    }
}
