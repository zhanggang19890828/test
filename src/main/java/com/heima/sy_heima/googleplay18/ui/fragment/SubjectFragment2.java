package com.heima.sy_heima.googleplay18.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.adapter.SubjectListAdapter;
import com.heima.sy_heima.googleplay18.bean.SubjectBean;
import com.heima.sy_heima.googleplay18.cachemanager.JsonCacheManager;
import com.heima.sy_heima.googleplay18.uimanager.Loadpager;
import com.heima.sy_heima.googleplay18.utils.Uris;
import com.heima.sy_heima.googleplay18.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class SubjectFragment2 extends Fragment {

    @Bind(R.id.lv_home_list)
    ListView mLvHomeList;
    private TextView mTextView;

    private List<SubjectBean> mShowItems = new ArrayList<>();
    private SubjectListAdapter mSubjectListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        Loadpager loadpager = new Loadpager(getContext()) {
            //数据
            @Override
            protected Object getNetData() {

                /*请求数据*/
           //使用缓存框架获取数据
                List<SubjectBean> subjectBeanList = JsonCacheManager.getInstance().getCacheList(Uris.SUBJECTURL, SubjectBean.class);

                //在获取数据前一定要进行数据判断
                if (subjectBeanList == null) {
                    return null;
                }

                mShowItems.addAll(subjectBeanList);


                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        mSubjectListAdapter.notifyDataSetChanged();
                    }
                });

                return mShowItems;
            }

            //这个成功的界面
            @Override
            public View createSuccessView() {
                View view = View.inflate(getContext(), R.layout.fragment_home, null);
                ButterKnife.bind(SubjectFragment2.this, view);

                initView();
                return view;

            }
        };


        return loadpager;
    }

    private void initView() {
        mSubjectListAdapter = new SubjectListAdapter(mShowItems);
        mLvHomeList.setAdapter(mSubjectListAdapter);
    }


}
