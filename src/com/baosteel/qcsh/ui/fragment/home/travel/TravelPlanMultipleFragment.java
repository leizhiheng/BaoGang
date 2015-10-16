package com.baosteel.qcsh.ui.fragment.home.travel;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.common.base.BaseFragment;
import com.common.utils.DialogUtil;

/**
 * 一年多次计划
 * 
 * @author 刘远祺
 * 
 * @todo TODO
 * 
 * @date 2015-9-10
 */
public class TravelPlanMultipleFragment extends BaseFragment implements OnClickListener{
	
	/**目的地**/
	private RelativeLayout destinationRl;
	
	/**目的地**/
	private TextView destinationTv;
	
	/**被保人数**/
	private RelativeLayout beInsuranceRl;
	
	/**被保人数**/
	private TextView beInsuranceTv;
	
	/**开始时间**/
	private RelativeLayout beginTimeRl;
	
	/**开始时间**/
	private TextView beginTimeTv;
	
	/**开始时间-右边提示**/
	private TextView beginTimeTipTv;
	
	/**结束时间**/
	private RelativeLayout finishTimeRl;
	
	/**结束时间**/
	private TextView finishTimeTv;
	
	/**选择保险公司**/
	private RelativeLayout chooseCompanyLayout;
	
	/**选择保险公司**/
	private TextView chooseCompanyTv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.fragment_plant_multiple, null);
		return fragmentView;
	}

	@Override
	public void bindView() {

		destinationRl = (RelativeLayout) findViewById(R.id.destinationRl);
		destinationTv = (TextView) findViewById(R.id.destinationTv);
		beInsuranceRl = (RelativeLayout) findViewById(R.id.beInsuranceRl);
		beInsuranceTv = (TextView) findViewById(R.id.beInsuranceTv);
		beginTimeRl = (RelativeLayout) findViewById(R.id.beginTimeRl);
		beginTimeTv = (TextView) findViewById(R.id.beginTimeTv);
		beginTimeTipTv = (TextView) findViewById(R.id.beginTimeTipTv);
		finishTimeRl = (RelativeLayout) findViewById(R.id.finishTimeRl);
		finishTimeTv = (TextView) findViewById(R.id.finishTimeTv);
		chooseCompanyLayout = (RelativeLayout) findViewById(R.id.chooseCompanyLayout);
		chooseCompanyTv = (TextView) findViewById(R.id.chooseCompanyTv);

		destinationRl.setOnClickListener(this);
		beInsuranceRl.setOnClickListener(this);
		beginTimeRl.setOnClickListener(this);
		finishTimeRl.setOnClickListener(this);
		chooseCompanyLayout.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.destinationRl:
			
			//目的地，选择省市县
			
			break;
		case R.id.beInsuranceRl:
			
			//被保人
			
			break;
		case R.id.beginTimeRl:
			
			//开始日期
			DialogUtil.showBirthdayDialog(beginTimeTv, mContext);
			
			break;
		case R.id.finishTimeRl:
			
			//结束日期
			DialogUtil.showBirthdayDialog(finishTimeTv, mContext);
			
			break;
		case R.id.chooseCompanyLayout:
			
			//选择公司
			
			break;
		}
	}
	
}
