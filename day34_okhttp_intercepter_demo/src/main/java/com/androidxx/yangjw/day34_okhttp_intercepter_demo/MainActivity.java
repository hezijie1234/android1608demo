package com.androidxx.yangjw.day34_okhttp_intercepter_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp基本使用之拦截器（过滤器）
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "android1608";
    private OkHttpClient httpClient;
    private LoggingIntercepter loggingIntercepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loggingIntercepter = new LoggingIntercepter();
        httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingIntercepter) //配置拦截器
                .build();

    }

    public void click(View view) {
        //Get请求
        Request request = new Request.Builder()
                .url("http://api.liwushuo.com/v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0")
                .build();
        Log.i(TAG, "begin");
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);
                Log.i(TAG, "onResponse: end");
            }
        });
    }


}
