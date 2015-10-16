package com.common.view.topbar;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;

public class MyListAdapter extends BaseAdapter {

	private List<String> mItems;

	private Context context;

	public MyListAdapter(Context context, List<String> items) {
		this.context = context;
		this.mItems = items;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	private class ViewHolder { // item的4个控件
		public TextView text;
	}

	@Override
	public View getView(final int arg0, final View arg1, ViewGroup arg2) {
		// 获取设置好的listener
		View view = arg1;
		ViewHolder holder = null;
		if (view == null) {
			view = View.inflate(context, R.layout.myspinner_list_item, null);
			holder = new ViewHolder();
			holder.text = (TextView) view.findViewById(R.id.tv_text);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.text.setText(mItems.get(arg0));
		return view;
	}

	// 定义接口和一个为实现的方法
	public interface onItemClickListener {
		public void click(int position, View view);
	}
}
