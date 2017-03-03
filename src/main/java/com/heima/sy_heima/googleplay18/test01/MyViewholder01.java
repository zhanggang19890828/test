package com.heima.sy_heima.googleplay18.test01;

import android.view.View;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.HomeBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class MyViewholder01 {


    public        TextView tv_home_title;
    private final View     mView;

    public MyViewholder01() {

        mView = View.inflate(GooglePlay.context, R.layout.item_home, null);

        //绑定
        mView.setTag(this);

        tv_home_title = (TextView) mView.findViewById(R.id.tv_home_title);
    }

    public void bindView(HomeBean.HomeItem item) {
        //ctrl+alt+P:抽取到参数中
        tv_home_title.setText(item.getName());
    }

    //把view进行返回
    public View getView() {
        return mView;
    }

}
