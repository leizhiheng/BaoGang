package com.baosteel.qcsh.database.bean;

import org.json.JSONObject;

import com.baosteel.qcsh.database.DatabaseManager.ProductColumns;

import android.database.Cursor;

/**
 * 商品表数据模型
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-28
 */
public class Product {
	
	/**商品ID**/
	private String id;
	
	/**商品名称**/
	private String name;

	public Product(){
		
	}
	
	public Product(JSONObject obj){
		
	}
	
	public Product(Cursor cursor){
		this.id = cursor.getString(cursor.getColumnIndex(ProductColumns.PID));
		this.name = cursor.getString(cursor.getColumnIndex(ProductColumns.NAME));
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPrice() {
		// TODO Auto-generated method stub
		return "¥788";
	}
	
	
	
}
