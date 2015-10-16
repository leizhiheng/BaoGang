package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.dialog.ConfirmSimpleDialog;
import com.baosteel.qcsh.ui.adapter.AppraiseListAdapter;
import com.baosteel.qcsh.ui.popwindow.SelectApplyReasonWindow;
import com.common.base.BaseCameraFragment;
import com.common.base.BaseFragment;
import com.common.net.uploadimg.ImageData;
import com.common.net.uploadimg.UploadImageAdapter;
import com.common.utils.DeviceUtils;
import com.common.view.listview.MyListView;

import java.util.HashMap;
import java.util.Map;

/**
 * 汽车维修
 * Created by kuangyong on 15/9/1.
 */
public class MainTainQCWXFragment extends BaseCameraFragment implements View.OnClickListener,AdapterView.OnItemClickListener{

    private TextView tvphone;//电话号码
    private LinearLayout btnphone;//拨打电话
    private LinearLayout layout_main;//主体布局
    private TextView tvmaintaintype;//维修类型
    private LinearLayout layoutselectetype;//维修类型布局
    private android.widget.GridView gvimgs;//上传图片
    private android.widget.EditText tvdescrib;//描述
    private android.widget.CheckBox cbisselected;//是否需要拖车
    private android.widget.EditText tvname;//车主姓名
    private android.widget.EditText tvusrephone;//联系电话
    private TextView btncommit;//提交
    private UploadImageAdapter adapter;//上传图片适配器
    private Map<String,String> map;
    private String selectedId;//选中的维修故障id

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_maintain_qcwx, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        setListener();
        initView();
    }

    private void findView() {
        this.btncommit = (TextView) findViewById(R.id.btn_commit);
        this.tvusrephone = (EditText) findViewById(R.id.tv_usre_phone);
        this.tvname = (EditText) findViewById(R.id.tv_name);
        this.cbisselected = (CheckBox) findViewById(R.id.cb_isselected);
        this.tvdescrib = (EditText) findViewById(R.id.tv_describ);
        this.gvimgs = (GridView) findViewById(R.id.gv_imgs);
        this.layoutselectetype = (LinearLayout) findViewById(R.id.layout_selecte_type);
        this.tvmaintaintype = (TextView) findViewById(R.id.tv_maintain_type);
        this.btnphone = (LinearLayout) findViewById(R.id.btn_phone);
        this.layout_main = (LinearLayout) findViewById(R.id.layout_main);
        this.tvphone = (TextView) findViewById(R.id.tv_phone);
    }

    private void setListener() {
        btnphone.setOnClickListener(this);
        layoutselectetype.setOnClickListener(this);
        btncommit.setOnClickListener(this);
    }

    private void initView() {
        adapter=new UploadImageAdapter(mContext, DeviceUtils.getWidthMaxPx(mContext)/2,3);
        adapter.setMax(2);
        gvimgs.setAdapter(adapter);
        gvimgs.setOnItemClickListener(this);
        map=new HashMap<String,String>();
        map.put("1","发动机故障");
        map.put("2","刹车故障");
        map.put("3","其他故障");
        selectedId="1";
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_phone:
                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:0211234567"));
                startActivity(intent);
                break;
            case R.id.layout_selecte_type://维修类型
                showReasonPopwindow(layout_main, map, "1", false, new SelectApplyReasonWindow.IOnItemClick() {
                    @Override
                    public void onItemClick(String id, String value) {
                        selectedId = id;
                        tvmaintaintype.setText(map.get(id));
                    }
                });
                break;
            case R.id.btn_commit://提交
                ConfirmSimpleDialog confirmSimpleDialog=new ConfirmSimpleDialog(mContext);
                confirmSimpleDialog.show();
                confirmSimpleDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                break;
        }
    }
}
