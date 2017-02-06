package com.androidxx.yangjw.android1608demo.model;

import android.util.Log;

import com.androidxx.yangjw.android1608demo.http.AsyncTaskTool;

/**
 * Created by yangjw on 2017/1/16.
 */
public class MainModel implements IModel{
    public static final String URL = "http://www.1688wan.com/majax.action?method=getGiftList";

    private ISuccess success;

    public interface ISuccess {
        public void success(String result);
    }

    public MainModel(ISuccess success) {
        this.success = success;
    }

    public void queryData() {
        //请求网络数据
        AsyncTaskTool.load(URL).post("pageno=1").execute(new AsyncTaskTool.IMyCallback() {

            @Override
            public void success(String result) {
                Log.d("androidxx", "success: " + result);
                if (success != null) {
                    success.success(result);
                }
            }
        });


    }
}
