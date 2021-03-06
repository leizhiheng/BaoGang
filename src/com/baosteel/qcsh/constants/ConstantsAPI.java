package com.baosteel.qcsh.constants;


/**
 * API常量
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-24
 */
public class ConstantsAPI {
	
	
	
	//客户端类型

	/**代表用户Android端**/
	public static final String Client_Android 						= "1";
	
	/**代表用户IOS端**/
	public static final String Client_IOS 							= "2";

	/**代表商家Android端**/
	public static final String Client_Business_Android				= "3";

	/**代表商家IOS端**/
	public static final String Client_Business_IOS 				    = "4";


	//模块ID
	/**首页**/
	public static final int ModuleId_Home							= 6;
	
	/**舌尖宝**/
	public static final int ModuleId_Tongue							= 7;
	
	/**旅行宝**/
	public static final int ModuleId_Travel							= 8;
	
	/**国内游**/
	public static final int ModuleId_Travel_Inner					= 9;
	
	/**出境游**/
	public static final int ModuleId_Travel_Outsite					= 10;
	
	/**保险**/
	public static final int ModuleId_Insurance						= 11;
	
	/**Wifi租赁**/
	public static final int ModuleId_Wifi							= 12;
	
	/**乐居宝**/
	public static final int ModuleId_Happy_living					= 13;
	
	/**物业服务**/
	public static final int ModuleId_Tenement_Service				= 14;
		
	/**健康宝**/
	public static final int ModuleId_Health							= 15;
	
	/**社区健康**/
	public static final int ModuleId_Health_Community				= 16;
	
	/**欢娱宝**/
	public static final int ModuleId_Recreation						= 17;
	
	/**趣学宝**/
	public static final int ModuleId_Study							= 18;
	
	
	//商品类型
	/**实物商品**/
	public static final int Goods_Genre_Normal						= 1;
	
	/**服务商品**/
	public static final int Goods_Genre_Service						= 2;
	
	
	
	//商品购买类型
	/**加入购物车**/
	public static final int Shopping_Type_Add_Cart					= 1;
	
	/**立即购买**/
	public static final int Shopping_Type_Buy_Now					= 0;
	
	
	//1、送货上门 2、上门自提、3、不操作
	/**送货上门**/
	public static final int Deliver_Method_1						= 1;
	
	/**上门自提**/
	public static final int Deliver_Method_2						= 2;
	
	/**不操作**/
	public static final int Deliver_Method_3						= 3;

	
	
	
	//统一的获取富文本类型
	/**1用户协议**/
	public static final int Content_Type_1 = 1;
	
	/**2成长值说明**/
	public static final int Content_Type_2 = 2;
	
	/**3积分说明**/
	public static final int Content_Type_3 = 3;
	
	
	
	//上传图片类型gif,jpg,jpeg,png,bmp
	public static final String File_Type_gif = "gif";
	public static final String File_Type_jpg = "jpg";
	public static final String File_Type_jpeg = "jpeg";
	public static final String File_Type_png = "png";
	public static final String File_Type_bmp = "bmp";

	//商品类型
	public static final String PRO_TYPE_COMMON="1";							//1：普通商品；
	public static final String PRO_TYPE_SERVICE="2";						//2,服务商品
	public static final String PRO_TYPE_TRIP="3";							//3：旅游商品
	public static final String PRO_TYPE_PASSENGER_LINER="4";				//4：邮轮产品
	public static final String PRO_TYPE_GROGSHOP="5";						//5：酒店产品
	public static final String PRO_TYPE_insurance="6";						//6：旅游保险
	public static final String PRO_TYPE_VISA="7";							//7：签证
	public static final String PRO_TYPE_WIFI="8";							//8：wifi
	public static final String PRO_TYPE_TRIP_GROGSHOP="9";					//9:旅游专用酒店
}
