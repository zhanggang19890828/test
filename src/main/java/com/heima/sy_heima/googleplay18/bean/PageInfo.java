package com.heima.sy_heima.googleplay18.bean;

import android.support.v4.app.Fragment;

/**
 * 作者: old样
 * 描述:页面集合封闭
 * 上海传智播客android黑马程序员
 */

public class PageInfo {

    //标题
    public String title;
    //fragment
    public Fragment fragment;

    public PageInfo(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
