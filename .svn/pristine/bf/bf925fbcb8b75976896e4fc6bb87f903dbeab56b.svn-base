package com.baosteel.qcsh.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.Brand;
import com.common.utils.DensityUtils;
import com.common.utils.DeviceUtils;
import com.common.view.fastlink.LnkToolsGridViewAdapter;
import com.common.view.fastlink.LnkToolsGridViewAdapter.BackItemClickListener;

/**
 * 品牌推荐适配器 
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-2
 */
public class HomeBrandPagerAdapter extends PagerAdapter {

	/**品牌数据**/
	private List<List<Brand>> dataList;
	
	/**每页显示的数量**/
	private int pageCount;
	
	/**上下文**/
	private Context context;
	
	public HomeBrandPagerAdapter(Context context, List<Brand> tempList, int pageCount) {
		
		this.pageCount = pageCount;
		this.context = context;
		
		//数据切割分组，每组条目数pageCount
		if(null != tempList){
			dataList = new ArrayList<List<Brand>>();
			List<Brand> temp = new ArrayList<Brand>();
			
			for(int i=0; i<tempList.size(); i++){
				
				if((i%pageCount) == 0){
					temp = new ArrayList<Brand>();
					dataList.add(temp);
				}
				temp.add(tempList.get(i));
			}
		}
	}

	@Override
	public int getCount() {
		return null != dataList ? dataList.size() :  0;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		GridView gridview = new GridView(context);
		gridview.setBackgroundColor(context.getResources().getColor(R.color.white));
		
		gridview.setNumColumns(4);
		gridview.setColumnWidth(GridView.AUTO_FIT);
		gridview.setHorizontalSpacing(0);
		gridview.setVerticalSpacing(0);
		
		container.addView(gridview, 0);
		LayoutParams params = gridview.getLayoutParams();
		params.width = LayoutParams.MATCH_PARENT;
		params.height = DensityUtils.dp2px(context, 100);
		gridview.setLayoutParams(params);
		
//		int width = LayoutParams.MATCH_PARENT;
//		ViewPager.LayoutParams params = new ViewPager.LayoutParams();
//		params.width = width;
//		params.height = width;
//		gridview.setLayoutParams(params);
		
		HomeBrandAdapter adapter = new HomeBrandAdapter(context, dataList.get(position));
		gridview.setAdapter(adapter);
		adapter.setBackItemClickListener(backItemClickListener);

		
		return gridview;
	}

	@Override
	public void destroyItem(View view, int arg1, Object arg2) {
		((ViewPager) view).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View view, Object arg1) {
		return view == arg1;
	}
	
	HomeBrandAdapter.BrandItemClickListener backItemClickListener;

	public void setOntemClickListener(HomeBrandAdapter.BrandItemClickListener backItemClickListener) {
		this.backItemClickListener = backItemClickListener;
	}
}
