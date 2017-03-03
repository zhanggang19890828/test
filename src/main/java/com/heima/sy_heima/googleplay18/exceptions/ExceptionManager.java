package com.heima.sy_heima.googleplay18.exceptions;

import com.heima.sy_heima.googleplay18.utils.ToastUtil;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class ExceptionManager {
    private ExceptionManager() {

    }

    private static ExceptionManager sDownManager = new ExceptionManager();

    public static ExceptionManager getInstance() {
        return sDownManager;
    }

    //弹出toast
    public void showException(Exception e) {

        if (e instanceof ExceptionA) {
            ToastUtil.showToast("异常A");
        }
        if (e instanceof ExceptionB) {
            ToastUtil.showToast("异常B");
        }
        if (e instanceof ExceptionC) {
            ToastUtil.showToast("异常C");
        }
        if (e instanceof ExceptionD) {
            ToastUtil.showToast("异常D");
        }

    }
}
