package com.baosteel.qcsh.ui.fragment.myorder;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.OtherOrderAdapter;
import com.common.base.BaseFragment;

public class OtherOrderFragment extends BaseFragment{
	
	private ExpandableListView mListView;
	
	private List<String> mGroupDatas;
	private List<List<String>> mChildItemDatas;
	
	private OtherOrderAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_other_order, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();
		initView();
		loadData();
	}
	
	private void initData() {
		
		mGroupDatas = new ArrayList<String>();
		mChildItemDatas = new ArrayList<List<String>>();
		mAdapter = new OtherOrderAdapter(getActivity(), mGroupDatas, mChildItemDatas);
		
	}
	
	private void initView() {
		mListView = (ExpandableListView) findViewById(R.id.expandable_listview);
		mListView.setAdapter(mAdapter);
		
		mListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if (mChildItemDatas.get(groupPosition).isEmpty()) {
					//如果child list的个数为0，则对应的group item 不展开
					return true;
				}
				return false;
			}
		});
		
		mListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(OtherOrderFragment.this.getActivity()
						, "选择 "+mGroupDatas.get(groupPosition)+"-"+mChildItemDatas.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}
	
	private void loadData() {
		/*
		 * 添加Group数据
		 */
		mGroupDatas.add("旅游类");
		mGroupDatas.add("健康类");
		mGroupDatas.add("安程类");
		mGroupDatas.add("物业服务类");
		
		/*
		 * 旅游类 的child items
		 */
		List<String> tourChildItems = new ArrayList<String>();
		tourChildItems.add("机票订单");
		tourChildItems.add("酒店订单");
		tourChildItems.add("跟团游订单");
		tourChildItems.add("自由行订单");
		tourChildItems.add("自由驾订单");
		tourChildItems.add("游轮订单");
		tourChildItems.add("签证订单");
		tourChildItems.add("旅游保险订单");
		tourChildItems.add("WiFi租赁订单");
		mChildItemDatas.add(tourChildItems);
		
		/*
		 * 健康类 的child items
		 */
		List<String> healthyChildItems = new ArrayList<String>();
		healthyChildItems.add("体检订单");
		healthyChildItems.add("养老现床订单");
		healthyChildItems.add("养老期床订单");
		mChildItemDatas.add(healthyChildItems);
		
		/*
		 * 安程类 的child items
		 */
		List<String> safeTripChildItems = new ArrayList<String>();
		safeTripChildItems.add("车辆保险订单");
		mChildItemDatas.add(safeTripChildItems);
		
		/*
		 * 物业服务类 的child items
		 */
		List<String> propertyChildItems = new ArrayList<String>();
		propertyChildItems.add("故障申报订单");
		mChildItemDatas.add(propertyChildItems);
		
		mAdapter.notifyDataSetChanged();
	}
}