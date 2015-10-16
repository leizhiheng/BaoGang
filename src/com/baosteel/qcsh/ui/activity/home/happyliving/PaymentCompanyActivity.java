package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.SingleSelectItemData;
import com.baosteel.qcsh.ui.fragment.commen.SingleSelectFragment;
import com.common.base.BaseActivity;

public class PaymentCompanyActivity extends BaseActivity{

	private SingleSelectFragment mSingleSelectFragment;
	
	private List<SingleSelectItemData> mDatas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commen_container_layout);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mSingleSelectFragment = new SingleSelectFragment();
		mDatas = new ArrayList<SingleSelectItemData>();
		mSingleSelectFragment.setDatas(mDatas);
		
		mSingleSelectFragment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mDatas.get(position).setSelected(true);
				Intent intent = new Intent();
				intent.putExtra(EXTRA_KEY_RESULT, mDatas.get(position).getTitle());
				setResult(RESULT_OK, intent);
				mSingleSelectFragment.notifyDataSetChanged();
				PaymentCompanyActivity.this.finish();
			}
		});
	}
	
	private void initView() {
		setTitle("缴费单位");
		getSupportFragmentManager().beginTransaction().replace(R.id.container, mSingleSelectFragment).commit();
	}
	
	private void loadData() {
		mDatas.add(new SingleSelectItemData("上海市电力公司", 0));
		mDatas.add(new SingleSelectItemData("上海市电力公司(银行代收)", 0));
		mDatas.add(new SingleSelectItemData("上海市电气电力公司", 0));
		mDatas.get(0).setSelected(true);
		mSingleSelectFragment.notifyDataSetChanged();
	}
}
