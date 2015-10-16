package com.baosteel.qcsh.dialog.tagviewdialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.model.QueryAppGoodsSpecListBean;
import com.baosteel.qcsh.model.QueryAppGoodsSpecListBean.ReturnMapEntity.SpecEntity;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.utils.LogUtil;
import com.common.utils.MathUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * 选择商品属性对话框
 *
 * @author 刘远祺
 * @todo TODO
 * @date 2015-10-9
 */
public class ProductAttributeSelecteDialogMy extends Dialog implements DialogInterface.OnDismissListener{

    private Context context;
    private ClickListenerInterface clickListenerInterface;              // 回调
    private QueryAppGoodsSpecListBean specListBean;                     // 规格列表
    private TextView btnbuynow;                                         // 立即购买
    private TextView btnadd2shoppingcar;                                // 加入购物车
    private LinearLayout btnclose;                                      // 关闭dialog
    private TextView tvtip;                                             // 提示
    private TextView tvstock;                                           // 库存
    private TextView tvprice;                                           // 商品价格
    private TextView tv_cut_num;                                        // 减号
    private TextView tv_change_num;                                     // 显示数量
    private TextView tv_add_num;                                        // 加号
    private ImageView iv_pro;                                           // 商品图片
    private ListView layout_valuelist;                                  // 属性列表
    private int dialogWidth;                                            // dialog宽度
    private LinearLayout layout_btns;                                   //按钮布局
    private String proType;                                             //商品类型

    /**
     * 规格适配器
     **/
    private ProductAttributeSelecteAdapter adapter;


    public interface ClickListenerInterface {

        void doAdd2Shoppingcar(String spec,String num);

        void doBuyNow(String spec,String num);

        void reLoadProductData(String snId);

        void setProNum(String num);
    }

    public ProductAttributeSelecteDialogMy(Context context, QueryAppGoodsSpecListBean specListBean,String proType) {
        super(context, R.style.dialog);
        this.context = context;
        this.proType = proType;
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
        View view = inflater.inflate(R.layout.layout_dialog_product_attribute_my, null);
        btnbuynow = (TextView) view.findViewById(R.id.btn_buynow);// 立即购买
        btnadd2shoppingcar = (TextView) view.findViewById(R.id.btn_add2shoppingcar);// 加入购物车
        btnclose = (LinearLayout) view.findViewById(R.id.btn_close);// 关闭dialog
        tvtip = (TextView) view.findViewById(R.id.tv_tip);// 提示
        tv_cut_num = (TextView) view.findViewById(R.id.tv_cut_num);// 减号
        tv_add_num = (TextView) view.findViewById(R.id.tv_add_num);// 加号
        tv_change_num = (EditText) view.findViewById(R.id.tv_change_num);
        iv_pro = (ImageView) view.findViewById(R.id.iv_pro);// 提示
        tvstock = (TextView) view.findViewById(R.id.tv_stock);// 库存
        tvprice = (TextView) view.findViewById(R.id.tv_price);// 商品价格
        layout_btns = (LinearLayout) view.findViewById(R.id.layout_btns);
        layout_valuelist = (ListView) view.findViewById(R.id.layout_valuelist);// 属性列表
        adapter = new ProductAttributeSelecteAdapter(context);
        layout_valuelist.setAdapter(adapter);
        adapter.setOnAttributeItemSelectedListener(new ProductAttributeSelecteAdapter.OnAttributeItemSelectedListener() {
            @Override
            public void onItemClick(String spec_value) {
                loadAttributeData(specListBean.getReturnMap().getGoodsSnSpec().getGoodsId(),spec_value);
                LogUtil.d("规格","重新加载数据。。。");
            }
        });
        setContentView(view);
        reFreshData(specListBean.getReturnMap().getSpec());// 刷新数据
        btnclose.setOnClickListener(new clickListener());
        btnbuynow.setOnClickListener(new clickListener());
        btnadd2shoppingcar.setOnClickListener(new clickListener());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.9); // 宽度设置为屏幕的0.9
        lp.height = (int) (d.heightPixels * 0.6); // 高度设置为屏幕的0.6
        dialogWidth = lp.width;
        dialogWindow.setAttributes(lp);
        setProductAttribute();
        if(ConstantsAPI.PRO_TYPE_SERVICE.equals(proType)){//服务商品
            btnbuynow.setText("立即预约");
            btnadd2shoppingcar.setVisibility(View.GONE);
            layout_btns.setWeightSum(1);
            btnbuynow.setBackgroundResource(R.drawable.common_right_and_left_bottomadius_orange_red);
        }
    }


    /**
     * 设置商品参数
     */
    private void setProductAttribute() {
        QueryAppGoodsSpecListBean.ReturnMapEntity.GoodsSnSpecEntity entity =
                specListBean.getReturnMap().getGoodsSnSpec();
        ImageManager.Load(specListBean.getReturnMap().getGoodsimg(), iv_pro);
        tvprice.setText(MathUtil.priceForAppWithOutSign(entity.getGoodsCommonPrice()));
        tvstock.setText("库存" + entity.getGoodsCount() + "件");
        if("0".equals(entity.getGoodsCount())){//库存为0
            tv_change_num.setText("0");
        }else{
            tv_change_num.setText("1");
        }
        tv_cut_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numStr = tv_change_num.getText().toString();
                double num = Double.parseDouble(numStr);
                numStr= --num+"";
                int index=numStr.indexOf(".");
                numStr=numStr.substring(0,index);
                tv_change_num.setText(numStr);
            }
        });
        tv_add_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numStr=tv_change_num.getText().toString();
                double num=Double.parseDouble(numStr);
                numStr= ++num+"";
                int index=numStr.indexOf(".");
                numStr=numStr.substring(0,index);
                tv_change_num.setText(numStr);
            }
        });

        tv_change_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String numStr=tv_change_num.getText().toString();
                double count=Double.parseDouble(specListBean.getReturnMap().getGoodsSnSpec().getGoodsCount());
                if(count==0){//库存为0
                    if(!numStr.equals("0")){
                        Toast.makeText(getContext(),"库存为0",Toast.LENGTH_SHORT).show();
                        tv_change_num.setText("0");
                    }
                }else{
                    if("".equals(numStr)){
                        Toast.makeText(getContext(),"最小数量为1",Toast.LENGTH_SHORT).show();
                        tv_change_num.setText("1");
                    }else{
                        double num=Double.parseDouble(numStr);

                        LogUtil.d("库存---",count+"");
                        if(num<1){//小于1,库存不为0
                            Toast.makeText(getContext(),"最小数量为1",Toast.LENGTH_SHORT).show();
                            tv_change_num.setText("1");
                        }
                        if(num > count ){//大于库存
                            Toast.makeText(getContext(),"最大数量为库存",Toast.LENGTH_SHORT).show();
                            String numStr1=count+"";
                            int index=numStr1.indexOf(".");
                            numStr1=numStr1.substring(0,index);
                            tv_change_num.setText(numStr1+"");
                        }
                    }
                }
            }
        });
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
                    LogUtil.i("Product", "liji spec:" + adapter.getSelectSpec());
                    if(null!=clickListenerInterface) {
                        clickListenerInterface.doBuyNow(adapter.getSelectSpec(), tv_change_num.getText().toString());
                    }
                    break;
                case R.id.btn_add2shoppingcar:

                    // 加入购物车
                    LogUtil.i("Product", "add spec:" + adapter.getSelectSpec());
                    if(null!=clickListenerInterface) {
                        clickListenerInterface.doAdd2Shoppingcar(adapter.getSelectSpec(), tv_change_num.getText().toString());
                    }
                    break;
                case R.id.btn_close:// 关闭对话框
                    ProductAttributeSelecteDialogMy.this.dismiss();
                    break;
            }
        }
    }

    /**
     * 刷新数据
     */
    private void reFreshData(List<SpecEntity> dataList) {
        adapter.refreshData(dataList);
    }

    private void loadAttributeData(String goodsId,String spec_value) {
        //查询商品规格属性列表
        RequestClient.queryAppGoodsSpecValueList(context, goodsId, spec_value, new RequestCallback<JSONObject>(false) {
            @Override
            public void onResponse(JSONObject response) {

                if (JSONParseUtils.isSuccessRequest(context, response)) {

                    //获取规格数据
                    specListBean = new Gson().fromJson(response.toString(), QueryAppGoodsSpecListBean.class);

                    //选中默认规格
                    specListBean.getReturnMap().checkDefaultSpec();

                    if (null != specListBean) {
                        reFreshData(specListBean.getReturnMap().getSpec());// 刷新数据
                        setProductAttribute();//设置商品规格参数
                        if (null != clickListenerInterface) {//刷新商品详情数据
                            clickListenerInterface.reLoadProductData(specListBean.getReturnMap().getGoodsSnSpec().getId());
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(null!=clickListenerInterface){
            clickListenerInterface.setProNum(tv_change_num.getText().toString());
        }
    }
}
