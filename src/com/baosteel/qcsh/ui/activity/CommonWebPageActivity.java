package com.baosteel.qcsh.ui.activity;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.WebViewUtil;
import com.common.base.BaseActivity;

public class CommonWebPageActivity extends BaseActivity {
	
	private final static String TAG = "CommonWebPageActivity";
	
	/**标题**/
	public static final String Title 			= "Title";
	
	/**内容类型**/
	public static final String Content_Type 	= "Content_Type";
	
	
	/**显示内容的webview**/
	private WebView mWebView;
	
	/**中间的标题**/
	private TextView middle_title_tv;
	
	/**内容类型 看ConstantsAPI.Content_Type**/
	private int contentType;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_webpage);
		setTitle("");
		
		initView();
		
		initData();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initView() {

		String title  = getIntent().getStringExtra(Title);
		contentType = getIntent().getIntExtra(Content_Type, 0);

		mWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webSettings.setBlockNetworkImage(false);
		webSettings.setBlockNetworkLoads(false);
		mWebView.setBackgroundResource(android.R.color.transparent); // 设置背景色
		mWebView.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255

	}

	/**
	 * 获取数据
	 */
	private void initData() {

		//请求内容
		RequestClient.queryAppGrowthVal(mContext, contentType+"", new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if(JSONParseUtils.isSuccessRequest(mContext, response)){
					response = response.optJSONObject("returnMap");
					String title = response.optString("title");
					String content = response.optString("content");
					
					//设置标题，与显示内容
					setTitle(title);
					loadWebData(content);
				}
			}
		});
	}

	/**
	 * 加载内容
	 * @param dataHtml
	 */
	private void loadWebData(String dataHtml) {
		mWebView.loadDataWithBaseURL(null, WebViewUtil.initWebViewFor19(dataHtml), "text/html", "utf-8", null);
	}
}
