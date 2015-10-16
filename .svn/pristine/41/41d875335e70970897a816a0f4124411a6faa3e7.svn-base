package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;

/**
 * 提醒列表适配器
 * Created by kuangyong on 15/9/14.
 */
public class RemindListAdapter extends BaseAdapter{
    private Context context;

    public RemindListAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if(null==convertView){
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_remind_item,null);
            myHolder=new MyHolder();
            myHolder.tv_type= (TextView) convertView.findViewById(R.id.tv_type);
            myHolder.tv_remind_detailed= (TextView) convertView.findViewById(R.id.tv_remind_detailed);
            myHolder.iv_type= (ImageView) convertView.findViewById(R.id.iv_type);
            convertView.setTag(myHolder);
        }else{
            myHolder= (MyHolder) convertView.getTag();
        }
        return convertView;
    }

    static class MyHolder{
        TextView tv_type;//类型名称
        TextView tv_remind_detailed;//详细描述
        ImageView iv_type;//类型图标
    }
}
