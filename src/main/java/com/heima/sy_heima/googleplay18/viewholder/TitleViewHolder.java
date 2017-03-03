package com.heima.sy_heima.googleplay18.viewholder;

import android.view.View;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;

import butterknife.Bind;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class TitleViewHolder extends BaseViewholder<String> {
    @Bind(R.id.tv_title)
    TextView mTvTitle;

    @Override
    public View createItemView() {
        //在传上下文的时候,就近原则,能用activity就用activity,如果没有那就application
        //在dialog的时候必须要使用activity的上下文,因为有样式

        View view = View.inflate(GooglePlay.context, R.layout.adapter_title, null);


        return view;
    }

    @Override
    public void bindView(String item) {
        mTvTitle.setText(item);
    }
}
