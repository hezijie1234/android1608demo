package com.androidxx.yangjw.day34_liwushuo_demo.presenter.home;

import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.BannerBean;
import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.SelectionBean;

/**
 * Created by yangjw on 2017/2/7.
 */
public interface IHomePresenter {

    void querySelectionList(int pageno);

    void queryBanner();

    public interface IHomePresenterCallback{
        void selectionDatas(SelectionBean bean);

        void bannerDatas(BannerBean bannerBean);
    }
}
