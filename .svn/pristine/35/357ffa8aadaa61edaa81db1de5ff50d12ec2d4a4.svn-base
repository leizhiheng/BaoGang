package com.baosteel.qcsh.ui.activity.my.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.OrderLogisticsAdapter;
import com.baosteel.qcsh.ui.adapter.OrderProductAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 物理详情
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-22
 */
public class LogisticsDetailActivity extends BaseActivity implements View.OnClickListener {
    
	/**标题栏**/
	private TitleBar mTitle_bar;
    
	/**商品listview**/
	private ListView productListView;
	
	/**物流跟踪listview**/
	private ListView logisticsListView;
	
	/**订单商品适配器**/
	private OrderProductAdapter productAdapter;
	
	/**物流适配器**/
	private OrderLogisticsAdapter logisticsAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_detail);
        
        initViews();
       
        intiData();
    }

    /**
     * 初始化控件
     */
    private void initViews(){
    	
    	//标题
        mTitle_bar = (TitleBar) findViewById(R.id.title_bar);
        mTitle_bar.setTitle("物流详情");
        mTitle_bar.setBackgroud(R.color.index_red);
        mTitle_bar.setRightIconVisibility(0, View.VISIBLE);
        
        //商品列表，物流列表
        productListView = (ListView)findViewById(R.id.productListView);
        logisticsListView = (ListView)findViewById(R.id.logisticsListView);
        
        productAdapter = new OrderProductAdapter(mContext);
        productListView.setAdapter(productAdapter);
        logisticsAdapter = new OrderLogisticsAdapter(mContext);
        logisticsListView.setAdapter(logisticsAdapter);
    }

    /**
     * 初始化数据
     */
    private void intiData(){
    	
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }
}
