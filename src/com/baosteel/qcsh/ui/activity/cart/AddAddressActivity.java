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

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl;
import com.baosteel.qcsh.dialog.PositionSelect;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.ConsigneeInfo;
import com.baosteel.qcsh.model.MemberReceiveAddressJson;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.ui.view.UISwitchButton;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/9/12.
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener {
    private TitleBar mTitle_bar;
    private EditText mEdit_consignee_name;//收货人
    private EditText mEdit_consignee_phone;//收货人电话
    private EditText mEdit_consignee_zipcode;//邮编
    private LinearLayout mLin_city_select;
    private TextView mTv_consignee_city;
    private EditText mEdit_consignee_address;//详细地址
    private UISwitchButton mBtn_switch;
    private Button mBtn_save_address;
    private String provinceName;//省
    private String cityName;//市
    private String areaName;//区
    private int provinceId;//省id
    private int cityId;//市id
    private int areaId;//区id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initViews();
        initData();
    }

    private void initViews() {
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


    private void initData() {
        mTitle_bar.setTitle("新增地址");
        mTitle_bar.setBackgroud(R.color.index_red);
        mLin_city_select.setOnClickListener(this);
        mBtn_save_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_city_select:
                selectPosition();
                break;
            case R.id.btn_save_address:
                if (checkData())
                    addConsigneeInfo();
                break;
        }
    }

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

            }
        });
    }

    private MemberReceiveAddressJson addressData() {
        MemberReceiveAddressJson addressJson = new MemberReceiveAddressJson();
        addressJson.setAddressDetail(mEdit_consignee_address.getText().toString().trim());
        addressJson.setAreaId(areaId);
        addressJson.setAreaName(areaName);
        addressJson.setCityId(cityId);
        addressJson.setCityName(cityName);
        addressJson.setIsDefault(mBtn_switch.isChecked() ? 1 : 0);
        addressJson.setProvinceName(provinceName);
        addressJson.setProvinceId(provinceId);
        addressJson.setReceiveUserRealName(mEdit_consignee_name.getText().toString().trim());
        addressJson.setUserTelephone(mEdit_consignee_phone.getText().toString().trim());
        addressJson.setZipCode(mEdit_consignee_zipcode.getText().toString().trim());
        addressJson.setUserId(BaoGangData.getInstance().getUser().userId);
        return addressJson;
    }

    private boolean checkData() {
        if (isNull(mEdit_consignee_name) || isNull(mEdit_consignee_phone) || isNull(mEdit_consignee_zipcode) || isNull(mEdit_consignee_address) || isNull(mTv_consignee_city)) {
            showToast("带星号的数据不能为空");
            return false;
        }
        return true;
    }

    private void addConsigneeInfo() {
        if (!userIsLogin()) {
            return;
        }
        RequestClient.queryAppAddMemberReceiveAddress(mContext, addressData(), new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    String id = JSONParseUtils.getString(response, "memberReceiveAddressId");
                    addressData().setMemberReceiveAddressId(id);
                    showToast("添加成功");
//                    ConsigneeInfo consigneeInfo = new ConsigneeInfo();
//                    consigneeInfo.setName(addressData().getReceiveUserRealName());
//                    consigneeInfo.setIsCheck(mBtn_switch.isChecked());
//                    consigneeInfo.setAddress(addressData().getAddressDetail());
//                    consigneeInfo.setPhone(addressData().getUserTelephone());
//                    consigneeInfo.setId(id);
//                    setResult(RESULT_OK, new Intent(AddAddressActivity.this, ConsigneeInfoActivity.class).putExtra("ConsigneeInfo", consigneeInfo));
                    finish();
                }
            }
        });
    }
}
