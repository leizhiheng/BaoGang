package com.baosteel.qcsh.ui.activity.prodcut;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl.Area;
import com.baosteel.qcsh.dialog.PositionSelect;
import com.baosteel.qcsh.dialog.ProductAttributeSelecteDialog;
import com.baosteel.qcsh.ui.activity.store.RedPackageListActivity;
import com.baosteel.qcsh.ui.adapter.CombinationProductAdapter;
import com.baosteel.qcsh.ui.adapter.GoodsDetailGalleryAdapter;
import com.baosteel.qcsh.ui.adapter.ProductCXMJAdapter;
import com.baosteel.qcsh.ui.adapter.ProductCXMZAdapter;
import com.baosteel.qcsh.ui.adapter.ProductCXZPAdapter;
import com.baosteel.qcsh.ui.view.BottomPopWindow;
import com.baosteel.qcsh.ui.view.MyGridView;
import com.common.base.BaseFragment;
import com.common.net.uploadimg.ImageAdapter;
import com.common.net.uploadimg.ImageData;
import com.common.utils.DeviceUtils;
import com.common.view.listview.HorizontialListView;
import com.common.view.listview.MyListView;
import com.common.view.photoview.anim.DepthPageTransformer;

/**
 * 安程宝商品详情-商品
 * Created by kuangyong on 15/9/1.
 */
public class CarProductFragment extends BaseFragment implements ViewPager.OnPageChangeListener,View.OnClickListener{
    // --------------------产品轮换图---------
    private ViewPager gallery;//商品图片
    private TextView tvtip;//商品图片提示
    private GoodsDetailGalleryAdapter galleryAdapter;//适配器
    private List<String> listGallerys;//商品图片数据
//    private TextView gallery_count;//显示图片下标
    private LinearLayout indicator;//图片指示器
    private List<ImageView> indicatorList;//图片指示器的下标图片集合
    private RelativeLayout gallerylayout;//商品图片布局
    private TextView tvproname;//产品名称
    private LinearLayout btnshare;//分享按钮
    private TextView tvappraise;//评价
    private TextView tvprice;//价格
    private TextView tvactive;//活动描述
    private TextView tvtransport;//运费描述
    private TextView tvsalenum;//月销量
    private TextView tvadress;//地址
    private TextView tvcx1;//促销1
    private TextView tvcx1describ;//促销描述1
    private TextView tvcx2;//促销2
    private TextView tvcx3;//促销3
    private TextView tvcx4;//促销4
    private TextView tvcx5;//促销5
    private TextView tvcx6;//促销6
    private LinearLayout layoutcxmore;//更多促销布局
    private LinearLayout layoutcxunopen;//促销收起布局
    private MyListView lvmj;//满减列表
    private LinearLayout layoutmj;//满减布局
    private HorizontialListView lvzh;//组合列表
    private LinearLayout layoutzh;//组合布局
    private MyListView lvzs;//赠品列表
    private LinearLayout layoutzs;//赠品布局
    private MyListView lvmz;//满赠列表
    private LinearLayout layoutmz;//满赠布局
    private LinearLayout layoutcxopen;//促销展开布局
    private LinearLayout layout_stop;//收起
    private LinearLayout layoutcx;//整个促销布局
    private TextView tvsendadress;//送至地址
    private TextView tvstock;//库存
    private LinearLayout layoutadress;//送至布局
    private LinearLayout layoutreceivejuan;//领取劵布局
    private LinearLayout layoutselecteattribute;//选择商品属性
    private LinearLayout layoutproparrams;//商品参数
    private TextView tvappraiseusername;//评价人姓名
    private RatingBar ratingBar;//评价星星
    private TextView tvtime;//评价时间
    private TextView tvdescrib;//评价描述
    private android.widget.GridView gvimgs;//评价图片
    private TextView tvappraisemore;//更多评价
    private ImageView ivstore;//店铺图标
    private TextView tvstorename;//店铺名称
    private TextView tvdescribscore;//店铺描述评分
    private TextView tvservicescore;//服务评分
    private TextView tvreleasescore;//物流评分
    private TextView tvallpronum;//全部商品数量
    private TextView tvattentionnum;//店铺关注数量
    private LinearLayout layoutlistsearch;//查看宝贝分类
    private LinearLayout layout2store;//进店逛逛
    private LinearLayout layoutstore;//整个店铺布局
    private LinearLayout layout_main;//主布局
    private String provinceName;//省
    private String cityName;//市
    private String areaName;//区
    private int provinceId;//省id
    private int cityId;//市id
    private int areaId;//区id

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.layout_fragment_car_product, null);
        initAllLists();
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
        setListener();
        fillMainImageGallery();
        initView();
        addViewToIndicator();
    }

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
//		String listGallerysStr = goodsDetailBeen.goodsDetail.GOODS_IMG_LIST;
//		String gallerys[] = listGallerysStr.split(",");
//
//		if (listGallerys == null) {
//			listGallerys = new ArrayList<String>();
//		} else {
//			listGallerys.clear();
//		}
//
//		for (String string : gallerys) {
//			listGallerys.add(URLs.IMAGE_URL + string);
//		}
//		galleryAdapter
//				.setQuota(goodsDetailBeen.goodsDetail.getQUOTA_ONE() == 0);
//		galleryAdapter.notifyDataSetChanged();
        listGallerys.add("http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg");
        listGallerys.add("http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg");
        listGallerys.add("http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg");
        listGallerys.add("http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg");
        listGallerys.add("http://lehumall.b0.upaiyun.com//upload/image/admin/2015/20150824/201508242224519073.jpg");
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
        int margin = mContext.getResources().getDimensionPixelSize(R.dimen.dp_10)*2;
        ImageAdapter uploadImageAdapter=new ImageAdapter(mContext, DeviceUtils.getWidthMaxPx(mContext)-margin,5);
        gvimgs.setAdapter(uploadImageAdapter);
        gvimgs.setNumColumns(5);
        ImageData imageData=new ImageData("111","111");
        ImageData imageData1=new ImageData("111","111");
        List<ImageData> imageDatas=new ArrayList<ImageData>();
        imageDatas.add(imageData);
        imageDatas.add(imageData1);
        uploadImageAdapter.refreshData(imageDatas);
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
        		
				// 记住当前选中的省市县
        		provinceId = (null != province) ? province.RecNo : -1;
				cityId = (null != city) ? city.RecNo : -1;
				areaId = (null != area) ? area.RecNo : -1;

				// 文本赋值
				provinceName = (null != province) ? province.name : "";
				cityName = (null != city) ? city.name : "";
				areaName = (null != area) ? area.name : "";
				tvsendadress.setText(provinceName + " " + cityName + " " + areaName);
			}
        });
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
                showToast("分享");
                break;
            case R.id.layout_cx_unopen://促销展开
                expendCXLayout(true);
                showToast("促销展开");
                break;
            case R.id.layout_stop://促销收起
                expendCXLayout(false);
                showToast("促销收起");
                break;
            case R.id.layout_adress://选择省市区
                selectPosition();
                showToast("选择省市区");
                break;
            case R.id.layout_receive_juan://领取现金劵
                startActivity(new Intent(mContext, RedPackageListActivity.class));
                break;
            case R.id.layout_selecte_attribute://选择商品属性
                break;
            case R.id.layout_pro_parrams://选择商品参数
                BottomPopWindow pop=new BottomPopWindow(mContext,layout_main,"系列\tE系列\n" +
                        "型号\tE550c (20E00007CD)\n" +
                        "颜色\t金属石墨黑\n" +
                        "平台\tIntel\n" +
                        "操作系统\n" +
                        "操作系统\tWindows 8.1\n" +
                        "处理器\n" +
                        "CPU类型\t第四代智能英特尔酷睿i5处理器\n" +
                        "CPU型号\ti5-4210U\n" +
                        "CPU速度\t1.7GHz睿频至2.7GHz\n" +
                        "三级缓存\t3M\n" +
                        "核心\t双核\n" +
                        "内存\n" +
                        "内存容量\t8GB\n" +
                        "内存类型\tDDR3L 1600\n" +
                        "插槽数量\t2 x SO-DIMM\n" +
                        "最大支持容量\t16GB\n" +
                        "硬盘\n" +
                        "硬盘容量\t1T\n" +
                        "转速\t5400转/分钟\n" +
                        "接口类型\tSATA 串行");
                showToast("选择商品参数");
                break;
            case R.id.tv_appraise_more://更多评价
                showToast("更多评价");
                break;
            case R.id.layout_store://进入店铺
            case R.id.layout_2store://进入店铺
                showToast("进入店铺");
                break;
            case R.id.layout_list_search://查看宝贝分类
                showToast("查看宝贝分类");
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
}
