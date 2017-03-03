package com.heima.sy_heima.googleplay18.down;

import com.heima.sy_heima.googleplay18.bean.DetailBean;

import java.io.File;

/**
 * 作者: old样
 * 描述:下载的信息
 * 上海传智播客android黑马程序员
 */

public class DownInfo {
    //下载状态
    public DowmManager.DOWMSTATE downstate;
    //下载地址
    public String                downurl;
    //保存地址
    public String                saveurl;
    //大小
    public long                  size;
    //进度
    public long                  progress;
    //id
    //详情里面来
    public int                   id;

    //我们可以直接通过详情的类初始化我们下载信息


    public DownInfo(DetailBean detailBean) {
        downstate = DowmManager.DOWMSTATE.NONE;//第一次进来空闲
        downurl = "" + detailBean.getDownloadUrl();
        //保存到sd卡/包名/apk名字
        saveurl = DowmManager.saveFileDir+ File.separator+ detailBean.getName() + ".apk";
        size = detailBean.getSize();
        progress = 0;//第一次进来没有进度
        id = detailBean.getId();
    }
}
