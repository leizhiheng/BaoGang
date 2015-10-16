package com.baosteel.qcsh.model;

public class PictureData {

	private String url;
	private int resId;
	
	public PictureData(String url, int id) {
		this.url = url;
		this.resId = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}
	
}
