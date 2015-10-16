package com.baosteel.qcsh.ui.activity.home;

import java.util.ArrayList;

import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.ui.fragment.commen.ProductCategoryFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseActivity;

/**
 * 品类推荐
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-10
 */
public class ClassRecomendActivity extends BaseActivity{
	
	private static final String TAG 						= "ClassRecomendActivity";
	
	/**标题**/
	public static final String TITLE 	= "title";
	
	/**类型ID**/
	public static final String ID 		= "id";
	
	/**分类信息**/
    private ArrayList<ProductCategory> mCategories;
    
    /**分类信息**/
    private ProductCategoryFragment mProductCategoryFragment;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tongue_type);
		
		initView();
		
		initData();
		
		loadData("");
	}

	/**
     * 
     * @Description 获取二级和三级数据
     * @Author blue
     * @Time 2015-9-25
     */
    private void loadData(String goodTypeId) {
    	RequestClient.queryAppClassifyList(mContext, goodTypeId, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, "loadDataRankOthers onResponse response = "+response.toString());
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
						
//					List<ClassifyRank2> rank2s = new ArrayList<ClassifyRank2>();
//					rank2s = JSONParseUtils.getClassifyDataRankOther(response);
//					mCatogeries.get(mCurListIndex).setmRank2Items(rank2s);
//					mItemAdapter.setData(rank2s);
				}
			}
		});
    }
	
	/**
	 * 初始化控件
	 */
	private void initView() {

		mProductCategoryFragment = (ProductCategoryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_product_category);
        mProductCategoryFragment.setmColorSelected(getResources().getColor(R.color.theme_color_tongue));
        mTitleBar = (TitleBar)findViewById(R.id.title_bar);
        mTitleBar.setTitle("品类推荐");
		mTitleBar.setBackgroud(R.color.index_red);
    }
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
		//获取分类ID
		//goodsTypeId = getStringExtra(ID);
	}
}
