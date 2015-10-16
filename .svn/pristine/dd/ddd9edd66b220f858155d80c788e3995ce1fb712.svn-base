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
import com.baosteel.qcsh.ui.activity.common.SingleSelectActivity;
import com.baosteel.qcsh.ui.fragment.commen.SingleSelectFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：乐居宝->我是房东（我是房客）->发布租售信息
 * @author blue
 * @Time 2015-9-8
 *
 */
public class ReleaseHouseMsgActivity extends BaseActivity{
	
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
		setTitle("发布租售信息");
		
		mSingleSelectFragment = (SingleSelectFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_single_select);
		mSingleSelectFragment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = null;
				if (mDatas.get(position).getId() == 5) {//厂房/仓库/土地
					intent =  new Intent(ReleaseHouseMsgActivity.this, FactoryStorehouseLandActivity.class);
				} else if (mDatas.get(position).getId() == 6) {//写字楼租售
					intent = new Intent(ReleaseHouseMsgActivity.this, OfficeBuildingRentActivity.class);  
				} else if (mDatas.get(position).getId() == 7) {
					intent = new Intent(ReleaseHouseMsgActivity.this, StoreRentActivity.class);
				} else {
					intent = new Intent(ReleaseHouseMsgActivity.this, ReleaseHouseActivity.class);
					intent.putExtra(EXTRA_KEY_TITLE, mDatas.get(position).getTitle());
					intent.putExtra(ReleaseHouseActivity.EXTRA_FRAGMENT_INDEX, mDatas.get(position).getId());
				}
				startActivity(intent);
			}
		});
	
	}
	
	private void loadData() {
		mDatas.add(new SingleSelectItemData("整租房", 0));
		mDatas.add(new SingleSelectItemData("合租房", 1));
		mDatas.add(new SingleSelectItemData("求租房", 2));
		mDatas.add(new SingleSelectItemData("二手房出售", 3));
		mDatas.add(new SingleSelectItemData("二手房求购", 4));
		mDatas.add(new SingleSelectItemData("厂房/仓库/土地", 5));
		mDatas.add(new SingleSelectItemData("写字楼租售", 6));
		mDatas.add(new SingleSelectItemData("商铺租售", 7));
		
		mSingleSelectFragment.setDatas(mDatas);
	}
}
