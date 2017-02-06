package com.androidxx.yangjw.day33_okhttp_get_demo;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
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
 * OkHttp的基本使用之Get请求
 * 步骤：
 * 1、导包
 * 2、创建一个OkHttpClient对象
 * 3、创建一个Request对象
 * 4、使用OkHttpClient执行Request对象
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "android1608";
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new AlertDialog.Builder(this)
                .setMessage("网络不可用，请检查网络")
                .setPositiveButton("确定",null)
                .create();
    }

    public void click(View view) {
        //创建OkHttp的对象
        //注意：官方建议一个APP中只能有一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //创建一个Request对象
        Request request = new Request.Builder()
//                .url("http://www.1688wan.com/majax.action?method=getJtkaifu")//配置请求地址
                .url("http://192.168.53.18:8080/Android1608/login?name=zhangsan&age=23")//配置请求地址
                .get()//表示Get请求，默认就是Get请求
                .build();
        //执行Request
        Call call = okHttpClient.newCall(request);
        //通过Call对象执行请求
//        try {
//            //execute不能再主线中执行
//            Response response = call.execute();//是一个同步方法，表示execute是由主线程执行。
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //异步请求
        //异步请求只能通过接口回掉获得数据
        call.enqueue(new Callback() {
            //请求失败:

            /**
             * 网络问题（链接不上、或者超时）、服务器宕机了
             * @param call 本次请求的Call对象，就是执行enqueue方法的Call对象
             * @param e 请求异常
             *
             *
             *          运行在子线程上
             */
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: -----------");
//                dialog.show();
                e.printStackTrace();
            }

            //请求成功

            /**
             *
             * @param call 本次请求的Call对象，就是执行enqueue方法的Call对象
             * @param response 服务器返回的对象，封装了服务器返回的JSON数据
             * @throws IOException
             *
             * 运行在子线程上
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取网络请求的结果
                //注意：response.body().string()执行执行一次，如果执行第2次则获取不到结果
//                Log.i(TAG, "onResponse: 1---" + response.body().string());
                String string = response.body().string();
                Log.i(TAG, "onResponse: 2---" + string);

            }
        });
    }
}
