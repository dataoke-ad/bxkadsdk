package com.dataoke.bxkadsdklib.util;

import android.text.TextUtils;

/**
 * Created by jey on 2019/8/25.
 * Desc : PicUrlFormatUtil 图片拼接webp 三种模式
 * Update :
 * {
 * by jey on 2019/8/25
 * }
 */

public class PicUrlFormatUtil {
    // 淘宝商品 //开头地址拼接
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

    // 拼接webp
    public static String assembleGoodsPicModel(String url, String picModel) {
        String urlWithModel = "";
        urlWithModel = urlFormat(url);
        if (!TextUtils.isEmpty(urlWithModel)) {
            if (!urlWithModel.endsWith(".webp")) {

                urlWithModel = urlWithModel.concat(picModel);
            }
        }
        return urlWithModel;
    }

}
