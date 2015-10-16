package com.baosteel.qcsh.model;

public class TopbarMenuItem {

	private int iconId;
	private String title;
	private int tag;

	public TopbarMenuItem(int tag, String title, int iconId) {
		this.tag = tag;
		this.title = title;
		this.iconId = iconId;
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

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

}
