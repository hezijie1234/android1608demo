package com.androidxx.yangjw.day35_retrofit_gson_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit和GSON结合
 * 导包：
 * retrofit
 * converter-gson
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppHttpService httpService = ((MyApplication)getApplication()).getRetrofit().create(AppHttpService.class);
        httpService.querySelectionDatas().enqueue(new Callback<SelectionBean>() {
            @Override
            public void onResponse(Call<SelectionBean> call, Response<SelectionBean> response) {
                SelectionBean selectionBean = response.body();
                Log.i(TAG, "onResponse: " + selectionBean.getData().getItems().size());
            }

            @Override
            public void onFailure(Call<SelectionBean> call, Throwable t) {

            }
        });
    }
}
