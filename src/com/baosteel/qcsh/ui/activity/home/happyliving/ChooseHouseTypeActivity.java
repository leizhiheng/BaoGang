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

/**
 * 
 * @description 页面：乐居宝->装修预定->选择户型（和装修风格公用）
 * @author blue
 * @Time 2015-9-8
 *
 */
public class ChooseHouseTypeActivity extends BaseActivity {

	private TitleBar mTitleBar;

	private SingleSelectFragment mSingleSelectFragment;

	private List<SingleSelectItemData> mDatas;
	
	public static final String EXTRA_KEY_TITLE = "title_key";
	public static final String TITLE_DECORATION_STYLE = "装修风格";
	public static final String TITLE_HOUSE_TYLE = "户型选择";
	
	private String mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release_house_msg);

		initData();
		initView();
		
		loadData();
	}

	private void initData() {
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
		mDatas = new ArrayList<SingleSelectItemData>();
	}

	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle(mTitle);

		mSingleSelectFragment = (SingleSelectFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_single_select);
		mSingleSelectFragment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mDatas.get(position).setSelected(true);
				mSingleSelectFragment.notifyDataSetChanged();
				
				Intent intent = new Intent();
				intent.putExtra(DecorationReserveActivity.EXTRA_CHOOSE_RESULT, mDatas.get(position).getTitle());
				if (TITLE_DECORATION_STYLE.equals(mTitle)) {
					setResult(DecorationReserveActivity.REQUEST_CODE_DECORATION_STYLE, intent);
				} else if (TITLE_HOUSE_TYLE.equals(mTitle)) {
					setResult(DecorationReserveActivity.REQUEST_CODE_HOUSE_TYPE, intent);
				}
				
				finish();
			}
		});
	}

	private void loadData() {
		
		if (TITLE_HOUSE_TYLE.equals(mTitle)) {
			
			mDatas.add(new SingleSelectItemData("小户型", 0));
			mDatas.add(new SingleSelectItemData("两房", 0));
			mDatas.add(new SingleSelectItemData("三房", 0));
			mDatas.add(new SingleSelectItemData("复式", 0));
			mDatas.add(new SingleSelectItemData("办公室", 0));
			mDatas.add(new SingleSelectItemData("别墅", 0));
			mDatas.add(new SingleSelectItemData("商业装修", 0));
			
		} else if (TITLE_DECORATION_STYLE.equals(mTitle)) {
			
			mDatas.add(new SingleSelectItemData("简约", 0));
			mDatas.add(new SingleSelectItemData("现代", 0));
			mDatas.add(new SingleSelectItemData("简欧", 0));
			mDatas.add(new SingleSelectItemData("田园", 0));
			mDatas.add(new SingleSelectItemData("混搭", 0));
			mDatas.add(new SingleSelectItemData("地中海", 0));
			mDatas.add(new SingleSelectItemData("古典", 0));
			
		}
		mDatas.get(0).setSelected(true);

		mSingleSelectFragment.setDatas(mDatas);
	}
}
