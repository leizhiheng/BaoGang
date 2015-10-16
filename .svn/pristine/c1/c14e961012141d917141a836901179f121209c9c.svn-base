package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CashList;
import com.baosteel.qcsh.model.CashType;

import java.util.List;


/**
 * Created by lenovo on 2015/7/22.
 */
public class CashListAdapter extends BaseAdapter {
    private Context context;
    private List<CashList> list;
    private LayoutInflater inflater;
    private boolean isUse;


    public CashListAdapter(Context context, List<CashList> list,boolean isUse) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.isUse = isUse;
    }

    public CashListAdapter setList(List<CashList> list) {
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
        ViewHolder holder =null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cash_volume_list, null);
            holder.mTv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.mTv_shop_title = (TextView) convertView.findViewById(R.id.tv_shop_title);
            holder.mTv_shop_content = (TextView) convertView.findViewById(R.id.tv_shop_content);
            holder.mTv_user_date = (TextView) convertView.findViewById(R.id.tv_user_date);
            holder.mRel_cash_background = (RelativeLayout) convertView.findViewById(R.id.rel_cash_background);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(isUse)
            holder.mRel_cash_background.setBackgroundResource(R.drawable.cash_use);
        else
            holder.mRel_cash_background.setBackgroundResource(R.drawable.cash_no_use);
        holder.mTv_price.setText(list.get(position).getPrice());
        holder.mTv_shop_content.setText(list.get(position).getContent());
        holder.mTv_user_date.setText(list.get(position).getUseDate());
        holder.mTv_shop_title.setText(list.get(position).getTitle());
        return  convertView;
    }

    public final class ViewHolder{
        public TextView mTv_price;
        public TextView mTv_shop_title;
        public TextView mTv_shop_content;
        public TextView mTv_user_date;
        public RelativeLayout mRel_cash_background;
    }
}
