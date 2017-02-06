package com.androidxx.yangjw.day32_dependency_injection_demo;

import android.os.*;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yangjw on 2017/2/4.
 */
public class MyFragment extends Fragment {

    @BindView(R.id.fragment_show_txt)
    TextView showTxt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        Binder.bind(this,view);
        showTxt.setText("这是Fragment");
        return view;
    }
}
