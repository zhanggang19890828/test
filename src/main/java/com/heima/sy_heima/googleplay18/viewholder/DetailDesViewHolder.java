package com.heima.sy_heima.googleplay18.viewholder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.DetailBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;

import butterknife.Bind;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class DetailDesViewHolder extends BaseViewholder<DetailBean> {
    @Bind(R.id.tv_app_desc)
    TextView     mTvAppDesc;
    @Bind(R.id.tv_app_desc_title)
    TextView     mTvAppDescTitle;
    @Bind(R.id.iv_desc_arrow)
    ImageView    mIvDescArrow;
    @Bind(R.id.ll_desc_root_layout)
    LinearLayout mLlDescRootLayout;
    private int mHeight_5;
    private int mHeight_max;

    //定义开关
    private boolean isOpen = false;

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.descview, null);
        return view;
    }

    @Override
    public void bindView(DetailBean item) {
        mTvAppDescTitle.setText(item.getName());
        mTvAppDesc.setText(item.getDes());

        //怎么显示五行
        mTvAppDesc.setMaxLines(5);

        mTvAppDesc.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTvAppDesc.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mHeight_5 = mTvAppDesc.getHeight();
                mTvAppDesc.setMaxLines(1000);
                mTvAppDesc.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mTvAppDesc.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        mHeight_max = mTvAppDesc.getHeight();

                        //设置5行的高度
                        mTvAppDesc.setHeight(mHeight_5);

                    }
                });
            }
        });

        //设置点击事件
        mLlDescRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = new ValueAnimator();
                if (isOpen) {
                    //关闭,最大到5行
                valueAnimator.setIntValues(mHeight_max,mHeight_5);
                } else {
                    //打开,5行到最大
                valueAnimator.setIntValues(mHeight_5,mHeight_max);
                }
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        mTvAppDesc.setHeight(animatedValue);

                        //mScrollView滚到底
                        mScrollView.smoothScrollTo(0,1000);
                    }
                });

                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mLlDescRootLayout.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLlDescRootLayout.setEnabled(true);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                valueAnimator.setDuration(500);
                valueAnimator.start();

                isOpen = !isOpen;

                ViewCompat.animate(mIvDescArrow).rotationBy(180).setDuration(500).start();
            }
        });



        /*mTvAppDesc.measure(0,0);
        final int measuredHeight5 = mTvAppDesc.getMeasuredHeight();

       // mTvAppDesc.setMaxLines(1000);

     *//*   mTvAppDesc.measure(0,0);
        int measuredHeightMax = mTvAppDesc.getMeasuredHeight();*//*

       // System.out.println("当前5行:"+measuredHeight5+"当前最大:"+measuredHeightMax);

        //得到高度还有一种方式,全局监听
        mTvAppDesc.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //去掉监听
                mTvAppDesc.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                //得到全部的高度
                int height = mTvAppDesc.getHeight();
                System.out.println("得到最大的高度:"+height);

                //现在得到最大的高度,现在要做的事情,就是进来以后还是显示5行的高度
                mTvAppDesc.setHeight(measuredHeight5);
            }
        });
*/
    }

    //得到scrollView
    private ScrollView mScrollView;
    public  void setScrollView(ScrollView scrollView) {
        this.mScrollView = scrollView;
    }
}
