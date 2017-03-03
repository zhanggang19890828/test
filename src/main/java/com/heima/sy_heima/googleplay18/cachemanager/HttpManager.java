package com.heima.sy_heima.googleplay18.cachemanager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者: old样
 * 描述:网络请求管理类
 * 上海传智播客android黑马程序员
 */

public class HttpManager {
    private HttpManager() {

    }

    private static HttpManager sDownManager = new HttpManager();

    public static HttpManager getInstance() {
        return sDownManager;
    }

    //使用get方式请求数据
    public String dataGet(String url){
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            //在异常的时候一定要返回一个空的对象
            return null;
        }
    }
}
