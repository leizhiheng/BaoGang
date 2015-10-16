package com.baosteel.qcsh.ui.activity.my.setting;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.UserInfo;
import com.baosteel.qcsh.ui.popwindow.SelectApplyReasonWindow.IOnItemClick;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.google.gson.Gson;

/**
 * 意见反馈
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-18
 */
public class FeedBackActivity extends BaseActivity implements OnClickListener {

	/**反馈类型**/
	private TextView funtionTipTv;
	
	/**意见内容**/
	private EditText feedbackEt;
	
	/**手机号码**/
	private EditText phoneEt;
	
	private Map<String, String> typeMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		
		//初始化控件
		initView();	
	
		//加载数据
		loadTypeList(false);
	}

	/**
	 * 加载反馈类型列表
	 */
	private void loadTypeList(final boolean isOnClickView){
		
		RequestClient.queryAppFeedbackType(mContext, new RequestCallback<JSONObject>(false) {
			@Override
			public void onResponse(JSONObject response) {
				if(JSONParseUtils.isSuccessRequest(mContext, response)){
					typeMap = JSONParseUtils.queryAppFeedbackType(response);
					
					//如果是点击了类型，则需要立马显示数据
					if(isOnClickView){
						View view = findViewById(R.id.feedback_type_layout);
						showPopWindow(view);
					}else{
						
						//默认选中第一条数据
						for(String key : typeMap.keySet()){
							funtionTipTv.setTag(key);
							funtionTipTv.setText(typeMap.get(key));
							break;
						}
					}
				}
			}
		});
	}
	
	/**
	 * 加载反馈类型列表
	 */
	private void feedbackSubmit(){
		
		String source = "2";
		String feedbackUserId = BaoGangData.getInstance().getUser().userId;
		String feedbackUserName = BaoGangData.getInstance().getUser().username;
		String feedbackClassifyId = funtionTipTv.getTag() != null ? funtionTipTv.getTag().toString() : "";
		String feedbackClassifyName = funtionTipTv.getText().toString().trim();
		String feedbackTelephone = phoneEt.getText().toString().trim();
		String suggestionContent = feedbackEt.getText().toString().trim();
		
		feedbackUserName = TextUtils.isEmpty(feedbackUserName) ? "" : feedbackUserName;
		
		RequestClient.queryAppFeedbackTypeSubmit(mContext, source, feedbackUserId, feedbackUserName, feedbackClassifyId, feedbackClassifyName, feedbackTelephone, suggestionContent,  new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if(JSONParseUtils.isSuccessRequest(mContext, response)){
					showToast("提交成功");
					finish();
				}
			}
		});
	}
	
	/**
	 * 显示意见反馈类型
	 * @param view
	 */
	private void showPopWindow(View view){
		String id = funtionTipTv.getTag() != null ? funtionTipTv.getTag().toString(): null;
		showReasonPopwindow(view, typeMap , id, false, new IOnItemClick() {
			
			@Override
			public void onItemClick(String id, String value) {
				funtionTipTv.setText(value);
				funtionTipTv.setTag(id);
			}
		});
	}
	
	/**
	 * 初始化控件
	 */
	private void initView() {

		//内容，手机
		feedbackEt = (EditText)findViewById(R.id.feedbackEt);
		phoneEt = (EditText)findViewById(R.id.phoneEt);
		funtionTipTv = (TextView)findViewById(R.id.funtionTipTv);
		
		//提交
		findViewById(R.id.submit).setOnClickListener(this);
		findViewById(R.id.feedback_type_layout).setOnClickListener(this);
	}


	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.submit:
			
			//提交
			
			if(isNull(feedbackEt)){
				showToast("请输入反馈意见");
				return;
			}
			
			if(isNull(phoneEt)){
				showToast("请输入手机号码");
				return;
			}
			
			//提交意见
			feedbackSubmit();
			
			break;
		case R.id.feedback_type_layout:
			
			//选择反馈类型
			if(null != typeMap){
				showPopWindow(view);
			}else{
				loadTypeList(true);
			}
			
			break;
			
		}
	}
	
}
