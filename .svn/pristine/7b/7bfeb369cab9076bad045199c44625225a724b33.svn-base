package com.baosteel.qcsh.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;

/**
 * 购物车列表
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-10-10
 */
public class ShoppingCarList {

	//这个对象是为了保存当前选中的所有商品，和店铺
	/**选中的商品集合  Map<storeId, List<shopping_id>>**/
	private Map<String, List<String>> selectMap;

	//这个对象是为了能够正常的给adapter提供数据
	/**购物车列表**/
	private List<ShoppingCar> shoppingCarList;
	
	//这个对象是为了方便(根据storeId取所有的product)
	/**购物车所有商品Map<storeId, List<Product>>**/
	private Map<String, List<CarItem>> productMap;
	
	public ShoppingCarList(){
		
		selectMap = new HashMap<String, List<String>>();
		shoppingCarList = new ArrayList<ShoppingCar>();
		productMap = new HashMap<String, List<CarItem>>();
	}
	
	/**
	 * 获取购物车店铺总数量
	 * @return
	 */
	public int size(){
		return null != shoppingCarList ? shoppingCarList.size() : 0;
	}
	
	/**
	 * 根据索引获取一个店铺
	 * @param position
	 * @return
	 */
	public ShoppingCar get(int position){
		if(position < size()){
			return shoppingCarList.get(position);
		}
		return null;
	}

	
	/**
	 * 刷新所有数据
	 * @param list
	 */
	public void refreshData(List<ShoppingCar> list){
		
		//清空老数据
		shoppingCarList.clear();
		productMap.clear();
		
		
		//将数据添加到主列表
		if(null != list && list.size() > 0){
			String tempStoreId = null;
			for(ShoppingCar shopcart : list){
				
				//将数据加入到对应的集合
				tempStoreId = shopcart.getId()+"";
				shoppingCarList.add(shopcart);
				productMap.put(tempStoreId, shopcart.getCarItem());
			}
			
			//比对出购物车的老数据，并将其删除(selectMap的老数据)
			List<String> oldList = getDiffrentData(selectMap.keySet(), productMap.keySet());
			for(String oldStoreId : oldList){
				selectMap.remove(oldStoreId);
			}
			
		}else{
			
			//没有数据，则将选中的数据也清空
			selectMap.clear();
		}
	}
	
	/**
	 * 比对两个集合的差异数据，抽取出oldData里面的差异数据
	 *
	 * @param oldData
	 * @param newData
	 * @return
	 */
	private List<String> getDiffrentData(Set<String> oldData, Set<String> newData){
		List<String> difList = new ArrayList<String>();
		
		if(null != oldData && null != newData){
			for(String oldKey : oldData){
				
				boolean include = false;
				for(String newKey : newData){
					if(oldKey.equals(newKey)){
						include = true;
						break;
					}
				}
				if(!include){
					difList.add(oldKey);
				}
			}
		}
		return difList;
	}
	
   
	/**
	 * 移除被删除的商品
	 * @param shopping_ids
	 */
	public void removeDeletedProduct() {
		if (null != selectMap && selectMap.size() > 0) {
			for(String storeId : selectMap.keySet()){
				removeDeletedProduct(storeId, selectMap.get(storeId));
			}
			
			//清空选中的数据
			selectMap.clear();
		}
	}
    
	/**
	 * 移除店铺里面被删除的商品
	 * @param deletedShoppingIds
	 */
	private void removeDeletedProduct(String storeId, List<String> deletedShoppingIds) {
		if (null != storeId && null!= deletedShoppingIds && deletedShoppingIds.size() > 0) {
			
			//获取店铺的所有商品
			List<CarItem> productList = productMap.get(storeId);
			
			CarItem product = null;
			for(int i=0; i<productList.size(); i++){
				
				product = productList.get(i);
				//判断商品有没有被移除
				if(hasId(deletedShoppingIds, product.getId()+"")){
					
					//因为是引用类型，只需要移除 productList的数据，productMap的数据也会跟着移除，
					//同样shoppingCarList的数据也会移除
					productList.remove(product);
					i--;
				}
			}
		}
	}
	
	private boolean hasId(List<String> ids, String newid){
		if(null == ids || null == newid){
			return false;
		}
		
		for(String id : ids){
			if(id.equals(newid)){
				return true;
			}
		}
		return false;
	}
	
	/**
     * 获取选中的商品
     * @return
     */
	public String getCheckedPorductShoppingIds() {
		
		StringBuffer sb = new StringBuffer();
		if(null != selectMap && selectMap.size() > 0){
			
			List<String> shoppingIds = null;
			
			//循环所有选中店铺
			for(String storeId : selectMap.keySet()){
				
				shoppingIds = selectMap.get(storeId);
				if(null != shoppingIds && shoppingIds.size() > 0){
					
					//循环店铺，所有选中商品
					for(String shoppingId : shoppingIds)
					if(0 == sb.length()){
						sb.append(shoppingId);
					}else{
						sb.append((","+shoppingId));
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 获取店铺选中的购物车id
	 * @param storeId
	 * @return
	 */
	public List<String> getStoreCheckedShoppingIds(String storeId) {
		return selectMap.get(storeId);
	}
	
	/**
	 * 获取店铺的商品总数量
	 * @param storeId
	 * @return
	 */
	private int getStoreCheckedProductCount(String storeId){
		if(TextUtils.isEmpty(storeId)){
			return 0;
		}
		
		if(!selectMap.containsKey(storeId)){
			return 0;
		}
		
		if(null == selectMap.get(storeId)){
			return 0;
		}
		
		return selectMap.get(storeId).size();
	}
	
	
	/**
	 * 获取店铺的商品总数量
	 * @param storeId
	 * @return
	 */
	private int getStoreProductCount(String storeId){
		if(TextUtils.isEmpty(storeId)){
			return 0;
		}
		
		if(!productMap.containsKey(storeId)){
			return 0;
		}
		
		if(null == productMap.get(storeId)){
			return 0;
		}
		
		return productMap.get(storeId).size();
	}
	
	/**
	 * 构建storeId选中的数据，防止空指针
	 * @param storeId
	 */
	private void buildCheckStore(String storeId){
		if(!selectMap.containsKey(storeId)){
			selectMap.put(storeId, new ArrayList<String>());
		}
	}
	
	/**
	 * 判断是否为全选
	 * @return
	 */
	public boolean isCheckAll() {
		for(String storeId : productMap.keySet()){
			if(!isCheckStore(storeId)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断店铺是否已经选中
	 * @param string
	 * @return
	 */
	public boolean isCheckStore(String storeId) {
		
		//构建空数据
		buildCheckStore(storeId);
		
		//店铺选中商品的数量，店铺里面的商品总数量
		int selectPCount = getStoreCheckedProductCount(storeId);
		int pCount = getStoreProductCount(storeId);
		
		//店铺选中依据，选中的数量==店铺里面的商品总数量
		if(selectPCount > 0 && pCount > 0 && selectPCount == pCount){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断商品有没有被选中
	 * @param shoppingId
	 * @param storeId
	 * @return
	 */
	public boolean isCheckProduct(String shoppingId, String storeId){
		
		//构建空数据
		buildCheckStore(storeId);
		
		//获取店铺里面选中的商品集合
		List<String> checkedProducts = selectMap.get(storeId);
		
		//看选中的商品里面是否包含要判断的商品
		return checkedProducts.contains(shoppingId);
	}
	
	/**
     * 全选，或反选
     * @param check
     */
	public void checkedAll(boolean check) {
		if(check){
			//全选-选中所有店铺即可
			for(String storeId : productMap.keySet()){
				checkStore(storeId, true);
			}
			
		}else{
			//反选
			selectMap.clear();
		}
	}
	
	/**
	 * 选中店铺，或反选店铺
	 * @param storeId
	 */
	public void checkStore(String storeId, boolean checked){
		
		//构建空数据
		buildCheckStore(storeId);
		
		if(checked){
			
			//没有选中，做选中操作
			List<CarItem> productList = productMap.get(storeId);
			selectMap.get(storeId).clear();
			
			for(CarItem product : productList){
				
				//将购物车id加入集合
				selectMap.get(storeId).add(product.getId()+"");
			}
			
		}else{
			
			
			
			
			//反选
			selectMap.get(storeId).clear();
		}
	}
	
	/**
	 * 选中商品，或反选商品
	 * @param shoppingId 购物车id
	 * @param storeId	店铺id 
	 * @param check		true则选中商品，false不选中商品
	 */
	public void checkProduct(String shoppingId, String storeId, boolean check){
		
		//构建空数据
		buildCheckStore(storeId);
		
		//添加到集合
		if(check && !selectMap.get(storeId).contains(shoppingId)){
			selectMap.get(storeId).add(shoppingId);
			return;
		}
		
		//从集合里面移除
		if(!check && selectMap.get(storeId).contains(shoppingId)){
			selectMap.get(storeId).remove(shoppingId);
		}
	}
	
	/**
	 * 根据店铺，和购物车Id返回一个商品
	 * @param storeId
	 * @param shoppingId
	 * @return
	 */
	public CarItem getProduct(String storeId, String shoppingId){
		if(null != storeId && null != shoppingId){
			List<CarItem> productList = productMap.get(storeId);
			if(null != productList && productList.size() > 0){
				for(CarItem product : productList){
					if(shoppingId.equals(product.getId()+"")){
						return product;
					}
				}
			}
		}
		return null;
	}
	
	/**
     * 加载购物车总价
     * @return
     */
    public double loadCheckedProductTotalMoney() {
    	
    	//获取购物车的所有商品
    	double allPrice = 0.00;
    	
    	List<String> shoppindIds = null;
    	for(String storeId : selectMap.keySet()){
    		
    		shoppindIds = selectMap.get(storeId);
    		CarItem product = null;
    		for(String shoppingId : shoppindIds){
    			
    			//获得商品
    			product = getProduct(storeId, shoppingId);
    			if(null != product){
    				double price = product.getCurprice();
    				int num = product.getNum();
    				allPrice += (price * num);
    			}
    		}
    	}
        return allPrice;
    }

    /**
     * 修改购物车商品数量
     * @param storeId
     * @param shopping_id
     * @param num
     */
	public void updateProductCount(String storeId, String shoppingId, int num) {
		if(null != storeId && null != shoppingId && num > 0){
			List<CarItem> productList = productMap.get(storeId);
			for(CarItem product : productList){
				if(shoppingId.equals(product.getId()+"")){

					//修改商品数量
					product.setNum(num);
				}
			}
		}
	}

}
