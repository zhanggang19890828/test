package com.heima.sy_heima.googleplay18.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heima.sy_heima.googleplay18.ui.activity.ShowActivity;
import com.heima.sy_heima.googleplay18.uimanager.Loadpager;
import com.heima.sy_heima.googleplay18.utils.Filds;

/**
 * 作者: old样
 * 描述:解决viewpager切换页面的时候fragment数据又重新加载了
 * 上海传智播客android黑马程序员
 */

public  abstract class BaseFragment extends Fragment {

    public Loadpager mLoadpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLoadpager == null) {
            mLoadpager = new Loadpager(container.getContext()) {
                @Override
                protected Object getNetData() {
                    //数据子类请求
                    return rquestURLData();
                }

                @Override
                public View createSuccessView() {

                    return createView();
                }
            };
        }

        return mLoadpager;
    }

    //子类创建一个view
    protected abstract View createView();

    //请求网络数据
    public abstract Object rquestURLData();

    //开启fragment的方法
    public void startFragment(Class<? extends BaseFragment> clss,Bundle bundle) {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Filds.showActivity.CLASSNAME, clss);
        intent.putExtra(Filds.showActivity.BUNDLE, bundle);
        startActivity(intent);
    }


}
