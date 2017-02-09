package com.androidxx.yangjw.day36_review_fragment_demo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailsActivity extends AppCompatActivity{

    private static final String TAG = "activity2222";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        MyFragment myFragment = MyFragment.newInstance("zhangsan");
        fragmentTransaction.replace(R.id.details_fragment_layout,myFragment);
        fragmentTransaction.commit();
    }

//    @Override
//    public void setActivityValue(String name) {
//        Log.i(TAG, "setActivityValue:2222 " + name);
//    }
}
