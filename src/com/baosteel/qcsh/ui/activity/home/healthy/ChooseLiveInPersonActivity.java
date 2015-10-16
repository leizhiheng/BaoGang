package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HealthyContactPerson;
import com.baosteel.qcsh.ui.adapter.ChooseContactPersonAdapter;
import com.baosteel.qcsh.ui.adapter.ChooseLiveInPersonAdapter;
import com.baosteel.qcsh.ui.adapter.ChooseLiveInPersonAdapter.OnClickEditListener;
import com.common.base.BaseActivity;

public class ChooseLiveInPersonActivity extends BaseActivity implements OnClickListener{

	/**
	 * 用于传递人员信息类型
	 */
	public static final String EXTRA_PERSON_TYPE = "person.type";
	public static final int PERSON_TYPE_PHYSICAL_EXAM= 0x10;//体检人
	public static final int PERSON_TYPE_LIVE_IN= 0x11;//入住人员
	
	public static final int REQUST_CODE_EDIT = 0;
	public static final int REQUST_CODE_ADD = 1;
	
	private ListView mListView;
	private List<HealthyContactPerson> mDatas;
	private ChooseLiveInPersonAdapter mAdapter;
	
	private TextView mTvPersonType;
	
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
		mPersonType = getIntent().getIntExtra(EXTRA_PERSON_TYPE, PERSON_TYPE_PHYSICAL_EXAM);
		
		mDatas = new ArrayList<HealthyContactPerson>();
		mAdapter = new ChooseLiveInPersonAdapter(this, mDatas);
		mAdapter.setOnClickEditListener(new OnClickEditListener() {
			
			@Override
			public void onEdit(int position) {
				Intent intent = new Intent(ChooseLiveInPersonActivity.this, EditLiveInPersonActivity.class);
				intent.putExtra(EditLiveInPersonActivity.EXTRA_KEY_PERSON, mDatas.get(position));
				intent.putExtra(EditLiveInPersonActivity.EXTRA_KEY_PERSON_POSITION, position);
				startActivityForResult(intent, REQUST_CODE_EDIT);
			}
		});
	}
	
	private void initView() {
		mTvPersonType = (TextView) findViewById(R.id.tv_person_type);
		if (mPersonType == PERSON_TYPE_PHYSICAL_EXAM) {
			setTitle("选择体检人");
			mTvPersonType.setText("新增体检人员");
		} else if (mPersonType == PERSON_TYPE_LIVE_IN) {
			setTitle("选择入住人");
			mTvPersonType.setText("新增入住人员");
		}
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
			HealthyContactPerson person = new HealthyContactPerson("刘洋", "男","15112154845", "43315548854554125478");
			
			mDatas.add(person);
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

