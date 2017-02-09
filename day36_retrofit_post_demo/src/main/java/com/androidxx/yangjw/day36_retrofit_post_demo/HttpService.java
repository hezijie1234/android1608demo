package com.androidxx.yangjw.day36_retrofit_post_demo;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yangjw on 2017/2/9.
 */
public interface HttpService {

    //http://www.1688wan.com
    @FormUrlEncoded
    @POST("/majax.action?method=getGiftList")
    Call<ResponseBody> queryList(@Field("pageno") int num);
}
