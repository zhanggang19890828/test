package com.heima.sy_heima.googleplay18.adapter;

import com.heima.sy_heima.googleplay18.bean.BodyItem;
import com.heima.sy_heima.googleplay18.bean.FootItem;
import com.heima.sy_heima.googleplay18.bean.HeadItem;
import com.heima.sy_heima.googleplay18.interfaces.ItemType;
import com.heima.sy_heima.googleplay18.viewholder.BaseViewholder;
import com.heima.sy_heima.googleplay18.viewholder.BodyViewHolder;
import com.heima.sy_heima.googleplay18.viewholder.FootViewHolder;
import com.heima.sy_heima.googleplay18.viewholder.HeadViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class CatagoryAdapter3 extends BasicAdapter<ItemType> {

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

    private List<ItemType> mShowItems = new ArrayList<>();

    public CatagoryAdapter3(List<ItemType> showItems) {
        super(showItems);
        this.mShowItems = showItems;
    }

    //返回不同的viewholder
    @Override
    public BaseViewholder createViewholder(int position) {
        //进行多条目判断返回不同的viewholder
        int itemViewType = getItemViewType(position);
        // 我们这里要返回不同的viewholder,因为每一个条目长的都不一样

        switch (itemViewType) {
            case headType:
                return new HeadViewHolder();
            case bodyType:
                return new BodyViewHolder();
            case footType:
                return new FootViewHolder();
        }

        //不会进入到这里,除非自己写
        return new BodyViewHolder();
    }


}
