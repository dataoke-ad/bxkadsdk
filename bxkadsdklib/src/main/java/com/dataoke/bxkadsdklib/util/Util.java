package com.dataoke.bxkadsdklib.util;

import android.content.Context;
import android.text.TextUtils;

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
//            map.put("type","8");
            map.put("sign", AssembleUtil.getSign(map));
            return map;
        }
        return new HashMap<>();
    }

    public static int dip2px(Context context,double dpValue) {
        final double scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5d);
    }
}
