package com.heima.sy_heima.googleplay18.down;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.heima.sy_heima.googleplay18.bean.DetailBean;
import com.heima.sy_heima.googleplay18.gloab.GooglePlay;
import com.heima.sy_heima.googleplay18.utils.HttpUtil;
import com.heima.sy_heima.googleplay18.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者: old样
 * 描述:下载管理类
 * 上海传智播客android黑马程序员
 */

public class DowmManager {

    //文件目录在初始化的时候创建
    public static String saveFileDir = Environment.getExternalStorageDirectory().getPath() + File.separator + GooglePlay.context.getPackageName();


    private DowmManager() {
        File dirFile = new File(saveFileDir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();//创建多级目录
        }
    }

    private static DowmManager sDownManager = new DowmManager();

    public static DowmManager getInstance() {
        return sDownManager;
    }

    //定义hashmap用来缓存我们downinfo
    private HashMap<Integer, DownInfo> mDowninfos = new HashMap<>();

    //在写程序第一步应该先写方法
    //下载
    public void down(DetailBean detailBean) {
        //分析一下有几种状态可以进来
        //空闲,暂停,出错
        //得到当前的状态
        DownInfo downInfo = mDowninfos.get(detailBean.getId());
        if (downInfo == null) {
            //初始化
            downInfo = new DownInfo(detailBean);
            //放入到集合中
            mDowninfos.put(detailBean.getId(), downInfo);
        }

        //到这里说明downinfo信息肯定有
        //在这里肯定要做下载的事情,开启线程池
        //这里必须注意时刻更改状态
        //当前状态应该等待
        downInfo.downstate = DOWMSTATE.WAIT;
        updateState(downInfo);

        //加入到线程开启了
        ThreadPoolManager.getInstance().addRunnable(new DownRunnable(downInfo));

    }

    //更新进度
    private void updateState(final DownInfo downInfo) {
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mDownListeners.size(); i++) {
                    mDownListeners.get(i).publishState(downInfo);
                }
            }
        });

    }

    //创建一个下载的线程
    public class DownRunnable implements Runnable {

        private DownInfo runDownInfo;

        public DownRunnable(DownInfo downInfo) {
            runDownInfo = downInfo;
        }

        @Override
        public void run() {
            //一运行说明就要下载了
            runDownInfo.downstate = DOWMSTATE.DOWNING;
            updateState(runDownInfo);
            //开启下载了,但是下载分两种
            //一种是重新下载,断点续传
            //怎么判断是断点还是重新下载
            //判断文件大小,防止用户删除
            File file = new File(runDownInfo.saveurl);
            if (!file.exists()) {
                //不存在文件,说明重新下载
                String downurl = "http://127.0.0.1:8090/download?name=" + runDownInfo.downurl;
                downAPK(downurl, runDownInfo);
            } else {
                //说明有进度
                /*判断当前的进度与当前文件长度是否一致*/
                if (file.length() != runDownInfo.progress) {
                    //说明有问题,删除文件,进度置空,重新下载
                    file.delete();
                    runDownInfo.progress = 0;
                    String downurl = "http://127.0.0.1:8090/download?name=" + runDownInfo.downurl;
                    downAPK(downurl, runDownInfo);
                } else {
                    //说明一致
                    //断点续传
                    String downurl = "http://127.0.0.1:8090/download?name=" + runDownInfo.downurl + "&range=" + runDownInfo.progress;
                    downAPK(downurl, runDownInfo);
                }
            }

        }
    }

    //下载apk
    private void downAPK(String downURL, DownInfo downInfo) {

        //创建文件
        File file = new File(downInfo.saveurl);
        HttpUtil.HttpResult mHttpResult = null;
        FileOutputStream    mFileOutputStream = null;

        try {
            mHttpResult = HttpUtil.download(downURL);
            if (mHttpResult != null && mHttpResult.getInputStream() != null) {
                InputStream inputStream = mHttpResult.getInputStream();

                //文件追加
                mFileOutputStream = new FileOutputStream(file, true);

                int len = -1;

                //15-20K
                byte[] buffer = new byte[1024 * 15];

                while ((len = inputStream.read(buffer)) != -1 && downInfo.downstate != DOWMSTATE.PAUSE) {

                    //更新进度
                    downInfo.progress += len;

                    updateProgress(downInfo);

                    mFileOutputStream.write(buffer, 0, len);
                }

                //如果走到这里写完了,还有一种可能暂停
                //如果下载完成告诉用户
       /*         if (downInfo.progress == downInfo.size) {
                    //下载完成
                    downInfo.downstate = DOWMSTATE.SUCCESS;

                    updateState(downInfo);
                } else {
                    //这里暂停
                   updateState(downInfo);
                }*/
                if (downInfo.progress == downInfo.size) {
                    //下载完成
                    downInfo.downstate = DOWMSTATE.SUCCESS;

                }
                updateState(downInfo);


            }
        } catch (Exception e) {
            e.printStackTrace();
            //如果有异常
            downInfo.progress = 0;
            file.delete();

            downInfo.downstate = DOWMSTATE.ERROR;
           updateState(downInfo);


        } finally {
            //一般处理关流
            if (mHttpResult != null) {
                mHttpResult.close();
            }
            if (mFileOutputStream != null) {
                try {
                    mFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private void updateProgress(final DownInfo downInfo) {
        Utils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mDownListeners.size(); i++) {
                    mDownListeners.get(i).publishProgress(downInfo);
                }
            }
        });

    }

    //暂停
    public void pause(DetailBean detailBean) {
        //怎么暂停
        DownInfo downInfo = mDowninfos.get(detailBean.getId());
        if (downInfo != null) {
            downInfo.downstate = DOWMSTATE.PAUSE;
        }

    }

    /*         <intent-filter>
               <action android:name="android.intent.action.VIEW" />
               <category android:name="android.intent.category.DEFAULT" />
               <data android:scheme="content" />
               <data android:scheme="file" />
               <data android:mimeType="application/vnd.android.package-archive" />
               </intent-filter>*/
    //安装apk的方法
    public void installAPK(DetailBean detailBean) {

        DownInfo downInfo = mDowninfos.get(detailBean.getId());
        File file = new File(downInfo.saveurl);

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GooglePlay.context.startActivity(intent);

    }

    //定义状态
    public enum DOWMSTATE {
        NONE,         //空闲
        DOWNING,      //下载
        PAUSE,        //暂停
        ERROR,        //出错
        SUCCESS,      //成功
        WAIT          //等待
    }


    //把数据暴露出去
    //通过接口回调出去
    //1. 定义接口
/*    public interface DownListener{
        //当前进度
        void publishProgress();
        //当前状态
        void publishState();
    }

    //3. 定义接收接口的对象
    private  DownListener mDownListener;

    //2. 接收接口
    public void setDownListener(DownListener downListener){
        this.mDownListener = downListener;
    }*/

    //把多个不同的数据发布出去
    //1. 定义接口
    public interface DownListener {
        //当前进度
        void publishProgress(DownInfo downInfo);

        //当前状态
        void publishState(DownInfo downInfo);
    }

    //定义监听的集合
    private List<DownListener> mDownListeners = new ArrayList<>();

    //接收接口
    public void addDownListener(DownListener downListener) {
        if (!mDownListeners.contains(downListener)) {
            mDownListeners.add(downListener);
        }

    }

    //返回一个downinfo
    public DownInfo getDowninfo(DetailBean detailBean) {
        return mDowninfos.get(detailBean.getId());
    }


}
