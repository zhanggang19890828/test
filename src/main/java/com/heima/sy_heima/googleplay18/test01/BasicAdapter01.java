package com.heima.sy_heima.googleplay18.test01;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: old样
 * 描述:把所有可变的放到viewholder中
 * 上海传智播客android黑马程序员
 */

public abstract class BasicAdapter01<T> extends BaseAdapter{
    //数据展示的集合
    private List<T> mShowItems = new ArrayList<>();

    public BasicAdapter01(List<T> showItems) {
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
        MyBaseViewholder myViewholder01;
        if (convertView == null) {
            //convertView = View.inflate(parent.getContext(), R.layout.item_home, null);
            //myViewholder01 = new MyViewholder01(convertView);

            //绑定
           /* convertView.setTag(myViewholder01);*/

            myViewholder01 = createViewHolder();

        } else {
            myViewholder01 = (MyBaseViewholder) convertView.getTag();
        }

        //绑定数据
        myViewholder01.bindView(mShowItems.get(position));

        return myViewholder01.getView();
    }

    public abstract MyBaseViewholder createViewHolder() ;



   /* class MyViewholder01{

        public TextView tv_home_title;
        private final View mView;

        public MyViewholder01() {

            mView = View.inflate(GooglePlay.context, R.layout.item_home, null);

            //绑定
            mView.setTag(this);

            tv_home_title = (TextView) mView.findViewById(R.id.tv_home_title);
        }

        private void bindView( HomeBean.HomeItem item) {
            //ctrl+alt+P:抽取到参数中
           tv_home_title.setText(item.getName());
        }

        //把view进行返回
        public View getView() {
            return mView;
        }
    }*/
}
