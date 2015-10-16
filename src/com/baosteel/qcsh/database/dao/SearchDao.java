package com.baosteel.qcsh.database.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baosteel.qcsh.database.DatabaseManager;
import com.baosteel.qcsh.database.DatabaseManager.SearchColumns;
import com.baosteel.qcsh.model.SearchItem;

/**
 * 搜索记录DAO
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-28
 */
public class SearchDao {
	
	/**
	 * 添加一个商品记录
	 * @param context	上下文
	 * @param SearchItem	商品
	 * @return id		本条记录在数据库自增的ID
	 */
	public static long addSearchItem(Context context, String name){
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		ContentValues values = new ContentValues();
		values.put(SearchColumns.NAME, name);
		long id = db.insert(DatabaseManager.TABLE_SEARCH, null, values);
		return id;
	}
	
	/**
	 * 删除一个商品
	 * @param context		上下文
	 * @param SearchItemId		商品ID
	 * @return boolean		true：删除成功，false：删除失败
	 */
	public static boolean deleteSearchItem(Context context, String name){
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		
		String where = SearchColumns.NAME + " = ?";
		String[] whereArgs = new String[]{ name };
		int deleteCount = db.delete(DatabaseManager.TABLE_SEARCH, where, whereArgs);
		
		return deleteCount > 0;
	}
	
	/**
	 * 删除一个商品
	 * @param context		上下文
	 * @param SearchItemId		商品ID
	 * @return boolean		true：删除成功，false：删除失败
	 */
	public static boolean deleteSearchItem(Context context){
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		int deleteCount = db.delete(DatabaseManager.TABLE_SEARCH, null, null);
		return deleteCount > 0;
	}
	
	
	/**
	 * 查找本地数据库所有商品
	 * @param context
	 * @return
	 */
	public static List<SearchItem> getSearchItemList(Context context){
		
		SQLiteDatabase db = DatabaseManager.getWriteableDatabase(context);
		Cursor cursor = db.query(DatabaseManager.TABLE_SEARCH, null, null, null, null, null, " _id desc ");
		
		List<SearchItem> list = new ArrayList<SearchItem>();
		while (cursor.moveToNext()) {
			list.add(new SearchItem(cursor));
		}
		cursor.close();
		
		return list;
	}
}
