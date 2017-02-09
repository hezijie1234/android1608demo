package com.androidxx.yangjw.day36_retrofit_rxjava_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Retrofit结合RxJava的实现
 * 1、导包
 *  compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'

 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    private Map<String,List<SelectionBean.DataBean.ItemsBean>> datas = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                //结合RxJava所必需的适配器的配置
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        AppService appService = retrofit.create(AppService.class);
        appService.querySelectionDatas()
               .map(new Func1<SelectionBean, List<SelectionBean.DataBean.ItemsBean>>() {
                   @Override
                   public List<SelectionBean.DataBean.ItemsBean> call(SelectionBean selectionBean) {
                       return selectionBean.getData().getItems();
                   }
               })
               .flatMap(new Func1<List<SelectionBean.DataBean.ItemsBean>, Observable<SelectionBean.DataBean.ItemsBean>>() {
                    @Override
                    public Observable<SelectionBean.DataBean.ItemsBean> call(List<SelectionBean.DataBean.ItemsBean> itemsBeen) {
                        return Observable.from(itemsBeen);
                    }
                })
               .map(new Func1<SelectionBean.DataBean.ItemsBean, Boolean>() {
                    @Override
                    public Boolean call(SelectionBean.DataBean.ItemsBean itemsBean) {
                        Log.i(TAG, "call: " + Thread.currentThread().getName());
                        Log.i(TAG, "call: " + itemsBean.getTitle());
                        String formatTime = formatTime(itemsBean.getCreated_at());
                        if (!datas.containsKey(formatTime)) {
                            datas.put(formatTime,new ArrayList<SelectionBean.DataBean.ItemsBean>());
                        }
                        datas.get(formatTime).add(itemsBean);
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
//                .subscribe(
//                        //参数1：表示的就是onNext方法
//                        new Action1<Boolean>() {
//                            @Override
//                            public void call(Boolean aBoolean) {
//
//                            }
//                        },
//                        //参数2：onError
//                        new Action1<Throwable>() {
//                            @Override
//                            public void call(Throwable throwable) {
//
//                            }
//                        },
//                        //参数3：onCompleted
//                        new Action0() {
//                            @Override
//                            public void call() {
//
//                            }
//                        });

    }


//    for (int i = 0; i < size; i++) {
//        SelectionBean.DataBean.ItemsBean itemsBean = itemsBeen.get(i);
//        long time = itemsBean.getCreated_at();
//        String formatTime = formatTime(time);
//        if (!datas.containsKey(formatTime)) {
//            keys.add(formatTime);
//            datas.put(formatTime,new ArrayList<SelectionBean.DataBean.ItemsBean>());
//        }
//        datas.get(formatTime).add(itemsBean);
//
//    }
//    fragmentExpandApdater.notifyDataSetChanged();
//    expandListView();

    private String formatTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(time*1000));
    }
}
