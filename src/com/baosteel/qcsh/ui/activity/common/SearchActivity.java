package com.baosteel.qcsh.ui.activity.common;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.dao.SearchDao;
import com.baosteel.qcsh.model.SearchItem;
import com.baosteel.qcsh.task.ILoadCallback;
import com.baosteel.qcsh.task.MyTask;
import com.baosteel.qcsh.ui.activity.prodcut.ProductListActivity;
import com.baosteel.qcsh.ui.adapter.SearchHistoryAdapter;
import com.common.base.BaseActivity;
import com.custom.vg.list.CustomListView;
import com.custom.vg.list.OnItemClickListener;

/**
 * 搜索页
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-10-10
 */
public class SearchActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "SearchActivity";
	
	/**搜索的关键字**/
	public static final String Keyword = "keyword";
	
	/**搜索输入框**/
	private EditText mEtInput;
	
	/**搜索历史**/
	private CustomListView historyListView;
	
	/**热门历史**/
	private CustomListView hotListView;
	
	/**热门搜索**/
	private SearchHistoryAdapter mHotAdapter;
	
	/**搜索历史**/
	private SearchHistoryAdapter mHistoryAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		//初始化数据
		initData();
		
		//初始化控件
		initView();
		
		//获取热门搜索关键字
		loadHotSearchItem();
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		//获取最近搜索关键字(每次跳转到该页面都重新获取)
		loadHistorySearchItem();
	}
	
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		
		mHotAdapter = new SearchHistoryAdapter(mContext);
		mHistoryAdapter = new SearchHistoryAdapter(mContext);
	}
	
	/**
	 * 初始化控件
	 */
	private void initView() {
		
		//初始化控件
		mEtInput = (EditText) findViewById(R.id.et_search_input);
		historyListView = (CustomListView) findViewById(R.id.historyListView);
		hotListView = (CustomListView) findViewById(R.id.hotListView);
		
		// 设置适配器
		historyListView.setAdapter(mHistoryAdapter);
		hotListView.setAdapter(mHotAdapter);
		
		// 点击事件
		hotListView.setOnItemClickListener(new OnItemListener(hotListView));
		historyListView.setOnItemClickListener(new OnItemListener(historyListView));
		bindClick(R.id.iv_back, this);
		bindClick(R.id.btn_clear, this);
		bindClick(R.id.btn_search, this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
			finish();
			break;
		case R.id.btn_clear:
			
			//清空历史
			SearchDao.deleteSearchItem(mContext);
			mHistoryAdapter.refreshData(null);
			showToast("清除成功");
			
			break;
		case R.id.btn_search:
			
			//搜索
			String keyword = getText(mEtInput);
			if(TextUtils.isEmpty(keyword)){
				showToast("请输入关键字");
				return;
			}
			
			//保存搜索关键字
			saveSearchKeyword(keyword);
			
			//跳转到商品列表
			startToProductList(keyword);
			
			break;

		default:
			break;
		}
	}
	
	/**
	 * 跳转到商品列表
	 */
	private void startToProductList(String keyword){
		Intent intent = new Intent(mContext, ProductListActivity.class);
		intent.putExtra(ProductListActivity.EXTRA_INTENT_FROM, ProductListActivity.INTENT_FROM_SEARCH);
		intent.putExtra(ProductListActivity.EXTRA_SEARCH_KEYWORD, keyword);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	/**
	 * 保存搜索关键字
	 */
	private void saveSearchKeyword(String keyword){
		
		//先删除老记录
		SearchDao.deleteSearchItem(mContext, keyword);
		
		//插入新记录
		SearchDao.addSearchItem(mContext, keyword);
	}
	
	/**
	 * 
	 * @Description 获取热搜关键字
	 * @Author blue
	 * @Time 2015-9-2
	 */
	private void loadHotSearchItem() {	
		
		//测试数据
		List<SearchItem> mHotSearchItems = new ArrayList<SearchItem>();
		mHotSearchItems .add(new SearchItem("润滑油"));
		mHotSearchItems.add(new SearchItem("饼干"));
		mHotSearchItems.add(new SearchItem("鲜花"));
		mHotSearchItems.add(new SearchItem("水果"));
		mHotSearchItems.add(new SearchItem("健康"));
		mHotSearchItems.add(new SearchItem("跟团游"));
		mHotSearchItems.add(new SearchItem("保险"));
		mHotAdapter.refreshData(mHotSearchItems);
	}
	
	/**
	 * 获取最近搜索关键字
	 */
	private void loadHistorySearchItem() {
		
		List<SearchItem> list = SearchDao.getSearchItemList(mContext);
		mHistoryAdapter.refreshData(list);
		
	}
	
	class OnItemListener implements com.custom.vg.list.OnItemClickListener{

		View v;
		OnItemListener(View v){
			this.v = v;
		}
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			
			//这里不能用arg0，用了就报空指针，自定义控件编写不完善导致的
			switch (v.getId()) {
			case R.id.historyListView:
				
				//搜索历史
				String keyword = mHistoryAdapter.getData(arg2).getmTitle();
				startToProductList(keyword);
				saveSearchKeyword(keyword);
				
				break;

			case R.id.hotListView:
				
				//热门搜索
				String keyword1 = mHotAdapter.getData(arg2).getmTitle();
				startToProductList(keyword1);
				saveSearchKeyword(keyword1);
				
				break;
			}
		}
	}


}
