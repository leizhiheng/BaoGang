package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.store.QueryAppStoreNewListBean;
import com.baosteel.qcsh.utils.ImageManager;

import java.util.List;


/**
 * 店铺商品列表适配器
 * Created by kuangyong on 2015/7/29.
 */
public class StoreProductAdapter extends BaseAdapter {
    private Context context;
    private List<QueryAppStoreNewListBean.ReturnMapEntity> list;//店铺商品列表数据
    private LayoutInflater inflater;

    /**
     * 是否显示销量，默认显示。该值可通过
     */
    private boolean isShowSaleVolume = true;

    public StoreProductAdapter(Context context, List<QueryAppStoreNewListBean.ReturnMapEntity> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return null != list ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
        QueryAppStoreNewListBean.ReturnMapEntity product = list.get(position);
        ViewHolder holder =null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_store_pro, null);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.buy_count = (TextView) convertView.findViewById(R.id.buy_count);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ImageManager.LoadWithServer(product.getImg(), holder.image);
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice()+"");
        
//        if (isShowSaleVolume) {
//        	holder.tv_index_product_num.setVisibility(View.VISIBLE);
//        	holder.tv_index_product_num.setText(list.get(position).getSale_count()+"人购买");
//		} else {
        holder.buy_count.setVisibility(View.INVISIBLE);
//		}
        return  convertView;
    }

    public final class ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView price;
        public TextView buy_count;
    }

    
    /**
     * 刷新数据
     * @param list
     */
	public void refreshData(List<QueryAppStoreNewListBean.ReturnMapEntity> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}

    public void appendData(List<QueryAppStoreNewListBean.ReturnMapEntity> dataList) {
        this.list.addAll(dataList);
        this.notifyDataSetChanged();
    }
}
