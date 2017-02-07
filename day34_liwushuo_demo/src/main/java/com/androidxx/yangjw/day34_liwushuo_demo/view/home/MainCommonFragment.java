package com.androidxx.yangjw.day34_liwushuo_demo.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yangjw on 2017/2/7.
 */
public class MainCommonFragment extends Fragment {

    public static final String PARAM_TYPE = "TYPE";
    private Context context;
    private String type;
    //工厂方法
    public static MainCommonFragment newInstance(String type) {
        MainCommonFragment mainCommonFragment = new MainCommonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_TYPE,type);
        mainCommonFragment.setArguments(bundle);
        return mainCommonFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        type = bundle.getString(PARAM_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(context);
        textView.setText(type);
        return textView;
    }
}
