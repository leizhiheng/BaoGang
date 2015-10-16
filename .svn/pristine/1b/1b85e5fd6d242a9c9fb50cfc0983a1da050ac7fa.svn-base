package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseFragment;

/**
 * 保养提醒
 * Created by kuangyong on 15/9/1.
 */
public class MainTainBYDAFragment extends BaseFragment implements View.OnClickListener{

    private TextView btn_add;//立即添加按钮
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_maintain_byda, null);
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
        btn_add= (TextView) findViewById(R.id.btn_add);
    }

    private void setListener() {
        btn_add.setOnClickListener(this);
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_add://立即添加
                startActivity(new Intent(mContext,AddArchivesActivity.class));
                break;
        }
    }
}
