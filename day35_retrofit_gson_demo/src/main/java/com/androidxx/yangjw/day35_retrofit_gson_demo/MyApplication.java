package com.androidxx.yangjw.day35_retrofit_gson_demo;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangjw on 2017/2/9.
 */
public class MyApplication extends Application {

    private Retrofit retrofit;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create()) //添加json解析方式为GSON
                .build();
    }
}
