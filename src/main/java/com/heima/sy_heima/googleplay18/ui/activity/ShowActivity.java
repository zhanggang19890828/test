package com.heima.sy_heima.googleplay18.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.utils.Filds;

/**
 * 作者: old样
 * 描述:用fragment进行展示,一个activity展示所有的fragment
 * 上海传智播客android黑马程序员
 */

public class ShowActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_activity);

        init();

    }

    private void init() {

        try {
/*            Class clss = DetailFragment.class;
            Fragment fragment = (Fragment) clss.newInstance();

            getSupportFragmentManager().beginTransaction().replace(R.id.fl_show_content_layout, new DetailFragment()).commit();*/

            //在程序中魔法数据都有常量进行替换

            Class clss = (Class) getIntent().getSerializableExtra(Filds.showActivity.CLASSNAME);

            Fragment fragment = (Fragment) clss.newInstance();

            // 拿到了数据
            Bundle bundle = getIntent().getBundleExtra(Filds.showActivity.BUNDLE);
            //发送数据
            fragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction().replace(R.id.fl_show_content_layout,fragment ).commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
