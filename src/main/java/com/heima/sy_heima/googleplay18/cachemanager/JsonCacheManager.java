package com.heima.sy_heima.googleplay18.cachemanager;

import android.text.TextUtils;

import com.heima.sy_heima.googleplay18.utils.GsonUtil;

import java.util.List;

/**
 * 作者: old样
 * 描述:json缓存框架
 * 上海传智播客android黑马程序员
 */

public class JsonCacheManager {
    private JsonCacheManager() {

    }

    private static JsonCacheManager sDownManager = new JsonCacheManager();

    public static JsonCacheManager getInstance() {
        return sDownManager;
    }


    //用户传入地址与对象的class返回对应的对象
    public<T> T getCacheBean(String url,Class<T> clss) {
        //1. 请求数据
        String content = HttpManager.getInstance().dataGet(url);
        //2. 判断数据
        if (TextUtils.isEmpty(content)) {
            //空的
            //3.去本地获取数据
            content = CacheManager.getInstance().getCacheData(url);
        } else {
            //说明有
            //4.去本地保存数据
            CacheManager.getInstance().saveCacheData(url,content);
        }

        //打印当前的数据是否存在
        //System.out.println(content.length());

        //5. 解析数据返回
        if (TextUtils.isEmpty(content)) {
            //如果为空,直接返回null
            return null;
        } else {
            //有数据,我们就返回解析后的对象
           return GsonUtil.parseJsonToBean(content, clss);
        }

    }

    //获取集合数据
    public<T> List<T> getCacheList(String url, Class<T> clss) {
        //1. 请求数据
        String content = HttpManager.getInstance().dataGet(url);
        //2. 判断数据
        if (TextUtils.isEmpty(content)) {
            //空的
            //3.去本地获取数据
            content = CacheManager.getInstance().getCacheData(url);
        } else {
            //说明有
            //4.去本地保存数据
            CacheManager.getInstance().saveCacheData(url,content);
        }

        //打印当前的数据是否存在
        //System.out.println(content.length());

        //5. 解析数据返回
        if (TextUtils.isEmpty(content)) {
            //如果为空,直接返回null
            return null;
        } else {
            //有数据,我们就返回解析后的对象
            return GsonUtil.fromJsonArray(content, clss);
        }

    }
}
