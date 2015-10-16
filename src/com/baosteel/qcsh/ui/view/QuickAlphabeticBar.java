package com.baosteel.qcsh.ui.view;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 字母表
 * 
 * @author liuyuanqi
 * 
 */
public class QuickAlphabeticBar extends ImageButton {
	private TextView mDialogText;
	private Handler mHandler;
	private ListView mList;
	private float mHight;
	private String[] letters = new String[] { "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private HashMap<String, Integer> alphaIndexer;

	public QuickAlphabeticBar(Context context) {
		super(context);
	}

	public QuickAlphabeticBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public QuickAlphabeticBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void init(Activity ctx, TextView dialogText) {
		mDialogText = dialogText;
		mDialogText.setVisibility(View.INVISIBLE);
		mHandler = new Handler();
	}

	public void setListView(ListView mList) {
		this.mList = mList;
	}

	public HashMap<String, Integer> getAlphaIndexer() {
		return this.alphaIndexer;
	}

	public void setAlphaIndexer(HashMap<String, Integer> alphaIndexer) {
		this.alphaIndexer = alphaIndexer;
	}

	public void setHight(float mHight) {
		this.mHight = mHight;
	}

	/**
	 * // System.out.println("ontouch"); //
	 * System.out.println("mHight="+mHight+"  selectindex="+selectIndex); //
	 * System.out.println("key="+key+"  selectindex="+selectIndex); //
	 * System.out.println("pos="+pos);
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try{
			this.mHight = this.getHeight();
			int act = event.getAction();
			float y = event.getY();
			// 计算手指位置，找到对应的段，让mList移动段开头的位置上
			int selectIndex = (int) (y / (mHight / 27));
			if (selectIndex < 27 && selectIndex > 0) {// 防止越界
				String key = letters[selectIndex];
				if (alphaIndexer.containsKey(key)) {
					int pos = alphaIndexer.get(key);
					if (mList.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
						this.mList.setSelectionFromTop(pos + mList.getHeaderViewsCount(), 0);
					} else {
						this.mList.setSelectionFromTop(pos, 0);
					}
				}
				mDialogText.setText(key);
			}
			if (act == MotionEvent.ACTION_DOWN) {
				if (mHandler != null) {
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							if (mDialogText != null && mDialogText.getVisibility() == View.INVISIBLE) {
								mDialogText.setVisibility(VISIBLE);
							}
						}
					});
				}
			} else if (act == MotionEvent.ACTION_UP) {
				if (mHandler != null) {
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							if (mDialogText != null && mDialogText.getVisibility() == View.VISIBLE) {
								mDialogText.setVisibility(INVISIBLE);
							}
						}
					});
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.onTouchEvent(event);
	}
	
	
	/**
	 * 该方法在设置数据后调用
	 * @param alpha
	 */
	public void setAlpha(List<String> sortKeyList) {
		this.alphaIndexer = new HashMap<String, Integer>();

		for (int i = 0; i < sortKeyList.size(); i++) {
			String name = getAlpha(sortKeyList.get(i));
			if (!alphaIndexer.containsKey(name)) {

				// 只记录在list中首次出现的位置
				alphaIndexer.put(name, i);
			}
		}

		setAlphaIndexer(alphaIndexer);
	}
	
	/**
	 * 获取sortKey
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		if (str == null) {
			return "#";
		}
		// 去掉字符串开头和结尾的空格,防止不必要的空格导致的错误。
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase(); // 大写输出
		} else {
			return "#";
		}
	}
}
