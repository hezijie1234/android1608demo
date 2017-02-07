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
public class HeartSelectionFragment extends Fragment {

    private Context context;

    //工厂方法
    public static HeartSelectionFragment newInstance() {
        return new HeartSelectionFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(context);
        textView.setText("1111111");
        return textView;
    }
}
