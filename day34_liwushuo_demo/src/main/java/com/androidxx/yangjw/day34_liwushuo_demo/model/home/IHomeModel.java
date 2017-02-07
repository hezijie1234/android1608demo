package com.androidxx.yangjw.day34_liwushuo_demo.model.home;

import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.SelectionBean;

/**
 * Created by yangjw on 2017/2/7.
 */
public interface IHomeModel {

    /**
     * 查询精选列表数据的接口
     * @param pageno 页码
     * added by yangjingwen
     */
    void querySelectionList(int pageno, IHomeModelCallBack callBack);

    public interface IHomeModelCallBack{
        void selectionDatas(SelectionBean bean);
    }
}
