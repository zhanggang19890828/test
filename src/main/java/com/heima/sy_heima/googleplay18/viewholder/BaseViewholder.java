package com.heima.sy_heima.googleplay18.viewholder;

import android.view.View;

import com.heima.sy_heima.googleplay18.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import butterknife.ButterKnife;

/**
 * 作者: old样
 * 描述:把viewholder共有的放在一起
 * 上海传智播客android黑马程序员
 */

public abstract class BaseViewholder<T> {

    private final View     mView;
    public final DisplayImageOptions mOptions;
    public final DisplayImageOptions mOptionsR;

    public BaseViewholder() {
        mView = createItemView();
        //领证
        mView.setTag(this);
        ButterKnife.bind(this, mView);

        //显示图片加载中
        //空的图片
        //错误的图片
        //内存缓存要不要
        //sd卡缓存要不要
        //会识别图片的方向信息
        //显示的效果
        mOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher) //显示图片加载中
                .showImageForEmptyUri(R.mipmap.ic_launcher) //空的图片
                .showImageOnFail(R.mipmap.ic_launcher) //错误的图片
                .cacheInMemory(true) //内存缓存要不要
                .cacheOnDisk(true) //sd卡缓存要不要
                .considerExifParams(true)//会识别图片的方向信息
                .displayer(new FadeInBitmapDisplayer(500)).build();
        //.displayer(new RoundedBitmapDisplayer(36)).build();

        mOptionsR = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher) //显示图片加载中
                .showImageForEmptyUri(R.mipmap.ic_launcher) //空的图片
                .showImageOnFail(R.mipmap.ic_launcher) //错误的图片
                .cacheInMemory(true) //内存缓存要不要
                .cacheOnDisk(true) //sd卡缓存要不要
                .considerExifParams(true)//会识别图片的方向信息
//                .displayer(new FadeInBitmapDisplayer(500)).build();
        .displayer(new RoundedBitmapDisplayer(36)).build();
    }

    public abstract View createItemView();

    public abstract void bindView(T item);

    //返回view
    public View getView() {
        return mView;
    }
}
