package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HealthyContactPerson;
import com.baosteel.qcsh.ui.adapter.ChooseContactPersonAdapter;
import com.baosteel.qcsh.ui.adapter.ChooseContactPersonAdapter.OnClickEditListener;
import com.common.base.BaseActivity;

public class ChooseContactPersonActivity extends BaseActivity implements OnClickListener{

	/**
	 * 用于传递人员信息类型
	 */
	public static final String EXTRA_PERSON_TYPE = "person.type";
	public static final int PERSON_TYPE_CONTACT= 0x10;//联系人
	public static final int PERSON_TYPE_LIVE_IN= 0x11;//入住人员
	
	public static final int REQUST_CODE_EDIT = 0;
	public static final int REQUST_CODE_ADD = 1;
	
	private ListView mListView;
	private List<HealthyContactPerson> mDatas;
	private ChooseContactPersonAdapter mAdapter;
	
	private View mBtnAddContact;
	/**
	 * 已选联系人的位置
	 */
	private int mSelectedIndex;
	private int mPersonType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_contact_person);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mPersonType = getIntent().getIntExtra(EXTRA_PERSON_TYPE, PERSON_TYPE_CONTACT);
		mDatas = new ArrayList<HealthyContactPerson>();
		mAdapter = new ChooseContactPersonAdapter(this, mDatas);
		mAdapter.setOnClickEditListener(new OnClickEditListener() {
			
			@Override
			public void onEdit(int position) {
				Intent intent = new Intent(ChooseContactPersonActivity.this, EditContactPersonActivity.class);
				intent.putExtra(EditContactPersonActivity.EXTRA_KEY_PERSON, mDatas.get(position));
				intent.putExtra(EditContactPersonActivity.EXTRA_KEY_PERSON_POSITION, position);
				startActivityForResult(intent, REQUST_CODE_EDIT);
			}
		});
	}
	
	private void initView() {
		
		setTitle("选择联系人");
		mTitleBar.setBackgroud(R.color.theme_color_healthy);
		mTitleBar.showRightText("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(EXTRA_KEY_TITLE, mAdapter.getSelectedPerson());
				setResult(RESULT_OK, intent);
				
				finish();
			}
		});
		
		mBtnAddContact = findViewById(R.id.layout_add_contact);
		mBtnAddContact.setOnClickListener(this);
		
		mListView = (ListView) findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);
	}
	
	private void loadData() {
		for (int i = 0; i < 3; i++) {
			mDatas.add(new HealthyContactPerson("曾小亚", "15315851526", "zengxiaoya@sina.com"));
		}
		mDatas.get(0).setSelected(true);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_add_contact:
			Intent intent = new Intent(this, EditContactPersonActivity.class);
			startActivityForResult(intent, REQUST_CODE_ADD);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUST_CODE_ADD && data != null) {
			/*
			 * 添加联系人结束
			 */
			HealthyContactPerson person = (HealthyContactPerson) data.getSerializableExtra(BaseActivity.EXTRA_KEY_TITLE);
			if (person != null) {
				mDatas.add(person);
			}
			
		} else if (requestCode == REQUST_CODE_EDIT && data != null) {
			/*
			 * 编辑联系人结束
			 */
			HealthyContactPerson person = (HealthyContactPerson) data.getSerializableExtra(BaseActivity.EXTRA_KEY_TITLE);
			int position = data.getIntExtra(EditContactPersonActivity.EXTRA_KEY_PERSON_POSITION, -1);
			if (position >= 0 && position < mDatas.size()) {
				boolean isSelected = mDatas.get(position).isSelected();
				person.setSelected(isSelected);
				mDatas.remove(position);
				mDatas.add(position, person);
			}
		}
		mAdapter.notifyDataSetChanged();
	}
}
