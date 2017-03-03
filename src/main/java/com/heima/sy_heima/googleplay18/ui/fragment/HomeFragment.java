package com.heima.sy_heima.googleplay18.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.adapter.FinalAdapter;
import com.heima.sy_heima.googleplay18.bean.HomeBean;
import com.heima.sy_heima.googleplay18.cachemanager.JsonCacheManager;
import com.heima.sy_heima.googleplay18.utils.ToastUtil;
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

public class HomeFragment extends BaseFragment implements FinalAdapter.AdapterListener<HomeBean.HomeItem> {


    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView mPullRefreshList;
    private List<HomeBean.HomeItem> mShowItems = new ArrayList<>();
    private FinalAdapter<HomeBean.HomeItem> mHomeListAdapter;

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
        View view = View.inflate(getContext(), R.layout.activity_ptr_list, null);
        //绑定控件
        //这个的参数必须写两个第一个是当前的控件的所在位置,第二个参数是当前要找到控件的view
        ButterKnife.bind(HomeFragment.this, view);

        //初始化
        initView();

        return view;
    }

    @Override
    public Object rquestURLData() {
        System.out.println("当前请求了数据:");

        //得到当前的模式
        PullToRefreshBase.Mode currentMode = mPullRefreshList.getCurrentMode();

        //如果模式是下拉的清空数据
        if (currentMode == PullToRefreshBase.Mode.PULL_FROM_START) {
            //mShowItems = new ArrayList<>();//这里要注意了,如果你这样操作是没有效果的,因为创建两个不同的对象

            mShowItems.clear();//代表同一个对象

        }

        //ctrl+alt+V:生成变量
        final HomeBean homeBean = JsonCacheManager.getInstance().getCacheBean(Uris.HOMEURL+mShowItems.size(), HomeBean.class);


        if (homeBean == null && mShowItems.size() == 0) {
            return null;
        } else {
            if (homeBean == null) {
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast("没有缓存数据了");
                    }
                });
            } else {
                mShowItems.addAll(homeBean.getList());
            }
        }


        //2. 把数据展示到控件上传
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                //得到数据后去更新界面



                System.out.println("当前集合数据:" + mShowItems.size());
                //请求结束后下拉动画结束
                mPullRefreshList.onRefreshComplete();//去猜一下

                ///更新界面
                mHomeListAdapter.notifyDataSetChanged();

            }
        });
        return mShowItems;
    }

    //创建我们控件初始化
    private void initView() {

        //下拉刷新控件中已经包含了listView
        ListView listView = mPullRefreshList.getRefreshableView();

        //设置模式
        mPullRefreshList.setMode(PullToRefreshBase.Mode.BOTH);

        //设置 监听
        mPullRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                //使用框架去请求数据
                mLoadpager.showPage();
            }
        });
        mHomeListAdapter = new FinalAdapter<>(mShowItems,R.layout.item_home,this);
        listView.setAdapter(mHomeListAdapter);



        //设置条目点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Bundle bundle = new Bundle();
                bundle.putString("pageName",mShowItems.get(position-1).getPackageName());
                startFragment(DetailFragment.class, bundle);

            }
        });
    }

    @Override
    public void bindView(FinalAdapter.FinalViewHolder finalViewHolder, HomeBean.HomeItem content) {
        //设置一个标题
       //TextView title = (TextView) finalViewHolder.getViewById(R.id.tv_home_title);
        TextView title = finalViewHolder.getTextView(R.id.tv_home_title);
        title.setText(content.getName());
    }

/*    //开启fragment的方法
    private void startFragment(Class<? extends BaseFragment> clss,Bundle bundle) {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Filds.showActivity.CLASSNAME, clss);
        intent.putExtra(Filds.showActivity.BUNDLE, bundle);
        startActivity(intent);
    }*/


}
