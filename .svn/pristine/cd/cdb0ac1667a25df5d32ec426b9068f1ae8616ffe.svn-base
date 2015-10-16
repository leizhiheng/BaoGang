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
 * 退货申请
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-21
 */
public class ReturnAddActivity extends BaseCameraActivity implements OnItemClickListener, OnClickListener {

	
	/**售后类型**/
	private MyListView lvsaleaftertype;
	
	/**申请理由**/
	private TextView applyResonTv;
	
	/**描述问题**/
	private EditText remarkinfo;
	
	/**描述问题-提示(0-200)**/
	private TextView remarkinfotip;
	
	/**提交申请**/
	private Button submitBtn;
	
	/**上传图片**/
	private GridView uploadGridView;
	
	/**售后类型适配器**/
	private KeyValueAdapter typeAdapter;
	
	/**图片上传适配器**/
	private UploadImageAdapter uploadImageAdapter;
	
	/** 售后类型 0:换货，1：退货 **/
	private int searchType;
	
	/**订单ID**/
	private String orderId; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_return_add);
		setTitle("申请退款");
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
		
		this.uploadGridView = (GridView) findViewById(R.id.upload_gridview);
		this.applyResonTv = (TextView) findViewById(R.id.apply_reson_textview);
		
		this.remarkinfo = (EditText) findViewById(R.id.returnRemarkEt);
		this.remarkinfotip = (TextView) findViewById(R.id.remark_info_tip);
		this.lvsaleaftertype = (MyListView) findViewById(R.id.lv_saleafter_type);
		this.submitBtn = (Button)findViewById(R.id.submit_apply_btn);
		
		//设置适配器
		typeAdapter = new KeyValueAdapter(mContext, null);
		lvsaleaftertype.setAdapter(typeAdapter);
		
		int screenWidth = DeviceUtils.getWidthMaxPx(mContext);
		uploadImageAdapter = new UploadImageAdapter(mContext, screenWidth, 5);
		uploadGridView.setAdapter(uploadImageAdapter);
		
		//监听描述问题文本变换
		setTextChangeListener(remarkinfo, remarkinfotip, 200);
		uploadGridView.setOnItemClickListener(this);
		lvsaleaftertype.setOnItemClickListener(this);
		
		submitBtn.setOnClickListener(this);
		findViewById(R.id.apply_reason_layout).setOnClickListener(this);
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
		// 设置售后类型数据
		List<KeyValue<String, String>> dataList = new ArrayList<KeyValue<String, String>>();
		dataList.add(new KeyValue<String, String>("1", "我要退货"));
		dataList.add(new KeyValue<String, String>("2", "我要换货"));

		typeAdapter.refreshData(dataList);
		typeAdapter.checkPosition(0);
	}
	
	
	
	/**
	 * 换货新增
	 */
	private void applyReplace(String img){
		
	}
	
	
	/**
	 * 换货新增
	 */
	private void addReturn(String img){
		
		
	}
	
	/**
	 * 提交申请
	 */
	private void submitApply(String img){
		String typeId = typeAdapter.getCurTypeId();
		if(typeId.equals("1")){

			//我要退货
			addReturn(img);
			
		}else{
			
			//我要换货
			applyReplace(img);
		}
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		switch (arg0.getId()) {
		case R.id.lv_saleafter_type:
			
			//售后类型
			boolean hasChangeType = typeAdapter.checkPosition(arg2);
			
			//设置默认的申请理由
			if(hasChangeType){
				KeyValue<String, String> type = (KeyValue<String, String>)typeAdapter.getItem(arg2);
				setDefaultApplyReason(type.getKey());
			}
			
			
			break;
		case R.id.upload_gridview:
			
			//上传图片
			if(uploadImageAdapter.isClickAddPic(arg2)){
				
				//打开相册，相机
				showCameraPopwindow(arg1, false, false);
			}
			
			break;
		}
		
	}
	
	/**
	 * 设置默认的申请理由
	 * @param applyType
	 */
	private void setDefaultApplyReason(String applyType){
		
		try{
			//代表退货ID
			if(applyType.equals("1")){
				
				//我要退货
				applyResonTv.setTag("");
				applyResonTv.setText("");
				
			}else{
				
				//我要换货
				applyResonTv.setTag("");
				applyResonTv.setText("");
			}
		}catch(Exception e){
			
			//这里可能会报数组越界
			e.printStackTrace();
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
			
			//跳转到详情界面
			startActivity(new Intent(mContext, ReturnVerifyActivity.class));
			finish();
			
			//提交申请
//			if(validateInput()){
//				
//				uploadImage(uploadImageAdapter.getDataList(), new UpLoadImageListener() {
//					
//					@Override
//					public void UpLoadSuccess(ArrayList<String> netimageurls) {
//						
//						//上传成功的图片
//						String img = getImgs(netimageurls);
//						
//						//提交申请
//						submitApply(img);
//					}
//					
//					@Override
//					public void UpLoadFail() {
//						showToast("图片上传失败");
//					}
//				});
//			}
			
			break;
		case R.id.apply_reason_layout:
			
			//申请理由
			String typeId = typeAdapter.getCurTypeId();
			if(typeId.equals("1")){

				//我要退货
				showReasonPopwindow(view, getReturnDataMap(), "", false, new IOnItemClick() {
					
					@Override
					public void onItemClick(String id, String value) {
						applyResonTv.setTag(id);
						applyResonTv.setText(value);
					}
				});
				
			}else{
				
				//我要换货
				showReasonPopwindow(view, getReturnDataMap(), "", false, new IOnItemClick() {
					
					@Override
					public void onItemClick(String id, String value) {
						applyResonTv.setTag(id);
						applyResonTv.setText(value);
					}
				});
				
			}
			
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
	 * 获取数据map
	 * @param dataList
	 * @return
	 */
	private Map<String, String> getReturnDataMap(){
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("1", "七天无理由退换货");
		dataMap.put("2", "退运费");
		dataMap.put("3", "收到商品破损");
		dataMap.put("4", "商品错发漏发");
		dataMap.put("5", "商品需要维修");
		dataMap.put("6", "发票问题");
		dataMap.put("7", "收到商品不符");
		dataMap.put("8", "商品质量问题");
		dataMap.put("9", "未按照约定时间发货");
		return dataMap;
	}
	
	
	
	/**
	 * 输入有效性验证
	 * @return
	 */
	public boolean validateInput(){
		
		
		
		if(null == applyResonTv.getTag()){
			showToast("请选择申请理由");
			return false;
		}
		
		String des = remarkinfo.getText().toString().trim();
		if(TextUtils.isEmpty(des)){
			showToast("请填写问题描述");
			return false;
		}
		
		return true;
	}
}
