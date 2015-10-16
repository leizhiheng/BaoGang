package com.baosteel.qcsh.ui.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baosteel.qcsh.R;
import com.baosteel.qcsh.model.CarItem;
import com.baosteel.qcsh.model.ShoppingCar;
import com.baosteel.qcsh.model.ShoppingCarList;
import com.baosteel.qcsh.ui.activity.store.StoreMainActivity;
import com.baosteel.qcsh.ui.adapter.ShoppingCarItemAdapter.ChangeNumOncliListener;
import com.baosteel.qcsh.ui.adapter.ShoppingCarItemAdapter.CheckItemOncliListener;
import com.common.utils.CountDown;
import com.common.view.listview.MyListView;


/**
 * 购物车-店铺-适配器
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-10-10
 */
public class ShoppingCarBusinessAdapter extends BaseAdapter {
    private Context context;
    private ShoppingCarList storeList;
    private LayoutInflater inflater;
    private ChangeNumOncliListener changeNumOncliListener;
    private CheckItemOncliListener checkItemOncliListener;
    private boolean isShowCheck = true;

    public ShoppingCarBusinessAdapter(Context context){
    	this.inflater = LayoutInflater.from(context);
    	this.storeList = new ShoppingCarList();
        this.context = context;
    }

    public void refreshData(List<ShoppingCar> shoppingCarsList, ChangeNumOncliListener changeNumOncliListener,CheckItemOncliListener checkItemOncliListener) {
    	refreshData(shoppingCarsList, changeNumOncliListener, checkItemOncliListener, true);
    }
    
    public void refreshData(List<ShoppingCar> shoppingCarsList, ChangeNumOncliListener changeNumOncliListener,CheckItemOncliListener checkItemOncliListener, boolean isShowCheck) {
    	this.changeNumOncliListener = changeNumOncliListener;
    	this.checkItemOncliListener = checkItemOncliListener;
        this.storeList.refreshData(shoppingCarsList);
        this.isShowCheck = isShowCheck;
        this.notifyDataSetChanged();
    }

    /**
     * 服务类商品刷新数据
     * @param shoppingCarsList
     */
    public void refreshData(List<ShoppingCar> shoppingCarsList){
        this.storeList.refreshData(shoppingCarsList);
        this.notifyDataSetChanged();
        isShowCheck=false;
    }
    
    public ShoppingCarBusinessAdapter(Context context, ShoppingCarList list,ChangeNumOncliListener changeNumOncliListener,boolean isShowCheck) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.changeNumOncliListener = changeNumOncliListener;
        this.storeList = list;
        this.isShowCheck = isShowCheck;
    }


    @Override
    public int getCount() {
        return null != storeList ? storeList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return storeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int storeIndex, View convertView, ViewGroup parent) {
    	CountDown cd = new CountDown();
    	cd.start("getView   " + storeIndex);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_shoppingcar_business, null);
            holder.mLayoutShop = convertView.findViewById(R.id.layout_shop);
            holder.mImg_shopitem_check = (ImageView) convertView.findViewById(R.id.img_shopitem_check);
            holder.mImg_shop_icon = (ImageView) convertView.findViewById(R.id.iv_shop_icon);
            holder.mImg_shopitem_name = (TextView) convertView.findViewById(R.id.img_shopitem_name);
            holder.mLv_shopping_car_item = (MyListView) convertView.findViewById(R.id.lv_shopping_car_item);
            
            holder.adapter = new ShoppingCarItemAdapter(context, null, changeNumOncliListener,checkItemOncliListener, isShowCheck);
            holder.mLv_shopping_car_item.setAdapter(holder.adapter);
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        /*
         * 点击店铺名称和图标进入到店铺主页
         */
        holder.mLayoutShop.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, StoreMainActivity.class);
				intent.putExtra(StoreMainActivity.MERCHANT_ID, storeList.get(storeIndex).getId()+"");
				context.startActivity(intent);
			}
        });
        
        ShoppingCar store = storeList.get(storeIndex);
        
        //店铺的选中
        int checkBg = storeList.isCheckStore(store.getId()+"") ? R.drawable.test_check : R.drawable.test_no_check;
        holder.mImg_shopitem_check.setImageResource(checkBg);
        holder.mImg_shopitem_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkItemOncliListener.checkBusiness(storeIndex);
            }
        });
        if(!isShowCheck){
            holder.mImg_shopitem_check.setVisibility(View.GONE);
        }
        holder.mImg_shopitem_name.setText(store.getName());
        
        List<String> checkedShoppingIds = storeList.getStoreCheckedShoppingIds(store.getId()+"");
        holder.adapter.refreshData(store.getCarItem(), checkedShoppingIds, storeIndex);
        
        cd.stop("getView   " + storeIndex);
        return  convertView;
    }


    public final class ViewHolder {
    	public View mLayoutShop;//店铺名称和店铺图标
        public ImageView mImg_shopitem_check;
        public ImageView mImg_shop_icon;//店铺图标
        public TextView mImg_shopitem_name;//店铺名称
        public MyListView mLv_shopping_car_item;
        private ShoppingCarItemAdapter  adapter;
    }

    /**
     * 获取选中的商品
     * @return
     */
	public String getCheckedPorductShoppingIds() {
		return storeList.getCheckedPorductShoppingIds();
	}


	/**
	 * 移除被删除的商品
	 */
	public void removeDeletedProduct() {
		
		//移除数据
		storeList.removeDeletedProduct();
		
		//通知刷新数据
		this.notifyDataSetChanged();
	}


	/**
	 * 全选或反选
	 * @param check
	 */
	public void checkedAll(boolean check) {
		
		storeList.checkedAll(check);
		
		//刷新数据
		notifyDataSetChanged();
	}

	/**
	 * 加载购物车总价
	 * @return
	 */
	public double loadShoppingCarPrice() {
		return storeList.loadCheckedProductTotalMoney();
	}
	
	/**
	 * 是否为全选
	 * @return
	 */
	public boolean isCheckAll(){
		return storeList.isCheckAll();
	}

	/**
	 * 选择店铺
	 * @param position
	 */
	public void checkBusiness(int position) {
		ShoppingCar store = storeList.get(position);
		String storeId = store.getId()+"";
		
		//反选
		boolean isChecked = storeList.isCheckStore(storeId);
		storeList.checkStore(storeId, !isChecked);
		
		//刷新数据
		this.notifyDataSetChanged();
	}

	/**
	 * 选择商品
	 * @param storePosition
	 * @param productPosition
	 */
	public void checkProduct(int storePosition, int productPosition) {
		ShoppingCar store = storeList.get(storePosition);
		CarItem product = store.getCarItem().get(productPosition);
		
		//获取店铺id，购物车id
		String storeId = store.getId()+"";
		String shoppingId = product.getId()+"";
		
		//反选
		boolean isChecked = storeList.isCheckProduct(shoppingId, storeId);
		storeList.checkProduct(shoppingId, storeId, !isChecked);
		
		//刷新数据
		this.notifyDataSetChanged();
	}

	/**
	 * 修改购物车商品数量
	 * @param storeId
	 * @param shopping_id
	 * @param num
	 */
	public void updateProductCount(String storeId, String shopping_id, int num) {
		
		//修改商品数量
		storeList.updateProductCount(storeId, shopping_id, num);
		
		//刷新数据
		this.notifyDataSetChanged();
	}
}
