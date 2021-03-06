package com.baosteel.qcsh.ui.activity.home.tongue;

import android.os.Bundle;
import android.util.Log;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.model.homeclassify.ClassifyRank2;
import com.baosteel.qcsh.ui.fragment.commen.ProductCategoryFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.StringUtils;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/9/2.
 */
public class TongueTypeActivity extends BaseActivity {

    public static final String EXTRA_TYPE_ID              = "typeId";//分类ID
    public static final String EXTRA_TYPE_LEVEL           = "typeLevel";//分类等级  0，1，2
    public static final String EXTRA_MODE_ID              = "type";//类型(1首页 2舌尖宝)
    public static final String EXTRA_TITLE                = "type_title";//标题
    
    /**flag:点击app模块，0跳到商品列表，1跳到没有导航栏的列表，2跳到有导航栏的列表**/
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    
    /** 分类信息*/
    private ArrayList<ClassifyRank2> mCategories;
    private ProductCategoryFragment mProductCategoryFragment;
    
    private String mTypeId;
    private int mTypeLevel;
    private int mModeId;
    private String mTitle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongue_type);
        
        initData();
        initView();
        
        LoadData();

    }
    
    private void initData() {
    	
    	mTypeId = getIntent().getStringExtra(EXTRA_TYPE_ID);
    	if (StringUtils.isEmpty(mTypeId)) {
			mTypeId = "86";
		}
    	mTypeLevel = getIntent().getIntExtra(EXTRA_TYPE_LEVEL, -1);
    	mModeId = getIntent().getIntExtra(EXTRA_MODE_ID, -1);
    	mTitle = getIntent().getStringExtra(EXTRA_TITLE);
    	
    	mCategories = new ArrayList<ClassifyRank2>();
    }

    private void initView() {
        setTitle(mTitle);
        mTitleBar.setBackgroud(R.color.sjb_color);
        
        mProductCategoryFragment = new ProductCategoryFragment();
        mProductCategoryFragment.setmColorSelected(getResources().getColor(R.color.theme_color_tongue));
        getSupportFragmentManager().beginTransaction().add(R.id.container, mProductCategoryFragment).commit();
    }
    
    private void LoadData() {
        RequestClient.queryAppClassifyList(mContext, mTypeId, new RequestCallback<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "loadDataRankOthers onResponse response = " + response.toString());
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {

                	List<ClassifyRank2> rank2s = new ArrayList<ClassifyRank2>();
					rank2s = JSONParseUtils.getClassifyDataRankOther(response);
                    Log.d(TAG, "loadDataRankOthers rank2s size = " + rank2s.size());
                    mProductCategoryFragment.setData(rank2s, mTypeLevel);
                }
            }
        });
    }

}
