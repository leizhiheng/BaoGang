package com.baosteel.qcsh.ui.fragment.myorder;

import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsOrder;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.OrderItem;
import com.baosteel.qcsh.ui.activity.my.order.OrderDetailActivity;
import com.baosteel.qcsh.ui.adapter.EntityAndServiceOrderAdapter;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseFragment;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 实体/服务订单列表
 * @author 雷志恒
 *
 * @todo TODO
 *
 * @date 2015-10-2
 */
public class EntityAndServiceOrderFragment extends BaseFragment implements OnRefreshListener2<ListView>, OnItemClickListener{

	private PullToRefreshListView mListView;
	private EntityAndServiceOrderAdapter mAdapter;
	
	private int mCurPage = 1;
	private int mPageSize = 6;
	
	@Override
	public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_entity_and_service_order, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated( Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initView();
		
		loadData();
	}
	
	
	private void initView() {
		mListView = (PullToRefreshListView) fragmentView.findViewById(R.id.refresh_listview);
		mListView.setOnRefreshListener(this);
		mListView.setOnItemClickListener(this);

		mAdapter = new EntityAndServiceOrderAdapter(mContext, null);
		mListView.setAdapter(mAdapter);
		mListView.setMode(Mode.PULL_FROM_END);
	}
	
	
	
	boolean showDialog = true;
	/**
	 * 
	 * @Description 下载数据
	 * @Author blue
	 * @Time 2015-9-28
	 */
	private void loadData() {
		
		//用户id，订单类型，订单状态
		String userId = BaoGangData.getInstance().getUserId();
		int orderType = ConstantsOrder.ORDER_TYPE_ENTITY_SERVICE;
		int orderStatus = ConstantsOrder.ORDER_STATUS_ALL;
		
		RequestClient.queryAppOrderList(mContext, userId, orderType, orderStatus, mCurPage, mPageSize, new RequestCallback<JSONObject>(showDialog) {
			
			@Override
			public void onFinish(){
				super.onFinish();
				mListView.onRefreshComplete();
				
				//首次加载数据显示对话框，后面则不显示加载对话框
				showDialog = false;
			}
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					
					//隐藏FooterView
					mListView.onRefreshComplete();
					
					//解析订单数据
					List<OrderItem> tempData = JSONParseUtils.getOrderItems(response);
					
					//非空判断
					if(null == tempData || tempData.size() == 0){
						showToast("没有更多数据");
						mListView.setMode(Mode.PULL_FROM_START);
						return;
					}
					
					//第一页，下拉刷新操作
					if(mCurPage <= 1){
						mAdapter.refreshData(tempData);
					}else{
						
						//第二页后，上拉加载数据
						mAdapter.appendData(tempData);
					}
					
					
					//无下一页数据,判断是否还有下一页
					Mode mode = (tempData.size() < mPageSize) ? Mode.PULL_FROM_START : Mode.BOTH;
					mListView.setMode(mode);
					
					//pageIndex++
					if(tempData.size() >= mPageSize){
						mCurPage++;
					}
					
				}
			}
		});
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//获取当前点击的订单
		position -= mListView.getHeadViewCount();
		OrderItem item = (OrderItem) mAdapter.getItem(position);
		
		Intent intent = new Intent(mContext, OrderDetailActivity.class);
		//传入订单状态
		intent.putExtra(OrderDetailActivity.EXTRA_ORDER_STATUS, item.getStatus());
		//传入订单Id
		intent.putExtra(OrderDetailActivity.EXTRA_ORDER_ID, item.getOrderId());
		startActivity(intent);
	}
	
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		mCurPage = 1;
		loadData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		loadData();
	}
}
