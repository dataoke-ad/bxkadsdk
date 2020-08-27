package com.dataoke.bxkadsdklib.util;
import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * Created by jey on 2019/9/24.
 * Desc : StrUtil
 * Update :
 * {
 * by jey on 2019/9/24
 * }
 */
public class StrUtil {

    public static String removeZero(String s) {
        if (s != null && !s.equals("") && !s.equals("null")) {
            if (s.indexOf(".") > 0) {
                //正则表达
                s = s.replaceAll("0+?$", "");//去掉后面无用的零
                s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
            }
        }
        return s;
    }

    public static String urlFormat(String url) {
        if (!TextUtils.isEmpty(url)) {
            url = url.trim();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                if (url.startsWith("//")) {
                    url = "http:" + url;
                } else {
                    url = "http://" + url;
                }
            }
        }
        return url;
    }

    /**
     * 数量转换为 千 万
     *
     * @param num
     * @return
     */
    public static String numToQianWan(int num) {
        if (num <= 0) return "0";
        else if (num < 1000) {
            return num + "";
        } else if (num >= 1000 && num < 10000) {
            float q = (float) num / 1000;
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            return decimalFormat.format(q) + "千";
        } else {
            float w = (float) num / 10000;
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            return decimalFormat.format(w) + "万";
        }
    }

}
