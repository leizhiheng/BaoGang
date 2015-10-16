package com.baosteel.qcsh.ui.fragment.commen;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.ui.activity.home.healthy.HealthyHomeActivity;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.common.base.BaseFragment;
import com.common.view.banner.BannerData;
import com.common.view.banner.BannerView;
import com.common.view.banner.IOnBannerItenClick;
import com.common.view.viewflow.CircleFlowIndicator;
import com.common.view.viewflow.ViewFlow;

/**
 * 
 * @description 将Banner图功能封装到这个Fragment
 * @author blue
 * @Time 2015-9-1
 *
 */
public class BannerFragment extends BaseFragment{

	/**Banner轮播图**/
	private BannerView bannerView;
	
	/**Banner轮播图数据**/
	private List<BannerData> dataList;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_banner, null);
		return fragmentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		//初始化控件
		initView();
		
		// 设置数据
		bannerView.refreshData(dataList);
		
	}
	

	private void initView(){
		
		//轮播图
		ViewFlow viewFlow = (ViewFlow) findViewById(R.id.mHomeViewflow);// 获得viewFlow对象
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.mHomeViewflowindic); // viewFlow下的indic
		bannerView = new BannerView(mContext, viewFlow, indic);
		
	}
	
	/**
	 * 
	 * @Description 设置Banner图数据
	 * @Author blue
	 * @Time 2015-9-1
	 */
	public void setData(List<BannerData> data) {
		dataList = data;
	}

	/**
	 * 
	 * @Description 给Banner图设置监听事件
	 * @Author blue
	 * @Time 2015-9-1
	 */
	public void setOnBannerItemClickListner(IOnBannerItenClick listner) {
		bannerView.setOnItemClickListener(listner);
	}
	
	/**
	 * 
	 * @Description 下载Banner图
	 * @param moduleId 模块Id
	 * @param moduleLevel 模块级别 首页跟七个宝为1级 其他为2级
	 * @Author blue
	 * @Time 2015-9-24
	 */
	public void loadBannerData(int moduleId, int moduleLevel) {
		
		RequestClient.queryAPPBanner(getActivity(), moduleId, moduleLevel, new RequestCallback<JSONObject>() {
			
			@Override
			public void onResponse(JSONObject response) {
				if (JSONParseUtils.isSuccessRequest(mContext, response)) {
					bannerView.refreshData(JSONParseUtils.getBannerDatas(response));
				
					if (bannerView.getCount() == 0) {
						Toast.makeText(BannerFragment.this.getActivity(), "服务器无数据，使用模拟Banner数据", Toast.LENGTH_SHORT).show();
						// 构造Banner假数据
						List <BannerData> mBannerDatas = new ArrayList<BannerData>();
						mBannerDatas.add(new BannerData());
						mBannerDatas.add(new BannerData());
						mBannerDatas.add(new BannerData());
						mBannerDatas.add(new BannerData());
						mBannerDatas.add(new BannerData());
						bannerView.refreshData(mBannerDatas);
					}
				} else {
					/*
					 * 如果请求错误，将返回的错误信息显示出来
					 */
					Toast.makeText(BannerFragment.this.getActivity(), JSONParseUtils.getResponseMsg(response), Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
				
				if (bannerView.getCount() == 0) {
		    		Toast.makeText(BannerFragment.this.getActivity(), "服务器无数据，使用模拟数据", Toast.LENGTH_SHORT).show();
		    		//构造Banner假数据
		            List<BannerData> dataList = new ArrayList<BannerData>();
		            dataList.add(new BannerData());
		            dataList.add(new BannerData());
		            dataList.add(new BannerData());
		            dataList.add(new BannerData());
		            dataList.add(new BannerData());
		            dataList.add(new BannerData());
		            dataList.add(new BannerData());
		            bannerView.refreshData(dataList);
				}
				
			}
		});
	}

}