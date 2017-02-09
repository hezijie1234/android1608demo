package com.androidxx.yangjw.day36_retrofit_helloworld_demo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yangjw on 2017/2/9.
 */
public interface AppHttpService {
    /**
     * GET注解表示的采用Get方式进行请求
     * 注解中的值是URI。
     * @return 服务器返回给我们值
     */
    @GET("v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0")
    Call<ResponseBody> querySelectionDatas();
}
