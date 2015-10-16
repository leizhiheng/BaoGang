package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CommonTicket;


/**
 * 常用发票信息列表适配器
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-16
 */
public class CommonTicketAdapter extends BaseAdapter {
    
	/**上下文**/
	private Context context;
    
	/**发票集合**/
    private List<CommonTicket> list;
    
    /**View加载器**/
    private LayoutInflater inflater;

    /**当前被选中的发票**/
    private CommonTicket onItemClickTicket;
    
    public CommonTicketAdapter(Context context, List<CommonTicket> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    /**
     * 记住当前点击的发票
     * @param position
     */
    public void remembItemClick(int position){
    	onItemClickTicket = list.get(position);
    }
    
    /**
     * 移除当前数据
     */
    public void removeCurrent() {
    	if(null != onItemClickTicket){
    		list.remove(onItemClickTicket);
    		notifyDataSetChanged();
    	}
	}
    
    /**
     * 刷新数据
     * @param list
     */
    public void refreshData(List<CommonTicket> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    /**
     * 刷新数据
     * @param list
     */
    public void appendData(List<CommonTicket> list) {
    	if(null == list){
    		list = new ArrayList<CommonTicket>();
    	}
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    
    /**
     * 添加发票到最后一条
     * @param ticketAdd
     */
    public void addData(CommonTicket ticketAdd) {
		if(null == list){
			list = new ArrayList<CommonTicket>();
		}
		list.add(ticketAdd);
		this.notifyDataSetChanged();
	}
    
    /**
     * 更新数据
     * @param ticketEdit
     */
    public void updateData(CommonTicket ticketEdit){
    	if(null != ticketEdit && null != onItemClickTicket){
    		onItemClickTicket.setTicketDescription(ticketEdit.getTicketDescription());
    		this.notifyDataSetChanged();
    	}
    }
    
    @Override
    public int getCount() {
        return null == list ? 0 : list.size();
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
        	convertView = inflater.inflate(R.layout.adapter_common_ticket, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        //显示数据
        CommonTicket ticket = list.get(position);
        holder.showData(ticket);
        
        return convertView;
    }

    class ViewHolder {
    	public TextView nameTv;
    	
    	ViewHolder(View view){
    		nameTv = (TextView)view.findViewById(R.id.nameTv);
    	}
    	
    	void showData(CommonTicket ticket){
    		nameTv.setText(ticket.getTicketDescription());
    	}
    }
}
