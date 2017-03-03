package com.heima.sy_heima.googleplay18.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.SubjectBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;
import com.heima.sy_heima.googleplay18.utils.Uris;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class SubjectViewHolder extends BaseViewholder<SubjectBean> {

    @Bind(R.id.iv_image)
    ImageView mIvImage;
    @Bind(R.id.tv_des)
    TextView  mTvDes;

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.adapter_subject, null);

        return view;
    }

    @Override
    public void bindView(SubjectBean item) {
        mTvDes.setText(item.getDes());
      /*  DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher) //显示图片加载中
                .showImageForEmptyUri(R.mipmap.ic_launcher) //空的图片
                .showImageOnFail(R.mipmap.ic_launcher) //错误的图片
                .cacheInMemory(true) //内存缓存要不要
                .cacheOnDisk(true) //sd卡缓存要不要
                .considerExifParams(true)//会识别图片的方向信息
                              .displayer(new FadeInBitmapDisplayer(500)).build();//显示的效果
                //.displayer(new RoundedBitmapDisplayer(36)).build();*/
        ImageLoader.getInstance().displayImage(Uris.IMAGEHOST +item.getUrl(),mIvImage,mOptions);
    }
}
