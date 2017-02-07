package com.androidxx.yangjw.day34_liwushuo_demo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidxx.yangjw.day34_liwushuo_demo.view.adapter.MainPagerAdapter;
import com.androidxx.yangjw.day34_liwushuo_demo.view.home.HeartSelectionFragment;
import com.androidxx.yangjw.day34_liwushuo_demo.view.home.MainCommonFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.main_view_pager)
    ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        ButterKnife.bind(this);
        initFragment();
        initViewPagerAndTab();
    }

    private void initFragment() {
        fragmentList.add(HeartSelectionFragment.newInstance());
        fragmentList.add(MainCommonFragment.newInstance("送女票1"));
        fragmentList.add(MainCommonFragment.newInstance("送女票2"));
        fragmentList.add(MainCommonFragment.newInstance("送女票3"));
        fragmentList.add(MainCommonFragment.newInstance("送女票4"));
        fragmentList.add(MainCommonFragment.newInstance("送女票5"));
    }

    private void initViewPagerAndTab() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(fragmentManager, fragmentList);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
