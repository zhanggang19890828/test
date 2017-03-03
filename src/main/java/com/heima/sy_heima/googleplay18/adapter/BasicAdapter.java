package com.heima.sy_heima.googleplay18.adapter;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;

import com.heima.sy_heima.googleplay18.viewholder.BaseViewholder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: old样
 * 描述:现在要做的事情是什么所有可变抽取到viewholdr中
 * 上海传智播客android黑马程序员
 */

public abstract class BasicAdapter<T> extends BaseAdapter {

    private List<T> mShowItems = new ArrayList<>();

    public BasicAdapter(List<T> showItems) {
        mShowItems = showItems;
    }

    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mShowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewholder viewHolder;


        if (convertView == null) {
            //谁用adapter谁给viewholder
            viewHolder = createViewholder(position);
        } else {
            //这里复用不会为空
            viewHolder = (BaseViewholder) convertView.getTag();
        }
        //绑定数据
        viewHolder.bindView(mShowItems.get(position));


        View view = viewHolder.getView();

        view.setScaleX(.6f);
        view.setScaleY(.6f);


        //旋转
        if (position % 2 == 0) {
            view.setRotation(90f);
        } else {
            view.setRotation(-90f);
        }


        //动画效果组件-->view组件
        ViewCompat.animate(view).scaleX(1f).scaleY(1f).rotation(0).setInterpolator(new OvershootInterpolator()).setDuration(500).start();

        return view;
    }

    public abstract BaseViewholder createViewholder(int position);


    //把 view初始化的工作在viewholdr中做
    /*class ViewHolder
    {
        public TextView tv;
        private final View mView;

        public ViewHolder() {
            mView = View.inflate(GooglePlay.context, R.layout.item_home, null);
            //领证
            mView.setTag(this);

            tv = (TextView) mView.findViewById(R.id.tv_home_title);
        }

        private void bindView(HomeBean.HomeItem item) {
            tv.setText(item.getName());
            tv.setText(item.getName());
        }

        //返回view
        public View getView() {
            return mView;
        }
    }*/
}
