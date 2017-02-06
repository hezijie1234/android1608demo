package com.androidxx.yangjw.android1608demo.controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidxx.yangjw.android1608demo.R;
import com.androidxx.yangjw.android1608demo.model.GiftModel;
import com.androidxx.yangjw.android1608demo.model.IModel;
import com.androidxx.yangjw.android1608demo.model.MainModel;

public class MainActivity extends AppCompatActivity implements MainModel.ISuccess {

    private TextView mShowTxt;
    private IModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShowTxt =  new TextView(this);
        setContentView(mShowTxt);
        model = new MainModel(this);
        model.queryData();
    }

    @Override
    public void success(String result) {
        mShowTxt.setText(result);
    }
}
