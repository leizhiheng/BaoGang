package com.baosteel.qcsh.ui.activity.home.happyliving;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PictureData;
import com.baosteel.qcsh.model.RoomerMsgData;
import com.baosteel.qcsh.ui.activity.common.SingleSelectActivity;
import com.baosteel.qcsh.ui.adapter.RoomerMsgAdapter;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;
import com.common.view.other.ListViewInScrollView;
import com.common.view.pulltorefresh.PullToRefreshGridView;

/**
 * 
 * @description 页面：乐居宝->物业服务->物业报修
 * @author blue
 * @Time 2015-9-7
 *
 */
public class RepairReportActivity extends BaseActivity implements OnClickListener{


	private TitleBar mTitleBar;
	/**
	 * 显示住户信息的ListView
	 */
	private ListViewInScrollView mListView;
	/**
	 * 点击选择报修类型
	 */
	private View mViewRepairType;
	/**
	 * 报修类型
	 */
	private TextView mTvRepairType;
	
	/**
	 * 点击选择小区
	 */
	private View mViewChangeHE;
	/**
	 * 点击选择所在单元
	 */
	private View mViewChangePart;
	/**
	 * 点击选择房号
	 */
	private View mViewChangeRoom;
	
	private RoomerMsgAdapter mListAdapter;
	
	private List<RoomerMsgData> mListDatas;
	private List<PictureData> mGriDatas;
	private LayoutInflater mInflater;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_report);

		initData();
		initView();

		loadData();

	}

	private void initData() {
		
		mInflater = LayoutInflater.from(this);
		mListDatas = new ArrayList<RoomerMsgData>();
		mGriDatas = new ArrayList<PictureData>();
		mListAdapter = new RoomerMsgAdapter(this, mListDatas);
	}

	private void initView() {
		mTitleBar = (TitleBar) findViewById(R.id.title_bar);
		mTitleBar.setTitle("物业报修");

		mListView = (ListViewInScrollView) findViewById(R.id.listview);
		mListView.setAdapter(mListAdapter);
		
		mViewRepairType = findViewById(R.id.layout_choose_repair_type);
		mTvRepairType = (TextView) findViewById(R.id.tv_repair_type);
		mViewChangeHE = findViewById(R.id.layout_choose_xiaoqu);
		mViewChangePart = findViewById(R.id.layout_choose_depart);
		mViewChangeRoom = findViewById(R.id.layout_choose_room);
		
		
		mViewRepairType.setOnClickListener(this);
		mViewChangeHE.setOnClickListener(this);
		mViewChangePart.setOnClickListener(this);
		mViewChangeRoom.setOnClickListener(this);

	}

	private void loadData() {
		
		/**
		 * 加载GridView数据
		 */
		mGriDatas.add(new PictureData(null, R.drawable.icon_add_pic));
		
		mListDatas.add(new RoomerMsgData("所在单元", "2单元", true));
		mListDatas.add(new RoomerMsgData("所在房号", "505", true));
		mListDatas.add(new RoomerMsgData("联系人", "张三", false));
		mListDatas.add(new RoomerMsgData("联系号码", "13264577558", false));
		mListAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_choose_repair_type:
			/*
			 * 选择报修类型
			 */
			intent = new Intent(this, SingleSelectActivity.class);
			intent.putExtra(EXTRA_KEY_TITLE, "报修类型");
			startActivityForResult(intent, 0);
			break;
		case R.id.layout_choose_xiaoqu:
			/*
			 * 选择小区
			 */
			intent = new Intent(this, HousingEstateMessageActivity.class);
			intent.putExtra(HousingEstateMessageActivity.INTENT_KEY_ACTION, HousingEstateMessageActivity.INTENT_ACTION_MODIFY);
			startActivityForResult(intent, 0);
			break;
		case R.id.layout_choose_depart:
			/*
			 * 选择所在单元
			 */
			intent = new Intent(this, HouseMessageActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.layout_choose_room:
			/*
			 * 选择所在房号
			 */
			intent = new Intent(this, HouseMessageActivity.class);
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if (arg2 != null) {
			String repairType = arg2.getStringExtra(EXTRA_KEY_RESULT);
			mTvRepairType.setText(repairType);
		}
	}
	
	/**
	 * 
	 * @description 填充GridView
	 * @author blue
	 * @Time 2015-9-7
	 *
	 */
	class MyGridAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mGriDatas == null ? 0 : mGriDatas.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.d("zhiheng", "GridView get View");
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_upload_image, null);
			}
			ImageView ivBig = (ImageView) convertView.findViewById(R.id.iv_big);
			if (mGriDatas.get(position).getUrl() == null) {
			}
			ivBig.setImageResource(R.drawable.qcsh);
			return convertView;
		}
		
	}

}

