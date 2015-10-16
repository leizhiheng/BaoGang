package com.baosteel.qcsh.ui.activity.home.happyliving;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.fragment.home.happyliving.BegRentHouseFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.FactoryBegRent;
import com.baosteel.qcsh.ui.fragment.home.happyliving.FactoryRentFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.FactorySaleFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.FactoryStoreHouseFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.FactoryTransferFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.FactoryWantBuyFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.JointRentHouseFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.OfficeBuildingFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.SecondHouseBuyingFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.SecondHouseSaleFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.ShopRentFragment;
import com.baosteel.qcsh.ui.fragment.home.happyliving.WholeRentHouseFragment;
import com.common.base.BaseActivity;

public class ReleaseHouseActivity extends BaseActivity {

	public static final String EXTRA_FRAGMENT_INDEX = "fragment.index";

	/** 整租房 */
	public static final int FRAGMENT_WHOLE_RENT = 0;
	/** 合租房 */
	public static final int FRAGMENT_JOINT_RENT = 1;
	/** 求租房 */
	public static final int FRAGMENT_BEG_RENT = 2;
	/** 二手房出售 */
	public static final int FRAGMENT_SECOND_HOUSE_SALE = 3;
	/** 二手房求购 */
	public static final int FRAGMENT_SECPND_HOUSE_BUYING = 4;
//	/** 厂房/仓库/土地 */
//	public static final int FRAGMENT_FACTORY = 5;
//	/** 写字楼出租 */
//	public static final int FRAGMENT_OFFICE_BUILDING = 6;
//	/** 商铺出租 */
//	public static final int FRAGMENT_SHOP_RENT = 7;
	
	
	/**厂房/仓库/土地  出租*/
	public static final int FRAGMENT_FACTORY_RENT = 10;
	/**厂房/仓库/土地  求租*/
	public static final int FRAGMENT_FACTORY_BEG_RENT = 11;
	/**厂房/仓库/土地  转让*/
	public static final int FRAGMENT_FACTORY_TRANSFER = 12;
	/**厂房/仓库/土地  求购*/
	public static final int FRAGMENT_FACTORY_WANT_BUY = 13;
	/**厂房/仓库/土地  出售*/
	public static final int FRAGMENT_FACTORY_SALE = 14;
	
	
	/**写字楼 出租*/
	public static final int FRAGMENT_OFFICE_RENT = 20;
	/**写字楼  求租*/
	public static final int FRAGMENT_OFFICE_BEG_RENT = 21;
	/**写字楼  出售*/
	public static final int FRAGMENT_OFFICE_SALE = 23;
	/**写字楼  求购*/
	public static final int FRAGMENT_OFFICE_WANT_BUY = 24;
	
	
	/**商铺 出租*/
	public static final int FRAGMENT_STORE_RENT = 30;
	/**商铺  求租*/
	public static final int FRAGMENT_STORE_BEG_RENT = 31;
	/**商铺  出售*/
	public static final int FRAGMENT_STORE_SALE = 33;
	/**商铺  求购*/
	public static final int FRAGMENT_STORE_WANT_BUY = 34;

	private int mFragmentIndex;
	private String mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release_house);

		initData();
		initView();

		loadData();

		setFragment();
	}

	private void initData() {
		mTitle = getIntent().getStringExtra(EXTRA_KEY_TITLE);
		mFragmentIndex = getIntExtra(EXTRA_FRAGMENT_INDEX);
	}

	private void initView() {
		setTitle(mTitle);

	}

	private void loadData() {
		
	}

	private void setFragment() {
		
		Fragment fragment = null;
		switch (mFragmentIndex) {
		case FRAGMENT_WHOLE_RENT:
			fragment = new WholeRentHouseFragment();
			break;
		case FRAGMENT_JOINT_RENT:
			fragment = new JointRentHouseFragment();
			break;
		case FRAGMENT_BEG_RENT:
			fragment = new BegRentHouseFragment();
			break;
		case FRAGMENT_SECOND_HOUSE_SALE:
			fragment = new SecondHouseSaleFragment();
			break;
		case FRAGMENT_SECPND_HOUSE_BUYING:
			fragment = new SecondHouseBuyingFragment();
			break;
//		case FRAGMENT_FACTORY:
//			fragment = new FactoryStoreHouseFragment();
//			break;
//		case FRAGMENT_OFFICE_BUILDING:
//			fragment = new OfficeBuildingFragment();
//			break;
//
//		case FRAGMENT_SHOP_RENT:
//			fragment = new ShopRentFragment();
//			break;
			
			/*
			 * 厂房/仓库/土地
			 */
		case FRAGMENT_FACTORY_RENT:
			fragment = new FactoryRentFragment();
			break;
		case FRAGMENT_FACTORY_BEG_RENT:
			fragment = new FactoryBegRent();
			break;
		case FRAGMENT_FACTORY_TRANSFER:
			fragment = new FactoryTransferFragment();
			break;
		case FRAGMENT_FACTORY_WANT_BUY:
			fragment = new FactoryWantBuyFragment();
			break;
		case FRAGMENT_FACTORY_SALE:
			fragment = new FactorySaleFragment();
			break;
			
			/*
			 * 写字楼
			 */
		case FRAGMENT_OFFICE_RENT://出租
			fragment = new FactoryRentFragment();
			break;
		case FRAGMENT_OFFICE_BEG_RENT://求租
			fragment = new FactoryBegRent();
			break;
		case FRAGMENT_OFFICE_SALE://出售
			fragment = new FactoryWantBuyFragment();
			break;
		case FRAGMENT_OFFICE_WANT_BUY://求购
			fragment = new FactorySaleFragment();
			break;
			
			/*
			 * 商铺
			 */
		case FRAGMENT_STORE_RENT://出租
			fragment = new FactoryRentFragment();
			break;
		case FRAGMENT_STORE_BEG_RENT://求租
			fragment = new FactoryBegRent();
			break;
		case FRAGMENT_STORE_SALE://出售
			fragment = new FactoryWantBuyFragment();
			break;
		case FRAGMENT_STORE_WANT_BUY://求购
			fragment = new FactorySaleFragment();
			break;
			
		}
		
		if (fragment != null) {
			getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
		}
	}
}
