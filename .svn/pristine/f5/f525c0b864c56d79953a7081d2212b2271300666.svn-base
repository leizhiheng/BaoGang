package com.baosteel.qcsh.ui.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.PickUpAddress;
import com.baosteel.qcsh.ui.adapter.PickupPointAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.listview.MyListView;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by jws on 2015/10/15.
 * 自提点选择页面
 */
public class PickUpPointActivity extends BaseActivity implements View.OnClickListener {
    private TitleBar mTitle_bar;
    private MyListView mLv_pickup;
    private Button mBtn_pickup_commit;
    private PickupPointAdapter adapter;
    private List<PickUpAddress> addressList;
    private int position;
    private int pickUpId;//运送方式选的自提点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_point);

        initView();
        initData();
    }

    /**
     * 初始化信息
     */
    private void initView() {
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
        mLv_pickup = (MyListView) findViewById(R.id.lv_pickup);
        mBtn_pickup_commit = (Button) findViewById(R.id.btn_pickup_commit);
    }


    /**
     * 初始化数据
     */
    private void initData() {
        mTitle_bar.setTitle("自提点选择");
        mTitle_bar.setBackgroud(R.color.index_red);
        mBtn_pickup_commit.setOnClickListener(this);
        queryAppSinceSome(getStringExtra("seller_id"));
        position = getIntExtra("position");
        pickUpId = getIntExtra("pickUpId");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pickup_commit:
                if (addressList != null && addressList.size() > 0) {
                    PickUpAddress pickUpAddress = getCheckItem();
                    if (pickUpAddress != null) {
                        Intent intent = new Intent(mContext, DeliverMethodActivity.class);
                        intent.putExtra("pickUpAddress", pickUpAddress);
                        intent.putExtra("p", position);
                        setResult(RESULT_OK, intent);
                        PickUpPointActivity.this.finish();
                    }
                }
                break;
        }
    }

    private PickUpAddress getCheckItem() {
        PickUpAddress pickUpAddress = null;
        for (int i = 0; i < addressList.size(); i++) {
            if (addressList.get(i).ischeck()) {
                pickUpAddress = addressList.get(i);
            }
        }
        return pickUpAddress;
    }

    /**
     * 查询自提点信息
     */
    private void queryAppSinceSome(String seller_id) {
        RequestClient.queryAppSinceSome(mContext, seller_id, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                        String returnMap = response.get("returnMap").toString();
                        addressList = JSONParseUtils.fromJsonArray(returnMap, PickUpAddress.class);
                        setDefalutCheck();
                        adapter = new PickupPointAdapter(mContext, addressList);
                        mLv_pickup.setAdapter(adapter);
                        setClickListener();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


    /**
     * 设置上级页面选中的信息
     **/
    private void setDefalutCheck() {
        for (int i = 0; i < addressList.size(); i++) {
            if (addressList.get(i).getId() == pickUpId) {
                addressList.get(i).setIscheck(true);
            }
        }
    }

    /**
     * 设置listview的点击
     **/
    private void setClickListener() {
        if (adapter != null) {
            mLv_pickup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    adapter.ChangeCheckState(i);
                }
            });
        }
    }

}
