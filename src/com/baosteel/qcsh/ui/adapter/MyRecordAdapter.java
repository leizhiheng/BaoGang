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
import com.baosteel.qcsh.model.ProductRecord;
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
public class MyRecordAdapter extends BaseSwipeAdapter {

	/**上下文**/
	private Context context;

	public List<ProductRecord> dataList;

	public MyRecordAdapter(Context context) {
		this(context, false);
	}


	public MyRecordAdapter(Context context, boolean swipeEnable) {
		this.context = context;
		this.dataList = new ArrayList<ProductRecord>();
		super.mSwipeEnabled = swipeEnable;
	}

	/**
	 * 刷新数据(分页加载)
	 * @param dataList
	 */
	public void appendData(List<ProductRecord> tempData) {

		if(null == tempData || 0 == tempData.size()){
			return;
		}

		if(null == this.dataList){
			this.dataList = new ArrayList<ProductRecord>();
		}

		//累加赋值
		this.dataList.addAll(tempData);

		this.notifyDataSetChanged();
	}

	/**
	 * 刷新数据
	 * @param dataList
	 */
	public void refreshData(List<ProductRecord> tempData) {

		if(null == tempData || 0 == tempData.size()){
			return;
		}

		//完全赋值
		this.dataList = tempData;

		this.notifyDataSetChanged();
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

	public ProductRecord getProduct(int position){
		return (ProductRecord)getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}


	class ViewHolder {

		public ImageView productImg;// 商品图片
		public TextView dateTv;		// 日期
		public TextView nameTv;		// 商品名称
		public TextView priceTv; 	// 商品价格

		ViewHolder(View convertView) {

			productImg = (ImageView) convertView.findViewById(R.id.iv_product);
			nameTv = (TextView) convertView.findViewById(R.id.tv_title);
			dateTv = (TextView) convertView.findViewById(R.id.tv_date);
			priceTv = (TextView) convertView.findViewById(R.id.tv_price);
		}

		/**
		 * 显示乐虎券信息
		 * @param product
		 */
		void show(ProductRecord product, int position){

			ImageManager.Load(product.getImg(), productImg);
			nameTv.setText(product.getName()+"\n");
			priceTv.setText("¥"+ product.getPrice());

			String preDate = position > 0 ? getProduct(position-1).getLook_time() : "";
			String curDate = product.getLook_time();
			if(preDate.equals(curDate)){
				dateTv.setVisibility(View.GONE);
			}else{
				dateTv.setText(curDate);
				dateTv.setVisibility(View.VISIBLE);
			}
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
		View convertView = LayoutInflater.from(context).inflate(R.layout.adapter_my_record, null);
		ViewHolder viewHolder = new ViewHolder(convertView);
		convertView.setTag(viewHolder);

		return convertView;
	}

	@Override
	public void fillValues(int position, View convertView) {

		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		viewHolder = (ViewHolder) convertView.getTag();

		//显示数据
		viewHolder.show((ProductRecord)getItem(position), position);
	}

	@Override
	protected void onDeleteClick(int position) {

		// 显示数据
		ProductRecord product= dataList.get(position);
		deleteCoupon(product.getId(), position);

	}

	//***********swipe end***********

	private void deleteCoupon( String id, final int position) {
        deleteMyRecord(id,position);

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

	private void deleteMyRecord(String ids,final int position){
		RequestClient.deleteMyRecord(context, ids, new RequestCallback<JSONObject>(false) {
			@Override
			public void onResponse(JSONObject response) {
                if(JSONParseUtils.isSuccessRequest(context,response)){
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    dataList.remove(position);
//					if(0==dataList.size()){
//						if(null!=listener){
//							listener.reload();
//						}
//					}else{
					notifyDataSetChanged();
//					}
                }
			}
		});
	}

	public interface CallBackListener{
		void reload();
	}

	private CallBackListener listener;

	public void setCallBackListener(CallBackListener listener){
		this.listener=listener;
	}
}
