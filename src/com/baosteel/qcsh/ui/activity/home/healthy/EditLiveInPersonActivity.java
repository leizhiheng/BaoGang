package com.baosteel.qcsh.ui.activity.home.healthy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HealthyContactPerson;
import com.common.base.BaseActivity;

public class EditLiveInPersonActivity extends BaseActivity {

	public static final String EXTRA_KEY_PERSON = "key.person";
	public static final String EXTRA_KEY_PERSON_POSITION = "key.person.position";
	
	private HealthyContactPerson mContactPerson;
	private int mEditPosition;
	
	private EditText mEtName;
	private EditText mEtPhone;
	private EditText mEtIdentityCode;
	private RadioGroup mRadioGroup;
	private RadioButton mBtnSexBoy;
	private RadioButton mBtnSexGirl;
	
	private String sex = "男";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_live_in_person);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mContactPerson = (HealthyContactPerson) getIntent().getSerializableExtra(EXTRA_KEY_PERSON);
		mEditPosition = getIntent().getIntExtra(EXTRA_KEY_PERSON_POSITION, -1);
	}
	
	private void initView() {
		
		setTitle("选择联系人");
		mTitleBar.setBackgroud(R.color.theme_color_healthy);
		mTitleBar.showRightText("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mContactPerson = new HealthyContactPerson(mEtName.getText().toString(),sex, 
						mEtPhone.getText().toString(), mEtIdentityCode.getText().toString());
				Intent intent = new Intent();
				intent.putExtra(EXTRA_KEY_TITLE, mContactPerson);
				/*
				 * 如果是编辑联系人，则将传递过来的联系人position再次传递回去
				 */
				if (mEditPosition >= 0) {
					intent.putExtra(EXTRA_KEY_PERSON_POSITION, mEditPosition);
					
				}
				setResult(RESULT_OK, intent);
				
				finish();
			}
		});
		
		mEtName = (EditText) findViewById(R.id.et_contact_name);
		mEtPhone = (EditText) findViewById(R.id.et_contact_phone);
		mEtIdentityCode = (EditText) findViewById(R.id.et_contact_identity_code);
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_sex);
		mBtnSexBoy = (RadioButton) findViewById(R.id.btn_sex_boy);
		mBtnSexGirl = (RadioButton) findViewById(R.id.btn_sex_girl);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.btn_sex_boy) {
					sex = "男";
				} else {
					sex = "女";
				}
			}
		});
		
		if (mContactPerson != null) {
			mEtName.setText(mContactPerson.getName());
			mEtPhone.setText(mContactPerson.getPhone());
			mEtIdentityCode.setText(mContactPerson.getIdentiryCode());
			if (mContactPerson.getSex().equals("男")) {
				mBtnSexBoy.setChecked(true);
			} else {
				mBtnSexGirl.setChecked(true);
			}
		}
	}
	
	private void loadData() {
		
	}
}
