package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;

/**
 * Created by kuangyong on 15/9/14.
 */
public class SimpleListAdapter extends BaseAdapter{
    private Context context;

    public SimpleListAdapter(Context context){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_simple_listview_item,null);
            myHolder=new MyHolder();
            myHolder.tv_content= (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(myHolder);
        }else{
            myHolder= (MyHolder) convertView.getTag();
        }
        return convertView;
    }

    static class MyHolder{
        TextView tv_content;
    }
}
