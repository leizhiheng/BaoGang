package com.baosteel.qcsh.model;

import android.database.Cursor;
import android.util.Log;

import com.baosteel.qcsh.database.DatabaseManager.SearchColumns;

/**
 * 
 * @description 搜索页面中 最近搜索和热门搜索项
 * @author blue
 * @Time 2015-9-2
 *
 */
public class SearchItem implements Comparable<SearchItem>{

	private String mTitle;
	private long mTime;
	
	public SearchItem(Cursor cursor){
		this.mTitle = cursor.getString(cursor.getColumnIndex(SearchColumns.NAME));
	}
	
	public SearchItem(String title) {
		mTitle = title;
	}
	
	public SearchItem(String title, long time) {
		mTitle = title;
		mTime = time;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	
	public long getmTime() {
		return mTime;
	}

	public void setmTime(long mTime) {
		this.mTime = mTime;
	}

	@Override
	public int compareTo(SearchItem another) {
		
		Log.d("SearchItem", "this.mTime = "+this.mTime+", anothor time = "+another.getmTime());
		if ((this.mTime > another.getmTime())) {
			Log.i("SearchItem", " > 0");
			return -1;
		} else {
			Log.i("SearchItem", " <= 0");
			return 1;
		}
	}
	
	
}
