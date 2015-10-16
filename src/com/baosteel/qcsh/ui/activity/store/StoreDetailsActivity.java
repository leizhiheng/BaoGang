package com.baosteel.qcsh.ui.activity.store;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.dialog.SimpleIconDialog;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.store.QueryAppMerchantInfoBean;
import com.baosteel.qcsh.model.store.StoreDetailData;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.baosteel.qcsh.ui.view.StoreCategoryPopWindow;
import com.baosteel.qcsh.ui.view.StoreContactServicePopWindow;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.circle.RingProgressView;
import com.common.view.imageview.CircleImageView;
import com.google.gson.Gson;

/**
 * 店铺主页 Created by kuangyong on 15/9/17.
 */
public class StoreDetailsActivity extends BaseActivity implements
        View.OnClickListener {

    /**
     * 用于传递店铺详情信息
     **/
    public static final String EXTRA_STORE_DETAIL = "store.detail";

    private ImageView iv_main;
    private LinearLayout layoutback;// 返回键
    private TextView btnsearch;// 搜索
    private FrameLayout layoutright;// 右侧按钮
    private CircleImageView ivstoreicon;// 店铺图标
    private TextView tvstorename;// 店铺名称
    private TextView tvattentionnum;// 收藏人数
    private LinearLayout btnattention;// 收藏按钮
    private TextView tv_attentin;// 收藏文本
    private LinearLayout btnshare;// 分享按钮
    private TextView tvallpronum;// 全部商品数量
    private LinearLayout btnallpro;// 全部商品按钮
    private TextView tvnewpronum;// 新品数量
    private LinearLayout btnnewpro;// 新品按钮
    private TextView tvdiscountpronum;// 促销数量
    private LinearLayout btndiscountpro;// 促销按钮
    private TextView btnallcategory;// 全部分类
    private TextView btnstoredetials;// 店铺详情
    private TextView btnservice;// 联系客服
    private LinearLayout layout_bottom;
    private LinearLayout btngo2storedetails;//进入店铺
    private LinearLayout layoutbottom;
    private com.common.view.circle.RingProgressView ring;//环
    private TextView tvdescribscore;//描述相符
    private TextView tvservicescore;//联系客服
    private TextView tvreleasescore;//发货速度
    private TextView tvdescribpercentate;//描述相符百分比
    private TextView tvservicepercentate;//联系客服百分比
    private TextView tvreleasepercentate;//发货速度百分比
    private TextView tvcompanyname;//公司名称
    private LinearLayout layoutcompanyname;
    private TextView tvadress;//地址
    private TextView tvdetailsadress;//详细地址
    private LinearLayout btnlocation;
    private TextView tvtime;//成立时间

    private String mStoreId;//店铺Id
    private QueryAppMerchantInfoBean bean;
    private StoreDetailData mStoreDetailData;//店铺详情
    private boolean isCollectedStore;//是否收藏了该店铺

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        mStoreId = getIntent().getStringExtra(StoreMainActivity.EXTRA_STORE_ID);
        bean = (QueryAppMerchantInfoBean) getIntent().getSerializableExtra(StoreMainActivity.EXTRA_STORE_MERCHANT_DATA);

        findView();
        setListener();
//		initView();
        loadStoreMsg();
    }

    private void findView() {
        this.iv_main = (ImageView) findViewById(R.id.iv_main);
        this.btnservice = (TextView) findViewById(R.id.btn_service);
        this.btnstoredetials = (TextView) findViewById(R.id.btn_store_detials);
        this.btnallcategory = (TextView) findViewById(R.id.btn_all_category);
        this.btndiscountpro = (LinearLayout) findViewById(R.id.btn_discountpro);
        this.tvdiscountpronum = (TextView) findViewById(R.id.tv_discountpro_num);
        this.btnnewpro = (LinearLayout) findViewById(R.id.btn_newpro);
        this.tvnewpronum = (TextView) findViewById(R.id.tv_newpro_num);
        this.btnallpro = (LinearLayout) findViewById(R.id.btn_allpro);
        this.tvallpronum = (TextView) findViewById(R.id.tv_allpro_num);
        this.btnshare = (LinearLayout) findViewById(R.id.btn_share);
        this.btnattention = (LinearLayout) findViewById(R.id.btn_attention);
        this.tv_attentin = (TextView) findViewById(R.id.tv_attentin);
        this.tvattentionnum = (TextView) findViewById(R.id.tv_attention_num);
        this.tvstorename = (TextView) findViewById(R.id.tv_store_name);
        this.ivstoreicon = (CircleImageView) findViewById(R.id.iv_store_icon);
        this.layoutright = (FrameLayout) findViewById(R.id.layout_right);
        this.btnsearch = (TextView) findViewById(R.id.btn_search);
        this.layoutback = (LinearLayout) findViewById(R.id.layout_back);
        this.layout_bottom = (LinearLayout) findViewById(R.id.layout_bottom);
        this.tvtime = (TextView) findViewById(R.id.tv_time);
        this.btnlocation = (LinearLayout) findViewById(R.id.btn_location);
        this.tvdetailsadress = (TextView) findViewById(R.id.tv_details_adress);
        this.tvadress = (TextView) findViewById(R.id.tv_adress);
        this.layoutcompanyname = (LinearLayout) findViewById(R.id.layout_company_name);
        this.tvcompanyname = (TextView) findViewById(R.id.tv_company_name);
        this.tvreleasepercentate = (TextView) findViewById(R.id.tv_release_percentate);
        this.tvservicepercentate = (TextView) findViewById(R.id.tv_service_percentate);
        this.tvdescribpercentate = (TextView) findViewById(R.id.tv_describ_percentate);
        this.tvreleasescore = (TextView) findViewById(R.id.tv_release_score);
        this.tvservicescore = (TextView) findViewById(R.id.tv_service_score);
        this.tvdescribscore = (TextView) findViewById(R.id.tv_describ_score);
        this.ring = (RingProgressView) findViewById(R.id.ring);
        this.layoutbottom = (LinearLayout) findViewById(R.id.layout_bottom);
        this.btngo2storedetails = (LinearLayout) findViewById(R.id.btn_go2store_details);
    }

    private void setListener() {
        layoutback.setOnClickListener(this);
        layoutright.setOnClickListener(this);
        btnattention.setOnClickListener(this);
        btnshare.setOnClickListener(this);
        btnallpro.setOnClickListener(this);
        btnnewpro.setOnClickListener(this);
        btndiscountpro.setOnClickListener(this);
        btnstoredetials.setOnClickListener(this);
        btnallcategory.setOnClickListener(this);
        btnservice.setOnClickListener(this);
        btnlocation.setOnClickListener(this);
        layoutcompanyname.setOnClickListener(this);
        btnsearch.setOnClickListener(this);
    }

    private void initView() {

        ring.setScore(Float.parseFloat(mStoreDetailData.getMultipleScore()));
        tvdescribscore.setText(mStoreDetailData.getDescriptionScore());
        tvservicescore.setText(mStoreDetailData.getServiceScore());
        tvreleasescore.setText(mStoreDetailData.getSendGoodsScore());
        tvcompanyname.setText(mStoreDetailData.getCompanyName());
        tvadress.setText(mStoreDetailData.getAddress());
        tvdetailsadress.setText(mStoreDetailData.getDetailAddress());
        tvtime.setText(mStoreDetailData.getMerchantOpenTime());

        ImageManager.Load(bean.getReturnMap().getBackground_img_app(), iv_main);//加载背景图片
        ImageManager.Load(bean.getReturnMap().getLogo(), ivstoreicon);//店铺图标
        tvstorename.setText(bean.getReturnMap().getMerchant_name());//店铺名称
        tvattentionnum.setText(bean.getReturnMap().getAttentionCount());//收藏人数
        tvallpronum.setText(bean.getReturnMap().getGoodsCount());//全部商品数量
        tvnewpronum.setText(bean.getReturnMap().getNewGoodsCount());//新品数量
    }

    /**
     * @param merchantId 店铺Id
     * @Description 下载店铺信息
     * @Author blue
     * @Time 2015-9-29
     */
    private void loadStoreMsg() {

        RequestClient.queryAppStoreDetail(this, mStoreId, new RequestCallback<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(StoreDetailsActivity.this, response)) {
                    Gson gson = new Gson();
                    try {
                        mStoreDetailData = gson.fromJson(response.getString("returnMap"), StoreDetailData.class);
                        initView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * @Description 店铺主页-判断是否收藏该店铺
     * @Author blue
     * @Time 2015-9-29
     */
    public void queryAppUserStoreCollect() {
        RequestClient.queryAppUserStoreCollect(this, mStoreId, BaoGangData.getInstance().getUserId(), new RequestCallback<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                String type = JSONParseUtils.getString(response, "type");
                /*
                 * type=0表示请求失败
				 * type=1表示用户已收藏该商家
				 * type=2表示用户没有收藏该商家.
				 */
                if (type.equals("1")) {
                    isCollectedStore = true;
                } else if (type.equals("2")) {
                    isCollectedStore = false;
                } else if (type.equals("0")) {
                    showToast(JSONParseUtils.getString(response, "msg"));
                }
                setCollectText();
            }
        });
    }

    /**
     * @Description 店铺主页-收藏店铺
     * @Author blue
     * @Time 2015-9-29
     */
    public void addAppStoreCollect() {
        RequestClient.addAppStoreCollect(this, mStoreId, BaoGangData.getInstance().getUserId(), new RequestCallback<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(StoreDetailsActivity.this, response)) {
                    isCollectedStore = true;
                    setCollectText();
                    SimpleIconDialog dialog = new SimpleIconDialog(StoreDetailsActivity.this);
                    dialog.setMsg("收藏成功");
                    dialog.show();
                }
            }
        });
    }

    /**
     * @Description 店铺主页-取消收藏店铺
     * @Author blue
     * @Time 2015-9-29
     */
    public void deleteAppStoreCollect() {
        RequestClient.deleteAppStoreCollect(this, mStoreId, BaoGangData.getInstance().getUserId(), new RequestCallback<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(StoreDetailsActivity.this, response)) {
                    isCollectedStore = false;
                    setCollectText();
                    SimpleIconDialog dialog = new SimpleIconDialog(StoreDetailsActivity.this);
                    dialog.setMsg("已取消收藏");
                    dialog.show();
                }
            }
        });
    }

    private void setCollectText() {
        if (isCollectedStore) {

            tv_attentin.setText("已收藏");
            tv_attentin.setTextColor(getResources().getColor(R.color.index_red));
        } else {

            tv_attentin.setText("收藏");
            tv_attentin.setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_store_detials:// 店铺主页
                startActivity(new Intent(mContext, StoreMainActivity.class));
                break;
            case R.id.layout_back:// 返回键
                finish();
                break;
            case R.id.layout_right:// menu
                showToast("菜单");
                break;
            case R.id.btn_attention:// 收藏
                if (userIsLogin()) {
                    if (isCollectedStore) {
                        deleteAppStoreCollect();//如果已收藏，则取消收藏
                    } else {
                        addAppStoreCollect();//如果未收藏，则收藏
                    }
                } else {
                    showToast("请先登录！");
                }

                break;
            case R.id.btn_share:// 分享
                showToast("分享成功！");
                break;
            case R.id.btn_allpro:// 全部商品
                Intent intent1=new Intent(mContext, StoreProductListActivity.class);
                intent1.putExtra(StoreProductListActivity.MERCHANT_ID,mStoreId);
                startActivity(intent1);
                break;
            case R.id.btn_newpro:// 新品
                Intent intent_newpro = new Intent(mContext, MoreProductsActivity.class);
                intent_newpro.putExtra(MoreProductsActivity.TITLE, MoreProductsActivity.PRO_NEW);
                intent_newpro.putExtra(MoreProductsActivity.MERCHANTID, mStoreId);
                startActivity(intent_newpro);
                break;
            case R.id.btn_discountpro:// 促销
                showToast("促销");
                break;
            case R.id.btn_all_category:// 全部分类
                new StoreCategoryPopWindow(mContext, layout_bottom, mStoreId);
                break;
            case R.id.btn_service:// 客服
                new StoreContactServicePopWindow(mContext, layout_bottom);
                break;
            case R.id.btn_location:// 定位
                showToast("定位");
                break;
            case R.id.btn_search:// 搜索
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.layout_company_name:// 查询执照
                Intent intent = new Intent(mContext, LicencseQueryActivity.class);
                intent.putExtra(EXTRA_STORE_DETAIL, mStoreDetailData);
                startActivity(intent);
                break;
        }
    }

	@Override
	protected void onResume() {
		super.onResume();
		if (userIsLogin()) {
			queryAppUserStoreCollect();
		}
	}
}
