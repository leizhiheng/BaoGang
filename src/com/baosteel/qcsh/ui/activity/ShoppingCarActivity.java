package com.baosteel.qcsh.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * Created by lenovo on 2015/9/6.
 */
public class ShoppingCarActivity extends BaseActivity implements View.OnClickListener{
    private HeadView mHeadView;
    private ListView mLv_shopping_car;
    private Button mBtn_shopcar_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        initViews();
        mHeadView.setTitle("购物车");

    }

    private void initViews() {
        mHeadView = (HeadView) findViewById(R.id.headview);
        mLv_shopping_car = (ListView) findViewById(R.id.lv_shopping_car);
        mBtn_shopcar_commit = (Button)findViewById(R.id.btn_shopcar_commit);
        mBtn_shopcar_commit.setOnClickListener(this);
//        mLv_shopping_car.setAdapter(new ShoppingCarBusinessAdapter(getBaseContext(), getBusinessData(), new CarItemOncliListener() {
//            @Override
//            public void addNum() {
//                Log.i("test","按了加");
//            }
//
//            @Override
//            public void cutNum() {
//                Log.i("test","按了減");
//            }
//        }, new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        }));
    }

//    private List<ShoppingCar> getBusinessData() {
//        List<ShoppingCar> list = new ArrayList<ShoppingCar>();
//        List<CarItem> carItemlist1 = new ArrayList<CarItem>();
//        carItemlist1.add(new CarItem(R.drawable.test_pic2,"牛蛋单睡了多久爱死了看得见阿斯顿了圣诞节","暂无优惠",180.00,180.00,new String[]{"项目一","项目二"}));
//        carItemlist1.add(new CarItem(R.drawable.test_pic2,"牛奶","暂无优惠",180.00,180.00,new String[]{"项目三","项目四"}));
//        List<CarItem> carItemlist2 = new ArrayList<CarItem>();
//        carItemlist2.add(new CarItem(R.drawable.test_pic2,"饼干","暂无优惠",180.00,180.00,new String[]{"项目五","项目六"}));
//        carItemlist2.add(new CarItem(R.drawable.test_pic2,"吐司","暂无优惠",180.00,180.00,new String[]{"项目七","项目八"}));
//        list.add(new ShoppingCar("七彩生活自营店",1,carItemlist1));
//        list.add(new ShoppingCar("艾丽奇家居商店",2,carItemlist2));
//        return list;
//    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_shopcar_commit:
                startActivity(new Intent(getBaseContext(),OnlinePaymentActivity.class));
                break;
        }
    }
}
