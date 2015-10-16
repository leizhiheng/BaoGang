package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.PickUpAddress;

import java.util.List;


/**
 * Created by lenovo on 2015/7/22.
 */
public class PickupPointAdapter extends BaseAdapter {
    private Context context;
    private List<PickUpAddress> list;
    private LayoutInflater inflater;

    public PickupPointAdapter(Context context, List<PickUpAddress> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    public PickupPointAdapter setList(List<PickUpAddress> list) {
        this.list = list;
        this.notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_pickup_point, null);
            holder.mImg_pickup = (ImageView) convertView.findViewById(R.id.img_pickup);
            holder.mTv_pickup_point = (TextView) convertView.findViewById(R.id.tv_pickup_point);
            holder.mTv_pickup_phone = (TextView) convertView.findViewById(R.id.tv_pickup_phone);
            holder.mTv_pickup_address = (TextView) convertView.findViewById(R.id.tv_pickup_address);
            holder.mTv_pickup_time = (TextView) convertView.findViewById(R.id.tv_pickup_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTv_pickup_point.setText(list.get(position).getServe_name());
        holder.mTv_pickup_phone.setText(list.get(position).getContact_tel());
        holder.mTv_pickup_time.setText(list.get(position).getServe_time());
        holder.mTv_pickup_address.setText(getAddressDetail(position));
        if (list.get(position).ischeck()){
            holder.mImg_pickup.setImageResource(R.drawable.icon_selected);
        }else{
            holder.mImg_pickup.setImageResource(R.drawable.icon_no_select);
        }
        return convertView;
    }

    public final class ViewHolder {
        public ImageView mImg_pickup;
        public TextView mTv_pickup_point;
        public TextView mTv_pickup_phone;
        public TextView mTv_pickup_address;
        public TextView mTv_pickup_time;
    }


    /**获取地址详细**/
    private String getAddressDetail(int position) {
        return list.get(position).getProvinceName() + list.get(position).getCityName()
                + list.get(position).getAreaName()+list.get(position).getStreet_address();
    }

    public void ChangeCheckState(int position){
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setIscheck(false);
        }
        list.get(position).setIscheck(true);
        this.notifyDataSetChanged();
    }
}
