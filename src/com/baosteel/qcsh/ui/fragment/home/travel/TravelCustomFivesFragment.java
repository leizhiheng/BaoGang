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
 * Created by lenovo on 2015/9/9.
 */
public class TravelCustomFivesFragment extends BaseFragment  implements View.OnClickListener{
    private TravelPrivateCustomActivity travelPrivateCustomActivity;
    private Button mBtn_travel_custom_last;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_custom_five, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews(){
        mBtn_travel_custom_last = (Button)findViewById(R.id.btn_travel_custom_last);
        mBtn_travel_custom_last.setOnClickListener(this);
        travelPrivateCustomActivity = (TravelPrivateCustomActivity)getActivity();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_travel_custom_last:
                travelPrivateCustomActivity.tabAdapter.checkedIndex(3);
                travelPrivateCustomActivity.setTabBackgroud(travelPrivateCustomActivity.mImg_complete, travelPrivateCustomActivity.mTv_complete, R.drawable.complete_not,R.color.gr_l_color);
                break;
        }
    }

}
