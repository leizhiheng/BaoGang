package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baosteel.qcsh.model.CarInfoBean;
import com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain.SelecteCarInfoActivity;
import com.baosteel.qcsh.R;

/**
 * Created by kuangyong on 15/9/9.
 */
public class CarManageAdapter extends BaseAdapter{
    private Context context;
    private List<CarInfoBean> data;
    private int selectedIndex;

    public CarManageAdapter(Context context){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_car_info_item,null);
            myHolder=new MyHolder();
            myHolder.iv_selected=(ImageView)convertView.findViewById(R.id.iv_selected);
            myHolder.iv_brand=(ImageView)convertView.findViewById(R.id.iv_brand);
            myHolder.tv_car_info=(TextView)convertView.findViewById(R.id.tv_car_info);
            myHolder.tv_car_describ=(TextView)convertView.findViewById(R.id.tv_car_describ);
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
                context.startActivity(new Intent(context, SelecteCarInfoActivity.class).putExtra(SelecteCarInfoActivity.OPRETATE_TYPE, SelecteCarInfoActivity.OPRETATE_EDIT));
            }
        });
        return convertView;
    }

    static class MyHolder{
        ImageView iv_selected;//是否选中的图片
        ImageView iv_brand;//汽车品牌图片
        TextView tv_car_info;//汽车信息
        TextView tv_car_describ;//描述
        LinearLayout btn_edit;//编辑
    }
}
