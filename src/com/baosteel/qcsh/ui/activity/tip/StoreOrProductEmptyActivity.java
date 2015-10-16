package com.baosteel.qcsh.ui.activity.tip;

import android.os.Bundle;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 店铺或商品不存在提示
 * Created by kuangyong on 15/10/16.
 */
public class StoreOrProductEmptyActivity extends BaseActivity{

    public static final String TIP_TYPE="tip_type";
    public static final String TIP_TYPE_PRO="tip_type_pro";     //商品
    public static final String TIP_TYPE_STORE="tip_type_store"; //店铺
    private HeadView headview;                                  //标题栏
    private TextView tv_tip;                                    //提示
    private String tipType;                                     //提示类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_product_empty);
        tipType=getStringExtra(TIP_TYPE);
        findView();
        initView();
    }

    private void findView(){
        headview= (HeadView) findViewById(R.id.headview);
        tv_tip= (TextView) findViewById(R.id.tv_tip);
    }

    private void initView(){
        headview.setTitle("");
        headview.setHeadViewBackGroundColor(getResources().getColor(R.color.index_red));
        if(tipType.equals(TIP_TYPE_PRO)){//商品
            tv_tip.setText("商品过期不存在！");
        }else  if(tipType.equals(TIP_TYPE_STORE)){//店铺
            tv_tip.setText("店铺过期不存在！");
        }
    }

}
