package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Hotel;
import com.baosteel.qcsh.model.ShipLine;

/**
 * 邮轮adapter
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-9
 */
public class HotelListAdapter extends BaseAdapter {

	/** 上下文 **/
	private Context mContext;

	/** 商品列表 **/
	private List<Hotel> dataList;

	public HotelListAdapter(Context mContext) {
		this.mContext = mContext;
		this.dataList = new ArrayList<Hotel>();
	}

	/**
	 * 刷新数据
	 * 
	 * @param dataList
	 */
	public void refreshData(List<Hotel> dataList) {
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}

	public void appendData(List<Hotel> dataList) {
		this.dataList.addAll(dataList);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return null == dataList ? 0 : dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_travel_hotel, parent, false);
		}
		
		// 显示数据
		Hotel product = dataList.get(position);
		showNormalData(convertView, product);
		return convertView;
	}


	/**
	 * 显示正常数据
	 * 
	 * @param convertView
	 * @param product1
	 */
	void showNormalData(View convertView, Hotel product) {
		
		// 商品布局，商品图片，商品名称，免运费，商品价格，购买数量
		RelativeLayout product_relativelayout = (RelativeLayout) convertView.findViewById(R.id.product_relativelayout);
		ImageView product_imageview = (ImageView) convertView.findViewById(R.id.product_imageview);
		TextView product_name_textview = (TextView) convertView.findViewById(R.id.product_name_textview);
		TextView product_price_textview = (TextView) convertView.findViewById(R.id.product_price_textview);
		TextView product_pay_count_textview = (TextView) convertView.findViewById(R.id.product_pay_count_textview);
	
		product_imageview.setImageResource(product.imgId);
		product_name_textview.setText(product.getName());
	}
}
