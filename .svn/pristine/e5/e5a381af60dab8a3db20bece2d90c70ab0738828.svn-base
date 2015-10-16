package com.baosteel.qcsh.model;

import java.util.List;

import com.common.net.uploadimg.ImageData;

/**
 * Created by lenovo on 2015/10/1.
 * 配送方式数据源
 */
public class DeliverMethod {
	
	
    private DeliverAddress address;

    /**商品列表**/
    private List<DeliverGoods> goodsImg ;

    /**运费**/
    private int shipment;
    
    /**类型（1，快递）（2自提） （1，2 快递和自提都有**/
    private String invoiceType;

    private int type = 1;//1、送货上门 2、上门自提、3、不操作

    public int getType() {
        return type;
    }

    public DeliverMethod setType(int type) {
        this.type = type;
        return this;
    }

    public List<DeliverGoods> getGoodsImg() {
        return goodsImg;
    }

    public DeliverMethod setGoodsImg(List<DeliverGoods> goodsImg) {
        this.goodsImg = goodsImg;
        return this;
    }


    public DeliverAddress getAddress() {
        return address;
    }

    public String getDetailAddress(){
    	if(null != address){
    		return address.getProvinceName() + address.getCityName() + address.getAreaName() + address.getStreet_address();
    	}
    	return "";
    }
    
    public DeliverMethod setAddress(DeliverAddress address) {
        this.address = address;
        return this;
    }

    public int getShipment() {
        return shipment;
    }

    public DeliverMethod setShipment(int     shipment) {
        this.shipment = shipment;
        return this;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public DeliverMethod setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }
}
