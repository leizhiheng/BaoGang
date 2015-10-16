package com.baosteel.qcsh.ui.activity.my.setting;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.CommonTicket;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;

/**
 * 常用发票抬头-新增-编辑
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-16
 */
public class CommonTicketEdditActivity extends BaseActivity implements View.OnClickListener {

	/**发票**/
	public static final String TICKET = "ticket";
	
	/**标题**/
	private TitleBar mTitle_bar;

	/**公司名称**/
	private EditText companyNameEt;
	
	/**完成**/
	private Button finishBtn;
	
	/**发票**/
	private CommonTicket ticket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_ticket_edit);
		
		//初始化控件
		initViews();
		
		//初始化数据
		initData();
	}

	/**
	 * 初始化控件
	 */
	private void initViews() {

		//标题
		mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
		mTitle_bar.setBackgroud(R.color.index_red);
		
		//公司名称
		companyNameEt = (EditText)findViewById(R.id.companyNameEt);

		//点击事件
		finishBtn = (Button)findViewById(R.id.finish_button);
		finishBtn.setOnClickListener(this);
	}

	/**
	 * 初始化数据 
	 */
	private void initData() {

		Intent intent = getIntent();
		if(null != intent && intent.hasExtra(TICKET)){
			
			mTitle_bar.setTitle("编辑常用发票抬头");
			finishBtn.setText("完成");
			ticket = (CommonTicket)intent.getSerializableExtra(TICKET);
			companyNameEt.setText(ticket.getTicketDescription());
			
		}else{
			
			mTitle_bar.setTitle("新增常用发票抬头");
			finishBtn.setText("添加");
		}
		

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.finish_button:

			//验证
			if(isNull(companyNameEt)){
				showToast("请输入公司名称");
				return;
			}
			
			if(null != ticket){
				//编辑
				updateTicket(getText(companyNameEt));
			}else{
				
				//添加
				addTicket(getText(companyNameEt));
			}
			
			break;
		}
	}
	
	/**
	 * 添加发票
	 */
	private void addTicket(String ticketContent){
		
		if (ticketContent.length() > 150) {
			showToast("发票抬头信息过长，请重新编辑");
			return;
		}
		
		String userId = BaoGangData.getInstance().getUserId();
		RequestClient.queryAppAddMemberTicket(this, userId, ticketContent, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					ticket = JSONParseUtils.parseTicket(response);
					if(null != ticket){
						
						showToast("添加成功");
						
						//将数据回传
						Intent data = new Intent();
						data.putExtra(TICKET, ticket);
						setResult(RESULT_OK, data);
						finish();
					}else{
						
						showToast("添加失败");
					}
					
				}
			}
		});
	}
	
	/**
	 * 修改发票
	 */
	private void updateTicket(String ticketContent){
		
		if (ticketContent.length() > 150) {
			showToast("发票抬头信息过长，请重新编辑");
			return;
		}
		
		String ticketId = this.ticket.getMemberTicketId();
		
		RequestClient.queryAppUpdateMemberTicket(this, ticketId, ticketContent, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					ticket = JSONParseUtils.parseTicket(response);
					if(null != ticket){
						
						showToast("修改成功");
						
						//将数据回传
						Intent data = new Intent();
						data.putExtra(TICKET, ticket);
						setResult(RESULT_OK, data);
						finish();
					}else{
						
						showToast("修改失败");
					}
					
				}
			}
		});
	}
}
