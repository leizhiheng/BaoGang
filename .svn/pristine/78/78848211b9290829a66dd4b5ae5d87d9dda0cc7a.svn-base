package com.baosteel.qcsh.ui.activity.home.travel.wifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.TravelGvData;
import com.baosteel.qcsh.ui.adapter.TravelCustomAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.banner.BannerData;
import com.common.view.banner.BannerView;
import com.common.view.banner.IOnBannerItenClick;
import com.common.view.viewflow.CircleFlowIndicator;
import com.common.view.viewflow.ViewFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jws on 2015/9/10.
 */
public class WifiLeaseActivity extends BaseActivity implements IOnBannerItenClick {
    /**
     * Banner轮播图
     **/
    private BannerView bannerView;
    private TitleBar  mTitleBar;
    private GridView  mGv_wifi_lease;
    private List<TravelGvData>  travelGvDatas;
    private TravelCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_lease);
        initView();
        loadData();

    }

    private void initView() {

        //轮播图
        ViewFlow viewFlow = (ViewFlow) findViewById(R.id.mHomeViewflow);// 获得viewFlow对象
        CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.mHomeViewflowindic); // viewFlow下的indic
        bannerView = new BannerView(mContext, viewFlow, indic);
        bannerView.setOnItemClickListener(this);

        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.setBackgroud(R.color.lxb_color);
        mTitleBar.setTitle(getStringExtra("travelType"));

        mGv_wifi_lease = (GridView) findViewById(R.id.gv_wifi_lease);
        adapter =new TravelCustomAdapter(getBaseContext(), getData());
        mGv_wifi_lease.setAdapter(adapter);
        travelGvDatas = getData();
    }

    private List<TravelGvData> getData(){
        List<TravelGvData>  travelGvDatas = new ArrayList<TravelGvData>();
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "韩国"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont,false,"台湾"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "美国"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "日本"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont,false,"泰国"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont,false,"新加坡"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "美国"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont,false,"法国"));
        return travelGvDatas;
    }

    private void loadData(){
        //构造Banner假数据
        List<BannerData> dataList = new ArrayList<BannerData>();
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        bannerView.refreshData(dataList);


        mGv_wifi_lease.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setDefalutCheck().get(i).setCheck(true);
                adapter.setList(travelGvDatas);
                startActivity(new Intent(getBaseContext(),WifiLeaseListActivity.class));
            }
        });
    }

    @Override
    public void onBannerItemClick(BannerData data, int position) {

    }

    private List<TravelGvData> setDefalutCheck(){
        for (int i = 0; i < travelGvDatas.size(); i++) {
            travelGvDatas.get(i).setCheck(false);
        }
        return travelGvDatas;
    }
}
