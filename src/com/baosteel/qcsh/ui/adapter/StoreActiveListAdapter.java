package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.database.bean.TopProduct;
import com.baosteel.qcsh.model.SingleSelectItemData;
import com.baosteel.qcsh.ui.activity.store.MoreProductsActivity;
import com.baosteel.qcsh.ui.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 *  店铺活动列表适配器
 * Created by kuangyong on 15/9/18.
 */
public class StoreActiveListAdapter extends BaseAdapter {

    private Context context;


    public StoreActiveListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
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
        MyHolder holder ;
        if(null==convertView){
            convertView=LayoutInflater.from(context).inflate(R.layout.adapter_active_item,null);
            holder=new MyHolder();
            holder.btn_more= (TextView) convertView.findViewById(R.id.btn_more);
            holder.tv_active_name= (TextView) convertView.findViewById(R.id.tv_active_name);
            holder.gv_top_list= (MyGridView) convertView.findViewById(R.id.gv_top_list);
            convertView.setTag(holder);
        }else{
            holder= (MyHolder) convertView.getTag();
        }
        final String title;
        if(position==0){
            title="产品推荐";
        }else{
            title="新品推荐";
        }
        holder.tv_active_name.setText(title);
        holder.gv_top_list.setAdapter(new TopProdectAdapter(context, getData()));
        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MoreProductsActivity.class).putExtra(MoreProductsActivity.TITLE,title));
            }
        });
        return convertView;
    }

    static class MyHolder{
        TextView tv_active_name;//活动名称
        TextView btn_more;//更多按钮
        MyGridView gv_top_list;//产品列表
    }

    private List<TopProduct> getData() {
        List<TopProduct> list = new ArrayList<TopProduct>();
        list.add(new TopProduct("丹麦进口 丹麦蓝罐曲奇饼干125g加仑子味下午茶点心",
                R.drawable.test_pic, "10.8", 157+""));
        list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
        list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
        list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
        list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
        list.add(new TopProduct("丹麦进口食品", R.drawable.test_pic, "10.8", 157+""));
        return list;
    }
}
