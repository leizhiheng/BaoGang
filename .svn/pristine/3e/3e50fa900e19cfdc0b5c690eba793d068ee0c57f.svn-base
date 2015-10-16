package com.baosteel.qcsh.ui.activity.my.setting;

import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.dialog.SimpleMsgDialog;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.CommonTicket;
import com.baosteel.qcsh.ui.adapter.CommonTicketAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;
import com.common.view.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.common.view.pulltorefresh.PullToRefreshListView;

/**
 * 常用发票抬头列表
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-16
 */
public class CommonTicketListActivity extends BaseActivity implements OnRefreshListener2<ListView>, View.OnClickListener, OnItemClickListener, OnItemLongClickListener {

	/**添加发票**/
	public static final int REQUEST_ADD = 1;
	
	/**编辑发票**/
	public static final int REQUEST_EDIT = 2;
	
	/**标题**/
	private TitleBar mTitle_bar;

	/**新增提示标题**/
	private TextView addTipTv;
	
	/**车辆列表**/
	public PullToRefreshListView listView;

	/**适配器**/
	private CommonTicketAdapter adapter;

	/**当前页**/
	private int pageIndex = 1;
	
	/**每页加载条数**/
	private int pageSize = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_car_info);
		
		initViews();
		
		initData();
		
		loadData();
	}

	/**
	 * 初始化控件
	 */
	private void initViews() {

		//标题
		mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
		mTitle_bar.setTitle("常用发票抬头");
		mTitle_bar.setBackgroud(R.color.index_red);
		
		//新增提示
		addTipTv = (TextView)findViewById(R.id.addTipTv);
		addTipTv.setText("新增发票抬头");
		
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

		adapter = new CommonTicketAdapter(mContext, null);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	boolean showDialog = true;
	/**
	 * 加载数据
	 */
	private void loadData(){
		
		String userId = BaoGangData.getInstance().getUserId();
		RequestClient.queryAppMemberTicketList(this, userId, new RequestCallback<JSONObject>(showDialog) {

			@Override
			public void onFinish() {
				super.onFinish();
				
				//只有首次加载数据才显示对话框
				showDialog = false;
				listView.onRefreshComplete();
			}
			
			@Override
			public void onResponse(JSONObject response) {
				listView.onRefreshComplete();
				
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					
					//解析数据
					List<CommonTicket> tempData = JSONParseUtils.queryAppMemberTicketList(response);
					
					//非空判断
					if(null == tempData || tempData.size() == 0){
						showToast("没有数据");
						return;
					}
					
					//第一页，下拉刷新操作
					if(pageIndex <= 1){
						adapter.refreshData(tempData);
					}else{
						
						//第二页后，上拉加载数据
						adapter.appendData(tempData);
					}
					
					//无下一页数据,判断是否还有下一页
					Mode mode = (tempData.size() < 10) ? Mode.PULL_FROM_START : Mode.BOTH;
					listView.setMode(mode);
					
					//pageIndex++
					if(tempData.size() >= 10){
						pageIndex++;
					}
				}
			}
		});
	}
	
	private void deleteTicket(String ticketId){
		RequestClient.queryAppDeleteMemberTicket(this, ticketId, new RequestCallback<JSONObject>(showDialog) {
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					
					showToast("删除成功");
					adapter.removeCurrent();
					
				}
			}
		});
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.lin_add_address:

			// 添加发票抬头
			Intent intent = new Intent(mContext, CommonTicketEdditActivity.class);
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
			
			CommonTicket ticketAdd = (CommonTicket)data.getSerializableExtra("ticket");
			adapter.addData(ticketAdd);
			
			break;

		case REQUEST_EDIT:
			
			CommonTicket ticketEdit = (CommonTicket)data.getSerializableExtra("ticket");
			adapter.updateData(ticketEdit);
			
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		position -= listView.getHeadViewCount();
		
		//记住当前点击的发票
		adapter.remembItemClick(position);
		CommonTicket ticket = (CommonTicket)adapter.getItem(position);
		
		//跳转到编辑界面
		Intent intent = new Intent(mContext, CommonTicketEdditActivity.class);
		intent.putExtra(CommonTicketEdditActivity.TICKET, ticket);
		startActivityForResult(intent, REQUEST_EDIT);
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		position -= listView.getHeadViewCount();
		
		//记住当前点击的发票
		adapter.remembItemClick(position);
		final CommonTicket ticket = (CommonTicket)adapter.getItem(position);
		final SimpleMsgDialog dialog = new SimpleMsgDialog(mContext);
		dialog.setMsg("您确定要删除这条数据?");
		dialog.setOkText("确定");
		dialog.show();
		dialog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
				if(v.getId() == R.id.btn_ok){
					deleteTicket(ticket.getMemberTicketId());
				}
			}
		});
		
		return false;
	}
	
	
	
	
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageIndex = 1;
		loadData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		loadData();
	}

	
}
