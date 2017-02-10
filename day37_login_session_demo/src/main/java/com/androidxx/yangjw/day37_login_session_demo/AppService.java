package com.androidxx.yangjw.day37_login_session_demo;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yangjw on 2017/2/10.
 */
public interface AppService {

    @FormUrlEncoded
    @POST("login.do")
    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password,@Field("type") String type);

    @Headers("cookie:JSESSIONID=CEBED94CD9BFE9258D67985687FE6E03")
    @GET("list.do")
    Call<ResponseBody> queryDatas(@Query("type") String type);
}
