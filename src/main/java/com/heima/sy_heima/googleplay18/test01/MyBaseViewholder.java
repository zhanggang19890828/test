package com.heima.sy_heima.googleplay18.test01;

import android.view.View;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public abstract class MyBaseViewholder<T> {

    private final View     mView;

    public MyBaseViewholder() {

        mView = createItemView();

        //绑定
        mView.setTag(this);

    }

    public abstract View createItemView();

    public abstract void bindView(T item);

    //把view进行返回
    public View getView() {
        return mView;
    }
}
