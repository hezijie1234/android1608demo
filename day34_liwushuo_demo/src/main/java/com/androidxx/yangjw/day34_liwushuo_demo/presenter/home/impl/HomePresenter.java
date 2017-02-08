package com.androidxx.yangjw.day34_liwushuo_demo.presenter.home.impl;

import com.androidxx.yangjw.day34_liwushuo_demo.model.home.IHomeModel;
import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.BannerBean;
import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.SelectionBean;
import com.androidxx.yangjw.day34_liwushuo_demo.model.home.impl.HomeModel;
import com.androidxx.yangjw.day34_liwushuo_demo.presenter.home.IHomePresenter;

import javax.inject.Inject;

/**
 * Created by yangjw on 2017/2/7.
 */
public class HomePresenter implements IHomePresenter , IHomeModel.IHomeModelCallBack{
    private IHomeModel homeModel;
    @Inject
    IHomePresenterCallback callback;

    public HomePresenter(IHomePresenterCallback callback,IHomeModel homeModel) {
        this.homeModel = homeModel;
        this.callback = callback;
    }

    @Override
    public void querySelectionList(int pageno) {
        homeModel.querySelectionList(pageno,this);
    }

    @Override
    public void queryBanner() {
        homeModel.queryBanner(this);
    }

    //获取Model层传递的结果数据
    @Override
    public void selectionDatas(SelectionBean bean) {
            //将数据由Presenter层传递给View层
            this.callback.selectionDatas(bean);
    }

    @Override
    public void bannerData(BannerBean bannerBean) {
        callback.bannerDatas(bannerBean);
    }
}
