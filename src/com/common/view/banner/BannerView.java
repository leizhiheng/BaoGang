package com.common.view.banner;

import java.util.List;

import android.content.Context;
import android.view.View;

import com.common.view.viewflow.CircleFlowIndicator;
import com.common.view.viewflow.ViewFlow;

/**
 * 首页滑动图
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-27
 */
public class BannerView {
	/** 跳转类型 - 活动详情 */
	public static final String LINK_TYPE_ACTION_DETAIL = "1";
	/** 跳转类型 - 商品详情 */
	public static final String LINK_TYPE_PRODUCT_DETAIL = "2";
	/** 跳转类型 - 便捷服务 */
	public static final String LINK_TYPE_NINBLE_SERVICE = "3";
	
	/**Banner图片轮播**/
	private ViewFlow viewFlow;
	
	/**Banner小圆点**/
	private CircleFlowIndicator indic;
	
	/**Banner图片适配器**/
	private BannerAdapter adapter;
	
	/**图片点击回调**/
	private IOnBannerItenClick onItemClick;
	
	/**上下文**/
	private Context context;
	
	public BannerView(Context context, ViewFlow viewFlow, CircleFlowIndicator indic){
		this.viewFlow = viewFlow;
		this.indic = indic;
		this.indic.setVisibility(View.GONE);
		this.context = context.getApplicationContext();
	}
	
	public void refreshData(List<BannerData> dataList){
		
		if(null == dataList || dataList.size() == 0){
			return;
		}
		
		adapter = new BannerAdapter(context, dataList);
		adapter.setOnItemClickListener(onItemClick);
		
		if (dataList.size() < 1) {
			indic.setVisibility(View.GONE);
		} else {
			indic.setVisibility(View.VISIBLE);
			viewFlow.setAdapter(adapter); // 对viewFlow添加图片
			viewFlow.setmSideBuffer(dataList.size());
			viewFlow.setFlowIndicator(indic);
			viewFlow.setTimeSpan(5000);
			viewFlow.setSelection(dataList.size() * 10); // 设置初始位置
			viewFlow.stopAutoFlowTimer();
			if (dataList.size() <= 1) {
				// 图片大于1才有指示器
				indic.setVisibility(View.GONE);
			} else {
				viewFlow.startAutoFlowTimer(); // 启动自动播放
			}
		}

	}
	
	/**
	 * 
	 * @Description 返回Banner图的个数
	 * @Author blue
	 * @Time 2015-9-24
	 */
	public int getCount() {
		return adapter == null ? 0 : adapter.getCount();
	}
	
	/**
	 * 设置点击回调
	 * @param onItemClick
	 */
	public void setOnItemClickListener(IOnBannerItenClick onItemClick){
		this.onItemClick = onItemClick;
		if(null != adapter){
			adapter.setOnItemClickListener(onItemClick);
		}
	}
	
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChanged();
	}


}
