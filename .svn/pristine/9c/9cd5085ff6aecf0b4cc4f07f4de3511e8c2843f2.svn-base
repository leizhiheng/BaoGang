package com.baosteel.qcsh.ui.fragment.commen;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.utils.WebViewUtil;
import com.common.base.BaseFragment;

public class WebViewFragment extends BaseFragment{

	private WebView mWebView;
	
	private String mHtmlString;
	
	private int mBgImage = R.drawable.ic_notice_detail;
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_web_view, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initView();
		
		initWebView();
	}
	
	private void initView() {
		mWebView = (WebView) findViewById(R.id.webview);
	}
	
	/**
	 * 
	 * @Description 设置数据
	 * @Author blue
	 * @Time 2015-9-9
	 */
	public void setHtmlString(String htmlString) {
		this.mHtmlString = htmlString;
		
		if (mWebView != null) {
			initWebView();
		}
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {

		if (!TextUtils.isEmpty(mHtmlString)) {
			loadWebData();
		} else {
//			mHtmlString = "暂无信息";
//			loadWebData();
		}
		//设置缩放开关
		mWebView.getSettings().setSupportZoom(true);
		//4.4以上的缩放支持
		if(android.os.Build.VERSION.SDK_INT>=19) {
			mWebView.getSettings().setBuiltInZoomControls(true);
		}
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setDisplayZoomControls(true);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setBlockNetworkImage(false);
		webSettings.setBlockNetworkLoads(false);
		mWebView.setBackgroundColor(0);
		//设置背景，设置背景之前一定要设置背景颜色为透明：mWebView.setBackgroundColor(0);
		mWebView.setBackgroundResource(mBgImage);
//		mWebView.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255
//		mWebView.setVisibility(View.INVISIBLE);
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				// 结束
				super.onPageFinished(view, url);
				mWebView.setVisibility(View.VISIBLE);
			}

		});

	}

	public void loadWebData() {
		if (mWebView != null) {
			mWebView.loadDataWithBaseURL(null,WebViewUtil.initWebViewFor19(mHtmlString), "text/html",
					"utf-8", null);
		}
	}
	
	public void setBgImage(int resId) {
		mBgImage = resId;
		mWebView.setBackgroundResource(mBgImage);
	}
}
