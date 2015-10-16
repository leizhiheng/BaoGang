package com.baosteel.qcsh.model;

/**
 * 
 * @description 取消订单理由
 * @author blue
 * @Time 2015-9-29
 * 
 */
public class CancelOrderReason {

	private String name;// 选项名称
	private String id;// 选项id
	private String sort;// 选项排序

	private boolean isSelected;

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
