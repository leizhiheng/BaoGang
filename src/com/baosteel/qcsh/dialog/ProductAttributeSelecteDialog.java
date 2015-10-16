package com.baosteel.qcsh.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.QueryAppGoodsSpecListBean;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * 选择商品属性对话框
 * Created by kuangyong on 15/9/7.
 */
public class ProductAttributeSelecteDialog extends Dialog {

    private Context context;
    private ClickListenerInterface clickListenerInterface;//回调
    private QueryAppGoodsSpecListBean specListBean;//规格列表
    private TextView btnbuynow;// 立即购买
    private TextView btnadd2shoppingcar;// 加入购物车
    private LinearLayout btnclose;// 关闭dialog
    private TextView tvtip;// 提示
    private TextView tvstock;// 库存
    private TextView tvprice;// 商品价格
    private LinearLayout layout_valuelist;// 属性列表
    private int dialogWidth;//dialog宽度

    public interface ClickListenerInterface {

        void doAdd2Shoppingcar(String spec);

        void doBuyNow(String spec);

        void reLoadProductData();
    }

    public ProductAttributeSelecteDialog(Context context, QueryAppGoodsSpecListBean specListBean) {
        super(context, R.style.dialog);
        this.context = context;
        this.specListBean = specListBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化数据
     */
    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog_product_attribute,
                null);
        btnbuynow = (TextView) view.findViewById(R.id.btn_buynow);// 立即购买
        btnadd2shoppingcar = (TextView) view
                .findViewById(R.id.btn_add2shoppingcar);// 加入购物车
        btnclose = (LinearLayout) view
                .findViewById(R.id.btn_close);// 关闭dialog
        tvtip = (TextView) view.findViewById(R.id.tv_tip);// 提示
        tvstock = (TextView) view.findViewById(R.id.tv_stock);// 库存
        tvprice = (TextView) view.findViewById(R.id.tv_price);// 商品价格
        layout_valuelist = (LinearLayout) view.findViewById(R.id.layout_valuelist);// 属性列表
        setContentView(view);
        reFreshData();//刷新数据
        btnclose.setOnClickListener(new clickListener());
        btnbuynow.setOnClickListener(new clickListener());
        btnadd2shoppingcar.setOnClickListener(new clickListener());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.9); // 宽度设置为屏幕的0.9
        lp.height = (int) (d.heightPixels * 0.6); // 高度设置为屏幕的0.6
        dialogWidth=lp.width;
        dialogWindow.setAttributes(lp);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            switch (id) {
                case R.id.btn_buynow:
                	
                	// 立即购买
                	
                    clickListenerInterface.doBuyNow(null);
                    
                    
                    break;
                case R.id.btn_add2shoppingcar:
                	
                	// 加入购物车
                	
                    clickListenerInterface.doAdd2Shoppingcar(null);

                    break;
                case R.id.btn_close:// 关闭对话框
                    ProductAttributeSelecteDialog.this.dismiss();
                    break;
            }
        }
    }

    /**
     * 商品属性
     */
    private void getQueryAppGoodsSpecValueList(String goodsId, String spec_value) {
        goodsId = 85 + "";
        spec_value = "46,47";
        RequestClient.queryAppGoodsSpecValueList(context, goodsId, spec_value, new RequestCallback<JSONObject>(true) {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(context, response)) {
                    specListBean = new Gson().fromJson(response.toString(), QueryAppGoodsSpecListBean.class);
                    if (null != specListBean) {
                        reFreshData();
                    }
                }
            }
        });
    }

    /**
     * 刷新数据
     */
    private void reFreshData() {
        List<QueryAppGoodsSpecListBean.ReturnMapEntity.SpecEntity> valueList= specListBean.getReturnMap().getSpec();
        for (int i = 0; i < valueList.size(); i++) {//遍历属性列表
            View itemview = LayoutInflater.from(context).inflate(R.layout.item_attibute, null);//每一个item
            LinearLayout layoutvalue2list = (LinearLayout) itemview.findViewById(R.id.layout_value2list);//属性值列表
            TextView tvattributename = (TextView) itemview.findViewById(R.id.tv_attributename);//属性名称
            tvattributename.setText(valueList.get(i).getName());
            LinearLayout layout=new LinearLayout(context);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params);//设置宽高
            layout.setOrientation(LinearLayout.HORIZONTAL);//设置横向排布
            List<QueryAppGoodsSpecListBean.ReturnMapEntity.SpecEntity.SpecValueListEntity> specValueList
                    =valueList.get(i).getSpecValueList();//属性值列表
//            for(int j=0;j<specValueList.size();j++){//遍历属性值列表
//                TextView tvValue=new TextView(context);
//                // tag text
//                tvValue.setText(tag.text);
//                tvValue.setPadding(textPaddingLeft, textPaddingTop, textPaddingRight, texPaddingBottom);
//                tvValue.setTextColor(tag.tagTextColor);
//                tvValue.setTextSize(Utils.spToPx(getContext(), tag.tagTextSize));
//                tagLayout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (mClickListener != null) {
//                            mClickListener.onTagClick(tag, position);
//                        }
//                    }
//                });
//
//                // calculate　of tag layout width
//                float tagWidth = tvValue.getPaint().measureText(tag.text) + textPaddingLeft + textPaddingRight;
//                // tvValue padding (left & right)
//
//                // deletable text
//                TextView deletableView = (TextView) tagLayout.findViewById(R.id.tv_tag_item_delete);
//                if (tag.isDeletable) {
//                    deletableView.setVisibility(View.VISIBLE);
//                    deletableView.setText(tag.deleteIcon);
//                    deletableView.setPadding(0, textPaddingTop, textPaddingRight, texPaddingBottom);
//                    deletableView.setTextColor(tag.deleteIndicatorColor);
//                    deletableView.setTextSize(Utils.spToPx(getContext(), tag.deleteIndicatorSize));
//                    deletableView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            tvValue.this.remove(position);
//                            if (mDeleteListener != null) {
//                                Tag targetTag = tag;
//                                mDeleteListener.onTagDeleted(targetTag, position);
//                            }
//                        }
//                    });
//                    tagWidth += deletableView.getPaint().measureText(tag.deleteIcon) +textPaddingLeft + textPaddingRight;
//                    // deletableView Padding (left & right)
//                } else {
//                    deletableView.setVisibility(View.GONE);
//                }
//
//                RelativeLayout.LayoutParams tagParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                //tagParams.setMargins(0, 0, 0, 0);
//
//                //add margin of each line
//                tagParams.bottomMargin = lineMargin;
//
//                if (mWidth <= total + tagWidth + Utils.dipToPx(this.getContext(), Constants.LAYOUT_WIDTH_OFFSET)) {
//                    //need to add in new line
//                    tagParams.addRule(RelativeLayout.BELOW, index_bottom);
//                    // initialize total param (layout padding left & layout padding right)
//                    total = getPaddingLeft() + getPaddingRight();
//                    index_bottom = listIndex;
//                    index_header =listIndex;
//                } else {
//                    //no need to new line
//                    tagParams.addRule(RelativeLayout.ALIGN_TOP, index_header);
//                    //not header of the line
//                    if (listIndex!=index_header) {
//                        tagParams.addRule(RelativeLayout.RIGHT_OF, listIndex - 1);
//                        tagParams.leftMargin = tagMargin;
//                        total += tagMargin;
//                        if (tag_pre!=null&&tag_pre.tagTextSize<tag.tagTextSize) {
//                            index_bottom = listIndex;
//                        }
//                    }
//
//
//
//                }
//                total += tagWidth;
//                addView(tagLayout, tagParams);
//            }
        }
    }
}
