package com.baosteel.qcsh.model;

public class HouseData {

	/**
	 * 房间名称
	 */
	private String longTitle;//长标题
	private String title;//房间名称
	private String price;
	private String releaseTime;// 发布时间
	private String rooms;// 厅室(eg:三室二厅一卫)
	private int area;// 面积
	private String simpleDesc;// 概况
	private String floor;// 楼层

	private String houseEstate;// 所在小区
	private String address;// 地址
	private String houseDesc;// 房源描述

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getSimpleDesc() {
		return simpleDesc;
	}

	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getHouseEstate() {
		return houseEstate;
	}

	public void setHouseEstate(String houseEstate) {
		this.houseEstate = houseEstate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouseDesc() {
		return houseDesc;
	}

	public void setHouseDesc(String houseDesc) {
		this.houseDesc = houseDesc;
	}

}
