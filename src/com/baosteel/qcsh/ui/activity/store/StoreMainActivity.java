package com.baosteel.qcsh.ui.activity.store;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.dialog.SimpleIconDialog;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.store.QueryAppMerchantInfoBean;
import com.baosteel.qcsh.model.store.QueryAppStoreNewListBean;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.baosteel.qcsh.ui.activity.prodcut.TongueTipProductDetailsActivity;
import com.baosteel.qcsh.ui.adapter.JuanHorizontalListAdapter;
import com.baosteel.qcsh.ui.adapter.StoreProductAdapter;
import com.baosteel.qcsh.ui.view.MyGridView;
import com.baosteel.qcsh.ui.view.StoreCategoryPopWindow;
import com.baosteel.qcsh.ui.view.StoreContactServicePopWindow;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;
import com.common.view.banner.BannerData;
import com.common.view.banner.BannerView;
import com.common.view.banner.IOnBannerItenClick;
import com.common.view.imageview.CircleImageView;
import com.common.view.listview.HorizontialListView;
import com.common.view.scrollview.NestScrollView;
import com.common.view.viewflow.CircleFlowIndicator;
import com.common.view.viewflow.ViewFlow;
import com.google.gson.Gson;

/**
 * 店铺主页
 * Created by kuangyong on 15/9/17.
 */
public class StoreMainActivity extends BaseActivity implements
        View.OnClickListener {

    public static final String MERCHANT_ID = "merchantId";
    /**
     * 用于传递店铺id
     **/
    public static final String EXTRA_STORE_ID = "store.id";
    /**
     * 用于传递QueryAppMerchantInfoBean对象
     **/
    public static final String EXTRA_STORE_MERCHANT_DATA = "merchant.data";

    private TextView tv_attentin;// 关注文本
    private LinearLayout layoutback;                                            // 返回键
    private TextView btnsearch;                                                 // 搜索
    private android.widget.FrameLayout layoutright;                             // 右侧按钮
    private com.common.view.imageview.CircleImageView ivstoreicon;              // 店铺图标
    private TextView tvstorename;                                               // 店铺名称
    private TextView tvattentionnum;                                            // 关注人数
    private LinearLayout btnattention;                                          // 关注按钮
    private LinearLayout btnshare;                                              // 分享按钮
    private LinearLayout btn_go2store_details;                                  // 店铺详情按钮
    private TextView tvallpronum;                                               // 全部商品数量
    private LinearLayout btnallpro;                                             // 全部商品按钮
    private TextView tvnewpronum;                                               // 新品数量
    private LinearLayout btnnewpro;                                             // 新品按钮
    private TextView tvdiscountpronum;                                          // 促销数量
    private LinearLayout btndiscountpro;                                        // 促销按钮
    private TextView btnallcategory;                                            // 全部分类
    private TextView btnstoredetials;                                           // 店铺详情
    private TextView btnservice;                                                // 联系客服
    private com.common.view.listview.HorizontialListView lvjuan;                // 优惠劵列表
    private String merchantId;                                                  //店铺id
    /**
     * 图片轮播
     */
    private com.common.view.viewflow.ViewFlow mHomeViewflow;
    private com.common.view.viewflow.CircleFlowIndicator mHomeViewflowindic;
    private android.widget.RelativeLayout layoutgallery;
    private ScrollView scrollview;
    private LinearLayout layout_bottom;
    private ImageView iv_main;                                                  //主页背景
    private QueryAppMerchantInfoBean bean;                                      //店铺主要信息
    private BannerView bannerView;
    /**
     * 促销活动
     */
    private TextView btn_recommended_more;                                      //产品推荐更多按钮
    private TextView btn_newpro_more;                                           //新品发布更多按钮
    private GridView gv_recommended;                                            //产品推荐列表
    private GridView gv_newpro;                                                 //新品发布列表
    private int page = 1;                                                       //当前页
    private int pageSize = 4;                                                   //每页大小
    private boolean isCollectedStore;                                           //是否关注了该店铺


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_store);
        merchantId = getIntent().getStringExtra(MERCHANT_ID);
        Log.d(TAG, "onCreate merchantId = " + merchantId);
        findView();
        setListener();
        getQueryAppMerchantInfo();//获取主要信息
        getueryMerchantBanner();//获取bannar图
        getQueryAppStoreRecommendList();//获取产品推荐列表
        getQueryAppStoreNewList();//获取店铺新品发布列表
    }


    private void findView() {
        this.layoutgallery = (RelativeLayout) findViewById(R.id.layout_gallery);
        this.mHomeViewflowindic = (CircleFlowIndicator) findViewById(R.id.mHomeViewflowindic);
        this.mHomeViewflow = (ViewFlow) findViewById(R.id.mHomeViewflow);
        this.lvjuan = (HorizontialListView) findViewById(R.id.lv_juan);
        this.btnservice = (TextView) findViewById(R.id.btn_service);
        this.btnstoredetials = (TextView) findViewById(R.id.btn_store_detials);
        this.btnallcategory = (TextView) findViewById(R.id.btn_all_category);
        this.btndiscountpro = (LinearLayout) findViewById(R.id.btn_discountpro);
        this.tvdiscountpronum = (TextView) findViewById(R.id.tv_discountpro_num);
        this.btnnewpro = (LinearLayout) findViewById(R.id.btn_newpro);
        this.tvnewpronum = (TextView) findViewById(R.id.tv_newpro_num);
        this.btnallpro = (LinearLayout) findViewById(R.id.btn_allpro);
        this.tvallpronum = (TextView) findViewById(R.id.tv_allpro_num);
        this.btn_go2store_details = (LinearLayout) findViewById(R.id.btn_go2store_details);
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
        this.iv_main = (ImageView) findViewById(R.id.iv_main);
        this.btn_recommended_more = (TextView) findViewById(R.id.btn_recommended_more);
        this.btn_newpro_more = (TextView) findViewById(R.id.btn_newpro_more);
        this.gv_recommended = (MyGridView) findViewById(R.id.gv_recommended);
        this.gv_newpro = (MyGridView) findViewById(R.id.gv_newpro);

        // 禁止自动滚动到底部
        disableAutoScrollToBottom();
        // 轮播图
        ViewFlow viewFlow = (ViewFlow) findViewById(R.id.mHomeViewflow);// 获得viewFlow对象
        CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.mHomeViewflowindic); // viewFlow下的indic
        bannerView = new BannerView(mContext, viewFlow, indic);
        bannerView.setOnItemClickListener(new IOnBannerItenClick() {
            @Override
            public void onBannerItemClick(BannerData data, int position) {
                go2ProDetails(data.getGoods_id());
            }
        });
    }

    private void go2ProDetails(String goods_id){
        Intent intent=new Intent(mContext, TongueTipProductDetailsActivity.class);
        intent.putExtra(TongueTipProductDetailsActivity.GOOOS_ID, goods_id);
        startActivity(intent);
    }

    /**
     * 获取店铺首页主要信息
     */
    private void getQueryAppMerchantInfo() {
        RequestClient.queryAppMerchantInfo(mContext, merchantId, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    bean = new Gson().fromJson(response.toString(), QueryAppMerchantInfoBean.class);
                    if (null != bean) {
                        initView();
                    }
                }
            }
        });
    }

    /**
     * 获取店铺产品推荐列表
     */
    private void getQueryAppStoreRecommendList() {
        RequestClient.queryAppStoreRecommendList(mContext, merchantId, page + "", pageSize + "", new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					final QueryAppStoreNewListBean bean = new Gson().fromJson(response.toString(), QueryAppStoreNewListBean.class);
					if (null != bean) {
						StoreProductAdapter adapter = new StoreProductAdapter(mContext, bean.getReturnMap());
						gv_recommended.setAdapter(adapter);
                        gv_recommended.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                go2ProDetails(bean.getReturnMap().get(position).getGoods_id());
                            }
                        });
					}
				}
			}
		});
    }

    /**
     * 获取店铺新品发布列表
     */
    private void getQueryAppStoreNewList() {
        RequestClient.queryAppStoreNewList(mContext, merchantId, page + "", pageSize + "", new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					final QueryAppStoreNewListBean bean = new Gson().fromJson(response.toString(), QueryAppStoreNewListBean.class);
					if (null != bean) {
						StoreProductAdapter adapter = new StoreProductAdapter(mContext, bean.getReturnMap());
						gv_newpro.setAdapter(adapter);
                        gv_newpro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                go2ProDetails(bean.getReturnMap().get(position).getGoods_id());
                            }
                        });
					}
				}
			}
		});
    }

    /**
     * 获取店铺Banner
     */
    private void getueryMerchantBanner() {
        RequestClient.queryMerchantBanner(mContext, merchantId, new RequestCallback<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {

				if (JSONParseUtils.isSuccessRequest(mContext, response)) {

					//解析Banner数据
					bannerView.refreshData(JSONParseUtils.getBannerDatas(response));
				}
			}
		});
    }


    /**
     * 禁止自动滚动到底部
     */
    private void disableAutoScrollToBottom() {

        scrollview = (NestScrollView) findViewById(R.id.scrollview);
        scrollview.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollview.setFocusable(true);
        scrollview.setFocusableInTouchMode(true);
        scrollview.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				v.requestFocusFromTouch();
				return false;
			}
		});
    }

    private void setListener() {
        layoutback.setOnClickListener(this);
        layoutright.setOnClickListener(this);
        ivstoreicon.setOnClickListener(this);
        btnattention.setOnClickListener(this);
        btnshare.setOnClickListener(this);
        btn_go2store_details.setOnClickListener(this);
        btnallpro.setOnClickListener(this);
        btnnewpro.setOnClickListener(this);
        btndiscountpro.setOnClickListener(this);
        btnstoredetials.setOnClickListener(this);
        btnallcategory.setOnClickListener(this);
        btnservice.setOnClickListener(this);
        btnsearch.setOnClickListener(this);
        btn_recommended_more.setOnClickListener(this);
        btn_newpro_more.setOnClickListener(this);
    }

    /**
     * 设置店铺主要信息
     */
    private void setMainInfo() {
        ImageManager.Load(bean.getReturnMap().getBackground_img_app(), iv_main);//加载背景图片
        ImageManager.Load(bean.getReturnMap().getLogo(), ivstoreicon);//店铺图标
        tvstorename.setText(bean.getReturnMap().getMerchant_name());//店铺名称
        tvattentionnum.setText(bean.getReturnMap().getAttentionCount());//关注人数
        tvallpronum.setText(bean.getReturnMap().getGoodsCount());//全部商品数量
        tvnewpronum.setText(bean.getReturnMap().getNewGoodsCount());//新品数量
//		tvdiscountpronum.setText(bean.getReturnMap().getNewGoodsCount());//TODO-促销暂时没有，推后完成
    }

    private void initView() {
        setMainInfo();
        JuanHorizontalListAdapter adapterJuan = new JuanHorizontalListAdapter(
                mContext);
        lvjuan.setAdapter(adapterJuan);
        lvjuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				if (position % 2 == 0) {
					showToast("恭喜您！领取成功！");
				} else {
					showToast("您已领取过了！");
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
        RequestClient.queryAppUserStoreCollect(this, merchantId, BaoGangData.getInstance().getUserId(), new RequestCallback<JSONObject>() {

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
        RequestClient.addAppStoreCollect(this, merchantId, BaoGangData.getInstance().getUserId(), new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(StoreMainActivity.this, response)) {
					isCollectedStore = true;
					setCollectText();
					SimpleIconDialog dialog = new SimpleIconDialog(StoreMainActivity.this);
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
        RequestClient.deleteAppStoreCollect(this, merchantId, BaoGangData.getInstance().getUserId(), new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(StoreMainActivity.this, response)) {
					isCollectedStore = false;
					setCollectText();
					SimpleIconDialog dialog = new SimpleIconDialog(StoreMainActivity.this);
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
            case R.id.btn_go2store_details:// 店铺详情
            case R.id.iv_store_icon:// 店铺详情
            case R.id.btn_store_detials:// 店铺详情
                Intent intent = new Intent(mContext, StoreDetailsActivity.class);
                intent.putExtra(EXTRA_STORE_ID, merchantId);
                intent.putExtra(EXTRA_STORE_MERCHANT_DATA, bean);
                startActivity(intent);
                break;
            case R.id.layout_back:// 返回键
                finish();
                break;
            case R.id.layout_right:// menu
                showToast("菜单");
                break;
            case R.id.btn_attention:// 关注
//			ConfirmSimpleDialog dialog=new ConfirmSimpleDialog(mContext);
//			dialog.show();
//			dialog.setDialogTitle("关注成功！");
//			dialog.setDialogTipVisible(View.GONE);
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
//			ConfirmSimpleDialog dialogShared=new ConfirmSimpleDialog(mContext);
//			dialogShared.show();
//			dialogShared.setDialogTitle("分享成功！");
//			dialogShared.setDialogTipVisible(View.GONE);
                showToast("分享成功！");
                break;
            case R.id.btn_allpro:// 全部商品
                Intent intent1=new Intent(mContext, StoreProductListActivity.class);
                intent1.putExtra(StoreProductListActivity.MERCHANT_ID,merchantId);
                startActivity(intent1);
                break;
            case R.id.btn_discountpro:// 促销
                showToast("促销");
                break;
            case R.id.btn_all_category:// 全部分类
                //merchantId="1";
                new StoreCategoryPopWindow(mContext, layout_bottom, merchantId);
                break;
            case R.id.btn_service:// 客服
                new StoreContactServicePopWindow(mContext, layout_bottom);
                break;
            case R.id.btn_search:// 搜索
                startActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.btn_recommended_more://推荐更多
                Intent intent_recommended = new Intent(mContext, MoreProductsActivity.class);
                intent_recommended.putExtra(MoreProductsActivity.TITLE, MoreProductsActivity.PRO_RECOMMENDED);
                intent_recommended.putExtra(MoreProductsActivity.MERCHANTID, merchantId);
                startActivity(intent_recommended);
                break;
            case R.id.btn_newpro_more:// 新品更多
            case R.id.btn_newpro:// 新品
                Intent intent_newpro = new Intent(mContext, MoreProductsActivity.class);
                intent_newpro.putExtra(MoreProductsActivity.TITLE, MoreProductsActivity.PRO_NEW);
                intent_newpro.putExtra(MoreProductsActivity.MERCHANTID, merchantId);
                startActivity(intent_newpro);
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
