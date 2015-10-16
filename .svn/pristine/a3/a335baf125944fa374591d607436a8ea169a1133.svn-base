package com.baosteel.qcsh.ui.fragment.home.travel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.TravelGvData;
import com.baosteel.qcsh.ui.activity.home.travel.custom.TravelPrivateCustomActivity;
import com.baosteel.qcsh.ui.adapter.TravelCustomAdapter;
import com.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/9/9.
 */
public class TravelCustomOneFragment extends BaseFragment implements View.OnClickListener {
    private Button mBtn_travel_custom_next;
    private TravelPrivateCustomActivity travelPrivateCustomActivity;
    private GridView mGv_custom_local;
    private TravelCustomAdapter adapter;
    private List<TravelGvData>  travelGvDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_custom_one, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
    }

    private void initViews() {
        mBtn_travel_custom_next = (Button) findViewById(R.id.btn_travel_custom_next);
        mGv_custom_local = (GridView) findViewById(R.id.gv_custom_local);

    }
    private void setCheck(int i){
        if(travelGvDatas.get(i).isCheck()){
            travelGvDatas.get(i).setCheck(false);
        }else{
            travelGvDatas.get(i).setCheck(true);
        }
    }

    private void initData() {
        travelGvDatas = getData();
        adapter = new TravelCustomAdapter(mContext, travelGvDatas);
        mGv_custom_local.setAdapter(adapter);
        mGv_custom_local.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(getCheckNum(travelGvDatas)>1){
                    clearCheck(travelGvDatas);
                    setCheck(i);
                }else{
                    setCheck(i);
                }
                adapter.setList(travelGvDatas);
            }
        });
        mBtn_travel_custom_next.setOnClickListener(this);
        travelPrivateCustomActivity = (TravelPrivateCustomActivity) getActivity();
        travelPrivateCustomActivity.setTabBackgroud(travelPrivateCustomActivity.mImg_city, travelPrivateCustomActivity.mTv_city, R.drawable.city, R.color.lxb_color);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_travel_custom_next:
                travelPrivateCustomActivity.tabAdapter.checkedIndex(1);
                travelPrivateCustomActivity.setTabBackgroud(travelPrivateCustomActivity.mImg_theme, travelPrivateCustomActivity.mTv_theme, R.drawable.theme, R.color.lxb_color);
                break;
        }
    }

    private List<TravelGvData> getData() {
        List<TravelGvData> travelGvDatas = new ArrayList<TravelGvData>();
        travelGvDatas.add(new TravelGvData(R.color.grayfont, true, "北京"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "三亚"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "云南"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "桂林"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "香港"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "深圳"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "台湾"));
        travelGvDatas.add(new TravelGvData(R.color.grayfont, false, "澳门"));
        return travelGvDatas;
    }

    private int getCheckNum(List<TravelGvData> travelList){
        int num = 0;
        for (int i = 0; i < travelList.size(); i++) {
            if(travelGvDatas.get(i).isCheck())
                num++;
        }
        return num;
    }

    private List<TravelGvData> clearCheck(List<TravelGvData> travelList){
        for (int i = 0; i < travelList.size(); i++) {
            travelList.get(i).setCheck(false);
        }
        return travelList;
    }
}
