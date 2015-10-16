package com.baosteel.qcsh.ui.activity;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.pay.PayResultActivity;
import com.common.pay.alipay.AlipayUtil;
import com.common.pay.alipay.AlipayUtil.PayCallBack;

/**
 * 在线支付
 * @author 江文思
 *
 * @todo TODO
 *
 * @date 2015-10-12
 */
public class OnlinePaymentActivity extends BaseActivity implements View.OnClickListener{

	/**用于传递订单ID**/
	public static final String EXTRA_ORDER_ID = "order.id";
	
	/**用于传递订单ID**/
	public static final String EXTRA_ORDER_PAY_ID = "order.pay.id";
	
	/**用于传递订单编号**/
	public static final String EXTRA_ORDER_CODE = "order.code";
	
	/**用于传递订单金额**/
	public static final String EXTRA_ORDER_PRICE = "order.price";

    private TitleBar mTitle_Bar;
    
    /**支付宝支付**/
    private LinearLayout pay_alipay;
    private LinearLayout pay_wechat_layout;
    private LinearLayout pay_yinlian_layout;
    private LinearLayout pay_credit_card_layout;
    
    private TextView mTvOrderCode;//订单编号
    private TextView mTvOrderPrice;//订单价格
    
    private String orderId;
    private String orderPayId;
    private String orderCode;
    private String orderPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);
        intiData();
        initViews();
    }
    
    /**
     * 初始化数据
     */
    private void intiData(){
    	
    	//订单id， 订单支付ID， 订单编号，订单支付总金额
    	orderId = getIntent().getStringExtra(EXTRA_ORDER_ID);
    	orderPayId = getIntent().getStringExtra(EXTRA_ORDER_PAY_ID);
        orderCode = getIntent().getStringExtra(EXTRA_ORDER_CODE);
        orderPrice = getIntent().getStringExtra(EXTRA_ORDER_PRICE);
    }

    /**
     * 初始化控件
     */
    private void initViews(){
    	
        mTitle_Bar = (TitleBar)findViewById(R.id.title_bar);
        mTitle_Bar.setTitle("在线支付");
        mTitle_Bar.setBackgroud(R.color.index_red);
        
        mTvOrderCode = (TextView) findViewById(R.id.tv_order_code);
        mTvOrderPrice = (TextView) findViewById(R.id.tv_order_price);
        
        mTvOrderCode.setText("订单编号：" + orderCode);
        mTvOrderPrice.setText(orderPrice+"");

        pay_alipay = (LinearLayout)findViewById(R.id.pay_alipay_layout);
        pay_wechat_layout = (LinearLayout)findViewById(R.id.pay_wechat_layout);
        pay_yinlian_layout = (LinearLayout)findViewById(R.id.pay_yinlian_layout);
        pay_credit_card_layout = (LinearLayout)findViewById(R.id.pay_credit_card_layout);
       
        pay_alipay.setOnClickListener(this);
        pay_wechat_layout.setOnClickListener(this);
        pay_yinlian_layout.setOnClickListener(this);
        pay_credit_card_layout.setOnClickListener(this);
        curView = pay_alipay;
    }

    View curView;
    private void selectView(View view){
    	if(curView.equals(view)){
    		return;
    	}
    	view.setBackgroundResource(R.drawable.common_radius_white_withorangestrok);
    	curView.setBackgroundResource(R.drawable.common_radius_white_withgraystrok);
    	curView = view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay_alipay_layout:
            	
            	//支付宝支付
            	selectView(pay_alipay);
            	alipayBefore(orderPayId);
            	
                break;
            case R.id.pay_wechat_layout:
            	
            	//微信支付
            	selectView(pay_wechat_layout);
            	
            	break;
            case R.id.pay_yinlian_layout:
            	
            	//银联支付
            	selectView(pay_yinlian_layout);
            	
            	break;
            case R.id.pay_credit_card_layout:
            	
            	//东方支付
            	selectView(pay_credit_card_layout);
            	
            	break;
            	
        }
    }

    
    /**
     * 支付前接口-获取支付相关参数
     */
    private void  alipayBefore(String orderId){
        RequestClient.alipayBefore(mContext, orderId, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            	
            	//{"type":1,"returnMap":{"total_fee":"250.0","private_key":"1","notify_url":"www.alipay.com","number":"1","partner":"ttsrt","seller_id":"ttsrt","public_key":"yy","out_trade_no":"OA_20151012195447685"},"msg":"成功"}
            	if (JSONParseUtils.isSuccessRequest(mContext, response)) {
            		response = response.optJSONObject("returnMap");
            		
            		String private_key = JSONParseUtils.getString(response, "private_key");
            		String public_key = JSONParseUtils.getString(response, "public_key");
            		String partner = JSONParseUtils.getString(response, "partner");
            		String seller_id = JSONParseUtils.getString(response, "seller_id");
            		final String order_no = JSONParseUtils.getString(response, "out_trade_no");
            		String order_name = "宝钢七彩生活";
            		String order_desc = "宝钢七彩生活";
            		String order_fee = JSONParseUtils.getString(response, "total_fee");
            		String notify_url = JSONParseUtils.getString(response, "notify_url");
            		
//            		String private_key = Keys.PRIVATE;
//            		String partner = Keys.DEFAULT_PARTNER;
//            		String seller_id = Keys.DEFAULT_SELLER;
//            		final String order_no = JSONParseUtils.getString(response, "out_trade_no");
//            		String order_name = "宝钢七彩生活";
//            		String order_desc = "宝钢七彩生活";
//            		String order_fee = JSONParseUtils.getString(response, "total_fee");
//            		String notify_url = JSONParseUtils.getString(response, "notify_url");
            		
            		//支付宝支付
            		AlipayUtil aplipay = new AlipayUtil(mContext);
            		aplipay.alipayOrder(private_key, partner, seller_id, order_no, order_name, order_desc, order_fee, notify_url);
            		aplipay.setPayResultCallBack(new PayCallBack() {
						
						@Override
						public void payResultCallBack(String status, String resultStatus) {
							Log.i("Alipay", "type:"+status + "   result:"+resultStatus);
							
							if(TextUtils.equals("9000", status)){
								alipayAfter(order_no);
							}else{
								
								//跳转支付结果界面
		                        goPayResultActivity(2, 1);
							}
						}
					});
                }
            }
        });
    }
    
    /**
     * 支付后的接口
     * @param orderNo
     */
    private void alipayAfter(String orderNo){
        RequestClient.alipayAfter(mContext, orderNo, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            	if (JSONParseUtils.isSuccessRequest(mContext, response)) {
            		showToast("支付成功");
            		
            		//跳转支付结果界面
            		goPayResultActivity(1, 1);
            	}else{
            		
            		//跳转支付结果界面
            		goPayResultActivity(2, 1);
            	}
            }
        });
    }
    
    
    /**
     * 跳转支付结果界面
     * @param payResult	 	1.支付成功 2.支付失败 3.WAP支付返回
     * @param payType		1.支付宝 2.银联 3.微信4.服务卡
     */
    private void goPayResultActivity(int payResult, int payType){
    	
    	Intent intent = new Intent(this, PayResultActivity.class);
    	
    	//这四个参数是为了能够让用户在支付失败的时候返回重新支付
    	intent.putExtra(PayResultActivity.EXTRA_ORDER_CODE, orderCode);
        intent.putExtra(PayResultActivity.EXTRA_ORDER_ID, orderId);
        intent.putExtra(PayResultActivity.EXTRA_ORDER_PAY_ID, orderPayId);
        intent.putExtra(PayResultActivity.EXTRA_ORDER_PRICE, orderPrice);
        
        //这两个参数是为了显示支付结果
    	intent.putExtra(PayResultActivity.PAY_RESULT, payResult);
    	intent.putExtra(PayResultActivity.PAY_TYPE, payType);
    	
    	startActivity(intent);
    	
    	finish();
    }
    
    
}

