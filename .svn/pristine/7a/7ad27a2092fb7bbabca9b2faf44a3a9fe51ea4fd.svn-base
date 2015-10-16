package com.baosteel.qcsh.ui.activity.my.setting;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CommonPerson;
import com.baosteel.qcsh.ui.adapter.CommonPersonAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshListView;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener2;

/**
 * 常用人员列表
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-16
 */
public class CommonPersionListActivity extends BaseActivity implements OnRefreshListener2<ListView>, View.OnClickListener, OnItemClickListener, OnItemLongClickListener {

	/**添加人员**/
	public static final int REQUEST_ADD = 1;
	
	/**编辑人员**/
	public static final int REQUEST_EDIT = 2;
	
	/**标题**/
	private TitleBar mTitle_bar;

	/**新增提示标题**/
	private TextView addTipTv;
	
	/**人员列表**/
	private PullToRefreshListView listView;

	/**适配器**/
	private CommonPersonAdapter adapter;

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
		mTitle_bar.setTitle("常用人员信息");
		mTitle_bar.setBackgroud(R.color.index_red);
		
		//新增提示
		addTipTv = (TextView)findViewById(R.id.addTipTv);
		addTipTv.setText("新增常用人员");
		
		//ListView
		listView = (PullToRefreshListView) findViewById(R.id.data_list);
		listView.setMode(Mode.BOTH);
		listView.setOnRefreshListener(this);
		listView.setOnItemLongClickListener(this);
		
		//点击事件
		findViewById(R.id.lin_add_address).setOnClickListener(this);

	}

	/**
	 * 初始化数据 
	 */
	private void initData() {

		adapter = new CommonPersonAdapter(mContext, getData());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	/**
	 * 构造人员数据
	 * @return
	 */
	private List<CommonPerson> getData(){
		List<CommonPerson> list = new ArrayList<CommonPerson>();
		list.add(new CommonPerson("毛泽东", "LaoMao", "身份证", "11025252"));
		list.add(new CommonPerson("周恩来", "LaoMao", "身份证", "11025252"));
		list.add(new CommonPerson("邓小平", "LaoMao", "身份证", "11025252"));
		list.add(new CommonPerson("习见平", "LaoMao", "身份证", "11025252"));
		list.add(new CommonPerson("胡锦涛", "LaoMao", "身份证", "11025252"));
		list.add(new CommonPerson("江泽民", "LaoMao", "身份证", "11025252"));
		return list;
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.lin_add_address:

			// 添加人员
			Intent intent = new Intent(mContext, CommonPersonEditActivity.class);
			startActivityForResult(intent, REQUEST_ADD);
			
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		//不是一次成功的结果
		if(RESULT_OK != resultCode){
			return;
		}
		
		switch (requestCode) {
		case REQUEST_ADD:
			
			CommonPerson houseAdd = (CommonPerson)data.getSerializableExtra("person");
			adapter.addData(houseAdd);
			
			break;

		case REQUEST_EDIT:
			
			CommonPerson ticketEdit = (CommonPerson)data.getSerializableExtra("person");
			adapter.updateData(ticketEdit);
			
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		//记住当前点击的人员
		adapter.remembItemClick(position);
		CommonPerson ticket = (CommonPerson)adapter.getItem(position);
		
		//跳转到编辑界面
		Intent intent = new Intent(mContext, CommonPersonEditActivity.class);
		intent.putExtra(CommonPersonEditActivity.PERSON, ticket);
		startActivityForResult(intent, REQUEST_ADD);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		return false;
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}
}
