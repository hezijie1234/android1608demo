package com.androidxx.yangjw.day34_okhttp_tool_test_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidxx.yangjw.day34_okhttp_tool_demo.OkHttpTools;

public class MainActivity extends AppCompatActivity implements OkHttpTools.OkCallBack{

    private TextView mShowTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowTxt = (TextView) findViewById(R.id.main_show_txt);
        OkHttpTools.builder()
                .url("http://api.liwushuo.com/v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0")
                .get()
                .callback(this);
    }

    @Override
    public void success(String result) {
        mShowTxt.setText(result);
    }
}
