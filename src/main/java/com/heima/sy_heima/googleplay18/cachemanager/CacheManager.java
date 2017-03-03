package com.heima.sy_heima.googleplay18.cachemanager;

import android.os.Environment;
import android.text.TextUtils;

import com.heima.sy_heima.googleplay18.gloab.GooglePlay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class CacheManager {
    String dir = "";

    private CacheManager() {
        if (TextUtils.isEmpty(dir)) {
            dir = Environment.getExternalStorageDirectory().getPath() + File.separator + GooglePlay.context.getPackageName() + File.separator + "cache";
            File fileDir = new File(dir);
            //在java中多级目录是无法直接创建的
            if (!fileDir.exists()) {
                fileDir.mkdirs();//创建多级目录时使用
            }
        }
    }

    private static CacheManager sDownManager = new CacheManager();

    public static CacheManager getInstance() {
        return sDownManager;
    }

    //从本地获取数据
    //返回的结果String,参数url
    public String getCacheData(String url) {
        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream =null;
        try {
            File file = new File(dir, getFileName(url));
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];

            int len = -1;

            while ((len = fileInputStream.read(buffer)) != -1) {
                stringBuffer.append(new String(buffer, 0, len));//这里一定要写0跟len
            }



        } catch (Exception e) {
            e.printStackTrace();
            //处理错误,以后可以写到日志文件中
            return null;

        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }


        return stringBuffer.toString();
    }

    //保存数据
    //返回结果可以不关心,参数url地址,保存的内容
    public void saveCacheData(String url, String content) {
        //16进制
        //把url用md5转成文件名

        //1. 我们生成一个文件
        //存在sd 卡包名下的cache目录下
        //File.separator跨平台的
        //有时候有些api在某些手机型号上面不能用,怎么处理,可以去判断当手操作系统型号
        FileOutputStream fileOutputStream =null;
        try {



            File file = new File(dir, getFileName(url));

            fileOutputStream =  new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
            //这里不需要返回
            //可以写入到错误日志文件中
        }finally {
            //关闭流
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //2. 把数据写入到文件
        //3.结束
    }

    //把当前的url用md5转成文件名
    private String getFileName(String url) {

        StringBuffer stringBuffer = new StringBuffer();

        //md5
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //把对应的数据传入
            messageDigest.update(url.getBytes());

            //得到转化完的数据
            byte[] digest = messageDigest.digest();
            for (int i = 0; i < digest.length; i++) {
                //把byte转成16进制
                //0xFFFFFF34
                String hexString = Integer.toHexString(digest[i] & 0xFF);
                System.out.println("打印当前的数据:"+hexString);
                stringBuffer.append(hexString);

            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
