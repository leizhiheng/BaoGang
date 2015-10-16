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
import com.baosteel.qcsh.model.TravelGvData;

import java.util.List;


/**
 * Created by lenovo on 2015/7/22.
 */
public class TravelCustomAdapter extends BaseAdapter {
    private Context context;
    private List<TravelGvData> list;
    private LayoutInflater inflater;

    public TravelCustomAdapter(Context context, List<TravelGvData> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    public TravelCustomAdapter setList(List<TravelGvData> list) {
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
            convertView = inflater.inflate(R.layout.item_travel_custom, null);
            holder.tv_travel_data = (TextView) convertView.findViewById(R.id.tv_travel_data);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_travel_data.setText(list.get(position).getContent());

        if(list.get(position).isCheck()) {
            holder.tv_travel_data.setBackgroundResource(R.drawable.common_radius_white_withorangestrok);
            holder.tv_travel_data.setTextColor(context.getResources().getColor(R.color.orange_red));
        }
        else {
            holder.tv_travel_data.setBackgroundResource(R.drawable.common_radius_white_withgraystrok);
            holder.tv_travel_data.setTextColor(context.getResources().getColor(R.color.grayfont));
        }
        return  convertView;
    }

    public final class ViewHolder{
        public TextView tv_travel_data;
    }
}
