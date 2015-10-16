package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory.Options;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.DeliverGoods;
import com.baosteel.qcsh.utils.ImageManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * 图片adapter
 * 
 * @author 刘远祺
 * 
 */
public class DeliverGoodsAdapter extends BaseAdapter {

	/**上下文**/
	private Activity context;

	/**数据**/
	private List<DeliverGoods> dataList;
	
	private DisplayImageOptions mOptions;
	
	public DeliverGoodsAdapter(Activity context) {
		this.context = context;
		this.dataList = new ArrayList<DeliverGoods>();
		
		Options bitmapOptions = new Options();
		bitmapOptions.inSampleSize = 8;
		mOptions = new DisplayImageOptions.Builder().cacheInMemory(false)
				.cacheOnDisc(true).showStubImage(0)
				.showImageForEmptyUri(0)
				.decodingOptions(bitmapOptions)
				.showImageOnFail(0).build();
	}

	/**
	 * 刷新数据
	 * @param dataList
	 */
	public void refreshData(List<DeliverGoods> dataList) {
		this.dataList = dataList;
		this.notifyDataSetChanged();
	}

	
	/**
	 * 获取网络资源图片集合
	 * @return
	 */
	public ArrayList<String> getImageUrlList(){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<dataList.size(); i++){
			list.add(dataList.get(i).getImg());
		}
		
		return list;
	}
	
	@Override
	public int getCount() {
		return null != dataList ? dataList.size() : 0; 
	}

	
	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_imageview, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		
		//显示数据
		holder.show(position);
		
		return convertView;
	}

	class ViewHolder {

		
		public ImageView picImageView;
		
		ViewHolder(View convertView) {
			picImageView = (ImageView) convertView.findViewById(R.id.goods_pic_imageview);
		}

		/**
		 * 显示商品信息
		 * @param GoodReplaceBean
		 */
		void show(int position){
			
			//普通的图片
			picImageView.setBackgroundResource(0);
			DeliverGoods data = dataList.get(position);
			//加载网络图片
			ImageManager.LoadWithServer(data.getImg(), picImageView);
			
		}
	}
}
