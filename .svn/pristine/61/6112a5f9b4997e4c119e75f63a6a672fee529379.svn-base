package com.baosteel.qcsh.ui.fragment.cart;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.baosteel.qcsh.AppContext;
import com.baosteel.qcsh.R;
import com.baosteel.qcsh.api.RequestCallback;
import com.baosteel.qcsh.api.RequestClient;
import com.baosteel.qcsh.dialog.SimpleMsgDialog;
import com.baosteel.qcsh.ui.activity.MainActivity;
import com.baosteel.qcsh.ui.view.TitleBar;
import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Preferences;
import com.common.base.BaseFragment;

/**
 * Created by jws on 2015/9/15.
 */
public class CartFragmentLayout extends BaseFragment {


    /**
     * 购物车列表
     **/
    private CartFragment cartFragment;

    /**
     * 空数据界面
     **/
    private EmptyCartFragment emptyCartFragment;

    /**
     * 当前显示的界面
     **/
    private Fragment currFragment;

    /**
     * 标题栏
     **/
    private TitleBar mTitle_Bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_cart_layout, null);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
    }

    /**
     * 初始化信息
     **/
    private void initViews() {
        mTitle_Bar = (TitleBar) findViewById(R.id.title_bar);
    }

    /**
     * 初始化数据
     **/
    private void initData() {
        mTitle_Bar.setTitle("购物车");
        mTitle_Bar.setBackgroud(R.color.index_red);
        mTitle_Bar.setLeftIconVisibility(View.GONE);
        mTitle_Bar.showRightIcon(3, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String shopping_id= cartFragment.getCheckedPorductShoppingIds();
                if(shopping_id.isEmpty()){
                    showToast("请选择要删除的商品");
                    return;
                }
                //不能申请介入
                final SimpleMsgDialog dialog = new SimpleMsgDialog(mContext);
                dialog.setMsg("您确定要删除选中商品");
                dialog.setOkText("确定");
                dialog.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (v.getId() == R.id.btn_ok) {
                            //清空购物车
                            clearShopcart(shopping_id);
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    /**
     * 删除购物车商品：
     */
    private void clearShopcart(final String shopping_id) {

        //获取被选中的商品(购物车id的集合，以逗号分隔)
        RequestClient.deleteAppShoppingCart(mContext, shopping_id, new RequestCallback<JSONObject>(false) {

            @Override
            public void onResponse(JSONObject response) {
                if (JSONParseUtils.isSuccessRequest(mContext, response)) {

                    //删除成功,刷新购物车数据(删除成功后，不刷新数据，直接从适配器里面删除对应的数据)
                    cartFragment.removeDeletedProduct(shopping_id);
                    showToast("删除成功");
                    
                    //查询购物车总数量
                    queryAppShoppingCartNum();
                }
            }
        });
    }

    /**
     * 查询购物车总数量
     * 在购物车本界面操作购物车数据，是不会刷新购物车数量的，需要手动调接口
     */
    private void queryAppShoppingCartNum(){
    	if(mContext instanceof MainActivity){
    		((MainActivity)mContext).queryAppShoppingCartNum();
    	}
    }
    
//    private void showCartFragment() {
//        if (userIsLogin()) {
//            //修改每次都会画购物车界面
//            if (null == cartFragment) {
//                cartFragment = new CartFragment();
//            } else {
//                //重新加载购物车cartFragment.loadData();
//            }
//            moreFragment(cartFragment);
//        } else {
//            if (null == emptyCartFragment) {
//                emptyCartFragment = new EmptyCartFragment();
//            }
//            moreFragment(emptyCartFragment);
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        showCartFragment();
    }

    private void moreFragment(Fragment to) {
        FragmentManager fragmentManager = mContext.getSupportFragmentManager();

        if (currFragment != to) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {
                if (currFragment == null) {
                    fragmentTransaction.add(R.id.fl_cart, to).commit();
                } else {
                    fragmentTransaction.hide(currFragment).add(R.id.fl_cart, to).commit();
                }

            } else {
                fragmentTransaction.hide(currFragment).show(to).commit();
            }
        }
        currFragment = to;

    }

    public void showCartFragment() {
        String shoppingId = Preferences.getString(Preferences.PreKey.SHOPPINGIDS);
        if (shoppingId.isEmpty()&&userIsLogin()==false) {
            if (null == emptyCartFragment) {
                emptyCartFragment = new EmptyCartFragment();
            }
            moreFragment(emptyCartFragment);

        } else {

            if (null == cartFragment) {
                cartFragment = new CartFragment();
            } else {
                //重新加载购物车cartFragment.loadData();
                cartFragment.onResume();
            }
            moreFragment(cartFragment);

        }
    }

    public void showEmptyFragment(){
        if (null == emptyCartFragment) {
            emptyCartFragment = new EmptyCartFragment();
        }
        moreFragment(emptyCartFragment);
    }

}
