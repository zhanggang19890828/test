package com.heima.sy_heima.googleplay18.adapter;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class FinalAdapter<T> extends BaseAdapter {

    private List<T> mShowItems = new ArrayList<>();

    private int mLayoutId = 0;

    private AdapterListener mAdapterListener;

    public FinalAdapter(List<T> showItems,int layoutId,AdapterListener adapterListener) {
        mShowItems = showItems;
        this.mLayoutId = layoutId;
        this.mAdapterListener = adapterListener;
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

        FinalViewHolder finalViewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), mLayoutId, null);
            finalViewHolder = new FinalViewHolder(convertView);

            convertView.setTag(finalViewHolder);

        } else {
            finalViewHolder = (FinalViewHolder) convertView.getTag();
        }

        //绑定数据
        bindView(finalViewHolder, mShowItems.get(position));


        return convertView;
    }

    //这里是可变
    private void bindView( FinalViewHolder finalViewHolder,T content) {
       // finalViewHolder.tv.setText(content);
        //谁用谁来绑定数据
        mAdapterListener.bindView(finalViewHolder,content);
    }

    //接口
    public interface AdapterListener<T>
    {
        void bindView(FinalViewHolder finalViewHolder,T content);
    }

    //接收

    public static class FinalViewHolder{


        public View mLayoutView;

        public FinalViewHolder(View layoutView) {
            //tv = (TextView) view.findViewById(R.id.tv_home_title);
            this.mLayoutView = layoutView;
        }

        //根据id自动查找控件
       // private HashMap<Integer, View> mViewHashMap = new HashMap<>();

        //使用性能更高的
        private SparseArray<View> mViewHashMap = new SparseArray<>();

        public View getViewById(int id) {
            View view = mViewHashMap.get(id);
            if (view == null) {
                view = mLayoutView.findViewById(id);
                mViewHashMap.put(id, view);
            }

            return view;
        }


//以后可以增加
        public TextView getTextView(int id) {
           return (TextView) getViewById(id);
        }


    }
}
