package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CarItem;
import com.baosteel.qcsh.ui.activity.prodcut.TongueTipProductDetailsActivity;
import com.baosteel.qcsh.utils.ImageManager;

import java.util.List;


/**
 * 购物车-商品-适配器
 * @author 江文思
 *
 * @todo TODO
 *
 * @date 2015-10-10
 */
public class ShoppingCarItemAdapter extends BaseAdapter {
    private Context context;
    private List<CarItem> list;
    private LayoutInflater inflater;
    private int businessPosition;
    private boolean isShowCheck;

    /**选中的商品**/
    private List<String> selectShoppingIds;

    public ShoppingCarItemAdapter setList(List<CarItem> list) {
        this.list = list;
        this.notifyDataSetChanged();
        return this;
    }

    /****/
    private ChangeNumOncliListener changeNumOncliListener;
    private CheckItemOncliListener checkItemOncliListener;

    public ShoppingCarItemAdapter(Context context, List<CarItem> list, ChangeNumOncliListener changeNumOncliListener,CheckItemOncliListener checkItemOncliListener, boolean isShowCheck) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.changeNumOncliListener = changeNumOncliListener;
        this.checkItemOncliListener = checkItemOncliListener;
        this.isShowCheck = isShowCheck;
        this.list = list;
    }

    /**
     * 刷新数据
     * @param list
     */
    public void refreshData(List<CarItem> list, List<String> selectList, int businessPosition){
    	this.list = list;
    	this.selectShoppingIds = selectList;
    	this.businessPosition = businessPosition;
    	this.notifyDataSetChanged();
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_shoppingcar_product, null);
            holder.mImg_shopitem_check = (ImageView) convertView.findViewById(R.id.img_shopitem_check);
            holder.mImg_shopitem = (ImageView) convertView.findViewById(R.id.img_shopitem);
            holder.mTv_shopcar_item_name = (TextView) convertView.findViewById(R.id.tv_shopcar_item_name);
            holder.mTv_shopcar_item_price = (TextView) convertView.findViewById(R.id.tv_shopcar_item_price);
            holder.mTv_favorable = (TextView) convertView.findViewById(R.id.tv_favorable);
            holder.mTv_cut_num = (TextView) convertView.findViewById(R.id.tv_cut_num);
            holder.mEdit_change_num = (TextView) convertView.findViewById(R.id.tv_change_num);
            holder.mTv_add_num = (TextView) convertView.findViewById(R.id.tv_add_num);
            holder.mTv_price_select = (TextView) convertView.findViewById(R.id.tv_price_select);
            holder.mTv_spec = (TextView)convertView.findViewById(R.id.tv_spec);
            holder.mV_item_line = convertView.findViewById(R.id.v_item_line);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        final CarItem product = list.get(position);
        if(null != selectShoppingIds && selectShoppingIds.contains(product.getId()+"")){
        	holder.mImg_shopitem_check.setImageResource(R.drawable.test_check);
        }else{
        	holder.mImg_shopitem_check.setImageResource(R.drawable.test_no_check);
        }
        holder.mImg_shopitem_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkItemOncliListener.checkProduct(businessPosition, position);
            }
        });
        
        ImageManager.Load(list.get(position).getGoods_img(), holder.mImg_shopitem);
        holder.mImg_shopitem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*
				 * 跳转到商品详情页面
				 */
				Intent intent = new Intent(context, TongueTipProductDetailsActivity.class);
				intent.putExtra(TongueTipProductDetailsActivity.GOOOS_ID, list.get(position).getGoods_id());
				context.startActivity(intent);
				
			}
		});
        holder.mTv_shopcar_item_name.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*
				 * 跳转到商品详情页面
				 */
				Intent intent = new Intent(context, TongueTipProductDetailsActivity.class);
				intent.putExtra(TongueTipProductDetailsActivity.GOOOS_ID, list.get(position).getGoods_id());
				context.startActivity(intent);
				
			}
		});
        
        holder.mTv_shopcar_item_name.setText(list.get(position).getGoods_name());
        holder.mTv_shopcar_item_price.setText("¥"+list.get(position).getCurprice());
        holder.mEdit_change_num.setText((list.get(position).getNum() + ""));
        holder.mTv_spec.setText(list.get(position).getSpec());
        holder.mTv_add_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNumOncliListener.addNum(product);
            }
        });
        holder.mTv_cut_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNumOncliListener.cutNum(product);
            }
        });
        if(!isShowCheck){
            holder.mImg_shopitem_check.setVisibility(View.GONE);
        }
        
        //最后一条数据，不显示线
        int vis = (position == (getCount()-1)) ? View.INVISIBLE : View.VISIBLE;
        holder.mV_item_line.setVisibility(vis);
        
        return convertView;
    }

    public final class ViewHolder {
        public ImageView mImg_shopitem_check;
        public ImageView mImg_shopitem;
        public TextView mTv_shopcar_item_name;
        public TextView mTv_shopcar_item_price;
        public TextView mTv_favorable;
        public TextView mTv_cut_num;
        public TextView mEdit_change_num;
        public TextView mTv_add_num;
        public TextView mTv_price_select;
        public TextView mTv_spec;
        public View mV_item_line;
    }

    public interface ChangeNumOncliListener {
        void addNum(CarItem product);
        void cutNum(CarItem product);
    }

    public interface CheckItemOncliListener{
        void checkProduct(int businessPosition,int position);
        void checkBusiness(int position);
    }


}
