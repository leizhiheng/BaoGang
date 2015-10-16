package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.ConfirmSimpleDialog;
import com.common.base.BaseActivity;
import com.common.base.BaseCameraActivity;
import com.common.net.uploadimg.ImageData;
import com.common.net.uploadimg.UploadImageAdapter;
import com.common.utils.DeviceUtils;
import com.common.view.topbar.HeadView;

/**
 * 反馈新车型 Created by kuangyong on 15/9/15.
 */
public class FeedBackNewCarActivity extends BaseCameraActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

	private HeadView headview;// 标题布局
	private android.widget.GridView gvimgs;//驾驶证/行驶证
	private android.widget.EditText tvname;//姓名
	private android.widget.EditText tvusrephone;//联系方式
	private android.widget.TextView btnfeedback;//立即反馈
	private UploadImageAdapter adapter;//上传图片适配器

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback_newcar);
		findView();
		initView();
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
		this.btnfeedback = (TextView) findViewById(R.id.btn_feedback);
		this.tvusrephone = (EditText) findViewById(R.id.tv_usre_phone);
		this.tvname = (EditText) findViewById(R.id.tv_name);
		this.gvimgs = (GridView) findViewById(R.id.gv_imgs);
	}

	private void initView() {
		headview.setTitle("反馈新车型");
		headview.setHeadViewBackGroundColor(getResources().getColor(
				R.color.green_safetrip));
		adapter=new UploadImageAdapter(mContext, DeviceUtils.getWidthMaxPx(mContext)/2,3);
		adapter.setMax(2);
		gvimgs.setAdapter(adapter);
		gvimgs.setOnItemClickListener(this);
		btnfeedback.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_feedback://立即反馈
				ConfirmSimpleDialog dialog=new ConfirmSimpleDialog(mContext);
				dialog.show();
				dialog.setTitle("反馈成功！");
				dialog.setDialogTipVisible(View.GONE);
				break;
		}
	}

	@Override
	public void onUpLoadSuccess(String imageUrl, String imageFile) {
		super.onUpLoadSuccess(imageUrl, imageFile);
		if(TextUtils.isEmpty(imageUrl)&&TextUtils.isEmpty(imageFile)){
			return;
		}
		adapter.appendData(new ImageData(imageFile,imageUrl));
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
			case R.id.gv_imgs:

				// 上传图片
				if (adapter.isClickAddPic(position)) {

					// 打开相册，相机
					showCameraPopwindow(view, false, false);
				}
				break;
		}
	}
}
