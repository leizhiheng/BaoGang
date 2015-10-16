package com.baosteel.qcsh.ui.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.HotService;
import com.baosteel.qcsh.model.HotService.LayoutItem;
import com.baosteel.qcsh.utils.ImageManager;

/**
 * 热门服务
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-2
 */
public class HotServiceView {

	/** 容器 **/
	private LinearLayout contentView;

	/** 上下文 **/
	private Context context;

	public HotServiceView(Context context, LinearLayout contentView) {
		this.context = context;
		this.contentView = contentView;
	}

	/**
	 * 
	 * @param layoutId
	 * @param context
	 * @return
	 */
	private View getLayoutView(int layoutId, Context context){
		
		LayoutInflater inflater = LayoutInflater.from(context);
		
		switch (layoutId) {
		case 1:
			return inflater.inflate(R.layout.adapter_hot_service_layout1, null);
		case 2:
			return inflater.inflate(R.layout.adapter_hot_service_layout2, null);
		case 3:
			return inflater.inflate(R.layout.adapter_hot_service_layout3, null);
		case 4:
			return inflater.inflate(R.layout.adapter_hot_service_layout4, null);
		case 5:
			return inflater.inflate(R.layout.adapter_hot_service_layout5, null);
		case 6:
			return inflater.inflate(R.layout.adapter_hot_service_layout6, null);
		default:
			return inflater.inflate(R.layout.adapter_hot_service_layout7, null);
		}
	}
	
	/**
	 * 刷新数据
	 * 
	 * @param dataList
	 */
	public void refreshData(List<HotService> dataList) {
		if (null != dataList && dataList.size() > 0) {
			contentView.removeAllViews();

			HotService hotService = null;
			for (int i = 0; i < dataList.size(); i++) {
				hotService = dataList.get(i);
				View view = getLayoutView(hotService.layoutId, context);
				showLayoutData(hotService, view);
				contentView.addView(view);
			}
		}
	}

	/**
	 * 显示布局数据
	 * 
	 * @param hotService
	 * @param view
	 */
	void showLayoutData(HotService hotService, View view) {
		
		//所有imageview的Id
		int[] imageIds = new int[]{R.id.image1, R.id.image2, R.id.image3, R.id.image4, R.id.image5, R.id.image6, R.id.image7};

		try{
			if(null != hotService && null != hotService.imgList){
				
				//循环每一个图片的信息
				for(int i=0; i<hotService.imgList.size(); i++){
					
					final LayoutItem item = hotService.imgList.get(i);
					ImageView imageView = (ImageView)view.findViewById(imageIds[i]);
					ImageManager.Load(item.imgUrl, imageView);
					
					//设置点击事件
					imageView.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if(null != onItemClick){
								onItemClick.onItemClickHotService(item);
							}
						}
					});
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/***
	 * 单个图片的点击事件回调
	 */
	private IOnItemClickHotService onItemClick;
	public void setOnItemClickListener(IOnItemClickHotService onItemClick){
		this.onItemClick = onItemClick;
	}
	
	public interface IOnItemClickHotService{
		public void onItemClickHotService(LayoutItem imgItem);
	}
	
}
