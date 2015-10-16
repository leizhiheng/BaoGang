package com.baosteel.qcsh.model;

public class HealthyLiveInPerson {

	private String name;
	private String sex;
	private String identityCode;

	public HealthyLiveInPerson(String name, String sex, String identityCode) {
		this.name = name;
		this.sex = sex;
		this.identityCode = identityCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

}
