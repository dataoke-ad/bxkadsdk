package com.dataoke.bxkadsdklib.util;

import java.util.HashMap;

public class Bxk {
    public static String key,appSecret;
    public volatile static Bxk instance;

    public Bxk(String key, String appSecret) {
        this.key = key;
        this.appSecret=appSecret;
    }

    public static Bxk init(String k,String s){
        if (instance == null) {
            synchronized (Bxk.class) {
                if (instance == null) {
                    instance = new Bxk(k,s);
                    return  instance;
                }
            }

        }
        return instance;
    }
}
