package com.androidxx.yangjw.day36_review_fragment_demo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Fragment和Activity间的通信
 * 1、activity向Fragment传递数据
 */
public class MainActivity extends AppCompatActivity implements MyFragment.IFragmentCallback{
    private static final String TAG = "activity";
    FragmentManager supportFragmentManager;
    MyFragment myFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportFragmentManager = getSupportFragmentManager();


    }

    public void click(View view) {
        Intent intent = new Intent(this,DetailsActivity.class);
        startActivity(intent);
    }

    /**
     * setArguments传值只能在Fragment初始化的时候进行
     * @param view
     */
    public void a2f(View view) {
        myFragment = MyFragment.newInstance("这是来自Activity的数据");
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_layout,myFragment);
        fragmentTransaction.commit();

    }

    /**
     * 当Fragment已经初始化后的传值方式
     * @param view
     */
    public void a2f2(View view) {
        myFragment.setValue("zhangsan");
    }


    @Override
    public void setActivityValue(String name) {
        Log.i(TAG, "setActivityValue: --- " + name);
    }
}
