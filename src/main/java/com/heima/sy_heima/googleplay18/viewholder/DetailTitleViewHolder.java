package com.heima.sy_heima.googleplay18.viewholder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.DetailBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;
import com.heima.sy_heima.googleplay18.utils.Uris;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class DetailTitleViewHolder extends BaseViewholder<DetailBean> {
    @Bind(R.id.iv_head_icon)
    ImageView mIvHeadIcon;
    @Bind(R.id.tv_head_title)
    TextView  mTvHeadTitle;
    @Bind(R.id.rb_head_score)
    RatingBar mRbHeadScore;
    @Bind(R.id.tv_head_down)
    TextView  mTvHeadDown;
    @Bind(R.id.tv_head_date)
    TextView  mTvHeadDate;
    @Bind(R.id.app_head_version)
    TextView  mAppHeadVersion;
    @Bind(R.id.app_head_size)
    TextView  mAppHeadSize;

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.headview, null);
        return view;
    }

    @Override
    public void bindView(DetailBean item) {
        mTvHeadTitle.setText(item.getName());
        mTvHeadDown.setText("下载:"+item.getDownloadNum());
        mTvHeadDate.setText("日期:"+item.getDate());
        mAppHeadVersion.setText("版本:"+item.getVersion());

        String size = Formatter.formatFileSize(GooglePlay.context, item.getSize());
        mAppHeadSize.setText("大小:"+size);

        mRbHeadScore.setRating(item.getStars());

        ImageLoader.getInstance().displayImage(Uris.IMAGEHOST+item.getIconUrl(),mIvHeadIcon,mOptions);


    }
}
