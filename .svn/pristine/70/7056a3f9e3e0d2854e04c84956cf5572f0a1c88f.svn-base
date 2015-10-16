package com.baosteel.qcsh.ui.fragment.commen;

import java.util.List;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.SingleSelectItemData;
import com.baosteel.qcsh.ui.adapter.SingleSelectAdapter;
import com.common.base.BaseFragment;

/**
 * 
 * @description 该类封装了一个单选的ListView，Item包括了左侧的Title和右侧的图标
 * @author blue
 * @Time 2015-9-8
 *
 */
public class SingleSelectFragment extends BaseFragment{

	private ListView mListView;
	
	private SingleSelectAdapter mAdapter;
	private List<SingleSelectItemData> mDatas;
	
	private OnItemClickListener onItemClickListener;
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_single_select, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();
		
		initView();
		
	}
	
	private void initData() {
		mAdapter = new SingleSelectAdapter(getActivity(), mDatas);
	}
	
	private void initView() {
		mListView = (ListView) fragmentView.findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(onItemClickListener);
	}
	
	/**
	 * 
	 * @Description 外部设置ListView的点击事件
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setOnItemClickListener(OnItemClickListener listener) {
		onItemClickListener = listener;
	}
	
	/**
	 * 
	 * @Description 外部设置显示数据
	 * @Author blue
	 * @Time 2015-9-8
	 */
	public void setDatas(List<SingleSelectItemData> datas) {
		mDatas = datas;
		
		if (mAdapter != null) {
			mAdapter.notifyDataSetChanged();
		}
	}
	
	/**
	 * 
	 * @Description 刷新数据
	 * @Author blue
	 * @Time 2015-9-11
	 */
	public void notifyDataSetChanged() {
		if (mAdapter != null) {
			mAdapter.notifyDataSetChanged();
		}
	}
}

