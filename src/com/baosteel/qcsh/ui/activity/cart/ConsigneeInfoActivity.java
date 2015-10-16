package com.baosteel.qcsh.ui.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.ConsigneeInfo;
import com.baosteel.qcsh.model.MemberReceiveAddressJson;
import com.baosteel.qcsh.ui.adapter.ConsigneeInfoAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/9/11.
 */
public class ConsigneeInfoActivity extends BaseActivity implements View.OnClickListener {
    private TitleBar mTitle_bar;
    private ListView mLv_address_list;
    private Button mBtn_consignee_commit;
    private LinearLayout mLin_add_address;
    private ConsigneeInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consignee_info);
        initViews();
        initData();
    }

    private void initViews() {
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
        mLv_address_list = (ListView) findViewById(R.id.lv_address_list);
        mBtn_consignee_commit = (Button) findViewById(R.id.btn_consignee_commit);
        mLin_add_address = (LinearLayout) findViewById(R.id.lin_add_address);
        mLv_address_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ConsigneeInfo consigneeInfo = (ConsigneeInfo)adapter.getItem(i);
                setResult(RESULT_OK, new Intent(ConsigneeInfoActivity.this, ConfirmOrderActivity.class).putExtra("ConsigneeInfo", consigneeInfo));
                ConsigneeInfoActivity.this.finish();
            }
        });
        queryConsigneeList();
    }

    private void initData() {
        mBtn_consignee_commit.setOnClickListener(this);
        mLin_add_address.setOnClickListener(this);
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
        mTitle_bar.setTitle("收货人信息");
        mTitle_bar.setBackgroud(R.color.index_red);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_consignee_commit:
                if(getStringExtra("type").equals("order")){
                    List<ConsigneeInfo> list = adapter.getList();
                    boolean isCheck = false;
                    for (int i = 0; i <list.size() ; i++) {
                        if(list.get(i).isCheck()){
                            isCheck = true;
                        }
                    }
                    if(isCheck){
                        setResult(RESULT_OK, new Intent(ConsigneeInfoActivity.this, ConfirmOrderActivity.class).putExtra("ConsigneeInfo", selectCheckItem()));
                        ConsigneeInfoActivity.this.finish();
                    }else{
                        showToast("请选择地址");
                    }
                } else {
					finish();
				}
                break;
            case R.id.lin_add_address:
//                startActivityForResult(new Intent(mContext, AddAddressActivity.class), 0);
                startActivity(new Intent(mContext, AddAddressActivity.class));
                break;
        }
    }

    private void queryConsigneeList() {
        if (!userIsLogin()) {
            return;
        }
        String userId = BaoGangData.getInstance().getUser().userId;
        RequestClient.queryAppMemberReceiveAddressList(mContext, userId, 8, 1, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                        Object returnMap =  response.optJSONObject("returnMap").get("memberReceiveAddressList");
                        final List<MemberReceiveAddressJson> memberReceiveAddress = JSONParseUtils.fromJsonArray(returnMap.toString(), MemberReceiveAddressJson.class);
                        if (null != memberReceiveAddress) {
                            List<ConsigneeInfo> list = new ArrayList<ConsigneeInfo>();
                            for (int i = 0; i < memberReceiveAddress.size(); i++) {
                                list.add(new ConsigneeInfo(memberReceiveAddress.get(i).getMemberReceiveAddressId(),
                                        memberReceiveAddress.get(i).getReceiveUserRealName(),
                                        memberReceiveAddress.get(i).getAddressDetail(),
                                        memberReceiveAddress.get(i).getUserTelephone(),
                                        isDefaultAddress(memberReceiveAddress.get(i).getIsDefault())));
                            }
                            adapter = new ConsigneeInfoAdapter(mContext, list);
                            mLv_address_list.setAdapter(adapter);
                            adapter.setEditOnClickListener(new ConsigneeInfoAdapter.EditOnClickListener() {
                                @Override
                                public void edit(int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("consignee",memberReceiveAddress.get(position));
                                    startActivity(new Intent(mContext, EditAddressActivity.class).putExtras(bundle));
                                }
                            });

                        } else {

                            //登录失败，用户信息解析错误
                            showToast("获取用户信息失败");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //showToast("数据为空");
                }
            }
        });
    }

    private ConsigneeInfo selectCheckItem(){
        for (int i = 0; i < adapter.getCount(); i++) {
            ConsigneeInfo consigneeInfo = (ConsigneeInfo)adapter.getItem(i);
            if(consigneeInfo.isCheck()){
                return consigneeInfo;
            }
        }
        return null;
    }


    private boolean isDefaultAddress(int defaultNum) {
        if (defaultNum == 1) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryConsigneeList();
    }


}
