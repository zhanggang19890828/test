package com.heima.sy_heima.googleplay18.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.cachemanager.JsonCacheManager;
import com.heima.sy_heima.googleplay18.ui.views.randomlayout.StellarMap;
import com.heima.sy_heima.googleplay18.utils.Utils;

import java.util.List;
import java.util.Random;

/**
 * 作者: old样
 * 描述:关键字飞入飞出飞窗效果
 *  类似于百度竞价排名
 * 上海传智播客android黑马程序员
 */

public class RecommendFragment extends BaseFragment {

    private StellarMap mStellarMap;

    @Override
    protected View createView() {
        //1.初始化布局
        mStellarMap = new StellarMap(getContext());


        return mStellarMap;
    }

    //一个随机的颜色
    private int randomTextColor() {
        return Color.rgb(myRandomColor(),myRandomColor(),myRandomColor());
    }

    //颜色的范围
    //0-255
    //但是颜色需要深一点
    //0-180
    private int myRandomColor() {
        Random random = new Random();
        return random.nextInt(180);

    }

    //创建一随机的字体大小
    //14-24
    private int showTextSize() {
        Random random = new Random();
        return random.nextInt(10)+14;

    }

    @Override
    public Object rquestURLData() {

        final List<String> recommendLists = JsonCacheManager.getInstance().getCacheList("http://127.0.0.1:8090/recommend?index=0", String.class);
        if (recommendLists == null || recommendLists.size() == 0) {
            return null;
        }

        //更新界面
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                //设置数据
                mStellarMap.setAdapter(new StellarMap.Adapter() {

                    //组数
                    @Override
                    public int getGroupCount() {
                        return 3;
                    }

                    //显示每一组个数
                    @Override
                    public int getCount(int group) {
                        return 11;
                    }

                    //显示一个view
                    @Override
                    public View getView(int group, int position, View convertView) {

                        //真实的坐标
                        int index = group * getCount(group) + position;

                        TextView textView = new TextView(getContext());
                        textView.setText(recommendLists.get(index));

                        //设置文字 大小
                        textView.setTextSize(showTextSize());

                        //设置文字颜色
                        textView.setTextColor(randomTextColor());

                        return textView;
                    }

                    //千年用不到
                    @Override
                    public int getNextGroupOnPan(int group, float degree) {
                        return 0;
                    }

                    //页面切换
                    @Override
                    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
                        //0-->1-->2-->0

                        return (group+1)%getGroupCount();
                    }
                });

                //设置第一页显示
                mStellarMap.setGroup(0,true);

                //设置显示数据个数
                //相当于棋盘的线
                mStellarMap.setRegularity(11,11);

                //离我们有一定的边距
                int dp20 = Utils.getDimens(R.dimen.dp20);
                mStellarMap.setInnerPadding(dp20,dp20,dp20,dp20);
            }
        });


        return recommendLists;
    }
}
