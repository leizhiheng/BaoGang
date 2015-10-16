package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Business;

import java.util.List;


/**
 * Created by lenovo on 2015/7/22.
 */
public class BusinessSelectAdapter extends BaseAdapter {
    private Context context;
    private List<Business> list;
    private LayoutInflater inflater;

    public BusinessSelectAdapter(Context context, List<Business> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
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
            convertView = inflater.inflate(R.layout.item_business_select_item, null);
            holder.mTv_business_name = (TextView) convertView.findViewById(R.id.tv_business_name);
            holder.mTv_business_address = (TextView) convertView.findViewById(R.id.tv_business_address);
            holder.mTv_business_tech = (TextView) convertView.findViewById(R.id.tv_business_tech);
            holder.mTv_business_comment = (TextView) convertView.findViewById(R.id.tv_business_comment);
            holder.mTv_business_far = (TextView) convertView.findViewById(R.id.tv_business_far);
            holder.mTv_business_comment_num = (TextView) convertView.findViewById(R.id.tv_business_comment_num);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTv_business_name.setText(list.get(position).getName());
        holder.mTv_business_address.setText("地址："+list.get(position).getName());
        holder.mTv_business_tech.setText("技术水平：" + list.get(position).getTech() + "");
        holder.mTv_business_far.setText(list.get(position).getCommentFar()+"米");
        holder.mTv_business_comment_num.setText(list.get(position).getCommentNum()+"条评论");
        return  convertView;
    }

    public final class ViewHolder{
        public TextView mTv_business_name;
        public TextView mTv_business_address;
        public TextView mTv_business_tech;
        public TextView mTv_business_comment;
        public TextView mTv_business_far;
        public TextView mTv_business_comment_num;
    }
}
