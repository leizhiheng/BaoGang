package com.baosteel.qcsh.ui.fragment.product.filter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.R.integer;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.FilterGridItem;
import com.baosteel.qcsh.ui.adapter.FilterListConditionAdapter;
import com.baosteel.qcsh.ui.adapter.FilterRank2Adapter;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.view.other.ListViewInScrollView;

public class FilterProductRank2Fragment extends BaseFilterProductFragment implements OnClickListener{

	private TextView mBtnOk;
	private TextView mBtnCancel;
	
	private ListViewInScrollView mListView;

	private List<FilterGridItem> mListItems;
	private FilterRank2Adapter mAdapter;
	
	/**
	 * 属性的名称
	 */
	private String title;
	
	/**
	 * 属性Id
	 */
	private String attrId;
	
	private int mSelectedIndex;
	
	/**
	 * 
	 * @description 筛选完后，将“确定”按钮点击事件通知到外部
	 * @author blue
	 * @Time 2015-9-23
	 *
	 */
	public interface OnFilterOkListener{
		void onFilterOk(int position, List<FilterGridItem> selectItems);
	}
	
	protected OnFilterOkListener mListener;
	
	public void setOnFilterOkListener(OnFilterOkListener listener) {
		this.mListener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_filter_product_rank2, null);
		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
		initView();
		
		loadAttrValuesList();

//		loadData();
	}
	
	private void initData() {

		mListItems = new ArrayList<FilterGridItem>();
		mAdapter = new FilterRank2Adapter(getActivity(), mListItems);
	}

	private void initView() {
		
		findViewById(R.id.btn_clear).setVisibility(View.INVISIBLE);
		mBtnCancel = (TextView) findViewById(R.id.btn_cancel);
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mBtnCancel.setOnClickListener(this);
		mBtnOk.setOnClickListener(this);
		
		mListView = (ListViewInScrollView) findViewById(R.id.listview);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if (position == 0) {
					mListItems.get(0).setSelected(true);
					for (int i = 1; i < mListItems.size(); i++) {
						if (mListItems.get(i).isSelected()) {
							mListItems.get(i).setSelected(false);
						}
					}
				} else {
					mListItems.get(position).setSelected(!mListItems.get(position).isSelected());
					mListItems.get(0).setSelected(false);
				}
				mSelectedIndex = position;
				mAdapter.notifyDataSetChanged();
			}
		});
		
	}

	private void loadData() {

		FilterGridItem allItem = new FilterGridItem("全部");
		allItem.setSelected(true);
		
		mListItems.clear();
		mListItems.add(allItem);

		//ListView中的筛选条件
		for (int i = 0; i < 12; i++) {
			mListItems.add(new FilterGridItem(title + " " + i));
		}
		
		mAdapter.notifyDataSetChanged();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 * @Description 设置属性Id，并根据属性Id请求属性值列表
	 * @Author blue
	 * @Time 2015-9-25
	 */
	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}
	
	private void loadAttrValuesList() {
		RequestClient.queryAppGoodsAttrValueList(mContext, attrId, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(FilterProductRank2Fragment.this.getActivity(), response)) {
					mListItems = JSONParseUtils.getFilterItem(response, true);
					Log.d(TAG, "loadFilterAttrs onResponse mListItems Size = "+mListItems.size());
					
					if (mListItems.size() == 1) {
						loadData();
					}
					mAdapter.setData(mListItems);
				}
			}
		});
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cancel:
			back();
			break;
		case R.id.btn_ok:
			if (mListener != null) {
				mListener.onFilterOk(mSelectedIndex, filterOkDatas());
			}
			back();
			break;

		default:
			break;
		}
	}
	
	/**
	 * 
	 * @Description 获取需要返回的筛选条件,删除未选中的属性值
	 * @Author blue
	 * @Time 2015-10-12
	 */
	private List<FilterGridItem> filterOkDatas() {
		
		List<FilterGridItem> items = new ArrayList<FilterGridItem>();
		items.addAll(mListItems);
		for (int i = items.size() - 1; i >= 0; i--) {
			/*
			 * 删除未被选中的属性值
			 */
			if (!items.get(i).isSelected()) {
				items.remove(i);
			}
		}
		return items;
	}
}

