package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ConsigneeInfo;
import com.baosteel.qcsh.model.TravelGvData;

import java.util.List;


/**
 * Created by jws on 2015/7/22.
 */
public class ConsigneeInfoAdapter extends BaseAdapter {
    private Context context;
    private List<ConsigneeInfo> list;
    private LayoutInflater inflater;
    private EditOnClickListener editOnClickListener;

    public List<ConsigneeInfo> getList() {
        return list;
    }

    public ConsigneeInfoAdapter setEditOnClickListener(EditOnClickListener editOnClickListener) {
        this.editOnClickListener = editOnClickListener;
        return this;
    }

    public ConsigneeInfoAdapter(Context context, List<ConsigneeInfo> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    public ConsigneeInfoAdapter setList(List<ConsigneeInfo> list) {
        this.list = list;
        this.notifyDataSetChanged();
        return this;
    }

    public ConsigneeInfoAdapter addConsigneeInfo(ConsigneeInfo  consigneeInfo) {
        this.list.add(consigneeInfo);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_consignee_info, null);
            holder.mImg_is_check = (ImageView) convertView.findViewById(R.id.img_is_check);
            holder.mTv_address_people = (TextView) convertView.findViewById(R.id.tv_address_people);
            holder.mTv_address_phone = (TextView) convertView.findViewById(R.id.tv_address_phone);
            holder.mTv_address_name = (TextView) convertView.findViewById(R.id.tv_address_name);
            holder.mLin_edit_info = (LinearLayout) convertView.findViewById(R.id.lin_edit_info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list.get(position).isCheck())
            holder.mImg_is_check.setImageResource(R.drawable.test_check);
        else
            holder.mImg_is_check.setImageResource(R.drawable.test_no_check);
        holder.mTv_address_people.setText(list.get(position).getName());
        holder.mTv_address_phone.setText(list.get(position).getPhone());
        holder.mTv_address_name.setText(list.get(position).getAddress());
        holder.mLin_edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editOnClickListener.edit(position);
            }
        });
        return convertView;
    }

    public final class ViewHolder {
        public ImageView mImg_is_check;
        public TextView mTv_address_people;
        public TextView mTv_address_phone;
        public TextView mTv_address_name;
        public LinearLayout mLin_edit_info;
    }

    public interface EditOnClickListener{
        void edit(int position);
    }
}
