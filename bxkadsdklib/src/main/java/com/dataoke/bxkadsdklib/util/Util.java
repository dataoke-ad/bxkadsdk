package com.dataoke.bxkadsdklib.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.dataoke.bxkadsdklib.http.util.AssembleUtil;

import java.util.HashMap;

public class Util {
    public static boolean checkKeySecret(){
        if (TextUtils.isEmpty(Bxk.key)||TextUtils.isEmpty(Bxk.appSecret)){
            throw new RuntimeException("请在application中初始化key,appSecret");
        }
        return true;
    }

    public static HashMap<String,String> getMyMap(String comid){
        if (checkKeySecret()){
            if (TextUtils.isEmpty(comid)){
                throw new RuntimeException("控件comId不能为空");
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("appKey",Bxk.key);
            map.put("client","wap");
            map.put("comId",comid);
            map.put("version","2.0.0");
            map.put("sign", AssembleUtil.getSign(map));
            return map;
        }
        return new HashMap<>();
    }

    public static HashMap<String,String> getSearchMap(String comid,String content){
        if (checkKeySecret()){
            if (TextUtils.isEmpty(comid)){
                throw new RuntimeException("控件comId不能为空");
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("appKey",Bxk.key);
            map.put("client","wap");
            map.put("comId",comid);
            map.put("keywords",content);
            map.put("version","2.0.0");
            map.put("sign", AssembleUtil.getSign(map));
            return map;
        }
        return new HashMap<>();
    }


    public static int dip2px(Context context,double dpValue) {
        final double scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5d);
    }

    public static void toOutBrowser(Context context, String url, View view){
        try {
            if (!TextUtils.isEmpty(url)){
                view.setOnClickListener(view1 -> {
                    Intent intent= new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                });
            }
        }catch (Exception e){}
    }
}
