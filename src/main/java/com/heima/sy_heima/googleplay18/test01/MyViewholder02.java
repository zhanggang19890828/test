package com.heima.sy_heima.googleplay18.test01;

import android.view.View;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.HomeBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class MyViewholder02 extends MyBaseViewholder<HomeBean.HomeItem> {


    @Override
    public View createItemView() {
        return View.inflate(GooglePlay.context, R.layout.item_home, null);
    }

    @Override
    public void bindView(HomeBean.HomeItem item) {
        //进行数据设置
    }
}
