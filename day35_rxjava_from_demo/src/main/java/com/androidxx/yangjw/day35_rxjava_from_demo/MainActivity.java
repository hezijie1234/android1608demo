package com.androidxx.yangjw.day35_rxjava_from_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Rxjava的基本使用之From方法
 * from ： 遍历
 * filter：过滤
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private String[] strs = {"a,a","bb","cc","dd","ee"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {

        //from方法就是遍历集合或者数组的方法
//        Observable<String> observable = Observable.from(strs);
        //action1仅仅是一个item，就是将observer中的onNext方法单独提出来使用的是一个类.
        //可以用来充当onNext方法
//        observable.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//
//            }
//        });


//        Observable.from(strs)
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.i(TAG, "call: " + s);
//                    }
//                });

        filter();

    }

    /**
     * filter方法中的func1对象和action1类似的，只是ffunc1有出口（有返回值）
     * action1没有返回值
     */
    private void filter() {
        Observable.from(strs)
                //过滤器
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        //当返回true才能执行下面的方法，返回false就不能执行下面的方法
                        return "bb".equals(s) ? false : true;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "call: " + s);
                    }
                });
    }
}
