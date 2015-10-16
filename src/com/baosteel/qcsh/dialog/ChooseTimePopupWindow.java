package com.baosteel.qcsh.dialog;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.common.view.wheel.OnWheelChangedListener;
import com.common.view.wheel.WheelView;
import com.common.view.wheel.adapter.ArrayWheelAdapter;

public class ChooseTimePopupWindow extends PopupWindow implements OnWheelChangedListener {

	private static final String TAG = "ChooseAreaPopWindow";
	
	private Context mContext;
	/**
	 * popupWindow布局
	 */
	private View mPopView;
	private TextView mBtnOk;
	private TextView mBtnCancel;
	/**
	 * 日期的WheelView控件
	 */
	private WheelView mDate;
	/**
	 * 时间的WheelView控件
	 */
	private WheelView mTime;

	/**
	 * 日期
	 */
	private String[] mDates;
	private String[] mTimes;

	public ChooseTimePopupWindow(Context context) {
		super(context);
		
		mContext = context;
		mPopView = LayoutInflater.from(context).inflate(R.layout.popwindow_choose_time, null);
		mBtnOk = (TextView) mPopView.findViewById(R.id.btn_ok);
		mBtnCancel = (TextView) mPopView.findViewById(R.id.btn_cancel);
		
		setWindow();
		
		initWheel();
	}
	
	private void setWindow() {
		// 设置SelectPicPopupWindow的View
		setContentView(mPopView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		setAnimationStyle(R.style.PopupAnimation);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		setBackgroundDrawable(dw);
		setOutsideTouchable(true);
		update();
	}
	
	private void initWheel() {

		mDate = (WheelView) mPopView.findViewById(R.id.id_day);
		mTime = (WheelView) mPopView.findViewById(R.id.id_time);
		
		mDate.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mDates));
		mTime.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mTimes));
		// 添加change事件
		mDate.addChangingListener(this);
		mTime.addChangingListener(this);

		mDate.setVisibleItems(5);
		mTime.setVisibleItems(5);
	}


	/**
	 * change事件的处理
	 */
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
	}

	
}
