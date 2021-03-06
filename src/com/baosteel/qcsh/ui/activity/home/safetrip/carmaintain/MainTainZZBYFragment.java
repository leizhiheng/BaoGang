package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseFragment;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.FastLinkView;
import com.common.view.fastlink.LnkToolsGridViewAdapter;
import com.common.view.scrolllayout.PageControlView;
import com.common.view.scrolllayout.ScrollLayout;

/**
 * 自助保养
 * Created by kuangyong on 15/9/1.
 */
public class MainTainZZBYFragment extends BaseFragment implements LnkToolsGridViewAdapter.BackItemClickListener, View.OnClickListener{
    private EditText tvlicheng;//里程数
    private TextView btn2maintain;//去保养
    /**
     * 快速导航
     */
    private ScrollLayout ScrollLayoutTest;
    private PageControlView pageControl;
    private FastLinkView fastLinkView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_maintain_zzby, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        setListener();
        initView();
        loadData();
    }

    private void findView() {
        this.pageControl = (PageControlView) findViewById(R.id.pageControl);
        this.ScrollLayoutTest = (ScrollLayout) findViewById(R.id.ScrollLayoutTest);
        this.btn2maintain = (TextView) findViewById(R.id.btn_2maintain);
        this.tvlicheng = (EditText) findViewById(R.id.tv_licheng);
    }

    private void setListener() {
        btn2maintain.setOnClickListener(this);
    }

    /**
     * 加载数据
     */
    public void loadData() {
        // 构造FastLink假数据
        List<FastLinkData> fastList = new ArrayList<FastLinkData>();
        fastList.add(new FastLinkData(0, "小保养", R.drawable.icon_maintenancex));

        fastList.add(new FastLinkData(1, "更换轮胎", R.drawable.icon_tire));

        fastList.add(new FastLinkData(2, "更换空气滤清器", R.drawable.icon_air_cleaner));

        fastList.add(new FastLinkData(3, "更换刮雨片", R.drawable.icon_wiper_film));

        fastList.add(new FastLinkData(4, "更换刹车油", R.drawable.icon_brake_oil));

        fastList.add(new FastLinkData(5, "更换火花塞", R.drawable.icon_spark_plug));

        fastList.add(new FastLinkData(6, "更换电瓶上门安装", R.drawable.icon_battery));

        fastList.add(new FastLinkData(7, "更换刹车片", R.drawable.icon_brake_pads));
        fastLinkView.refreshData(fastList);
    }

    private void initView() {
        // 快速导航图
        ScrollLayout mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayoutTest);
        PageControlView pageControl = (PageControlView) findViewById(R.id.pageControl);
        fastLinkView = new FastLinkView(mContext, mScrollLayout, pageControl);
        fastLinkView.setOntemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_2maintain://去保养
                showToast("去保养");
                break;
        }
    }

    @Override
    public void onFastLinkItemClick(FastLinkData bean) {
        switch (bean.getId()) {
            case 0://小保养

                break;
            case 1://更换轮胎

                break;
            case 2://更换空气滤清器

                break;
            case 3://更换刮雨片

                break;
            case 4://更换刹车油

                break;
            case 5://更换火花塞

                break;
            case 6://更换电瓶上门安装

                break;
            case 7://更换刹车片

                break;
        }
        showToast("点击了第" + bean.getName() + "项数据");
    }
}
