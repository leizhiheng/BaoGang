package com.baosteel.qcsh.model;

import java.io.Serializable;

/**
 * 常用人员信息
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-17
 */
public class CommonPerson implements Serializable{
	
	/**姓名**/
	private String name;
	
	/**英文名字**/
	private String enName;
	
	/**证件类型**/
	private String type;
	
	/**证件号码**/
	private String number;

	public CommonPerson(String name, String enName, String type, String number){
		this.name = name;
		this.enName = enName;
		this.type = type;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
