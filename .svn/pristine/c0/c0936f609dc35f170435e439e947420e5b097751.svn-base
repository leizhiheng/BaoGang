package com.baosteel.qcsh.ui.activity.home.travel.wifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.WifiLease;
import com.baosteel.qcsh.ui.adapter.WifiLeaseAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jws on 2015/9/16.
 */
public class WifiLeaseListActivity extends BaseActivity {
    private TitleBar mTitle_bar;
    private ListView mLv_wifi_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_lease_list);
        initViews();
        initData();
    }

    private void initViews(){
        mTitle_bar = (com.baosteel.qcsh.ui.view.TitleBar) findViewById(R.id.title_bar);
        mLv_wifi_product = (ListView) findViewById(R.id.lv_wifi_product);
    }

    private void initData(){
        mTitle_bar.setTitle("WiFi租赁");
        mTitle_bar.setBackgroud(R.color.lxb_color);
        mLv_wifi_product.setAdapter(new WifiLeaseAdapter(mContext, getData()));
        mLv_wifi_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(mContext,WifiOrderCommitActivity.class));
            }
        });
    }

    private List<WifiLease> getData(){
        List<WifiLease> list = new ArrayList<WifiLease>();
        list.add(new WifiLease("日本旅行Wifi租赁","258",500));
        list.add(new WifiLease("日本旅行Wifi租赁","258",500));
        list.add(new WifiLease("日本旅行Wifi租赁","258",500));
        list.add(new WifiLease("日本旅行Wifi租赁","258",500));
        return list;
    }
}
