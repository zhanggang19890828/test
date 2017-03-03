package com.heima.sy_heima.googleplay18.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.DetailBean;
import com.heima.sy_heima.googleplay18.cachemanager.JsonCacheManager;
import com.heima.sy_heima.googleplay18.utils.Utils;
import com.heima.sy_heima.googleplay18.viewholder.DetailBottomViewHolder;
import com.heima.sy_heima.googleplay18.viewholder.DetailDesViewHolder;
import com.heima.sy_heima.googleplay18.viewholder.DetailSafeViewHolder;
import com.heima.sy_heima.googleplay18.viewholder.DetailTitleViewHolder;
import com.heima.sy_heima.googleplay18.viewholder.ShowImageViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者: old样
 * 描述:界面展示最主要的功能是展示一个成功的界面
 * 那我们可不可以把成功的界面通过addView的方式加载 进来
 * 上海传智播客android黑马程序员
 */

public class DetailFragment extends BaseFragment {
    @Bind(R.id.ll_detail_show_layout)
    LinearLayout mLlDetailShowLayout;
    @Bind(R.id.sv_detail_scrol_layout)
    ScrollView   mSvDetailScrolLayout;
    @Bind(R.id.ll_detail_root_layout)
    LinearLayout mLlDetailRootLayout;
    private DetailTitleViewHolder mDetailTitleViewHolder;
    private DetailSafeViewHolder  mDetailSafeViewHolder;
    private ShowImageViewHolder   mShowImageViewHolder;
    private DetailDesViewHolder   mDetailDesViewHolder;
    private DetailBottomViewHolder mDetailBottomViewHolder;
    private String mPageName;

    @Override
    protected View createView() {
        //获取数据
        View view = View.inflate(getContext(), R.layout.activity_detail, null);
        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        mPageName = arguments.getString("pageName");
        init();
        return view;
    }

    //加载成功的界面
    private void init() {
        //使用viewholder去包装代码片段,把成功的view进行返回
        mDetailTitleViewHolder = new DetailTitleViewHolder();

        mLlDetailShowLayout.addView(mDetailTitleViewHolder.getView());

        //加入安全布局
        mDetailSafeViewHolder = new DetailSafeViewHolder();
        mLlDetailShowLayout.addView(mDetailSafeViewHolder.getView());


        //加入展示图片的布局
        mShowImageViewHolder = new ShowImageViewHolder();
        mLlDetailShowLayout.addView(mShowImageViewHolder.getView());

        //加入备注的布局
        mDetailDesViewHolder = new DetailDesViewHolder();
        mDetailDesViewHolder.setScrollView(mSvDetailScrolLayout);
        mLlDetailShowLayout.addView(mDetailDesViewHolder.getView());

        //加入底部布局
        mDetailBottomViewHolder = new DetailBottomViewHolder();
        mLlDetailRootLayout.addView(mDetailBottomViewHolder.getView());
    }

    @Override
    public Object rquestURLData() {
        //获取数据
        final DetailBean detailBean = JsonCacheManager.getInstance().getCacheBean("http://127.0.0.1:8090/detail?packageName="+mPageName, DetailBean.class);
        if (detailBean == null) {
            return null;
        }

        //更新数据
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                mDetailTitleViewHolder.bindView(detailBean);
                mDetailSafeViewHolder.bindView(detailBean);
                mShowImageViewHolder.bindView(detailBean);
                mDetailDesViewHolder.bindView(detailBean);

                mDetailBottomViewHolder.bindView(detailBean);

            }
        });

        return detailBean;
    }




}
