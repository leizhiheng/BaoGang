package com.baosteel.qcsh.ui.activity.prodcut;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.ViewPagerFragmentAdapter;
import com.common.base.BaseActivity;
import com.common.view.other.IndexViewPager;
import java.util.ArrayList;
import java.util.List;

/**
 * 安程宝
 */
public class CarProductDetailsActivity extends BaseActivity implements
		View.OnClickListener,ViewPager.OnPageChangeListener {

	private android.widget.TextView tvproduct; // 商品
	private android.widget.TextView tvdetails; // 详情
	private android.widget.TextView tvappraise; // 评价
	private android.widget.LinearLayout layoutback; // 返回键
	private android.widget.ImageButton iv_headview_goback; // 返回键
	private android.widget.ImageButton btnright; // 右侧按钮
	private android.widget.ImageButton btnmiddle; // 中间按钮
	private android.widget.RelativeLayout top; // 顶部布局
	private LinearLayout layout_service;//客服
	private LinearLayout layout_2store;//进店
	private LinearLayout layout_collect;//收藏
	private TextView tvadd2car;//加入购物车
	private TextView tvbuynow;//立即购买
	private ViewPager vpfragments;
	private CarProductFragment carProductFragment;//商品
	private CarDetailsFragment carDetailsFragment;//详情
	private CarAppraiseFragment carAppraiseFragment;//评价
	private ViewPagerFragmentAdapter adapter;
	private List<Fragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_baseproduct_details);
		findView();
		setListener();
		init();
	}

	private void findView() {
		this.top = (RelativeLayout) findViewById(R.id.top);
		this.btnmiddle = (ImageButton) findViewById(R.id.btn_middle);
		this.btnright = (ImageButton) findViewById(R.id.btn_right);
		this.layoutback = (LinearLayout) findViewById(R.id.layout_back);
		this.iv_headview_goback = (ImageButton) findViewById(R.id.iv_headview_goback);
		this.tvappraise = (TextView) findViewById(R.id.tv_appraise);
		this.tvdetails = (TextView) findViewById(R.id.tv_details);
		this.tvproduct = (TextView) findViewById(R.id.tv_product);
		this.vpfragments = (IndexViewPager) findViewById(R.id.vp_fragments);
		this.tvbuynow = (TextView) findViewById(R.id.tv_buynow);
		this.tvadd2car = (TextView) findViewById(R.id.tv_add2car);
		this.layout_collect = (LinearLayout) findViewById(R.id.layout_collect);
		this.layout_2store = (LinearLayout) findViewById(R.id.layout_2store);
		this.layout_service = (LinearLayout) findViewById(R.id.layout_service);
	}

	/**
	 * 设置监听事件
	 */
	private void setListener() {
		layoutback.setOnClickListener(this);
		iv_headview_goback.setOnClickListener(this);
		tvbuynow.setOnClickListener(this);
		tvadd2car.setOnClickListener(this);
		layout_collect.setOnClickListener(this);
		layout_2store.setOnClickListener(this);
		layout_service.setOnClickListener(this);
	}

	/**
	 * 初始化UI
	 */
	private void init() {
		top.setBackgroundResource(R.color.green_safetrip);// 设置顶部布局的背景颜色
		setProductSelected(true);
		setDetailsSelected(false);
		setAppraiseSelected(false);
		tvproduct.setOnClickListener(this);
		tvappraise.setOnClickListener(this);
		tvdetails.setOnClickListener(this);
		setMiddleBtnBackGroundAndOnclick(R.drawable.icon_shoppingcar, this);
		setRightBtnBackGroundAndOnclick(R.drawable.icon_3point, this);
		initFragments();
		tvbuynow.setText("预定");
	}

	/**
	 * 初始化 商品、详情、评价三个页面
	 */
	private void initFragments(){
		carProductFragment=new CarProductFragment();
		carDetailsFragment=new CarDetailsFragment();
		carAppraiseFragment=new CarAppraiseFragment();
		fragments=new ArrayList<Fragment>();
		fragments.add(carProductFragment);
		fragments.add(carDetailsFragment);
		fragments.add(carAppraiseFragment);
		adapter=new ViewPagerFragmentAdapter(getSupportFragmentManager(),fragments);
		vpfragments.setAdapter(adapter);
		vpfragments.setCurrentItem(0);
		vpfragments.setOnPageChangeListener(this);
	}

	/**
	 * 商品是否选中
	 *
	 * @param isSelected
	 */
	public void setProductSelected(boolean isSelected) {
		if (isSelected) {
			tvproduct
					.setBackgroundResource(R.drawable.common_leftradius_green);
			tvproduct.setTextColor(getResources().getColor(
					R.color.green_safetrip));
		} else {
			tvproduct
					.setBackgroundResource(R.drawable.common_leftradius_green_strok);
			tvproduct.setTextColor(getResources().getColor(
					R.color.green_light_safetrip));
		}
	}

	/**
	 * 详情是否选中
	 *
	 * @param isSelected
	 */
	public void setDetailsSelected(boolean isSelected) {
		if (isSelected) {
			tvdetails.setBackgroundResource(R.color.green_light_safetrip);
			tvdetails.setTextColor(getResources().getColor(
					R.color.green_safetrip));
		} else {
			tvdetails
					.setBackgroundResource(R.drawable.common_rectangle_green_strok);
			tvdetails.setTextColor(getResources().getColor(
					R.color.green_light_safetrip));
		}
	}

	/**
	 * 详情是否选中
	 *
	 * @param isSelected
	 */
	public void setAppraiseSelected(boolean isSelected) {
		if (isSelected) {
			tvappraise
					.setBackgroundResource(R.drawable.common_rightradius_green);
			tvappraise.setTextColor(getResources().getColor(
					R.color.green_safetrip));
		} else {
			tvappraise
					.setBackgroundResource(R.drawable.common_rightradius_green_strok);
			tvappraise.setTextColor(getResources().getColor(
					R.color.green_light_safetrip));
		}
	}

	/**
	 * 设置右侧按钮图片背景和监听事件
	 *
	 * @param backGround
	 */
	public void setRightBtnBackGroundAndOnclick(int backGround,
												View.OnClickListener clickListener) {
		btnright.setImageResource(backGround);
		btnright.setVisibility(View.VISIBLE);
		btnright.setOnClickListener(clickListener);
	}

	/**
	 * 设置中间按钮图片背景和监听事件
	 *
	 * @param backGround
	 */
	public void setMiddleBtnBackGroundAndOnclick(int backGround,
												 View.OnClickListener clickListener) {
		btnmiddle.setImageResource(backGround);
		btnmiddle.setVisibility(View.VISIBLE);
		btnmiddle.setOnClickListener(clickListener);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.layout_back:
			case R.id.iv_headview_goback: // 返回键
				finish();
				break;
			case R.id.tv_product: // 商品
				vpfragments.setCurrentItem(0);
				break;
			case R.id.tv_details: // 详情
				vpfragments.setCurrentItem(1);
				break;
			case R.id.tv_appraise: // 评价
				vpfragments.setCurrentItem(2);
				break;
			case R.id.btn_right: // 右侧按钮
				showToast("右侧按钮");
				break;
			case R.id.btn_middle: // 中间按钮
				showToast("中间按钮");
				break;
			case R.id.tv_buynow: // 预定
				showToast("预定");
				break;
			case R.id.tv_add2car: // 加入购物车
				showToast("加入购物车");
				break;
			case R.id.layout_collect: // 收藏
				showToast("收藏");
				break;
			case R.id.layout_2store: //进店
				showToast("进店");
				break;
			case R.id.layout_service: // 客服
				showToast("客服");
				break;
		}
	}

	@Override
	public void onPageScrolled(int i, float v, int i1) {

	}

	@Override
	public void onPageSelected(int i) {
		setProductSelected(false);
		setDetailsSelected(false);
		setAppraiseSelected(false);
		switch (i){
			case 0://商品
				setProductSelected(true);
				break;
			case 1://详情
				setDetailsSelected(true);
				break;
			case 2://评价
				setAppraiseSelected(true);
				break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int i) {

	}
}