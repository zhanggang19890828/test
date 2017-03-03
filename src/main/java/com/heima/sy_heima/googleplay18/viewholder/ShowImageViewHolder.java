package com.heima.sy_heima.googleplay18.viewholder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.DetailBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;
import com.heima.sy_heima.googleplay18.ui.activity.ShowActivity;
import com.heima.sy_heima.googleplay18.ui.fragment.ShowImageFragment;
import com.heima.sy_heima.googleplay18.utils.Filds;
import com.heima.sy_heima.googleplay18.utils.Uris;
import com.heima.sy_heima.googleplay18.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class ShowImageViewHolder extends BaseViewholder<DetailBean> {
    @Bind(R.id.ll_app_show_layout)
    LinearLayout mLlAppShowLayout;

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.showimages, null);
        return view;
    }

    @Override
    public void bindView(DetailBean item) {

        final ArrayList<String> screen = (ArrayList<String>) item.getScreen();
        for (int i = 0; i < screen.size(); i++) {
          /*  <ImageView
            android:layout_width="90dp"
            android:layout_height="150dp"
            android:layout_marginLeft="10dp"
            android:background="@drawable/h12"/>*/

            ImageView imageView = new ImageView(GooglePlay.context);
            int width = Utils.getDimens(R.dimen.dp90);
            int heigth = Utils.getDimens(R.dimen.dp150);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,heigth);

            params.setMargins(Utils.getDimens(R.dimen.dp10),0,0,0);
            imageView.setLayoutParams(params);

            //进行图片展示
            ImageLoader.getInstance().displayImage(Uris.IMAGEHOST+screen.get(i),imageView,mOptions);

            //设置点击事件
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GooglePlay.context, ShowActivity.class);
                    intent.putExtra(Filds.showActivity.CLASSNAME, ShowImageFragment.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("images",screen);
                    bundle.putInt("position", finalI);
                    intent.putExtra(Filds.showActivity.BUNDLE, bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    GooglePlay.context.startActivity(intent);

                }
            });

            mLlAppShowLayout.addView(imageView);
        }

    }
}
