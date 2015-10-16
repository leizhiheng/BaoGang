package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.utils.ImageManager;

import java.util.List;


/**
 * Created by lenovo on 2015/7/22.
 */
public class TopProdectAdapter extends BaseAdapter {
    private Context context;
    private List<TopProduct> list;
    private LayoutInflater inflater;
    
    /**
     * 是否显示销量，默认显示。该值可通过
     */
    private boolean isShowSaleVolume = true;

    public TopProdectAdapter(Context context, List<TopProduct> list) {
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
    	TopProduct product = list.get(position);
        ViewHolder holder =null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.gv_base_index_item, null);
            holder.img_index_product = (ImageView) convertView.findViewById(R.id.img_index_product);
            holder.tv_index_product_name = (TextView) convertView.findViewById(R.id.tv_index_product_name);
            holder.tv_index_product_price = (TextView) convertView.findViewById(R.id.tv_index_product_price);
            holder.tv_index_product_num = (TextView) convertView.findViewById(R.id.tv_index_product_num);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ImageManager.LoadWithServer(product.getImg(), holder.img_index_product);
        holder.tv_index_product_name.setText(list.get(position).getName());
        holder.tv_index_product_price.setText(list.get(position).getPrice()+"");
        
        if (isShowSaleVolume) {
        	holder.tv_index_product_num.setVisibility(View.VISIBLE);
        	holder.tv_index_product_num.setText(list.get(position).getSale_count()+"人购买");
		} else {
			holder.tv_index_product_num.setVisibility(View.INVISIBLE);
		}
        return  convertView;
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
	public void refreshData(List<TopProduct> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}
}
