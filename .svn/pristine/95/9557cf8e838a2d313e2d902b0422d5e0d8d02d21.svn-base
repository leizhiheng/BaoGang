package com.baosteel.qcsh.ui.activity.prodcut;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.AppraiseListAdapter;
import com.common.base.BaseFragment;
import com.common.view.listview.MyListView;

/**
 * 安程宝商品详情-评价
 * Created by kuangyong on 15/9/1.
 */
public class CarAppraiseFragment extends BaseFragment{
    // --------------------产品轮换图---------
    private MyListView lvappraise;
    private TextView tvappraise;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.layout_fragment_car_appraise, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        setListener();
        initView();
    }

    private void findView() {
        lvappraise = (MyListView) findViewById(R.id.lv_appraise);
        tvappraise = (TextView) findViewById(R.id.tv_appraise);
    }

    private void setListener() {

    }

    private void initView() {
        AppraiseListAdapter adapter=new AppraiseListAdapter(mContext);
        lvappraise.setAdapter(adapter);
    }

}
