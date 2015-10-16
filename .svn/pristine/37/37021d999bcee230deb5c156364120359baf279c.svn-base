package com.baosteel.qcsh.model;

import java.io.Serializable;

import android.os.Parcelable;

/**
 * 
 * @description 一个基础的ListView Item填充数据对象，只包含id、name、iconUrl, iconResId4属性
 * @author blue
 * @Time 2015-9-2
 *
 */
public class BaseItemData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mID;
	private String mName;
	private String mIconUrl;
	private int mIconId;
	
	public BaseItemData(String name) {
		this.mName = name;
	}
	
	public BaseItemData(String id, String name) {
		this.mID = id;
		this.mName = name;
	}
	
	public BaseItemData(String id, String name, int iconId) {
		this.mID = id;
		this.mName = name;
		this.mIconId = iconId;
	}
	
	public BaseItemData(String id, String name, int iconId, String iconUrl) {
		this.mID = id;
		this.mName = name;
		this.mIconId = iconId;
		this.mIconUrl = iconUrl;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmIconUrl() {
		return mIconUrl;
	}

	public void setmIconUrl(String mIconUrl) {
		this.mIconUrl = mIconUrl;
	}

	public int getmIconId() {
		return mIconId;
	}

	public void setmIconId(int mIconId) {
		this.mIconId = mIconId;
	}
	
}
