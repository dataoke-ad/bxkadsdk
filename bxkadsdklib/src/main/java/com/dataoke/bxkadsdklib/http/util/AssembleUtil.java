package com.dataoke.bxkadsdklib.http.util;

import android.text.TextUtils;

import com.dataoke.bxkadsdklib.util.Bxk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jey on 2019/9/23.
 * Desc : AssembleUtil
 * Update :
 * {
 * by bxk on 2019/9/23
 * }
 */
public class AssembleUtil {

    // 获取签名
    public static String getSign(HashMap<String, String> paramsAss) {
        if (TextUtils.isEmpty(Bxk.appSecret)) throw new RuntimeException("请在application中初始化appSecret");
        else {
            String sign = "";

            //        HashMap<String, String> paramsMap = new HashMap<>();

            List<String> paramKeyList = new ArrayList<>();

            for (String key : paramsAss.keySet()) {

                paramKeyList.add(key);
            }

            // 参数ascii排序
            List<char[]> paramKeyO = new ArrayList<>();
            paramKeyO = AsciiUtil.sort(paramKeyList);
            String paramsStr = "";
            for (int i = 0; i < paramKeyO.size(); i++) {
                String param = "";

                String paramKey = String.valueOf(paramKeyO.get(i));

                String paramValue = paramsAss.get(String.valueOf(paramKeyO.get(i)));

                if (!TextUtils.isEmpty(paramValue)) {

                    if (!TextUtils.isEmpty(paramsStr)) {
                        param = "&" + paramKey + "=" + paramValue;

                    } else {
                        param = "" + paramKey + "=" + paramValue;
                    }
                }
                paramsStr = paramsStr.concat(param);
            }
            paramsStr = paramsStr.concat("&key=" + Bxk.appSecret);
            sign = Md5Util.MD5(paramsStr);
            return sign;
        }
    }

}
