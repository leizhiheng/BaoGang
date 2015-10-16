package com.baosteel.qcsh.model;

import java.util.List;

/**
 * 
 * @description 筛选页面中的属性列表和属性值列表，公用一个数据结构
 * @author blue
 * @Time 2015-9-25
 * 
 */
public class FilterGridItem {
	// id:属性名id，
	// name：名称，
	// is_required：是否必选（1：必选；0：非必须），
	// opt_type：选择（1：单选；0：多选）
	private String id;
	private String name;
	private String is_required;
	private String opt_type;
	
	private String conditionValue;
	
	/**
	 * 属性值列表中的值
	 */
	private String value;
	
	/**
	 * 某个属性下的属性值集合（即某个属性的筛选结果）
	 */
	private List<FilterGridItem> mAttrValues;

	/**
	 * 用于表示该选项是否被选中
	 */
	private boolean isSelected;

	public FilterGridItem(String name) {
		this.name = name;
		this.value = name;
	}

	public FilterGridItem(String id, String name, String is_required,
			String opt_type) {
		this.id = id;
		this.name = name;
		this.is_required = is_required;
		this.opt_type = opt_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIs_required() {
		return is_required;
	}

	public void setIs_required(String is_required) {
		this.is_required = is_required;
	}

	public String getOpt_type() {
		return opt_type;
	}

	public void setOpt_type(String opt_type) {
		this.opt_type = opt_type;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	public List<FilterGridItem> getmAttrValues() {
		return mAttrValues;
	}

	public void setmAttrValues(List<FilterGridItem> mAttrValues) {
		this.mAttrValues = mAttrValues;
	}
	
}
