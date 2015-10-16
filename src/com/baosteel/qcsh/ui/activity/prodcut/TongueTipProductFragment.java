package com.baosteel.qcsh.ui.activity.prodcut;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.constants.ConstantsAPI;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl.Area;
import com.baosteel.qcsh.dialog.PositionSelect;
import com.baosteel.qcsh.dialog.SimpleMsgDialog;
import com.baosteel.qcsh.dialog.tagviewdialog.ProductAttributeSelecteDialogMy;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.QueryAppGoodsCommentBean;
import com.baosteel.qcsh.model.QueryAppGoodsDetailBean;
import com.baosteel.qcsh.model.QueryAppGoodsSpecListBean;
import com.baosteel.qcsh.ui.activity.cart.ConfirmOrderActivity;
import com.baosteel.qcsh.ui.activity.cart.ConfirmServiceOderActivity;
import com.baosteel.qcsh.ui.activity.store.RedPackageListActivity;
import com.baosteel.qcsh.ui.activity.store.StoreMainActivity;
import com.baosteel.qcsh.ui.adapter.CombinationProductAdapter;
import com.baosteel.qcsh.ui.adapter.GoodsDetailGalleryAdapter;
import com.baosteel.qcsh.ui.adapter.ProductCXMJAdapter;
import com.baosteel.qcsh.ui.adapter.ProductCXMZAdapter;
import com.baosteel.qcsh.ui.adapter.ProductCXZPAdapter;
import com.baosteel.qcsh.ui.view.BottomPopWindow;
import com.baosteel.qcsh.ui.view.MyGridView;
import com.baosteel.qcsh.utils.BaiduMapUtil;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Preferences;
import com.baosteel.qcsh.utils.Preferences.PreKey;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseActivity;
import com.common.base.BaseFragment;
import com.common.net.uploadimg.ImageAdapter;
import com.common.net.uploadimg.ImageData;
import com.common.utils.DeviceUtils;
import com.common.utils.LogUtil;
import com.common.utils.MathUtil;
import com.common.view.listview.HorizontialListView;
import com.common.view.listview.MyListView;
import com.common.view.photoview.anim.DepthPageTransformer;
import com.common.view.scrollview.NestScrollView;
import com.google.gson.Gson;

/**
 * 舌尖宝商品详情-商品
 * Created by kuangyong on 15/9/1.
 */
public class TongueTipProductFragment extends BaseFragment implements ViewPager.OnPageChangeListener,View.OnClickListener{


    public static final String GOODS_ID="";//商品id


    private NestScrollView mScrollView;
    // --------------------产品轮换图---------
    private android.support.v4.view.ViewPager gallery;                  //商品图片
    private TextView tvtip;                                             //商品图片提示
    private GoodsDetailGalleryAdapter galleryAdapter;                   //适配器
    private List<String> listGallerys;                                  //商品图片数据
//    private TextView gallery_count;                                   //显示图片下标
    private LinearLayout indicator;                                     //图片指示器
    private List<ImageView> indicatorList;                              //图片指示器的下标图片集合
    private RelativeLayout gallerylayout;                               //商品图片布局
    private TextView tvproname;                                         //产品名称
    private LinearLayout btnshare;                                      //分享按钮
    private TextView tvappraise;                                        //评价
    private TextView tvprice;                                           //价格
    private TextView tvactive;                                          //活动描述
    private TextView tvtransport;                                       //运费描述
    private TextView tvsalenum;                                         //月销量
    private TextView tvadress;                                          //地址
    private TextView tvcx1;                                             //促销1
    private TextView tvcx1describ;                                      //促销描述1
    private TextView tvcx2;                                             //促销2
    private TextView tvcx3;                                             //促销3
    private TextView tvcx4;                                             //促销4
    private TextView tvcx5;                                             //促销5
    private TextView tvcx6;                                             //促销6
    private LinearLayout layoutcxmore;                                  //更多促销布局
    private LinearLayout layoutcxunopen;                                //促销收起布局
    private com.common.view.listview.MyListView lvmj;                   //满减列表
    private LinearLayout layoutmj;                                      //满减布局
    private com.common.view.listview.HorizontialListView lvzh;          //组合列表
    private LinearLayout layoutzh;                                      //组合布局
    private com.common.view.listview.MyListView lvzs;                   //赠品列表
    private LinearLayout layoutzs;                                      //赠品布局
    private com.common.view.listview.MyListView lvmz;                   //满赠列表
    private LinearLayout layoutmz;                                      //满赠布局
    private LinearLayout layoutcxopen;                                  //促销展开布局
    private LinearLayout layout_stop;                                   //收起
    private LinearLayout layoutcx;                                      //整个促销布局
    private TextView tvsendadress;                                      //送至地址
    private TextView tvstock;                                           //库存
    private LinearLayout layoutadress;                                  //送至布局
    private LinearLayout layoutreceivejuan;                             //领取劵布局
    private LinearLayout layoutselecteattribute;                        //选择商品属性
    private LinearLayout layoutproparrams;                              //商品参数
    private TextView tvappraiseusername;                                //评价人姓名
    private android.widget.RatingBar ratingBar;                         //评价星星
    private TextView tvtime;                                            //评价时间
    private TextView tvdescrib;                                         //评价描述
    private android.widget.GridView gvimgs;                             //评价图片
    private TextView tvappraisemore;                                    //更多评价
    private android.widget.ImageView ivstore;                           //店铺图标
    private TextView tvstorename;                                       //店铺名称
    private TextView tvdescribscore;                                    //店铺描述评分
    private TextView tvservicescore;                                    //服务评分
    private TextView tvreleasescore;                                    //物流评分
    private TextView tvallpronum;                                       //全部商品数量
    private TextView tv_attribute;                                      //商品规格属性
    private TextView tvattentionnum;                                    //店铺关注数量
    private LinearLayout layoutlistsearch;                              //查看宝贝分类
    private LinearLayout layout2store;                                  //进店逛逛
    private LinearLayout layoutstore;                                   //整个店铺布局
    private LinearLayout layout_main;                                   //主布局
    private String provinceName;                                        //省
    private String cityName;                                            //市
    private String areaName;                                            //区
    private int provinceId;                                             //省id
    private int cityId;                                                 //市id
    private int areaId;                                                 //区id
    private QueryAppGoodsDetailBean bean;                               //商品详情数据
    private String goodsId = null;                                        //商品id

    private QueryAppGoodsCommentBean commentBean;                       //评价列表数据
    private ImageAdapter uploadImageAdapter;                            //评价图片适配器
    private int oneLocate;                                              //一次定位只回调一次


    public static TongueTipProductFragment newInstance(String goodsId) {
        TongueTipProductFragment fragment = new TongueTipProductFragment();
        Bundle args = new Bundle();
        args.putString(GOODS_ID, goodsId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            goodsId = getArguments().getString(GOODS_ID);
        }
    }

    private TongueTipProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.layout_fragment_tonguetip_product, null);
        initAllLists();
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        //初始化控件
        findView();
        
        //禁止scrollview自动滚动到底部 add by liuyuanqi 20150930
        disableAutoScrollToBottom();
        
        //设置监听数据
        setListener();

        //启动百度定位
        setPosition();
    }

    
    //启动百度定位
    private void setPosition(){
        oneLocate=-1;
        BaiduMapUtil.getInstance().setOnLocateLResultistener(new BaiduMapUtil.OnLocateLResultistener() {
            @Override
			public void onLocateResult(BDLocation location) {
				if (-1 == oneLocate) {
					
					String province = location.getProvince();
					String city = location.getCity();
					String area = location.getDistrict();
					
					if (!TextUtils.isEmpty(province) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(area)) {
						LogUtil.i("Location", "当前定位城市：" + province + "  " + city + "  " + area);
						
						//停止定位
						oneLocate++;
						BaiduMapUtil.getInstance().stop();
						
						//计算运费
						caculateFreight(province, city, area);
						
					}else{
						
						//定位失败
						LogUtil.i("Location", "定位失败....");
					}
				}

			}
        });
        BaiduMapUtil.getInstance().start();
        LogUtil.i("Location", "开启定位.....");
    }

    /**
     * 根据省市区，计算运费
     * @param provinceName
     * @param cityName
     * @param areaName
     */
    private void caculateFreight(String provinceName, String cityName, String areaName){
    	
    	//非空
    	if(!TextUtils.isEmpty(provinceName) && !TextUtils.isEmpty(cityName) && !TextUtils.isEmpty(areaName)){
    		
    		//显示当前省市区
    		tvsendadress.setText(provinceName + " " + cityName + " " + areaName);
    		this.provinceName = provinceName;
    		this.cityName = cityName;
    		this.areaName = areaName;
    		
    		//查询省市区id
    		LogUtil.i("Location", "城市表查询Name：" + provinceName + "  " + cityName + "  " + areaName);
    		SQLOpearteImpl temp = new SQLOpearteImpl(mContext);
			int provinceId = temp.checkIdByName(provinceName);
			int cityId = temp.checkIdByName(cityName);
			int areaId = temp.checkIdByName(areaName);
			LogUtil.i("Location", "城市表查询ID：" + provinceId + "  " + cityId + "  " + areaId);
			
			//查询运费
			if(provinceId > 0 && cityId > 0 && areaId > 0){
				
				queryGoodsFreight(provinceId, cityId, areaId);
				this.provinceId = provinceId;
				this.cityId = cityId;
				this.areaId = areaId;
			}
    	}
    }
    
    
    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("TongueTipProductFragment", "重新加载数据！®");
        //加载商品详情数据
        reloadDate();
    }

    public void reloadDate(){
        getQueryAppGoodsDetail("");
        getQueryAppGoodsComment();
    }

    @Override
    public void onStop() {
        super.onStop();
        BaiduMapUtil.getInstance().stop();
    }

    /**
	 * 禁止自动滚动到底部
	 */
	private void disableAutoScrollToBottom() {
		mScrollView = (NestScrollView)findViewById(R.id.scrollview);
		mScrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
		mScrollView.setFocusable(true);
		mScrollView.setFocusableInTouchMode(true);
		mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });
    }
    
    /**
     * 获取商品详情数据
     */
    public void getQueryAppGoodsDetail(String snId){
    	
    	if (StringUtils.isEmpty(goodsId)) return;
    	
    	String userId = BaoGangData.getInstance().getUserId();
    	
        RequestClient.queryAppGoodsDetail(mContext, goodsId, userId, snId, new RequestCallback<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    bean = new Gson().fromJson(response.toString(), QueryAppGoodsDetailBean.class);
                    if (null != bean) {
                    	if (bean.getReturnMap().getStatus().equals("3")) {
                    		//显示商品图片
                            fillMainImageGallery();

                            //商品参数赋值
                            initView();

                            //显示轮播图
                            addViewToIndicator();

                            if(null!=listener&&null!=bean.getReturnMap()){
                                listener.getProType(bean.getReturnMap().getGoods_genre());
                            }
                            mScrollView.setVisibility(View.VISIBLE);
						} else {
							final SimpleMsgDialog dialog = new SimpleMsgDialog(mContext);
							dialog.setCancelable(false);
							dialog.hideCancelBtn();
							dialog.setOkText("返回");
							dialog.setMsg("该商品已下架！");
							dialog.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									if (v.getId() == R.id.btn_ok) {
										dialog.dismiss();
										/*
										 * 将该商品状态通知到商品列表页面，以便删除列表中的该商品
										 */
										Intent intent = new Intent();
										intent.putExtra(BaseActivity.EXTRA_KEY_RESULT, bean.getReturnMap().getStatus());
										mContext.setResult(0, intent);
										mContext.finish();
									}
								}
							});
							dialog.show();
						}

                        
                    }
                }
            }
        });
    }

    /**
     * 获取商品评价
     */
    private void getQueryAppGoodsComment(){
    	
    	if (StringUtils.isEmpty(goodsId)) return;
    	
        RequestClient.queryAppGoodsComment(mContext, goodsId, "1", "1", new RequestCallback<JSONObject>(true) {
            @Override
            public void onResponse(JSONObject response) {

                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    commentBean = new Gson().fromJson(response.toString(), QueryAppGoodsCommentBean.class);
                    if (null != commentBean && commentBean.getReturnMap().hasComments()) {
                        tvappraise.setText("宝贝评价（"+commentBean.getReturnMap().getTotal()+"）");
                        tvappraiseusername.setText(commentBean.getReturnMap().getList().get(0).getUsername());
                        ratingBar.setRating(commentBean.getReturnMap().getList().get(0).getAvgScore());
                        tvtime.setText(commentBean.getReturnMap().getList().get(0).getComment_time());
                        tvdescrib.setText(commentBean.getReturnMap().getList().get(0).getContent());
                        String images=commentBean.getReturnMap().getList().get(0).getUrl();
                        String []imgs=images.split(",");
                        List<ImageData> imageDatas=new ArrayList<ImageData>();
                        for (int i=0;i<imgs.length;i++){
                            ImageData imageData=new ImageData("",imgs[i]);
                            imageDatas.add(imageData);
                        }
                        uploadImageAdapter.refreshData(imageDatas);
                    }
                }
            }
        });
    }

    /**
     * 初始化控件
     */
    private void findView() {
//        this.gallerycount = (TextView) findViewById(R.id.gallery_count);
        this.tvtip = (TextView) findViewById(R.id.tv_tip);
        this.gallery = (ViewPager) findViewById(R.id.gallery);
        this.indicator = (LinearLayout) findViewById(R.id.indicator);
        this.layoutstore = (LinearLayout) findViewById(R.id.layout_store);
        this.layout2store = (LinearLayout) findViewById(R.id.layout_2store);
        this.layoutlistsearch = (LinearLayout) findViewById(R.id.layout_list_search);
        this.tvattentionnum = (TextView) findViewById(R.id.tv_attention_num);
        this.tvallpronum = (TextView) findViewById(R.id.tv_allpro_num);
        this.tvreleasescore = (TextView) findViewById(R.id.tv_release_score);
        this.tvservicescore = (TextView) findViewById(R.id.tv_service_score);
        this.tvdescribscore = (TextView) findViewById(R.id.tv_describ_score);
        this.tvstorename = (TextView) findViewById(R.id.tv_store_name);
        this.ivstore = (ImageView) findViewById(R.id.iv_store);
        this.tvappraisemore = (TextView) findViewById(R.id.tv_appraise_more);
        this.gvimgs = (MyGridView) findViewById(R.id.gv_imgs);
        this.tvdescrib = (TextView) findViewById(R.id.tv_describ);
        this.tvtime = (TextView) findViewById(R.id.tv_time);
        this.ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        this.tvappraiseusername = (TextView) findViewById(R.id.tv_appraise_username);
        this.tvappraise = (TextView) findViewById(R.id.tv_appraise);
        this.layoutproparrams = (LinearLayout) findViewById(R.id.layout_pro_parrams);
        this.layoutselecteattribute = (LinearLayout) findViewById(R.id.layout_selecte_attribute);
        this.layoutreceivejuan = (LinearLayout) findViewById(R.id.layout_receive_juan);
        this.layoutadress = (LinearLayout) findViewById(R.id.layout_adress);
        this.layout_stop = (LinearLayout) findViewById(R.id.layout_stop);
        this.tvstock = (TextView) findViewById(R.id.tv_stock);
        this.tv_attribute = (TextView) findViewById(R.id.tv_attribute);
        this.tvsendadress = (TextView) findViewById(R.id.tv_send_adress);
        this.layoutcx = (LinearLayout) findViewById(R.id.layout_cx);
        this.layoutcxopen = (LinearLayout) findViewById(R.id.layout_cx_open);
        this.layoutmz = (LinearLayout) findViewById(R.id.layout_mz);
        this.lvmz = (MyListView) findViewById(R.id.lv_mz);
        this.layoutzs = (LinearLayout) findViewById(R.id.layout_zs);
        this.lvzs = (MyListView) findViewById(R.id.lv_zs);
        this.layoutzh = (LinearLayout) findViewById(R.id.layout_zh);
        this.lvzh = (HorizontialListView) findViewById(R.id.lv_zh);
        this.layoutmj = (LinearLayout) findViewById(R.id.layout_mj);
        this.lvmj = (MyListView) findViewById(R.id.lv_mj);
        this.layoutcxunopen = (LinearLayout) findViewById(R.id.layout_cx_unopen);
        this.layoutcxmore = (LinearLayout) findViewById(R.id.layout_cx_more);
        this.tvcx6 = (TextView) findViewById(R.id.tv_cx6);
        this.tvcx5 = (TextView) findViewById(R.id.tv_cx5);
        this.tvcx4 = (TextView) findViewById(R.id.tv_cx4);
        this.tvcx3 = (TextView) findViewById(R.id.tv_cx3);
        this.tvcx2 = (TextView) findViewById(R.id.tv_cx2);
        this.tvcx1describ = (TextView) findViewById(R.id.tv_cx1_describ);
        this.tvcx1 = (TextView) findViewById(R.id.tv_cx1);
        this.tvadress = (TextView) findViewById(R.id.tv_adress);
        this.tvsalenum = (TextView) findViewById(R.id.tv_sale_num);
        this.tvtransport = (TextView) findViewById(R.id.tv_transport);
        this.tvactive = (TextView) findViewById(R.id.tv_active);
        this.tvprice = (TextView) findViewById(R.id.tv_price);
        this.btnshare = (LinearLayout) findViewById(R.id.btn_share);
        this.layout_main = (LinearLayout) findViewById(R.id.layout_main);
        this.tvproname = (TextView) findViewById(R.id.tv_proname);
        this.gallerylayout = (RelativeLayout) findViewById(R.id.gallery_layout);
    }

	/**
	 * 绑定点击事件
	 */
    private void setListener() {
        gallery.setOnPageChangeListener(this);
        btnshare.setOnClickListener(this);
        layoutcxunopen.setOnClickListener(this);
        layoutadress.setOnClickListener(this);
        layoutreceivejuan.setOnClickListener(this);
        layoutselecteattribute.setOnClickListener(this);
        layoutproparrams.setOnClickListener(this);
        tvappraisemore.setOnClickListener(this);
        layoutstore.setOnClickListener(this);
        layout2store.setOnClickListener(this);
        layoutlistsearch.setOnClickListener(this);
        layout_stop.setOnClickListener(this);
        int margin = mContext.getResources().getDimensionPixelSize(R.dimen.dp_10)*2;
        uploadImageAdapter=new ImageAdapter(mContext, DeviceUtils.getWidthMaxPx(mContext)-margin,5);
        gvimgs.setAdapter(uploadImageAdapter);
        gvimgs.setNumColumns(5);
    }

    /**
     * 显示库存和定位,服务
     */
    private void showStockAndLocationAndServe() {
    	
    }

    /**
     * 填充商品的主图片
     */
    private void fillMainImageGallery() {
        // 产品图
    	if(null != bean && null != bean.getReturnMap() && null != bean.getReturnMap().getImgs()){
	        List<QueryAppGoodsDetailBean.ReturnMapEntity.ImgsEntity> imgs = bean.getReturnMap().getImgs();
	        listGallerys.clear();//在加载新商品的轮播图之前，清除旧商品的轮播图
	        for (int i=0;i<imgs.size();i++){
	            listGallerys.add(imgs.get(i).getImg_url());
	        }
    	}
    }

    private void initView() {
        galleryAdapter = new GoodsDetailGalleryAdapter(listGallerys, mContext);
        gallery.setPageTransformer(true, new DepthPageTransformer());
        gallery.setAdapter(galleryAdapter);
        ProductCXMJAdapter adapter_mj=new ProductCXMJAdapter(mContext);
        lvmj.setAdapter(adapter_mj);
        ProductCXMZAdapter adapter_mz=new ProductCXMZAdapter(mContext);
        lvmz.setAdapter(adapter_mz);
        ProductCXZPAdapter adapter_zs=new ProductCXZPAdapter(mContext);
        lvzs.setAdapter(adapter_zs);
        CombinationProductAdapter productAdapter=new CombinationProductAdapter(mContext);
        lvzh.setAdapter(productAdapter);
        tvproname.setText(bean.getReturnMap().getName());
        tvprice.setText(MathUtil.priceForAppWithSign(bean.getReturnMap().getPrice()));
        tvactive.setText(bean.getReturnMap().getAd());
        tvsalenum.setText("月销"+bean.getReturnMap().getMonth_sale_count()+"笔");
        tvadress.setText(bean.getReturnMap().getSellerProvince()+bean.getReturnMap().getSellerCity());
        tvstorename.setText(  bean.getReturnMap().getMerchantName());
        tvdescribscore.setText(bean.getReturnMap().getDescription_score());
        tvservicescore.setText(bean.getReturnMap().getService_score());
        tvreleasescore.setText(bean.getReturnMap().getLogistics_score());
        tvallpronum.setText(bean.getReturnMap().getGoods_count());
        tvattentionnum.setText(bean.getReturnMap().getInterests());
        ImageManager.Load(bean.getReturnMap().getMerchantLogo(), ivstore);
        tv_attribute.setText(bean.getReturnMap().getSpecValueNames());
    }

    private void initAllLists() {
        listGallerys = new ArrayList<String>();
        indicatorList = new ArrayList<ImageView>();
    }

    /**
     * 初始化主图片下面的圆点指示器
     */
    private void addViewToIndicator() {
        if (0 != indicator.getChildCount()) {
            indicator.removeAllViews();
        }
        if (0 != indicatorList.size()) {
            indicatorList = new ArrayList<ImageView>();
        }
        if (listGallerys.size() > 0) {
//            gallery_count.setText(1 + "/" + listGallerys.size() + "张");
            for (int i = 0; i < listGallerys.size(); i++) {
                ImageView imageView = new ImageView(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT); // , 1是可选写的
                lp.setMargins(5, 0, 5, 0);
                imageView.setLayoutParams(lp);
                if (i == 0) {
                    imageView
                            .setImageResource(R.drawable.page_indicator_focused);
                } else {
                    imageView.setImageResource(R.drawable.page_indicator);
                }
                indicator.addView(imageView);
                indicatorList.add(imageView);
            }
            if (listGallerys.size() > 1) {
                indicator.setVisibility(View.VISIBLE);
            } else {
                indicator.setVisibility(View.GONE);
            }
        } else {
//            gallery_count.setText("");
            indicator.setVisibility(View.GONE);
        }
    }

    /**
     * 选择省市县位置
     */
    private void selectPosition(){
    	
        //选择位置
        View relyview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_tonguetip_product, null);
        PositionSelect positionSelect = new PositionSelect(getActivity(), relyview, provinceId, cityId, areaId);
        positionSelect.setOnSelectResultListener(new PositionSelect.SelectResultListener() {
            @Override
            public void selectResult(Area province, Area city, Area area) {

            	//非空判断
            	if(null == province || null == city || null == area){
            		return;
            	}
            	
                //记住当前选中的省市县
                provinceId = (null != province) ? province.RecNo : -1;
                cityId = (null != city) ? city.RecNo : -1;
                areaId = (null != area) ? area.RecNo : -1;

                //文本赋值
                provinceName = (null != province) ? province.name : "";
                cityName = (null != city) ? city.name : "";
                areaName = (null != area) ? area.name : "";
                tvsendadress.setText(provinceName + " " + cityName + " " + areaName);

                //三级数据都不为空才查询运费
                if (null != province && null != city && null != area) {
                    queryGoodsFreight(provinceId, cityId, areaId);
                }
            }
        });
    }

    /**
     * 根据地区查区商品运费与库存情况
     */
    private void queryGoodsFreight(int provinceId, int cityId, int areaId){
    	
    	if (StringUtils.isEmpty(goodsId)) return;
    	
        RequestClient.queryGoodsFreight(mContext, goodsId, provinceId + "", cityId + "", areaId + "", new RequestCallback<JSONObject>(false) {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    JSONObject returnMap = response.optJSONObject("returnMap");
                    String freight = returnMap.optString("freight");//运费
                    String available = returnMap.optString("available");//有/无货
                    tvtransport.setText("运费  " + freight);
                    tvstock.setText(available);
                }
            }
        });
    }

    /**
     * 商品参数
     */
    private void queryAppGoodsIntroduce(){
    	
    	if (StringUtils.isEmpty(goodsId)) return;
    	
        RequestClient.queryAppGoodsIntroduce(mContext, goodsId, "2", "1", new RequestCallback<JSONObject>(false) {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    JSONObject returnMap = response.optJSONObject("returnMap");
                    if (null != returnMap) {
                        String content = returnMap.optString("content");//商品参数
                        new BottomPopWindow(mContext, layout_main, content);
                    }
                }
            }
        });
    }

    /**
     * 商品属性
     */
    private void getQueryAppGoodsSpecValueList(){
        
    	//参数，商品id， 商品默认规格
    	String goodsId = bean.getReturnMap().getId();
    	String spec_value = bean.getReturnMap().getSpecValueIds();
    	
    	if (StringUtils.isEmpty(goodsId)) return;
    	
    	//查询商品规格属性列表
        RequestClient.queryAppGoodsSpecValueList(mContext, goodsId, spec_value, new RequestCallback<JSONObject>(true) {
            @Override
            public void onResponse(JSONObject response) {

                if (JSONParseUtils.isSuccessRequest(mContext, response)) {

                	//获取规格数据
                    QueryAppGoodsSpecListBean specListBean = new Gson().fromJson(response.toString(), QueryAppGoodsSpecListBean.class);
                    
                    //选中默认规格
                    specListBean.getReturnMap().checkDefaultSpec();
                    
                    if (null != specListBean) {
                    	
                    	//弹出规格对话框
                        String proType="1";
                        if(null!=bean&&null!=bean.getReturnMap()){
                            proType=bean.getReturnMap().getGoods_genre();
                        }
                    	final ProductAttributeSelecteDialogMy dialog = new ProductAttributeSelecteDialogMy(mContext, specListBean,proType);
                    	dialog.show();
                    	
                        dialog.setClicklistener(new ProductAttributeSelecteDialogMy.ClickListenerInterface() {

                            @Override
                            public void doAdd2Shoppingcar(String spec, String num) {
                                dialog.dismiss();

                                //这里需要把商品属性传递下
                                getQueryAppAddShopCar(ConstantsAPI.Shopping_Type_Add_Cart, num, spec);
                            }

                            @Override
                            public void doBuyNow(String spec, String num) {
                                dialog.dismiss();

                                getQueryAppAddShopCar(ConstantsAPI.Shopping_Type_Buy_Now, num, spec);
                            }

                            @Override
                            public void reLoadProductData(String snId) {
                                getQueryAppGoodsDetail(snId);
                            }

                            @Override
                            public void setProNum(String num) {
                                if(null!=listener){
                                    listener.setProNum(num);
                                }
                            }


                        });
                    }
                }
            }
        });
    }

    /**
     * 加入购物车
     * @param shoppingType 		1需要显示在购物车中的商品  (加入购物车)
        						0不需要显示在购物车中的商品(立即购买)
     * @param num 				商品购买数量
     * @param spec 				商品规格 (如果spec为空，则会自动填上默认规格)
     */
    public void getQueryAppAddShopCar(final int shoppingType, String num, String spec){
    	
    	//商品非空判断
    	if(null == bean){
    		return;
    	}
        
        String goodsId = this.goodsId; 
        final String user_id = BaoGangData.getInstance().getUserId();
        String sn_id = bean.getReturnMap().getSnId();
        String type= bean.getReturnMap().getGoods_genre();			//1、普通商品 2 服务商品
        double surprice = bean.getReturnMap().getPrice();			//商品价格
        String seller_id = bean.getReturnMap().getMerchantId();		//这里 商家ID 就是 店铺ID
        String shopping_id = Preferences.getString(PreKey.SHOPPINGIDS);
        
        //如果规格为空，则选中商品中默认规格
        spec = TextUtils.isEmpty(spec) ? bean.getReturnMap().getSpecValueIds() : spec;
        
        if (StringUtils.isEmpty(goodsId)) return;
        
        RequestClient.queryAppAddShopCar(mContext, goodsId, user_id, num+"", sn_id, type, surprice+"", spec, seller_id, shoppingType+"", shopping_id, new RequestCallback<JSONObject>(true) {
            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {
                    
                	JSONObject returnMap = response.optJSONObject("returnMap");
                	
                	//购物车id
                    String shoppingId = returnMap.optString("shoppingId");
                    
                    switch (shoppingType) {
					case ConstantsAPI.Shopping_Type_Add_Cart:
						
						//加入购物车
						showToast("恭喜您！加入购车成功！");
						
						
						//加入购物车成功，需要刷新购物车数量
						refreshShopcartCount();
						
						
						//用户没有登录，需要将shoppingid保存到本地
						//其目的是为了，在未登录的时候，能够根据购物车id查询到购物车列表
						//当然，在用户登录注册的时候需要将本地的购物车id传给服务器，将购物车商铺与用户绑定
						if(!userIsLogin()){
							Preferences.appendString(PreKey.SHOPPINGIDS, shoppingId, ",");
						}
						
						break;

					case ConstantsAPI.Shopping_Type_Buy_Now:
						
                        if(null!=bean&&null!=bean.getReturnMap()){
                            if(ConstantsAPI.PRO_TYPE_SERVICE.equals(bean.getReturnMap().getGoods_genre())){//服务商品
                                //立即购买(跳转到结算界面)
                                Bundle bundle = new Bundle();
                                bundle.putString(ConfirmServiceOderActivity.SHOPPING_IDS, shoppingId);
                                bundle.putString("price", bean.getReturnMap().getPrice() + "");
                                Intent intent = new Intent(mContext, ConfirmServiceOderActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }else if(ConstantsAPI.PRO_TYPE_COMMON.equals(bean.getReturnMap().getGoods_genre())){//实物商品
                                //立即购买(跳转到结算界面)
                                Bundle bundle = new Bundle();
                                bundle.putString(ConfirmOrderActivity.SHOPPING_IDS, shoppingId);
                                bundle.putInt(ConfirmOrderActivity.ORDER_FROM_TYPE, 0);
                                bundle.putString("price", bean.getReturnMap().getPrice() + "");
                                Intent intent = new Intent(mContext, ConfirmOrderActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                        }else{
                            showToast("网络错误，请重试！");
                        }

						break;
					}

                }
            }
        });
    }


    /**
     * 刷新购物车数量
     */
    private void refreshShopcartCount(){
    	
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        galleryAdapter.setCurrentIndex(position);
//        gallery_count.setText((position + 1) + "/" + listGallerys.size());
        for (int i = 0; i < indicatorList.size(); i++) {
            if (i == position) {
                indicatorList.get(i).setImageResource(
                        R.drawable.page_indicator_focused);
            } else {
                indicatorList.get(i)
                        .setImageResource(R.drawable.page_indicator);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_share://分享
//                showToast("分享");
                break;
            case R.id.layout_cx_unopen://促销展开
                expendCXLayout(true);
//                showToast("促销展开");
                break;
            case R.id.layout_stop://促销收起
                expendCXLayout(false);
//                showToast("促销收起");
                break;
            case R.id.layout_adress://选择省市区
                selectPosition();
                break;
            case R.id.layout_receive_juan://领取现金劵
                startActivity(new Intent(mContext, RedPackageListActivity.class));
                break;
            case R.id.layout_selecte_attribute://选择商品属性
                getQueryAppGoodsSpecValueList();
                break;
            case R.id.layout_pro_parrams://选择商品参数
                queryAppGoodsIntroduce();
                break;
            case R.id.tv_appraise_more://更多评价
                if(null!=listener){
                    listener.go2Appraise();
                }
                break;
            case R.id.layout_store://进入店铺
            case R.id.layout_2store://进入店铺
            	Intent intent = new Intent(mContext, StoreMainActivity.class);
    			intent.putExtra(StoreMainActivity.MERCHANT_ID, getStoreId());
    			startActivity(intent);
                break;
            case R.id.layout_list_search://查看宝贝分类
                //showToast("查看宝贝分类");
                break;
        }
    }

    /**
     * 是否展开促销
     * @param isExpend
     */
    private void expendCXLayout(boolean isExpend){
        if(isExpend){
            layoutcxopen.setVisibility(View.VISIBLE);
            layoutcxunopen.setVisibility(View.GONE);
        }else{
            layoutcxopen.setVisibility(View.GONE);
            layoutcxunopen.setVisibility(View.VISIBLE);
        }
    }
    
    /**
     * 
     * @Description 返回店铺ID
     * @Author blue
     * @Time 2015-9-29
     */
    public String getStoreId() {
    	if (bean == null || bean.getReturnMap() == null) {
			return "51";
		} else {
			return bean.getReturnMap().getMerchantId();
		}
    }

    public interface Go2AppraiseListener{
        void go2Appraise();

        void setProNum(String num);

        void getProType(String goods_genre);
    }

    /**
     * 跳转到评价列表的回调
     */
    private Go2AppraiseListener listener;

    public void setGo2AppraiseListener(Go2AppraiseListener listener){
        this.listener=listener;
    }
}
