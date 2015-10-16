package com.baosteel.qcsh.ui.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl;
import com.baosteel.qcsh.dialog.PositionSelect;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.ConsigneeInfo;
import com.baosteel.qcsh.model.MemberReceiveAddressJson;
import com.baosteel.qcsh.ui.adapter.ConsigneeInfoAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.ui.view.UISwitchButton;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.swipe.interfaces.SwipeAdapterInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jws on 2015/9/12.
 * 修改收货人信息
 */
public class EditAddressActivity extends BaseActivity implements View.OnClickListener {
    private TitleBar mTitle_bar;
    private EditText mEdit_consignee_name;//收货人
    private EditText mEdit_consignee_phone;//收货人电话
    private EditText mEdit_consignee_zipcode;//邮编
    private LinearLayout mLin_city_select;
    private TextView mTv_consignee_city;//省份市区
    private EditText mEdit_consignee_address;//详细地址
    private UISwitchButton mBtn_switch;
    private Button mBtn_save_address;
    private String provinceName;//省
    private String cityName;//市
    private String areaName;//区
    private int provinceId;//省id
    private int cityId;//市id
    private int areaId;//区id TitleBar mTitle_bar;
    private MemberReceiveAddressJson memberReceiveAddressJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);


        initView();
        initData();
    }

    private void initView() {
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
        mEdit_consignee_name = (EditText) findViewById(R.id.edit_consignee_name);
        mEdit_consignee_phone = (EditText) findViewById(R.id.edit_consignee_phone);
        mEdit_consignee_zipcode = (EditText) findViewById(R.id.edit_consignee_zipcode);
        mLin_city_select = (LinearLayout) findViewById(R.id.lin_city_select);
        mTv_consignee_city = (TextView) findViewById(R.id.tv_consignee_city);
        mEdit_consignee_address = (EditText) findViewById(R.id.edit_consignee_address);
        mBtn_switch = (com.baosteel.qcsh.ui.view.UISwitchButton) findViewById(R.id.btn_switch);
        mBtn_save_address = (Button) findViewById(R.id.btn_save_address);
    }

    private boolean checkData() {
        if (isNull(mEdit_consignee_name) || isNull(mEdit_consignee_phone) || isNull(mEdit_consignee_address) || isNull(mTv_consignee_city)) {
            showToast("带星号的数据不能为空");
            return false;
        }
        return true;
    }

    private void initData() {
        memberReceiveAddressJson = (MemberReceiveAddressJson) getIntent().getSerializableExtra("consignee");
        mTitle_bar.setTitle("编辑收货地址");
        mTitle_bar.setBackgroud(R.color.index_red);
        mLin_city_select.setOnClickListener(this);
        mBtn_save_address.setOnClickListener(this);
        mEdit_consignee_name.setText(memberReceiveAddressJson.getReceiveUserRealName());
        mEdit_consignee_zipcode.setText(memberReceiveAddressJson.getZipCode());
        mEdit_consignee_phone.setText(memberReceiveAddressJson.getUserTelephone());
        mEdit_consignee_address.setText(memberReceiveAddressJson.getAddressDetail());
        mTv_consignee_city.setText(memberReceiveAddressJson.getProvinceName() + " " + memberReceiveAddressJson.getCityName() + " " + memberReceiveAddressJson.getAreaName());
        mBtn_switch.setChecked(memberReceiveAddressJson.getIsDefault() == 1 ? true : false);
        mTitle_bar.showRightIcon(3, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delAddressInfo(memberReceiveAddressJson.getMemberReceiveAddressId());
            }
        });
    }

    private void getUpdateInfo() {
    	
        memberReceiveAddressJson.setUserTelephone(mEdit_consignee_phone.getText().toString().trim());
        memberReceiveAddressJson.setZipCode(mEdit_consignee_zipcode.getText().toString().trim());
        memberReceiveAddressJson.setIsDefault(mBtn_switch.isChecked() == true ? 1 : 0);
        memberReceiveAddressJson.setReceiveUserRealName(mEdit_consignee_name.getText().toString().trim());
        memberReceiveAddressJson.setAddressDetail(mEdit_consignee_address.getText().toString().trim());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_address:
                if (checkData()) {
                    getUpdateInfo();
                    saveConsigneeInfo();
                }
                break;
            case R.id.lin_city_select:
                selectPosition();
                break;
        }
    }

    /**
     * 删除收货人信息
     **/
    private void delAddressInfo(String id) {
        if (!userIsLogin()) {
            return;
        }
        String userId = BaoGangData.getInstance().getUser().userId;
        RequestClient.queryAppDeleteMemberReceiveAddress(mContext, userId, id, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    showToast("删除成功");
                    EditAddressActivity.this.finish();
                } else {
                    //登录失败，用户信息解析错误
                    showToast("删除失败");
                }
            }
        });
    }

    /**
     * 保存收货人信息
     **/
    private void saveConsigneeInfo() {
        if (!userIsLogin()) {
            return;
        }
        RequestClient.queryAppUpdateMemberReceiveAddress(mContext, memberReceiveAddressJson, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    showToast("保存成功");
                    EditAddressActivity.this.finish();
                } else {
                    //登录失败，用户信息解析错误
                    showToast("保存失败");
                }
            }
        });
    }

    /**
     * 选择省市县
     **/
    private void selectPosition() {
        //选择位置
        View relyview = LayoutInflater.from(mContext).inflate(R.layout.layout_fragment_tonguetip_product, null);
        PositionSelect positionSelect = new PositionSelect(mContext, relyview, provinceId, cityId, areaId);
        positionSelect.setOnSelectResultListener(new PositionSelect.SelectResultListener() {
            @Override
            public void selectResult(SQLOpearteImpl.Area province, SQLOpearteImpl.Area city, SQLOpearteImpl.Area area) {

                //记住当前选中的省市县
                provinceId = (null != province) ? province.RecNo : -1;
                cityId = (null != city) ? city.RecNo : -1;
                areaId = (null != area) ? area.RecNo : -1;

                //文本赋值
                provinceName = (null != province) ? province.name : "";
                cityName = (null != city) ? city.name : "";
                areaName = (null != area) ? area.name : "";
                mTv_consignee_city.setText(provinceName + " " + cityName + " " + areaName);

                //为数据源赋值
                memberReceiveAddressJson.setProvinceId(provinceId);
                memberReceiveAddressJson.setProvinceName(provinceName);
                memberReceiveAddressJson.setCityId(cityId);
                memberReceiveAddressJson.setCityName(cityName);
                memberReceiveAddressJson.setAreaId(areaId);
                memberReceiveAddressJson.setAreaName(areaName);
            }
        });
    }
}
