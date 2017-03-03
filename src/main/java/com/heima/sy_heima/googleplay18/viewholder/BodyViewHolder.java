package com.heima.sy_heima.googleplay18.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.CatagoryBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;
import com.heima.sy_heima.googleplay18.utils.Uris;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class BodyViewHolder extends BaseViewholder<CatagoryBean.CatagoryItem> {
    @Bind(R.id.iv_image1)
    ImageView    mIvImage1;
    @Bind(R.id.tv_name1)
    TextView     mTvName1;
    @Bind(R.id.ll_info_info01)
    LinearLayout mLlInfoInfo01;
    @Bind(R.id.iv_image2)
    ImageView    mIvImage2;
    @Bind(R.id.tv_name2)
    TextView     mTvName2;
    @Bind(R.id.ll_info2)
    LinearLayout mLlInfo2;
    @Bind(R.id.iv_image3)
    ImageView    mIvImage3;
    @Bind(R.id.tv_name3)
    TextView     mTvName3;
    @Bind(R.id.ll_info3)
    LinearLayout mLlInfo3;
    @Bind(R.id.ll_info_root_layout)
    LinearLayout mLlInfoRootLayout;

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.adapter_info, null);
        return view;
    }

    @Override
    public void bindView(CatagoryBean.CatagoryItem item) {
        mLlInfoInfo01.setVisibility(View.INVISIBLE);
        mLlInfo2.setVisibility(View.INVISIBLE);
        mLlInfo3.setVisibility(View.INVISIBLE);

        if (!TextUtils.isEmpty(item.getName1())) {
            mTvName1.setText(item.getName1());
            ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + item.getUrl1(), mIvImage1, mOptions);
            mLlInfoInfo01.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(item.getName2())) {
            mTvName2.setText(item.getName2());
            ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + item.getUrl2(), mIvImage2, mOptions);
            mLlInfo2.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(item.getName3())) {
            mTvName3.setText(item.getName3());
            ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + item.getUrl3(), mIvImage3, mOptions);
            mLlInfo3.setVisibility(View.VISIBLE);
        }

    }

}
