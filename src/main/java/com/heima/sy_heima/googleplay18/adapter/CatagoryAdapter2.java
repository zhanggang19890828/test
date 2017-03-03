/*
package com.heima.sy_heima.googleplay18.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.BodyItem;
import com.heima.sy_heima.googleplay18.bean.FootItem;
import com.heima.sy_heima.googleplay18.bean.HeadItem;
import com.heima.sy_heima.googleplay18.interfaces.ItemType;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 *//*


public class CatagoryAdapter2 extends BaseAdapter {
    private List<ItemType> mShowItems = new ArrayList<>();

    public CatagoryAdapter2(List<ItemType> showItems) {
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
        //根据不同的条目类型去展示不同的view
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case headType:
                TextView textView = new TextView(parent.getContext());
                textView.setText("我是头");
                return textView;
            case bodyType:
                ImageView imageView = new ImageView(parent.getContext());
                imageView.setImageResource(R.mipmap.ic_launcher);
                return imageView;
            case  footType:
                TextView textViewf = new TextView(parent.getContext());
                textViewf.setText("我是脚");
                return textViewf;

        }

        return null;
    }

    //viewholder
    class HeadViewHolder
    {
        TextView mTextView;
        public HeadViewHolder() {
            mTextView = mTextView.findViewById(R.id.xxx);
        }
    }
    class HeadViewHolder2
    {
        ImageView mTextView;
        public HeadViewHolder2() {
            mTextView = mTextView.findViewById(R.id.xxx);
        }
    }

    //显示三个条目
    //这个必须是0开始
    public static final int footType = 2;
    public static final int bodyType = 1;
    public static final int headType = 0;


    //告诉有几个类型
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    //具体的postion显示的类型
    //position是数组的个数
    @Override
    public int getItemViewType(int position) {
   */
/*     switch (position) {
            case  0:
                return headType;
            case  1:
                return bodyType;
            case 2:
                return footType;
        }
       return bodyType;*//*

        if (mShowItems.get(position) instanceof HeadItem) {
            return headType;
        }
        if (mShowItems.get(position) instanceof BodyItem) {
            return bodyType;
        }
        if (mShowItems.get(position) instanceof FootItem) {
            return footType;
        }

        return bodyType;

    }
}
*/
