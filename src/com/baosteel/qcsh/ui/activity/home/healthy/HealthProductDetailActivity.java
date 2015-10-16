package com.baosteel.qcsh.ui.activity.home.healthy;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.activity.prodcut.TongueTipAppraiseFragment;
import com.baosteel.qcsh.ui.activity.prodcut.TongueTipDetailsFragment;
import com.baosteel.qcsh.ui.activity.prodcut.TongueTipProductFragment;
import com.baosteel.qcsh.ui.adapter.ViewPagerFragmentAdapter;
import com.baosteel.qcsh.ui.fragment.home.healthy.PhysicalExamDetailFragment;
import com.baosteel.qcsh.ui.fragment.home.healthy.ReadyBedDetailFragment;
import com.common.base.BaseActivity;
import com.common.view.other.IndexViewPager;

/**
 * 
 * @description 页面：健康宝-》体检预约-》体检套餐详情
 * @author blue
 * @Time 2015-9-15
 *
 */
public class HealthProductDetailActivity extends BaseActivity implements
		View.OnClickListener, ViewPager.OnPageChangeListener {

	/** 跳转到该页面时需要将产品类型传递过来 */
	public static final String EXTRA_KEY_PRODUCT_TYPE = "product.type";
	/** 跳转到该页面时需要将产品类型传递过来 */
	public static final String EXTRA_KEY_PRODUCT_ID= "product.id";
	/** 商品类型：体检套餐  */
	public static final int PRODUCT_PAYSICAL_AXAM = 0;
	/** 商品类型：养老现床  */
	public static final int PRODUCT_READY_BED = 1;
	/** 商品类型：养老期床  */
	public static final int PRODUCT_FUTURE_BED = 2;
	
	private RadioGroup mRadioGroup;
	private RadioButton mRadioBtnLeft;
	private android.widget.LinearLayout layoutback; // 返回键
	private android.widget.ImageView iv_headview_goback; // 返回键
	private android.widget.ImageButton btnright; // 右侧按钮
	private android.widget.ImageButton btnmiddle; // 中间按钮
	private android.widget.RelativeLayout top; // 顶部布局
	private LinearLayout layout_service;// 客服
	private LinearLayout layout_2store;// 进店
	private LinearLayout layout_collect;// 收藏
	private TextView mBtnReserveNow;
//	private TextView tvadd2car;// 加入购物车
//	private TextView tvbuynow;// 立即购买
	private ViewPager vpfragments;
	private Fragment productFragment;// 商品
	private Fragment detailFragment;// 详情
	private Fragment commentFragment;// 评价
	private ViewPagerFragmentAdapter adapter;
	private List<Fragment> fragments;
	
	/** 商品类型 */
	private int mProductType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_physical_exam_details);
		initData();
		findView();
		setListener();
		init();
	}
	
	private void initData() {
		mProductType = getIntent().getIntExtra(EXTRA_KEY_PRODUCT_TYPE, PRODUCT_PAYSICAL_AXAM);
	}

	private void findView() {
		this.top = (RelativeLayout) findViewById(R.id.top);
		this.mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
		this.mRadioBtnLeft = (RadioButton) findViewById(R.id.radio_btn_left);
		this.btnmiddle = (ImageButton) findViewById(R.id.btn_middle);
		this.btnright = (ImageButton) findViewById(R.id.btn_right);
		this.layoutback = (LinearLayout) findViewById(R.id.layout_back);
		this.iv_headview_goback = (ImageView) findViewById(R.id.iv_headview_goback);
		this.vpfragments = (IndexViewPager) findViewById(R.id.vp_fragments);
		this.mBtnReserveNow = (TextView) findViewById(R.id.btn_reserve_now);
		this.layout_collect = (LinearLayout) findViewById(R.id.layout_collect);
		this.layout_2store = (LinearLayout) findViewById(R.id.layout_2store);
		this.layout_service = (LinearLayout) findViewById(R.id.layout_service);
	
	}
	
	/**
	 * 
	 * @Description 给所有子部件进行初始化赋值
	 * @Author blue
	 * @Time 2015-9-15
	 */
	private void initView() {
		
	}

	/**
	 * 设置监听事件
	 */
	private void setListener() {
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch (checkedId) {
				case R.id.radio_btn_left: // 商品
					vpfragments.setCurrentItem(0);
					break;
				case R.id.radio_btn_mid: // 详情
					vpfragments.setCurrentItem(1);
					break;
				case R.id.radio_btn_right: // 评价
					vpfragments.setCurrentItem(2);
					break;
				default:
					break;
				}
			}
		});
		mRadioBtnLeft.setChecked(true);
		layoutback.setOnClickListener(this);
		iv_headview_goback.setOnClickListener(this);
		mBtnReserveNow.setOnClickListener(this);
		layout_collect.setOnClickListener(this);
		layout_2store.setOnClickListener(this);
		layout_service.setOnClickListener(this);
	}

	/**
	 * 初始化UI
	 */
	private void init() {
		top.setBackgroundResource(R.color.theme_color_healthy);// 设置顶部布局的背景颜色
		setMiddleBtnBackGroundAndOnclick(R.drawable.icon_shoppingcar, this);
		setRightBtnBackGroundAndOnclick(R.drawable.icon_3point, this);
		initFragments();
	}
	
	/**
	 * 初始化 商品、详情、评价三个页面
	 */
	private void initFragments() {
		
		if (mProductType == PRODUCT_PAYSICAL_AXAM) {
			/*
			 * 体检套餐 信息
			 */
			productFragment = new PhysicalExamDetailFragment();
			detailFragment = new TongueTipDetailsFragment();
			commentFragment = TongueTipAppraiseFragment.newInstance("");
		} else if (mProductType == PRODUCT_READY_BED){
			/*
			 * 养老现床 信息
			 */
			productFragment = new ReadyBedDetailFragment();
			detailFragment = new TongueTipDetailsFragment();
			commentFragment =TongueTipAppraiseFragment.newInstance("");
		} else if (mProductType == PRODUCT_FUTURE_BED) {
			/*
			 * 养老期床信息
			 */
			productFragment = new ReadyBedDetailFragment();
			((ReadyBedDetailFragment) productFragment).setProductType(mProductType);
			detailFragment = new TongueTipDetailsFragment();
			commentFragment = TongueTipAppraiseFragment.newInstance("");
		}
		
		fragments = new ArrayList<Fragment>();
		fragments.add(productFragment);
		fragments.add(detailFragment);
		fragments.add(commentFragment);
		adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(),
				fragments);
		vpfragments.setAdapter(adapter);
		vpfragments.setCurrentItem(0);
		vpfragments.setOnPageChangeListener(this);
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
		Intent intent = null;
		switch (view.getId()) {
		case R.id.layout_back:
		case R.id.iv_headview_goback: // 返回键
			finish();
			break;
		
		case R.id.btn_right: // 右侧按钮
			showToast("右侧按钮");
			break;
		case R.id.btn_middle: // 中间按钮
			showToast("中间按钮");
			break;
		case R.id.btn_reserve_now: // 立即预定
			intent = new Intent(this, CommitOrderActivity.class);
			if (mProductType == PRODUCT_PAYSICAL_AXAM) {
				intent.putExtra(CommitOrderActivity.EXTRA_ORDER_TYPE, CommitOrderActivity.ORDER_PHYSICAL_EXAM);
			} else if (mProductType == PRODUCT_READY_BED) {
				intent.putExtra(CommitOrderActivity.EXTRA_ORDER_TYPE, CommitOrderActivity.ORDER_READY_BED);
			}else if (mProductType == PRODUCT_FUTURE_BED) {
				intent.putExtra(CommitOrderActivity.EXTRA_ORDER_TYPE, CommitOrderActivity.ORDER_FUTURE_BED);
			}
			startActivity(intent);
			break;
		case R.id.layout_collect: // 收藏
			showToast("收藏");
			break;
		case R.id.layout_2store: // 进店
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
	}

	@Override
	public void onPageScrollStateChanged(int i) {

	}
	
	public int getProductType(){
		return mProductType;
	}
}
