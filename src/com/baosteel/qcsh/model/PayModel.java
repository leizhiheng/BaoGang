package com.baosteel.qcsh.model;

import android.os.Parcelable;
import android.os.Parcel;

/**
 * Created by jws on 2015/10/2.
 * 订单号生成类
 */
public class PayModel{
    private String number;
    private String orderId;

    public String getNumber() {
        return number;
    }

    public PayModel setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PayModel setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }


}
