package com.androidxx.yangjw.day36_retrofit_rxjava_demo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yangjw on 2017/2/9.
 */
public interface AppService {

    //?ad=2&gender=1&generation=2&limit=20&offset=0

    /**
     * Path注解：表示替换URI的路径中的字符串
     * Query注解：用来传递参数
     * @param num
     * @param ad
     * @param gender
     * @param generation
     * @param limit
     * @param offset
     * @return
     */
    @GET("v2/channels/{num}/items")
    Observable<SelectionBean> querySelectionDatas(@Path("num") int num
            , @Query("ad") int ad,@Query("gender") int gender,@Query("generation") int generation
            , @Query("limit") int limit, @Query("offset") int offset);
}
