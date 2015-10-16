package com.baosteel.qcsh.model;

import java.io.Serializable;

/**
 * Created by lenovo on 2015/9/28.
 */
public class MemberReceiveAddressJson implements Serializable{
    private String id;//收货地址id
    private String userId;//当前登录用户id
    private String receiveUserRealName;//收货人真实姓名
    private String userTelephone;//联系人电话
    private String zipCode;//邮编
    private int provinceId;//省
    private int cityId;//市
    private int areaId;//区
    private String addressDetail;//街道详细地址
    private String provinceName;//省的名称
    private String cityName;//市的名称
    private String areaName;//区的名称
    private int isDefault;//是否为默认收货地址

    public String getMemberReceiveAddressId() {
        return id;
    }

    public MemberReceiveAddressJson setMemberReceiveAddressId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public MemberReceiveAddressJson setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getReceiveUserRealName() {
        return receiveUserRealName;
    }

    public MemberReceiveAddressJson setReceiveUserRealName(String receiveUserRealName) {
        this.receiveUserRealName = receiveUserRealName;
        return this;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public MemberReceiveAddressJson setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public MemberReceiveAddressJson setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public MemberReceiveAddressJson setProvinceId(int provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public int getCityId() {
        return cityId;
    }

    public MemberReceiveAddressJson setCityId(int cityId) {
        this.cityId = cityId;
        return this;
    }

    public int getAreaId() {
        return areaId;
    }

    public MemberReceiveAddressJson setAreaId(int areaId) {
        this.areaId = areaId;
        return this;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public MemberReceiveAddressJson setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
        return this;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public MemberReceiveAddressJson setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public MemberReceiveAddressJson setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public MemberReceiveAddressJson setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public MemberReceiveAddressJson setIsDefault(int isDefault) {
        this.isDefault = isDefault;
        return this;
    }
}
