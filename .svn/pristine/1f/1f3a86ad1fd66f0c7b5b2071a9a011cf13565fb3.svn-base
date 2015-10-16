package com.baosteel.qcsh.ui.fragment.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.ProductMaybeLike;
import com.baosteel.qcsh.ui.activity.my.LoginActivity;
import com.baosteel.qcsh.ui.activity.my.PhoneRegistActivity;
import com.baosteel.qcsh.ui.adapter.FragmentViewPagerAdapter;
import com.baosteel.qcsh.ui.fragment.product.GoodsDetailLikeFragment;
import com.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jws on 2015/9/15.
 */
public class EmptyCartFragment extends BaseFragment implements View.OnClickListener{
    private Button mBtn_login;
    private Button mBtn_register;
    /**猜你喜欢**/
    private LinearLayout maybeLikeLayout;
    private ViewPager mebyLikeViewPager;
    /**猜你喜欢适配器**/
    private FragmentViewPagerAdapter likeViewpagerAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_cart_empty, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        initViews();
        // 初始化数据

        // 加载竞拍数据
        loadData();
    }

    private void initViews(){
        //猜你喜欢
        maybeLikeLayout = (LinearLayout)findViewById(R.id.mebylike_layout);
        mebyLikeViewPager = (ViewPager) findViewById(R.id.mebyLikeViewPager);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_register = (Button) findViewById(R.id.btn_register);
    }

    private void loadData(){
        mBtn_login.setOnClickListener(this);
        mBtn_register.setOnClickListener(this);
        likeViewpagerAdapter = new FragmentViewPagerAdapter(getFragmentManager(), null);
        mebyLikeViewPager.setAdapter(likeViewpagerAdapter);

        //猜你喜欢
        List<ProductMaybeLike> listLikes = new ArrayList<ProductMaybeLike>();
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        listLikes.add(new ProductMaybeLike());
        fillMayLike(listLikes);
    }

    /**
     * 填充猜你喜欢的数据
     */
    private void fillMayLike(List<ProductMaybeLike> listLikes) {
        List<ProductMaybeLike> listTemp = new ArrayList<ProductMaybeLike>();
        if (listLikes != null) {
            List<Fragment> listLikeFragments = new ArrayList<Fragment>();
            for (int i = 0; i < listLikes.size(); i++) {
                if (listTemp.size() < 4) {
                    listTemp.add(listLikes.get(i));
                    if (i >= listLikes.size() - 1) {
                        List<ProductMaybeLike> listItems = new ArrayList<ProductMaybeLike>();
                        listItems.addAll(listTemp);
                        GoodsDetailLikeFragment fragment = new GoodsDetailLikeFragment();
                        fragment.setData(listItems);
                        listLikeFragments.add(fragment);
                    }
                } else {
                    List<ProductMaybeLike> listItems = new ArrayList<ProductMaybeLike>();
                    listItems.addAll(listTemp);
                    GoodsDetailLikeFragment fragment = new GoodsDetailLikeFragment();
                    fragment.setData(listItems);
                    listLikeFragments.add(fragment);
                    listTemp = new ArrayList<ProductMaybeLike>();
                    listTemp.add(listLikes.get(i));
                }
            }

            //通知刷新
            likeViewpagerAdapter.refreshFragmentList(listLikeFragments);

            //显示猜你喜欢布局
            if (listLikes.size() > 0) {
                maybeLikeLayout.setVisibility(View.VISIBLE);
            } else {
                maybeLikeLayout.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.btn_register:
                startActivity(new Intent(mContext,PhoneRegistActivity.class));
                break;
        }
    }
}
