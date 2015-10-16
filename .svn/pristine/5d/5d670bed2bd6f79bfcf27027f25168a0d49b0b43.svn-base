package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.OrderItem;
import com.baosteel.qcsh.ui.activity.my.order.ReturnDetailActivity;

/**
 * 我的订单-退换货
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-21
 */
public class OrderReturnAdapter extends BaseAdapter {
	
	/**上下文**/
	private Context mContext;
	
	/**订单列表**/
	private List<OrderItem> mDatas;
	
	/**layout加载器**/
	private LayoutInflater mInflater;

	public OrderReturnAdapter(Context context, List<OrderItem> datas) {
		this.mContext = context;
		this.mDatas = datas;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_order_return, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		return convertView;
	}

	class ViewHolder {
		
		/**订单状态**/
		TextView tvStateName;
		
		/**订单对象**/
		private OrderItem order;

		public ViewHolder(View view) {
			
			tvStateName = (TextView) view.findViewById(R.id.tv_order_state_name);
		}
		
		/**
		 * 显示数据
		 * @param order
		 */
		public void showData(OrderItem order){
			
		}

	}

}
