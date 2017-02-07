package com.androidxx.yangjw.day34_liwushuo_demo.model.home.impl;

import com.androidxx.yangjw.day34_liwushuo_demo.model.home.IHomeModel;
import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.SelectionBean;
import com.androidxx.yangjw.day34_liwushuo_demo.utils.UrlConstants;
import com.androidxx.yangjw.day34_okhttp_tool_demo.OkHttpTools;
import com.google.gson.Gson;

/**
 * Created by yangjw on 2017/2/7.
 */
public class HomeModel implements IHomeModel {

    @Override
    public void querySelectionList(int pageno,final IHomeModelCallBack callBack) {
        //异步方法，不能通过return返回数据，所以只能使用接口回调获取数据
        OkHttpTools.builder()
                .url(UrlConstants.SELECTION_URL)
                .get()
                .callback(new OkHttpTools.OkCallBack() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        SelectionBean selectionBean = gson.fromJson(result, SelectionBean.class);
                        //将Bean对象传递到Presenter层
                        callBack.selectionDatas(selectionBean);
                    }
                });
    }
}
