package com.androidxx.yangjw.day34_liwushuo_demo.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.SelectionBean;

import java.util.List;
import java.util.Map;

/**
 * Created by yangjw on 2017/2/7.
 */
public class FragmentSelectionAdapter extends BaseAdapter {

    private Context context;
    private List<String> keys;
    private Map<String,List<SelectionBean.DataBean.ItemsBean>> datas;

    public FragmentSelectionAdapter(Context context, List<String> keys, Map<String, List<SelectionBean.DataBean.ItemsBean>> datas) {
        this.context = context;
        this.keys = keys;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) convertView;
        if (view == null) {
            view = new TextView(context);
        }
        view.setText("ITEM" + position);
        return view;
    }
}
