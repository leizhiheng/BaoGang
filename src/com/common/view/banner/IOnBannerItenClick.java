package com.common.view.banner;

/**
 * Banner图的点击回调
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-31
 */
public interface IOnBannerItenClick {
	
	/**
	 * Banner图点击事件 
	 * @param data		数据对象
	 * @param position	
	 */
	public void onBannerItemClick(BannerData data, int position);
}
