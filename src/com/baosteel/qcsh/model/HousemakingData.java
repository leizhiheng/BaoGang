package com.baosteel.qcsh.model;

public class HousemakingData {

	private String title;
	private int iconId;
	private int bgDrawableId;

	public HousemakingData(String title, int iconId, int bgDrawableId) {
		this.title = title;
		this.iconId = iconId;
		this.bgDrawableId = bgDrawableId;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBgDrawableId() {
		return bgDrawableId;
	}

	public void setBgDrawableId(int bgDrawableId) {
		this.bgDrawableId = bgDrawableId;
	}

}
