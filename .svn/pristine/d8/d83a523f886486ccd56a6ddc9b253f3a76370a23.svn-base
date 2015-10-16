package com.baosteel.qcsh.ui.activity.home.healthy;

import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HealthyContactPerson;
import com.common.base.BaseActivity;

public class EditContactPersonActivity extends BaseActivity{

	public static final String EXTRA_KEY_PERSON = "key.person";
	public static final String EXTRA_KEY_PERSON_POSITION = "key.person.position";
	
	private HealthyContactPerson mContactPerson;
	private int mEditPosition;
	
	private EditText mEtName;
	private EditText mEtPhone;
	private EditText mEtEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact_person);
		
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
				mContactPerson = new HealthyContactPerson(mEtName.getText().toString(),
						mEtPhone.getText().toString(), mEtEmail.getText().toString());
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
		mEtEmail = (EditText) findViewById(R.id.et_contact_email);
		
		if (mContactPerson != null) {
			mEtName.setText(mContactPerson.getName());
			mEtPhone.setText(mContactPerson.getPhone());
			mEtEmail.setText(mContactPerson.getEmail());
		}
	}
	
	private void loadData() {
		
	}
}
