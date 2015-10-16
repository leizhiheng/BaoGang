package com.baosteel.qcsh.ui.activity.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.store.StoreDetailData;
import com.common.base.BaseActivity;
import com.common.utils.CodeUtil;
import com.common.utils.LogUtil;
import com.common.view.topbar.HeadView;

/**
 * 执照查询 Created by kuangyong on 15/9/22.
 */
public class LicencseQueryActivity extends BaseActivity implements View.OnClickListener{

	private com.common.view.topbar.HeadView headview;//标题栏
	private android.widget.EditText etcode;//二维码输入框
	private android.widget.ImageView ivcode;//二维码图片
	private android.widget.TextView btnnext;//切换二维码
	private android.widget.TextView btnquery;//确定按钮
	
	private StoreDetailData mStoreDetailData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_license_query);
		
		mStoreDetailData = (StoreDetailData) getIntent().getSerializableExtra(StoreDetailsActivity.EXTRA_STORE_DETAIL);
		
		findView();
		setListener();
		initView();
	}

	private void findView() {
        this.btnquery = (TextView) findViewById(R.id.btn_query);
        this.btnnext = (TextView) findViewById(R.id.btn_next);
        this.ivcode = (ImageView) findViewById(R.id.iv_code);
        this.etcode = (EditText) findViewById(R.id.et_code);
        this.headview = (HeadView) findViewById(R.id.headview);
	}

	private void setListener() {
        btnnext.setOnClickListener(this);
        btnquery.setOnClickListener(this);
    }


    private void initView() {
        headview.setTitle("查询执照");
        headview.setRightImageBtnBackGround(R.drawable.icon_3point);
        headview.showRightImageBtn(View.VISIBLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("菜单");
            }
        });
        headview.setHeadViewBackGroundColor(getResources().getColor(R.color.red_baosteel));
        ivcode.setImageBitmap(CodeUtil.getInstance().createBitmap(1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next://换一张
                ivcode.setImageBitmap(CodeUtil.getInstance().createBitmap(1));
                break;
            case R.id.btn_query://查询
                LogUtil.d("输入码",etcode.getText().toString());
                LogUtil.d("验证码",CodeUtil.getInstance().getCode());
                if("".equals(etcode.getText().toString())){
                    showToast("请输入验证码！");
                    return ;
                }else if(!etcode.getText().toString().equals(CodeUtil.getInstance().getCode())){
                    showToast("验证码不正确！");
                    return ;
                }
                Intent intent = new Intent(mContext,LicencseQueryResultActivity.class);
                intent.putExtra(StoreDetailsActivity.EXTRA_STORE_DETAIL, mStoreDetailData);
                startActivity(intent);
                break;
        }
    }
}