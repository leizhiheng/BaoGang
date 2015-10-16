package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.os.Bundle;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.fragment.commen.WebViewFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 
 * @description 页面：乐居宝-》物业服务-》小区通知-》通知详情（须知详情）
 * @author blue
 * @Time 2015-9-9
 *
 */
public class NoticeDetailActivity extends BaseActivity{

	public static final String TITLE_NOTICE_DETAIL = "通知详情";
	public static final String TITLE_SHOULD_KNOW_DETAIL = "须知详情";
	public static final String TITLE_PAYMENT_PROTOL = "自助缴费服务协议";
	
	private WebViewFragment mWebViewFragment;
	
	private String mTitle;
	
	private String mHtmlString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice_detail);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
	}
	
	private void initView() {
		setTitle(mTitle);
		mTitleBar.showRightIcon(TitleBar.ICON_INDEX_MENU);
		
		mWebViewFragment = (WebViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_web_view);
	}
	
	private void loadData() {
		if (TITLE_NOTICE_DETAIL.equals(mTitle)) {
			showToast("获取通知详情");
		} else if (TITLE_SHOULD_KNOW_DETAIL.equals(mTitle)) {
			showToast("获取须知详情");
		} else if (TITLE_PAYMENT_PROTOL.equals(mTitle)){
			showToast("获取自助缴费服务协议");
			mWebViewFragment.setBgImage(R.drawable.ic_payment_protol);
		}
		
		mWebViewFragment.setHtmlString(mHtmlString);
	}
}
