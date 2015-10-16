package com.baosteel.qcsh.model;

import java.io.Serializable;

/**
 * 常用房屋
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-17
 */
public class CommonHouse implements Serializable{
	
	/**姓名**/
	private String name;

	public CommonHouse(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
