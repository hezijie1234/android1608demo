package com.androidxx.yangjw.day33_okhttp_post_demo;

import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp的基本使用之POST请求
 * 步骤：
 * 1、导包
 * 2、创建一个OkHttpClient
 * 3、创建Request对象（传值）
 * 4、执行Request
 */
public class MainActivity extends AppCompatActivity {

    private TextView mShowTxt;


//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            mShowTxt.setText(msg.obj.toString());
//        }
//    };

    private MyHandler myHandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHandler = new MyHandler(this);
        mShowTxt = (TextView) findViewById(R.id.main_show_txt);
    }

    public void click(View view) {
        jsonParamToServer();
    }


    /**
     * 传递Form表单格式的数据
     */
    public void formDataToServer() {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("name","zhangsan") //键值对的形式保存参数（需要传递到后台服务器的参数）
                .add("age","33")
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.53.18:8080/Android1608/login")
                .post(formBody) //需要传值
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Message message = myHandler.obtainMessage();
                message.obj = result;
                message.sendToTarget();
            }
        });
    }


    /**
     * 传递Json格式的数据
     */
    public void jsonParamToServer() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        /**
         * 参数1：传递的数据类型（MIME，image/jpeg,application/json）
         * 参数2：需要传递的数据
         */
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType,"{name:zhangsan,age:38}");
        Request request = new Request.Builder()
                .url("http://192.168.53.18:8080/Android1608/login")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Message message = myHandler.obtainMessage();
                message.obj = result;
                message.sendToTarget();
            }
        });
    }


    /**
     * 使用static修饰的内部类是一个独立的类，不受任何类的影响
     */
    static class MyHandler extends Handler {

        /**
         * 弱引用：特点就是当垃圾回收机制需要回收被引用的对象时，是直接回收。
         */
        private WeakReference<MainActivity> weakReference;

        public MyHandler(MainActivity activity) {
            weakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //空指针判断
            MainActivity mainActivity = weakReference.get();
            try {
                if (mainActivity != null) {
                    mainActivity.mShowTxt.setText(msg.obj.toString());
                }
            }catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
    }
}
