package com.androidxx.yangjw.day36_retrofit_rxjava_demo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by yangjw on 2017/2/9.
 */
public interface AppService {

    @GET("v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0")
    Observable<SelectionBean> querySelectionDatas();
}
