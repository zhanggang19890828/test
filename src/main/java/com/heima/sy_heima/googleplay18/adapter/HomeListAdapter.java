package com.heima.sy_heima.googleplay18.adapter;

import com.heima.sy_heima.googleplay18.bean.HomeBean;
import com.heima.sy_heima.googleplay18.viewholder.BaseViewholder;
import com.heima.sy_heima.googleplay18.viewholder.HomeViewHolder;

import java.util.List;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class HomeListAdapter extends BasicAdapter<HomeBean.HomeItem> {
    public HomeListAdapter(List<HomeBean.HomeItem> showItems) {
        super(showItems);
    }

    @Override
    public BaseViewholder createViewholder(int position) {
        return new HomeViewHolder();
    }
}
