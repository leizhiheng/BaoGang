package com.baosteel.qcsh.ui.fragment.classify;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.R.integer;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.model.homeclassify.ClassifyRank1;
import com.baosteel.qcsh.model.homeclassify.ClassifyRank2;
import com.baosteel.qcsh.ui.activity.common.SearchActivity;
import com.baosteel.qcsh.ui.activity.prodcut.ProductListActivity;
import com.baosteel.qcsh.ui.adapter.ClassifyProductCategoryAdapter;
import com.baosteel.qcsh.ui.adapter.HomeClassifyTitleAdapter;
import com.baosteel.qcsh.ui.fragment.commen.ProductCategoryFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseFragment;
import com.common.utils.LogUtil;

/**
 * 分类
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-27
 */
public class ClassifyFragment extends BaseFragment {
	private static final String TAG = "ClassFragment";
	
	
	/**
	 * 搜索框
	 */
	private View mLoayoutSearch;
	
	 // View menbers
    private ListView mCatogeryTitleListView;
    private ListView mCatogetyDetailListView;
    
    private HomeClassifyTitleAdapter mTitleAdapter;
    private ClassifyProductCategoryAdapter mItemAdapter;

    /**分类数据*/
    private List<ClassifyRank1> mCatogeries;
    
    /**当前选择分类的下标*/
    private int mCurListIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	Log.d("zhiheng", "onCreateView");
    	fragmentView = inflater.inflate(R.layout.fragment_classify_product_category, container, false);
        return fragmentView;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	Log.d("zhiheng", "onActivityCreated");
    	initData();
    	initView();
    	
//    	loadData();
//    	loadDataRank1();
    }
    
    @Override
    public void onResume() {
    	Log.d("zhiheng", "onResume");
    	super.onResume();
    	if (mCatogeries == null || mCatogeries.size() == 0) {
			loadDataRank1();
		}
    }
    
    private void initView() {
    	
    	
		findViewById(R.id.btn_back).setVisibility(View.GONE);
		findViewById(R.id.activity_index_sousuo_iv).setVisibility(View.GONE);
		
		mLoayoutSearch = findViewById(R.id.ab_search);
		mLoayoutSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ClassifyFragment.this.getActivity(),SearchActivity.class));
			}
		});
		
    	mCatogeryTitleListView = (ListView) fragmentView.findViewById(R.id.listview_product_catogery_title);
        mCatogetyDetailListView = (ListView) fragmentView.findViewById(R.id.listview_product_catogery_detail);
        mCatogeryTitleListView.setAdapter(mTitleAdapter);
        mCatogetyDetailListView.setAdapter(mItemAdapter);
        
        mCatogeryTitleListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//if (position != mCurListIndex) {
					
					/*
					 * 如果该一级分类的二级分类不为null,表示易下载过二级数据；如果为null，表示没有下载过二级数据，则进行下载
					 */
					if (mCatogeries.get(position).getmRank2Items() != null) {
						
						if ( mCatogeries.get(position).getmRank2Items().size() == 0) {
							/*
							 * 如果二级数据个数为零，则直接跳转到商品列表页面，并传入该一级分类的ID
							 */
							Intent intent = new Intent(mContext, ProductListActivity.class);
							intent.putExtra(ProductListActivity.EXTRA_INTENT_FROM, ProductListActivity.INTENT_FROM_CLASSIFY);
							intent.putExtra(ProductListActivity.EXTRA_SEARCH_KEYWORD, mCatogeries.get(position).getName());
							intent.putExtra(ProductListActivity.EXTRA_PRODUCT_TYPE_ID, mCatogeries.get(position).getId());
							mContext.startActivity(intent);
						} else {
							/*
							 * 如果有二级数据，则显示二、三分类数据
							 */
							refreshView(position);
						}
						
					} else {
						
						//下载二、三级分类数据
						loadDataRankOthers(position, mCatogeries.get(position).getId());
						
					}
				}
			//}
		});
    }
    
    /**
     * 
     * @Description 当二级数据个数大于0时刷新列表
     * @Author blue
     * @Time 2015-10-13
     */
    private void refreshView(int position) {
		/*
		 * 刷新一级分类列表
		 */
		mCatogeries.get(mCurListIndex).setSelected(false);
		mCatogeries.get(position).setSelected(true);
		mTitleAdapter.notifyDataSetChanged();
		
		//加载已下载的数据
		mItemAdapter.setData(mCatogeries.get(position).getmRank2Items());
		mCurListIndex = position;
    }
    
    /**
     * init data
     */
    private void initData() {
    	
    	mCatogeries = new ArrayList<ClassifyRank1>();
    	mTitleAdapter = new HomeClassifyTitleAdapter(mContext, mCatogeries, R.color.index_red);
    	mItemAdapter = new ClassifyProductCategoryAdapter(mContext, null);
    	
    }
    
    /**
     * @Description 添加分类数据
     * @Author blue
     * @Time 下午2:32:33
     */
    private void loadData() {
    	/*
    	 * 以及分类数据
    	 */
//    	mCatogeries.add(new ClassifyRank1("坚果"));
//    	mCatogeries.add(new ClassifyRank1("肉干"));
//    	mCatogeries.add(new ClassifyRank1("蜜饯果干"));
//    	mCatogeries.add(new ClassifyRank1("瓜子花生"));
//    	mCatogeries.add(new ClassifyRank1("坚果"));
//    	mCatogeries.add(new ClassifyRank1("肉干"));
//    	mCatogeries.add(new ClassifyRank1("蜜饯果干"));
//    	mCatogeries.add(new ClassifyRank1("瓜子花生"));
    	
    	/*
    	 * 二级分类数据
    	 */
    	List<ClassifyRank2> classifyProductCategoryItems = new ArrayList<ClassifyRank2>();
    	for (int i = 0; i < 3; i++) {
    		ClassifyRank2 item = new ClassifyRank2("烘焙食品");
    		ArrayList<ProductCategory> items = new ArrayList<ProductCategory>();
        	items.add(new ProductCategory("烘焙饼干"));
        	items.add(new ProductCategory("水果蛋糕"));
        	items.add(new ProductCategory("芝士蛋糕"));
        	items.add(new ProductCategory("烘焙饼干"));
        	items.add(new ProductCategory("烘焙饼干"));
        	items.add(new ProductCategory("烘焙饼干"));
        	
        	item.setmRank3Items(items);
        	
        	classifyProductCategoryItems.add(item);
    	}
    	
    	/*
    	 * 以及分类数据和二级分类数据关联
    	 */
    	for (int i = 0; i < mCatogeries.size(); i++) {
			((ClassifyRank1) mCatogeries.get(i)).setmRank2Items(classifyProductCategoryItems);
		}
    	
    	/*
    	 * 选中第一个分类
    	 */
    	mCatogeries.get(0).setSelected(true);
    	mItemAdapter.setData((mCatogeries.get(0)).getmRank2Items());
    
    	mTitleAdapter.notifyDataSetChanged();
    	mItemAdapter.notifyDataSetChanged();
    }
    
    /**
     * 
     * @Description 获取一级分类数据
     * @Author blue
     * @Time 2015-9-25
     */
    private void loadDataRank1() {
    	RequestClient.queryAppClassifyList(this.getActivity(), -1+"", new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, "loadDataRank1 onResponse response = "+response.toString());
				if (JSONParseUtils.isSuccessRequest(ClassifyFragment.this.getActivity(), response)) {
					
					mCatogeries = JSONParseUtils.getClassifyDataRank1(response);
					if(null != mCatogeries && mCatogeries.size() > 0){
						
						mCatogeries.get(0).setSelected(true);
						mTitleAdapter.setData(mCatogeries);//重新设置数据，并刷新列表
						loadDataRankOthers(0, mCatogeries.get(0).getId());
					}
				}
			}
		});
    }
    
    /**
     * 
     * @Description 获取二级和三级数据
     * @Author blue
     * @Time 2015-9-25
     */
    private void loadDataRankOthers(final int position, String goodTypeId) {
    	RequestClient.queryAppClassifyList(this.getActivity(), goodTypeId, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(ClassifyFragment.this.getActivity(), response)) {
						
					List<ClassifyRank2> rank2s = new ArrayList<ClassifyRank2>();
					rank2s = JSONParseUtils.getClassifyDataRankOther(response);
					
					/*
					 * 如果该二级分类下面没有数据则跳转至商品分类
					 */
					if (rank2s.size() == 0) {
						/*
						 * 如果下载的二级数据为空，则跳转到商品列表页面，此时二级数据对象依然为null，之后会再次下载这个二级数据
						 */
						Intent intent = new Intent(mContext, ProductListActivity.class);
						intent.putExtra(ProductListActivity.EXTRA_INTENT_FROM, ProductListActivity.INTENT_FROM_CLASSIFY);
						intent.putExtra(ProductListActivity.EXTRA_SEARCH_KEYWORD, mCatogeries.get(position).getName());
						intent.putExtra(ProductListActivity.EXTRA_PRODUCT_TYPE_ID, mCatogeries.get(position).getId());
						mContext.startActivity(intent);
					} else {
						/*
						 * 如果下载的二级数据不为空，则将其存储到对应的一级分类对象中(也可将这两句放到外面，则二级数据为空的时候不会再次下载)
						 */
						mCatogeries.get(position).setmRank2Items(rank2s);
						refreshView(position);
					}
				}
			}
		});
    }
    
}
