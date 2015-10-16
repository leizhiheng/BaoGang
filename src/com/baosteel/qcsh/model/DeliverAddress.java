package com.baosteel.qcsh.model;

import com.baosteel.qcsh.utils.StringUtils;

/**
 * Created by jws on 2015/10/1.
 * 配送地址类
 */
public class DeliverAddress {
    private String contact_tel;//手机号

    private String cityName;//市名称

    private String serve_name;//服务点名称

    private String provinceName;//省名称

    private double remark_lat;//地图标注维度

    private String serve_time;// 服务时间

    private int city;//市id

    private double remark_lng;//地图标注经度

    private int id;//自提点id

    private int area;//区域id

    private String areaName;//区域名称

    private int province;//省id

    private String street_address;//详细地址

    private int merchant_id;//商家id

    public String getContact_tel() {
        return contact_tel;
    }

    public DeliverAddress setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
        return this;
    }

    public String getCityName() {
    	if(StringUtils.isEmpty(cityName)){
    		return "";
    	}
        return cityName;
    }

    public DeliverAddress setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getServe_name() {
        return serve_name;
    }

    public DeliverAddress setServe_name(String serve_name) {
        this.serve_name = serve_name;
        return this;
    }

    public String getProvinceName() {
    	if(StringUtils.isEmpty(provinceName)){
    		return "";
    	}
    	return provinceName;
    }

    public DeliverAddress setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public double getRemark_lat() {
        return remark_lat;
    }

    public DeliverAddress setRemark_lat(double remark_lat) {
        this.remark_lat = remark_lat;
        return this;
    }

    public String getServe_time() {
        return serve_time;
    }

    public DeliverAddress setServe_time(String serve_time) {
        this.serve_time = serve_time;
        return this;
    }

    public int getCity() {
        return city;
    }

    public DeliverAddress setCity(int city) {
        this.city = city;
        return this;
    }

    public double getRemark_lng() {
        return remark_lng;
    }

    public DeliverAddress setRemark_lng(double remark_lng) {
        this.remark_lng = remark_lng;
        return this;
    }

    public int getId() {
        return id;
    }

    public DeliverAddress setId(int id) {
        this.id = id;
        return this;
    }

    public int getArea() {
        return area;
    }

    public DeliverAddress setArea(int area) {
        this.area = area;
        return this;
    }

    public String getAreaName() {
    	if(StringUtils.isEmpty(areaName)){
    		return "";
    	}
        return areaName;
    }

    public DeliverAddress setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public int getProvince() {
        return province;
    }

    public DeliverAddress setProvince(int province) {
        this.province = province;
        return this;
    }

    public String getStreet_address() {
    	if(StringUtils.isEmpty(street_address)){
    		return "";
    	}
        return street_address;
    }

    public DeliverAddress setStreet_address(String street_address) {
        this.street_address = street_address;
        return this;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public DeliverAddress setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
        return this;
    }
}
