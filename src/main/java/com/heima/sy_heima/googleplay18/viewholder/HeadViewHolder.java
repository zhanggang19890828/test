package com.heima.sy_heima.googleplay18.viewholder;

import android.view.View;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.gloab.GooglePlay;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class HeadViewHolder extends BaseViewholder {
    @Override
    public View createItemView() {
        TextView textView = new TextView(GooglePlay.context);
        textView.setText("我是头");
        return textView;
    }

    @Override
    public void bindView(Object item) {

    }
}
