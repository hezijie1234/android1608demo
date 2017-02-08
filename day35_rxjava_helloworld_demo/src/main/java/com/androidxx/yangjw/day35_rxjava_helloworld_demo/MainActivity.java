package com.androidxx.yangjw.day35_rxjava_helloworld_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Rxjava的基本使用
 * 步骤：
 * 1、导包  compile 'io.reactivex:rxjava:1.2.6'
 * 2、创建一个观察者
 * 3、创建一个被观察者
 * 4、关联观察者和被观察者
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        //观察者对象
        //泛型的含义：表示此对象中操作的数据类型只能是String类型
        Observer<String> observer = new Observer<String>() {

            /**
             * 完成：此方法是一个终点。表示观察与被观察的一个结束。
             */
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            /**
             * 当被观察者出现异常，则执行此方法
             * @param e
             */
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                
            }

            /**
             * 观察者用来接收被观察者发送的消息
             * @param s 被观察者发送的消息
             */
            @Override
            public void onNext(String s) {
                Log.i("androidxx", "onNext: " + s);
            }
        };

        //被观察者对象
        Observable.OnSubscribe<String> subscribe = new Observable.OnSubscribe<String>() {

            /**
             * 触发观察者的方法
             * @param subscriber 用来和观察者进行通信的一个对象
             */
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("zhangsan");
                throw new NullPointerException("这是人为的一个异常");
//                subscriber.onCompleted();
            }
        };

        Observable<String> observable = Observable.create(subscribe);

        //关联,触发被观察者对象中的call方法执行的语句
        observable.subscribe(observer);
    }

}
