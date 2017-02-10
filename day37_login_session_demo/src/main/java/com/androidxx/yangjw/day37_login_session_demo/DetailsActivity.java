package com.androidxx.yangjw.day37_login_session_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private AppService appService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.53.18:8080/Web1608Demo/")
                .build();
        appService = retrofit.create(AppService.class);
        appService.queryDatas("ANDROID")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.i(TAG, "onResponse: " + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
