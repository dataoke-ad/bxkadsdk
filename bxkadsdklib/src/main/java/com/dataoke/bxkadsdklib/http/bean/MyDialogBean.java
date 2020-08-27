package com.dataoke.bxkadsdklib.http.bean;

public class MyDialogBean {
    /**
     * front_img : https://thumbnail.dataoke.com/img/dtk/article/201912/2019121217311783039035.gif
     * mall_url : http://bxk.dataoke.com/api/wap/component/click-static/v1?url=aHR0cDovL2J4ay5kYXRhb2tlLmNvbS8_cj13YXAmYXBwa2V5PWVlYmY0MWRlJnRlbXBrZXk9ZjVkYWRlMjgjL3J1c2hpbmdSYW5raW5n&comptType=12&comId=1885&comptId=231
     * show_type : 0
     * data_type : 2
     */

    private String front_img;
    private String mall_url;
    private int show_type;
    private int data_type;

    public String getFront_img() {
        return front_img;
    }

    public void setFront_img(String front_img) {
        this.front_img = front_img;
    }

    public String getMall_url() {
        return mall_url;
    }

    public void setMall_url(String mall_url) {
        this.mall_url = mall_url;
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
