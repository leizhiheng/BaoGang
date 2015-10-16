package com.baosteel.qcsh.ui.activity.store;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.store.StoreDetailData;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

/**
 * 执照查询结果 Created by kuangyong on 15/9/22.
 */
public class LicencseQueryResultActivity extends BaseActivity{

	private HeadView headview;// 标题栏
	private TextView tvcompanyname;// 公司名称
	private TextView tvlicensenum;// 营业执照号码
	private TextView tvlegalperson;// 法人代表
	private TextView tvadress;// 营业执照所在地
	private TextView tvregisteredmoney;// 注册资金
	private TextView tvlicensevalidity;// 执照期限
	private TextView tvbusinessrange;// 经营范围
	private TextView tvcompanyadress;// 公司地址
	private TextView tvstorename;// 店铺名称
	private TextView tvstoreweb;// 店铺网址
	private TextView tvtip;//提示
	
	private StoreDetailData mStoreDetailData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_license_query_result);
		
		mStoreDetailData = (StoreDetailData) getIntent().getSerializableExtra(StoreDetailsActivity.EXTRA_STORE_DETAIL);
		
		findView();
		setListener();
		initView();
	}

	private void findView() {
        this.tvtip = (TextView) findViewById(R.id.tv_tip);
		this.headview = (HeadView) findViewById(R.id.headview);
		this.tvstoreweb = (TextView) findViewById(R.id.tv_store_web);
		this.tvstorename = (TextView) findViewById(R.id.tv_store_name);
		this.tvcompanyadress = (TextView) findViewById(R.id.tv_company_adress);
		this.tvbusinessrange = (TextView) findViewById(R.id.tv_business_range);
		this.tvlicensevalidity = (TextView) findViewById(R.id.tv_license_validity);
		this.tvregisteredmoney = (TextView) findViewById(R.id.tv_registered_money);
		this.tvadress = (TextView) findViewById(R.id.tv_adress);
		this.tvlegalperson = (TextView) findViewById(R.id.tv_legal_person);
		this.tvlicensenum = (TextView) findViewById(R.id.tv_license_num);
		this.tvcompanyname = (TextView) findViewById(R.id.tv_company_name);
	}

	private void setListener() {
	}

	private void initView() {
		headview.setTitle("查询执照");
		headview.setRightImageBtnBackGround(R.drawable.icon_3point);
		headview.showRightImageBtn(View.VISIBLE, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("菜单");
			}
		});
		headview.setHeadViewBackGroundColor(getResources().getColor(
				R.color.red_baosteel));
		tvlicensenum.setText("营业执照号："+mStoreDetailData.getBusinessLicenceCode());
		tvcompanyname.setText("企业名称："+mStoreDetailData.getCompanyName());
		tvlegalperson.setText("公司法人："+mStoreDetailData.getLegalPeople());
		tvadress.setText("营业执照所在地："+mStoreDetailData.getBusinessLicenceAddress());
		tvregisteredmoney.setText("企业注册资金："+mStoreDetailData.getRegisiterMoney());
		tvlicensevalidity.setText("营业执照有效期："+mStoreDetailData.getLicenceTime());
		tvbusinessrange.setText("营业执照经营范围："+mStoreDetailData.getBusinessRange());
		tvcompanyadress.setText("公司地址："+mStoreDetailData.getAddress());
		tvstorename.setText("店铺名称："+mStoreDetailData.getMerchantName());
		tvstoreweb.setText("店铺网址："+mStoreDetailData.getWebsite());
		
	}
	
	
	/**
	 * 
	 * @Description 获取执照信息
	 * @Author blue
	 * @Time 2015-9-30
	 */
	private void loadData() {
		
	}
}
