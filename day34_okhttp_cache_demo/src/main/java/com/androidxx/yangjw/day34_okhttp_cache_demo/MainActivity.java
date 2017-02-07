package com.androidxx.yangjw.day34_okhttp_cache_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp基本使用之缓存策略
 *  * 实现缓存的类DiskLruCache。
 * 步骤
 * 1、配置缓存的路径和大小
 * 2、配置拦截器，在拦截器中修改reponse中的请求头信息中的Cache-Control属性
 * 3、在Request对象中添加CacheControl属性
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "android1608";
    private OkHttpClient httpClient;
    private CacheIntercepter cacheIntercepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取缓存路径
        File cacheDir = getCacheDir();

        //设置缓存文件大小总和的上限
        int maxSize = 4 * 1024 * 1024;
        //创建缓存对象
        /**
         * 参数1：缓存路径
         * 参数2：缓存的大小
         */
        Cache cache = new Cache(cacheDir, maxSize);

        cacheIntercepter = new CacheIntercepter();

        httpClient = new OkHttpClient.Builder()
                .cache(cache) //配置OkHttp的缓存
                .addNetworkInterceptor(cacheIntercepter) //配置拦截器
                .build();
    }

    public void click(View view){
        Request request = new Request.Builder()
                .url("http://api.liwushuo.com/v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0")
//                .cacheControl(CacheControl.FORCE_CACHE)//强制取缓存
                .cacheControl(new CacheControl.Builder().maxAge(3600, TimeUnit.SECONDS).build()) //配置缓存控制，表示此次请求需要缓存
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: " + string);
            }
        });

    }


}
