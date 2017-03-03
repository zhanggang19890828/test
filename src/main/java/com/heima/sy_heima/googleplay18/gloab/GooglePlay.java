package com.heima.sy_heima.googleplay18.gloab;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者: old样
 * 描述:这里必须是要在清单文件中注册,如果不注册就会空指针
 * 上海传智播客android黑马程序员
 */

public class GooglePlay extends Application {
    public static  Handler mainHandler;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        mainHandler = new Handler();

        context = this;
        //自由配置
 /*       ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();//不会在内存中缓存太大的图片
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());//为了保证图片名称唯一
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        //内存缓存大小默认是：app可用内存的1/8
        config.tasksProcessingOrder(QueueProcessingType.LIFO);//Last In First Out后进先出
        config.writeDebugLogs(); // Remove for release app

        //		http://www.baidu.com/abc/2.jpg

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());*/

        //默认设置
        //后期不需要知道这么多,都可以进行封装
        ImageLoader.getInstance().init( ImageLoaderConfiguration.createDefault(this));


    }
}
