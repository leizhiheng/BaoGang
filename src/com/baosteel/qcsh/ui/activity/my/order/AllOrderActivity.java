package com.baosteel.qcsh.ui.activity.my.order;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.fragment.myorder.EntityAndServiceOrderFragment;
import com.baosteel.qcsh.ui.fragment.myorder.OtherOrderFragment;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：我的-》全部订单
 * @author blue
 * @Time 2015-9-22
 *
 */
public class AllOrderActivity extends BaseActivity{

	private RadioGroup mRadioGroup;
	private RadioButton mBtnEntityServiceOrder;
	
	private FragmentManager fragmentManager;
	
	/**实物/服务订单**/
	private EntityAndServiceOrderFragment entityAndServiceOrderFragment;
	
	/**其他订单**/
	private OtherOrderFragment mOtherOrderFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_all_my_order);
		
		initData();
		initView();
		
		loadData();
		
	}
	
	private void initData() {
		fragmentManager = getSupportFragmentManager();
		entityAndServiceOrderFragment = new EntityAndServiceOrderFragment();
		mOtherOrderFragment = new OtherOrderFragment();
		fragmentManager.beginTransaction().add(R.id.container, entityAndServiceOrderFragment).commit();
		fragmentManager.beginTransaction().add(R.id.container, mOtherOrderFragment).commit();
		fragmentManager.beginTransaction().hide(mOtherOrderFragment).commit();
	}
	
	private void initView() {
		setTitle("我的订单");
		mTitleBar.setBackgroud(R.color.theme_color_order);
		
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
		mBtnEntityServiceOrder = (RadioButton) findViewById(R.id.btn_entity_service_order);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.btn_entity_service_order) {
					showEntityOrderFragment();
				} else if (checkedId == R.id.btn_other_order) {
					showOtherOrderFragment();
				}
				
			}
		});
		//mBtnEntityServiceOrder.setChecked(true);
	}
	
	private void loadData() {
		
	}
	
	/**
	 * 
	 * @Description 显示实物订单Fragment
	 * @Author blue
	 * @Time 2015-9-22
	 */
	private void showEntityOrderFragment() {
		fragmentManager.beginTransaction().hide(mOtherOrderFragment).commit();
		fragmentManager.beginTransaction().show(entityAndServiceOrderFragment).commit();
	}
	
	/**
	 * 
	 * @Description 显示其他订单Fragment
	 * @Author blue
	 * @Time 2015-9-22
	 */
	private void showOtherOrderFragment() {
		fragmentManager.beginTransaction().hide(entityAndServiceOrderFragment).commit();
		fragmentManager.beginTransaction().show(mOtherOrderFragment).commit();
	}
}
