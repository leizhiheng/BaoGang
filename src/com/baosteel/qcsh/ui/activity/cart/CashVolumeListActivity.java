package com.baosteel.qcsh.ui.activity.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CashList;
import com.baosteel.qcsh.ui.adapter.CashListAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/9/11.
 */
public class CashVolumeListActivity extends BaseActivity {
    private ListView mLv_use_cash;
    private ListView mLv_no_use_cash;
    private TitleBar mTitle_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_volume_list);
        initViews();
        initData();
    }

    private void initViews() {
        mLv_use_cash = (ListView) findViewById(R.id.lv_use_cash);
        mLv_no_use_cash = (ListView) findViewById(R.id.lv_no_use_cash);
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
    }

    private void initData() {
        mLv_use_cash.setAdapter(new CashListAdapter(mContext, getUseData(), true));
        mLv_no_use_cash.setAdapter(new CashListAdapter(mContext, getNoUseData(), false));
        mTitle_bar.setTitle("现金卷");
        mTitle_bar.setBackgroud(R.color.index_red);
    }

    private List<CashList> getNoUseData() {
        List<CashList> list = new ArrayList<CashList>();
        list.add(new CashList("60", "店铺现金卷 全场通用", "订单满100.00元使用（不含邮费）", "2015.06.10-2015.07.10"));
        list.add(new CashList("60", "店铺现金卷 全场通用", "订单满100.00元使用（不含邮费）", "2015.06.10-2015.07.10"));
        return list;
    }

    private List<CashList> getUseData() {
        List<CashList> list = new ArrayList<CashList>();
        list.add(new CashList("20", "店铺现金卷 全场通用", "订单满100.00元使用（不含邮费）", "2015.06.10-2015.07.10"));
        return list;
    }
}
