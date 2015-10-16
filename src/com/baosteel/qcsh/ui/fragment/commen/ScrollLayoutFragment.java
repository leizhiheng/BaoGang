package com.baosteel.qcsh.ui.fragment.commen;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baosteel.qcsh.R;
import com.common.base.BaseFragment;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.FastLinkView;
import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;
import com.common.view.scrolllayout.PageControlView;
import com.common.view.scrolllayout.ScrollLayout;

public class ScrollLayoutFragment extends BaseFragment{

	/**快速导航**/
	private FastLinkView mFastLinkView;
	
	/**快速导航数据**/
	private List<FastLinkData> mFastList;
	
	private BackItemClickListener mListener;
	
	/**
	 * 每页Item个数
	 */
	private int pageSize;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_scrolllayout, null);
		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		//初始化控件
		initView();
		
		// 设置数据
		mFastLinkView.refreshData(mFastList);
	}

	private void initView(){
		
		//快速导航图
		ScrollLayout mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayoutTest);
		PageControlView pageControl = (PageControlView) findViewById(R.id.pageControl);
		mFastLinkView = new FastLinkView(mContext, mScrollLayout, pageControl);
		mFastLinkView.setOntemClickListener(mListener);
		mFastLinkView.setPageSize(pageSize);
	
	}
	
	/**
	 * 
	 * @Description 设置每页Item的个数
	 * @Author blue
	 * @Time 2015-9-7
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (mFastLinkView != null) {
			mFastLinkView.setPageSize(pageSize);
		}
	}
	
	/**
	 * 
	 * @Description 设置fastLinkView的显示数据
	 * @Author blue
	 * @Time 2015-9-1
	 */
	public void setData(List<FastLinkData> data) {
		mFastList = data;
		
		if (mFastLinkView != null) {
			mFastLinkView.refreshData(mFastList);
		}
	}
	
	/**
	 * 
	 * @Description 设置fastLinkView监听事件
	 * @Author blue
	 * @Time 2015-9-1
	 */
	public void setFastLickItemClickListener(BackItemClickListener listener) {
		mListener = listener;
	}
	
	
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
	}

}
