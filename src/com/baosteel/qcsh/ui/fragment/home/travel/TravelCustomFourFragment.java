package com.baosteel.qcsh.ui.fragment.home.travel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.activity.home.travel.custom.TravelPrivateCustomActivity;
import com.common.base.BaseFragment;

/**
 * Created by jws on 2015/9/9.
 */
public class TravelCustomFourFragment extends BaseFragment implements View.OnClickListener{
    private Button mBtn_travel_custom_next;
    private Button mBtn_travel_custom_last;
    private TravelPrivateCustomActivity travelPrivateCustomActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_custom_four, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews(){
        mBtn_travel_custom_next = (Button)findViewById(R.id.btn_travel_custom_next);
        mBtn_travel_custom_last = (Button)findViewById(R.id.btn_travel_custom_last);
        mBtn_travel_custom_last.setOnClickListener(this);
        mBtn_travel_custom_next.setOnClickListener(this);
        travelPrivateCustomActivity = (TravelPrivateCustomActivity)getActivity();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_travel_custom_next:
                travelPrivateCustomActivity.tabAdapter.checkedIndex(4);
                travelPrivateCustomActivity.setTabBackgroud(travelPrivateCustomActivity.mImg_complete, travelPrivateCustomActivity.mTv_complete, R.drawable.complete,R.color.lxb_color);
                break;
            case R.id.btn_travel_custom_last:
                travelPrivateCustomActivity.tabAdapter.checkedIndex(2);
                travelPrivateCustomActivity.setTabBackgroud(travelPrivateCustomActivity.mImg_budget, travelPrivateCustomActivity.mTv_budget, R.drawable.budget_not, R.color.gr_l_color);
                break;
        }
    }
}
