package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.CarDisplacementListAdapter;
import com.baosteel.qcsh.ui.adapter.CarProductionDateListAdapter;
import com.common.base.BaseCameraFragment;

/**
 * 设置生产日期 Created by kuangyong on 15/9/14.
 */
public class SelecteCarProductionDateFragment extends BaseCameraFragment {
	private ExpandableListView lv_car_production_date;// 生产日期列表

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_car_production_date_list, null);
		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		findView();
		setListener();
		initView();
	}

	private void findView() {
		lv_car_production_date = (ExpandableListView) findViewById(R.id.lv_car_production_date);
	}

	private void setListener() {
		lv_car_production_date
				.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent,
                                                View v, int groupPosition, int childPosition,
                                                long id) {
                        if (null != listener) {
                            listener.onItemClick(parent, v, groupPosition,
                                    childPosition, id);
                        }
                        return true;
                    }
                });
		lv_car_production_date.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
										int groupPosition, long id) {
				// TODO Auto-generated method stub
				return true;
			}
		});

	}

	private void initView() {
		CarProductionDateListAdapter adapter=new CarProductionDateListAdapter(mContext);
		lv_car_production_date.setAdapter(adapter);
		for (int i = 0; i < 3; i++) {
			lv_car_production_date.expandGroup(i);
		}
	}

	public interface OnListItemClickListener {
		void onItemClick(ExpandableListView parent, View v, int groupPosition,
						 int childPosition, long id);
	}

	/**
	 * 列表item点击事件的回调
	 */
	private OnListItemClickListener listener;

	public void setOnItemClickListener(OnListItemClickListener listener) {
		this.listener = listener;
	}

}
