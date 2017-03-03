package com.heima.sy_heima.googleplay18.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.cachemanager.JsonCacheManager;
import com.heima.sy_heima.googleplay18.ui.views.FlowLayout;
import com.heima.sy_heima.googleplay18.utils.ToastUtil;
import com.heima.sy_heima.googleplay18.utils.Utils;

import java.util.List;


/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class HotFragment extends BaseFragment implements View.OnClickListener {

    private FlowLayout mFlowLayout;

    //返回一个view
    @Override
    protected View createView() {
        ScrollView scrollView = new ScrollView(getContext());

        //就是一个流式的布局
        mFlowLayout = new FlowLayout(getContext());


        //设置padding值
        int dp10 = Utils.getDimens(R.dimen.dp10);
        mFlowLayout.setPadding(dp10, dp10, dp10, dp10);

        //滚动
        scrollView.addView(mFlowLayout);


        return scrollView;
    }

    @NonNull
    private GradientDrawable getGradientDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        //设置形状
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        //设置颜色
        gradientDrawable.setColor(Utils.randomColor());
        //设置圆角
        gradientDrawable.setCornerRadius(Utils.getDimens(R.dimen.dp5));
        return gradientDrawable;
    }

    //返回一个数据
    @Override
    public Object rquestURLData() {
        final List<String> hots = JsonCacheManager.getInstance().getCacheList("http://127.0.0.1:8090/hot?index=0", String.class);
        if (hots == null) {
            return null;
        }

        //更新界面
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < hots.size(); i++) {
                    TextView textView = new TextView(getContext());
                    textView.setText(hots.get(i));

                    //添加背景
                    //textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.hotbg));

     /*       ShapeDrawable shapeDrawable = new ShapeDrawable();
            //shapeDrawable.setShape(ShapeDrawable.);
            shapeDrawable.setco*/
                    //G
                    //生成一张图片
  /*          GradientDrawable gradientDrawable = getGradientDrawable();

            //设置背景
            textView.setBackgroundDrawable(gradientDrawable);*/

                    //文字剧中
                    textView.setGravity(Gravity.CENTER);
                    //设置边距
                    int dp5 = Utils.getDimens(R.dimen.dp5);
                    textView.setPadding(dp5, dp5, dp5, dp5);

                    //设置文字大小及颜色
                    textView.setTextSize(16);
                    textView.setTextColor(Color.WHITE);

                    //设置textView状态选择器
                    StateListDrawable stateListDrawable = new StateListDrawable();
                    //第一个参数就是状态,第二个参数是图片
                    //按下状态
                    stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, getGradientDrawable());
                    //默认状态
                    stateListDrawable.addState(new int[]{}, getGradientDrawable());

                    //设置效果
                    stateListDrawable.setEnterFadeDuration(1000);
                    stateListDrawable.setExitFadeDuration(1000);

                    //设置背景
                    textView.setBackgroundDrawable(stateListDrawable);

                    //设置点击事件
                    textView.setOnClickListener(HotFragment.this);


                    mFlowLayout.addView(textView);

                }
            }
        });
        return hots;
    }

    //当前的v代表是当前点击的对象
    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            TextView textView = (TextView) v;
            ToastUtil.showToast(textView.getText().toString());
        }

    }
}
