package com.baosteel.qcsh.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库管理类，负责数据库的创建、版本更新和相关数据操作（增、删、改、查）
 * 
 * @version [版本号, 2015-08-28]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 * @author 刘远祺
 */
public class DatabaseManager {

	// 数据库代理类实例
	public static DatabaseHelper openDataBaseHelper = null;
	
	//private static final String DB_NAME 				= "/sdcard/baogang/baogang.db";
	private static final String DB_NAME 				= "baogang.db";

	/**数据库版本号**/
	private static final int DB_VERSION 				= 1;

	
	
	
	
	
	/****************************************表名 start********************************************/
	
	/**商品表**/
	public static final String TABLE_PRODUCT 			= "product";		//商品表
	
	/**搜索记录**/
	public static final String TABLE_SEARCH				= "search";			//搜索记录
	
	/****************************************表名 end********************************************/
	
	
	
	
	
	
	
	
	/****************************************表字段 start********************************************/
	
	/**
	 * 商品表字段
	 * 
	 * @author liuyuanqi
	 * 
	 */
	public static final class ProductColumns {
		public static final String 	ID	 				= "_id";			// 本地自增ID
		public static final String 	PID	 				= "productId";		// 商品ID
		public static final String 	NAME 				= "Name";			// 商品名称
	}

	/**
	 * 搜索记录表字段
	 * 
	 * @author liuyuanqi
	 * 
	 */
	public static final class SearchColumns {
		public static final String 	ID	 				= "_id";			// 本地自增ID
		public static final String 	NAME 				= "Name";			// 搜索名称
	}
	
	/****************************************表字段 end********************************************/
	
	
	
	
	/**
	 * 数据库代理类，负责数据库的创建和版本更新
	 * 
	 * @version [版本号, 2014-09-24]
	 * @see [相关类/方法]
	 * @since [产品/模块版本]
	 */
	public static class DatabaseHelper extends SQLiteOpenHelper {
		private SQLiteDatabase database;
		private Context context;

		public SQLiteDatabase getDatabase() {
			return this.database;
		}

		@Override
		public synchronized SQLiteDatabase getWritableDatabase() {
			if (this.database == null) {
				this.database = super.getWritableDatabase();
			}
			return this.database;
		}

		@Override
		public synchronized SQLiteDatabase getReadableDatabase() {
			this.database = super.getReadableDatabase();
			return database;
		}

		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			this.context = context;
			this.database = null;
		}

		public void CloseDatabase() {
			if (this.database != null && this.database.isOpen()) {
				this.database.close();
				this.database = null;
			}
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			StringBuffer stringBuffer = null;

			//商品表
			stringBuffer = new StringBuffer();
			stringBuffer.append("create table if not exists ").append(TABLE_PRODUCT)
			.append(" (").append(ProductColumns.ID)
			.append(" integer primary key autoincrement,")
			.append(ProductColumns.PID).append(" varchar(50),")
			.append(ProductColumns.NAME).append(" varchar(50))");
			db.execSQL(stringBuffer.toString());
			
			//搜索记录表
			stringBuffer = new StringBuffer();
			stringBuffer.append("create table if not exists ").append(TABLE_SEARCH)
			.append(" (").append(SearchColumns.ID)
			.append(" integer primary key autoincrement,")
			.append(SearchColumns.NAME).append(" varchar(50))");
			db.execSQL(stringBuffer.toString());
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			//数据库升级
			
		}
	}

	/**
	 * 初始化数据库代理类
	 * 
	 * @param [context] [context 应用上下文]
	 * @return [DatabaseHelper 数据库代理类]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public synchronized static DatabaseHelper getDataBaseHelper(Context context) {
		if (null == openDataBaseHelper) {
			openDataBaseHelper = new DatabaseHelper(context.getApplicationContext());
		}
		return openDataBaseHelper;
	}

	/**
	 * 获得可写的Database对象
	 * 
	 * @param context
	 * @return
	 */
	public static SQLiteDatabase getWriteableDatabase(Context context) {
		SQLiteDatabase database = null;
		DatabaseHelper helper = getDataBaseHelper(context);
		database = helper.getWritableDatabase();
		return database;
	}
}