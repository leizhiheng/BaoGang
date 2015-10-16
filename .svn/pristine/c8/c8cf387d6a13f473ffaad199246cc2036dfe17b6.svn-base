package com.baosteel.qcsh.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jws on 2015/9/6.
 * 购物车商家商品类
 */
public class ShoppingCar implements Serializable {

	/**店铺ID**/
    private int id;
    private String merchant_name;
    private int type;
    private List<CarItem> shoppingList;
    private boolean isCheck;

    public ShoppingCar(String merchant_name, int type, List<CarItem> shoppingList,boolean isCheck) {
        this.merchant_name = merchant_name;
        this.type = type;
        this.shoppingList = shoppingList;
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public ShoppingCar setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
        return this;
    }

    /**
     * 是否有数据
     * @return
     */
    public boolean hasCartItem(){
    	if(null == shoppingList || shoppingList.size() == 0){
    		return false;
    	}
    	
    	return true;
    }
    
    public List<CarItem> getCarItem() {
        return shoppingList;
    }

    public ShoppingCar setCarItem(List<CarItem> shoppingList) {
        this.shoppingList = shoppingList;
        return this;
    }

    public int getId() {
        return id;
    }

    public ShoppingCar setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return merchant_name;
    }

    public ShoppingCar setName(String merchant_name) {
        this.merchant_name = merchant_name;
        return this;
    }

    public int getType() {
        return type;
    }

    public ShoppingCar setType(int type) {
        this.type = type;
        return this;
    }


}
