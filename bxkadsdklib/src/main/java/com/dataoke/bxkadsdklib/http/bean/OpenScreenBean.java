package com.dataoke.bxkadsdklib.http.bean;

import java.util.List;

public class OpenScreenBean {
    private int show_type;
    private int data_type;
    private String mall_url;
    private String front_img;
    private String appLogo;
    private String appLogoLinkUrl;
    private List<ListBean> list;

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getAppLogoLinkUrl() {
        return appLogoLinkUrl;
    }

    public void setAppLogoLinkUrl(String appLogoLinkUrl) {
        this.appLogoLinkUrl = appLogoLinkUrl;
    }

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * pic : //img.alicdn.com/i1/2208227844594/O1CN01ZKrTJg1jo68uPQeDu_!!2208227844594.jpg
         * tag : 天猫
         * tag1 : 5.90元购10包
         * quanJine : 5.0
         * istmall : 1
         * xiaoliang : 9474
         * dtitle : 【人间珍果】香酥脆灰枣手抓包10包
         * jiage : 5.9
         * url : http://bxk.dataoke.com/api/wap/component/click-static/v1?url=aHR0cDovL2J4ay5kYXRhb2tlLmNvbS8_cj13YXAmYXBwa2V5PWVlYmY0MWRlJnRlbXBrZXk9ZjVkYWRlMjgjL2RldGFpbGVkLzI4NTg0ODEyXzE&comptType=12&comId=1886&comptId=255
         */

        private String pic;
        private String tag;
        private String tag1;
        private double quanJine;
        private int istmall;
        private int xiaoliang;
        private String dtitle;
        private double jiage;
        private String url;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTag1() {
            return tag1;
        }

        public void setTag1(String tag1) {
            this.tag1 = tag1;
        }

        public double getQuanJine() {
            return quanJine;
        }

        public void setQuanJine(double quanJine) {
            this.quanJine = quanJine;
        }

        public int getIstmall() {
            return istmall;
        }

        public void setIstmall(int istmall) {
            this.istmall = istmall;
        }

        public int getXiaoliang() {
            return xiaoliang;
        }

        public void setXiaoliang(int xiaoliang) {
            this.xiaoliang = xiaoliang;
        }

        public String getDtitle() {
            return dtitle;
        }

        public void setDtitle(String dtitle) {
            this.dtitle = dtitle;
        }

        public double getJiage() {
            return jiage;
        }

        public void setJiage(double jiage) {
            this.jiage = jiage;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
