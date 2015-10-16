package com.baosteel.qcsh.database.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baosteel.qcsh.database.DatabaseManager;
import com.baosteel.qcsh.database.DatabaseManager.ProductColumns;
import com.baosteel.qcsh.database.bean.Product;

/**
 * 商品表DAO
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-28
 */
public class ProductDao {
	
	/**
	 * 添加一个商品记录
	 * @param context	上下文
	 * @param product	商品
	 * @return id		本条记录在数据库自增的ID
	 */
	public static long addProduct(Context context, Product product){
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		ContentValues values = new ContentValues();
		values.put(ProductColumns.PID, product.getId());
		values.put(ProductColumns.NAME, product.getName());
		// insert value into table
		long id = db.insert(DatabaseManager.TABLE_PRODUCT, null, values);
		return id;
	}
	
	/**
	 * 删除一个商品
	 * @param context		上下文
	 * @param productId		商品ID
	 * @return boolean		true：删除成功，false：删除失败
	 */
	public static boolean deleteProduct(Context context, String productId){
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		
		String where = ProductColumns.PID + " = ?";
		String[] whereArgs = new String[]{ productId };
		int deleteCount = db.delete(DatabaseManager.TABLE_PRODUCT, where, whereArgs);
		
		return deleteCount > 0;
	}
	
	
	/**
	 * 批量删除商品
	 * @param context		上下文
	 * @param productId		商品ID
	 * @return boolean		true：删除成功，false：删除失败
	 */
	public static boolean deleteProduct(Context context, String[] productIds) {
		
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from " + DatabaseManager.TABLE_PRODUCT);
		sql.append(" where "+ ProductColumns.PID +" in(");
		
		boolean tag = false;
		for (String id : productIds) {
			if(!tag){
				sql.append(""+id);
				tag = true;
			}else{
				sql.append(", "+id);
			}
		}
		sql.append(")");
		db.execSQL(sql.toString());
		return true;
	}
	
	/**
	 * 更新一个商品的数据
	 * @param context	上下文
	 * @param product	商品
	 * @return
	 */
	public static boolean updateProduct(Context context, Product product){
		
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		ContentValues values = new ContentValues();
		values.put(ProductColumns.NAME, product.getName());
		
		String whereClause = ProductColumns.PID + " = ? "; 
		String[] whereArgs = new String[]{ product.getId() };
		int updateRowsCount = db.update(DatabaseManager.TABLE_PRODUCT, values, whereClause, whereArgs);
		
		return updateRowsCount > 0;
	}
	
	/**
	 * 查找本地数据库所有商品
	 * @param context
	 * @return
	 */
	public static List<Product> getProductList(Context context){
		
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		Cursor cursor = db.query(DatabaseManager.TABLE_PRODUCT, null, null, null, null, null, null);
		
		List<Product> list = new ArrayList<Product>();
		while (cursor.moveToNext()) {
			list.add(new Product(cursor));
		}
		cursor.close();
		
		return list;
	}
}
