package com.androidxx.yangjw.android1608demo.model;

import android.util.Log;

import com.androidxx.yangjw.android1608demo.http.AsyncTaskTool;

/**
 * Created by yangjw on 2017/1/16.
 */
public class GiftModel implements IModel{

    private MainModel.ISuccess iSuccess;

    public GiftModel(MainModel.ISuccess iSuccess) {
        this.iSuccess = iSuccess;
    }


    @Override
    public void queryData() {

    }
}
