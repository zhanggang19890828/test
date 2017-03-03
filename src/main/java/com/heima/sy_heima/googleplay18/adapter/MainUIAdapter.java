package com.heima.sy_heima.googleplay18.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.heima.sy_heima.googleplay18.bean.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: old样
 * 描述: 这个ui的adapter
 * 上海传智播客android黑马程序员
 */

public class MainUIAdapter extends FragmentStatePagerAdapter {

    private List<PageInfo> mShowItems = new ArrayList<>();

    public MainUIAdapter(FragmentManager fm, List<PageInfo> showItems) {
        super(fm);
        mShowItems = showItems;
    }

    @Override
    public Fragment getItem(int position) {
        return mShowItems.get(position).fragment;
    }

    @Override
    public int getCount() {
        return mShowItems.size();
    }

    //标题头

    @Override
    public CharSequence getPageTitle(int position) {
        return mShowItems.get(position).title;
    }
}
