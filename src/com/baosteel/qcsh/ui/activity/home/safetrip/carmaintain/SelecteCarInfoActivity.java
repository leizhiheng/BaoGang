package com.baosteel.qcsh.ui.activity.home.safetrip.carmaintain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

import com.baosteel.qcsh.R;
import com.common.base.BaseActivity;
import com.common.view.topbar.HeadView;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择汽车信息 Created by kuangyong on 15/9/15.
 */
public class SelecteCarInfoActivity extends BaseActivity {
	public static final String OPRETATE_TYPE="opretate_type";//操作类型
	public static final String OPRETATE_ADD="1";//添加
	public static final String OPRETATE_EDIT="2";//编辑
	private HeadView headview;// 标题布局
	private List<Fragment> fragments;//fragment的集合
	private List<String> titles;//标题集合
	private int preFragmentIndex=-1;//上一个fragment的下标
    private SelecteCarBrandFragment selecteCarBrandFragment;//选择汽车品牌
    private SelecteCarTypeFragment selecteCarTypeFragment;//选择汽车型号
    private SelecteCarDisplacementFragment selecteCarDisplacementFragment;//选择汽车排量
    private SelecteCarProductionDateFragment selecteCarProductionDateFragment;//选择汽车生成日期
    private CarInfoFragment carInfoFragment;//汽车信息
	private String type;//1.添加  2.编辑

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selecte_car_info);
		type=getIntent().getStringExtra(OPRETATE_TYPE);
		findView();
		initView();
		if(type.equals(OPRETATE_ADD)){//添加
			initFragments();
		}else if(type.equals(OPRETATE_EDIT)){//编辑
			showSelecteCarProductionDate();
		}
	}

	private void findView() {
		this.headview = (HeadView) findViewById(R.id.headview);
	}

	/**
	 * 初始化页面 显示选择汽车品牌
	 */
	private void initFragments() {
		if(null==selecteCarBrandFragment){
			selecteCarBrandFragment=new SelecteCarBrandFragment();
			fragments.add(selecteCarBrandFragment);
			selecteCarBrandFragment.setOnItemClickListener(new SelecteCarBrandFragment.OnListItemClickListener() {
				@Override
				public void onItemClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
					showSelecteCarType(groupPosition, childPosition);
				}
			});
		}
        showFragment(R.id.fragment_selecte_car_info, selecteCarBrandFragment);
	}

	/**
	 * 显示选择型号列表
	 * @param groupPosition
	 * @param childPosition
	 */
	private void showSelecteCarType(int groupPosition, int childPosition){
		preFragmentIndex++;//上一个fragment下标
		setTitleAndAdd2List("奥迪");
		if(null==selecteCarTypeFragment){
			selecteCarTypeFragment=new SelecteCarTypeFragment();
			fragments.add(selecteCarTypeFragment);
			selecteCarTypeFragment.setOnItemClickListener(new SelecteCarTypeFragment.OnListItemClickListener() {
				@Override
				public void onItemClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
					showSelecteCarDisplacement(groupPosition, childPosition);
				}
			});
		}
		showFragment(R.id.fragment_selecte_car_info, selecteCarTypeFragment);
	}

	/**
	 * 显示选择排量列表
	 * @param groupPosition
	 * @param childPosition
	 */
	private void showSelecteCarDisplacement(int groupPosition, int childPosition){
		preFragmentIndex++;//上一个fragment下标
		setTitleAndAdd2List("A1");
		if(null==selecteCarDisplacementFragment){
			selecteCarDisplacementFragment=new SelecteCarDisplacementFragment();
			fragments.add(selecteCarDisplacementFragment);
			selecteCarDisplacementFragment.setOnItemClickListener(new SelecteCarDisplacementFragment.OnListItemClickListener() {
				@Override
				public void onItemClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
					showSelecteCarProductionDate(groupPosition, childPosition);
				}
			});
		}
		showFragment(R.id.fragment_selecte_car_info, selecteCarDisplacementFragment);
	}

	/**
	 * 显示选择生成日期列表
	 * @param groupPosition
	 * @param childPosition
	 */
	private void showSelecteCarProductionDate(int groupPosition, int childPosition){
		preFragmentIndex++;//上一个fragment下标
		setTitleAndAdd2List("2.2L");
		if(null==selecteCarProductionDateFragment){
			selecteCarProductionDateFragment=new SelecteCarProductionDateFragment();
			fragments.add(selecteCarProductionDateFragment);
			selecteCarProductionDateFragment.setOnItemClickListener(new SelecteCarProductionDateFragment.OnListItemClickListener() {
				@Override
				public void onItemClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
					showSelecteCarProductionDate();
				}
			});
		}
		showFragment(R.id.fragment_selecte_car_info, selecteCarProductionDateFragment);
	}


	/**
	 * 显示已添加的汽车信息
	 */
	private void showSelecteCarProductionDate(){
		preFragmentIndex=-2;//上一个fragment下标
		headview.setTitle("车辆信息");
		carInfoFragment=new CarInfoFragment();
		showFragment(R.id.fragment_selecte_car_info, carInfoFragment);
		headview.showRightTextBtn(View.VISIBLE, "确定",
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						go2CarManager();
					}
				});
	}

	/**
	 * 设置标题并加到list中
	 * @param title
	 */
	private void setTitleAndAdd2List(String title){
		headview.setTitle(title);
		titles.add(title);
	}

	private void initView() {
		titles=new ArrayList<String>();
		fragments=new ArrayList<Fragment>();
		setTitleAndAdd2List("设置车型");
		headview.setHeadViewBackGroundColor(getResources().getColor(
				R.color.green_safetrip));
		headview.showRightTextBtn(View.VISIBLE, "反馈新车型",
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(mContext,FeedBackNewCarActivity.class));
					}
				});
		headview.setBackOnclickListener(new View.OnClickListener() {//监听返回键
			@Override
			public void onClick(View v) {
				back();
			}
		});
	}

	private void go2CarManager(){
		startActivity(new Intent(mContext,CarManageActivity.class));
		finish();
	}

	@Override
	public void onBackPressed() {
		back();
	}

	private void back(){
		if(-1==preFragmentIndex){//上一个fragment为空
			finish();
		}else if(-2==preFragmentIndex){//信息添加完成
			go2CarManager();
		}else{//返回上一个fragment
			showFragment(R.id.fragment_selecte_car_info, fragments.get(preFragmentIndex));
			headview.setTitle(titles.get(preFragmentIndex));
			preFragmentIndex--;
		}
	}
}
