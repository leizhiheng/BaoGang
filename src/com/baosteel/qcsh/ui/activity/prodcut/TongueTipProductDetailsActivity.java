package com.baosteel.qcsh.ui.activity.prodcut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.AppContext;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.dialog.TopbarMenuPopwindow;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.ui.activity.MainActivity;
import com.baosteel.qcsh.ui.activity.store.StoreMainActivity;
import com.baosteel.qcsh.ui.adapter.ViewPagerFragmentAdapter;
import com.baosteel.qcsh.ui.view.MyGridView;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseActivity;
import com.common.utils.AppManager;
import com.common.utils.DeviceUtils;
import com.common.utils.LogUtil;
import com.common.view.other.IndexViewPager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 舌尖宝商品详情 Created by kuangyong on 15/8/31.
 */
public class TongueTipProductDetailsActivity extends BaseActivity implements
		View.OnClickListener,ViewPager.OnPageChangeListener {

	public static final String GOOOS_ID="gooos_id";						//商品id
	private android.widget.TextView tvproduct; 							// 商品
	private android.widget.TextView tvdetails; 							// 详情
	private android.widget.TextView tvappraise; 						// 评价
	private android.widget.LinearLayout layoutback; 					// 返回键
	private android.widget.ImageButton iv_headview_goback; 				// 返回键
	private android.widget.ImageButton btnright; 						// 右侧按钮
	private android.widget.ImageButton btnmiddle; 						// 中间按钮
	private android.widget.RelativeLayout top; 							// 顶部布局
	private LinearLayout layout_service;								//客服
	private LinearLayout layout_2store;									//进店
	private LinearLayout layout_collect;								//收藏
	private TextView tvadd2car;											//加入购物车
	private TextView tvbuynow;											//立即购买
	private ViewPager vpfragments;
	private TongueTipProductFragment tongueTipProductFragment;			//商品
	private TongueTipDetailsFragment tongueTipDetailsFragment;			//详情
	private TongueTipAppraiseFragment tongueTipAppraiseFragment;		//评价
	private ViewPagerFragmentAdapter adapter;
	private List<Fragment> fragments;
	private String goodsId;												//商品id
	private TextView tv_collect;										//收藏按钮文字
	private boolean isCollected=false;									//商品是否收藏
	private String proNum="1";											//商品数量
	private LinearLayout layout_btns;									//按钮布局

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_baseproduct_details);
		goodsId=getIntent().getStringExtra(GOOOS_ID);
		findView();
		setListener();
		init();
		queryAppHasMemberGoodsCollected();
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
		this.tv_collect = (TextView) findViewById(R.id.tv_collect);
		this.vpfragments = (IndexViewPager) findViewById(R.id.vp_fragments);
		this.tvbuynow = (TextView) findViewById(R.id.tv_buynow);
		this.tvadd2car = (TextView) findViewById(R.id.tv_add2car);
		this.layout_collect = (LinearLayout) findViewById(R.id.layout_collect);
		this.layout_2store = (LinearLayout) findViewById(R.id.layout_2store);
		this.layout_service = (LinearLayout) findViewById(R.id.layout_service);
		this.layout_btns = (LinearLayout) findViewById(R.id.layout_btns);

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
		top.setBackgroundResource(R.color.orange_product);// 设置顶部布局的背景颜色
		setProductSelected(true);
		setDetailsSelected(false);
		setAppraiseSelected(false);
		tvproduct.setOnClickListener(this);
		tvappraise.setOnClickListener(this);
		tvdetails.setOnClickListener(this);
		setMiddleBtnBackGroundAndOnclick(R.drawable.icon_shoppingcar, this);
		setRightBtnBackGroundAndOnclick(R.drawable.icon_3point, this);
		initFragments();
	}

	/**
	 * 初始化 商品、详情、评价三个页面
	 */
	private void initFragments(){
		tongueTipProductFragment=TongueTipProductFragment.newInstance(goodsId);
		tongueTipProductFragment.setGo2AppraiseListener(new TongueTipProductFragment.Go2AppraiseListener() {
			@Override
			public void go2Appraise() {
				vpfragments.setCurrentItem(2);//跳转到评价
			}

			@Override
			public void setProNum(String num) {
				proNum=num;//设置商品数量
			}

			@Override
			public void getProType(String goods_genre) {
				if(ConstantsAPI.PRO_TYPE_SERVICE.equals(goods_genre)){//服务商品
					tvbuynow.setText("立即预约");
					tvadd2car.setVisibility(View.GONE);
					layout_btns.setWeightSum(1);
				}
			}
		});
		tongueTipDetailsFragment=new TongueTipDetailsFragment();
		tongueTipAppraiseFragment=TongueTipAppraiseFragment.newInstance(goodsId);
		fragments=new ArrayList<Fragment>();
		fragments.add(tongueTipProductFragment);
		fragments.add(tongueTipDetailsFragment);
		fragments.add(tongueTipAppraiseFragment);
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
					.setBackgroundResource(R.drawable.common_leftradius_orange);
			tvproduct.setTextColor(getResources().getColor(
					R.color.orange_product));
		} else {
			tvproduct
					.setBackgroundResource(R.drawable.common_leftradius_orange_strok);
			tvproduct.setTextColor(getResources().getColor(
					R.color.orange_light_product));
		}
	}

	/**
	 * 详情是否选中
	 * 
	 * @param isSelected
	 */
	public void setDetailsSelected(boolean isSelected) {
		if (isSelected) {
			tvdetails.setBackgroundResource(R.color.orange_light_product);
			tvdetails.setTextColor(getResources().getColor(
					R.color.orange_product));
		} else {
			tvdetails
					.setBackgroundResource(R.drawable.common_rectangle_orange_strok);
			tvdetails.setTextColor(getResources().getColor(
					R.color.orange_light_product));
		}
	}

	/**
	 * 评论是否选中
	 *
	 * @param isSelected
	 */
	public void setAppraiseSelected(boolean isSelected) {
		if (isSelected) {
			tvappraise
					.setBackgroundResource(R.drawable.common_rightradius_orange);
			tvappraise.setTextColor(getResources().getColor(
					R.color.orange_product));
		} else {
			tvappraise
					.setBackgroundResource(R.drawable.common_rightradius_orange_strok);
			tvappraise.setTextColor(getResources().getColor(
					R.color.orange_light_product));
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
		Intent intent = null;
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
			TopbarMenuPopwindow popwindow = new TopbarMenuPopwindow(this);
			popwindow.showAsDropDown(top, DeviceUtils.getWidthMaxPx(this) - popwindow.getWidth(), 0);
			break;
		case R.id.btn_middle: // 中间按钮
			startToMainActivity(MainActivity.TAB_SHOPCART);
			break;
		case R.id.tv_buynow: // 立即购买
			if (userIsLogin(true)) {
				tongueTipProductFragment.getQueryAppAddShopCar(ConstantsAPI.Shopping_Type_Buy_Now, proNum, null);
			}
			break;
		case R.id.tv_add2car: // 加入购物车
			tongueTipProductFragment.getQueryAppAddShopCar(ConstantsAPI.Shopping_Type_Add_Cart, proNum, null);
			break;
		case R.id.layout_collect: // 收藏
			if(isCollected){//取消收藏
				queryAppDeleteMemberGoodsCollection();
			}else{//收藏商品
				queryAppaddMemberGoodsCollection();
			}
			break;
		case R.id.layout_2store: //进店
			
			intent = new Intent(mContext, StoreMainActivity.class);
			intent.putExtra(StoreMainActivity.MERCHANT_ID, tongueTipProductFragment.getStoreId());
			startActivity(intent);
			break;
		case R.id.layout_service: // 客服
			showToast("客服");
			break;
		}
	}

	/**
	 * 商品是否已收藏
	 */
	private void queryAppHasMemberGoodsCollected(){
		if(userIsLogin(false)){//是否已登录
			
			if (StringUtils.isEmpty(goodsId)) return;
			
			RequestClient.queryAppHasMemberGoodsCollected(mContext,BaoGangData.getInstance().getUser().userId, goodsId, new RequestCallback<JSONObject>(false) {
				@Override
				public void onResponse(JSONObject response) {
					if (JSONParseUtils.isSuccessRequest(mContext, response)) {
						JSONObject returnMap = response.optJSONObject("returnMap");
						isCollected = returnMap.optBoolean("hasCollected");//商品是否已收藏
						if(isCollected){//已收藏
							tv_collect.setText("已收藏");
						}else{//未收藏
							tv_collect.setText("收藏");
						}
					}
				}
			});
		}
	}

	/**
	 * 收藏商品
	 */
	private void queryAppaddMemberGoodsCollection(){
		if(userIsLogin(true)) {//是否已登录
			RequestClient.queryAppaddMemberGoodsCollection(mContext,BaoGangData.getInstance().getUser().userId, goodsId, new RequestCallback<JSONObject>(false) {
				@Override
				public void onResponse(JSONObject response) {
					if (JSONParseUtils.isSuccessRequest(mContext, response)) {
						isCollected=true;
						showToast("收藏成功！");
						tv_collect.setText("已收藏");
					}
				}
			});
		}
	}


	/**
	 * 取消收藏商品
	 */
	private void queryAppDeleteMemberGoodsCollection(){
		if(userIsLogin(true)) {//是否已登录
			RequestClient.queryAppDeleteMemberGoodsCollection(mContext, BaoGangData.getInstance().getUser().userId, goodsId, new RequestCallback<JSONObject>(false) {
				@Override
				public void onResponse(JSONObject response) {
					if (JSONParseUtils.isSuccessRequest(mContext, response)) {
						isCollected=false;
						showToast("已取消收藏！");
						tv_collect.setText("收藏");
					}
				}
			});
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
