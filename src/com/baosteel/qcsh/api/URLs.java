package com.baosteel.qcsh.api;

/**
 * 服务器请求地址
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-24
 */
public class URLs {
	
	//正式环境
	//public static final String ServerUrl 								= "http://192.168.3.8:8088/baosteel_qcsh_base_app/app/";
	//public static final String IMAGE_URL 								= "http://192.168.3.8:8088/baosteel_qcsh_base_app/app/";
	
	//测试环境
//	public static final String ServerUrl 								= "http://d1-app.java.shovesoft.com/app/";
//	public static final String IMAGE_URL 								= "http://d1-app.java.shovesoft.com/";
	
	
	//服务器地址 & 图片服务器地址-宋青
//	public static final String ServerUrl 								= "http://192.168.3.16:8088/baosteel_qcsh_base_app/app/";
//	public static final String IMAGE_URL 								= "http://192.168.3.16:8088/baosteel_qcsh_base_app/app/";
		
	//服务器地址 & 图片服务器地址-王磊
//	public static final String ServerUrl 								= "http://192.168.3.13:8080/baosteel_qcsh_base_app/app/";
//	public static final String IMAGE_URL 								= "http://192.168.3.13:8080/baosteel_qcsh_base_app/app/";
	
	//服务器地址 & 图片服务器地址-余琦
	public static final String ServerUrl 								= "http://192.168.3.9:8080/baosteel_qcsh_base_app/app/";
	public static final String IMAGE_URL 								= "http://192.168.3.9:8080/baosteel_qcsh_base_app/app/";

	//服务器地址 & 图片服务器地址-李立强
//	public static final String ServerUrl 								= "http://192.168.3.6:8080/baosteel_qcsh_base_app/app/";
//	public static final String IMAGE_URL 								= "http://192.168.3.6:8080/baosteel_qcsh_base_app/app/";
	
	//服务器地址 & 图片服务器地址-程文豪
//	public static final String ServerUrl 								= "http://192.168.3.17:8080/baosteel_qcsh_base_app/app/";
//	public static final String IMAGE_URL 								= "http://192.168.3.17:8080/file_upload/";
	
	/**登录**/
	public static final String user_queryAppLogin 						= ServerUrl + "user/queryAppLogin.do";
	
	/**获取验证码**/
	public static final String user_queryAppReceiveValidateCode 		= ServerUrl + "user/queryAppReceiveValidateCode.do";
	
	/**提交新密码（修改密码和忘记密码共用一个接口）**/
	public static final String user_queryAppUpdatePassword 		 		= ServerUrl + "user/queryAppUpdatePassword.do";
	
	/**手机验证接口**/
	public static final String user_queryAppMobileValidate 				= ServerUrl + "user/queryAppMobileValidate.do";
	
	/**注册**/
	public static final String user_queryAppMobileRegisiter 			= ServerUrl + "user/queryAppMobileRegisiter.do";

	/**商品详情**/
	public static final String queryAppGoodsDetail 						= ServerUrl + "queryAppGoodsDetail.do";

	/**店铺首页信息**/
	public static final String queryAppMerchantInfo 					= ServerUrl + "queryAppMerchantInfo.do";

	/**商品图文详情**/
	public static final String queryAppGoodsIntroduce 					= ServerUrl + "queryAppGoodsIntroduce.do";


	/**根据地区查区商品运费与库存情况**/
	public static final String queryGoodsFreight 						= ServerUrl + "queryGoodsFreight.do";

	/**商品评价**/
	public static final String queryAppGoodsComment 					= ServerUrl + "queryAppGoodsComment.do";

	/**商品规格**/
	public static final String queryAppGoodsSpecValueList 				= ServerUrl + "queryAppGoodsSpecValueList.do";

	/**店铺详情**/
	public static final String queryAppStoreDetail 						= ServerUrl + "queryAppStoreDetail.do";

	/**店铺收藏**/
	public static final String queryAppAddStoreCollectList 				= ServerUrl + "queryAppAddStoreCollectList.do";

	/**收藏列表-取消店铺收藏**/
	public static final String queryAppDeleteStoreCollect 				= ServerUrl + "queryAppDeleteStoreCollect.do";
	
	/**店铺主页-判断用户是否已经收藏了该店铺**/
	public static final String queryAppUserStoreCollect 				= ServerUrl + "queryAppUserStoreCollect.do";

	/**店铺主页-店铺收藏**/
	public static final String addAppStoreCollect 						= ServerUrl + "addAppStoreCollect.do";

	/**店铺主页-取消店铺收藏**/
	public static final String deleteAppStoreCollect 					= ServerUrl + "deleteAppStoreCollect.do";

	/**店铺主页**/
	public static final String queryAppStore 							= ServerUrl + "queryAppStore.do";

	/**店铺主页列表**/
	public static final String queryAppStoreList 						= ServerUrl + "queryAppStoreList.do";

	/**店铺关注／取消关注**/
	public static final String queryAppStoreAttention 					= ServerUrl + "queryAppStoreAttention.do";

	/**店铺新品列表**/
	public static final String queryAppStoreNewList 					= ServerUrl + "queryAppStoreNewList.do";

	/**店铺推荐列表**/
	public static final String queryAppStoreRecommendList 				= ServerUrl + "queryAppStoreRecommendList.do";

	/**商家分类商品列表**/
	public static final String queryAppStoreGoodsByType 				= ServerUrl + "queryAppStoreGoodsByType.do";

	/**店铺执照**/
	public static final String queryAppStoreLicense 					= ServerUrl + "queryAppStoreLicense.do";

	/**商家Banner**/
	public static final String queryMerchantBanner 						= ServerUrl + "queryMerchantBanner.do";

	/** 商家分类列表 **/
	public static final String queryAppStoreGoodsType 					= ServerUrl + "queryAppStoreGoodsType.do";


	/**加入购物车**/
	public static final String addAppShoppingCart 						= ServerUrl + "addAppShoppingCart.do";
	/**商家评价**/
	public static final String user_queryAppStoreComment 				= ServerUrl + "queryAppStoreComment.do";
	/**立即购买	**/
	public static final String user_queryAppBuyNow 						= ServerUrl + "queryAppBuyNow.do";
	/**计算运费**/
	public static final String user_queryAppComputeFreightFee			= ServerUrl + "queryAppComputeFreightFee.do";
	/**进入购物车**/
	public static final String user_queryAppEnterShopCar				= ServerUrl + "queryAppEnterShopCar.do";
	/**查询购物车数量**/
	public static final String queryAppShoppingCartNum					= ServerUrl + "queryAppShoppingCartNum.do";
	/**清空失效商品**/
	public static final String user_queryAppClearInvalidGoods			= ServerUrl + "queryAppClearInvalidGoods.do";
	/**修改购物车商品购买数量**/
	public static final String user_queryAppUpdateShopCarGoodsNum		= ServerUrl + "queryAppUpdateShopCarGoodsNum.do";
	/**确认订单初始化数据**/
	public static final String user_queryAppOrderConfirmInit			= ServerUrl + "queryAppOrderConfirmInit.do";
	/**收获地址列表**/
	public static final String user_queryAppReceiveAddressList			= ServerUrl + "queryAppReceiveAddressList.do";
	/**修改收获地址**/
	public static final String user_queryAppUpdateReceiveAddress		= ServerUrl + "queryAppUpdateReceiveAddress.do";
	/**添加收获地址**/
	public static final String user_queryAppAddReceiveAddress			= ServerUrl + "queryAppAddReceiveAddress.do";
	/**删除收获地址**/
	public static final String user_queryAppDeleteReceiveAddress		= ServerUrl + "queryAppDeleteReceiveAddress.do";
	/**配送方式**/
	public static final String user_queryAppSelectSendMethod			= ServerUrl + "queryAppSelectSendMethod.do";
	/**选择自提点**/
	public static final String user_queryAppSelectSelfReceivePoint		= ServerUrl + "queryAppSelectSelfReceivePoint.do";
	/**付款接口**/
	public static final String user_queryAppPayMoney					= ServerUrl + "queryAppPayMoney.do";
	/**订单列表**/
	public static final String user_queryAppOrderList					= ServerUrl + "order/queryAppOrderList.do";
	/**订单取消理由**/
	public static final String user_queryAppOrderStatusCancelReason		= ServerUrl + "order/queryAppOrderStatusCancelReason.do";
	/**订单状态取消**/
	public static final String user_queryAppOrderStatusCancel			= ServerUrl + "order/queryAppOrderStatusCancel.do";
	/**订单状态确认**/
	public static final String user_queryAppOrderStatusConfirm			= ServerUrl + "order/queryAppOrderStatusConfirm.do";
	/**订单物流**/
	public static final String user_queryAppOrderLogistics				= ServerUrl + "queryAppOrderLogistics.do";
	/**订单再次购买**/
	public static final String user_queryAppOrderPurchaseAgain			= ServerUrl + "queryAppOrderPurchaseAgain.do";
	/**订单详情**/
	public static final String user_queryAppOrderDetail					= ServerUrl + "queryAppOrderDetail.do";
	/**订单状态删除**/
	public static final String user_queryAppOrderStatusDel				= ServerUrl + "order/queryAppOrderStatusDel.do";
	/**订单退换货原因**/
	public static final String user_queryAppOrderReturn					= ServerUrl + "queryAppOrderReturn.do";
	/**订单提交退货**/
	public static final String user_queryAppOrderReturnSubmit			= ServerUrl + "queryAppOrderReturnSubmit.do";
	/**订单退货详情**/
	public static final String user_queryAppOrderReturnDetail			= ServerUrl + "queryAppOrderReturnDetail.do";
	/**订单修改退货**/
	public static final String user_queryAppOrderReturnUpdata			= ServerUrl + "queryAppOrderReturnUpdata.do";
	/**订单退换货记录**/
	public static final String user_queryAppOrderReturnRecord			= ServerUrl + "queryAppOrderReturnRecord.do";
	/**订单退换货寄回商品**/
	public static final String user_queryAppOrderReturnSendGoods		= ServerUrl + "queryAppOrderReturnSendGoods.do";
	/**订单退换货寄回物流选择**/
	public static final String user_queryAppOrderReturnLogistics		= ServerUrl + "queryAppOrderReturnLogistics.do";
	/**订单退换货平台介入**/
	public static final String user_queryAppOrderReturnPlatform			= ServerUrl + "queryAppOrderReturnPlatform.do";
	/**预订商品提交**/
	public static final String user_queryAppBookGoodsSubmit				= ServerUrl + "queryAppBookGoodsSubmit.do";


	//============ 首页-begin ====================
	
	/** Banner图 */
	public static final String queryAPPBanner 			     			= ServerUrl + "queryAppBanner.do";
	
	/** 首页七个宝（或者七个宝的子模块） 获取*/
	public static final String queryAppClassify 			     		= ServerUrl + "queryAppClassify.do";
	
	/** 首页-品类推荐*/
	public static final String queryAppGoodsTypeSuggest 			     = ServerUrl + "queryAppGoodsTypeSuggest.do";
	
	/** 首页-热门服务**/
	public static final String queryAppHotService 			     		= ServerUrl + "queryAppHotService.do";
	
	/** 首页-猜你喜欢**/
	public static final String queryAppGuessYouLike 			     	= ServerUrl + "queryAppGuessYouLike.do";
	
	//============ 首页-end ====================


	//个人中心
	/**会员等级**/
	public static final String user_queryAppVIPLevel 					= ServerUrl + "user/queryAppVIPLevel.do";

	/**个人中心初始化**/
	public static final String user_queryAppUserInfo 					= ServerUrl + "user/queryAppUserInfo.do";

	/**个人中心数据修改**/
	public static final String user_queryAppUpdateUserInfo 				= ServerUrl + "user/queryAppUpdateUserInfo.do";

	/**个人中心版本更新**/
	public static final String user_queryAppUpdateVersion				= ServerUrl + "user/queryAppUpdateVersion.do";

	/**成长值说明接口**/
	public static final String user_queryAppGrowthVal					= ServerUrl + "user/queryAppGrowthVal.do";


	/** 商品列表 */
	public static final String queryAppGoodsList 			     		= ServerUrl + "queryAppGoodsList.do";
	/** 首页分类 */
	public static final String queryAppClassifyList 			     	= ServerUrl + "queryAppClassifyList.do";
	/** 商品筛选-属性列表 */
	public static final String queryAppGoodsAttr 			     		= ServerUrl + "queryAppGoodsAttr.do";
	/** 商品筛选-属性列表-属性值 */
	public static final String queryAppGoodsAttrValueList 			     = ServerUrl + "queryAppGoodsAttrValueList.do";


	/** 加入购物车*/
	public static final String queryAppAddShopCar 			     = ServerUrl + "queryAppAddShopCar.do";

	/** 进入购物车*/
	public static final String queryAppShoppingCart 			     = ServerUrl + "queryAppShoppingCart.do";

	/** 清空失效商品*/
	public static final String updateAppShoppingCartEmpty  			     = ServerUrl + "updateAppShoppingCartEmpty.do";

	/** 修改购物车商品购买数量接口*/
	public static final String updateAppShoppingCartNum   			     = ServerUrl + "updateAppShoppingCartNum.do";

	/** 删除购物车商品*/
	public static final String deleteAppShoppingCart   			     = ServerUrl + "deleteAppShoppingCart.do";


	/** 收获地址列表接口*/
	public static final String queryAppMemberReceiveAddressList 			     = ServerUrl + "queryAppMemberReceiveAddressList.do";

	/** 查询收获地址详情接口*/
	public static final String queryAppMemberReceiveAddressDetail 			     = ServerUrl + "queryAppMemberReceiveAddressDetail.do";

	/** 查询默认收获地址接口*/
	public static final String queryAppMemberReceiveAddressDefault  			     = ServerUrl + "queryAppMemberReceiveAddressDefault.do";

	/** 修改收获地址接口*/
	public static final String queryAppUpdateMemberReceiveAddress   			     = ServerUrl + "queryAppUpdateMemberReceiveAddress.do";

	/** 添加收获地址接口*/
	public static final String queryAppAddMemberReceiveAddress   			     = ServerUrl + "queryAppAddMemberReceiveAddress.do";

	/** 添加收获地址接口*/
	public static final String queryAppDeleteMemberReceiveAddress   			     = ServerUrl + "queryAppDeleteMemberReceiveAddress.do";


	
	/** 商品收藏列表 **/
	public static final String queryAppMemberGoodsCollectionList 		= ServerUrl + "queryAppMemberGoodsCollectionList.do";
	
	/** 店铺收藏列表 **/
	public static final String queryAppSelectCollectList 				= ServerUrl + "queryAppSelectCollectList.do";
	
	/** 我的足迹列表 **/
	public static final String queryAppAddMyRecord 						= ServerUrl + "user/queryAppAddMyRecord.do";
	
	/** 常用发票添加 **/
	public static final String queryAppAddMemberTicket 					= ServerUrl + "queryAppAddMemberTicket.do";
	
	/** 常用发票修改 **/
	public static final String queryAppUpdateMemberTicket 				= ServerUrl + "queryAppUpdateMemberTicket.do";
	
	/** 常用发票列表 **/
	public static final String queryAppMemberTicketList 				= ServerUrl + "queryAppMemberTicketList.do";
	
	/** 常用发票删除 **/
	public static final String queryAppDeleteMemberTicket 				= ServerUrl + "queryAppDeleteMemberTicket.do";


	/** 意见反馈类型列表 **/
	public static final String queryAppFeedbackType 					= ServerUrl + "queryAppFeedbackType.do";
	
	/** 意见反馈提交 **/
	public static final String queryAppFeedbackTypeSubmit 				= ServerUrl + "queryAppFeedbackTypeSubmit.do";

	/** 提交订单初始化 **/
	public static final String submitAppShoppingCartInit				= ServerUrl + "submitAppShoppingCartInit.do";

	/** 配送方式 **/
	public static final String queryAppDistributionMode				= ServerUrl + "queryAppDistributionMode.do";

	/** 自提点 **/
	public static final String queryAppSinceSome				= ServerUrl + "queryAppSinceSome.do";

	/** 提交订单 **/
	public static final String submitAppShoppingCart				= ServerUrl + "submitAppShoppingCart.do";

	/** 支付接口 **/
	public static final String queryAppPay				= ServerUrl + "order/queryAppPay.do";

	/** 新增商品收藏接口 **/
	public static final String queryAppaddMemberGoodsCollection			= ServerUrl + "queryAppaddMemberGoodsCollection.do";

	/** 取消商品收藏接口 **/
	public static final String queryAppDeleteMemberGoodsCollection		= ServerUrl + "queryAppDeleteMemberGoodsCollection.do";

	/** 商品是否已收藏 **/
	public static final String queryAppHasMemberGoodsCollected			= ServerUrl + "queryAppHasMemberGoodsCollected.do";

	/** 修改手机号码 **/
	public static final String queryAppUpdatePhone						= ServerUrl + "user/queryAppUpdatePhone.do";

	/** 修改设置邮件验证身份 **/
	public static final String queryAppUpdateEmailFirstValidate			= ServerUrl + "user/queryAppUpdateEmailFirstValidate.do";

	/** 修改邮箱发送验证链接 **/
	public static final String queryAppUpdateEmail						= ServerUrl + "user/queryAppUpdateEmail.do";

	/** 修改手机支付密码 **/
	public static final String queryAppUpdatePayPassword				= ServerUrl + "user/queryAppUpdatePayPassword.do";

	
	/** 支付前接口 **/
	public static final String alipayBefore								= ServerUrl + "alipayBefore.do";
	
	/** 支付后接口 **/
	public static final String alipayAfter								= ServerUrl + "alipayAfter.do";

	/** 足迹删除 **/
	public static final String deleteMyRecord							= ServerUrl + "user/deleteMyRecord.do";

	
	/** 图片上传接口 **/
	public static final String mobileImageUpload						= IMAGE_URL + "file_upload/mobileImageUpload.do";
	
	
}
