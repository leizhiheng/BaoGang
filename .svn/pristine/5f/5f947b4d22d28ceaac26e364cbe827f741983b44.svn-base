package com.baosteel.qcsh.ui.activity.home.tongue;

import android.os.Bundle;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Business;
import com.baosteel.qcsh.ui.adapter.BusinessSelectAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/9/2.
 */
public class BusinessSelectActivity extends BaseActivity {
    private TitleBar mTitle_Bar;
    private ListView mLv_business_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_select);
        initViews();
    }

    private void initViews() {
        mTitle_Bar = (TitleBar)findViewById(R.id.title_bar);
        mTitle_Bar.setTitle("选择商家");
        mLv_business_select = (ListView) findViewById(R.id.lv_business_select);
        mLv_business_select.setAdapter(new BusinessSelectAdapter(getBaseContext(),getData()));

    }

    private List<Business> getData(){
        List<Business> list = new ArrayList<Business>();
        list.add(new Business("上海市清浦区朱家角驰嘉店", "上海市清浦区沈砖公路750号","特级",1000,76));
        list.add(new Business("上海市清浦区朱家角驰嘉店", "上海市清浦区沈砖公路750号","特级",1000,76));
        list.add(new Business("上海市清浦区朱家角驰嘉店", "上海市清浦区沈砖公路750号","特级",1000,76));
        list.add(new Business("上海市清浦区朱家角驰嘉店", "上海市清浦区沈砖公路750号","特级",1000,76));
        list.add(new Business("上海市清浦区朱家角驰嘉店", "上海市清浦区沈砖公路750号","特级",1000,76));
        list.add(new Business("上海市清浦区朱家角驰嘉店", "上海市清浦区沈砖公路750号","特级",1000,76));
        list.add(new Business("上海市清浦区朱家角驰嘉店", "上海市清浦区沈砖公路750号","特级",1000,76));
        return list;
    }

}
