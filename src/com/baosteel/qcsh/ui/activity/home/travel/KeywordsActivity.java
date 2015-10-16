package com.baosteel.qcsh.ui.activity.home.travel;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.model.ProductCategory;
import com.baosteel.qcsh.ui.adapter.KeywordsAdapter;
import com.baosteel.qcsh.ui.adapter.ProductCatogeryTitleAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

/**
 * 关键字
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class KeywordsActivity extends BaseActivity implements OnItemClickListener {

	/** 标题栏 **/
	private TitleBar mTitleBar;
	
	/** 一级分类 **/
	private ListView dataListview1;

	/** 二级分类 **/
	private ListView dataListview2;

	/** 三级分类 **/
	private ListView dataListview3;

	/** 一级分类适配器 **/
	private KeywordsAdapter adapter1;

	/** 一级分类适配器 **/
	private KeywordsAdapter adapter2;

	/** 二级分类适配器 **/
	private KeywordsAdapter adapter3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_keywords);

		// 初始化控件
		initView();

		// 初始化数据
		initData();
	}

	/**
	 * 初始化数据
	 */
	public void initView() {
		
		// 标题栏
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setRightIconVisibility(0, View.GONE);
		mTitleBar.setBackgroudValue(Constants.getTypeColor(Constants.TYPE_TRAVEL));
		mTitleBar.setTitle("关键字");
		
		dataListview1 = (ListView) findViewById(R.id.dataListview1);
		dataListview2 = (ListView) findViewById(R.id.dataListview2);
		dataListview3 = (ListView) findViewById(R.id.dataListview3);

		int color = Constants.getTypeColor(Constants.TYPE_TRAVEL);
		adapter1 = new KeywordsAdapter(mContext, getData(1), color, true);
		adapter2 = new KeywordsAdapter(mContext, getData(2), color, false);
		adapter3 = new KeywordsAdapter(mContext, getData(3), color, false);

		dataListview1.setAdapter(adapter1);
		dataListview2.setAdapter(adapter2);
		dataListview3.setAdapter(adapter3);

		dataListview1.setOnItemClickListener(this);
		dataListview2.setOnItemClickListener(this);
		dataListview3.setOnItemClickListener(this);
	}

	private List<ProductCategory> getData(int i) {
		List<ProductCategory> dataList = new ArrayList<ProductCategory>();
		dataList.add(new ProductCategory("坚果" + i));
		dataList.add(new ProductCategory("肉干" + i));
		dataList.add(new ProductCategory("蜜饯果干" + i));
		dataList.add(new ProductCategory("瓜子花生" + i));
		dataList.add(new ProductCategory("坚果" + i));
		dataList.add(new ProductCategory("肉干" + i));
		dataList.add(new ProductCategory("蜜饯果干" + i));
		dataList.add(new ProductCategory("瓜子花生" + i));
		return dataList;
	}

	/**
	 * 初始化数据
	 */
	public void initData() {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
		case R.id.dataListview1:
			
			adapter1.selectItem(position);
			
			break;

		case R.id.dataListview2:

			adapter2.selectItem(position);
			
			break;
		case R.id.dataListview3:

			adapter3.selectItem(position);
			
			break;
		}
	}

}
