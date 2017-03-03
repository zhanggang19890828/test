package com.heima.sy_heima.googleplay18.viewholder;

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

public class HomeViewHolder2 {
    public           TextView tv;
    private final View     mView;

    public HomeViewHolder2() {
        mView = View.inflate(GooglePlay.context, R.layout.item_home, null);
        //领证
        mView.setTag(this);

        tv = (TextView) mView.findViewById(R.id.tv_home_title);
    }

    public void bindView(HomeBean.HomeItem item) {
        tv.setText(item.getName());
        tv.setText(item.getName());
    }

    //返回view
    public View getView() {
        return mView;
    }
}
