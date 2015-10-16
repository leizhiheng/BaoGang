package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.DeliverGoods;
import com.baosteel.qcsh.model.DeliverMethod;
import com.common.base.BaseActivity;
import com.common.utils.DialogUtil;
import com.common.view.listview.HorizontialListView;


/**
 * Created by jws on 2015/7/22.
 */
public class DeliveryMethodAdapter extends BaseAdapter {
    private Context mContext;
    private List<DeliverMethod> list;
    private LayoutInflater inflater;
    private ItemOnclickListener itemOnclickListener;

    public DeliveryMethodAdapter(Context mContext, List<DeliverMethod> list, ItemOnclickListener itemOnclickListener) {
        super();
        this.inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.list = list;
        this.itemOnclickListener = itemOnclickListener;
    }

    /**
     * 刷新数据
     * @param list
     * @return
     */
    public void refreshData(List<DeliverMethod> list) {
        this.list = list;
        
        this.notifyDataSetChanged();
    }

    public List<DeliverMethod> getList() {
        return list;
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
        	convertView = inflater.inflate(R.layout.item_delivery_method, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        //显示与隐藏 送货上门，上门自提
        findMethodByType(holder, position);
        
        
        if(list.get(position).getType()==1){
            holder.mTv_delivery_gohome.setBackgroundResource(R.drawable.delivery_select);
            holder.mTv_delivery_gohome.setTextColor(mContext.getResources().getColor(R.color.g_orage_color));
            holder.mTv_gohome_goget.setBackgroundResource(R.drawable.delivery_noselect);
            holder.mTv_gohome_goget.setTextColor(mContext.getResources().getColor(R.color.gr_l_color));
        }else if(list.get(position).getType()==2){
            holder.mTv_delivery_gohome.setBackgroundResource(R.drawable.delivery_noselect);
            holder.mTv_delivery_gohome.setTextColor(mContext.getResources().getColor(R.color.gr_l_color));
            holder.mTv_gohome_goget.setBackgroundResource(R.drawable.delivery_select);
            holder.mTv_gohome_goget.setTextColor(mContext.getResources().getColor(R.color.g_orage_color));
            holder.mTv_gohome_goget.setVisibility(View.VISIBLE);
            holder.mLin_tips.setVisibility(View.VISIBLE);
            if(list.get(position).getAddress()==null){
                Log.i("jws","自提点暂无数据，予以隐藏");
                holder.mTv_gohome_goget.setVisibility(View.GONE);
            }
        }
        
        //刷新商品图片
        holder.adapter.refreshData(list.get(position).getGoodsImg());
        
        //运费  地址 自提时间  手机
        holder.mTv_fare.setText("商品运费" + list.get(position).getShipment() + "元");
        holder.mTv_address.setText(list.get(position).getDetailAddress());
        holder.mTv_date.setText(list.get(position).getAddress().getServe_time());
        holder.mTv_phone.setText(list.get(position).getAddress().getContact_tel());
        
        //自提点
        holder.mTv_mentionpoint.setText(list.get(position).getAddress().getServe_name());
        
        
        holder.mRel_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //地址选择
                itemOnclickListener.getAddress(position);
            }
        });
        
        
        holder.mRel_date.setTag(position);
        holder.mRel_date.setOnClickListener(holder);
        
        holder.mRel_mentionpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
            	//选择自提点
                itemOnclickListener.getPoint(position,view);
            }
        });
        holder.mRel_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
            	//点击手机号码
                itemOnclickListener.getPhone(position);
            }
        });
        holder.mTv_delivery_gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
            	//送货上门
                itemOnclickListener.clickDelivery(position);
            }
        });
        holder.mTv_gohome_goget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnclickListener.clickMention(position);
            }
        });
        
        
        
        
        return convertView;
    }

    /**
     * 根据类型，显示与隐藏  送货上门，上门自提
     * @param holder
     * @param position
     */
    private void findMethodByType(ViewHolder holder,int position){
        if (list.get(position).getInvoiceType().equals("1,2")) {
            holder.mTv_delivery_gohome.setBackgroundResource(R.drawable.delivery_select);
            holder.mTv_delivery_gohome.setTextColor(mContext.getResources().getColor(R.color.g_orage_color));
            holder.mTv_gohome_goget.setBackgroundResource(R.drawable.delivery_noselect);
            holder.mTv_gohome_goget.setTextColor(mContext.getResources().getColor(R.color.gr_l_color));
            holder.mTv_delivery_gohome.setVisibility(View.VISIBLE);
            holder.mTv_gohome_goget.setVisibility(View.VISIBLE);
            holder.mLin_tips.setVisibility(View.GONE);
            if(list.get(position).getAddress().getId()==0){
                Log.i("jws","自提点暂无数据，予以隐藏");
                holder.mTv_gohome_goget.setVisibility(View.GONE);
            }
        } else if(list.get(position).getInvoiceType().equals("1")) {
            holder.mTv_delivery_gohome.setBackgroundResource(R.drawable.delivery_select);
            holder.mTv_delivery_gohome.setTextColor(mContext.getResources().getColor(R.color.g_orage_color));
            holder.mTv_delivery_gohome.setVisibility(View.VISIBLE);
            holder.mTv_gohome_goget.setVisibility(View.GONE);
            holder.mLin_tips.setVisibility(View.GONE);
            list.get(position).setType(3);
        }else if(list.get(position).getInvoiceType().equals("2")){
            holder.mTv_gohome_goget.setBackgroundResource(R.drawable.delivery_select);
            holder.mTv_gohome_goget.setTextColor(mContext.getResources().getColor(R.color.g_orage_color));
            holder.mTv_delivery_gohome.setVisibility(View.GONE);
            holder.mTv_gohome_goget.setVisibility(View.VISIBLE);
            holder.mLin_tips.setVisibility(View.VISIBLE);
            if(list.get(position).getAddress().getId()==0){
                Toast.makeText(mContext, "该商品没有自提点信息，请后台更新", Toast.LENGTH_SHORT).show();
                holder.mTv_gohome_goget.setVisibility(View.GONE);
            }
            list.get(position).setType(3);
        }
    }

    public final class ViewHolder implements OnItemClickListener, OnClickListener {
    	
    	public HorizontialListView imgListView;
		public TextView mTv_delivery_gohome;
        public TextView mTv_gohome_goget;
        public TextView mTv_fare;
        public RelativeLayout mRel_mentionpoint;
        public TextView mTv_mentionpoint;
        public RelativeLayout mRel_address;
        public TextView mTv_address;
        public RelativeLayout mRel_phone;
        public TextView mTv_phone;
        public RelativeLayout mRel_date;
        public TextView mTv_date;
        public LinearLayout mLin_tips;
        
        /**商品图片适配器**/
        public DeliverGoodsAdapter adapter;
        
        public ViewHolder(View convertView) {
        	
        	imgListView = (HorizontialListView) convertView.findViewById(R.id.lv_h);
        	mTv_delivery_gohome = (TextView) convertView.findViewById(R.id.tv_delivery_gohome);
            mTv_gohome_goget = (TextView) convertView.findViewById(R.id.tv_gohome_goget);
            mTv_fare = (TextView) convertView.findViewById(R.id.tv_fare);
            mRel_mentionpoint = (RelativeLayout) convertView.findViewById(R.id.rel_mentionpoint);
            mTv_mentionpoint = (TextView) convertView.findViewById(R.id.tv_mentionpoint);
            mRel_address = (RelativeLayout) convertView.findViewById(R.id.rel_address);
            mLin_tips = (LinearLayout) convertView.findViewById(R.id.lin_tips);
            mTv_address = (TextView) convertView.findViewById(R.id.tv_address);
            mRel_phone = (RelativeLayout) convertView.findViewById(R.id.rel_phone);
            mTv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            mRel_date = (RelativeLayout) convertView.findViewById(R.id.rel_date);
            mTv_date = (TextView) convertView.findViewById(R.id.tv_date);
            
            adapter = new DeliverGoodsAdapter((Activity)mContext);
            imgListView.setAdapter(adapter);
            imgListView.setOnItemClickListener(this);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			DeliverGoods goods = (DeliverGoods)adapter.getItem(position);
			//这里做商品详情跳转
			((BaseActivity)mContext).showToast("这里做商品详情界面的跳转");
		}

		@Override
		public void onClick(View v) {
			
			final int position = Integer.parseInt(v.getTag().toString());
			
			switch (v.getId()) {
			case R.id.rel_date:
				
				//自提时间
				DialogUtil.showBirthdayDialog(mTv_date, new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//设置时间
						list.get(position).getAddress().setServe_time(mTv_date.getText().toString());
					}
				}, mContext);
				
				break;

			}
		}
    }
    
    public interface ItemOnclickListener {
        /**选择自提点**/
        void getPoint(int position,View view);
        /**选择地址**/
        void getAddress(int position);
        /**选择电话**/
        void getPhone(int position);
        /**选择日期**/
        void getDate(int position);
        /**选择上门自提**/
        void clickMention(int position);
        /**选择送货上门**/
        void clickDelivery(int position);
    }

}
