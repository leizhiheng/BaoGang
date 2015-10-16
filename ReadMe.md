宝钢 v1.0
======================================

# Http 网络请求数据
	
## 方法一(需要自己解析结果)
  	com.common.net.RequestParams params = new com.common.net.RequestParams();
	params.url = Urls.Login;
	params.addParam("key1", "value1");
	HttpUtils.addRequest(this, params, new Listener<JSONObject>() {
		@Override
		public void onResponse(JSONObject response) {
				//解析返回数据
		}
	});
## 方法二(无需自己解析结果)

   	//请求参数
	Map<String, String> params = new HashMap<String, String>();
	params.put("key1", "value1");
	params.put("key2", "value2");
		
	//请求回调
	ResponeListener<User> callback = new ResponeListener<User>() {

		@Override
		public void onErrorResponse(VolleyError error) {
			//错误回调
		}

		@Override
		public void onResponse(User response) {
			//得到json对应的Bean对象
		}
	};
		
	//请求对象
	Request<User> request = new RequestJSONBean<User>(url, User.class, params, callback);

# 图片加载
  ImageManager.Load(String imgUrl, ImageView imageView);
  
  
# 本地SharedPreferences键值对存储
  Preferences.putString(String key, String value);
  
# 本地数据库管理
  1.添加表-在DatabaseManager里面
  2.对表进行增删改查-在TableDao里面如(ProductDao)
  
 
# 支付宝集成
  1.拷贝libs的jar包 alipay.jar
  2.拷贝com.alipay.andoid.app.lib整个包
  3.拷贝drawable目录alipay开头的所有文件
  4.拷贝drawable-hdpi的alipay开头的所有图片文件
  5.拷贝layout目录的alipay开头的所有布局文件
  6.拷贝values目录下的所有alipay所有资源文件
  7.拷贝com.common.pay.alipay包的所有java文件
  8.向老大要支付宝的参数(老大向商户要)
    public_key			RSA公钥(由商户生成)
    private_key			RSA私钥(由商户生成)
    partner				合作身份者id
    seller_id			收款支付宝账号
    
  9.代码调用如下(^_^是不是很简单，支付宝集成是不是分分钟的事情)
  AlipayUtil aplipay = new AlipayUtil(mContext);
  aplipay.alipayOrder(private_key, partner, seller_id, order_no, order_name, order_desc, order_fee, notify_url);
  aplipay.setPayResultCallBack(new PayCallBack() {
		@Override
		public void payResultCallBack(int type, String resultStatus) {
							
			alipayAfter(orderCode);
		}
  });
  

  
  

	
	