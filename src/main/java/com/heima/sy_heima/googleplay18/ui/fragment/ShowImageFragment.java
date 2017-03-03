package com.heima.sy_heima.googleplay18.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;
import com.heima.sy_heima.googleplay18.utils.Uris;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class ShowImageFragment extends BaseFragment {
    public DisplayImageOptions mOptions;

    @Override
    protected View createView() {

        Bundle arguments = getArguments();
        final ArrayList<String> images = arguments.getStringArrayList("images");
        int position = arguments.getInt("position");

        ViewPager viewPager = new ViewPager(GooglePlay.context);

        //设置adapter
        viewPager.setAdapter(new PagerAdapter() {


            @Override
            public int getCount() {
                return images.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                mOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher) //显示图片加载中
                        .showImageForEmptyUri(R.mipmap.ic_launcher) //空的图片
                        .showImageOnFail(R.mipmap.ic_launcher) //错误的图片
                        .cacheInMemory(true) //内存缓存要不要
                        .cacheOnDisk(true) //sd卡缓存要不要
                        .considerExifParams(true)//会识别图片的方向信息
                        .displayer(new FadeInBitmapDisplayer(500)).build();
                //.displayer(new RoundedBitmapDisplayer(36)).build();
//                ImageView imageView = new ImageView(container.getContext());
                PhotoView imageView = new PhotoView(container.getContext());
                ImageLoader.getInstance().displayImage(Uris.IMAGEHOST+images.get(position),imageView,mOptions);

                //添加到控件中
                container.addView(imageView);

                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        //设置当前选中的
        viewPager.setCurrentItem(position);

        return viewPager;
    }

    @Override
    public Object rquestURLData() {
        return "";
    }
}
