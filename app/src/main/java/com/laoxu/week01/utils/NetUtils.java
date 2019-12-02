package com.laoxu.week01.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 *@auther: 王亚奇
 *@Date: 2019/11/26
 *@Time:下午 01:45
 *@Description:${DESCRIPTION}
 *①　使用单例模式封装一个NetUtils类。
②　在类中封装网络状态判断的方法，可以判断有网无网。
③　在类中封装HttpUrlConnection的get获取数据的方法。
④　在类中封装HttpUrlConnection的get获取图片的方法。
⑤　给HttpUrlConnection设置读取与连接超时为5秒钟，处理网络异常、判断网络响应状态码，关闭流。
⑥　封装网络数据响应成功处理的接口回调，在接口中定义网络响应成功的方法和网络响应失败的方法。
⑦　使用Log打印网络响应数据成功，（打印不成功扣除5分）。
 *
 */public class NetUtils {

    private static NetUtils netUtils = new NetUtils();

    private NetUtils() {
    }

    public static NetUtils getInstance() {
        return netUtils;
    }

    //string  json 接口
    public interface MyCallBack {
        void onGetJson(String json);




    }

    //getJson
    @SuppressLint("StaticFieldLeak")
    public void getJson(final String httpUrl, final MyCallBack myCallBack) {
        new AsyncTask<Void, Void, String>() {


            /**
             * 回调到主线程
             * @param s
             */
            @Override
            protected void onPostExecute(String s) {
                myCallBack.onGetJson(s);
            }

            @Override
            protected String doInBackground(Void... voids) {
                InputStream inputStream = null;
                String json = "";
                try {
                    URL url = new URL(httpUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    System.out.println("code:"+httpURLConnection.getResponseCode());
                    if (httpURLConnection.getResponseCode() == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        json = io2String(inputStream);
                        Log.i("w", json);
                        return json;

                    } else {
                        Log.e("tag", "请求失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return "";
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    // 流转  字符串
    private String io2String(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len = -1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        String json = new String(bytes1);


        return json;
    }

    // 图片接口
    public interface MyCallBacks {
        void onGetPhoto(Bitmap bitmap);
    }

    //getPhoto
    @SuppressLint("StaticFieldLeak")
    public void getPhoto(final String photoUrl, final MyCallBacks myCallBacks) {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                myCallBacks.onGetPhoto(bitmap);
            }

            @Override
            protected Bitmap doInBackground(Void... voids) {

                InputStream inputStream = null;
                Bitmap bitmap = null;
                try {
                    URL url = new URL(photoUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        bitmap = io2Bitmap(inputStream);
                    } else {
                        Log.e("tag", "请求失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return bitmap;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //流转 图片
    private Bitmap io2Bitmap(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    //  有无网  hasNet
    public  boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null &&activeNetworkInfo.isAvailable()) {
            return  true;
        }else{
            return false;
        }
    }
}
