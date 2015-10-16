package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.AppraiseListAdapter;
import com.baosteel.qcsh.ui.adapter.SimpleListAdapter;
import com.common.base.BaseFragment;
import com.common.utils.DialogUtil;
import com.common.view.listview.MyListView;

/**
 * 保养提醒
 * Created by kuangyong on 15/9/1.
 */
public class MainTainBYTXFragment extends BaseFragment implements View.OnClickListener{

    private TextView tvtime;//上次保养时间
    private LinearLayout layouttime;
    private TextView tvprelicheng;//上次保养的历程
    private TextView tvcurlicheng;//当前保养里程
    private TextView tvremindtime;//提醒时间
    private LinearLayout layoutremindtime;
    private TextView tvneedtime;//提醒项目的时间
    private TextView tvneedtype;//提醒项目
    private com.common.view.listview.MyListView lvtip;//提醒列表
    private LinearLayout layoutexpend;//隐藏层
    private TextView btncontinueorhighmaintain;//继续/高级提醒设置按钮
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_maintain_bytx, null);
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
        this.btncontinueorhighmaintain = (TextView) findViewById(R.id.btn_continue_or_highmaintain);
        this.layoutexpend = (LinearLayout) findViewById(R.id.layout_expend);
        this.lvtip = (MyListView) findViewById(R.id.lv_tip);
        this.tvneedtype = (TextView) findViewById(R.id.tv_need_type);
        this.tvneedtime = (TextView) findViewById(R.id.tv_needtime);
        this.layoutremindtime = (LinearLayout) findViewById(R.id.layout_remind_time);
        this.tvremindtime = (TextView) findViewById(R.id.tv_remind_time);
        this.tvcurlicheng = (TextView) findViewById(R.id.tv_cur_licheng);
        this.tvprelicheng = (TextView) findViewById(R.id.tv_pre_licheng);
        this.layouttime = (LinearLayout) findViewById(R.id.layout_time);
        this.tvtime = (TextView) findViewById(R.id.tv_pre_time);
    }

    private void setListener() {
        lvtip.setAdapter(new SimpleListAdapter(mContext));
        layouttime.setOnClickListener(this);
        btncontinueorhighmaintain.setOnClickListener(this);
    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_time://上次保养时间
                DialogUtil.showBirthdayDialog(tvtime, mContext);
                break;
            case R.id.btn_continue_or_highmaintain://继续/高级提醒设置按钮
                if(View.GONE==layoutexpend.getVisibility()){
                    layoutexpend.setVisibility(View.VISIBLE);
                    lvtip.setAdapter(new SimpleListAdapter(mContext));
                    btncontinueorhighmaintain.setBackgroundResource(R.drawable.common_radius_orange_red);
                    btncontinueorhighmaintain.setText("高级提醒设置");
                }else{
                    startActivity(new Intent(mContext,HighRemindSettingActivity.class));
                }
                break;
        }
    }
}
