package com.baosteel.qcsh.ui.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CashType;
import com.baosteel.qcsh.ui.adapter.CashTypeAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/9/11.
 */
public class CashVolumeActivity extends BaseActivity {
    private ListView mLv_cash_volume_detail;
    private TitleBar mTitle_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_volume);
        initViews();
        initData();


    }

    private void initViews() {
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
        mLv_cash_volume_detail = (ListView) findViewById(R.id.lv_cash_volume_detail);
    }

    private void initData() {
        mLv_cash_volume_detail.setAdapter(new CashTypeAdapter(getBaseContext(), getData()));
        mLv_cash_volume_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(mContext,CashVolumeListActivity.class));
            }
        });
        mTitle_bar.setTitle("现金抵扣卷");
        mTitle_bar.setBackgroud(R.color.index_red);
    }

    private List<CashType> getData() {
        List<CashType> list = new ArrayList<CashType>();
        list.add(new CashType("樱花谜语", "满200减20"));
        list.add(new CashType("天猫旗舰店", "满200减20"));
        list.add(new CashType("七彩生活平台现金", "满200减20"));
        return list;
    }
}
