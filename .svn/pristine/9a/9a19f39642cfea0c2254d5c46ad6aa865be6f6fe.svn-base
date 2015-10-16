package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CashType;
import com.baosteel.qcsh.model.TravelGvData;

import java.util.List;


/**
 * Created by lenovo on 2015/7/22.
 */
public class CashTypeAdapter extends BaseAdapter {
    private Context context;
    private List<CashType> list;
    private LayoutInflater inflater;

    public CashTypeAdapter(Context context, List<CashType> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    public CashTypeAdapter setList(List<CashType> list) {
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
            convertView = inflater.inflate(R.layout.item_cash_volume, null);
            holder.mTv_cash_type = (TextView) convertView.findViewById(R.id.tv_cash_type);
            holder.mTv_cash_detail = (TextView) convertView.findViewById(R.id.tv_cash_detail);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTv_cash_type.setText(list.get(position).getName());
        holder.mTv_cash_detail.setText(list.get(position).getDetail());

        return  convertView;
    }

    public final class ViewHolder{
        public TextView mTv_cash_type;
        public TextView mTv_cash_detail;
    }
}
