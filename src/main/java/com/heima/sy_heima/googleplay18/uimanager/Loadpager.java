package com.heima.sy_heima.googleplay18.uimanager;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.utils.Utils;

import java.util.List;

/**
 * 作者: old样
 * 描述:根据网络数据自动切换ui的框架
 * 上海传智播客android黑马程序员
 */

public abstract class Loadpager extends FrameLayout {

    private View mLoadView;
    private View mErrorView;
    private View mSuccessView;

    public Loadpager(Context context) {
        this(context, null);
    }

    public Loadpager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Loadpager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        init();
    }

    public void init() {
        //把三个界面加入布局中
        //谁用谁传
        if (mLoadView == null) {
            mLoadView = View.inflate(getContext(), R.layout.page_loading, null);
        }
        if (mErrorView == null) {
            mErrorView = View.inflate(getContext(), R.layout.page_error, null);
            Button button = (Button) mErrorView.findViewById(R.id.btn_reload);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentState = LOADSTATE.LOADING;
                    changeView(mCurrentState);

                    showPage(500);
                }
            });
        }
        if (mSuccessView == null) {
            mSuccessView = createSuccessView();
            if (mSuccessView == null) {
                throw new RuntimeException("小白来个界面吧!");
            }
        }
        //加入到布局
        addView(mLoadView);
        addView(mErrorView);
        addView(mSuccessView);

        //切换页面
        changeView(mCurrentState);

        //根据网络数据自动切换ui
        showPage();
    }

    //根据网络数据自动切换
    public void showPage(final int time) {
        //请求数据我们需要开线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //判断数据,数据那里来,谁用谁传
                Object data = getNetData();
                SystemClock.sleep(time);
                //判断数据
                mCurrentState = checkData(data);
                //当前的状态,去切换页面,主线程
                Utils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        changeView(mCurrentState);
                    }
                });


                /*rxjava-->异步处理*/
                
            }
        }).start();



    }

    //默认方法
    public void showPage() {
        showPage(0);
    }

    //判断当前的数据

    /**
     * 判断当前的数据
     * @param data
     * @return
     */
    private LOADSTATE checkData(Object data) {
        //得到数据后如果是对象为空,那么失败的界面
        //如果得到的数据有,
            //1. 集合
                    //个数如果为0-->失败
                    //个数如果大于0-->成功
            //2. 对象--->成功

        if (data == null) {
            return LOADSTATE.ERROR;
        } else {
            if (data instanceof List) {
                List items = (List) data;
                if (items.size() > 0) {
                    return LOADSTATE.SUCCESS;
                } else {
                    return LOADSTATE.ERROR;
                }
            } else {
                return LOADSTATE.SUCCESS;
            }
        }

    }

    //谁用谁传,把数据传入
    protected abstract Object getNetData();

    //定义状态
    public enum LOADSTATE {
        LOADING, //加载中状态
        ERROR,  //错误状态
        SUCCESS //成功状态
    }

    private LOADSTATE mCurrentState = LOADSTATE.LOADING;//默认状态是加载

    /**
     * 根据当前的状态去切换当前的view
     */
    private void changeView(LOADSTATE currentState) {
        //先全部隐藏
        mLoadView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mSuccessView.setVisibility(GONE);
        //后根据状态显示对应的界面
        switch (currentState) {
            case LOADING:
                //加载 中
                mLoadView.setVisibility(VISIBLE);
                break;
            case ERROR:
                //加载 中
                mErrorView.setVisibility(VISIBLE);
                break;
            case SUCCESS:
                //加载 中

                mSuccessView.setVisibility(VISIBLE);
                break;

            default:
                break;

        }

    }

    //成功的界面传入
    public abstract View createSuccessView();
}
