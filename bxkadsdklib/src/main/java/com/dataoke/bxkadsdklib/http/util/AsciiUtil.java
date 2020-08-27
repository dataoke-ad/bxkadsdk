package com.dataoke.bxkadsdklib.http.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jey on 2019/9/23.
 * Desc : AsciiUtil ascii码 工具类
 * Update :
 * {
 *
 * by bxk on 2019/9/23
 * }
 */
public class AsciiUtil {

    // 按ascii码排序
    public static List<char[]> sort(List<String> list) {

        List<char[]> listCharO = new ArrayList<char[]>();
        List<char[]> listChar = new ArrayList<char[]>();

        try {

            //1.把list数组中的每个字符串 转为字符数组
            for (int i = 0; i < list.size(); i++) {
                listChar.add(list.get(i).toCharArray());
            }

            listCharO = listChar;

            //2.循环数组listChar取出每一个进行冒泡排序比较
            for (int a = 0; a < listChar.size(); a++) {
                for (int b = 0; b < listChar.size(); b++) {
                    char[] aChar = listChar.get(a);
                    char[] bChar = listChar.get(b);

                    //相同的就不需要比较
                    if (aChar != bChar) {
                        int size = 0;

                        //可能会存在前面都一样的字符所有取字符数组长度小的 来进行循环比较 字符的ascll

                        if (aChar.length > bChar.length) {
                            size = bChar.length;
                        } else {
                            size = aChar.length;
                        }

                        //循环比较赋值
                        for (int c = 0; c < size; c++) {
                            if ((int) aChar[c] > (int) bChar[c]) {
                                if (a < b) {
                                    listCharO.set(b, aChar);
                                    listCharO.set(a, bChar);

                                    break;
                                }
                            } else if ((int) aChar[c] == (int) bChar[c]) {

                            } else if ((int) aChar[c] < (int) bChar[c]) {
                                break;
                            }
                        }
                    }

                }
            }
            for (char[] s : listCharO) {

                System.out.println(String.valueOf(s));
            }
        } catch (Exception e) {

            e.printStackTrace();

        }

        return listCharO;
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("2");
        list.add("1");
        list.add("aB");
        list.add("ac");
        list.add("ab");
        list.add("aC");
        list.add("Aa");
        list.add("Bc");
        list.add("bQ");
        list.add("BW");

        sort(list);
    }


}
