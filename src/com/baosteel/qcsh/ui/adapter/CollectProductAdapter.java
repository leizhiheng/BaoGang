package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.database.bean.Product;
import com.baosteel.qcsh.dialog.SimpleIconDialog;
import com.baosteel.qcsh.model.BaoGangData;
import com.baosteel.qcsh.model.ProductCollect;
import com.baosteel.qcsh.utils.ImageManager;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.view.swipe.adapters.BaseSwipeAdapter;

import org.json.JSONObject;

/**
 * 乐虎券适配器
 * 
 * @author 刘远祺
 * 
 */
public class CollectProductAdapter extends BaseSwipeAdapter {

	/**上下文**/
	private Context context;
	
	public List<ProductCollect> dataList;
	
	public CollectProductAdapter(Context context) {
		this(context, false);
	}

	
	public CollectProductAdapter(Context context, boolean swipeEnable) {
		this.context = context;
		this.dataList = new ArrayList<ProductCollect>();
		super.mSwipeEnabled = swipeEnable;
	}

	/**
	 * 刷新数据(分页加载)
	 * @param dataList
	 */
	public void appendData(List<ProductCollect> tempData) {
		
		if(null == tempData || 0 == tempData.size()){
			return;
		}
		
		//累加赋值
		this.dataList.addAll(tempData);
		
		this.notifyDataSetChanged();
	}

	/**
	 * 刷新数据
	 * @param dataList
	 */
	public void refreshData(List<ProductCollect> tempData) {
		
		//完全赋值
		this.dataList = tempData;
		
		this.notifyDataSetChanged();
	}
	
	public List<ProductCollect> getData() {
		return dataList;
	}
	
	@Override
	public int getCount() {
		if(null == dataList){
			return 0;
		}
		
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}


	class ViewHolder {

		public ImageView productImg;// 商品图片
		public TextView nameTv;		// 商品名称
		public TextView priceTv; 	// 商品价格
		
		ViewHolder(View convertView) {
			
			productImg = (ImageView) convertView.findViewById(R.id.iv_product);
			nameTv = (TextView) convertView.findViewById(R.id.tv_title);
			priceTv = (TextView) convertView.findViewById(R.id.tv_price);
		}

		/**
		 * 显示乐虎券信息
		 * @param product
		 */
		void show(ProductCollect product, int position){
			
			nameTv.setText(product.getGoodsName());
			priceTv.setText("¥"+ product.getGoodsPrice());
			ImageManager.Load(product.getImgUrl(),productImg);
		}
		
	}

	/**
	 * 移除当前领取的票
	 * @param ticket
	 */
	public void removeProduct(Product product) {
		dataList.remove(product);
		this.notifyDataSetChanged();
	}

	
	//***********swipe start***********
	@Override
	public int getSwipeLayoutResourceId(int position) {
		//返回xml 里面 对应 SwipeLayout对应的id
		return R.id.swipe;
	}

	@Override
	public View generateView(int position, ViewGroup parent) {
		View convertView = LayoutInflater.from(context).inflate(R.layout.adapter_collect_product, null);
		ViewHolder viewHolder = new ViewHolder(convertView);
		convertView.setTag(viewHolder);

		return convertView;
	}

	@Override
	public void fillValues(int position, View convertView) {

		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		viewHolder = (ViewHolder) convertView.getTag();
		
		//显示数据
		viewHolder.show((ProductCollect)getItem(position), position);
	}
	
	@Override
	protected void onDeleteClick(int position) {

		// 显示数据
		ProductCollect product= dataList.get(position);
		deleteCoupon(product.getGoodsId(), position);
		
	}
	
	//***********swipe end***********
	
	private void deleteCoupon(final String id, final int position) {

		queryAppDeleteMemberGoodsCollection(id, position);

//		CustomResponseHandler handler = new CustomResponseHandler(context, true) {
//			@Override
//			public void onRefreshData(String content) {
//				super.onRefreshData(content);
//				
//				int type = JSONParseUtils.getInt(content, "type");
//				if (type > 0) {
//					
//					//删除成功
//					Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
//					dataList.ticketList.remove(position);
//					notifyDataSetChanged();
//					
//				} else {
//					
//					//删除失败
//					String msg = JSONParseUtils.getString(content, "msg");
//					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//				}
//			}
//		};
//		RequstClient.appMyCouponDelete(handler, id);
	}

	/**
	 * 取消收藏商品
	 */
	private void queryAppDeleteMemberGoodsCollection(String goodsId,final int position){
		RequestClient.queryAppDeleteMemberGoodsCollection(context, BaoGangData.getInstance().getUser().userId, goodsId, new RequestCallback<JSONObject>(false) {
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(context, response)) {
					Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
					dataList.remove(position);
					notifyDataSetChanged();
				}
			}
		});
	}
}
