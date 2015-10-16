package com.baosteel.qcsh.dialog;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.ui.adapter.KeywordsAdapter;
import com.baosteel.qcsh.ui.adapter.KeywordsAdapter.OnItemSelctedListener;

public class HealthyProductFilterPopwindow extends PopupWindow implements OnItemClickListener, OnClickListener{

	private Context mContext;
	private View mPopView;
	
	/** 一级分类 **/
	private ListView dataListview1;

	/** 二级分类 **/
	private ListView dataListview2;

	/** 三级分类 **/
	private ListView dataListview3;

	/** 一级分类适配器 **/
	private KeywordsAdapter adapter1;

	/** 一级分类适配器 **/
	private KeywordsAdapter adapter2;

	/** 二级分类适配器 **/
	private KeywordsAdapter adapter3;
	
	/** 取消按钮 */
	private TextView mBtnCancel;
	/** 清楚按钮 */
	private TextView mBtnClean;
	/** 确定按钮 */
	private TextView mBtnOk;
	/** 底部半透明区域 */
	private View mViewOutside;
	
	public HealthyProductFilterPopwindow(Context context) {
		super(context);
		
		init(context);
	}
	
	public HealthyProductFilterPopwindow(Context context, OnClickListener listener) {
		super(context);
		
		init(context);
	}
	
	private void init(Context context) {
		mContext = context;
		mPopView = LayoutInflater.from(context).inflate(R.layout.popwindow_healthy_product_fileter, null);
		
		setWindow();
		
		initData();
		
		initView();
	}
	
	private void setWindow() {
		// 设置SelectPicPopupWindow的View
		setContentView(mPopView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		setHeight(LayoutParams.MATCH_PARENT);
		// 设置Dialog之外区域的透明度
		//backgroundAlpha(0.7f);
		// 设置外部点击隐藏popwindow
		setOutsideTouchable(true);
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
	
	/**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity)mContext).getWindow().setAttributes(lp);
    }
    

	/**
	 * 初始化数据
	 */
	public void initView() {
		
		dataListview1 = (ListView) mPopView.findViewById(R.id.list_filter_rank_1);
		dataListview2 = (ListView) mPopView.findViewById(R.id.list_filter_rank_2);
		dataListview3 = (ListView) mPopView.findViewById(R.id.list_filter_rank_3);

		int color = Constants.getTypeColor(Constants.TYPE_HEALTH);
		adapter1 = new KeywordsAdapter(mContext, getDataRank1(), color, true);
		adapter1.setRank(1);
		adapter2 = new KeywordsAdapter(mContext, getDataRank2(0), color, false);
		adapter2.setRank(2);
		adapter3 = new KeywordsAdapter(mContext, getDataRank3(0, 0), color, false, true);
		adapter3.setRank(3);
		adapter1.setOnItemSelctedListener(new OnItemSelctedListener() {
			
			@Override
			public void onItemSelected(int position) {
				/*
				 * 一级分类选中项改变时，回调该方法
				 */
				adapter2.setData(getDataRank2(position));//重新设置二级分类数据
				adapter2.selectItem(0);//设置二级分类中默认选中第一项
				dataListview2.requestFocusFromTouch();//获取焦点，否则执行setSelection时会失效
				dataListview2.setSelection(0);//ListView显示第一项
				adapter2.notifyDataSetChanged();
			}
		});
		adapter2.setOnItemSelctedListener(new OnItemSelctedListener() {
			
			@Override
			public void onItemSelected(int position) {
				/*
				 * 一级分类选中项改变时，回调该方法
				 */
				adapter3.setData(getDataRank3(position, adapter1.getSelectedItemIndex()));
				adapter3.selectItem(0);//重新设置三级分类数据
				dataListview3.requestFocusFromTouch();//获取焦点，否则执行setSelection时会失效
				dataListview3.setSelection(0);//ListView显示第一项
				adapter3.notifyDataSetChanged();
			}
		});
		
		dataListview1.setAdapter(adapter1);
		dataListview2.setAdapter(adapter2);
		dataListview3.setAdapter(adapter3);

		dataListview1.setOnItemClickListener(this);
		dataListview2.setOnItemClickListener(this);
		dataListview3.setOnItemClickListener(this);
		
		mBtnOk = (TextView) mPopView.findViewById(R.id.btn_ok);
		mBtnCancel = (TextView) mPopView.findViewById(R.id.btn_cancel);
		mBtnClean = (TextView) mPopView.findViewById(R.id.btn_clean);
		mViewOutside = mPopView.findViewById(R.id.layout_outsize_area);
		
		mBtnOk.setOnClickListener(this);
		mBtnCancel.setOnClickListener(this);
		mBtnClean.setOnClickListener(this);
		mViewOutside.setOnClickListener(this);
	}

	/**
	 * 
	 * @Description 获第1级分类数据
	 * @Author blue
	 * @Time 2015-9-18
	 */
	private List<ProductCategory> getDataRank1() {
		List<ProductCategory> categories = new ArrayList<ProductCategory>();
		for (int i = 0; i < 15; i++) {
			categories.add(new ProductCategory("分类"+i));
		}
		
		return categories;
	}
	
	/**
	 * 
	 * @Description 获取第2级分类数据
	 * @param fatherItemPosition :上一级分类在上一级分类列表中的Position
	 * @Author blue
	 * @Time 2015-9-18
	 */
	private List<ProductCategory> getDataRank2(int fatherItemPosition) {
		List<ProductCategory> categories = new ArrayList<ProductCategory>();
		for (int i = 0; i < 15; i++) {
			categories.add(new ProductCategory("分类"+fatherItemPosition+"_"+i));
		}
		return categories;
	}
	
	/**
	 * 
	 * @Description 获取第3级分类的数据
	 * @param fatherItemPosition :上一级分类在上一级分类列表中的Position
	 * @param grandfatherItemPosition 上上级分类在上上级分类列表中的position
	 * @Author blue
	 * @Time 2015-9-18
	 */
	private List<ProductCategory> getDataRank3(int fatherItemPosition, int grandfatherItemPosition) {
		List<ProductCategory> categories = new ArrayList<ProductCategory>();
		for (int i = 0; i < 15; i++) {
			categories.add(new ProductCategory("分类_"+grandfatherItemPosition+"_"+fatherItemPosition+"_"+i));
		}
		return categories;
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
		case R.id.list_filter_rank_1:
			
			adapter1.selectItem(position);
			break;

		case R.id.list_filter_rank_2:

			adapter2.selectItem(position);
			
			break;
		case R.id.list_filter_rank_3:

			adapter3.selectItem(position);
			
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_outsize_area:
		case R.id.btn_cancel:
			dismiss();
		case R.id.btn_clean:
			clean();
			break;
			
		case R.id.btn_ok:
			commit();
			dismiss();
			break;

		default:
			break;
		}
	}
	
	/**
	 * 
	 * @Description 清楚筛选条件
	 * @Author blue
	 * @Time 2015-9-18
	 */
	private void clean() {
		Toast.makeText(mContext, "to clean filter condition", Toast.LENGTH_SHORT).show();
	}
	
	private void commit() {
		Toast.makeText(mContext, "filter ok", Toast.LENGTH_SHORT).show();
	}

}
