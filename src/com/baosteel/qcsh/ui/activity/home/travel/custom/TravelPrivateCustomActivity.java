package com.baosteel.qcsh.ui.activity.home.travel.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.ui.adapter.FragmentTabAdapter;
import com.baosteel.qcsh.ui.fragment.home.travel.TravelCustomFivesFragment;
import com.baosteel.qcsh.ui.fragment.home.travel.TravelCustomFourFragment;
import com.baosteel.qcsh.ui.fragment.home.travel.TravelCustomOneFragment;
import com.baosteel.qcsh.ui.fragment.home.travel.TravelCustomThreeFragment;
import com.baosteel.qcsh.ui.fragment.home.travel.TravelCustomTwoFragment;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jws on 2015/9/9.
 */
public class TravelPrivateCustomActivity extends BaseActivity {
    public FragmentTabAdapter tabAdapter;
    private TitleBar mTitle_Bar;
    public ImageView mImg_city;
    public TextView mTv_city;
    public ImageView mImg_theme;
    public TextView mTv_theme;
    public ImageView mImg_date;
    public TextView mTv_date;
    public ImageView mImg_budget;
    public TextView mTv_budget;
    public ImageView mImg_complete;
    public TextView mTv_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_private_custom);

        initViews();
        initData();


    }

    private void initViews() {
        mImg_city = (ImageView) findViewById(R.id.img_city);
        mTv_city = (TextView) findViewById(R.id.tv_city);
        mImg_theme = (ImageView) findViewById(R.id.img_theme);
        mTv_theme = (TextView) findViewById(R.id.tv_theme);
        mImg_date = (ImageView) findViewById(R.id.img_date);
        mTv_date = (TextView) findViewById(R.id.tv_date);
        mImg_budget = (ImageView) findViewById(R.id.img_budget);
        mTv_budget = (TextView) findViewById(R.id.tv_budget);
        mImg_complete = (ImageView) findViewById(R.id.img_complete);
        mTv_complete = (TextView) findViewById(R.id.tv_complete);
        mTitle_Bar = (TitleBar) findViewById(R.id.title_bar);
    }

    private void initData() {
        mTitle_Bar.setTitle(getStringExtra("travelType"));
        mTitle_Bar.setBackgroud(R.color.lxb_color);
        // 得到所有的fragment
        List<Fragment> fragments = getFragments();
        // 得到Fragment的适配器
        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content);
        tabAdapter.init();
    }

    /**
     * 让所有tab恢复默认背景
     *
     * **/
    public void clearTabIocStyle(){
        mImg_city.setImageResource(R.drawable.city_not);
        mTv_city.setTextColor(getResources().getColor(R.color.gr_l_color));
        mImg_theme.setImageResource(R.drawable.theme_not);
        mTv_theme.setTextColor(getResources().getColor(R.color.gr_l_color));
        mImg_date.setImageResource(R.drawable.date_not);
        mTv_date.setTextColor(getResources().getColor(R.color.gr_l_color));
        mImg_budget.setImageResource(R.drawable.budget_not);
        mTv_budget.setTextColor(getResources().getColor(R.color.gr_l_color));
        mImg_complete.setImageResource(R.drawable.complete_not);
        mTv_complete.setTextColor(getResources().getColor(R.color.gr_l_color));
    }

    /**
     * 设置tab选中时的背景
     *
     * **/
    public void setTabBackgroud(ImageView imageView,TextView textView,int imgResId,int colorId){
        imageView.setImageResource(imgResId);
        textView.setTextColor(getResources().getColor(colorId));
    }

    /**
     * 得到所有的Fragment
     *
     * @return
     */
    private List<Fragment> getFragments() {

        List<Fragment> list = new ArrayList<Fragment>();

        list.add(new TravelCustomOneFragment());

        list.add(new TravelCustomTwoFragment());

        list.add(new TravelCustomThreeFragment());

        list.add(new TravelCustomFourFragment());

        list.add(new TravelCustomFivesFragment());

        return list;
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        Fragment fragment = tabAdapter.getCurrentFragment();
        if (null != fragment) {
            fragment.onActivityResult(arg0, arg1, arg2);
        }
    }

}
