package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.bean.Product;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.utils.ImageManager;

/**
 * 团购adapter
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-9
 */
public class ClassRecomendAdapter extends BaseAdapter {

	/** 上下文 **/
	private Context mContext;

	/** 是否显示grid方式 **/
	private boolean isTable;

	/** 商品列表 **/
	private List<TopProduct> dataList;

	public ClassRecomendAdapter(Context mContext) {
		this.mContext = mContext;
		this.dataList = new ArrayList<TopProduct>();
	}

	/**
	 * 刷新数据
	 * 
	 * @param dataList
	 */
	public void refreshData(List<TopProduct> dataList) {
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}

	public void appendData(List<TopProduct> dataList) {
		this.dataList.addAll(dataList);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (isTable) {
			return null == dataList ? 0 : (dataList.size() + 1) / 2;
		}
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

	/**
	 * 是否改变了Views
	 * 
	 * @param convertView
	 * @return
	 */
	private boolean isChangeView(View convertView) {
		if (null == convertView) {
			return true;
		}

		if (null == convertView.getTag()) {
			return true;
		}

		boolean tag = Boolean.parseBoolean(convertView.getTag().toString());
		if (tag != isTable) {
			return true;
		}

		return false;
	}

	/**
	 * 改变listview的显示样式
	 * 
	 * @param isTable
	 */
	public void ChangeView(boolean isTable) {
		this.isTable = isTable;
		this.notifyDataSetChanged();
	}

	@Override
	public int getItemViewType(int position) {
		return isTable ? 1 : 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (isTable) {
			if (convertView == null || isChangeView(convertView)) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.class_sort_list1_img_mode, parent, false);
				convertView.setTag(isTable);
			}

			// 显示数据
			TopProduct product1 = dataList.get(2 * position);
			TopProduct product2=null;
			if(dataList.size()==1){
				product2 = dataList.get(0);
			}else{
				product2 = dataList.get(2 * position + 1);
			}
			showTableData(convertView, product1, product2);

		} else {
			if (convertView == null || isChangeView(convertView)) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.class_sort_list1_normal_mode, parent, false);
				convertView.setTag(isTable);
			}

			// 显示数据
			TopProduct product = dataList.get(position);
			showNormalData(convertView, product);
		}
		return convertView;
	}

	/**
	 * 显示表格数据
	 * 
	 * @param convertView
	 * @param product1
	 * @param product2
	 */
	private void showTableData(View convertView, TopProduct product1, TopProduct product2) {

		// 商品布局1，商品图片，商品名称，免运费，商品价格，购买数量
		LinearLayout product_linearlayout1 = (LinearLayout) convertView.findViewById(R.id.product_linearlayout1);
		ImageView product_imgview1 = (ImageView) convertView.findViewById(R.id.product_imgview1);
		TextView product_name_textview1 = (TextView) convertView.findViewById(R.id.product_name_textview1);
		TextView freight_textview1 = (TextView) convertView.findViewById(R.id.freight_textview1);
		TextView address_textview1 = (TextView) convertView.findViewById(R.id.address_textview1);
		TextView product_price_textview1 = (TextView) convertView.findViewById(R.id.product_price_textview1);
		TextView product_pay_count_textview1 = (TextView) convertView.findViewById(R.id.product_pay_count_textview1);
		
		//点击事件,赋值
		product_linearlayout1.setOnClickListener(new MyClick(product1));
		ImageManager.Load(product1.getImg(), product_imgview1);
		product_name_textview1.setText(product1.getName());
		freight_textview1.setText(product1.getFreight());
		address_textview1.setText(product1.getCity_name() + " " + product1.getArea_name());
		product_price_textview1.setText(product1.getPrice());
		product_pay_count_textview1.setText(product1.getSale_count()+"人付款");
		
		// 商品布局2，商品图片，商品名称，免运费，商品价格，购买数量
		LinearLayout product_linearlayout2 = (LinearLayout) convertView.findViewById(R.id.product_linearlayout2);
		ImageView product_imageview2 = (ImageView) convertView.findViewById(R.id.product_imageview2);
		TextView product_name_textview2 = (TextView) convertView.findViewById(R.id.product_name_textview2);
		TextView freight_textview2 = (TextView) convertView.findViewById(R.id.freight_textview2);
		TextView address_textview2 = (TextView) convertView.findViewById(R.id.address_textview2);
		TextView product_price_textview2 = (TextView) convertView.findViewById(R.id.product_price_textview2);
		TextView product_pay_count_textview2 = (TextView) convertView.findViewById(R.id.product_pay_count_textview2);

		//点击事件,赋值
		product_linearlayout2.setOnClickListener(new MyClick(product2));
		ImageManager.Load(product2.getImg(), product_imageview2);
		product_name_textview2.setText(product2.getName());
		freight_textview2.setText(product2.getFreight());
		address_textview2.setText(product2.getCity_name() + " " + product2.getArea_name());
		product_price_textview2.setText(product2.getPrice());
		product_pay_count_textview2.setText(product2.getSale_count()+"人付款");
	}

	/**
	 * 显示正常数据
	 * 
	 * @param convertView
	 * @param product1
	 */
	void showNormalData(View convertView, TopProduct product) {
		
		// 商品布局，商品图片，商品名称，免运费，商品价格，购买数量
		RelativeLayout product_relativelayout = (RelativeLayout) convertView.findViewById(R.id.product_relativelayout);
		ImageView product_imageview = (ImageView) convertView.findViewById(R.id.product_imageview);
		TextView product_name_textview = (TextView) convertView.findViewById(R.id.product_name_textview);
		TextView freight_textview = (TextView) convertView.findViewById(R.id.freight_textview);
		TextView address_textview = (TextView) convertView.findViewById(R.id.address_textview);
		TextView product_price_textview = (TextView) convertView.findViewById(R.id.product_price_textview);
		TextView product_pay_count_textview = (TextView) convertView.findViewById(R.id.product_pay_count_textview);
	
		//点击事件，赋值
		product_relativelayout.setOnClickListener(new MyClick(product));
		ImageManager.Load(product.getImg(), product_imageview);
		product_name_textview.setText(product.getName());
		freight_textview.setText(product.getFreight());
		address_textview.setText(product.getCity_name() + " " + product.getArea_name());
		product_price_textview.setText(product.getPrice());
		product_pay_count_textview.setText(product.getSale_count()+"人付款");
	}

	
	/**
	 * 点击回调
	 * @author 刘远祺
	 *
	 * @todo TODO
	 *
	 * @date 2015-9-9
	 */
	class MyClick implements View.OnClickListener{

		private TopProduct product;
		
		MyClick(TopProduct product){
			this.product = product;
		}
		
		@Override
		public void onClick(View v) {
			if(null != onclick){
				onclick.onProductClick(product);
			}
		}
	}
	
	/**
	 * 点击回调
	 * @author 刘远祺
	 *
	 * @todo TODO
	 *
	 * @date 2015-9-9
	 */
	public interface OnProductClick {
		public void onProductClick(TopProduct product);
	}
	private OnProductClick onclick;
	public void setOnProductClick(OnProductClick onclick){
		this.onclick = onclick;
	}
}
