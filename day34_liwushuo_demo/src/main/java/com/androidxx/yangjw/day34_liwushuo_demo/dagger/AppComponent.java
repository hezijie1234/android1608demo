package com.androidxx.yangjw.day34_liwushuo_demo.dagger;

import com.androidxx.yangjw.day34_liwushuo_demo.MainActivity;
import com.androidxx.yangjw.day34_liwushuo_demo.view.home.HeartSelectionFragment;
import com.androidxx.yangjw.day34_liwushuo_demo.view.home.MainCommonFragment;

import dagger.Component;

/**
 * Created by yangjw on 2017/2/7.
 */
@Component(modules = {AppModule.class,SecModule.class})
public interface AppComponent {

    void inject(HeartSelectionFragment fragment);

    void inject(MainCommonFragment fragment);

    void inject(MainActivity activity);
}
