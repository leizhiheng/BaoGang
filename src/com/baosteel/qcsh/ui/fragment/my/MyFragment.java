package com.baosteel.qcsh.ui.fragment.my;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.constants.ConstantsOrder;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.UserInfo;
import com.baosteel.qcsh.ui.activity.home.happyliving.MyReleaseRecordeActivity;
import com.baosteel.qcsh.ui.activity.my.AccountBalanceActivity;
import com.baosteel.qcsh.ui.activity.my.BindHealthPointActivity;
import com.baosteel.qcsh.ui.activity.my.CollectProductActivity;
import com.baosteel.qcsh.ui.activity.my.CollectStoreActivity;
import com.baosteel.qcsh.ui.activity.my.LoginActivity;
import com.baosteel.qcsh.ui.activity.my.MyBalanceActivity;
import com.baosteel.qcsh.ui.activity.my.MyGiftCardActivity;
import com.baosteel.qcsh.ui.activity.my.MyHealthPointActivity;
import com.baosteel.qcsh.ui.activity.my.MyRecordActivity;
import com.baosteel.qcsh.ui.activity.my.MyScoreActivity;
import com.baosteel.qcsh.ui.activity.my.MyWalletActivity;
import com.baosteel.qcsh.ui.activity.my.PhoneRegistActivity;
import com.baosteel.qcsh.ui.activity.my.order.AllOrderActivity;
import com.baosteel.qcsh.ui.activity.my.order.OrderListActivity;
import com.baosteel.qcsh.ui.activity.my.setting.FeedBackActivity;
import com.baosteel.qcsh.ui.activity.my.setting.MyMessageActivity;
import com.baosteel.qcsh.ui.activity.my.setting.SettingActivity;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.base.BaseFragment;
import com.google.gson.Gson;

/**
 * 我的(个人中心)
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-27
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{
	
	/**健康点**/
	private static final int REQUEST_CODE_HEALTH 		= 1;
	
	/**现金券**/
	private static final int REQUEST_CODE_BALANCE 		= 2;
	
	/**礼品卡**/
	private static final int REQUEST_CODE_GIFT_CARD 	= 3;
	
	/**帐户余额 **/
	private static final int REQUEST_CODE_ACCOUNT_BALANCE = 4;
	
	/**我的积分 **/
	private static final int REQUEST_CODE_ACCOUNT_SCORE = 5;	
	
	/**已登录的布局**/
	private View loginView;
	
	/**未登录的布局**/
	private View unLoginView;
	
	/**我的发布*/
	private View mLayout_all_release;
	/**草稿*/
	private View mLayout_draft_release;
	/**已提交*/
	private View mLayout_commited_release;
	/**审核中*/
	private View mLayout_in_checking_release;
	/**已审核*/
	private View mLayout_checked_release;
	/**已退回*/
	private View mLayout_canceled_release;

	
	/**头像**/
	private ImageView lehu_login_head;
	
	/**收藏的商品数量***/
	private TextView mTv_attention_product;
	
	/**收藏的店铺数量***/
	private TextView mTv_attention_shop;
	
	/**我的足迹数***/
	private TextView mTv_browse;
	
	/**订单-待审核数***/
	private TextView waitVerifyOrdernum;
	
	/**订单-待付款数***/
	private TextView waitPayOrdernum;

	/**订单-待发货数***/
	private TextView wait_send_ordernum;

	/**订单-待收货数***/
	private TextView waitreceive_ordernum;

	/**订单-待评价数***/
	private TextView waitcomment_ordernum;

	/**订单-待预约退换数***/
	private TextView backgood_ordernum;
	
	/**账户余额**/
	private TextView tv_account_balance;
	
	/**礼品卡**/
	private TextView tv_gift_card;
	
	/**优惠券**/
	private TextView tv_favorable;
	
	/**健康点**/
	private TextView tv_health;
	
	/**我的积分**/
	private TextView tv_score;
	
	/**用户名**/
	private TextView mUserName;
	
	/**用户等级**/
	private TextView mUserLevelTv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 fragmentView = inflater.inflate(R.layout.fragment_my, null);  
		 return fragmentView;  
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//初始化控件
		initView();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		//判断登录视图
		adjustLoginView();

		//加载个人中心的数据
		loadUserInfo();
	}
	
	/**
	 * 初始化控件
	 */
	private void initView(){
		
		//未登录，已登录布局
		unLoginView = findViewById(R.id.unlogin_layout);
		loginView = findViewById(R.id.login_layout);
		
		//设置
		findViewById(R.id.user_setting).setOnClickListener(this);
		findViewById(R.id.lehu_login_head).setOnClickListener(this);
		
		//登录注册,消息
		lehu_login_head = (ImageView)findViewById(R.id.lehu_login_head);
		mUserName = (TextView) findViewById(R.id.userName);
		mUserLevelTv = (TextView) findViewById(R.id.userLevelTv);
		findViewById(R.id.loginTv).setOnClickListener(this);
		findViewById(R.id.registTv).setOnClickListener(this);
		findViewById(R.id.new_msg_layout).setOnClickListener(this);
		
		//收藏的商品,收藏的店铺,我的足迹
		mTv_attention_product = (TextView)findViewById(R.id.tv_attention_product);
		mTv_attention_shop = (TextView)findViewById(R.id.tv_attention_shop);
		mTv_browse = (TextView)findViewById(R.id.tv_browse);
		
		mTv_attention_product.setOnClickListener(this);
		mTv_attention_shop.setOnClickListener(this);
		mTv_browse.setOnClickListener(this);
		
		//订单
		waitVerifyOrdernum = (TextView)findViewById(R.id.waitVerifyOrdernum);
		waitPayOrdernum = (TextView)findViewById(R.id.waitPayOrdernum);
		wait_send_ordernum = (TextView)findViewById(R.id.wait_send_ordernum);
		waitreceive_ordernum = (TextView)findViewById(R.id.waitreceive_ordernum);
		waitcomment_ordernum = (TextView)findViewById(R.id.waitcomment_ordernum);
		backgood_ordernum = (TextView)findViewById(R.id.backgood_ordernum);
		findViewById(R.id.all_order).setOnClickListener(this);
		findViewById(R.id.wait_verify_orderlist).setOnClickListener(this);
		findViewById(R.id.waitpay_orderlist).setOnClickListener(this);
		findViewById(R.id.wait_send_orderlist).setOnClickListener(this);
		findViewById(R.id.waitreceive_orderlist).setOnClickListener(this);
		findViewById(R.id.waitcomment_orderlist).setOnClickListener(this);
		findViewById(R.id.backgood_orderlist).setOnClickListener(this);
		
		//我的钱包
		tv_account_balance = (TextView)findViewById(R.id.tv_account_balance);
		tv_gift_card = (TextView)findViewById(R.id.tv_gift_card);
		tv_favorable = (TextView)findViewById(R.id.tv_favorable);
		tv_health = (TextView)findViewById(R.id.tv_health);
		tv_score = (TextView)findViewById(R.id.tv_score);
		findViewById(R.id.layout_assets).setOnClickListener(this);
		tv_account_balance.setOnClickListener(this);
		tv_gift_card.setOnClickListener(this);
		tv_favorable.setOnClickListener(this);
		tv_health.setOnClickListener(this);
		tv_score.setOnClickListener(this);
		
		//意见反馈
		findViewById(R.id.layout_feedback).setOnClickListener(this);
		
		//我的发布
		mLayout_all_release = findViewById(R.id.layout_all_release);
		mLayout_draft_release = findViewById(R.id.layout_draft_release);
		mLayout_commited_release = findViewById(R.id.layout_commited_release);
		mLayout_in_checking_release = findViewById(R.id.layout_in_checking_release);
		mLayout_checked_release = findViewById(R.id.layout_checked_release);
		mLayout_canceled_release = findViewById(R.id.layout_canceled_release);
		mLayout_all_release.setOnClickListener(this);
		mLayout_draft_release.setOnClickListener(this);
		mLayout_commited_release.setOnClickListener(this);
		mLayout_in_checking_release.setOnClickListener(this);
		mLayout_checked_release.setOnClickListener(this);
		mLayout_canceled_release.setOnClickListener(this);
	}
	
	/**
	 * 初始化数据
	 */
	private void adjustLoginView(){
		
		//判断用户是否登录
		if(userIsLogin()){
			loginView.setVisibility(View.VISIBLE);
			unLoginView.setVisibility(View.GONE);
		}else{
			loginView.setVisibility(View.GONE);
			unLoginView.setVisibility(View.VISIBLE);
			
			//清空已填入的用户信息
			clearData();
		}
		
	}

	/**
	 * 加载用户信息
	 */
	private void loadUserInfo(){
		if(!userIsLogin()){
			return;
		}
		String userId = BaoGangData.getInstance().getUser().userId;
		String client = ConstantsAPI.Client_Android;
		RequestClient.queryAppUserInfo(mContext, userId, "1", client, new RequestCallback<JSONObject>(false) {
			@Override
			public void onResponse(JSONObject response) {
				if(JSONParseUtils.isSuccessRequest(mContext, response)){
					JSONObject returnMap = response.optJSONObject("returnMap");

					//解析userId
					Gson gson = new Gson();
					UserInfo userInfo = gson.fromJson(returnMap.toString(), UserInfo.class);
					if(null != userInfo){
						
						//显示用户信息
						showUserInfo(userInfo);
						
					}else{

						//登录失败，用户信息解析错误
						showToast("获取用户信息失败");
					}
				}
			}
		});

	}

	
	/**
	 * 显示用户信息
	 */
	public void showUserInfo(UserInfo userInfo){
		
		//用户名，用户等级
		ImageManager.Load(userInfo.getHeadUrl(), lehu_login_head);
		mUserName.setText((userInfo.getNickName() != null)?userInfo.getNickName():"未设置");
		mUserLevelTv.setText((userInfo.getGradeName() != null)?userInfo.getGradeName():"普通用户");

		//收藏(商品，店铺)，我的足迹
		mTv_attention_product.setText(userInfo.getCollectGoods()+"\n收藏的商品");
		mTv_attention_shop.setText(userInfo.getCollectShops() +"\n收藏的店铺");
		mTv_browse.setText(userInfo.getUserLookNum() +"\n我的足迹");
		
		//待审核，待付款，待发货，待收货，待评价，预约退换---数量
		showNumber(waitVerifyOrdernum, userInfo.getWaitAudit());
		showNumber(waitPayOrdernum, userInfo.getWaitPay());
		showNumber(wait_send_ordernum, userInfo.getWaitDeliver());
		showNumber(waitreceive_ordernum, userInfo.getWaitReceive());
		showNumber(waitcomment_ordernum, userInfo.getWaitEvaluate());
		showNumber(backgood_ordernum, userInfo.getRefundAndBarter());
		
		//账户余额，礼品卡，优惠券，健康点，我的积分
		tv_account_balance.setText(userInfo.getAccountCurrency() + "\n账户余额");
		tv_gift_card.setText(userInfo.getGifCard() + "\n礼品卡");
		tv_favorable.setText(userInfo.getCoupon() + "\n优惠券");
		tv_health.setText(userInfo.getHealthValue() + "\n健康点");
		tv_score.setText(userInfo.getScore() + "\n积分");
		
	}

	/**
	 * 清空数据
	 */
	private void clearData() {

		// 收藏(商品，店铺)，我的足迹
		lehu_login_head.setImageResource(R.drawable.default_head);
		mTv_attention_product.setText("0\n收藏的商品");
		mTv_attention_shop.setText("0\n收藏的店铺");
		mTv_browse.setText("0\n我的足迹");

		// 待审核，待付款，待发货，待收货，待评价，预约退换---数量
		showNumber(waitVerifyOrdernum, 0);
		showNumber(waitPayOrdernum, 0);
		showNumber(wait_send_ordernum, 0);
		showNumber(waitreceive_ordernum, 0);
		showNumber(waitcomment_ordernum, 0);
		showNumber(backgood_ordernum, 0);

		// 账户余额，礼品卡，优惠券，健康点，我的积分
		tv_account_balance.setText("\n账户余额");
		tv_gift_card.setText("0\n礼品卡");
		tv_favorable.setText("0\n优惠券");
		tv_health.setText("0\n健康点");
		tv_score.setText("0\n积分");
	}
	
	/**
	 * 订单数
	 * @param numberTv
	 * @param number
	 */
	private void showNumber(TextView numberTv, int number){
		if(null == numberTv){
			return;
		}
		
		if(number <= 0){
			numberTv.setVisibility(View.GONE);
		}else{
			numberTv.setVisibility(View.VISIBLE);
			if(number > 99){
				numberTv.setText("99+");
			}else{
				numberTv.setText(""+number);
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		
		int vId = v.getId();
		
		//判断用户是否已经登录
		if(vId != R.id.loginTv && vId != R.id.registTv
				&& !userIsLogin(true)){
			return;
		}
		
		Intent intent = null;
		
		switch (v.getId()) {
		case R.id.user_setting:
		case R.id.lehu_login_head:
			
			//设置
			startActivity(new Intent(mContext, SettingActivity.class));
			
			break;
		case R.id.new_msg_layout:
			
			//我的消息
			startActivity(new Intent(mContext, MyMessageActivity.class));
			
			break;
		case R.id.loginTv:
			
			//登录
			startActivity(new Intent(mContext, LoginActivity.class));
			
			break;

		case R.id.registTv:
			
			//注册
			startActivity(new Intent(mContext, PhoneRegistActivity.class));
			
			break;
		case R.id.tv_attention_product: 
			
			//收藏的商品
			startActivity(new Intent(mContext, CollectProductActivity.class));
			
			break;
		case R.id.tv_attention_shop: 
			
			//收藏的店铺
			startActivity(new Intent(mContext, CollectStoreActivity.class));
			
			break;
		case R.id.tv_browse: 
			
			//我的足迹
			startActivity(new Intent(mContext, MyRecordActivity.class));
			
			break;
		case R.id.all_order:
			
			//全部订单
			intent = new Intent(getActivity(), AllOrderActivity.class);
			
			break;
		case R.id.wait_verify_orderlist:
			
			//待审核
			intent = new Intent(getActivity(), OrderListActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "待审核订单");
			intent.putExtra(OrderListActivity.EXTRAN_ORDER_STATUS, ConstantsOrder.ORDER_STATUS_NOT_CHECK);
			
			break;
		case R.id.waitpay_orderlist:
			
			//待支付
			intent = new Intent(getActivity(), OrderListActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "待付款订单");
			intent.putExtra(OrderListActivity.EXTRAN_ORDER_STATUS, ConstantsOrder.ORDER_STATUS_NOT_PAY);
			
			break;
		case R.id.wait_send_orderlist:
			
			//待发货
			intent = new Intent(getActivity(), OrderListActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "待发货订单");
			intent.putExtra(OrderListActivity.EXTRAN_ORDER_STATUS, ConstantsOrder.ORDER_STATUS_NOT_SEND);
			
			break;
		case R.id.waitreceive_orderlist:
			
			//待收货
			intent = new Intent(getActivity(), OrderListActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "待收货订单");
			intent.putExtra(OrderListActivity.EXTRAN_ORDER_STATUS, ConstantsOrder.ORDER_STATUS_NOT_RECEIVE);
			
			
			break;
			
		case R.id.waitcomment_orderlist:
			
			//待评价
			intent = new Intent(getActivity(), OrderListActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "待评价订单");
			intent.putExtra(OrderListActivity.EXTRAN_ORDER_STATUS, ConstantsOrder.ORDER_STATUS_NOT_COMMENT);
			
			
			break;
		case R.id.backgood_orderlist:
			
			//退款/退货
//			intent = new Intent(getActivity(), OrderListActivity.class);
//			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "申请退款");
//			intent.putExtra(OrderListActivity.EXTRAN_ORDER_STATUS, ConstantsOrder.ORDER_STATUS_RETURN);
			
			break;
		case R.id.layout_assets:
			
			//我的钱包
			startActivity(new Intent(mContext, MyWalletActivity.class));
			
			break;
		case R.id.tv_account_balance:
			
			//账户余额
			Intent intenta = new Intent(mContext, AccountBalanceActivity.class);
			startActivityForResult(intenta, REQUEST_CODE_ACCOUNT_BALANCE);
			
			break;
		case R.id.tv_gift_card:
			
			//礼品卡
			Intent intentB = new Intent(mContext, MyGiftCardActivity.class);
			startActivityForResult(intentB, REQUEST_CODE_GIFT_CARD);
			
			break;
		case R.id.tv_favorable:
			
			//优惠券
			Intent intentF = new Intent(mContext, MyBalanceActivity.class);
			startActivityForResult(intentF, REQUEST_CODE_BALANCE);
			
			break;
		case R.id.tv_health:
			
			//健康点
			if(userIsLogin()){
				Intent intentH = new Intent(mContext, MyHealthPointActivity.class);
				startActivityForResult(intentH, REQUEST_CODE_HEALTH);
				
			}else{
				Intent intentH = new Intent(mContext, BindHealthPointActivity.class);
				startActivityForResult(intentH, REQUEST_CODE_HEALTH);
			}
			
			break;
		case R.id.tv_score:
			
			//我的积分
			Intent intents = new Intent(mContext, MyScoreActivity.class);
			startActivityForResult(intents, REQUEST_CODE_ACCOUNT_SCORE);
			
			break;
		case R.id.layout_feedback:
			
			//意见反馈
			startActivity(new Intent(mContext, FeedBackActivity.class));
			
			break;
		case R.id.layout_all_release:
			
			/*
			 * 我所有的发布
			 */
			intent = new Intent(getActivity(), MyReleaseRecordeActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "全部发布信息");
			break;

		case R.id.layout_draft_release:

			/*
			 * 草稿
			 */
			intent = new Intent(getActivity(), MyReleaseRecordeActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "草稿箱");
			break;

		case R.id.layout_commited_release:

			/*
			 * 已提交
			 */
			intent = new Intent(getActivity(), MyReleaseRecordeActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "已提交发布");
			break;

		case R.id.layout_in_checking_release:

			/*
			 * 审核中
			 */
			intent = new Intent(getActivity(), MyReleaseRecordeActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "审核中发布");
			break;

		case R.id.layout_checked_release:

			/*
			 * 已审核
			 */
			intent = new Intent(getActivity(), MyReleaseRecordeActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "已审核发布");
			break;
			
		case R.id.layout_canceled_release:
			
			/*
			 * 已退回
			 */
			intent = new Intent(getActivity(), MyReleaseRecordeActivity.class);
			intent.putExtra(BaseActivity.EXTRA_KEY_TITLE, "已取消发布");
			break;
		}
		
		if (intent != null) startActivity(intent);
	}
}
