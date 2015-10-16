package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.baosteel.qcsh.R;

/**
 * 汽车生产日期列表适配器
 * Created by kuangyong on 15/9/15.
 */
public class CarProductionDateListAdapter extends BaseExpandableListAdapter{

    private Context context;

    public CarProductionDateListAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 4;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if(null==convertView){
            holder=new GroupHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_car_info_group,null);
            holder.tv_group= (TextView) convertView.findViewById(R.id.tv_group);
            convertView.setTag(holder);
        }else{
            holder= (GroupHolder) convertView.getTag();
        }
        String group="";
        if(0==groupPosition){
            group="国产";
        }else if(1==groupPosition){
            group="进口";
        }
        holder.tv_group.setText(group);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if(null==convertView){
            holder=new ChildHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_car_info_production_date,null);
            holder.tv_type_icon= (TextView) convertView.findViewById(R.id.tv_type_icon);
            holder.child_diver= (View) convertView.findViewById(R.id.child_diver);
            holder.tv_type= (TextView) convertView.findViewById(R.id.tv_type);
            convertView.setTag(holder);
        }else{
            holder= (ChildHolder) convertView.getTag();
        }
        holder.child_diver.setVisibility(View.VISIBLE);
        if(3==childPosition){
            holder.child_diver.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupHolder{
        TextView tv_group;//组别
    }

    static class ChildHolder{
        TextView tv_type;//型号名称
        View child_diver;;//分割线
        TextView tv_type_icon;//型号图标
    }
}
