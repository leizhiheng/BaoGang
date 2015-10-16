package com.baosteel.qcsh.ui.fragment.product.filter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R.integer;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.database.adress.SQLOpearteImpl.Area;
import com.baosteel.qcsh.dialog.PositionSelect;
import com.baosteel.qcsh.model.FilterGridItem;
import com.baosteel.qcsh.ui.adapter.FilterGridConditionAdapter;
import com.baosteel.qcsh.ui.adapter.FilterListConditionAdapter;
import com.baosteel.qcsh.ui.fragment.product.filter.FilterProductRank2Fragment.OnFilterOkListener;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseFragment;
import com.common.view.other.GridViewInScrollView;
import com.common.view.other.ListViewInScrollView;

/**
 * 
 * @description 商品列表页面 ：商品筛选
 * @author blue
 * @Time 2015-9-23
 * 
 */
public class FilterProductFragment extends BaseFilterProductFragment implements OnClickListener{

	private static final String TAG = "FilterProductFragment";
	private TextView mBtnOk;
	private TextView mBtnCancel;
	private TextView mTvSelectPosition;//配送地址
	private CheckBox mCkHasProduct;//仅看有货
	private CheckBox mCkQiCaiShop;//七彩生活自营
	private EditText mEtPriceStart;//价格区间-最低价格
	private EditText mEtPriceEnd;//价格区间-最高价格
	private TextView mBtnClear;//清楚筛选条件
	
	private GridViewInScrollView mGridView;
	private ListViewInScrollView mListView;

	private List<FilterGridItem> mGridItems;
	private FilterGridConditionAdapter mGridConditionAdapter;

	private List<FilterGridItem> mListItems;
	private FilterListConditionAdapter mListConditionAdapter;
	
	/**
	 * 二级筛选Fragment
	 */
	private FilterProductRank2Fragment filterProductRank2Fragment;
	
	/**
	 * 点击ListView的第mListIndex进入筛选
	 */
	private int mListIndex;
	
	/**
	 * 商品类型Id
	 */
	private String goodsTypeId;
	
    private String provinceName;                                        //省
    private String cityName;                                            //市
    private String areaName;                                            //区
    private int provinceId;                                             //省id
    private int cityId;                                                 //市id
    private int areaId;                                                 //区id
    
    /**
     * 是否显示底部ListView中的筛选条件。如果从搜索页面跳转到商品列表页面则不显示，如果从首页分类页面
     * 跳转到商品列表页面则显示。
     */
    private boolean isShowListConditions;
	
	/**
	 * 
	 * @description 通过这个回调接口将，筛选结果通知到外部，“取消”或“确定”
	 * @author blue
	 * @Time 2015-9-24
	 *
	 */
	public interface onFilterFinishListener{
		void onFilterFinish(boolean isOk);
	}
	private onFilterFinishListener mFilterFinishListener;
	public void setonFilterFinishListener(onFilterFinishListener listener) {
		mFilterFinishListener = listener;
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate ==>");
		
		initData();
		
		loadGridData();//获取GridView中的筛选条件
		if (isShowListConditions) loadFilterAttrs();//获取ListView中的筛选条件
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_filter_product, null);
		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

//		initData();
		initView();

	}

	private void initData() {
		mGridItems = new ArrayList<FilterGridItem>();
		mGridConditionAdapter = new FilterGridConditionAdapter(getActivity(), mGridItems);

		mListItems = new ArrayList<FilterGridItem>();
		mListConditionAdapter = new FilterListConditionAdapter(getActivity(), mListItems);
	
		filterProductRank2Fragment = new FilterProductRank2Fragment();
		filterProductRank2Fragment.setOnFilterOkListener(new OnFilterOkListener() {
			
			@Override
			public void onFilterOk(int position, List<FilterGridItem> selectItems) {
				Log.d(TAG,"position = "+position+", selectItems size = "+selectItems.size());
				if (selectItems.size() == 1 && selectItems.get(0).getName().equals("全部")) {
					mListItems.get(mListIndex).setConditionValue(null);
				} else {
					StringBuffer conditions = new StringBuffer();
					mListItems.get(mListIndex).setmAttrValues(selectItems);//为这个筛选项设置筛选结果
					conditions.append(selectItems.get(0).getValue());
					for (int i = 1; i < selectItems.size(); i++) {
						conditions.append(","+selectItems.get(i).getValue());
					}
					mListItems.get(mListIndex).setConditionValue(conditions.toString());
				}
				mListConditionAdapter.notifyDataSetChanged();
			}

		});
	}

	private void initView() {
		
		mBtnCancel = (TextView) findViewById(R.id.btn_cancel);
		mBtnOk = (TextView) findViewById(R.id.btn_ok);
		mTvSelectPosition = (TextView) findViewById(R.id.tv_select_position);
		mCkQiCaiShop = (CheckBox) findViewById(R.id.cb_qicai_sell);
		mCkHasProduct = (CheckBox) findViewById(R.id.cb_has_product);
		mEtPriceEnd = (EditText) findViewById(R.id.et_price_end);
		mEtPriceStart = (EditText) findViewById(R.id.et_price_start);
		mBtnClear = (TextView) findViewById(R.id.tv_clear);
		mBtnCancel.setOnClickListener(this);
		mBtnOk.setOnClickListener(this);
		mTvSelectPosition.setOnClickListener(this);
		mBtnClear.setOnClickListener(this);
		
		mListView = (ListViewInScrollView) findViewById(R.id.listview);
		mGridView = (GridViewInScrollView) findViewById(R.id.gridview);
		mListView.setAdapter(mListConditionAdapter);
		mGridView.setAdapter(mGridConditionAdapter);
		
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mGridItems.get(position).setSelected(!mGridItems.get(position).isSelected());
				mGridConditionAdapter.notifyDataSetChanged();
			}
		});
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showNext(filterProductRank2Fragment,R.id.drawer_content);
				
				mListIndex = position;
				filterProductRank2Fragment.setTitle(mListItems.get(position).getName());
				filterProductRank2Fragment.setAttrId(mListItems.get(position).getId());//设置属性值Id
			}
		});
	}
	
	/**
	 * 
	 * @Description 设置是否显示LIstView中的筛选条件
	 * @Author blue
	 * @Time 2015-10-12
	 */
	public void setIsShowListConditions(boolean isShow) {
		Log.d(TAG, "setIsShowListConditions ==>");
		this.isShowListConditions = isShow;
	}
	
	/**
	 * @Description 获取GridView中筛选数据
	 * @Author blue
	 * @Time 2015-10-12
	 */
	private void loadGridData() {

		//GridView中的筛选数据
		mGridItems.add(new FilterGridItem("免运费"));
		mGridItems.add(new FilterGridItem("促销"));
		mGridItems.add(new FilterGridItem("七彩币支付"));
		mGridConditionAdapter.notifyDataSetChanged();
	}

	private void loadData() {
		Log.d(TAG, "===> loadData");

		//ListView中的筛选条件
		mListItems.add(new FilterGridItem("品牌"));
		mListItems.add(new FilterGridItem("价格"));
		mListItems.add(new FilterGridItem("产地"));
		mListItems.add(new FilterGridItem("类型"));
		mListItems.add(new FilterGridItem("包装"));
		mListItems.add(new FilterGridItem("口味"));
		mListItems.add(new FilterGridItem("选购热点"));
		mListItems.add(new FilterGridItem("大家说"));
		mListConditionAdapter.notifyDataSetChanged();
	}
	
	private void loadFilterAttrs() {
		
		/*
		 *避免多次加载 
		 */
		if (mListItems != null && mListItems.size() > 0) {
			return;
		}
		
		RequestClient.queryAppGoodsAttr(getActivity(), goodsTypeId, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(FilterProductFragment.this.getActivity(), response)) {
					mListItems.addAll(JSONParseUtils.getFilterItem(response, false));
					Log.d(TAG, "loadFilterAttrs onResponse mListItems Size = "+mListItems.size());
					mListConditionAdapter.notifyDataSetChanged();
				}
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
				//加载测试数据
//				loadData();
				Log.d(TAG, "onFinish ");
			}
		});
		
	}
	
	public void setGoodsTypeId(String typeId) {
		this.goodsTypeId = typeId;
		if (this.goodsTypeId == null) {
			goodsTypeId = "46";
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ok:
			if (mFilterFinishListener != null) {
				mFilterFinishListener.onFilterFinish(true);
			}
			break;
		case R.id.btn_cancel:
			if (mFilterFinishListener != null) {
				mFilterFinishListener.onFilterFinish(false);
			}
			break;
			
		case R.id.tv_select_position:
			
			/*
			 * 选择配送地址
			 */
			selectPosition();
			
			break;
			
		case R.id.tv_clear:
			
			/*
			 * 清除筛选条件
			 */
			clearConditions();
			
			break;

		default:
			break;
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
                mTvSelectPosition.setText(provinceName + " " + cityName + " " + areaName);

                //三级数据都不为空才查询运费
//                if (null != province && null != city && null != area) {
//                    queryGoodsFreight(provinceId, cityId, areaId);
//                }
            }
        });
    }
    
    /**
     * 
     * @Description 清楚筛选条件
     * @Author blue
     * @Time 2015-10-9
     */
    private void clearConditions() {
    	/*
    	 * 清楚配送地址
    	 */
    	mTvSelectPosition.setText("");
    	
    	/*
    	 * 重置 仅看有货
    	 */
    	mCkHasProduct.setChecked(false);
    	
    	/*
    	 * 重置 七彩生活自营
    	 */
    	mCkQiCaiShop.setChecked(false);
    	
    	/*
    	 * 清楚GridView中的筛选条件
    	 */
    	for (int i = 0; i < mGridItems.size(); i++) {
			mGridItems.get(i).setSelected(false);
		}
    	mGridConditionAdapter.notifyDataSetChanged();
    	
    	/*
    	 * 重置价格区间
    	 */
    	mEtPriceStart.setText("");
    	mEtPriceEnd.setText("");
    	mEtPriceStart.clearFocus();
    	mEtPriceEnd.clearFocus();
    	
    	
    	/*
    	 * 清除ListView中的筛选条件
    	 */
    	if (isShowListConditions) {
    		for (int i = 0; i < mListItems.size(); i++) {
    			mListItems.get(i).setConditionValue(null);
    		}
    		mListConditionAdapter.notifyDataSetChanged();
		}
    	
    }
}
