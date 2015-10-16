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
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

public class StoreRentActivity extends BaseActivity{

	private TitleBar mTitleBar;
	
	private SingleSelectFragment mSingleSelectFragment;
	
	private List<SingleSelectItemData> mDatas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release_house_msg);
		
		initData();
		initView();
		loadData();
	}
	
	private void initData() {
		mDatas = new ArrayList<SingleSelectItemData>();
	}
	
	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("商铺");
		
		mSingleSelectFragment = (SingleSelectFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_single_select);
		mSingleSelectFragment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(StoreRentActivity.this, ReleaseHouseActivity.class);
				intent.putExtra(EXTRA_KEY_TITLE, "商铺"+mDatas.get(position).getTitle());
				intent.putExtra(ReleaseHouseActivity.EXTRA_FRAGMENT_INDEX, mDatas.get(position).getId());
				startActivity(intent);
			}
		});
	
	}
	
	private void loadData() {
		mDatas.add(new SingleSelectItemData("出租", 20));
		mDatas.add(new SingleSelectItemData("求租", 21));
		mDatas.add(new SingleSelectItemData("出售", 23));
		mDatas.add(new SingleSelectItemData("求购", 24));
		
		mSingleSelectFragment.setDatas(mDatas);
	}
}