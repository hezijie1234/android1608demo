package com.androidxx.yangjw.day35_rxjava_map_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Rxjava的基本使用之map和flatmap方法的使用
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void click(View view) {
        String[] strs  = {"1","2","3"};
        //需要将数组中的每一个字母转换成整数进行输出
        Observable.from(strs)
                //如果需要对数据进行2次处理，可以使用map
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {

                        return Integer.parseInt(s);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "call: " + integer);
                    }
        });
    }


//        for (int i = 0; i < strs.length; i++) {
//            String str = strs[i];
//            String[] items = str.split(",");
//            for (int j = 0; j < items.length; j++) {
//                Log.i(TAG, "click: " + items[j]);
//            }
//        }
    public void flatMap(View view) {
        String[] strs = {"zhang,san","li,si","wang,wu"};
        Observable.from(strs)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        String[] items = s.split(",");
                        Log.i(TAG, "call111: " + Thread.currentThread().getName());
                        return Observable.from(items);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "call222: " + Thread.currentThread().getName());
                        Log.i(TAG, "call: " + s);
                    }
                });

    }
}
