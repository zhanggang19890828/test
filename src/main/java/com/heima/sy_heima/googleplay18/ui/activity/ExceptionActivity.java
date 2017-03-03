package com.heima.sy_heima.googleplay18.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.heima.sy_heima.googleplay18.R;
import com.heima.sy_heima.googleplay18.exceptions.ExceptionA;
import com.heima.sy_heima.googleplay18.exceptions.ExceptionB;
import com.heima.sy_heima.googleplay18.exceptions.ExceptionC;
import com.heima.sy_heima.googleplay18.exceptions.ExceptionD;
import com.heima.sy_heima.googleplay18.exceptions.ExceptionManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class ExceptionActivity extends AppCompatActivity {
    @Bind(R.id.bt_exception_a)
    Button mBtExceptionA;
    @Bind(R.id.bt_exception_B)
    Button mBtExceptionB;
    @Bind(R.id.bt_exceptionC)
    Button mBtExceptionC;
    @Bind(R.id.bt_exception_D)
    Button mBtExceptionD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exception);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_exception_a, R.id.bt_exception_B, R.id.bt_exceptionC, R.id.bt_exception_D})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_exception_a:
                try {
                    throw new ExceptionA();
                } catch (Exception exceptionA) {
                    ExceptionManager.getInstance().showException(exceptionA);
                    exceptionA.printStackTrace();
                }
                break;
            case R.id.bt_exception_B:
                try {
                    throw new ExceptionB();
                } catch (Exception exceptionB) {
                    ExceptionManager.getInstance().showException(exceptionB);
                    exceptionB.printStackTrace();
                }
                break;
            case R.id.bt_exceptionC:
                try {
                    throw new ExceptionC();
                } catch (Exception exceptionC) {
                    ExceptionManager.getInstance().showException(exceptionC);
                    exceptionC.printStackTrace();
                }
                break;
            case R.id.bt_exception_D:
                try {
                    throw new ExceptionD();
                } catch (Exception exceptionD) {
                    ExceptionManager.getInstance().showException(exceptionD);
                    exceptionD.printStackTrace();
                }
                break;
        }
    }
}
