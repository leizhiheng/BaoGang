package com.common.pay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.activity.MainActivity;
import com.baosteel.qcsh.ui.activity.OnlinePaymentActivity;
import com.baosteel.qcsh.ui.activity.my.order.AllOrderActivity;
import com.common.base.BaseActivity;

/**
 * 支付结果
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-10-15
 */
public class PayResultActivity extends BaseActivity implements OnClickListener {

	private static final String TAG 		= "PayResultActivity";
	
	
	/**用于传递订单ID**/
	public static final String EXTRA_ORDER_ID = "order.id";
	
	/**用于传递订单ID**/
	public static final String EXTRA_ORDER_PAY_ID = "order.pay.id";
	
	/**用于传递订单编号**/
	public static final String EXTRA_ORDER_CODE = "order.code";
	
	/**用于传递订单金额**/
	public static final String EXTRA_ORDER_PRICE = "order.price";
	
	
	
	/**支付结果**/
	public static final String PAY_RESULT 	= "pay_result";
	
	/**支付类型**/
	public static final String PAY_TYPE 	= "pay_type";
	
	

	private LinearLayout layout_pay_result;	// 支付结果
	private TextView pay_find_orderinfo;	// 查看订单
	private TextView pay_continueshop;		// 继续购物
	private TextView tv_pay_tip1;			// 支付提示
	private TextView tv_pay_orderid;		// 订单编号
	private TextView title;					// 标题
	private TextView tv_pay_tip2;			// 您已成功使用支付宝支付完成交易
	private TextView tv_countdown;			// 倒计时
	private ImageView iv_pay_image;			// 支付图标
	
	
	//以下四个参数的作用是(订单支付失败，能够正常跳转到支付界面)
	private String orderId;					//订单ID
    private String orderPayId;				//订单支付ID
    private String orderCode;				//订单编号
    private String orderPrice;				//订单支付总额
	
    //以下两个参数作用是(提示用户支付结果)
	private int pay_result;					// 支付结果 1.支付成功 2.支付失败 3.WAP支付返回
	private int payType;					// 支付方式 1.支付宝 2.银联 3.微信4.服务卡

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_result);
		
		//初始化控件
		initView();
		
		//初始化数据
		initData();
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

		//订单id， 订单支付ID， 订单编号，订单支付总金额
    	orderId = getStringExtra(EXTRA_ORDER_ID);
    	orderPayId = getStringExtra(EXTRA_ORDER_PAY_ID);
        orderCode = getStringExtra(EXTRA_ORDER_CODE);
        orderPrice = getStringExtra(EXTRA_ORDER_PRICE);
        pay_result = getIntExtra(PAY_RESULT);
		payType = getIntExtra(PAY_TYPE);
        
		//显示支付结果
		showPayResult();
		
		//显示倒计时
		showCountDown();
	}

	/**
	 * 初始化控件
	 */
	public void initView() {
		layout_pay_result = (LinearLayout) findViewById(R.id.layout_pay_result);
		pay_find_orderinfo = (TextView) findViewById(R.id.pay_find_orderinfo);
		pay_continueshop = (TextView) findViewById(R.id.pay_continueshop);
		tv_pay_tip1 = (TextView) findViewById(R.id.tv_pay_tip1);
		tv_pay_tip2 = (TextView) findViewById(R.id.tv_pay_tip2);
		tv_pay_orderid = (TextView) findViewById(R.id.tv_pay_orderid);
		title = (TextView) findViewById(R.id.title);
		tv_countdown = (TextView) findViewById(R.id.tv_countdown);
		iv_pay_image = (ImageView) findViewById(R.id.iv_pay_image);
		
		pay_find_orderinfo.setOnClickListener(this);
		pay_continueshop.setOnClickListener(this);
		
	}

	
	
	/**
	 * 初始化数据
	 */
	public void showPayResult() {
		
		//字体加粗
		TextPaint tp = title.getPaint();
		tp.setFakeBoldText(true);
		TextPaint tp1 = tv_pay_tip1.getPaint();
		tp1.setFakeBoldText(true);
		
		//显示订单编号
		tv_pay_orderid.setText("订单编号：" + orderCode);
		
		//标题
		String result = (pay_result == 1) ? "支付成功" : ((pay_result == 2) ? "支付失败" : "支付结果");
		title.setText(result);
		
		//支付平台名称，图标
		String payName = getPayType(payType);
		int img = getPayImg(payType);
		iv_pay_image.setImageResource(img);
		
		if (1 == pay_result) {
			// 成功
			tv_pay_tip1.setText("支付成功，谢谢惠顾！");
			tv_pay_tip2.setText("您已成功使用" + payName + "支付完成交易");
			
		} else if (2 == pay_result) {
			//失败
			tv_pay_tip1.setText("支付失败！");
			tv_pay_tip2.setText("您使用" + payName + "支付交易失败");

			// 设置背景为重新支付
			pay_find_orderinfo.setBackgroundResource(R.drawable.btn_backpay);
		
		} else {
			
			title.setText("支付结果");
			tv_pay_tip1.setText("查看支付结果！");
			tv_pay_tip2.setText("");
		}
	}

	/**
	 * 获取支付类型的图标
	 * @param payType
	 * @return
	 */
	private int getPayImg(int payType){
		switch (payType) {
		case 1:
			return R.drawable.pay_alipay;
		case 2:
			return R.drawable.pay_up;
		case 3:
			return R.drawable.pay_weixin;
		default:
			return R.drawable.pay_alipay;
		}
	}
	
	/**
	 * 获取支付类型
	 * @param payType
	 * @return
	 */
	private String getPayType(int payType) {

		switch (payType) {
		case 1:
			return "支付宝";
		case 2:
			return "银联";
		case 3:
			return "微信";
		default:
			return "支付宝";
		}

	}
	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.pay_find_orderinfo:
			
			// 查看订单
			if (1 == pay_result) {
				
				// 成功-进入订单列表
				findOrderInfo();
				
			} else {
				
				// 失败--返回支付页面
				backPay();
			}
			break;
		case R.id.pay_continueshop:
			
			// 继续购物--自动跳转到主页
			startToMainActivity(MainActivity.TAB_HOME);
			
			break;
		}
	}

	/**
	 * 查看订单
	 */
	public void findOrderInfo() {
		
		//跳转全部订单列表
		Intent intent = new Intent(this, AllOrderActivity.class);
		startActivity(intent);
		finish();
		
	}

	
	
	//倒计时------------------start
	private static final int HANDLER_TIME_FINISH = 1;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			switch (msg.what) {
			case HANDLER_TIME_FINISH:

				if (count > 0) {
					count--;
					mHandler.sendEmptyMessageDelayed(HANDLER_TIME_FINISH, 1000);
					// 更新ui, count;
					tv_countdown.setText(count + "s后将自动返回订单详情");
				} else {
					findOrderInfo();
				}
				break;
			default:
				break;
			}
		}
	};
	private int count = 10;
	private void showCountDown() {
		count = 10;
		mHandler.sendEmptyMessageDelayed(HANDLER_TIME_FINISH, 1000);
	}
	//倒计时------------------end
	

	/**
	 * 返回支付页面
	 */
	public void backPay() {
		
		Intent intent = new Intent(mContext, OnlinePaymentActivity.class);
        intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_CODE, orderCode);
        intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_ID, orderId);
        intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_PAY_ID, orderPayId);
        intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_PRICE, orderPrice);
        startActivity(intent);
        
        //结束当前界面
        finish();
	}

	

	@Override
	protected void onDestroy() {
		mHandler.removeMessages(HANDLER_TIME_FINISH);
		super.onDestroy();
	}
}