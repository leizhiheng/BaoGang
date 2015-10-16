package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class PhotoPagerAdapter extends PagerAdapter{

    private List<View> mViewList;

    public PhotoPagerAdapter(List<View> viewList) {
        mViewList = viewList;
    }

    //viewpager中的组件数量
    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }
  //滑动切换的时候销毁当前的组件
    @Override
    public void destroyItem(ViewGroup container, int position,
            Object object) {
        ((ViewPager) container).removeView(mViewList.get(position));
    }
  //每次滑动的时候生成的组件
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
