package com.androidxx.yangjw.day34_liwushuo_demo.dagger;

import com.androidxx.yangjw.day34_liwushuo_demo.model.home.IHomeModel;
import com.androidxx.yangjw.day34_liwushuo_demo.model.home.impl.HomeModel;
import com.androidxx.yangjw.day34_liwushuo_demo.presenter.home.IHomePresenter;
import com.androidxx.yangjw.day34_liwushuo_demo.presenter.home.impl.HomePresenter;
import com.androidxx.yangjw.day34_liwushuo_demo.view.home.HeartSelectionFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangjw on 2017/2/7.
 */
@Module
public class AppModule {
    private IHomePresenter.IHomePresenterCallback callback;

    public AppModule(IHomePresenter.IHomePresenterCallback callback) {
        this.callback = callback;
    }

    @Provides
    public IHomeModel provideHomeModel() {
        return new HomeModel();
    }
    @Provides
    public IHomePresenter provideHomePresenter(IHomeModel model) {
        return new HomePresenter(callback,model);
    }
}
