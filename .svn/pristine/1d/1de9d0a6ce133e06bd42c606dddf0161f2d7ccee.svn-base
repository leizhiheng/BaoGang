package com.baosteel.qcsh.ui.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HotCity;
import com.baosteel.qcsh.ui.view.QuickAlphabeticBar;

/**
 * 旅行宝-热门城市
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-11
 */
public class HotCityAdapter extends BaseAdapter {

	private List<HotCity> mData;
	private Context context;
	
	private int selectIndex = -1;
	private HashMap<String, Integer> alphaIndexer;// 保存每个索引在list中的位置【#-0，A-4，B-10】
	
	public HotCityAdapter(Context context, List<HotCity> data) {
		this.mData = data;
		this.context = context;
	}

	/**
	 * 该方法在设置数据后调用
	 * @param alpha
	 */
	public void setAlpha(QuickAlphabeticBar alpha) {
		this.alphaIndexer = new HashMap<String, Integer>();

		for (int i = 0; i < mData.size(); i++) {
			String name = getAlpha(mData.get(i).getSortKey());
			if (!alphaIndexer.containsKey(name)) {

				// 只记录在list中首次出现的位置
				alphaIndexer.put(name, i);
			}
		}

		alpha.setAlphaIndexer(alphaIndexer);
	}
	
	/**
	 * 获取sortKey
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		if (str == null) {
			return "#";
		}
		// 去掉字符串开头和结尾的空格,防止不必要的空格导致的错误。
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase(); // 大写输出
		} else {
			return "#";
		}
	}
	
	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
		//return 30;
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_hot_city, null);
		}
		
		TextView sortKey = (TextView) convertView.findViewById(R.id.tv_sortkey);
		TextView title = (TextView) convertView.findViewById(R.id.tv_title);
		CheckBox checkbox = (CheckBox)convertView.findViewById(R.id.checkbox);
		
		//城市拼音首字母开头
		String preKey = position > 0 ? mData.get(position-1).getSortKey() : "";
		String key = mData.get(position).getSortKey();
		int vis = preKey.equals(key) ? View.GONE : View.VISIBLE;
		sortKey.setText(mData.get(position).getSortKey().toUpperCase());
		sortKey.setVisibility(vis);
		
		//城市名称
		title.setText(mData.get(position).getName());
		
		//选中
		boolean checked = selectIndex == position;
		checkbox.setChecked(checked);
		
		return convertView;
	}

	/**
	 * 选中某一项
	 * @param index
	 */
	public void checkIndex(int index){
		this.selectIndex = index;
		this.notifyDataSetChanged();
	}
}
