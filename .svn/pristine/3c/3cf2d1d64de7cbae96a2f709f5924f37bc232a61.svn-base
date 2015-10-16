package com.baosteel.qcsh.api;

import org.json.JSONObject;

import android.R.integer;
import android.content.Context;
import android.text.TextUtils;

import com.baosteel.qcsh.model.DeliverCommit;
import com.baosteel.qcsh.model.MemberReceiveAddressJson;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.net.HttpUtils;
import com.common.net.RequestParams;
import com.common.utils.LogUtil;
import com.common.volley.Request.Method;
import com.common.volley.VolleyError;

import java.util.List;

/**
 * 请求接口
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-23
 */
public class RequestClient {
	
	/**
	 * 请求加密
	 * @param params
	 */
	private static void requestEncrypt(RequestParams params){
		
		//拼上加密参数
		
	}
	

	/**
	 * 发送请求(这个方法目的是为了后面做接口加密，统一处理)
	 * @param context
	 * @param params
	 * @param listener
	 */
	private static void postRequest(final Context context, final RequestParams params, final RequestCallback<JSONObject> listener){
		
		// 请求加密
		requestEncrypt(params);
		
		// 打印请求的url
		LogUtil.d("RequestClient", params.toString());
		
		//请求开启，显示加载对话框
		listener.onStart(context);
		
		// 添加到请求队列
		HttpUtils.addRequest(context, params, new RequestCallback<JSONObject>(true) {

			@Override
			public void onErrorResponse(VolleyError error) {
				listener.isSuccessResult = false;
				
				String errorMsg = HttpUtils.convertError(context, error, true);
				// 打印请求的url
				LogUtil.d("RequestClient", params.getMethod() + "\n" + errorMsg);
				
				//请求结束，关闭加载对话框
				listener.onFinish();
			}

			@Override
			public void onResponse(JSONObject response) {
				listener.isSuccessResult = true;

				//打印返回的结果
				LogUtil.d("RequestClient", params.getMethod() + "\n" + (null != response ? response.toString() : "返回结果为null"));

				//数据回传
				if (null != listener) {
					listener.onResponse(response);
				}

				//请求结束，关闭加载对话框
				listener.onFinish();

			}
		});
	}
	
	/**
	 * 测试接口
	 * @param context
	 * @param params
	 * @param listener
	 */
	public static void test(Context context, RequestParams params, RequestCallback<JSONObject> listener){
		
		//发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 用户登录
	 * @param loginName 用户名
	 * @param password	密码
	 * @param phoneIp	手机IP地址
	 * @param client	客户端类型	1PC,2安卓,3,ios
	 * @param shopping_id 购物车ID
	 * @param listener	回调
	 */
	public static void appLogin(Context context, String loginName, String password, String phoneIp, String client, String shopping_id, RequestCallback<JSONObject> listener){
		
		//拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppLogin;
		params.method = Method.POST;
		params.addParam("loginName", loginName);
		params.addParam("phoneIp", phoneIp);
		params.addParam("client", client);
		params.addParam("password", password);
		params.addParam("shopping_id", shopping_id);
		
		//请求加密
		requestEncrypt(params);
		
		//发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 获取验证码
	 * @param context	上下文
	 * @param phone		手机号码
	 * @param type		使用模块(1:注册 2:忘记密码 3:修改密码4:修改手机_验证旧手机5:修改手机_验证新手机6:支付密码修改)
	 * @param listener	回调
	 */
	public static void appReceiveValidateCode(Context context, String phone, String type, RequestCallback<JSONObject> listener) {
		
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppReceiveValidateCode;
		params.method = Method.POST;
		params.addParam("phone", phone);
		params.addParam("type", type);

		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 手机验证接口,验证输入的验证码是否正确
	 * @param context	上下文
	 * @param phone		手机号码
	 * @param type		验证码使用模块(1:注册 2:忘记密码 3:修改密码 4:修改手机_验证旧手机 6:支付密码修改)
	 * @param listener	回调
	 */
	public static void appMobileValidate(Context context, String phone, String validateKey, String type, RequestCallback<JSONObject> listener) {
		
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppMobileValidate;
		params.method = Method.POST;
		params.addParam("phone", phone);
		params.addParam("validateKey", validateKey);
		params.addParam("type", type);

		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 提交新密码（修改密码和忘记密码共用一个接口）
	 * @param context	上下文
	 * @param phone		手机号码
	 * @param type		验证码使用模块(1:注册2:忘记密码)
	 * @param listener	回调
	 */
	public static void queryAppUpdatePassword(Context context, String validateKey, String phone, String password, RequestCallback<JSONObject> listener) {
		
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppUpdatePassword;
		params.method = Method.POST;
		params.addParam("phone", phone);
		params.addParam("validateKey", validateKey);
		params.addParam("password", password);

		// 发送请求
		postRequest(context, params, listener);
	}
	
	
	/**
	 * 手机注册
	 * @param context		上下文
	 * @param phone			手机号码
	 * @param validateKey	验证码
	 * @param phoneIp		手机IP地址
	 * @param client		客户端类型 :1PC,2安卓,3,ios
	 * @param password		密码
	 * @param shopping_id 	购物车ID
	 * @param listener		回调
	 */
	public static void queryAppMobileRegisiter(Context context, String phone, String validateKey, String phoneIp, String client, String password, String shopping_id, RequestCallback<JSONObject> listener) {
		
		
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppMobileRegisiter;
		params.method = Method.POST;
		params.addParam("phone", phone);
		params.addParam("validateKey", validateKey);
		params.addParam("phoneIp", phoneIp);
		params.addParam("client", client);
		params.addParam("password", password);
		params.addParam("shopping_id", shopping_id);

		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 获取首页和七个宝的Banner图
	 * @Description 
	 * @param moduleId 		模块ID( 看 ConstantsAPI.ModuleId_xxxx )
	 * @param moduleLevel 	模块级别 首页跟七个宝为1级 其他为2级
	 * @Author blue
	 * @Time 2015-9-24
	 */
	public static void queryAPPBanner(Context context, int moduleId, int moduleLevel, RequestCallback<JSONObject> listener) {
		
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAPPBanner;
		params.method = Method.POST;
		params.addParam("moduleLevel", moduleLevel+"");
		params.addParam("moduleId", moduleId+"");

		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 *  首页分类
	 * @param parentId 	模块ID(首页传-1，七个宝看模型数据里面的模块ID是什么就传什么)
	 * @Author blue
	 * @Time 2015-9-24
	 */
	public static void queryAppClassify(Context context, int parentId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppClassify;
		params.method = Method.POST;
		params.addParam("parentId", parentId+"");

		// 发送请求
		postRequest(context, params, listener);
	}
	
	
	/**
	 * 获取商品列表
	 * @param context		上下文	
	 * @param goodsTypeId	商品类别ID
	 * @param orderBy		商品分类：0代表默认排序，1代表销量排序，2代表价格排序,3表示新品排序（非必填）；默认值0
	 * @param orderStyle	商品排序 0代表升序，1代表降序	(非必填)，默认值0
	 * @param pageNum		起始页，默认值1
	 * @param pageSize		每页数据量，默认值10
	 * @param goodsName     搜索关键字
	 * @param listener
	 */
	public static void queryAppGoodsList(Context context, String goodsTypeId, int orderBy, int orderStyle, int pageNum, int pageSize, String goodsName, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGoodsList;
		params.method = Method.POST;
		params.addParam("goodsTypeId", goodsTypeId);
		params.addParam("orderBy", orderBy+"");
		params.addParam("orderStyle", orderStyle+"");
		params.addParam("pageNum", pageNum+"");
		params.addParam("pageSize", pageSize+"");
		params.addParam("goodsName", goodsName);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	
	/**
	 * 品类推荐
	 * @Time 2015-9-25
	 */
	public static void queryAppGoodsTypeSuggest(Context context, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGoodsTypeSuggest;
		params.method = Method.POST;
		params.addParam("flag", "1");
		
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 首页-热门服务器
	 * @Time 2015-9-25
	 */
	public static void queryAppHotService(Context context, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppHotService;
		params.method = Method.POST;
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	
	/**
	 * 首页-猜你喜欢
	 * @Time 2015-9-25
	 */
	public static void queryAppGuessYouLike(Context context, String userId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGuessYouLike;
		params.method = Method.POST;
		
		if(!TextUtils.isEmpty(userId)){
			params.addParam("userId", userId);
		}
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	
	/**
	 * 商品详情
	 * @param context
	 * @param goodsId 		商品ID(必填）
	 * @param userId 		用户id(必填）
	 * @param snId 			规格id 在商品规格变更的时候需要重新刷新商品详情数据，这时候，需要把选择的规格传给这个参数 
	 * @param listener
	 */
	public static void queryAppGoodsDetail(Context context, String goodsId, String userId, String snId, RequestCallback<JSONObject> listener) {

		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGoodsDetail;
		params.method = Method.POST;
		params.addParam("goodsId", goodsId);
		params.addParam("userId", userId);
		params.addParam("snId", snId);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 *商品图文详情
	 * @param context
	 * @param goodsId 		商品id，
	 * @param type 			类型（1，商品介绍，2 产品参数）
	 * @param showType 		显示端（1：手机端；2：pc端）
	 * @param listener
	 */
	public static void queryAppGoodsIntroduce(Context context, String goodsId,String type,String showType, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGoodsIntroduce;
		params.method = Method.POST;
		params.addParam("goodsId", goodsId);
		params.addParam("type", type);
		params.addParam("showType", showType);

		// 发送请求
		postRequest(context, params, listener);
	}



	/**
	 *根据地区查区商品运费与库存情况
	 * @param context
	 * @param goodsId   	商品ID
	 * @param province		省id
	 * @param city			市id
	 * @param area			区id
	 * @param listener
	 */
	public static void queryGoodsFreight(Context context, String goodsId,String province,String city,String area, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryGoodsFreight;
		params.method = Method.POST;
		params.addParam("goodsId", goodsId);
		params.addParam("province", province);
		params.addParam("city", city);
		params.addParam("area", area);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 商品评价
	 * @param context
	 * @param goodsId 				商品id
	 * @param pageNum	 			起始页，默认值1
	 * @param pageSize 				每页数据量，默认值100
	 * @param listener
	 */
	public static void queryAppGoodsComment(Context context, String goodsId,String pageNum,String pageSize, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGoodsComment;
		params.method = Method.POST;
		params.addParam("goodsId", goodsId);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 *商品规格
	 * @param context
	 * @param goodsId 		商品id
	 * @param spec_value 	规格值id
	 * @param listener
	 */
	public static void queryAppGoodsSpecValueList(Context context, String goodsId,String spec_value ,RequestCallback<JSONObject> listener) {

		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGoodsSpecValueList;
		params.method = Method.POST;
		params.addParam("goods_id", goodsId);
		params.addParam("spec_value", spec_value);

		// 发送请求
		postRequest(context, params, listener);
	}



	/**
	 *店铺详情
	 * @param context
	 * @param merchantId	商家 ID 		，
	 * @param listener
	 */
	public static void queryAppStoreDetail(Context context, String merchantId, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppStoreDetail;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 *店铺主页
	 * @param context
	 * @param merchantId	商家 ID 		，
	 * @param listener
	 */
	public static void queryAppMerchantInfo(Context context, String merchantId, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppMerchantInfo;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);

		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 首页分类-获取二三级分类数据
	 * @param context
	 * @param goodsTypeId	商品分类ID;值为-1时，表示去第一级商品分类;其余则表示取对应id下的商品分类。		，
	 * @param listener
	 */
	public static void queryAppClassifyList(Context context, String goodsTypeId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppClassifyList;
		params.method = Method.POST;
		params.addParam("goodsTypeId", goodsTypeId + "");

		// 发送请求
		postRequest(context, params, listener);

	}
	/**
	 *会员等级
	 * @param context
	 * @param userId	用户Id	，
	 * @param listener
	 */
	public static void queryAppVIPLevel(Context context, String userId, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppVIPLevel;
		params.method = Method.POST;
		params.addParam("userId", userId);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 个人中心初始化
	 *
	 * @param context
	 * @param userId 用户id
	 * @param versionNum 客户端版本号
	 * @param client 1用户端andriod 2用户端ios 3商家andriod 4商家ios
	 * @param listener
	 */
	public static void queryAppUserInfo(Context context, String userId,String versionNum,String client, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppUserInfo;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("versionNum", versionNum);
		params.addParam("client", client);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 个人中心数据修改
	 *
	 * @param context
	 * @param username 用户名(修改的用户名不能以bg_开头)
	 * @param nickname 昵称
	 * @param imgUrl 头像url
	 * @param userId 用户id(必传)
	 * @param sex 性别 1男2女3保密
	 * @param identityCard 身份证号
	 */
	public static void queryAppUpdateUserInfo(Context context, String username, String nickname, String imgUrl, String userId, String sex, String identityCard, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppUpdateUserInfo;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("username", username);
		params.addParam("nickname", nickname);
		params.addParam("imgUrl", imgUrl);
		params.addParam("sex", sex);
		params.addParam("identityCard", identityCard);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 个人中心版本更新
	 *
	 * @param context
	 * @param versionNum 客户端版本号
	 * @param client 1用户端andriod 2用户端ios 3商家andriod 4商家ios
	 * @param listener
	 */
	public static void queryAppUpdateVersion(Context context, String versionNum, String client, RequestCallback<JSONObject> listener) {


		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppUpdateVersion;
		params.method = Method.POST;
		params.addParam("versionNum", versionNum);
		params.addParam("client", client);

		// 发送请求
		postRequest(context, params, listener);
	}





	/**
	 * 
	 * @Description 获取商品属性列表
	 * @param goodsTypeId 商品分类编号（必填）
	 * @Author blue
	 * @Time 2015-9-24
	 */
	public static void queryAppGoodsAttr(Context context, String goodsTypeId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGoodsAttr;
		params.method = Method.POST;
		params.addParam("goodsTypeId", goodsTypeId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description 获取商品属性的属性值列表
	 * @param attrId 属性值Id
	 * @Author blue
	 * @Time 2015-9-24
	 */
	public static void queryAppGoodsAttrValueList(Context context, String attrId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppGoodsAttrValueList;
		params.method = Method.POST;
		params.addParam("attrId", attrId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description     获取订单列表
	 * @param userId    用户Id
	 * @param orderType 订单类型(0全部1实物2服务12实物+服务)
	 * @param status    订单状态(0全部 1 待付款 2 待发货 3 待确认 4 待评价 5 已取消 6 已关闭 7 待审核 8 待消费 9 已完成)
	 * @param pageSize  每页订单个数
	 * @param pageNum   当前页数
	 * @Author blue
	 * @Time 2015-9-24
	 */
	public static void queryAppOrderList(Context context, String userId, int orderType, int status, int pageNum, int pageSize, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppOrderList;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("orderType", orderType + "");
		params.addParam("status", status + "");
		params.addParam("pageSize", pageSize + "");
		params.addParam("pageNum", pageNum + "");
		
		// 发送请求
		postRequest(context, params, listener);
	}
	/**
	 * 
	 * @Description 商品收藏列表
	 * @param userId 用户ID
	 * @Author liuyuanqi
	 * @Time 2015-9-28
	 */
	public static void queryAppMemberGoodsCollectionList(Context context, String userId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppMemberGoodsCollectionList;
		params.method = Method.POST;
		params.addParam("userId", userId);
		
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 
	 * @Description 店铺收藏列表
	 * @param userId 	用户ID
	 * @param page 		页码
	 * @Author liuyuanqi
	 * @Time 2015-9-28
	 */
	public static void queryAppSelectCollectList(Context context, String userId, int page, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppSelectCollectList;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("page", page+"");
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 我的足迹列表
	 * @param context	上下文
	 * @param userId	用户ID
	 * @param pageNum	页码
	 * @param pageSize	每页数量
	 * @param listener	监听器
	 */
	public static void queryAppSelectMyRecordList(Context context, String userId, int pageNum, int pageSize, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppAddMyRecord;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("pageNum", pageNum+"");
		params.addParam("pageSize", pageSize+"");
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description 店铺主页-判断用户是否已经收藏了该店铺
	 * @param   merchantId：店铺ID.
	 * @param	userId:用户ID.
	 * @Author liuyuanqi
	 * @Time 2015-9-28
	 */
	public static void queryAppUserStoreCollect(Context context, String merchantId, String userId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppUserStoreCollect;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);
		params.addParam("userId", userId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description 店铺主页-收藏店铺
	 * @param   merchantId：店铺ID.
	 * @param	userId:用户ID.
	 * @Author liuyuanqi
	 * @Time 2015-9-28
	 */
	public static void addAppStoreCollect(Context context, String merchantId, String userId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.addAppStoreCollect;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);
		params.addParam("userId", userId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description 店铺主页-取消收藏店铺
	 * @param   merchantId：店铺ID.
	 * @param	userId:用户ID.
	 * @Author liuyuanqi
	 * @Time 2015-9-28
	 */
	public static void deleteAppStoreCollect(Context context, String merchantId, String userId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.deleteAppStoreCollect;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);
		params.addParam("userId", userId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description 常用发票添加
	 * @param userId 				用户ID
	 * @param ticketDescription 	发票内容
	 * @Time 2015-9-29
	 */
	public static void queryAppAddMemberTicket(Context context, String userId, String ticketDescription, RequestCallback<JSONObject> listener) {
	
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppAddMemberTicket;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("ticketDescription", ticketDescription);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description 常用发票修改
	 * @param memberTicketId 		发票ID
	 * @param ticketDescription 	发票内容
	 * @Time 2015-9-29
	 */
	public static void queryAppUpdateMemberTicket(Context context, String memberTicketId, String ticketDescription, RequestCallback<JSONObject> listener) {
	
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppUpdateMemberTicket;
		params.method = Method.POST;
		params.addParam("memberTicketId", memberTicketId);
		params.addParam("ticketDescription", ticketDescription);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description 常用发票删除
	 * @param memberTicketId 		发票ID
	 * @Time 2015-9-29
	 */
	public static void queryAppDeleteMemberTicket(Context context, String memberTicketId,RequestCallback<JSONObject> listener) {
	
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppDeleteMemberTicket;
		params.method = Method.POST;
		params.addParam("memberTicketId", memberTicketId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 
	 * @Description 常用发票列表
	 * @param userId 用户ID
	 * @Time 2015-9-29
	 */
	public static void queryAppMemberTicketList(Context context, String userId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppMemberTicketList;
		params.method = Method.POST;
		params.addParam("userId", userId);
		
		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 店铺新品列表
	 * @param context
	 * @param merchantId			商家ID
	 * @param page				 	页码
	 * @param pageSize				每页显示条数
	 * @param listener
	 */
	public static void queryAppStoreNewList(Context context, String merchantId, String page,String pageSize,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppStoreNewList;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);
		params.addParam("page", page);
		params.addParam("pageSize", pageSize);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 店铺推荐列表
	 * @param context
	 * @param merchantId			商家ID
	 * @param page				 	页码
	 * @param pageSize				每页显示条数
	 * @param listener
	 */
	public static void queryAppStoreRecommendList(Context context, String merchantId, String page,String pageSize,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppStoreRecommendList;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);
		params.addParam("page", page);
		params.addParam("pageSize", pageSize);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 店铺推荐列表
	 * @param context
	 * @param merchantId			商家ID
	 * @param listener
	 */
	public static void queryMerchantBanner(Context context, String merchantId,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryMerchantBanner;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);

		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 加入购物车
	 *
	 * @param context
	 * @param goods_id     			商品id
	 * @param user_id      			用户id
	 * @param num   				数量
	 * @param sn_id					商品编号id
	 * @param type					商品类型（） 1、普通商品 2 服务商品
	 * @param surprice 				价格
	 * @param spec 					商品规格id
	 * @param seller_id				商家id
	 * @param shoppingType 			购物车类型：
	 *                              1需要显示在购物车中的商品，
	 *                              0不需要显示在购物车中的商品（点击立即结算加入购物车的商品不需要显示在购物车列表中）
	 * @param shopping_id			购物车id集合
	 * @param listener
	 */
	public static void queryAppAddShopCar(Context context, String goods_id,String user_id,String num,String sn_id,String type,String surprice,String spec,String seller_id,String shoppingType, String shopping_id, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.addAppShoppingCart;
		params.method = Method.POST;
		params.addParam("goods_id", goods_id);
		params.addParam("user_id", user_id);
		params.addParam("num", num);
		params.addParam("sn_id", sn_id);
		params.addParam("type", type);
		params.addParam("surprice", surprice);
		params.addParam("spec", spec);
		params.addParam("seller_id", seller_id);
		params.addParam("shoppingType", shoppingType);
		params.addParam("shopping_id", shopping_id);

		// 发送请求
		postRequest(context, params, listener);
	}
	
	
	/**
	 * 查询购物车数量
	 *
	 * @param context
	 * @param user_id  		用户ID
	 * @param shopping_id   购物车ID
	 * @param listener
	 */
	public static void queryAppShoppingCartNum(Context context, String user_id, String shopping_id, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppShoppingCartNum;
		params.method = Method.POST;
		params.addParam("user_id", user_id);
		params.addParam("shopping_id", shopping_id);

		// 发送请求
		postRequest(context, params, listener);
	}
	

	/**
	 * 商家分类列表
	 * @param context
	 * @param merchantId 			商家ID(必填）
	 * @param goodsTypeId 			店铺分类id，-1表示第一级，其余表示取该id下对应的分类
	 * @param listener
	 */
	public static void queryAppStoreGoodsType(Context context, String merchantId,String goodsTypeId,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppStoreGoodsType;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);
		params.addParam("goodsTypeId", goodsTypeId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 订单状态-确认（收货） 接口
	 * @param userId 用户Id
	 * @param orderId 订单Id
	 */
	public static void queryAppOrderStatusConfirm(Context context,String userId, String orderId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppOrderStatusConfirm;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("orderId", orderId);

		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 订单状态-取消 接口
	 * @param userId 用户Id
	 * @param orderId 订单Id
	 * @param orderType订单类型(1实物2服务)
	 * @param closeReason 订单取消理由
	 */
	public static void queryAppOrderStatusCancel(Context context, String userId, String orderId, String orderType, String closeReason, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppOrderStatusCancel;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("orderId", orderId);
		params.addParam("orderType", orderType);
		params.addParam("closeReason", closeReason);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 订单取消理由借口
	 */
	public static void queryAppOrderStatusCancelReason(Context context, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppOrderStatusCancelReason;
		params.method = Method.POST;
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 订单状态-删除 接口
	 */
	public static void queryAppOrderStatusDel(Context context, String userId, String orderId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppOrderStatusDel;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("orderId", orderId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 订单状态-再次购买 接口
	 */
	public static void queryAppOrderPurchaseAgain(Context context, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppOrderPurchaseAgain;
		params.method = Method.POST;
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 订单详情
	 * @param  userId：用户id
	 * @param  orderId:订单id
	 */
	public static void queryAppOrderDetail(Context context, String userId, String orderId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppOrderDetail;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("orderId", orderId);
		
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 进入购物车
	 *
	 * @param userId     用户id
	 */
	public static void queryAppShoppingCart(Context context, String userId, String shopping_id, RequestCallback<JSONObject> listener) {
		
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppShoppingCart;
		params.method = Method.POST;
		params.addParam("user_id", userId);
		params.addParam("shopping_id", shopping_id);
		
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 清空失效商品
	 *
	 * @param user_id     用户id
	 */
	public static void updateAppShoppingCartEmpty(Context context, String user_id, String shopping_id, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.updateAppShoppingCartEmpty;
		params.method = Method.POST;
		params.addParam("user_id", user_id);
		params.addParam("shopping_id", shopping_id);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 修改购物车商品购买数量接口
	 *
	 * @param user_id		用户ID
	 * @param sn_id 		商品编号id
	 * @param shopping_id 	购物车id
	 * @param num         	数量
	 */
	public static void updateAppShoppingCartNum(Context context, String user_id, String sn_id, String shopping_id ,int num, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.updateAppShoppingCartNum;
		params.method = Method.POST;
		params.addParam("user_id", user_id);
		params.addParam("sn_id", sn_id);
		params.addParam("shopping_id", shopping_id);
		params.addParam("num", num+"");

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 删除购物车商品
	 *
	 * @param shopping_id 购物车id(id以逗号分隔 如  "id1,id2,id3,id4" )
	 */
	public static void deleteAppShoppingCart(Context context, String shopping_id, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.deleteAppShoppingCart ;
		params.method = Method.POST;
		params.addParam("shopping_id", shopping_id);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 收获地址列表接口
	 *
	 * @param userId   用户ID
	 * @param pageSize  每页显示条数 不传默认为8
	 * @param currentPage  当前页数 不传默认为1
	 */
	public static void queryAppMemberReceiveAddressList(Context context, String userId ,int pageSize,int currentPage, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppMemberReceiveAddressList ;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("pageSize", pageSize+"");
		params.addParam("currentPage", currentPage+"");

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 查询收获地址详情接口
	 *
	 * @param userId   用户ID
	 * @param receiveAddressId  收货地址id
	 */
	public static void queryAppMemberReceiveAddressDetail(Context context, String userId ,String receiveAddressId, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppMemberReceiveAddressDetail;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("receiveAddressId", receiveAddressId);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 查询默认收获地址接口
	 *
	 * @param userId   用户ID
	 */
	public static void queryAppMemberReceiveAddressDefault(Context context, String userId ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppMemberReceiveAddressDefault;
		params.method = Method.POST;
		params.addParam("userId", userId);

		// 发送请求
		postRequest(context, params, listener);
	}




	/**
	 * 商家分类商品列表
	 * @param context
	 * @param merchantId			商家ID(必填)
	 * @param goodsTypeId			分类ID(必填)
	 * @param orderBy				0代表默认排序，1代表销量排序，2代表价格排序,3表示新品排序（非必填）；默认值0
	 * @param orderStyle			0代表升序，1代表降序	(非必填)，默认值0
	 * @param pageNum				起始页，默认值1
	 * @param pageSize				每页数据量，默认值100
	 * @param listener
	 */
	public static void queryAppStoreGoodsByType   (Context context, String merchantId ,String goodsTypeId,String orderBy,String orderStyle,String pageNum,String pageSize,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppStoreGoodsByType;
		params.method = Method.POST;
		params.addParam("merchantId", merchantId);
		params.addParam("goodsTypeId", goodsTypeId);
		params.addParam("orderBy", orderBy);
		params.addParam("orderStyle", orderStyle);
		params.addParam("pageNum", pageNum);
		params.addParam("pageSize", pageSize);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 意见反馈类型列表
	 */
	public static void queryAppFeedbackType(Context context, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppFeedbackType;
		params.method = Method.POST;

		// 发送请求
		postRequest(context, params, listener);
	}
	
	
	/**
	 * 意见反馈提交
	 * @param context				上下文
	 * @param source				来源 1PC端  2用户APP端(andriod) 3用户APP端（IOS）4商家APP端(andriod) 5商家APP端（IOS）类型:int
	 * @param feedbackUserId		登录用户id 类型:int
	 * @param feedbackUserName		登录用户名 类型:String
	 * @param feedbackClassifyId	反馈类型id 类型:int
	 * @param feedbackClassifyName	反馈类型名称 类型:String
	 * @param feedbackTelephone		反馈手机号 类型:String
	 * @param suggestionContent		反馈内容 类型:String
	 * @param listener
	 */
	public static void queryAppFeedbackTypeSubmit(Context context, String source, String feedbackUserId, 
			String feedbackUserName, String feedbackClassifyId, String feedbackClassifyName, String feedbackTelephone, 
			String suggestionContent, RequestCallback<JSONObject> listener) {
		
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppFeedbackTypeSubmit;
		params.method = Method.POST;
		params.addParam("source", source);
		params.addParam("feedbackUserId", feedbackUserId);
		params.addParam("feedbackUserName", feedbackUserName);
		params.addParam("feedbackClassifyId", feedbackClassifyId);
		params.addParam("feedbackClassifyName", feedbackClassifyName);
		params.addParam("feedbackTelephone", feedbackTelephone);
		params.addParam("suggestionContent", suggestionContent);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 修改收获地址接口
	 *
	 * @param memberReceiveAddressJson   地址详细
	 */
	public static void queryAppUpdateMemberReceiveAddress   (Context context, MemberReceiveAddressJson memberReceiveAddressJson ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppUpdateMemberReceiveAddress;
		params.method = Method.POST;
		String data = JSONParseUtils.getConsigneeInfo(memberReceiveAddressJson,"update");
		params.addParam("memberReceiveAddressJson", data);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 添加收获地址接口
	 *
	 * @param memberReceiveAddressJson   地址详细
	 */
	public static void queryAppAddMemberReceiveAddress   (Context context, MemberReceiveAddressJson memberReceiveAddressJson ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppAddMemberReceiveAddress;
		params.method = Method.POST;
		String data = JSONParseUtils.getConsigneeInfo(memberReceiveAddressJson,"add");
		params.addParam("memberReceiveAddressJson", data);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 删除收获地址接口
	 *
	 * @param userId   用户ID
	 * @param memberReceiveAddressId   收货地址id
	 */
	public static void queryAppDeleteMemberReceiveAddress   (Context context, String userId ,String memberReceiveAddressId,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppDeleteMemberReceiveAddress;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("memberReceiveAddressId", memberReceiveAddressId);

		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 提交订单初始化
	 *
	 * @param user_id   				用户ID
	 * @param shopping_id   			购物车ID
	 * @param shopping_cart_type   		1显示购物车列表 0.不显示
	 */
	public static void submitAppShoppingCartInit   (Context context, String user_id ,String shopping_id,int shopping_cart_type,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.submitAppShoppingCartInit;
		params.method = Method.POST;
		params.addParam("user_id", user_id);
		params.addParam("shopping_id", shopping_id);
		params.addParam("shopping_cart_type", shopping_cart_type+"");

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 查询配送方式
	 *
	 * @param user_id   用户ID
	 * @param shopping_id   购物车ID
	 * @param address_id   收货地址id
	 */
	public static void queryAppDistributionMode   (Context context, String user_id ,String shopping_id,String address_id,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppDistributionMode;
		params.method = Method.POST;
		params.addParam("user_id", user_id);
		params.addParam("shopping_id", shopping_id);
		params.addParam("address_id", address_id);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 查询自提点
	 *
	 * @param seller_id   商家ID
	 */
	public static void queryAppSinceSome   (Context context, String seller_id,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppSinceSome;
		params.method = Method.POST;
		params.addParam("seller_id", seller_id);

		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 查询自提点
	 *
	 * @param address_id 商家ID
	 * @param user_id 商家ID
	 * @param shopping_id 商家ID
	 * @param invoice_type 商家ID
	 * @param order_type 商家ID
	 * @param serviceTime 商家ID
	 * @param distributiondist 商家ID
	 */
	public static void submitAppShoppingCart(Context context, String address_id, String user_id, int order_type, String shopping_id, List<DeliverCommit> distributiondist, String serviceTime,int invoice_type, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.submitAppShoppingCart;
		params.method = Method.POST;
		String data = JSONParseUtils.getOrderCommit(distributiondist);
		params.addParam("address_id", address_id);
		params.addParam("user_id", user_id);
		params.addParam("shopping_id", shopping_id);
		params.addParam("invoice_type", invoice_type+"");
		params.addParam("order_type", order_type+"");
		params.addParam("distributiondist", data);
		if(order_type==2){
			params.addParam("serviceTime", serviceTime);
		}
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 支付查询
	 *
	 * @param order_id   订单Id
	 * @param order_type   订单类型
	 */
	public static void queryAppPay(Context context, String order_id ,int order_type ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppPay;
		params.method = Method.POST;
		params.addParam("order_id", order_id);
		params.addParam("order_type", order_type+"");
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 支付前接口
	 * @param order_pay_id   订单Id
	 */
	public static void alipayBefore(Context context, String order_pay_id, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.alipayBefore;
		params.method = Method.POST;
		params.addParam("order_pay_id", order_pay_id);
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 支付后接口
	 * @param out_trade_no   订单编号
	 */
	public static void alipayAfter(Context context, String out_trade_no, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.alipayAfter;
		params.method = Method.POST;
		params.addParam("out_trade_no", out_trade_no);
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 新增商品收藏
	 *
	 * @param userId  	用户id
	 * @param goodsId   商品id
	 */
	public static void queryAppaddMemberGoodsCollection(Context context, String userId ,String goodsId ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppaddMemberGoodsCollection;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("goodsId", goodsId);
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 取消商品收藏
	 *
	 * @param userId  	用户id
	 * @param goodsId   商品id
	 */
	public static void queryAppDeleteMemberGoodsCollection(Context context, String userId ,String goodsId ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppDeleteMemberGoodsCollection;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("goodsId", goodsId);
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 商品是否已收藏
	 *
	 * @param userId  	用户id
	 * @param goodsId   商品id
	 */
	public static void queryAppHasMemberGoodsCollected(Context context, String userId ,String goodsId ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppHasMemberGoodsCollected;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("goodsId", goodsId);
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 修改手机
	 *
	 * @param validateKey  			旧手机验证码
	 * @param phone   				旧手机号码
	 * @param validateKeyNew  		新手机验证码
	 * @param phoneNew   			新手机号码
	 */
	public static void queryAppUpdatePhone(Context context, String validateKey ,String phone ,String validateKeyNew  ,String phoneNew  ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppUpdatePhone;
		params.method = Method.POST;
		params.addParam("validateKey", validateKey);
		params.addParam("phone", phone);
		params.addParam("validateKeyNew", validateKeyNew);
		params.addParam("phoneNew", phoneNew);
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 修改设置邮件验证身份
	 *
	 * @param userId  					用户id
	 * @param password   				登录密码
	 */
	public static void queryAppUpdateEmailFirstValidate(Context context, String userId ,String password ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppUpdateEmailFirstValidate;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("password", password );
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 修改邮箱发送验证链接
	 *
	 * @param userId  					用户id
	 * @param password   				登录密码
	 * @param email   					新邮箱
	 */
	public static void queryAppUpdateEmail(Context context, String userId ,String password ,String email ,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppUpdateEmail;
		params.method = Method.POST;
		params.addParam("userId", userId);
		params.addParam("password", password );
		params.addParam("email", email  );
		// 发送请求
		postRequest(context, params, listener);
	}
	/**
	 * 修改邮箱发送验证链接
	 *
	 * @param validateKey   			手机验证码
	 * @param phone   					手机号码
	 * @param userId  					用户id
	 * @param password   				支付密码
	 * @param payPasswordGrade   		安全程度  1.低  2.中  3.高
	 */
	public static void queryAppUpdatePayPassword(Context context, String validateKey ,String phone ,String userId ,String password ,String payPasswordGrade,RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.queryAppUpdatePayPassword;
		params.method = Method.POST;
		params.addParam("validateKey", validateKey);
		params.addParam("phone", phone );
		params.addParam("userId", userId  );
		params.addParam("password", password  );
		params.addParam("payPasswordGrade", payPasswordGrade  );
		// 发送请求
		postRequest(context, params, listener);
	}

	/**
	 * 成长值说明接口：统一的获取富文本框，接口
	 *
	 * @param contentType   1 用户协议     2成长值说明  
	 */
	public static void queryAppGrowthVal(Context context, String contentType, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.user_queryAppGrowthVal;
		params.method = Method.POST;
		params.addParam("contentType", contentType);
		// 发送请求
		postRequest(context, params, listener);
	}
	
	/**
	 * 图片上传接口
	 * @param context 	上下文
	 * @param userid	用户id
	 * @param filetype	文件类型  gif,jpg,jpeg,png,bmp
	 * @param data		图片Base64字符串
	 * @param listener
	 */
	public static void mobileImageUpload(Context context, String userid, String data, String filetype, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.mobileImageUpload;
		params.method = Method.POST;
		params.addParam("userid", userid);
		params.addParam("filetype", filetype);
		params.addParam("data", data);
		// 发送请求
		postRequest(context, params, listener);
	}


	/**
	 * 成长值说明接口：统一的获取富文本框，接口
	 *
	 * @param ids				必填(以逗号将要删除的记录id组成字符串）
	 */
	public static void deleteMyRecord(Context context, String ids, RequestCallback<JSONObject> listener) {
		// 拼参数
		RequestParams params = new RequestParams();
		params.url = URLs.deleteMyRecord;
		params.method = Method.POST;
		params.addParam("ids", ids);
		// 发送请求
		postRequest(context, params, listener);
	}
}
