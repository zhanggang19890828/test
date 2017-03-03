package com.heima.sy_heima.googleplay18.viewholder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.DetailBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;
import com.heima.sy_heima.googleplay18.utils.Uris;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class DetailSafeViewHolder extends BaseViewholder<DetailBean> {
    @Bind(R.id.iv_safe_title_01)
    ImageView    mIvSafeTitle01;
    @Bind(R.id.iv_safe_title02)
    ImageView    mIvSafeTitle02;
    @Bind(R.id.iv_safe_title03)
    ImageView    mIvSafeTitle03;
    @Bind(R.id.iv_safe_arrow)
    ImageView    mIvSafeArrow;
    @Bind(R.id.iv_safe_content01)
    ImageView    mIvSafeContent01;
    @Bind(R.id.tv_safe_content01)
    TextView     mTvSafeContent01;
    @Bind(R.id.ll_safe_01)
    LinearLayout mLlSafe01;
    @Bind(R.id.iv_safe_content02)
    ImageView    mIvSafeContent02;
    @Bind(R.id.tv_safe_content02)
    TextView     mTvSafeContent02;
    @Bind(R.id.ll_safe_02)
    LinearLayout mLlSafe02;
    @Bind(R.id.iv_safe_content03)
    ImageView    mIvSafeContent03;
    @Bind(R.id.tv_safe_content03)
    TextView     mTvSafeContent03;
    @Bind(R.id.ll_safe_03)
    LinearLayout mLlSafe03;
    @Bind(R.id.ll_app_safe_content_layout)
    LinearLayout mLlAppSafeContentLayout;
    @Bind(R.id.ll_safe_root_layout)
    LinearLayout mLlSafeRootLayout;

    //定义当前开关值
    private boolean isOpen = false;//默认是关闭

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.safeview, null);
        return view;
    }

    @Override
    public void bindView(DetailBean item) {

        List<DetailBean.SafeBean> itemSafes = item.getSafe();

        //排除我们集合为空
        if (itemSafes == null || itemSafes.size() == 0) {
            //整个隐藏
            mLlSafeRootLayout.setVisibility(View.GONE);
        } else {
            //有数据有几个,显示几个
            mLlAppSafeContentLayout.setVisibility(View.VISIBLE);

            switch (itemSafes.size()) {
                case 3:
                    mTvSafeContent03.setText(itemSafes.get(2).getSafeDes());
                    ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + itemSafes.get(2).getSafeUrl(), mIvSafeTitle03, mOptions);
                    ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + itemSafes.get(2).getSafeDesUrl(), mIvSafeContent03, mOptions);
                case 2:
                    mTvSafeContent02.setText(itemSafes.get(1).getSafeDes());
                    ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + itemSafes.get(1).getSafeUrl(), mIvSafeTitle02, mOptions);
                    ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + itemSafes.get(1).getSafeDesUrl(), mIvSafeContent02, mOptions);
                case 1:
                    mTvSafeContent01.setText(itemSafes.get(0).getSafeDes());
                    ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + itemSafes.get(0).getSafeUrl(), mIvSafeTitle01, mOptions);
                    ImageLoader.getInstance().displayImage(Uris.IMAGEHOST + itemSafes.get(0).getSafeDesUrl(), mIvSafeContent01, mOptions);

                    break;
            }

        }

        //设置高度
        int top = mLlAppSafeContentLayout.getHeight();

        //有几种得到高度的方法
        System.out.println("当前的高度1:"+mLlAppSafeContentLayout.getHeight());

        mLlAppSafeContentLayout.measure(0,0);
        final int measuredHeight = mLlAppSafeContentLayout.getMeasuredHeight();
        System.out.println("当前的高度2:"+measuredHeight);

        mLlAppSafeContentLayout.setPadding(0,-measuredHeight,0,0);

        //设置点击事件
        mLlSafeRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = new ValueAnimator();

                if (isOpen) {
                    //如果当前是打开,那么关闭,0到-控件高度
                    valueAnimator.setIntValues(0,-measuredHeight);
                } else {
                    //如果当前是关闭,那么打开,-控件高度到0
                    valueAnimator.setIntValues(-measuredHeight,0);
                }


                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    //改变的值
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                            int top = (int) animation.getAnimatedValue();

                            mLlAppSafeContentLayout.setPadding(0,top,0,0);
                    }
                });

                //设置动画监听
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mLlSafeRootLayout.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLlSafeRootLayout.setEnabled(true);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                //时长
                valueAnimator.setDuration(500);

                //开启
                valueAnimator.start();

                //更改状态
                isOpen = !isOpen;

                //在原先的基础再转180
                if (isOpen) {
                    ViewCompat.animate(mIvSafeArrow).rotation(180).setDuration(500).start();
                } else {
                    ViewCompat.animate(mIvSafeArrow).rotation(0).setDuration(500).start();
                }


            }
        });



    }
}
