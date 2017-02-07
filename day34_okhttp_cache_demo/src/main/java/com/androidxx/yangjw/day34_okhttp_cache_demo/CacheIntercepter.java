package com.androidxx.yangjw.day34_okhttp_cache_demo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangjw on 2017/2/7.
 */
public class CacheIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Response cacheResponse = response.newBuilder()
                .removeHeader("Pragma") //是Http协议中用来设置缓存时间的一个属性
                .removeHeader("Cache-Control") // 也是用来设置缓存的一个属性
                .header("Cache-Control", "max-age=3600") //添加缓存，并且设置缓存时间
                .build();
        return cacheResponse;
    }
}
