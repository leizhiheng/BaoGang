package com.baosteel.qcsh.ui.fragment.commen;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.model.homeclassify.ClassifyRank2;
import com.baosteel.qcsh.ui.activity.home.tongue.TongueTypeActivity;
import com.baosteel.qcsh.ui.activity.prodcut.ProductListActivity;
import com.baosteel.qcsh.ui.adapter.ProductCategoryItemAdapter;
import com.baosteel.qcsh.ui.adapter.ProductCatogeryAdapter;
import com.common.base.BaseFragment;

/**
 * Description 这个Fragment封装了商品分类的UI。根据左边ListView的item,切换右边商品列表的数据
 *      使用到这个Fragment的页面有：主界面分类，汽配商场和食品列表
 * Author blue
 * Time 2015/8/31
 */
public class ProductCategoryFragment extends BaseFragment {

	private View mViewListView;
    // View menbers
    private ListView mCatogeryTitleListView;
    private GridView mCatogetyDetailGridView;
    
    private ProductCatogeryAdapter mTitleAdapter;
    private ProductCategoryItemAdapter mItemAdapter;
    
    /**ListView中item被选中时文字的颜色*/
    private int mColorSelected;

    /**分类数据*/
    private List<ClassifyRank2> mCatogeries;
    
    /**当前选择分类的下标*/
    private int mCurListIndex = 0;
    
    /**用于判断布局是否已经初始化完毕**/
    private boolean hasInitedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_product_catogery, container, false);
        return fragmentView;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	initData();
    	initView();
    }
    
    /**
     * init data
     */
    private void initData() {
    	mCatogeries = new ArrayList<ClassifyRank2>();
    	
    	mTitleAdapter = new ProductCatogeryAdapter(mContext, mCatogeries, mColorSelected);
    	mItemAdapter = new ProductCategoryItemAdapter(mContext, null);
    	
    }
    
    private void initView() {
    	mViewListView = findViewById(R.id.layout_listview);
    	mCatogeryTitleListView = (ListView) fragmentView.findViewById(R.id.listview_product_catogery_title);
        mCatogetyDetailGridView = (GridView) fragmentView.findViewById(R.id.gridview_product_catogery_detail);
        mCatogeryTitleListView.setAdapter(mTitleAdapter);
        mCatogetyDetailGridView.setAdapter(mItemAdapter);
        
        mCatogeryTitleListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				//if (position != mCurListIndex) {
					mCatogeries.get(mCurListIndex).setSelected(false);
					mCatogeries.get(position).setSelected(true);
					mTitleAdapter.notifyDataSetChanged();
					mCurListIndex = position;
					
					Log.d(TAG, "mCatogeries.get(position) size = "+mCatogeries.get(position).getmRank3Items().size());

					mItemAdapter.setData(mCatogeries.get(position).getmRank3Items());
				//}
			}
		});

		mCatogetyDetailGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(mContext, ProductListActivity.class);
				intent.putExtra(ProductListActivity.EXTRA_INTENT_FROM, ProductListActivity.INTENT_FROM_CLASSIFY);
				intent.putExtra(ProductListActivity.EXTRA_SEARCH_KEYWORD, mItemAdapter.getData().get(position).getName());
				intent.putExtra(ProductListActivity.EXTRA_PRODUCT_TYPE_ID, mItemAdapter.getData().get(position).getId());
				startActivity(intent);
			}
		});
    	
    	//界面初始化完毕
    	hasInitedView = true;
    }
    
    /**
     * 
     * @Description 设置分类数据
     * @param 显示的数据
     * @param 数据的等级
     * @Author blue
     * @Time 2015-10-13
     */
    public void setData(List<ClassifyRank2> categories, int typeLevel) {
    	
    	if (categories.size() == 0) {
			showToast("暂无数据！");
			return;
		}
    	
    	mCatogeries.addAll(categories);
    	
    	if (hasInitedView) {
    		setView(typeLevel);
		}
    	
    }
    
    /**
     * 
     * @Description 更具Level值改变UI显示方式
     * @Author blue
     * @Time 2015-10-13
     */
    private void setView(int typeLevel) {
    	Log.d(TAG, "level = "+typeLevel);
    	if (typeLevel == TongueTypeActivity.TYPE_LEVEL_1) {
    		/*
    		 * 只有二级数据没有三级数据，则将二级数据显示在GridView中
    		 */
    		mViewListView.setVisibility(View.GONE);
    		List<ProductCategory> rank2Items = new ArrayList<ProductCategory>();
    		for (int i = 0; i < mCatogeries.size(); i++) {
				ProductCategory category = new ProductCategory(mCatogeries.get(i).getName());
				category.setId(mCatogeries.get(i).getId());
				category.setImg_url(mCatogeries.get(i).getId());
				rank2Items.add(category);
			}
    		mItemAdapter.setData(rank2Items);
			
		} else {
			/*
			 * 有二级数据和三级数据
			 */
			mCatogeries.get(0).setSelected(true);
	    	mItemAdapter.setData(mCatogeries.get(0).getmRank3Items());
	    	mTitleAdapter.notifyDataSetChanged();
	    	mItemAdapter.notifyDataSetChanged();
		}
    }
    
    /**
     * 设置选中时文字的颜色
     * @Description TODO
     * @Author blue
     * @Time 2015-9-2
     */
    public void setmColorSelected(int mColorSelected) {
		this.mColorSelected = mColorSelected;
		if (mTitleAdapter != null) {
			mTitleAdapter.setSeletedItemColor(mColorSelected);
		}
	}

	public interface OnListItemClickListener {
		void onItemClick(AdapterView<?> parent, View view,int position, long id);
	}

	/**
	 * 列表item点击事件的回调
	 */
	private OnListItemClickListener listener;

	public void setOnItemClickListener(OnListItemClickListener listener) {
		this.listener = listener;
	}
}
