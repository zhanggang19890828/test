package com.heima.sy_heima.googleplay18.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.adapter.HomeListAdapter;
import com.heima.sy_heima.googleplay18.bean.HomeBean;
import com.heima.sy_heima.googleplay18.cachemanager.JsonCacheManager;
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

public class HomeFragment2 extends BaseFragment {


    @Bind(R.id.lv_home_list)
    ListView mLvHomeList;

    private List<HomeBean.HomeItem> mShowItems = new ArrayList<>();
    private HomeListAdapter mHomeListAdapter;

 /*   @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Loadpager loadpager = new Loadpager(getContext()) {
            //数据
            //1. 请求到数据

            //2. 判断数据是否为空

            //3. 如果为空去缓存拿数据
            @Override
            protected Object getNetData() {

                //ctrl+alt+V:生成变量
                final HomeBean homeBean = JsonCacheManager.getInstance().getCacheBean(Uris.HOMEURL, HomeBean.class);

                if (homeBean == null) {
                    return null;
                }


                //2. 把数据展示到控件上传
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        //得到数据后去更新界面
                        mShowItems.addAll(homeBean.getList());

                        System.out.println("当前集合数据:"+mShowItems.size());
                        ///更新界面
                        mHomeListAdapter.notifyDataSetChanged();

                    }
                });
                return homeBean;
            }

            //这个成功的界面
            @Override
            public View createSuccessView() {

                View view = View.inflate(getContext(), R.layout.fragment_home, null);
                //绑定控件
                //这个的参数必须写两个第一个是当前的控件的所在位置,第二个参数是当前要找到控件的view
                ButterKnife.bind(HomeFragment.this, view);

                //初始化
                initView();

                return view;
            }
        };

        return loadpager;
    }*/

    @Override
    protected View createView() {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        //绑定控件
        //这个的参数必须写两个第一个是当前的控件的所在位置,第二个参数是当前要找到控件的view
        ButterKnife.bind(HomeFragment2.this, view);

        //初始化
        initView();

        return view;
    }

    @Override
    public Object rquestURLData() {
        //ctrl+alt+V:生成变量
        final HomeBean homeBean = JsonCacheManager.getInstance().getCacheBean(Uris.HOMEURL, HomeBean.class);

        if (homeBean == null) {
            return null;
        }


        //2. 把数据展示到控件上传
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                //得到数据后去更新界面
                mShowItems.addAll(homeBean.getList());

                System.out.println("当前集合数据:"+mShowItems.size());
                ///更新界面
                mHomeListAdapter.notifyDataSetChanged();

            }
        });
        return homeBean;
    }

    //创建我们控件初始化
    private void initView() {
        mHomeListAdapter = new HomeListAdapter(mShowItems);
        mLvHomeList.setAdapter(mHomeListAdapter);
    }

}
