package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.fragment.commen.ScrollLayoutFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;

/**
 * 
 * @description 页面：乐居宝->便捷缴费
 * @author blue
 * @Time 2015-9-6
 * 
 */
public class NimblePaymentActivity extends BaseActivity {

	private ScrollLayoutFragment mScrollLayoutFragment;

	/** 快速导航数据 **/
	private List<FastLinkData> mFastList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nimble_payment);

		initData();
		initView();
		loadData();
	}

	private void initData() {
		mFastList = new ArrayList<FastLinkData>();
	}

	private void initView() {
		setTitle(R.string.title_nimble_payment);
		mTitleBar.setBackgroud(R.color.title_bar_bg_voilet);
		mTitleBar.showRightIcon(TitleBar.ICON_INDEX_MENU);
		
		mScrollLayoutFragment = (ScrollLayoutFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_linkList);
		mScrollLayoutFragment.setPageSize(6);
		mScrollLayoutFragment
				.setFastLickItemClickListener(new BackItemClickListener() {

					@Override
					public void onFastLinkItemClick(FastLinkData bean) {

						if (bean.getId() == 5) {
							startActivity(new Intent(NimblePaymentActivity.this,NimblePaymentPropertyActivity.class));
						} else {
							Intent intent = new Intent(NimblePaymentActivity.this, NimblePaymentEntryActivity.class);
							int index = 0;
							String title = bean.getName();
							switch (bean.getId()) {
							case 0:
								index = NimblePaymentEntryActivity.INDEX_POWER_COST;
								break;
							case 1:
								index = NimblePaymentEntryActivity.INDEX_WATER_COST;
								break;
							case 2:
								index = NimblePaymentEntryActivity.INDEX_GAS_COST;
								break;
							case 3:
								index = NimblePaymentEntryActivity.INDEX_BROADBAND_COST;
								break;
							case 4:
								index = NimblePaymentEntryActivity.INDEX_TV_COST;
								break;
							default:
								break;
							}
							
							intent.putExtra(NimblePaymentEntryActivity.EXTRA_KEY_INDEX, index);
							intent.putExtra(NimblePaymentEntryActivity.EXTRA_KEY_TITLE, title);
							startActivity(intent);
						}
					}
				});
	}

	private void loadData() {
		mFastList.add(new FastLinkData(0, "电费", R.drawable.ic_happling_dianfei));
		mFastList.add(new FastLinkData(1, "水费", R.drawable.ic_happling_shuifei));
		mFastList.add(new FastLinkData(2, "燃气费", R.drawable.ic_happling_pay_gas));
		mFastList.add(new FastLinkData(3, "固话宽带", R.drawable.ic_happling_pay_broad));
		mFastList.add(new FastLinkData(4, "有线电视", R.drawable.ic_happling_pay_line_tv));
		mFastList.add(new FastLinkData(5, "物业费", R.drawable.ic_happling_pay_property));
		mScrollLayoutFragment.setData(mFastList);
	}
}
