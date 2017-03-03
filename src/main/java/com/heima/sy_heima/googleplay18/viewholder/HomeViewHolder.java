package com.heima.sy_heima.googleplay18.viewholder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.HomeBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;
import com.heima.sy_heima.googleplay18.utils.Uris;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class HomeViewHolder extends BaseViewholder<HomeBean.HomeItem> {


    @Bind(R.id.tv_home_title)
    TextView  mTvHomeTitle;
    @Bind(R.id.iv_home_icon)
    ImageView mIvHomeIcon;
    @Bind(R.id.rb_home_start)
    RatingBar mRbHomeStart;
    @Bind(R.id.tv_home_size)
    TextView  mTvHomeSize;
    @Bind(R.id.tv_home_desc)
    TextView  mTvHomeDesc;

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.item_home, null);

        return view;
    }

    public void bindView(HomeBean.HomeItem item) {
        mTvHomeTitle.setText(item.getName());
        mRbHomeStart.setRating(item.getStars());
        String apkSize = Formatter.formatFileSize(GooglePlay.context, item.getSize());
        mTvHomeSize.setText(apkSize);
        mTvHomeDesc.setText(item.getDes());
  /*      DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher) //显示图片加载中
                .showImageForEmptyUri(R.mipmap.ic_launcher) //空的图片
                .showImageOnFail(R.mipmap.ic_launcher) //错误的图片
                .cacheInMemory(true) //内存缓存要不要
                .cacheOnDisk(true) //sd卡缓存要不要
                .considerExifParams(true)//会识别图片的方向信息
//                .displayer(new FadeInBitmapDisplayer(500)).build();//显示的效果
        				.displayer(new RoundedBitmapDisplayer(36)).build();*/


        //图片地址:

        //设置图片
        ImageLoader.getInstance().displayImage(Uris.IMAGEHOST +item.getIconUrl(),mIvHomeIcon,mOptions);
    }
}
