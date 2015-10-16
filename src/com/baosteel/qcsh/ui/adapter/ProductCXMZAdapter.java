package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;

/**
 * 满赠适配器
 * Created by lian on 2015/5/13.
 */
public class ProductCXMZAdapter extends BaseAdapter{
    public static final String TAG="GoodsGiftAdapter";
    private ArrayList PROMOTION_REDUCE;
    private Context context;

    public ProductCXMZAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
//        return null != PROMOTION_REDUCE ? PROMOTION_REDUCE.size() : 0;
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if(null==convertView){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_product_list_cx,null);
            myHolder=new MyHolder();
            myHolder.tv_describ= (TextView) convertView.findViewById(R.id.tv_describ);
            myHolder.iv_arrow= (ImageView) convertView.findViewById(R.id.iv_arrow);
            convertView.setTag(myHolder);
        }else
            myHolder= (MyHolder) convertView.getTag();
        if(position==0){
            myHolder.iv_arrow.setVisibility(View.VISIBLE);
        }else{
            myHolder.iv_arrow.setVisibility(View.INVISIBLE);
        }
        myHolder.tv_describ.setText("满200赠20元现金劵");
        return convertView;
    }
    static class MyHolder{
        public TextView tv_describ;//类型
        public ImageView iv_arrow;//箭头
    }
}
