package com.heima.sy_heima.googleplay18.adapter;

import com.heima.sy_heima.googleplay18.bean.SubjectBean;
import com.heima.sy_heima.googleplay18.viewholder.BaseViewholder;
import com.heima.sy_heima.googleplay18.viewholder.SubjectViewHolder;

import java.util.List;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class SubjectListAdapter extends BasicAdapter<SubjectBean> {

    public SubjectListAdapter(List<SubjectBean> showItems) {
        super(showItems);
    }

    @Override
    public BaseViewholder createViewholder(int position) {
        return new SubjectViewHolder();
    }
}
