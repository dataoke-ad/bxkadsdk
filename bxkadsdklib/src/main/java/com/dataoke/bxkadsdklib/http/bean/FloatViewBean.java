package com.dataoke.bxkadsdklib.http.bean;

public class FloatViewBean {

    /**
     * front_img : https://sr.ffquan.cn/bxk_admin/20200728/bsg0q1f6vrkam3akm0q00.gif
     * show_type : 0
     * data_type : 2
     */

    private String front_img;
    private int show_type;
    private int data_type;
    private String mall_url;

    public String getMall_url() {
        return mall_url;
    }

    public void setMall_url(String mall_url) {
        this.mall_url = mall_url;
    }

    public String getFront_img() {
        return front_img;
    }

    public void setFront_img(String front_img) {
        this.front_img = front_img;
    }

    public int getShow_type() {
        return show_type;
    }

    public void setShow_type(int show_type) {
        this.show_type = show_type;
    }

    public int getData_type() {
        return data_type;
    }

    public void setData_type(int data_type) {
        this.data_type = data_type;
    }
}
