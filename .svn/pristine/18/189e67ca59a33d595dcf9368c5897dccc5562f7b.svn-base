package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CommonCar;


/**
 * 常用车辆信息
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-16
 */
public class CommonCarAdapter extends BaseAdapter {
    
	
	private Context context;
    
    private List<CommonCar> list;
    
    private LayoutInflater inflater;

    /**当前被选中的发票**/
    private CommonCar onItemClickCar;
    
    public CommonCarAdapter(Context context, List<CommonCar> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    /**
     * 记住当前点击的发票
     * @param position
     */
    public void remembItemClick(int position){
    	onItemClickCar = list.get(position);
    }
    
    /**
     * 添加发票到最后一条
     * @param ticketAdd
     */
    public void addData(CommonCar ticketAdd) {
		if(null == list){
			list = new ArrayList<CommonCar>();
		}
		list.add(ticketAdd);
		this.notifyDataSetChanged();
	}
    
    /**
     * 更新数据
     * @param ticketEdit
     */
    public void updateData(CommonCar ticketEdit){
    	if(null != ticketEdit && null != onItemClickCar){
    		//onItemClickCar.setCompany(ticketEdit.getCompany());
    		this.notifyDataSetChanged();
    	}
    }
    
    /**
     * 刷新数据
     * @param list
     */
    public void refreshData(List<CommonCar> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    	//return 10;
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
            convertView = inflater.inflate(R.layout.adapter_common_car_info, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public final class ViewHolder {
        public ImageView mImg_is_check;
        public TextView mTv_address_people;
        public TextView mTv_address_phone;
        public TextView mTv_address_name;
        public LinearLayout mLin_edit_info;
    }
}
