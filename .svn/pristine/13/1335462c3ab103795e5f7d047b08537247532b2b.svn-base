package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.utils.LogUtil;

/**
 * 
 * @description 页面 ：乐居宝->装修预定
 * @author blue
 * @Time 2015-9-6
 *
 */
public class DecorationReserveActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "DecorationReserveActivity";
	
	public static final String EXTRA_CHOOSE_RESULT = "choose.result";
	
	private TitleBar mTitleBar;
	
	private RadioGroup mRadioGroup;
	private RadioButton mRadioBtnNew;
	private RadioButton mRadioBtnOld;
	
	/**选择户型*/
	private View mViewChooseHouseType;
	
	/**选择装修风格*/
	private View mViewChooseDecorationStyle;
	
	/**获取验证码*/
	private TextView mBtnGetCheckCode;
	
	/**提交*/
	private TextView mBtnCommit;
	
	private TextView mTvHouseType;
	private TextView mTvDecorationStyle;
	
	public static final int REQUEST_CODE_DECORATION_STYLE = 0;
	public static final int REQUEST_CODE_HOUSE_TYPE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decoration_reserve);
		initView();
	}
	
	private void initView() {
		LogUtil.d(TAG," == >initView");
		setTitle(R.string.title_decration_reserve);
		
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
		mRadioBtnNew = (RadioButton) findViewById(R.id.btn_new);
		mRadioBtnNew.setChecked(true);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.btn_new) {
					showToast("Right button has been checked");
				} else {
					showToast("Left button has been checked");
				}
			}
		});
		
		
		mBtnCommit = (TextView) findViewById(R.id.btn_commit);
		mViewChooseDecorationStyle = findViewById(R.id.layout_choose_decoration_style);
		mViewChooseHouseType = findViewById(R.id.layout_choose_house_type);
		mBtnGetCheckCode = (TextView) findViewById(R.id.btn_get_check_code);
		mTvDecorationStyle = (TextView) findViewById(R.id.tv_decoration_style);
		mTvHouseType = (TextView) findViewById(R.id.tv_house_type);
		
		mViewChooseDecorationStyle.setOnClickListener(this);
		mViewChooseHouseType.setOnClickListener(this);
		mBtnGetCheckCode.setOnClickListener(this);
		mBtnCommit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_choose_decoration_style:
			intent = new Intent(this, ChooseHouseTypeActivity.class);
			intent.putExtra(ChooseHouseTypeActivity.EXTRA_KEY_TITLE, ChooseHouseTypeActivity.TITLE_DECORATION_STYLE);
			startActivityForResult(intent, REQUEST_CODE_DECORATION_STYLE);
			break;
		case R.id.layout_choose_house_type:
			intent = new Intent(this, ChooseHouseTypeActivity.class);
			intent.putExtra(ChooseHouseTypeActivity.EXTRA_KEY_TITLE, ChooseHouseTypeActivity.TITLE_HOUSE_TYLE);
			startActivityForResult(intent, REQUEST_CODE_HOUSE_TYPE);
			break;
		case R.id.btn_get_check_code:
			showToast("get check code");
			break;
		case R.id.btn_commit:
			showToast("Btn commit has been clicked");
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if (arg2 == null) {
			return;
		}
		
		String result = arg2.getStringExtra(EXTRA_CHOOSE_RESULT);
		//arg1 是 resultCode
		if (arg1 == REQUEST_CODE_DECORATION_STYLE) {
			mTvDecorationStyle.setTextColor(getResources().getColor(R.color.black));
			mTvDecorationStyle.setText(result);
		} else if (arg1 == REQUEST_CODE_HOUSE_TYPE) {
			mTvHouseType.setTextColor(getResources().getColor(R.color.black));
			mTvHouseType.setText(result);
		}
	}
}
