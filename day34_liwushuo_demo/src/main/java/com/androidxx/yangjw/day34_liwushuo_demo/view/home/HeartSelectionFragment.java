package com.androidxx.yangjw.day34_liwushuo_demo.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.day34_liwushuo_demo.R;
import com.androidxx.yangjw.day34_liwushuo_demo.dagger.AppModule;
import com.androidxx.yangjw.day34_liwushuo_demo.dagger.DaggerAppComponent;
import com.androidxx.yangjw.day34_liwushuo_demo.dagger.SecModule;
import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.BannerBean;
import com.androidxx.yangjw.day34_liwushuo_demo.model.home.bean.SelectionBean;
import com.androidxx.yangjw.day34_liwushuo_demo.presenter.home.IHomePresenter;
import com.androidxx.yangjw.day34_liwushuo_demo.presenter.home.impl.HomePresenter;
import com.androidxx.yangjw.day34_liwushuo_demo.view.adapter.FragmentExpandApdater;
import com.androidxx.yangjw.day34_liwushuo_demo.view.adapter.FragmentSelectionAdapter;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangjw on 2017/2/7.
 */
public class HeartSelectionFragment extends Fragment implements IHomePresenter.IHomePresenterCallback{

    private static final String TAG = "androidxx";
    private Context context;
    @BindView(R.id.fgm_selection_refresh_list_view)
    PullToRefreshExpandableListView refreshListView;
    private FragmentSelectionAdapter fragmentSelectionAdapter;
    private List<BannerBean.DataBean.BannersBean> banners = new ArrayList<>();

    @Inject
    IHomePresenter homePresenter;
    private Map<String,List<SelectionBean.DataBean.ItemsBean>> datas = new HashMap<>();
    private List<String> keys = new ArrayList<>();
    private FragmentExpandApdater fragmentExpandApdater;
    private ExpandableListView expandableListView;
    private ConvenientBanner convenientBannber;

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
        View view = inflater.inflate(R.layout.fragment_selection_view, container, false);
        ButterKnife.bind(this,view);
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build().inject(this);
        initHeaderView();
        setupListView();
//        homePresenter = new HomePresenter(this);

        homePresenter.querySelectionList(1);
        homePresenter.queryBanner();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        convenientBannber.startTurning(3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        convenientBannber.stopTurning();
    }

    /**
     * 添加头部的Banner和横向滚动的视图
     */
    private void initHeaderView() {
        Log.i(TAG, "initHeaderView: ");
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_selection_header_view, null);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder(view);
        expandableListView = refreshListView.getRefreshableView();
        expandableListView.addHeaderView(view);
        convenientBannber = headerViewHolder.banner;

    }

    class HeaderViewHolder {
        @BindView(R.id.fragment_selection_bannner_view)
        ConvenientBanner banner;
        public HeaderViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    private void setupBanner(ConvenientBanner convenientBanner) {
        convenientBanner.setPages(new CBViewHolderCreator<MyBannerCreater>() {
            @Override
            public MyBannerCreater createHolder() {
                return new MyBannerCreater();
            }
            },banners)
            //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
            .setPageIndicator(new int[]{R.drawable.btn_check_disabled_nightmode, R.drawable.btn_check_disabled})
            //设置指示器的方向
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }

    class MyBannerCreater implements Holder<BannerBean.DataBean.BannersBean> {

        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerBean.DataBean.BannersBean data) {
            Picasso.with(context).load(data.getImage_url()).into(imageView);
        }
    }

    /**
     * 配置List View的适配器
     * added by yangjingwen at 2017/2/7
     * 为了XXXXX添加了XXXXX
     * modified by zhangsan at 2017/3/7
     *
     */
    private void setupListView() {
        fragmentExpandApdater = new FragmentExpandApdater(keys,datas,context);
//        fragmentSelectionAdapter = new FragmentSelectionAdapter(context);
//        refreshListView.setAdapter(fragmentSelectionAdapter);
        //getRefreshableView()返回的是原生的ExpandableListView
        expandableListView.setAdapter(fragmentExpandApdater);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public void selectionDatas(SelectionBean bean) {

        List<SelectionBean.DataBean.ItemsBean> itemsBeen = bean.getData().getItems();
        int size = itemsBeen.size();
        for (int i = 0; i < size; i++) {
            SelectionBean.DataBean.ItemsBean itemsBean = itemsBeen.get(i);
            long time = itemsBean.getCreated_at();
            String formatTime = formatTime(time);
            if (!datas.containsKey(formatTime)) {
                keys.add(formatTime);
                datas.put(formatTime,new ArrayList<SelectionBean.DataBean.ItemsBean>());
            }
            datas.get(formatTime).add(itemsBean);

        }
        fragmentExpandApdater.notifyDataSetChanged();
        expandListView();

    }

    @Override
    public void bannerDatas(BannerBean bannerBean) {
        banners.addAll(bannerBean.getData().getBanners());
        setupBanner(convenientBannber);
    }

    private String formatTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date(time*1000));
        return format;
    }

    private void expandListView() {
        int size = keys.size();
        for (int i = 0; i < size; i++) {
            ExpandableListView refreshableView = refreshListView.getRefreshableView();
            refreshableView.expandGroup(i);
        }
    }
}
