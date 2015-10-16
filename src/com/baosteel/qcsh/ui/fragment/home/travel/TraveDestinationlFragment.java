package com.baosteel.qcsh.ui.fragment.home.travel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HotCity;
import com.baosteel.qcsh.model.SearchItem;
import com.baosteel.qcsh.ui.adapter.HotAddressAdapter;
import com.baosteel.qcsh.ui.adapter.HotCityAdapter;
import com.baosteel.qcsh.ui.view.QuickAlphabeticBar;
import com.common.base.BaseFragment;

/**
 * 目的地
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class TraveDestinationlFragment extends BaseFragment implements OnItemClickListener, TextWatcher {

	/**搜索框**/
	private EditText searchEt;
	
	/**右边字母**/
	private QuickAlphabeticBar zimuBar;
	
	/**数据ListView**/
	private ListView dataListview;

	/**历史城市适配器**/
	private HotAddressAdapter historyAddress;
	
	/**热门城市适配器**/
	private HotAddressAdapter hotAdapter;
	
	/**城市适配器**/
	private HotCityAdapter cityAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_travel_destination, null);
		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//初始化数据
		initData();
	}
	
	@Override
	public void bindView() {
		searchEt = (EditText) findViewById(R.id.searchEt);
		searchEt.addTextChangedListener(this);
		
		dataListview = (ListView) findViewById(R.id.dataListview);
		dataListview.setOnItemClickListener(this);
		
		
		TextView zimu = (TextView)findViewById(R.id.zimu_position);
		zimuBar = (QuickAlphabeticBar)findViewById(R.id.zimu);
		zimuBar.init(mContext, zimu);
		zimuBar.setListView(dataListview);
		zimuBar.setHight(zimuBar.getHeight());
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(){
		
		//设置HeadView
		View headView = LayoutInflater.from(mContext).inflate(R.layout.adapter_head_travel_destination, null);
		GridView historyGv = (GridView)headView.findViewById(R.id.historyGridview);
		GridView hotGridview = (GridView)headView.findViewById(R.id.hotGridview);
		
		historyAddress = new HotAddressAdapter(mContext, getData());
		historyGv.setAdapter(historyAddress);
		
		hotAdapter = new HotAddressAdapter(mContext, getData());
		hotGridview.setAdapter(hotAdapter);
		
		//设置城市
		List<HotCity> cityList = getCitys();
		cityAdapter = new HotCityAdapter(mContext, cityList);
		dataListview.addHeaderView(headView);
		dataListview.setAdapter(cityAdapter);
		
		//设置右边字母的滑动索引数据
		List<String> zimuList = getSortKeys(cityList);
		zimuBar.setAlpha(zimuList);
	}

	/**
	 * 获取字母集合
	 * @param cityList
	 * @return
	 */
	private List<String> getSortKeys(List<HotCity> cityList){
		List<String> keys = new ArrayList<String>();
		if(null != cityList){
			for(int i=0; i<cityList.size(); i++){
				keys.add(cityList.get(i).getSortKey());
			}
		}
		return keys;
	}
	
	private List<SearchItem> getData(){
		List<SearchItem> list = new ArrayList<SearchItem>();
		list.add(new SearchItem("日本"));
		list.add(new SearchItem("法国"));
		list.add(new SearchItem("澳大利亚"));
		list.add(new SearchItem("美国"));
		list.add(new SearchItem("瑞士"));
		list.add(new SearchItem("荷兰"));
		list.add(new SearchItem("英国"));
		list.add(new SearchItem("德国"));
		list.add(new SearchItem("中国"));
		list.add(new SearchItem("朝鲜"));
		list.add(new SearchItem("加拿大"));
		
		return list;
	}
	
	private List<HotCity> getCitys(){
		List<HotCity> dataList = new ArrayList<HotCity>();
		dataList.add(new HotCity("1", "阿富汗", "A"));
		dataList.add(new HotCity("1", "吧富汗", "B"));
		dataList.add(new HotCity("1", "猜富汗", "C"));
		dataList.add(new HotCity("1", "的富汗", "D"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "额富汗", "E"));
		dataList.add(new HotCity("1", "发富汗", "F"));
		dataList.add(new HotCity("1", "发富汗", "F"));
		dataList.add(new HotCity("1", "个富汗", "G"));
		dataList.add(new HotCity("1", "人富汗", "R"));
		dataList.add(new HotCity("1", "在富汗", "Z"));
		dataList.add(new HotCity("1", "想富汗", "X"));
		
		return dataList;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		HotCity city = (HotCity)cityAdapter.getItem(position);
		Intent intent = new Intent();
		intent.putExtra("name", city.getName());
		intent.putExtra("id", city.getId());
		
		mContext.setResult(Activity.RESULT_OK, intent);
		mContext.finish();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		
	}
}
