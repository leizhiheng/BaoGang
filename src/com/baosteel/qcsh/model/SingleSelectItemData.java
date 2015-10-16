package com.baosteel.qcsh.model;

public class SingleSelectItemData {

	private String title;
	private int id;
	private boolean isSelected;

	public SingleSelectItemData(String title, int id) {
		this.title = title;
		this.id = id;
		this.isSelected = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
