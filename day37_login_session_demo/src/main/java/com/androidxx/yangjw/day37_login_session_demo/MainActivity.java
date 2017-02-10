package com.androidxx.yangjw.day37_login_session_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 怎么样在Android中获取session，保证android的请求在同一个session中
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "androidxx";
    private EditText mPasswordEdt;
    private EditText mUsernameEdt;
    private Button mSubmitBtn;
    private Retrofit retrofit;
    private AppService appService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPasswordEdt = (EditText) findViewById(R.id.login_password_edt);
        mUsernameEdt = (EditText) findViewById(R.id.login_username_edt);
        mSubmitBtn = (Button) findViewById(R.id.login_submit_btn);
        mSubmitBtn.setOnClickListener(this);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.53.18:8080/Web1608Demo/")
                .build();
        appService = retrofit.create(AppService.class);
    }

    public void click(View view) {
        Intent intent = new Intent(this,DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        appService.login(mUsernameEdt.getText().toString(),mPasswordEdt.getText().toString(),"ANDROID")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String cookie = response.headers().get("set-cookie");
                            Log.i(TAG, "onResponse: " + cookie);
                            String result = response.body().string();
                            if ("success".equals(result)) {
                                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            }
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
