package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.OrderProduct;
import com.baosteel.qcsh.ui.activity.prodcut.TongueTipProductDetailsActivity;
import com.baosteel.qcsh.utils.ImageManager;

/**
 * 
 * @description 订单列表-订单中商品的数据适配器
 * @author blue
 * @Time 2015-9-28
 *
 */
public class OrderProductsAdapter extends BaseAdapter{

	private Context mContext;
	private List<OrderProduct> mDatas;
	private LayoutInflater mInflater;
	
	public OrderProductsAdapter(Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
	}
	
	public OrderProductsAdapter(Context context, List<OrderProduct> datas) {
		this.mContext = context;
		this.mDatas = datas;
		this.mInflater = LayoutInflater.from(context);
	}
	
	public void refreshData(List<OrderProduct> datas){
		if (datas == null) {
			return;
		}
		
		if (this.mDatas == null) {
			this.mDatas = new ArrayList<OrderProduct>();
		}
		
		this.mDatas.clear();
		this.mDatas.addAll(datas);
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return null != mDatas ? mDatas.size() : 0;
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
		
		ViewHolder holder = null;;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_order_products, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		//显示数据
		holder.showData(mDatas.get(position));
		//点击商品图标跳转到商品详情页面
		holder.setOnClickListener(position);
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView picImageView;
		TextView nameTv;
		TextView priceTv;
		TextView buyCountTv;
		
		ViewHolder(View view){
			picImageView = (ImageView)view.findViewById(R.id.picImageView);
			nameTv = (TextView)view.findViewById(R.id.nameTv);
			priceTv = (TextView)view.findViewById(R.id.priceTv);
			buyCountTv = (TextView)view.findViewById(R.id.buyCountTv);
		}
		
		void showData(OrderProduct product){
			
			ImageManager.Load(product.getGoodsImg(), picImageView);
			nameTv.setText(product.getGoodsName());
			priceTv.setText(product.getGoodsPrice());
			buyCountTv.setText(product.getGoodsSum());
		}
		
		void setOnClickListener(final int position) {
			picImageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, TongueTipProductDetailsActivity.class);
					intent.putExtra(TongueTipProductDetailsActivity.GOOOS_ID, mDatas.get(position).getGoodsId());
					mContext.startActivity(intent);
				}
			});
		}
	}
}
