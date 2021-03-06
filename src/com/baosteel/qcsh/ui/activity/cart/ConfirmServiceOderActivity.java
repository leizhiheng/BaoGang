package com.baosteel.qcsh.ui.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.CarItem;
import com.baosteel.qcsh.model.ConsigneeInfo;
import com.baosteel.qcsh.model.DeliverCommit;
import com.baosteel.qcsh.model.MemberReceiveAddressJson;
import com.baosteel.qcsh.model.PayModel;
import com.baosteel.qcsh.model.ShoppingCar;
import com.baosteel.qcsh.ui.activity.OnlinePaymentActivity;
import com.baosteel.qcsh.ui.adapter.ShoppingCarBusinessAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.ui.view.UISwitchButton;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseActivity;
import com.common.utils.DialogUtil;
import com.common.utils.LogUtil;
import com.common.view.listview.MyListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kuangyong on 15/10/14.
 */
public class ConfirmServiceOderActivity extends BaseActivity implements View.OnClickListener {


    private static final int request_code_deliver = 1;
    private static final int request_code_address = 2;

    /**
     * 购物车id集合
     **/
    public static final String SHOPPING_IDS = "shopping_ids";

    private TitleBar mTitle_bar;

    private RelativeLayout mRel_noAddress_info;
    private RelativeLayout mRel_address_info;
    private RelativeLayout mRel_cash_volume;
    private RelativeLayout mRel_deliver_method;
    private RelativeLayout mRel_product_inventory;
    private TextView mTv_name;
    private TextView mTv_phone;
    private TextView mTv_address;
    private TextView tv_price;
    private UISwitchButton mBtn_switch;
    private TextView mTv_gohome;
    private TextView mTv_invoice_name;
    private TextView mTv_goods_num;
    private TextView mTv_shipment;
    private RelativeLayout mRel_invoice_detail;
    private RelativeLayout mRel_invoice_name;
    private Button mBtn_pay;
    private ConsigneeInfo consigneeInfo;//重新定义的联系人信息
    private MemberReceiveAddressJson consignee;
    private List<DeliverCommit> deliverCommit;
    private int shipment = 0;
    private double price = 0;
    private List<ShoppingCar> shoppingCarts;
    private String shoppingId;
    private MyListView mLv_shopping_car;
    private ShoppingCarBusinessAdapter shoppingCarBusinessAdapter;
    private TextView tv_service_date;                               //日期
    private TextView tv_service_starttime;                          //时间
    private TextView tv_service_endtime;                            //时间
    private ArrayList<String> times;                                //时间列表
    private String startDate;
    private String endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_serviceorder);
        initViews();
        initData();
    }

    private void initViews() {
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
        mRel_product_inventory = (RelativeLayout) findViewById(R.id.rel_product_inventory);
        mRel_address_info = (RelativeLayout) findViewById(R.id.rel_address_info);
        mRel_noAddress_info = (RelativeLayout) findViewById(R.id.rel_no_address_info);
        mRel_cash_volume = (RelativeLayout) findViewById(R.id.rel_cash_volume);
        mRel_deliver_method = (RelativeLayout) findViewById(R.id.rel_deliver_method);
        mBtn_pay = (Button) findViewById(R.id.btn_pay);
        mTitle_bar = (com.baosteel.qcsh.ui.view.TitleBar) findViewById(R.id.title_bar);
        mTv_name = (TextView) findViewById(R.id.tv_name);
        mTv_phone = (TextView) findViewById(R.id.tv_phone);
        mTv_address = (TextView) findViewById(R.id.tv_address);
        mTv_gohome = (TextView) findViewById(R.id.tv_gohome);
        tv_price = (TextView) findViewById(R.id.tv_price);
        mBtn_switch = (com.baosteel.qcsh.ui.view.UISwitchButton) findViewById(R.id.btn_switch);
        mTv_invoice_name = (TextView) findViewById(R.id.tv_invoice_name);
        mRel_invoice_detail = (RelativeLayout) findViewById(R.id.rel_invoice_detail);
        mRel_invoice_name = (RelativeLayout) findViewById(R.id.rel_invoice_name);
        mTv_goods_num = (TextView) findViewById(R.id.tv_goods_num);
        mTv_shipment = (TextView) findViewById(R.id.tv_shipment);
        tv_service_starttime = (TextView) findViewById(R.id.tv_service_starttime);
        tv_service_endtime = (TextView) findViewById(R.id.tv_service_endtime);
        tv_service_date = (TextView) findViewById(R.id.tv_service_date);
        mLv_shopping_car = (MyListView) findViewById(R.id.lv_shopping_car);
        shoppingCarBusinessAdapter = new ShoppingCarBusinessAdapter(mContext);
        mLv_shopping_car.setAdapter(shoppingCarBusinessAdapter);
        times=new ArrayList<String>();
        for (int i=1;i<25;i++){
            times.add(i+":00");
        }
    }


    private void initData() {
        mTitle_bar.setTitle("确认订单");
        mTitle_bar.setBackgroud(R.color.index_red);
        mRel_address_info.setOnClickListener(this);
        mRel_noAddress_info.setOnClickListener(this);
        mRel_cash_volume.setOnClickListener(this);
        mBtn_pay.setOnClickListener(this);
        mRel_deliver_method.setOnClickListener(this);
        mRel_product_inventory.setOnClickListener(this);
        tv_service_date.setOnClickListener(this);
        tv_service_starttime.setOnClickListener(this);
        tv_service_endtime.setOnClickListener(this);
        mBtn_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    mRel_invoice_name.setVisibility(View.VISIBLE);
                    mRel_invoice_detail.setVisibility(View.VISIBLE);
                } else {
                    mRel_invoice_name.setVisibility(View.GONE);
                    mRel_invoice_detail.setVisibility(View.GONE);
                }
            }
        });
        shoppingId = getIntent().getExtras().getString(SHOPPING_IDS);
        String prices = getIntent().getExtras().getString("price");
        if (!StringUtils.isEmpty(prices)) price = Double.parseDouble(prices);
        initOrderInfo();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_address_info:

                //收货地址-进入收货地址列表
                startActivityForResult(new Intent(mContext, ConsigneeInfoActivity.class).putExtra("type", "order"), request_code_address);
                break;
            case R.id.rel_no_address_info:

                //收货地址-进入新增收货地址
                Intent intentAdd = new Intent(mContext, AddAddressActivity.class);
                startActivityForResult(intentAdd, request_code_address);

                break;
            case R.id.rel_cash_volume:
                startActivity(new Intent(getBaseContext(), CashVolumeActivity.class));
                break;
            case R.id.btn_pay:

                //付款
                commitOrderInfo();

                break;
            case R.id.rel_deliver_method:
                startActivityForResult(new Intent(getBaseContext(), DeliverMethodActivity.class).putExtra("id", shoppingId), request_code_deliver);
                break;
            case R.id.tv_service_date:                                      //日期
                DialogUtil.showBirthdayDialog(tv_service_date,mContext);
                break;
            case R.id.tv_service_starttime:                                 //开始时间
                showTimeSelectPop(tv_service_starttime);
                break;
            case R.id.tv_service_endtime:                                   //截止时间
                showTimeSelectPop(tv_service_endtime);
                break;
        }
    }

    private void showTimeSelectPop(final TextView downview){
        showListPopBelowView(downview, times,downview.getMeasuredWidth()*3, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downview.setText(times.get(position));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) {
            //不是成功回调
            return;
        }

        switch (requestCode) {
            case request_code_address:
                consigneeInfo = (ConsigneeInfo) data.getSerializableExtra("ConsigneeInfo");
                Log.i("test", consigneeInfo.getName() + "---------");

                mTv_name.setText(consigneeInfo.getName());
                mTv_address.setText("送货地址：" + consigneeInfo.getAddress());
                mTv_phone.setText(consigneeInfo.getPhone());
                break;
            case request_code_deliver:
                deliverCommit = data.getParcelableArrayListExtra("delive");
                mTv_gohome.setText(getDeliverName(deliverCommit));
                shipment = data.getIntExtra("shipment", 0);
                mTv_shipment.setText("¥" + shipment * 1.00);
                break;

        }
    }

    private String getDeliverName(List<DeliverCommit> deliverCommit){
        String method = "";
        boolean pickup = false;
        boolean takeup = false;
        for (int i = 0; i <deliverCommit.size() ; i++) {
            if(deliverCommit.get(i).getInvoiceType().equals("1")){
                method = "送货上门";
                pickup = true;
            }else if(deliverCommit.get(i).getInvoiceType().equals("2")){
                method = "上门自提";
                takeup= true;
            }
        }
        if(pickup&&takeup){
            method = "送货上门、上门自提";
        }
        return method;
    }

    /**
     * 初始化订单信息
     **/
    private void initOrderInfo() {
        if (!userIsLogin()) {
            return;
        }
        String userId = BaoGangData.getInstance().getUser().userId;
        String shoppingId = getIntent().getExtras().getString(SHOPPING_IDS);
        RequestClient.submitAppShoppingCartInit(mContext, userId, shoppingId, 0, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                        Object returnMap = response.optJSONObject("returnMap").get("shoppingList");
                        shoppingCarts = JSONParseUtils.fromJsonArray(returnMap.toString(), ShoppingCar.class);
                        Object consigneeReturnMap = response.optJSONObject("returnMap").get("address");
                        consignee = JSONParseUtils.fromJson(consigneeReturnMap.toString(), MemberReceiveAddressJson.class);
                        mTv_name.setText(consignee.getReceiveUserRealName());
                        mTv_address.setText("送货地址：" + consignee.getAddressDetail());
                        mTv_phone.setText(consignee.getUserTelephone());
                        mTv_goods_num.setText("共" + getGoodsCount(shoppingCarts) + "件");
                        tv_price.setText("¥" + getIntent().getExtras().getString("price"));
                        mTv_shipment.setText("¥" + shipment * 1.0);
                        shoppingCarBusinessAdapter.refreshData(shoppingCarts);
                        startDate=response.optJSONObject("returnMap").optString("startDate");
                        endDate=response.optJSONObject("returnMap").optString("endDate");
                        showServiceTime(startDate, endDate);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 显示服务时间
     * @param start
     * @param end
     */
    private void showServiceTime(String start,String end){
        String date="";
        String startTime="";
        String endTime="";
        if(start.contains(" ")){
            String [] time=start.split(" ");
            date=time[0];
            startTime=time[1];
        }
        if(end.contains(" ")){
            String [] time=end.split(" ");
            endTime=time[1];
        }
        tv_service_date.setText(date);
        tv_service_starttime.setText(startTime);
        tv_service_endtime.setText(endTime);
    }

    private double getPrice(List<CarItem> list) {
        double price = 0.00;
        for (int i = 0; i < list.size(); i++) {
            price += list.get(i).getCurprice();
        }
        return price;
    }


    /**
     * 获取商品的总数量
     **/
    private int getGoodsCount(List<ShoppingCar> carItemList) {
        int num = 0;
        for (int i = 0; i < carItemList.size(); i++) {
            List<CarItem> itemList = carItemList.get(i).getCarItem();
            for (int j = 0; j < itemList.size(); j++) {
                num += itemList.get(j).getNum();
            }
        }
        return num;
    }

    private void commitOrderInfo() {
        if (!userIsLogin()) {
            return;
        }
        String userId = BaoGangData.getInstance().getUser().userId;
        String address_id;
        if (consigneeInfo != null) {
            address_id = consigneeInfo.getId();
        } else {
            address_id = consignee.getMemberReceiveAddressId();
        }
        if (consigneeInfo == null && consignee.getAddressDetail() == null) {
            showToast("配送地址不能为空");
            return;
        }
        int order_type = 2;
        String date=tv_service_date.getText().toString();
        String startTime=tv_service_starttime.getText().toString();
        String endTime=tv_service_endtime.getText().toString();
        String serviceTime = date+" "+startTime+"-"+date+" "+endTime;
        if (deliverCommit == null) {
            showToast("配送方式不能为空");
            return;
        }
        Date serviceCurdate=new Date();
        LogUtil.d("服务时间：",serviceTime);
        RequestClient.submitAppShoppingCart(mContext, address_id, userId, order_type, shoppingId, deliverCommit, serviceTime, 2, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //{"type":1,"returnMap":[{"orderId":"166","number":"20151012173030409"}],"msg":"成功","order_pay_id":16}
                    if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                        showToast("提交成功");

                        //解析数据
                        Object returnMap = response.get("returnMap");
                        ArrayList<PayModel> payModel = JSONParseUtils.fromJsonArray(returnMap.toString(), PayModel.class);

                        //订单id，订单支付id，订单编号，订单支付总金额
                        String orderId = payModel.get(0).getOrderId();
                        String orderPayId = JSONParseUtils.getString(response, "order_pay_id");
                        String orderNo = payModel.get(0).getNumber();
                        String orderPay = JSONParseUtils.getString(response, "pay_price");

                        //跳转支付界面
                        Intent intent = new Intent(mContext, OnlinePaymentActivity.class);
                        intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_CODE, orderNo);
                        intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_ID, orderId);
                        intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_PAY_ID, orderPayId);
                        intent.putExtra(OnlinePaymentActivity.EXTRA_ORDER_PRICE, orderPay);
                        startActivity(intent);

                        //结束当前界面
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

//    private  List<DeliverCommit>  getData(){
//        String goodsIds ="";
//        String sellerIds = "";
//        List<DeliverCommit> deliverCommit = new ArrayList<>();
//
//        for (int i = 0; i <goodsList.size() ; i++) {
//            if (goodsList.size() > 1) {
//                goodsIds += "," + goodsList.get(i).getId();
//                String[] sellerIdArray = sellerIds.split(",");
//                for (int k = 0; k < sellerIdArray.length; k++) {
//                    if (!sellerIdArray[k].equals(goodsList.get(i).getSeller_id())) {
//                        sellerIds += "," + goodsList.get(i).getSeller_id();
//                    }
//                }
//            } else {
//                goodsIds = goodsList.get(i).getId() + "";
//                sellerIds = goodsList.get(i).getSeller_id() + "";
//            }
//            deliverCommit.add(new DeliverCommit(sellerIds,goodsIds,1,1));
//        }
//        return deliverCommit;
//    }


    private String getOrderIds(ArrayList<PayModel> payModel) {
        String orderIds = "";
        for (int i = 0; i < payModel.size(); i++) {
//            if (payModel.size() > 1) {
//                orderIds += "," + payModel.get(i).getOrderId();
//            } else {
//                orderIds += payModel.get(i).getOrderId();
//            }
            if (orderIds.isEmpty()) {
                orderIds = payModel.get(i).getOrderId();
            } else {
                orderIds += "," + payModel.get(i).getOrderId();
            }
        }
        return orderIds;
    }
}
