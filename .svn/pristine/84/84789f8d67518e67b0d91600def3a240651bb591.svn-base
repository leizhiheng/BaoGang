package com.baosteel.qcsh.ui.activity.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.model.store.QueryAppStoreNewListBean;
import com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain.CarManageActivity;
import com.baosteel.qcsh.ui.adapter.StoreProductAdapter;
import com.baosteel.qcsh.ui.adapter.TopProdectAdapter;
import com.baosteel.qcsh.ui.view.MyGridView;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Utils;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshGridView;
import com.common.view.pulltorefresh.PullToRefreshListView;
import com.common.view.topbar.HeadView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多店铺活动 Created by kuangyong on 15/9/18.
 */
public class MoreProductsActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<GridView>,
		View.OnClickListener {
	public static final String TITLE = "title";
	public static final String MERCHANTID="merchantId";//店铺id
	public static final String PRO_RECOMMENDED="产品推荐";//产品推荐
	public static final String PRO_NEW="新品发布";//新品发布
	private HeadView headview;// 标题布局
	private String title;// 标题
	private PullToRefreshGridView gvtoplist;// 产品列表
	private String merchantId;//店铺id
	private int page=1;														//当前页
	private int pageSize=10;												//每页大小
	private boolean showDialog=true;//是否显示进度框
	private StoreProductAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_more_pro);
		title = getIntent().getStringExtra(TITLE);
		merchantId=getIntent().getStringExtra(MERCHANTID);
		findView();
		setListener();
		initView();
		getData();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
		this.gvtoplist = (PullToRefreshGridView) findViewById(R.id.gv_top_list);
	}

	private void setListener() {
		adapter=new StoreProductAdapter(mContext,new ArrayList<QueryAppStoreNewListBean.ReturnMapEntity>());
		gvtoplist.setAdapter(adapter);
		// 设定上下拉刷新
		gvtoplist = (PullToRefreshGridView) findViewById(R.id.gv_top_list);
		gvtoplist.setMode(PullToRefreshBase.Mode.BOTH);

		gvtoplist.getLoadingLayoutProxy().setLastUpdatedLabel(Utils.getCurrTiem());
		gvtoplist.getLoadingLayoutProxy().setPullLabel("往下拉更新数据...");
		gvtoplist.getLoadingLayoutProxy().setRefreshingLabel("正在载入中...");
		gvtoplist.getLoadingLayoutProxy().setReleaseLabel("放开更新数据...");

		// 下拉刷新数据
		gvtoplist.setOnRefreshListener(this);
	}

	private void initView() {
		headview.setTitle(title);
		headview.setHeadViewBackGroundColor(getResources().getColor(R.color.red_baosteel));
		headview.setRightImageBtnBackGround(R.drawable.icon_3point);
		headview.showRightImageBtn(View.VISIBLE, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("菜单");
			}
		});
	}

	private void getData() {
		if(PRO_RECOMMENDED.equals(title)){//产品推荐
			getQueryAppStoreRecommendList();
		}else if(PRO_NEW.equals(title)){//新品发布
			getQueryAppStoreNewList();
		}
	}

	/**
	 * 获取新品发布商品列表数据
	 */
	private void getQueryAppStoreNewList() {
		RequestClient.queryAppStoreNewList(mContext, merchantId, page + "", pageSize + "", new RequestCallback<JSONObject>(showDialog) {

			@Override
			public void onFinish() {
				super.onFinish();

				//只有首次加载数据才显示对话框
				showDialog = false;

				gvtoplist.onRefreshComplete();
			}

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					QueryAppStoreNewListBean bean = new Gson().fromJson(response.toString(), QueryAppStoreNewListBean.class);
					//解析数据
					if(null==bean){
						return;
					}
					List<QueryAppStoreNewListBean.ReturnMapEntity> tempData = bean.getReturnMap();
					//非空判断
					if (null == tempData) {
						return;
					}

					//第一页，下拉刷新操作
					if (page <= 1) {
						adapter.refreshData(tempData);
					} else {

						//第二页后，上拉加载数据
						adapter.appendData(tempData);
					}

					//无下一页数据,判断是否还有下一页
					PullToRefreshBase.Mode mode = (tempData.size() < 10) ? PullToRefreshBase.Mode.PULL_FROM_START : PullToRefreshBase.Mode.BOTH;
					gvtoplist.setMode(mode);

					//pageIndex++
					if (tempData.size() >= 10) {
						page++;
					}
				}
			}
		});
	}

	/**
	 * 获取新品发布商品列表数据
	 */
	private void getQueryAppStoreRecommendList() {
		RequestClient.queryAppStoreRecommendList(mContext, merchantId, page + "", pageSize + "", new RequestCallback<JSONObject>(showDialog) {

			@Override
			public void onFinish() {
				super.onFinish();

				//只有首次加载数据才显示对话框
				showDialog = false;

				gvtoplist.onRefreshComplete();
			}

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					QueryAppStoreNewListBean bean = new Gson().fromJson(response.toString(), QueryAppStoreNewListBean.class);
					//解析数据
					if (null == bean) {
						return;
					}
					List<QueryAppStoreNewListBean.ReturnMapEntity> tempData = bean.getReturnMap();
					//非空判断
					if (null == tempData) {
						return;
					}

					//第一页，下拉刷新操作
					if (page <= 1) {
						adapter.refreshData(tempData);
					} else {

						//第二页后，上拉加载数据
						adapter.appendData(tempData);
					}

					//无下一页数据,判断是否还有下一页
					PullToRefreshBase.Mode mode = (tempData.size() < 10) ? PullToRefreshBase.Mode.PULL_FROM_START : PullToRefreshBase.Mode.BOTH;
					gvtoplist.setMode(mode);

					//pageIndex++
					if (tempData.size() >= 10) {
						page++;
					}
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_car_info:// 汽车信息
			startActivity(new Intent(mContext, CarManageActivity.class));
			break;
		case R.id.layout_maintain_type:// 保养类型
			showToast("保养类型");
			break;
		case R.id.layout_selecte_from_order:// 从订单选择
			showToast("从订单选择");
			break;
		case R.id.layout_time:// 保养时间
			showToast("保养时间");
			break;
		case R.id.btn_commit:// 完成
			finish();
			break;
		}
	}


	@Override
	public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
		page=1;
		getData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
		getData();
	}
}