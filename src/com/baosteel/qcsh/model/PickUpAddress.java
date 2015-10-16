package com.baosteel.qcsh.model;

import java.io.Serializable;

/**
 * Created by lenovo on 2015/10/1.
 * 自提点信息类
 */
public class PickUpAddress  implements Serializable{
    private String contact_tel;//手机号码

    private String serve_name;//自提点名称

    private String cityName;//市

    private String provinceName;//省

    private String serve_time;//服务时间

    private String remark_lat;//纬度

    private int city;//城市ID

    private int id;//自提点ID

    private String remark_lng;//精度

    private int area;//区域id

    private String areaName;//区域名称

    private int province;//省ID

    private int merchant_id;//商家ID

    private String street_address;//详细地址

    private boolean ischeck;//默认选中的自提点

    public boolean ischeck() {
        return ischeck;
    }

    public PickUpAddress setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
        return this;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public PickUpAddress setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
        return this;
    }

    public String getServe_name() {
        return serve_name;
    }

    public PickUpAddress setServe_name(String serve_name) {
        this.serve_name = serve_name;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public PickUpAddress setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public PickUpAddress setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public String getServe_time() {
        return serve_time;
    }

    public PickUpAddress setServe_time(String serve_time) {
        this.serve_time = serve_time;
        return this;
    }

    public String getRemark_lat() {
        return remark_lat;
    }

    public PickUpAddress setRemark_lat(String remark_lat) {
        this.remark_lat = remark_lat;
        return this;
    }

    public int getCity() {
        return city;
    }

    public PickUpAddress setCity(int city) {
        this.city = city;
        return this;
    }

    public int getId() {
        return id;
    }

    public PickUpAddress setId(int id) {
        this.id = id;
        return this;
    }

    public String getRemark_lng() {
        return remark_lng;
    }

    public PickUpAddress setRemark_lng(String remark_lng) {
        this.remark_lng = remark_lng;
        return this;
    }

    public int getArea() {
        return area;
    }

    public PickUpAddress setArea(int area) {
        this.area = area;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public PickUpAddress setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public int getProvince() {
        return province;
    }

    public PickUpAddress setProvince(int province) {
        this.province = province;
        return this;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public PickUpAddress setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
        return this;
    }

    public String getStreet_address() {
        return street_address;
    }

    public PickUpAddress setStreet_address(String street_address) {
        this.street_address = street_address;
        return this;
    }
}
