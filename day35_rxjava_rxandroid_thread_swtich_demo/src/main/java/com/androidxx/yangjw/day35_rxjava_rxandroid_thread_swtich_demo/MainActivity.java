package com.androidxx.yangjw.day35_rxjava_rxandroid_thread_swtich_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Rxjava的线程切换
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private TextView mOneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOneTxt = (TextView) findViewById(R.id.main_txt_one);
    }

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
                //此方法是用来决定被观察者执行的线程，同时也能决定此方法以上的所有方法都执行在指定的线程上
                .subscribeOn(Schedulers.newThread()) // 子线程
                //决定观察者的线程，一般情况只能决定此方法以下的所有方法执行的线程类型
                .observeOn(AndroidSchedulers.mainThread()) //主线程
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "call222: " + Thread.currentThread().getName());
                        Log.i(TAG, "call: " + s);
                    }
                });

    }

    public void switchThread(View view) {
        String[] strs = {"zhang,san","li,si","wang,wu"};
        Observable.from(strs)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String[] items = s.split(",");
                        return Observable.from(items);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        mOneTxt.setText(s);
                        return s;
                    }
                })
                .observeOn(Schedulers.newThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String replace = s.replace('a', '_');
                        return replace;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
