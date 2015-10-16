package com.baosteel.qcsh.ui.activity.home.travel;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.constants.Constants;
import com.baosteel.qcsh.model.Hotel;
import com.baosteel.qcsh.ui.adapter.HotelListAdapter;
import com.common.base.BaseActivity;

/**
 * 旅行宝-国内酒店
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class HotelSearchActivity extends BaseActivity implements OnClickListener {

	/**酒店列表**/
	private ListView dataListView;

	/**适配器**/
	private HotelListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_hotel_search);

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
		findViewById(R.id.layout_ab).setBackgroundColor(Constants.getTypeColor(Constants.TYPE_TRAVEL));
		setTitle("酒店列表");
		
		dataListView = (ListView)findViewById(R.id.dataListView);
		adapter = new HotelListAdapter(mContext);
		dataListView.setAdapter(adapter);
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		
		List<Hotel> hotLineList = new ArrayList<Hotel>();
		hotLineList.add(new Hotel(1, "上海浦东绘家商务宾馆(上海浦东国际机场分店)", R.drawable.bg_hotel1));
		hotLineList.add(new Hotel(2, "上海浦东绘家商务宾馆(上海浦东国际机场分店)", R.drawable.bg_hotel2));
		hotLineList.add(new Hotel(3, "上海浦东绘家商务宾馆(上海浦东国际机场分店)", R.drawable.bg_hotel3));
		hotLineList.add(new Hotel(4, "上海浦东绘家商务宾馆(上海浦东国际机场分店)", R.drawable.bg_hotel1));
		hotLineList.add(new Hotel(5, "上海浦东绘家商务宾馆(上海浦东国际机场分店)", R.drawable.bg_hotel2));
		hotLineList.add(new Hotel(6, "上海浦东绘家商务宾馆(上海浦东国际机场分店)", R.drawable.bg_hotel3));
		hotLineList.add(new Hotel(7, "上海浦东绘家商务宾馆(上海浦东国际机场分店)", R.drawable.bg_hotel2));
		hotLineList.add(new Hotel(0, "上海浦东绘家商务宾馆(上海浦东国际机场分店)", R.drawable.bg_hotel1));
		adapter.refreshData(hotLineList);
	}

	@Override
	public void onClick(View v) {
	}

}
