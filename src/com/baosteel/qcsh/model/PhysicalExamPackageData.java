/**
 * @Description PhysicalExamPackageData.java
 * @Author blue
 * @Time 2015-9-15
 */
package com.baosteel.qcsh.model;

/**
 * @description TODO
 * @author blue
 * @Time 2015-9-15
 * 
 */
public class PhysicalExamPackageData {

	private String title;
	private int iconId;
	
	public PhysicalExamPackageData(String title, int iconId) {
		this.title = title;
		this.iconId = iconId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
	
}
