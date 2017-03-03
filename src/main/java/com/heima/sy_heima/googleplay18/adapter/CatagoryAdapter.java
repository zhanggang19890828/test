package com.heima.sy_heima.googleplay18.adapter;

import com.heima.sy_heima.googleplay18.viewholder.BaseViewholder;
import com.heima.sy_heima.googleplay18.viewholder.BodyViewHolder;
import com.heima.sy_heima.googleplay18.viewholder.TitleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class CatagoryAdapter extends BasicAdapter<Object> {

    private List<Object> mShowItems = new ArrayList<>();

    public CatagoryAdapter(List<Object> showItems) {
        super(showItems);
        mShowItems = showItems;
    }

    /**************定义type类型**************/
    public static final int TITLETYPE = 0;
    public static final int BODYTYPE = 1;


    //根据不同的对象返回不同的类型
    @Override
    public int getItemViewType(int position) {
        if (mShowItems.get(position) instanceof String) {
            return TITLETYPE;
        } else {
            return BODYTYPE;
        }
    }

    //当前条目数
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    //这里要返回一个viewholder,viewholder根据view查找控件,多个view,代表就是多个viewholder
    @Override
    public BaseViewholder createViewholder(int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == TITLETYPE) {
            //头部的viewholder
            return new TitleViewHolder();
        } else {
            //body的viewholder
            return new BodyViewHolder();
        }

    }
}
