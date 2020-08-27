package com.dataoke.bxkadsdklib.http.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Created by jey on 2019/9/23.
 * Desc : Md5Util  Md5工具类
 * Update :
 * {
 * by bxk on 2019/9/23
 * }
 */
public class Md5Util {

    public static void main(String[] args) {

        System.out.println(MD5("hello world"));
    }

    // 字符串 md5 用于 生成sign
    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes(Charset.forName("UTF-8")));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHex(byte[] bs) {
        StringBuilder sb = new StringBuilder(40);
        for (byte x : bs) {
            if ((x & 0xff) >> 4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }
    

}
