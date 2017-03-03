package com.heima.sy_heima.googleplay18.bean;

/**
 * 作者: old样
 * 描述:subject对象
 * 上海传智播客android黑马程序员
 */

public class SubjectBean {


    /**
     * des : 一周新锐游戏精选
     * url : image/recommend_01.jpg
     */

    private String des;
    private String url;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SubjectBean(String des, String url) {
        this.des = des;
        this.url = url;
    }
}
