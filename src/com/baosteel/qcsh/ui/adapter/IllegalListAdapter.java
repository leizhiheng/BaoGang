package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.common.utils.ResourceUtils;

/**
 * Created by kuangyong on 15/9/9.
 */
public class IllegalListAdapter extends BaseAdapter{
    private Context context;

    public IllegalListAdapter(Context context){
        this.context=context;
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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if(null==convertView){
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_illegal_item,null);
            myHolder=new MyHolder();
            myHolder.tv_time=(TextView)convertView.findViewById(R.id.tv_time);
            myHolder.tv_adress=(TextView)convertView.findViewById(R.id.tv_adress);
            myHolder.tv_deal_status= (TextView) convertView.findViewById(R.id.tv_deal_status);
            myHolder.tv_describ= (TextView) convertView.findViewById(R.id.tv_describ);
            myHolder.tv_detail= (TextView) convertView.findViewById(R.id.tv_detail);
            convertView.setTag(myHolder);
        }else{
            myHolder= (MyHolder) convertView.getTag();
        }
        if(0==position){
            myHolder.tv_deal_status.setText("未处理");
            myHolder.tv_deal_status.setBackgroundResource(R.drawable.common_leftradius_red);
        }else{
            myHolder.tv_deal_status.setText("已处理");
            myHolder.tv_deal_status.setBackgroundResource(R.drawable.common_leftradius_green);
        }
        Spanned detils= Html.fromHtml("违章人次&nbsp;"+ ResourceUtils.changeStringColor("#F03A1A", "2")+"人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;扣&nbsp;"+ResourceUtils.changeStringColor("#F03A1A","2")+"&nbsp;分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罚款&nbsp;"+ResourceUtils.changeStringColor("#F03A1A","100元"));
        myHolder.tv_detail.setText(detils);
        return convertView;
    }

    static class MyHolder{
        TextView tv_time;//时间
        TextView tv_adress;//地点
        TextView tv_deal_status;//处理状态
        TextView tv_describ;//描述
        TextView tv_detail;//具体处罚
    }
}
