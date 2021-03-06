package com.baosteel.qcsh.ui.fragment.commen;

import java.util.List;

import javax.crypto.spec.PSource;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.BaseItemData;
import com.baosteel.qcsh.ui.adapter.SimpleTextAdapter;
import com.common.base.BaseFragment;

import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * 
 * @description 这个Fragment封装了一个ListView，ListView中的数据和Item布局可通过外部设置的Adapter来
 * 				确定
 * @author blue
 * @Time 2015-9-2
 *
 */
public class SimpleTextListViewFragment extends BaseFragment{

	private ListView mListView;
//	private List<? extends BaseItemData> mDatas;
	private SimpleTextAdapter mAdapter;
	private OnItemClickListener mItemClickListener;
	
	public SimpleTextListViewFragment(List<? extends BaseItemData> datas) {
//		mDatas = datas;
		mAdapter = new SimpleTextAdapter(getActivity(), datas);
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_simple_text, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mListView = (ListView) findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mAdapter instanceof SimpleTextAdapter) {
					mAdapter.setSelectedItem(position);
				}
			}
		});
	}
	
	/**
	 * 
	 * @Description 在外部设置Adapter
	 * @Author blue
	 * @Time 2015-9-2
	 */
	private void setAdapter(SimpleTextAdapter adapter) {
		mAdapter = adapter;
	}
	
	/**
	 * 
	 * @Description 设置ListView的点击事件
	 * @Author blue
	 * @Time 2015-9-2
	 */
	private void setOnItemClickListener(OnItemClickListener listener) {
		mItemClickListener = listener;
	}
	
}
