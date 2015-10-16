package com.baosteel.qcsh.model;

import java.io.Serializable;

/**
 * 常用车辆
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-17
 */
public class CommonCar implements Serializable{
	
	/**车牌号**/
	private String number;

	/**车牌类型**/
	private String type;
	
	/**车型品牌**/
	private String brand;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
