package com.baosteel.qcsh.ui.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.DeliverCommit;
import com.baosteel.qcsh.model.DeliverGoods;
import com.baosteel.qcsh.model.DeliverMethod;
import com.baosteel.qcsh.model.PickUpAddress;
import com.baosteel.qcsh.ui.adapter.DeliveryMethodAdapter;
import com.baosteel.qcsh.ui.popwindow.SelectApplyReasonWindow;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.listview.MyListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配送方式
 *
 * @author 江文思
 * @todo TODO
 * @date 2015-10-2
 */
public class DeliverMethodActivity extends BaseActivity implements View.OnClickListener {

	/**送货方式列表**/
    private MyListView mLv_deliver_method;
    
    /**送货方式适配器**/
    private DeliveryMethodAdapter adapter;
    
    /**标题**/
    private TitleBar mTitle_bar;
    
    /**确定按钮**/
    private Button btn_ok;

    private static final int request_pickup_point = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_method);
        
        initViews();
        
        initData();
        
        queryDeliverMethod();
    }

    private void initViews() {
        mLv_deliver_method = (MyListView) findViewById(R.id.lv_deliver_method);
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
        btn_ok = (Button) findViewById(R.id.btn_ok);
    }

    private void initData() {

        mTitle_bar.setTitle("配送方式");
        mTitle_bar.setBackgroud(R.color.index_red);
        btn_ok.setOnClickListener(this);
    }

    /**
     * 查询配送方式
     */
    private void queryDeliverMethod() {
        if (!userIsLogin()) {
            return;
        }
        String userId = BaoGangData.getInstance().getUserId();
        String addressId = getStringExtra("addressId");
        String shoppingId = getStringExtra("id");
        
        RequestClient.queryAppDistributionMode(mContext, userId, shoppingId, addressId, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (JSONParseUtils.isSuccessRequest(mContext, response)) {

                        String returnMap = response.get("returnMap").toString();
                        List<DeliverMethod> list = JSONParseUtils.fromJsonArray(returnMap, DeliverMethod.class);
                        setAdapterData(list);
                        list.get(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 设置配送方式数据
     *
     * @param list
     */
    private void setAdapterData(final List<DeliverMethod> list) {
        if (null == adapter) {
            adapter = new DeliveryMethodAdapter(mContext, list, new DeliveryMethodAdapter.ItemOnclickListener() {
                @Override
                public void getPoint(int position, View view) {
                	
                	//查询自提点
//                    queryAppSinceSome(list.get(position).getAddress().getMerchant_id() + "", view,position);
                    String seller_id = list.get(position).getAddress().getMerchant_id()+"";
                    int pickUpId = list.get(position).getAddress().getId();
                    Intent intent = new Intent();
                    intent.setClass(mContext, PickUpPointActivity.class);
                    intent.putExtra("seller_id", seller_id);
                    intent.putExtra("position",position);
                    intent.putExtra("pickUpId",pickUpId);
                    startActivityForResult(intent, request_pickup_point);
                }

                @Override
                public void getAddress(int position) {
                	
                	//地址，根据自提点返回的地址，调地图，在地图中显示自提点对应的位置
                    Toast.makeText(DeliverMethodActivity.this, "选择地址", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void getPhone(int position) {
                	
                	//打自提点对应的电话
                    Toast.makeText(DeliverMethodActivity.this, "更改电话", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void getDate(int position) {
                	
                	//修改日期
                    Toast.makeText(DeliverMethodActivity.this, "选择自提时间", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void clickMention(int position) {
                	
                	//送货上门
                    if (list.get(position).getType() == 1) {
                        list.get(position).setType(2);
                        adapter.refreshData(list);
                    }
                }

                @Override
                public void clickDelivery(int position) {

                	//上门自提
                    if (list.get(position).getType() == 2) {
                        list.get(position).setType(1);
                        adapter.refreshData(list);
                    }

                }
            });

            mLv_deliver_method.setAdapter(adapter);

        } else {

            //刷新数据
            adapter.refreshData(list);
        }
    }

    /**
     * 查询自提点信息
     */
    private void queryAppSinceSome(String seller_id, final View view,final int position) {
        RequestClient.queryAppSinceSome(mContext, seller_id, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                        String returnMap = response.get("returnMap").toString();
                        List<PickUpAddress> addressList = JSONParseUtils.fromJsonArray(returnMap, PickUpAddress.class);
                      
                        if(null == addressList || addressList.size() == 0){
                        	showToast("暂无自提点");
                        	return;
                        }
                        
                        showReasonPopwindow(view, loadPickUpData(addressList), null, false, new SelectApplyReasonWindow.IOnItemClick() {

                            @Override
                            public void onItemClick(String id, String value) {
                            	Log.i("jws",id+"--"+value);
                                adapter.getList().get(position).getAddress().setId(Integer.parseInt(id));
                                adapter.getList().get(position).getAddress().setServe_name(value);
                                adapter.notifyDataSetChanged();

                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


    /**
     * 获取自提点信息
     */
    private Map<String, String> loadPickUpData(List<PickUpAddress> addressList) {
        Map<String, String> dataMap = new HashMap<String, String>();
        
        PickUpAddress address = null;
        for (int i = 0; i < addressList.size(); i++) {
        	address = addressList.get(i);
        	String id = address.getId()+"";
        	String value = address.getProvinceName() + address.getCity() + address.getAreaName();
            dataMap.put(id, value);
        }
        return dataMap;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                if(adapter.getCount() == 0){
                    showToast("无配送方式，不予下单");
                    return;
                }
                
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("delive", getDeliveList());
                intent.putExtra("shipment", getShipment());
                setResult(RESULT_OK, intent);
                
                this.finish();
                break;
        }
    }

    private int getShipment() {
        int shipment = 0;
        List<DeliverMethod> list = adapter.getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType()==1) {
                shipment += list.get(i).getShipment();
            }
        }
        return shipment;
    }


    /**
     * 提取返回给订单页的信息
     */
    private ArrayList<DeliverCommit> getDeliveList() {
        List<DeliverMethod> methodList = adapter.getList();
        
        ArrayList<DeliverCommit> list = new ArrayList<DeliverCommit>();
        
        String serllsId = "";
        String goodsId = "";
        for (int i = 0; i < methodList.size(); i++) {
            List<DeliverGoods> goodsList = methodList.get(i).getGoodsImg();
            for (int k = 0; k < goodsList.size(); k++) {
                if (serllsId.isEmpty()) {
                    serllsId = goodsList.get(k).getSeller_id() + "";
                } else {
                    serllsId += "," + goodsList.get(k).getSeller_id();
                }
                if (goodsId.isEmpty()) {
                    goodsId = goodsList.get(k).getId() + "";
                } else {
                    goodsId += "," + goodsList.get(k).getId();
                }
                serllsId = getString(serllsId.split(","));
            }
            list.add(new DeliverCommit(serllsId, goodsId, methodList.get(i).getType(), methodList.get(i).getAddress().getId(), methodList.get(i).getInvoiceType()));
            serllsId = "";
        }
        return list;
    }

    private String getString(String[] array) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            if (!list.contains(array[i])) {
                list.add(array[i]);
            }
        }
        String str = "";
        boolean flag = false;
        for (String s : list) {
            if (!flag) {
                str += s;
                flag = true;
            } else {
                str += ("," + s);
            }
        }
        return str;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) {
            //不是成功回调
            return;
        }
        switch (requestCode) {
            case request_pickup_point:
                PickUpAddress pickUpAddress = (PickUpAddress)data.getSerializableExtra("pickUpAddress");
                int position = data.getIntExtra("p",0);
                adapter.getList().get(position).getAddress().setId(pickUpAddress.getId());
                adapter.getList().get(position).getAddress().setServe_name(pickUpAddress.getServe_name());
                adapter.getList().get(position).getAddress().setContact_tel(pickUpAddress.getContact_tel());
                adapter.getList().get(position).getAddress().setStreet_address(getAddressDetail(pickUpAddress));
                adapter.notifyDataSetChanged();
                break;
        }
    }

    /**获取地址详细**/
    private String getAddressDetail(PickUpAddress pickUpAddress) {
        return pickUpAddress.getProvinceName() + pickUpAddress.getCityName()
                + pickUpAddress.getAreaName()+pickUpAddress.getStreet_address();
    }
}
