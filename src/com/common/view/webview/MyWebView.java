package com.common.view.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * 该webview是为了处理图片详情中setBuiltInZoomControls(true)与onTouch事件冲突的问题
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-11
 */
public class MyWebView extends WebView {

	public MyWebView(Context context) {
		super(context);
	}

	public MyWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(null != touchListener){
			touchListener.onTouch(ev);
		}
		return super.dispatchTouchEvent(ev);
	}
	
	private IOnTouchListener touchListener;
	public void setTouchListener(IOnTouchListener touchListener){
		this.touchListener = touchListener;
	}
	
	public interface IOnTouchListener{
		public void onTouch(MotionEvent ev);
	}
}
