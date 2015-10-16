package com.baosteel.qcsh.ui.activity.home.travel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.dialog.TopbarMenuPopwindow;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.baosteel.qcsh.ui.activity.home.travel.custom.TravelPrivateCustomActivity;
import com.baosteel.qcsh.ui.activity.home.travel.wifi.WifiLeaseActivity;
import com.baosteel.qcsh.ui.adapter.TopProdectAdapter;
import com.baosteel.qcsh.ui.view.MyGridView;
import com.common.base.BaseActivity;
import com.common.utils.DeviceUtils;
import com.common.view.banner.BannerData;
import com.common.view.banner.BannerView;
import com.common.view.banner.IOnBannerItenClick;
import com.common.view.fastlink.FastLinkData;
import com.common.view.fastlink.FastLinkView;
import com.common.view.fastlink.LnkToolsGridViewAdapter;
import com.common.view.scrolllayout.PageControlView;
import com.common.view.scrolllayout.ScrollLayout;
import com.common.view.viewflow.CircleFlowIndicator;
import com.common.view.viewflow.ViewFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jws on 2015/9/9.
 */
public class TravelActivity extends BaseActivity implements OnClickListener, IOnBannerItenClick, LnkToolsGridViewAdapter.BackItemClickListener {
    
    private View mViewSearch;
	private ImageView mModoIcon;
	private ImageView mRightIcon;
	private ImageView mBtnBack;
	
	/**
     * Banner轮播图
     **/
    private BannerView bannerView;
    /**
     * 快速导航
     **/
    private FastLinkView fastLinkView;
    /**
     * 热门榜单
     **/
    private MyGridView myGridView;

    private ImageView mIndex_sousuo_iv;
    private LinearLayout mIndex_sousuo_linlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongue);

        //初始化控件
        initView();
        
        // 加载竞拍数据
        loadData();
    }

    public void initView() {
    	
    	mRightIcon = (ImageView) findViewById(R.id.home_location_imageview);
    	mRightIcon.setOnClickListener(this);

    	mBtnBack = (ImageView) findViewById(R.id.btn_back);
    	mBtnBack.setOnClickListener(this);
    	
    	mViewSearch = findViewById(R.id.ab_search);
    	mViewSearch.setOnClickListener(this);

        //轮播图
        ViewFlow viewFlow = (ViewFlow) findViewById(R.id.mHomeViewflow);// 获得viewFlow对象
        CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.mHomeViewflowindic); // viewFlow下的indic
        bannerView = new BannerView(mContext, viewFlow, indic);
        bannerView.setOnItemClickListener(this);

        //快速导航图
        ScrollLayout mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayoutTest);
        PageControlView pageControl = (PageControlView) findViewById(R.id.pageControl);
        mIndex_sousuo_iv = (ImageView) findViewById(R.id.activity_index_sousuo_iv);
        mIndex_sousuo_iv.setImageResource(R.drawable.lvxingbao);

        mIndex_sousuo_linlayout = (LinearLayout) findViewById(R.id.index_sousuo_linlayout);
        mIndex_sousuo_linlayout.setBackgroundColor(getResources().getColor(R.color.lxb_color));

        fastLinkView = new FastLinkView(mContext, mScrollLayout, pageControl);
        fastLinkView.setOntemClickListener(this);

        myGridView = (MyGridView) findViewById(R.id.gv_top_list);

    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 加载数据
     */
    public void loadData() {

        //构造Banner假数据
        List<BannerData> dataList = new ArrayList<BannerData>();
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        dataList.add(new BannerData());
        bannerView.refreshData(dataList);

        //构造FastLink假数据
        List<FastLinkData> fastList = new ArrayList<FastLinkData>();
        fastList.add(new FastLinkData(0, "酒店", R.drawable.hotel));
        fastList.add(new FastLinkData(1, "机票", R.drawable.plane_ticket));
        fastList.add(new FastLinkData(2, "签证", R.drawable.visa));
        fastList.add(new FastLinkData(3, "保险", R.drawable.insurance));
        fastList.add(new FastLinkData(4, "国内游", R.drawable.domestic_tour));
        fastList.add(new FastLinkData(5, "出境游", R.drawable.outbound_tour));
        fastList.add(new FastLinkData(6, "游轮", R.drawable.cruise));
        fastList.add(new FastLinkData(7, "WiFi租赁", R.drawable.wifi_lease));
        fastList.add(new FastLinkData(8, "私人定制", R.drawable.private_custom));
        fastLinkView.refreshData(fastList);

        myGridView.setAdapter(new TopProdectAdapter(mContext, getData()));

    }

    private List<TopProduct> getData() {
        List<TopProduct> list = new ArrayList<TopProduct>();
        list.add(new TopProduct("宝钢上海旅行社旅游杭州一日游", R.drawable.bg_inland_8, "10.8", 157+""));
        list.add(new TopProduct("巴厘岛5日跟团游", R.drawable.bg_inland_7, "10.8", 157+""));
        list.add(new TopProduct("台湾岛7日跟团游", R.drawable.bg_inland_9, "10.8", 157+""));
        list.add(new TopProduct("巴厘岛5日跟团游", R.drawable.bg_inland_8, "10.8", 157+""));
        list.add(new TopProduct("巴厘岛5日跟团游", R.drawable.bg_inland_9, "10.8", 157+""));
        list.add(new TopProduct("台湾岛7日跟团游", R.drawable.bg_inland_8, "10.8", 157+""));
        return list;
    }

    @Override
    public void onBannerItemClick(BannerData data, int position) {
//        showToast("点击了第" + position + "项数据");
    }

    @Override
    public void onFastLinkItemClick(FastLinkData bean) {
    	
        switch (bean.getId()) {
            case 0:
            	
            	//酒店
            	startActivity(new Intent(mContext, HotelActivity.class));
            	
                break;
            case 1:
            	
            	//机票
            	startActivity(new Intent(mContext, AirTicketActivity.class));
            	
                break;
            case 2:
            	
            	//签证
            	startActivity(new Intent(mContext, VisaActivity.class));
            	
                break;
            case 3:
            	
            	//保险
            	startActivity(new Intent(mContext, InsuranceActivity.class));
            	
                break;
            case 4:
            	
            	//国内游
            	startActivity(new Intent(mContext, InlandActivity.class));
            	
                break;
            case 5:
            	
            	//出境游
            	startActivity(new Intent(mContext, OutboundActivity.class));
            	
                break;
            case 6:
            	
            	//游轮
            	startActivity(new Intent(mContext, PassengerLinerActivity.class));
            	
                break;
            case 7:
            	
            	//wifi租赁
                startActivity(new Intent(getBaseContext(), WifiLeaseActivity.class).putExtra("travelType", "Wifi租赁"));
                break;
            case 8:
            	
            	//私人订制
                startActivity(new Intent(getBaseContext(), TravelPrivateCustomActivity.class).putExtra("travelType", "私人定制"));
                
                break;
        }
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.home_location_imageview:
			TopbarMenuPopwindow popwindow = new TopbarMenuPopwindow(this);
			popwindow.showAsDropDown(mRightIcon, DeviceUtils.getWidthMaxPx(this) - popwindow.getWidth(), 0);
			break;
		case R.id.ab_search:
			Intent intent = new Intent(this, SearchActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
}
