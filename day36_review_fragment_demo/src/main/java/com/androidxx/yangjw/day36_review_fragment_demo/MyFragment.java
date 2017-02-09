package com.androidxx.yangjw.day36_review_fragment_demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yangjw on 2017/2/9.
 */
public class MyFragment extends Fragment {

    public static final String KEY = "val";
    private static final String TAG = "fragment";
    private Context context;
    private IFragmentCallback callback;

    public static MyFragment newInstance(String value) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,value);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    public void setValue(String value) {
        Log.i(TAG, "setValue: " + value);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() instanceof IFragmentCallback) {
            callback = (IFragmentCallback) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(context);
        String string = getArguments().getString(KEY);
        textView.setText(string);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                activity.setActivityValue("lisi");
                callback.setActivityValue("lisi");
            }
        });
        return textView;
    }


    /**
     * 此接口用来Fragment向Activity传值
     */
    public interface IFragmentCallback {
        void setActivityValue(String name);
    }
}
