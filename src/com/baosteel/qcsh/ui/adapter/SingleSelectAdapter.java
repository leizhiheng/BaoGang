package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.SingleSelectItemData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @description 用于SingleSelectFragment中的ListView填充数据，该ListView仿写了一个单选ListView
 * @author blue
 * @Time 2015-9-8
 *
 */
public class SingleSelectAdapter extends BaseAdapter{

	private Context mContext;
	private List<SingleSelectItemData> mDatas;
	private LayoutInflater mInflater;
	
	
	/**
	 * 右侧图标
	 */
	private int rightIconId;
	/**
	 * 是否显示右侧的图标
	 */
	private boolean isShowRightIcon = false;
	/**
	 * 文字颜色
	 */
	private int textColor = R.color.orange_red;
	
	
	
	public SingleSelectAdapter(Context context, List<SingleSelectItemData> datas) {
		this.mContext = context;
		this.mDatas = datas;
		this.mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
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
		SingleSelectItemData item = mDatas.get(position);
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_single_select, null);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.tv_item_title);
		ImageView icon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
		
		if (item.isSelected()) {
			title.setTextColor(textColor);
			icon.setVisibility(View.VISIBLE);
		} else {
			title.setTextColor(mContext.getResources().getColor(R.color.black));
			icon.setVisibility(View.INVISIBLE);
		}
		
//		if (isShowRightIcon) {
//			icon.setVisibility(View.VISIBLE);
//		} else {
//			icon.setVisibility(View.INVISIBLE);
//		}
		
		title.setText(item.getTitle());
		return convertView;
	}

	/**
	 * 
	 * @Description 设置右侧图标
	 * @Author blue
	 * @Time 2015-9-9
	 */
	public void setRightIconId(int rightIconId) {
		this.rightIconId = rightIconId;
	}

	/**
	 * 
	 * @Description 设置是否显示右侧图标
	 * @Author blue
	 * @Time 2015-9-9
	 */
	public void setShowRightIcon(boolean isShowRightIcon) {
		this.isShowRightIcon = isShowRightIcon;
	}

	/**
	 * 
	 * @Description 设置文字颜色
	 * @Author blue
	 * @Time 2015-9-9
	 */
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	
	

}
