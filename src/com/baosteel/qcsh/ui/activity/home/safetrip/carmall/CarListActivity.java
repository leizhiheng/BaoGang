package com.baosteel.qcsh.ui.activity.home.safetrip.carmall;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.ui.fragment.commen.ProductCategoryFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 汽车商城列表
 * Created by kuangyong on 2015/9/17.
 */
public class CarListActivity extends BaseActivity {
    /**
     * 分类信息
     */
    private ArrayList<ProductCategory> mCategories;
    private ProductCategoryFragment mProductCategoryFragment;
    private HeadView headview;// 标题布局
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        initView();

    }

    private void initView() {
        mProductCategoryFragment = (ProductCategoryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_product_category);
        mProductCategoryFragment.setmColorSelected(getResources().getColor(R.color.green_safetrip));
        mProductCategoryFragment.setOnItemClickListener(new ProductCategoryFragment.OnListItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(mContext,ProductListActivity.class));
            }
        });
        headview = (HeadView) findViewById(R.id.headview);
        headview.setTitle("汽配商城");
        headview.setHeadViewBackGroundColor(getResources().getColor(R.color.green_safetrip));
        headview.setRightImageBtnBackGround(R.drawable.icon_3point);
    }
}
