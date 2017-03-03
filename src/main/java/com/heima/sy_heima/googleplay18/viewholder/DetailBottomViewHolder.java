package com.heima.sy_heima.googleplay18.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.bean.DetailBean;
import com.heima.sy_heima.googleplay18.down.DowmManager;
import com.heima.sy_heima.googleplay18.down.DownInfo;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者: old样
 * 描述:底部按钮
 * 上海传智播客android黑马程序员
 */

public class DetailBottomViewHolder extends BaseViewholder<DetailBean> implements DowmManager.DownListener {


    @Bind(R.id.pb_bottom_progress)
    ProgressBar mPbBottomProgress;
    @Bind(R.id.bt_bottom_down)
    Button      mBtBottomDown;
    private DetailBean mDetailBean;

    @Override
    public View createItemView() {
        View view = View.inflate(GooglePlay.context, R.layout.bottomview, null);
        return view;
    }

    @Override
    public void bindView(DetailBean item) {
        this.mDetailBean = item;

        //一进来显示正确数据
        DownInfo downinfo = DowmManager.getInstance().getDowninfo(item);
        if (downinfo != null) {
        mBtBottomDown.setText(getStateText(downinfo.downstate));

        }
    }

    @OnClick({R.id.bt_bottom_down, R.id.bt_bottomview_pause, R.id.bt_bottom_install})
    public void onClick(View view) {

   /*     if (view.getId() == R.id.bt_bottomview_pause) {
            DowmManager.getInstance().pause(mDetailBean);
        } else {
            DowmManager.getInstance().down(mDetailBean);
        }*/

          /* switch (view.getId()) {
                       case R.id.bt_bottom_down:
                           DowmManager.getInstance().down(mDetailBean);

                           break;
               case R.id.bt_bottomview_pause:
                   DowmManager.getInstance().pause(mDetailBean);
                   break;
               case R.id.bt_bottom_install:


                   DowmManager.getInstance().installAPK(mDetailBean);
                   break;
                       default:
                           break;

                   }
*/

        //添加监听
        DowmManager.getInstance().addDownListener(this);

        //我们是不是应该按钮不同状态去调用不同的方法
        //调用下载方法
        DownInfo downinfo = DowmManager.getInstance().getDowninfo(mDetailBean);
        if (downinfo == null) {
            downinfo = new DownInfo(mDetailBean);
        }

        //调用下载,空闲,暂停,出错
        if (downinfo.downstate == DowmManager.DOWMSTATE.NONE || downinfo.downstate == DowmManager.DOWMSTATE.PAUSE || downinfo.downstate == DowmManager.DOWMSTATE.ERROR) {
            //调用下载
            DowmManager.getInstance().down(mDetailBean);
        } else {
            //下载中,成功,等待
            //下载中跟等待是暂停
            //成功是安装
            if (downinfo.downstate == DowmManager.DOWMSTATE.SUCCESS) {
                DowmManager.getInstance().installAPK(mDetailBean);
            } else {
                DowmManager.getInstance().pause(mDetailBean);
            }
        }

    }

    @Override
    public void publishProgress(DownInfo downInfo) {
        System.out.println("当前的进度:" + downInfo.progress);
        if (downInfo.id == mDetailBean.getId()) {
            mPbBottomProgress.setProgress((int) (downInfo.progress * 100 / downInfo.size));
            mBtBottomDown.setBackgroundResource(0);//背景透明
        }

    }

    @Override
    public void publishState(DownInfo downInfo) {
        System.out.println("当前的状态:" + downInfo.downstate);
        if (downInfo.id == mDetailBean.getId()) {
            mBtBottomDown.setText(getStateText(downInfo.downstate));
        }


    }

    //根据状态产生不同的文字
    private String getStateText(DowmManager.DOWMSTATE downstate) {
        StringBuffer stringBuffer = new StringBuffer();
        switch (downstate) {
            case NONE:

                stringBuffer.append("空闲");
                break;
            case DOWNING:

                stringBuffer.append("下载");
                break;
            case PAUSE:

                stringBuffer.append("暂停");
                break;
            case ERROR:

                stringBuffer.append("出错");
                break;
            case SUCCESS:

                stringBuffer.append("成功");
                break;
            case WAIT:

                stringBuffer.append("等待");
                break;

            default:
                break;

        }
        return stringBuffer.toString();
    }


}
