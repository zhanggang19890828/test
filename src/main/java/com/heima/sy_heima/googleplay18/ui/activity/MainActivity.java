package com.heima.sy_heima.googleplay18.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.adapter.MainUIAdapter;
import com.heima.sy_heima.googleplay18.bean.PageInfo;
import com.heima.sy_heima.googleplay18.ui.fragment.CatagoryFragment;
import com.heima.sy_heima.googleplay18.ui.fragment.HomeFragment;
import com.heima.sy_heima.googleplay18.ui.fragment.HotFragment;
import com.heima.sy_heima.googleplay18.ui.fragment.RecommendFragment;
import com.heima.sy_heima.googleplay18.ui.fragment.SubjectFragment;
import com.heima.sy_heima.googleplay18.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_root)
    DrawerLayout mActivityMainRoot;
    @Bind(R.id.tab_main_title_layout)
    TabLayout    mTabMainTitleLayout;
    @Bind(R.id.vp_main_show_layout)
    ViewPager    mVpMainShowLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private List<PageInfo> mShowItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initactionbar();

        //初始化方法
        init();

       // ViewCompat.animate(mActivityMainRoot).rotation(180).setDuration(1000).start();


    }

    //初始化
    private void init() {

        //集合初始化
/*        for (int i = 0; i < 5; i++) {
     *//*       PageInfo pageInfo = new PageInfo();
            pageInfo.title = "美女" + i;
            pageInfo.fragment = new HomeFragment();
            mShowItems.add(pageInfo);*//*

            mShowItems.add(new PageInfo("大美女"+i,new HomeFragment()));

        }*/

        String[] titles = Utils.getStringArray(R.array.tab_names);
        mShowItems.add(new PageInfo(titles[0],new HomeFragment()));
        mShowItems.add(new PageInfo(titles[1],new SubjectFragment()));
        mShowItems.add(new PageInfo(titles[2],new RecommendFragment()));
        mShowItems.add(new PageInfo(titles[3],new CatagoryFragment()));
        mShowItems.add(new PageInfo(titles[4],new HotFragment()));

        //初始化控件
        //viewpager设置adapter
        mVpMainShowLayout.setAdapter(new MainUIAdapter(getSupportFragmentManager(),mShowItems));

        //页签绑定viewpager
        mTabMainTitleLayout.setupWithViewPager(mVpMainShowLayout);

        //设置颜色
        //设置文件颜色,选中和非选中状态
         int normalColor = Color.parseColor("#8C8C8C");
         int selectColor = Color.parseColor("#3F51B5");
        mTabMainTitleLayout.setTabTextColors(normalColor,selectColor);

        //指示条颜色
        mTabMainTitleLayout.setSelectedTabIndicatorColor(selectColor);



    }

    //这里的代码不需要记忆
    //初始化actionbar
    private void initactionbar() {
        //箭头home,显示箭头
        ActionBar supportActionBar = getSupportActionBar();

        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);

        supportActionBar.setTitle("黑马18期");

        //actionbar开关
        //alt+shift+F12全屏
        //alt+1//目录列表
        mDrawerToggle = new ActionBarDrawerToggle(this, mActivityMainRoot, R.string.open, R.string.close);
        //同步
        mDrawerToggle.syncState();
        //设置监听
        mActivityMainRoot.addDrawerListener(mDrawerToggle);

    }


    //actionbar点击按钮
    //记住单词选择select
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerToggle.onOptionsItemSelected(item);

                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
