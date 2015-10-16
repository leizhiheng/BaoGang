package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.ui.adapter.TopProdectAdapter.ViewHolder;
import com.baosteel.qcsh.utils.ImageManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductListAdapter extends BaseAdapter{

	private Context mContext;
	private List<TopProduct> mDatas;
	private LayoutInflater mInflater;
	
	/**是否显示销量**/
	private boolean isShowSaleVolume = true;
	
	public ProductListAdapter(Context context, List<TopProduct> datas) {
		this.mContext = context;
		this.mDatas = datas;
		this.mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	/**
     * 
     * @Description 设置是否显示销量
     * @Author blue
     * @Time 2015-9-6
     */
    public void setIsShowSaleVolume(boolean isShow) {
    	isShowSaleVolume = isShow;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TopProduct product = mDatas.get(position);
        ViewHolder holder =null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_product_list, null);
            holder.img_index_product = (ImageView) convertView.findViewById(R.id.iv_product_pic);
            holder.tv_index_product_name = (TextView) convertView.findViewById(R.id.tv_product_name);
            holder.tv_index_product_price = (TextView) convertView.findViewById(R.id.tv_product_price);
            holder.tv_index_product_num = (TextView) convertView.findViewById(R.id.tv_sale_num);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ImageManager.LoadWithServer(product.getImg(), holder.img_index_product);
        holder.tv_index_product_name.setText(mDatas.get(position).getName());
        holder.tv_index_product_price.setText(mDatas.get(position).getPrice()+"");
        
        if (isShowSaleVolume) {
        	holder.tv_index_product_num.setVisibility(View.VISIBLE);
        	holder.tv_index_product_num.setText(mDatas.get(position).getSale_count()+"人购买");
		} else {
			holder.tv_index_product_num.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}
	
	 public final class ViewHolder{
	        public ImageView img_index_product;
	        public TextView tv_index_product_name;
	        public TextView tv_index_product_price;
	        public TextView tv_index_product_num;
	    }

	    
    /**
     * 刷新数据
     * @param list
     */
	public void refreshData(List<TopProduct> datas) {
		this.mDatas = datas;
		this.notifyDataSetChanged();
	}

}
