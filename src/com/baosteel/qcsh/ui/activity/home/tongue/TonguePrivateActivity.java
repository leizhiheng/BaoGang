package com.baosteel.qcsh.ui.activity.home.tongue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * Created by jws on 2015/9/1.
 */
public class TonguePrivateActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mBanquetType;
    private RelativeLayout mBusinessSelect;
    private TitleBar mTitle_Bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongue_custom);
        mBanquetType = (RelativeLayout)findViewById(R.id.rela_banquet_type);
        mBusinessSelect = (RelativeLayout)findViewById(R.id.rela_business_select);
        mBanquetType.setOnClickListener(this);
        mBusinessSelect.setOnClickListener(this);
        mTitle_Bar = (TitleBar)findViewById(R.id.title_bar);
        mTitle_Bar.setTitle(getStringExtra("私人定制"));
        mTitle_Bar.setBackgroud(R.color.sjb_color);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rela_banquet_type:
                startActivity(new Intent(getBaseContext(),BanquetTypeActivity.class));
                break;
            case R.id.rela_business_select:
                startActivity(new Intent(getBaseContext(),BusinessSelectActivity.class));
                break;
        }
    }
}
