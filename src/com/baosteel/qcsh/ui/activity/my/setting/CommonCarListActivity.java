package com.baosteel.qcsh.ui.activity.my.setting;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CommonCar;
import com.baosteel.qcsh.ui.adapter.CommonCarAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 常用车辆信息
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-16
 */
public class CommonCarListActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener {

	/**添加**/
	public static final int REQUEST_ADD 	= 1;
	
	/**编辑**/
	public static final int REQUEST_EDIT 	= 2;
	
	/**标题**/
	private TitleBar mTitle_bar;

	/**车辆列表**/
	private PullToRefreshListView listView;

	/**适配器**/
	private CommonCarAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_car_info);
		
		initViews();
		
		initData();
	}

	/**
	 * 初始化控件
	 */
	private void initViews() {

		//标题
		mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
		mTitle_bar.setTitle("常用车辆信息");
		mTitle_bar.setBackgroud(R.color.index_red);
		
		//ListView
		listView = (PullToRefreshListView) findViewById(R.id.data_list);
		listView.setOnItemClickListener(this);
		
		//点击事件
		findViewById(R.id.lin_add_address).setOnClickListener(this);

	}

	/**
	 * 初始化数据 
	 */
	private void initData() {

		adapter = new CommonCarAdapter(mContext, getData());
		listView.setAdapter(adapter);
	}

	/**
	 * 获取构造数据
	 * @return
	 */
	private List<CommonCar> getData(){
		List<CommonCar> list = new ArrayList<CommonCar>();
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		list.add(new CommonCar());
		return list;
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.lin_add_address:

			// 添加车辆信息
			Intent intent = new Intent(mContext, CommonCarEditActivity.class);
			startActivityForResult(intent, REQUEST_ADD);
			
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(mContext, CommonCarEditActivity.class);
		intent.putExtra(CommonCarEditActivity.CAR, (CommonCar)adapter.getItem(position));
		startActivityForResult(intent, REQUEST_EDIT);
	}
}
