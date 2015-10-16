package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.common.base.BaseCameraFragment;

/**
 * 提醒详情
 * Created by kuangyong on 15/9/1.
 */
public class RemindDetailsFragment extends BaseCameraFragment implements View.OnClickListener{

    private TextView btn_complete;//完成
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_remind_detials, null);
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
        btn_complete= (TextView) findViewById(R.id.btn_complete);
    }

    private void setListener() {
        btn_complete.setOnClickListener(this);
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_complete://完成
                if(null!=listener){
                    listener.complete();
                }
                break;
        }
    }

    public interface OnCompleteListener {
        void complete();
    }

    /**
     * 完成的回调
     */
    private OnCompleteListener listener;

    public void setOnCompleteListener(OnCompleteListener listener){
        this.listener=listener;
    }
}
