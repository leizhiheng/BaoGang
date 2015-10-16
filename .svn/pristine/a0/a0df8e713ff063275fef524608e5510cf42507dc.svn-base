package com.baosteel.qcsh.ui.activity.prodcut;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.dialog.ProductListMultiPopwindow;
import com.baosteel.qcsh.dialog.ProductListMultiPopwindow.OnItemSelectedListener;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.baosteel.qcsh.ui.fragment.product.ProductListFragment;
import com.baosteel.qcsh.ui.fragment.product.ProductListFragment.OnDataRefreshListener;
import com.baosteel.qcsh.ui.fragment.product.filter.FilterProductFragment;
import com.baosteel.qcsh.ui.fragment.product.filter.FilterProductFragment.onFilterFinishListener;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseActivity;
import com.common.utils.LogUtil;
import com.common.view.pulltorefresh.PullToRefreshBase.Mode;

/**
 * 
 * @description 页面：商品列表 ， 首页分类中点击一个商品进入到该页面
 * @author blue
 * @Time 2015-9-22
 *
 */
public class ProductListActivity extends BaseActivity implements OnClickListener{

	private static final String TAG 						= "ProductListActivity";
	
	/**用于判断从那个页面跳转到该页面**/
	public static final String EXTRA_INTENT_FROM 			= "intent.from";
	/**从搜索页面跳转到该页面，需要传递搜索关键字**/
	public static final int INTENT_FROM_SEARCH			    = 0;
	/**从首页分类跳转到该页面，需要传递分类ID**/
	public static final int INTENT_FROM_CLASSIFY			= 1;
	
	/**从首页分类跳转到该页面时，需要将商品类型传递过来**/
	public static final String EXTRA_PRODUCT_TYPE_ID 		= "product.type.id";
	
	/**从搜索页面跳转过来，需传递搜索关键字**/
	public static final String EXTRA_SEARCH_KEYWORD 		= "keyword";
	
	/**搜索requestCode**/
	public static final int REQUEST_CODE_SEARCH  			= 1;
	
	
	/**返回按钮*/
	private ImageView mBtnBack;
	
	private ImageView mModeIcon;
	private TextView mTvSearch;
	/**右侧展示方式按钮*/
	private ImageView mRightIcon;
	
	/**综合*/
	private View mLayoutMulti;
	private TextView mTvMulti;
	private ImageView mIvMulti;
	
	/**销量*/
	private View mLayoutSaleNum;
	private TextView mTvSaleNum;
	
	/**价格*/
	private View mLayoutPrice;
	private TextView mTvPrice;
	private ImageView mIvPriceUp;
	private ImageView mIvPriceDown;
	
	/**筛选*/
	private View mLayoutFilter;
	
	/**抽屉控件用来显示筛选页面*/
	private DrawerLayout mDrawerLayout;
    private RelativeLayout drawer_content;
	
    /** 商品列表Fragment */
	private ProductListFragment productListFragment;
	
	/** 商品筛选Fragment */
	private FilterProductFragment filterProductFragment;
	
	private int listModeIcon = R.drawable.icon_right_hor;
	private int gridModeIcon = R.drawable.icon_class_in;
	
	private int colorNor = R.color.font_gray;//Tab未选中颜色
	private int colorSel = R.color.red;//Tab选中颜色
	
	private boolean isPriceUp = true;//当前价格排序是升价排序
	private boolean isGridMode = true;//当前显示模式是否为网格模式
	
	private int mCurSelectedTab = -1;//当前选中的Tab
	
	private FragmentManager mFragmentManager;

	
	/**用于判断从搜索页面跳转过来还是从首页分类跳转过来**/
	private int mIntentFrom;
	/*
	 * 请求商品列表的参数
	 */
	/**要搜索的内容**/
	private String keyword;
	/**商品类型 默认值-1表示所有所有商品*/
	private String mProductTypeId;
	/** 商品分类：0代表默认排序，1代表销量排序，2代表价格排序,3表示新品排序（非必填）；默认值0*/
	private int mOrderBy;
	/** 0代表升序，1代表降序	(非必填)，默认值0 */
	private int mOrderStyle;
	/** 起始页，默认值1 */
	private int mCurPage = 1;
	/** 每页数据量，默认值10 */
	private int mPageSize = 10;
	
	private List<TopProduct> mProducts;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list);
		
		initData();
		initView();
		
		loadData();
	}
	
	private void initData() {
		
		mIntentFrom = getIntent().getIntExtra(EXTRA_INTENT_FROM, INTENT_FROM_SEARCH);
		if (mIntentFrom == INTENT_FROM_SEARCH) {
			//获取搜索关键字
			keyword = getStringExtra(EXTRA_SEARCH_KEYWORD);
		} else if (mIntentFrom == INTENT_FROM_CLASSIFY) {
			//获取商品类型Id
			mProductTypeId = getIntent().getStringExtra(EXTRA_PRODUCT_TYPE_ID);
			//获取搜索关键字(也就是分类的名称)
			keyword = getStringExtra(EXTRA_SEARCH_KEYWORD);
		}
		
		mProducts = new ArrayList<TopProduct>();
		
		mFragmentManager = getSupportFragmentManager();

	}
	
	private void initView() {
		mBtnBack = (ImageView) findViewById(R.id.btn_back);
		mModeIcon = (ImageView) findViewById(R.id.activity_index_sousuo_iv);
		mTvSearch = (TextView) findViewById(R.id.activity_index_search_content);
		mRightIcon = (ImageView) findViewById(R.id.home_location_imageview);
		
		mBtnBack.setOnClickListener(this);
		mTvSearch.setOnClickListener(this);
		mRightIcon.setOnClickListener(this);
		mModeIcon.setImageResource(R.drawable.ab_ic_search);
		mTvSearch.setText(keyword);
		if (mIntentFrom == INTENT_FROM_CLASSIFY) {
			keyword = null;
		}
		mRightIcon.setImageResource(gridModeIcon);
		
		/*
		 * 综合
		 */
		mLayoutMulti = findViewById(R.id.tab_multi_llayout);
		mIvMulti = (ImageView) findViewById(R.id.tab_multi_imageview);
		mTvMulti = (TextView) findViewById(R.id.tab_multi_textview);
		mLayoutMulti.setOnClickListener(this);
		mLayoutMulti.setTag(0);

		/*
		 * 销量
		 */
		mLayoutSaleNum = findViewById(R.id.tab_sale_num_llayout);
		mTvSaleNum = (TextView) findViewById(R.id.tab_sale_num_textview);
		mLayoutSaleNum.setOnClickListener(this);
		mLayoutMulti.setTag(1);
		
		/*
		 * 价格
		 */
		mLayoutPrice = findViewById(R.id.tab_price_llayout);
		mIvPriceUp = (ImageView) findViewById(R.id.iv_price_up);
		mIvPriceDown = (ImageView) findViewById(R.id.iv_price_down);
		mTvPrice = (TextView) findViewById(R.id.tab_price_textview);
		mLayoutPrice.setOnClickListener(this);
		mLayoutMulti.setTag(2);


		/*
		 * 筛选
		 */
		mLayoutFilter = findViewById(R.id.tab_filter_llayout);
		initFilterLayout();

		
		productListFragment = (ProductListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_product_list);
		//productListFragment 中PullToRefreshListView和PullToRefreshGridView上拉加载
		//时调用该回调方法进行数据加载
		productListFragment.setOnRefreshListener(new OnDataRefreshListener() {
			
			@Override
			public void onRefresh() {
				loadData();
			}
		});
		
		
	}
	
	private void initFilterLayout() {
		
		mLayoutFilter.setOnClickListener(this);
		mLayoutFilter.setTag(3);
		
		/*
		 * 筛选侧滑布局，用DrawerLayout实现
		 */
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_content = (RelativeLayout) findViewById(R.id.drawer_content);
        filterProductFragment = new FilterProductFragment();
        //设置是否显示筛选界面的ListView
        filterProductFragment.setIsShowListConditions(mIntentFrom == INTENT_FROM_CLASSIFY);
        filterProductFragment.setGoodsTypeId(mProductTypeId);
        filterProductFragment.setonFilterFinishListener(new onFilterFinishListener() {
			
			@Override
			public void onFilterFinish(boolean isOk) {
				if (isOk) {//“确定”筛选
					
				} else {//“取消”筛选
					
				}
				mDrawerLayout.closeDrawers();
			}
		});
        mFragmentManager.beginTransaction().replace(R.id.drawer_content, filterProductFragment).commit();

	}
	
	/**
	 * 
	 * @Description 下载商品列表数据
	 * @Author blue
	 * @Time 2015-9-25
	 */
	private void loadData() {
		
		RequestClient.queryAppGoodsList(this, mProductTypeId, mOrderBy, mOrderStyle, mCurPage, mPageSize, keyword, new RequestCallback<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(ProductListActivity.this, response)) {
					
					mProducts = JSONParseUtils.getProductList(response);
					productListFragment.setData(mProducts);
					
					if (mProducts.size() < mPageSize) {
						productListFragment.setRefreshMode(Mode.DISABLED);
					}
					
					mCurPage++;
					
				}
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
				LogUtil.d(TAG, "request onFinish");
				if (productListFragment.getData().size() == 0) {
					showToast("暂时没有数据！");
				}
				
				mProducts.clear();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.activity_index_search_content:
			
			//取搜索关键字
			String keyword = getText(mTvSearch);
			
			//跳转搜索关键字界面
			Intent intent = new Intent(mContext, SearchActivity.class);
			intent.putExtra(SearchActivity.Keyword, keyword);
			startActivity(intent);
			
			
			break;
			
		case R.id.home_location_imageview:
			if (isGridMode) {
				mRightIcon.setImageResource(listModeIcon);
			} else {
				mRightIcon.setImageResource(gridModeIcon);
			}
			isGridMode = !isGridMode;
			productListFragment.showMode(isGridMode);
			break;
			
		case R.id.tab_multi_llayout:
			/*
			 * 综合
			 */
			clickMultiLayout();
			break;
			
		case R.id.tab_sale_num_llayout:
			/*
			 * 销量
			 */
			clickSaleNumLayout();
			
			break;
			
		case R.id.tab_price_llayout:
			/*
			 * 价格
			 */
			clickPriceLayout();
			
			break;
			
		case R.id.tab_filter_llayout:
			/*
			 * 筛选
			 */
			clickFilterLayout();
			
			break;

		default:
			break;
		}
	}
	
	/**
	 * 
	 * @Description 点击”综合“选项
	 * @Author blue
	 * @Time 2015-9-22
	 */
	private void clickMultiLayout() {
		
		mIvMulti.setImageResource(R.drawable.icon_arrow_up_gray);
		final ProductListMultiPopwindow popWindow = new ProductListMultiPopwindow(this);
		popWindow.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(int position, String title) {
				resetLastSelectedTab();
				
				if (position == 0) {
					mOrderBy = 0;//表示默认的“综合”排序
				} else if (position == 1) {
					mOrderBy = 3;//表示“新品”排序
				}
				mCurSelectedTab = 0;
				
				mTvMulti.setTextColor(getResources().getColor(colorSel));
				mTvMulti.setText(title);
				popWindow.dismiss();
				
				loadData();
			}
		});
		
		popWindow.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				//popwindow dismiss时改变箭头的方向
				mIvMulti.setImageResource(R.drawable.icon_arrow_down_gray);
			}
		});
		
		popWindow.showAsDropDown(mLayoutMulti, 0, 1);
		
	}
	
	/**
	 * 
	 * @Description 点击”销量“选项
	 * @Author blue
	 * @Time 2015-9-22
	 */
	private void clickSaleNumLayout() {
		
		resetLastSelectedTab();
		mCurSelectedTab = 1;
		mOrderBy = 1;//表示“销量”排序
		
		mTvSaleNum.setTextColor(getResources().getColor(colorSel));
		
		loadData();//重新获取数据
	}
	
	/**
	 * 
	 * @Description 点击”价格“选项
	 * @Author blue
	 * @Time 2015-9-22
	 */
	private void clickPriceLayout() {
		
		resetLastSelectedTab();
		mCurSelectedTab = 2;
		mOrderBy = 2;//表示“价格排序”
		
		if (isPriceUp) {
			
			//价格升序排列
			mIvPriceUp.setImageResource(R.drawable.sort_button_price_up);
			mIvPriceDown.setImageResource(R.drawable.sort_button_price_down_grey);
			mOrderStyle = 0;
			
		} else {
			
			//价格降序排列
			mIvPriceUp.setImageResource(R.drawable.sort_button_price);
			mIvPriceDown.setImageResource(R.drawable.sort_button_price_down);
			mOrderStyle = 1;
			
		}
		isPriceUp = !isPriceUp;
		mTvPrice.setTextColor(getResources().getColor(colorSel));
		
		loadData();//重新获取数据
	}
	
	/**
	 * 
	 * @Description 点击”筛选“选项
	 * @Author blue
	 * @Time 2015-9-22
	 */
	private void clickFilterLayout() {
		
		mDrawerLayout.openDrawer(drawer_content);
		
	}
	
	/**
	 * 
	 * @Description 重置上一个选中项
	 * @Author blue
	 * @Time 2015-9-22
	 */
	private void resetLastSelectedTab() {
		
		//如果之前选中的Tab，不是PriceTab，则价格恢复默认升序排列
		if (mCurSelectedTab != 2) isPriceUp = true;
		
		mCurPage = 1;
		
		productListFragment.clearData();//清除之前的数据
		productListFragment.setRefreshMode(Mode.PULL_FROM_START);
		/*
		 * 重新设置列表和网格数据显示位置
		 */
		if (isGridMode) {
			productListFragment.setGridSelection(0);
		} else {
			productListFragment.setListSelection(0);
		}
		
		switch (mCurSelectedTab) {
		case 0:
			mTvMulti.setTextColor(getResources().getColor(colorNor));
			mIvMulti.setImageResource(R.drawable.icon_arrow_down_gray);
			break;
		case 1:
			
			mTvSaleNum.setTextColor(getResources().getColor(colorNor));
			break;
		case 2:
			
			mTvPrice.setTextColor(getResources().getColor(colorNor));
			mIvPriceUp.setImageResource(R.drawable.sort_button_price);
			mIvPriceDown.setImageResource(R.drawable.sort_button_price_down_grey);
			break;
		case 3:
			
			break;

		default:
			break;
		}
	}
}
