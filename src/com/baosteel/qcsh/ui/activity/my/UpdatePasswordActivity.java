package com.baosteel.qcsh.ui.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.activity.MainActivity;
import com.baosteel.qcsh.ui.fragment.my.FragmentForgetPwOne;
import com.baosteel.qcsh.ui.fragment.my.FragmentForgetPwOne.OnStepToForgetPwListener;
import com.baosteel.qcsh.ui.fragment.my.FragmentModifyFinish;
import com.baosteel.qcsh.ui.fragment.my.FragmentModifyPwOne;
import com.baosteel.qcsh.ui.fragment.my.FragmentModifyPwOne.OnStepToModifyPwListener;
import com.baosteel.qcsh.ui.fragment.my.FragmentModifyPwTwo;
import com.baosteel.qcsh.ui.fragment.my.FragmentModifyPwTwo.OnStepToFinishListener;
import com.common.base.BaseActivity;

/**
 * 修改密码
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-6
 */
public class UpdatePasswordActivity extends BaseActivity{
	
	/**用于区分修改密码和忘记密码**/
	public static final String EXTRA_MANAGER_PW_TYPE = "manager.pw.type";
	/**修改密码**/
	public static final int MANAGER_PW_MODIFY = 0x1;
	/**忘记密码**/
	public static final int MANAGER_PW_FORGET = 0x2;
	
	/**framgent管理**/
	private FragmentManager mFragmentManager;
	
	/**修改密码第一步**/
	private FragmentModifyPwOne fragmentModifyPwOne;
	
	/**忘记密码第一步**/
	private FragmentForgetPwOne fragmentForgetPwOne;
	
	/**修改密码第二步**/
	private FragmentModifyPwTwo fragmentModifyPwTwo;
	
	/**修改密码第三步**/
	private FragmentModifyFinish fragmentModifyFinish;
	
	//下面步骤提示文本
	private TextView tv_title_step_1, tv_title_step_2,tv_title_step_3;
	
	//步骤图标
	private ImageView iv_step_2,iv_step_3, leftIcon, rightIcon;
	
	//消息处理器-用于处理倒计时
	private Handler mHandler;
	
	private int mManagerPwType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_modify_mobilephone);
		
		init();
	}
	
	public void init(){
		mManagerPwType = getIntent().getIntExtra(EXTRA_MANAGER_PW_TYPE, MANAGER_PW_MODIFY);
		mHandler = new Handler();
		mFragmentManager = this.getSupportFragmentManager();
		if (mManagerPwType == MANAGER_PW_MODIFY) {
			fragmentModifyPwOne = new FragmentModifyPwOne(this,mHandler);
			fragmentModifyPwOne.setOnStepToModifyPwListener(new OnStepToModifyPwListener() {
				
				@Override
				public void stepToModifyPw(String checkCode) {
					
					replaceFragment(fragmentModifyPwTwo);
					showStep(2);
					
				}
			});
		} else if (mManagerPwType == MANAGER_PW_FORGET) {
			fragmentForgetPwOne = new FragmentForgetPwOne(this, mHandler);
			fragmentForgetPwOne.setOnStepToForgetPwListener(new OnStepToForgetPwListener() {
				
				@Override
				public void stepToModifyPw(String phone, String checkCode) {
					
					fragmentModifyPwTwo.setPhone(phone);
					replaceFragment(fragmentModifyPwTwo);
					showStep(2);
					
				}
			});
		}
		
		
		fragmentModifyPwTwo = new FragmentModifyPwTwo(this);
		fragmentModifyFinish = new FragmentModifyFinish();
		
		
		fragmentModifyPwTwo.setOnStepToFinishListener(new OnStepToFinishListener() {
			
			@Override
			public void stepToFinish() {
				
				replaceFragment(fragmentModifyFinish);
				showStep(3);
				
				
				//清空用户信息(在用户点击返回的时候，返回主页的乐虎主页)
	        	clearUserInfo();
	        	
			}
		});
		
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//直接退出
				finish();
				
				//如果用户已经修改手机成功，则返回跳转到主页
				if(!userIsLogin(false)) {
					if (mManagerPwType == MANAGER_PW_MODIFY) {
						startToMainActivity(MainActivity.TAB_MY);
					}
				}else{
					
					//直接退出
					finish();
				}
			}
		});
		
		TextView titleTv = (TextView)findViewById(R.id.tv_title);
		
		tv_title_step_1 = (TextView) findViewById(R.id.tv_step_1);
		tv_title_step_2 = (TextView) findViewById(R.id.tv_step_2);
		tv_title_step_3 = (TextView) findViewById(R.id.tv_step_3);
		
		
		iv_step_2 = (ImageView) findViewById(R.id.iv_mod_phone_step_2);
		iv_step_3 = (ImageView) findViewById(R.id.iv_mod_phone_step_3);
		leftIcon = (ImageView) findViewById(R.id.left_icon_imageview);
		rightIcon = (ImageView) findViewById(R.id.right_icon_imageview);
		
		tv_title_step_2.setText("2.修改登录密码");
		
		if (mManagerPwType == MANAGER_PW_FORGET) {
			titleTv.setText("忘记密码");
			replaceFragment(fragmentForgetPwOne);
		} else if (mManagerPwType == MANAGER_PW_MODIFY) {
			titleTv.setText("修改密码");
			replaceFragment(fragmentModifyPwOne);
		}
		showStep(1);
	}
	
	/**
	 * 显示步骤
	 * @param step
	 */
	private void showStep(int step){
		
		switch (step) {
		case 1:
			
			iv_step_2.setImageResource(R.drawable.xiugaimima_step2);
			leftIcon.setImageResource(R.drawable.line_circle_gray);
			tv_title_step_2.setTextColor(getResources().getColor(R.color.gray_pressed));
			
			iv_step_3.setImageResource(R.drawable.xiugaimimia_step3);
			rightIcon.setImageResource(R.drawable.line_circle_gray);
			tv_title_step_3.setTextColor(getResources().getColor(R.color.gray_pressed));
			
			break;
		case 2:
			
			iv_step_2.setImageResource(R.drawable.xiugaimima_step2_xuanzhong);
			leftIcon.setImageResource(R.drawable.line_circle_red);
			tv_title_step_2.setTextColor(getResources().getColor(R.color.orange_red));
			
			iv_step_3.setImageResource(R.drawable.xiugaimimia_step3);
			rightIcon.setImageResource(R.drawable.line_circle_gray);
			tv_title_step_3.setTextColor(getResources().getColor(R.color.gray_pressed));
			
			break;
		case 3:
			
			iv_step_2.setImageResource(R.drawable.xiugaimima_step2_xuanzhong);
			leftIcon.setImageResource(R.drawable.line_circle_red);
			tv_title_step_2.setTextColor(getResources().getColor(R.color.orange_red));
			
			iv_step_3.setImageResource(R.drawable.xiugaimima_step3_xuanzhong);
			rightIcon.setImageResource(R.drawable.line_circle_red);
			tv_title_step_3.setTextColor(getResources().getColor(R.color.orange_red));
			
			break;
		}
	}
	
	/**
	 * 替换fragment
	 * @param fragment
	 */
	public void replaceFragment(Fragment fragment){
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.addToBackStack(null);
		transaction.replace(R.id.fragment_container_mod_phone, fragment);
		transaction.commit();
	}
	
	@Override
	public void onBackPressed() {
		
		int count = mFragmentManager.getBackStackEntryCount();
		if(count <= 1 || count == 3){
			finish();
		}else{
			mFragmentManager.popBackStack();
			
			showStep(count);
		}
		
		//如果用户已经修改手机成功，则返回跳转到主页，乐虎
//		if(!userIsLogin(false)){
//			startToMainActivity(4);
//		}else{
//			//直接退出
//			super.onBackPressed();
//		}
	}
	
}
