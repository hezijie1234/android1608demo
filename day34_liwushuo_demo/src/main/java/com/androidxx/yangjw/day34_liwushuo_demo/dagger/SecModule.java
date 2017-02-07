package com.androidxx.yangjw.day34_liwushuo_demo.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangjw on 2017/2/7.
 */
@Module
public class SecModule {

    @Provides
    public String provideStr() {
        return "zhangsan";
    }
}
