package com.baosteel.qcsh.ui.activity.my.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.KeyValue;
import com.baosteel.qcsh.ui.adapter.KeyValueAdapter;
import com.baosteel.qcsh.ui.popwindow.SelectApplyReasonWindow.IOnItemClick;
import com.common.base.BaseCameraActivity;
import com.common.net.uploadimg.ImageData;
import com.common.net.uploadimg.UploadImageAdapter;
import com.common.utils.DeviceUtils;
import com.common.view.listview.MyListView;

/**
 * 申请平台介入
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-21
 */
public class ReturnApplyPlatformActivity extends BaseCameraActivity implements OnItemClickListener, OnClickListener {

	/**手机号码**/
	private EditText phoneEt;
	
	/**描述问题**/
	private EditText questionInfoEt;
	
	/**描述问题-提示(0-200)**/
	private TextView questionInfoTip;
	
	/**提交申请**/
	private Button submitBtn;
	
	/**上传图片**/
	private GridView uploadGridView;
	
	/**图片上传适配器**/
	private UploadImageAdapter uploadImageAdapter;
	
	/**订单ID**/
	private String orderId; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_return_apply_platform);
		setTitle("申请平台介入");
		setTitleBarColor(getResources().getColor(R.color.index_red));
		
		//初始化控件
		initView();
		
		//初始化数据
		initData();
	}
	
	/**
	 * 初始化控件
	 */
	private void initView(){
		
		//手机号码，问题信息内容
		this.phoneEt= (EditText)findViewById(R.id.contactPhoneEt); 
		this.questionInfoTip = (TextView) findViewById(R.id.remark_info_tip);
		this.questionInfoEt = (EditText) findViewById(R.id.questionInfoEt);

		//上传凭证图片
		this.uploadGridView = (GridView) findViewById(R.id.upload_gridview);
		this.submitBtn = (Button)findViewById(R.id.submit_apply_btn);
		int screenWidth = DeviceUtils.getWidthMaxPx(mContext);
		uploadImageAdapter = new UploadImageAdapter(mContext, screenWidth, 5);
		uploadGridView.setAdapter(uploadImageAdapter);
		
		//监听描述问题文本变换
		setTextChangeListener(questionInfoEt, questionInfoTip, 200);
		uploadGridView.setOnItemClickListener(this);
		
		submitBtn.setOnClickListener(this);
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		switch (arg0.getId()) {
		case R.id.upload_gridview:
			
			//上传图片
			if(uploadImageAdapter.isClickAddPic(arg2)){
				
				//打开相册，相机
				showCameraPopwindow(arg1, false, false);
			}
			
			break;
		}
		
	}
	
	
	@Override
	public void onUpLoadSuccess(String imageUrl, String imageFile) {

		//非空判断
		if (TextUtils.isEmpty(imageUrl) && TextUtils.isEmpty(imageFile)){
			return;
		}
		
		//显示新增的数据
		uploadImageAdapter.appendData(new ImageData(imageFile, imageUrl));
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.submit_apply_btn:

			//修改成功，返回到上一界面，刷新数据
			//setResult(RESULT_OK);
			//finish();
			
			
			break;
		}
	}
	
	/**
	 * 根据List<String>获得 "str1,str2,str3..."
	 * @param netimageurls
	 * @return
	 */
	public String getImgs(List<String> netimageurls){
		if(null == netimageurls || netimageurls.size() == 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<netimageurls.size(); i++){
			if(i==0){
				sb.append(netimageurls.get(i));
			}else{
				sb.append(","+netimageurls.get(i));
			}
		}
		
		return sb.toString();
	}
	
	
	
	
	
	/**
	 * 输入有效性验证
	 * @return
	 */
	public boolean validateInput(){
		
		if(null == phoneEt.getTag()){
			showToast("请输入手机号码");
			return false;
		}
		
		String des = questionInfoEt.getText().toString().trim();
		if(TextUtils.isEmpty(des)){
			showToast("请输入信息");
			return false;
		}
		
		return true;
	}
}
