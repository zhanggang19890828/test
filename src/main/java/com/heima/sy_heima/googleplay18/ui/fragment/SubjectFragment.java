package com.heima.sy_heima.googleplay18.ui.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.adapter.SubjectListAdapter;
import com.heima.sy_heima.googleplay18.bean.SubjectBean;
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

public class SubjectFragment extends BaseFragment {


    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView mPullRefreshList;
    private TextView mTextView;

    private List<SubjectBean> mShowItems = new ArrayList<>();
    private SubjectListAdapter mSubjectListAdapter;

    //给一个布局
    @Override
    protected View createView() {
        View view = View.inflate(getContext(), R.layout.activity_ptr_list, null);
        ButterKnife.bind(SubjectFragment.this, view);

        initView();
        return view;
    }

    //给个数据
    @Override
    public Object rquestURLData() {

        //得到当前的模式
        if (mPullRefreshList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
            mShowItems.clear();
        }

            /*请求数据*/
        //使用缓存框架获取数据
        List<SubjectBean> subjectBeanList = JsonCacheManager.getInstance().getCacheList(Uris.SUBJECTURL+mShowItems.size(), SubjectBean.class);

        //在获取数据前一定要进行数据判断

        //如果服务器没有数据真的返回一个null,判断当前的mshowItem的人数如果是0,返回的结果是0,那么返回一个错误的页面
        //如果mshowitems的个数大于0,当前的数据为0,提示用户没有更多数据了

        if (subjectBeanList == null) {
            return null;
        }

        mShowItems.addAll(subjectBeanList);


        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                mSubjectListAdapter.notifyDataSetChanged();

                //结束动画
                mPullRefreshList.onRefreshComplete();
            }
        });

        return mShowItems;
    }

    private void initView() {
        ListView listView = mPullRefreshList.getRefreshableView();
        //设置模式
        mPullRefreshList.setMode(PullToRefreshBase.Mode.BOTH);

        //设置监听
        mPullRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                mLoadpager.showPage();
            }
        });

        mSubjectListAdapter = new SubjectListAdapter(mShowItems);
        listView.setAdapter(mSubjectListAdapter);
    }

}
