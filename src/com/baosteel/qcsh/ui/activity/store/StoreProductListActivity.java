package com.baosteel.qcsh.ui.activity.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.database.bean.Product;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.model.store.QueryAppStoreNewListBean;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.baosteel.qcsh.ui.activity.prodcut.TongueTipProductDetailsActivity;
import com.baosteel.qcsh.ui.adapter.ClassRecomendAdapter;
import com.baosteel.qcsh.ui.adapter.GroupBuyAdapter;
import com.baosteel.qcsh.ui.view.StoreScreeningPopWindow;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Utils;
import com.common.base.BaseActivity;
import com.common.view.pulltorefresh.PullToRefreshBase;
import com.common.view.pulltorefresh.PullToRefreshListView;
import com.common.view.topbar.HeadView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺列表 Created by kuangyong on 15/9/22.
 */
public class StoreProductListActivity extends BaseActivity implements
        View.OnClickListener, AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, ClassRecomendAdapter.OnProductClick {
    public static final String CATEGORY = "category";                           //商品类别
    public static final String MERCHANT_ID = "merchantId";                      //店铺id
    public static final String GOODS_TYPE_ID = "goodsTypeId";                   //商品类别id
    private com.common.view.topbar.HeadView headview;                           //标题栏
    private TextView btnsearch;                                                 //搜索
    private android.widget.ImageView btnlistchange;                             //展示方式切换
    private LinearLayout btnsalenum;                                            //销量优先
    private LinearLayout btnprice;                                              //价格高低
    private LinearLayout btnnewpro;                                             //新品
    private LinearLayout btnshaixuan;                                           //筛选
    private com.common.view.pulltorefresh.PullToRefreshListView gvtoplist;      //商品列表
    /**
     * 商品列表适配器
     **/
    private ClassRecomendAdapter adapter;
    private boolean isGrid = false;                                              //是否是大图
    private LinearLayout layout_bottom;
    private ProductCategory category;                                           //分类对象
    private String merchantId;                                                  //店铺id
    private String goodsTypeId;                                                 //分类ID(必填)
    private String orderBy;                                                     //0代表默认排序，1代表销量排序，2代表价格排序,3表示新品排序（非必填）；默认值0
    private String orderStyle;                                                  //0代表升序，1代表降序	(非必填)，默认值0
    private int pageNum = 1;                                                    //起始页，默认值1
    private int pageSize = 10;                                                  //每页数据量，默认值100
    private boolean showDialog = true;                                          //是否显示进度框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_prolist);
        category = (ProductCategory) getIntent().getSerializableExtra(CATEGORY);
        if(null!=category){
            goodsTypeId=category.getId();
        }
        findView();
        setListener();
        initView();
        merchantId=getStringExtra(MERCHANT_ID);
        orderBy="1";
        orderStyle="0";
        getueryAppStoreGoodsByType();
    }

    private void findView() {
        this.gvtoplist = (PullToRefreshListView) findViewById(R.id.gv_top_list);
        this.btnshaixuan = (LinearLayout) findViewById(R.id.btn_shaixuan);
        this.btnnewpro = (LinearLayout) findViewById(R.id.btn_newpro);
        this.btnprice = (LinearLayout) findViewById(R.id.btn_price);
        this.layout_bottom = (LinearLayout) findViewById(R.id.layout_bottom);
        this.btnsalenum = (LinearLayout) findViewById(R.id.btn_salenum);
        this.btnlistchange = (ImageView) findViewById(R.id.btn_list_change);
        this.btnsearch = (TextView) findViewById(R.id.btn_search);
        this.headview = (HeadView) findViewById(R.id.headview);
    }

    private void setListener() {
        btnsearch.setOnClickListener(this);
        btnlistchange.setOnClickListener(this);
        btnsalenum.setOnClickListener(this);
        btnprice.setOnClickListener(this);
        btnnewpro.setOnClickListener(this);
        btnshaixuan.setOnClickListener(this);
    }

    private void initView() {
        if(null!=category){
            headview.setTitle(category.getName());
        }else{
            headview.setTitle("全部商品");
        }
        headview.setRightImageBtnBackGround(R.drawable.icon_3point);
        headview.showRightImageBtn(View.VISIBLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("菜单");
            }
        });
        headview.setHeadViewBackGroundColor(getResources().getColor(
                R.color.red_baosteel));
        gvtoplist.setEnabled(false);
        gvtoplist.setMode(PullToRefreshBase.Mode.BOTH);
        gvtoplist.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        gvtoplist.getLoadingLayoutProxy().setLastUpdatedLabel(Utils.getCurrTiem());
        gvtoplist.getLoadingLayoutProxy().setPullLabel("往下拉更新数据...");
        gvtoplist.getLoadingLayoutProxy().setRefreshingLabel("正在载入中...");
        gvtoplist.getLoadingLayoutProxy().setReleaseLabel("放开更新数据...");

        // 下拉刷新数据
        gvtoplist.setOnRefreshListener(this);

        //设置adapter
        adapter = new ClassRecomendAdapter(mContext);
        adapter.setOnProductClick(this);
        gvtoplist.setAdapter(adapter);
    }

    /**
     * 获取店铺商品列表
     */
    private void getueryAppStoreGoodsByType() {
        RequestClient.queryAppStoreGoodsByType(mContext, merchantId, goodsTypeId, orderBy,
                orderStyle, pageNum + "", pageSize + "", new RequestCallback<JSONObject>(showDialog) {

                    @Override
                    public void onFinish() {
                        super.onFinish();

                        //只有首次加载数据才显示对话框
                        showDialog = false;

                        gvtoplist.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        if (JSONParseUtils.isSuccessRequest(mContext, response)) {

                            //解析数据
                            List<TopProduct> tempData = JSONParseUtils.getProductList(response);

                            //非空判断
                            if (null == tempData) {
                                return;
                            }

                            //第一页，下拉刷新操作
                            if (pageNum <= 1) {
                                adapter.refreshData(tempData);
                            } else {

                                //第二页后，上拉加载数据
                                adapter.appendData(tempData);
                            }

                            //无下一页数据,判断是否还有下一页
                            PullToRefreshBase.Mode mode = (tempData.size() < pageSize) ? PullToRefreshBase.Mode.PULL_FROM_START : PullToRefreshBase.Mode.BOTH;
                            gvtoplist.setMode(mode);

                            //pageIndex++
                            if (tempData.size() >= pageSize) {
                                pageNum++;
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list_change:// 切换展示方式
                if (isGrid) {
                    isGrid = !isGrid;
                    btnlistchange.setImageResource(R.drawable.icon_liebiao_datu);
                    //切换单列显示
                    adapter.ChangeView(isGrid);
                } else {
                    isGrid = !isGrid;
                    btnlistchange.setImageResource(R.drawable.icon_tupianliebiaoqiehuan);
                    //切换两列显示
                    adapter.ChangeView(isGrid);
                }
                break;
            case R.id.btn_salenum:// 销量优先
                orderBy="1";
                pageNum = 1;
                getueryAppStoreGoodsByType();
                break;
            case R.id.btn_price:// 价格高低
                orderBy="2";
                pageNum = 1;
                getueryAppStoreGoodsByType();
                break;
            case R.id.btn_shaixuan:// 筛选
                new StoreScreeningPopWindow(mContext, layout_bottom);
                break;
            case R.id.btn_newpro:// 新品
                orderBy="3";
                pageNum = 1;
                getueryAppStoreGoodsByType();
                break;
            case R.id.btn_search:// 搜索
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public void onProductClick(TopProduct product) {
        startActivity(new Intent(mContext, TongueTipProductDetailsActivity.class).putExtra(TongueTipProductDetailsActivity.GOOOS_ID,product.getId()));
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageNum = 1;
        getueryAppStoreGoodsByType();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        getueryAppStoreGoodsByType();
    }
}
