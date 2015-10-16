package com.baosteel.qcsh.database.adress;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public static final String TAG = "DWSqliteUtils";
	public static SQLiteDatabase mDatabase;
	private static final String dbName = "city_baogang.db";
	private static final String tableName = "t_city";
	private static final int VERSION = 1;
	public static String parentId = "parentid";
	public static String areaName = "name";
	public static String level = "rank";

	private static final String VALUES = "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ parentId
			+ " INTEGER, "
			+ areaName
			+ " TEXT, "
			+ level
			+ " INTEGER) ";

	private static final String CREATE_DB_ORDER = "CREATE TABLE IF NOT EXISTS "
			+ tableName + VALUES;

	public DBOpenHelper(Context context, CursorFactory factory) {
		super(context, dbName, factory, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_DB_ORDER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		ContentValues values = new ContentValues();
		db.update(tableName, values, null, null);
	}

}
