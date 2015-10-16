package com.baosteel.qcsh.ui.activity.home;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.bean.Product;
import com.baosteel.qcsh.model.KeyValue;
import com.baosteel.qcsh.ui.activity.prodcut.TongueTipProductDetailsActivity;
import com.baosteel.qcsh.ui.adapter.GroupBuyAdapter;
import com.baosteel.qcsh.ui.adapter.GroupBuyAdapter.OnProductClick;
import com.baosteel.qcsh.ui.adapter.HscViewAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.Utils;
import com.common.base.BaseActivity;
import com.common.view.listview.HorizontialListView;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 健康点购买产品
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-9
 */
public class HealthPointActivity extends BaseActivity implements OnItemClickListener, OnRefreshListener<ListView>, OnProductClick{
	
	/**标题栏**/
	private TitleBar mTitleBar;
	
	/**顶部水平分类ListView**/
	public HorizontialListView lv_h;
	
	/**顶部垂直分页GridView**/
	public PullToRefreshListView listView;
	
	/**水平adapter**/
	private HscViewAdapter hAdapter;

	/**商品列表适配器**/
	private GroupBuyAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_buy);
		
		initView();
		
		initData();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {

		//标题
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("健康点");
		mTitleBar.setBackgroud(R.color.index_red);
		
		// 设置横条中每个条目对应的单击事件
		lv_h = (HorizontialListView) findViewById(R.id.lv_h);
		lv_h.setOnItemClickListener(this);
		hAdapter = new HscViewAdapter(mContext);
		lv_h.setAdapter(hAdapter);
		lv_h.setOnItemClickListener(this);

		// 设定上下拉刷新
		listView = (PullToRefreshListView) findViewById(R.id.gv_top_list);
		listView.setEnabled(false);
		listView.setMode(Mode.BOTH);
		listView.setMode(Mode.PULL_FROM_START);
		listView.getLoadingLayoutProxy().setLastUpdatedLabel(Utils.getCurrTiem());
		listView.getLoadingLayoutProxy().setPullLabel("往下拉更新数据...");
		listView.getLoadingLayoutProxy().setRefreshingLabel("正在载入中...");
		listView.getLoadingLayoutProxy().setReleaseLabel("放开更新数据...");

		// 下拉刷新数据
		listView.setOnRefreshListener(this);

		//设置adapter
		adapter = new GroupBuyAdapter(mContext);
		adapter.setOnProductClick(this);
		listView.setAdapter(adapter);
		
		//标题栏设置右边图标
		mTitleBar.setRightViewDrawable(R.drawable.icon_right_hor);
		mTitleBar.setRightIconVisibility(0, View.VISIBLE);
		mTitleBar.setRightClick(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int bg = Integer.parseInt(v.getTag().toString());
				if(bg == R.drawable.icon_right_ver){
					
					//切换两列显示
					mTitleBar.setRightViewDrawable(R.drawable.icon_right_hor);
					adapter.ChangeView(false);
					
				}else{

					//切换单列显示
					mTitleBar.setRightViewDrawable(R.drawable.icon_right_ver);
					adapter.ChangeView(true);
				}
			}
		});

    }
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
		//顶部分类
		List<KeyValue<String, String>> dataList = new ArrayList<KeyValue<String,String>>();
		dataList.add(new KeyValue<String, String>("0", "全部"));
		dataList.add(new KeyValue<String, String>("1", "女装"));
		dataList.add(new KeyValue<String, String>("2", "男装"));
		dataList.add(new KeyValue<String, String>("3", "饰品"));
		dataList.add(new KeyValue<String, String>("4", "绿植"));
		dataList.add(new KeyValue<String, String>("5", "汽车"));
		dataList.add(new KeyValue<String, String>("6", "爱情"));
		hAdapter.refreshData(dataList);
		
		//团购商品
		List<Product> list = new ArrayList<Product>();
		list.add(new Product());
		list.add(new Product());
		list.add(new Product());
		list.add(new Product());
		list.add(new Product());
		list.add(new Product());
		list.add(new Product());
		list.add(new Product());
		list.add(new Product());
		list.add(new Product());		
		adapter.refreshData(list);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
		case R.id.lv_h:
			
			//顶部分类
			hAdapter.setCurrent(position);
			
			break;
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		listView.onRefreshComplete();
	}

	@Override
	public void onProductClick(Product product) {
		
		//商品列表-进入到团购商品详情--这里要修改 goodsGenreType不能填1
		startToProductDetailActivity(product.getId(), 1);
	}
}
