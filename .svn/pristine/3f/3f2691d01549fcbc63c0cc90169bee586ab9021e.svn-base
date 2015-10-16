package com.baosteel.qcsh.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.model.store.QueryAppStoreGoodsTypeBean;
import com.baosteel.qcsh.ui.activity.store.StoreProductListActivity;
import com.baosteel.qcsh.ui.adapter.ProductCatogeryTitleAdapter;
import com.baosteel.qcsh.ui.adapter.Store2CategoryListAdapter;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺分类
 * Created by lian on 2015/5/14.
 */
public class StoreCategoryPopWindow {
    private static final String START_STATUS="-1";
    private Context context;
    private View parentview;
    private PopupWindowUtils popupWindowUtils;
    private List<ProductCategory> categories;
    private List<ProductCategory> categories2;
    private String merchantId;//店铺id
    private ProductCatogeryTitleAdapter adapter;//一级分类列表适配器
    private Store2CategoryListAdapter categoryListAdapter;//二级分类列表适配器

    public StoreCategoryPopWindow(Context context, View relyview,String merchantId){
        this.context=context;
        this.merchantId=merchantId;
        parentview= LayoutInflater.from(context).inflate(R.layout.pop_store_category, null);
        popupWindowUtils=new PopupWindowUtils(context, parentview, relyview, Gravity.BOTTOM,0,0,R.style.PopupAnimation,0.5f);
        init();
        getQueryAppStoreGoodsType("-1");
    }
    public void init(){
        ListView lv_category= (ListView) parentview.findViewById(R.id.lv_category);//1级列表
        ListView lv_2category= (ListView) parentview.findViewById(R.id.lv_2category);//2级列表
        View layout_bottom= parentview.findViewById(R.id.layout_bottom);


        /**
         * 一级列表
         */
        categories=new ArrayList<ProductCategory>();
        adapter=new ProductCatogeryTitleAdapter(context,categories,context.getResources().getColor(R.color.brick_red));
        lv_category.setAdapter(adapter);
        lv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (ProductCategory category : categories) {
                    category.setSelected(false);
                }
                categories.get(position).setSelected(true);
                adapter.notifyDataSetChanged();
                getQueryAppStoreGoodsType(categories.get(position).getId());//请求二级列表数据
            }
        });
        /**
         * 二级列表
         */
        categories2=new ArrayList<ProductCategory>();
        categoryListAdapter=new Store2CategoryListAdapter(context,categories2);
        lv_2category.setAdapter(categoryListAdapter);
        final PopupWindow pop= popupWindowUtils.getPopupWindow();
        lv_2category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pop.dismiss();
                Intent intent=new Intent(context, StoreProductListActivity.class);
                intent.putExtra(StoreProductListActivity.MERCHANT_ID,merchantId);
                intent.putExtra(StoreProductListActivity.CATEGORY,categories2.get(position));
                context.startActivity(intent);
            }
        });
        layout_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
    }

    /**
     * 获取分类信息
     * @param goodsTypeId   店铺分类id，-1表示第一级，其余表示取该id下对应的分类
     */
    private void getQueryAppStoreGoodsType(final String goodsTypeId){
        RequestClient.queryAppStoreGoodsType(context, merchantId, goodsTypeId, new RequestCallback<JSONObject>(false) {
            @Override
            public void onResponse(JSONObject response) {
                if(JSONParseUtils.isSuccessRequest(context,response)){
                    QueryAppStoreGoodsTypeBean bean =new Gson().fromJson(response.toString(),QueryAppStoreGoodsTypeBean.class);
                    if(null==bean||null==bean.getReturnMap()){
                        return ;
                    }
                    if(START_STATUS.equals(goodsTypeId)){//第一次加载
                        categories=bean.getReturnMap();
                        categories.get(0).setSelected(true);
                        adapter.setData(categories);
                        getQueryAppStoreGoodsType(categories.get(0).getId());
                    }else{//加载下一级数据
                        categories2=bean.getReturnMap();
                        categoryListAdapter.refreshDate(categories2);
                    }
                }
            }
        });
    }
}
