package com.baosteel.qcsh.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CarInfoBean;
import com.baosteel.qcsh.ui.activity.home.safetrip.carsafe.EditAddCarInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuangyong on 15/9/9.
 */
public class CarInfoAdapter extends BaseAdapter{
    private Context context;
    private List<CarInfoBean> data;
    private int selectedIndex;

    public CarInfoAdapter(Context context){
        this.context=context;
        data=new ArrayList<CarInfoBean>();
        for (int i=0;i<5;i++){
            CarInfoBean bean=new CarInfoBean();
            if(i==1){
                bean.setIsSelected(true);
                selectedIndex=i;
            }
            data.add(bean);
        }
    }

    @Override
    public int getCount() {
        return null==data?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if(null==convertView){
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_car_list_item,null);
            myHolder=new MyHolder();
            myHolder.iv_selected=(ImageView)convertView.findViewById(R.id.iv_selected);
            myHolder.tv_carnum=(TextView)convertView.findViewById(R.id.tv_carnum);
            myHolder.tv_frame_number=(TextView)convertView.findViewById(R.id.tv_frame_number);
            myHolder.tv_motor_number=(TextView)convertView.findViewById(R.id.tv_motor_number);
            myHolder.tv_time=(TextView)convertView.findViewById(R.id.tv_time);
            myHolder.btn_edit= (LinearLayout) convertView.findViewById(R.id.btn_edit);
            convertView.setTag(myHolder);
        }else{
            myHolder= (MyHolder) convertView.getTag();
        }
        if(data.get(position).isSelected()){
            myHolder.iv_selected.setImageResource(R.drawable.icon_selected_red);
        }else{
            myHolder.iv_selected.setImageResource(R.drawable.icon_unselected_gray);
        }
        myHolder.iv_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedIndex!=position){
                    data.get(selectedIndex).setIsSelected(false);
                    data.get(position).setIsSelected(true);
                    selectedIndex=position;
                    notifyDataSetChanged();
                }
            }
        });
        myHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditAddCarInfoActivity.class).putExtra(EditAddCarInfoActivity.EDIT_TYPE, "2"));
            }
        });
        return convertView;
    }

    static class MyHolder{
        ImageView iv_selected;//是否选中的图片
        TextView tv_carnum;//车牌号码
        TextView tv_frame_number;//车架号
        TextView tv_motor_number;//发动机号码
        TextView tv_time;//登记时间
        LinearLayout btn_edit;//编辑
    }
}
