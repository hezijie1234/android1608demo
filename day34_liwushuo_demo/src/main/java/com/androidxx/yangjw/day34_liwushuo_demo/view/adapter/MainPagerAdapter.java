package com.androidxx.yangjw.day34_liwushuo_demo.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yangjw on 2017/2/7.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] pageTitles = {"精选","送女票","海涛","创意生活","单身生活","送男票"};
    private List<Fragment> fragmentList;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
