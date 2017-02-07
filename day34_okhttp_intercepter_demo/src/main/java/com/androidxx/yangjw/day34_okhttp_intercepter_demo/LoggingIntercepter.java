package com.androidxx.yangjw.day34_okhttp_intercepter_demo;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangjw on 2017/2/7.
 * 通过继承intercepter实现拦截器
 */
public class LoggingIntercepter implements Interceptor {
    private static final String TAG = "android1608";

    /**
     * 拦截的点
     * @param chain 用来执行网络请求的一个对象
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {

        //获取被执行的request对象
        Request request = chain.request();
        Log.i(TAG, "request begin");
        long beginTime = System.currentTimeMillis();
        //执行Request请求
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        Log.i(TAG, "request end:" + (endTime - beginTime));
        return response;
    }
}
