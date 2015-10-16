package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.MemberRank;

/**
 * 会员级别适配器
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-8
 */
public class MyRankAdapter extends BaseAdapter{

	/**上下文**/
	private Context context;

	/**布局加载器**/
	private LayoutInflater mInflater;
	
	private List<MemberRank> dataList;
	
	public MyRankAdapter(Context context, List<MemberRank> dataList){
		mInflater = LayoutInflater.from(context);
		this.dataList = dataList;
		this.context = context;
	}
	
	
	@Override
	public int getCount() {
		return dataList == null ? 0 : dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		ViewHolder holder;
		if(view == null){
			holder = new ViewHolder();
			view = mInflater.inflate(R.layout.adapter_member_rank, null);
			holder.rank_icon = (ImageView) view.findViewById(R.id.rank_icon);
			holder.rank_message = (TextView) view.findViewById(R.id.rank_message);
			view.setTag(holder);
		}
		holder = (ViewHolder) view.getTag();
		
		holder.rank_icon.setImageResource(dataList.get(arg0).img);
		
		//holder.rank_message.setText("成长值："+dataList.get(arg0).minPoint+" -- "+dataList.get(arg0).maxPoint);
		return view;
	}
	
	class ViewHolder{
		ImageView rank_icon;
		TextView rank_message;
	}
}
