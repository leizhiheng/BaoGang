package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.CarDisplacementListAdapter;
import com.common.base.BaseCameraFragment;

/**
 * 设置排量 Created by kuangyong on 15/9/14.
 */
public class SelecteCarDisplacementFragment extends BaseCameraFragment {
	private ExpandableListView lv_car_displacement;// 排量列表

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_car_displacement_list, null);
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
		lv_car_displacement = (ExpandableListView) findViewById(R.id.lv_car_displacement);
	}

	private void setListener() {
		lv_car_displacement
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
		lv_car_displacement.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
										int groupPosition, long id) {
				// TODO Auto-generated method stub
				return true;
			}
		});

	}

	private void initView() {
		CarDisplacementListAdapter adapter=new CarDisplacementListAdapter(mContext);
		lv_car_displacement.setAdapter(adapter);
		for (int i = 0; i < 3; i++) {
			lv_car_displacement.expandGroup(i);
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
