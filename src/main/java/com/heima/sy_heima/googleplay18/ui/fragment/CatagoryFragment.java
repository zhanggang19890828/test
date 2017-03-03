package com.heima.sy_heima.googleplay18.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.adapter.CatagoryAdapter;
import com.heima.sy_heima.googleplay18.bean.CatagoryBean;
import com.heima.sy_heima.googleplay18.cachemanager.JsonCacheManager;
import com.heima.sy_heima.googleplay18.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者: old样
 * 描述:分类界面
 * 上海传智播客android黑马程序员
 */

public class CatagoryFragment extends BaseFragment {
    @Bind(R.id.lv_home_list)
    ListView mLvHomeList;

    private List<Object> mShowItems = new ArrayList<>();
    private CatagoryAdapter mCatagoryAdapter;

    @Override
    protected View createView() {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
       //传入数据
 /*       mShowItems.add("标题");
        for (int i = 0; i < 5; i++) {
            mShowItems.add(new Object());
        }
        mShowItems.add("标题");
        for (int i = 0; i < 5; i++) {
            mShowItems.add(new Object());
        }
*/


        mCatagoryAdapter = new CatagoryAdapter(mShowItems);
        mLvHomeList.setAdapter(mCatagoryAdapter);
    }

    @Override
    public Object rquestURLData() {

        List<CatagoryBean> catagoryBeanList = JsonCacheManager.getInstance().getCacheList("http://127.0.0.1:8090/category?index=0", CatagoryBean.class);

        //进行数据的判断
        if (catagoryBeanList == null) {
            return null;
        }

        //这里直接拿到的数据不能用,因为tiltle跟内容在一起类里面,把数据进行拆分
        for (int i = 0; i < catagoryBeanList.size(); i++) {
            //添加标题
            mShowItems.add(catagoryBeanList.get(i).getTitle());
            //添加内容
            mShowItems.addAll(catagoryBeanList.get(i).getInfos());
        }

        //更新集合
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                mCatagoryAdapter.notifyDataSetChanged();//更新集合
            }
        });



        return mShowItems;
    }


}
