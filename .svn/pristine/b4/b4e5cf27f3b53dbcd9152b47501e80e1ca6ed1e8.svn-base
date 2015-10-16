package com.common.view.fastlink;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.GridView;

import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;
import com.common.view.scrolllayout.PageControlView;
import com.common.view.scrolllayout.ScrollLayout;
import com.common.view.scrolllayout.ScrollLayout.OnScreenChangeListenerDataLoad;

/**
 * 快速链接
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-8-31
 */
public class FastLinkView {

	/** 水平的scrollview **/
	private ScrollLayout mScrollLayout;

	/** 底部圆点 **/
	private PageControlView pageControl;

	private Activity context;
	
	/**
	 * 每页Item的个数
	 */
	private int pageSize = 8;

	public FastLinkView(Activity context, ScrollLayout scrollLayout, PageControlView pageControl) {
		this.context = context;
		this.mScrollLayout = scrollLayout;
		this.pageControl = pageControl;
		this.pageControl.setVisibility(View.GONE);
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 刷新数据
	 * @param dataList
	 */
	public void refreshData(List<FastLinkData> dataList) {

		mScrollLayout.removeAllViews();
		mScrollLayout.snapToScreen(0);

		List<FastLinkData> tempList = null;
		for (int i = 0; i < dataList.size(); i++) {
			
			if(i%pageSize==0){
				tempList = new ArrayList<FastLinkData>();
			}
			tempList.add(dataList.get(i));
			
			//8个一组						代表循环到了最后一个
			if(tempList.size() == pageSize || (i==dataList.size()-1)){
				GridView appPage = new GridView(context);
				LnkToolsGridViewAdapter lnkToolsGridViewAdapter;
				lnkToolsGridViewAdapter = new LnkToolsGridViewAdapter(context, tempList, i);
				appPage.setAdapter(lnkToolsGridViewAdapter);
				appPage.setNumColumns(pageSize / 2);
				lnkToolsGridViewAdapter.setBackItemClickListener(backItemClickListener);
				mScrollLayout.addView(appPage);
			}
			
		}
		
		
		
		// 加载分页
		pageControl.bindScrollViewGroup(mScrollLayout);
		if (dataList.size() > 8) {
			pageControl.setVisibility(View.VISIBLE);
		} else {
			pageControl.setVisibility(View.GONE);
		}
		
		// 加载分页数据
		mScrollLayout.setOnScreenChangeListenerDataLoad(new OnScreenChangeListenerDataLoad() {
			public void onScreenChange(int currentIndex) {
				
			}
		});
	}
	
	BackItemClickListener backItemClickListener;

	public void setOntemClickListener(BackItemClickListener backItemClickListener) {
		this.backItemClickListener = backItemClickListener;
	}

}
