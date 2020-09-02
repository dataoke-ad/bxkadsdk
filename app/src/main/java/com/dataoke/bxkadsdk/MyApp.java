package com.dataoke.bxkadsdk;

import android.app.Application;

import com.dataoke.bxkadsdklib.util.Bxk;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bxk.init("0df59c13","4351e56096c6c4410bd3094bbc862b2e");
    }
}
