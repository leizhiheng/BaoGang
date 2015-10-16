package com.common.pay.alipay;

import java.net.URLEncoder;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.common.utils.LogUtil;

/***
 * 支付宝支付工具类
 * 
 * @author Administrator
 * 
 */
public class AlipayUtil {
	public static final String TAG = "alipay-sdk";

	private Activity context;

	
	private static final int SDK_PAY_FLAG = 1;

	private static final int SDK_CHECK_FLAG = 2;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);

				// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
				String resultInfo = payResult.getResult();
				String resultStatus = payResult.getResultStatus();

				// 支付成功，调用接口
				if (callBack != null) {
					callBack.payResultCallBack(resultStatus, resultInfo);
				}
				
				//-----------------------下面代码只是显示支付结果-------------------------------
				
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
					
				} else {
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(context, "支付结果确认中", Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
					}
				}
				break;
			}
			case SDK_CHECK_FLAG: {
				Toast.makeText(context, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};
	
	
	public AlipayUtil(Activity context) {
		this.context = context;
	}


	/***
	 * 签名类型
	 * 
	 * @return
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}

	/**
	 * 支付宝支付
	 * @param private_key	私钥
	 * @param partner		合作身份者id，以2088开头的16位纯数字
	 * @param seller_id		收款支付宝账号
	 * @param order_no   	订单编号
	 * @param order_name 	订单名称
	 * @param order_desc 	订单描述
	 * @param order_fee  	订单金额
	 * @param notify_url 	回调URL
	 */
	public String getOrderInfo(String partner, String seller_id, String order_no, String order_name, String order_desc, String order_fee, String notify_url) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + seller_id + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + order_no + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + order_name + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + order_desc + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + order_fee + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + notify_url + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"1m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		//orderInfo += "&return_url=\"m.alipay.com\"";
		orderInfo += "&return_url=\"" + notify_url+ "\"";
		

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}
	
	
	
	/**
	 * 支付宝支付
	 * @param private_key	私钥
	 * @param partner		合作身份者id，以2088开头的16位纯数字
	 * @param seller_id		收款支付宝账号
	 * @param order_no   	订单编号
	 * @param order_name 	订单名称
	 * @param order_desc 	订单描述
	 * @param order_fee  	订单金额
	 * @param notify_url 	回调URL
	 */
	public void alipayOrder(String private_key, String partner, String seller_id, String order_no, String order_name, String order_desc, String order_fee, String notify_url) {
		try {
			
			// 订单
			String orderInfo = getOrderInfo(partner, seller_id, order_no, order_name, order_desc, order_fee, notify_url);
			LogUtil.i("Alipay", "orderInfo:"+orderInfo);
			
			// 对订单做RSA 签名
			String sign = SignUtils.sign(orderInfo, private_key);
			
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
			
			// 完整的符合支付宝参数规范的订单信息
			final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
			LogUtil.i("Alipay", "payInfo:"+payInfo);
			
			Runnable payRunnable = new Runnable() {

				@Override
				public void run() {
					// 构造PayTask 对象
					PayTask alipay = new PayTask(context);
					// 调用支付接口，获取支付结果
					String result = alipay.pay(payInfo);
					LogUtil.i("Alipay", "支付宝支付结果:"+result);
					

					Message msg = new Message();
					msg.what = SDK_PAY_FLAG;
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			};

			// 必须异步调用
			Thread payThread = new Thread(payRunnable);
			payThread.start();

		} catch (Exception ex) {
			ex.printStackTrace();
			
			//将结果返回
			Message msg = new Message();
			msg.what = SDK_PAY_FLAG;
			msg.obj = "";
			mHandler.sendMessage(msg);
		}
	}


	public interface PayCallBack {
		void payResultCallBack(String status, final String resultStatus);
	}

	private PayCallBack callBack;

	public void setPayResultCallBack(PayCallBack callBack) {
		this.callBack = callBack;
	}
}
