package com.baosteel.qcsh.model;

import java.util.List;

import android.text.TextUtils;

/**
 * 
 * @description 我的订单 列表中的订单项
 * @author blue
 * @Time 2015-9-21
 * 
 */
public class OrderItem {
//	{
//		"type": 1/0,
//		"msg": "成功",失败原因 
//	returnMap：[{
//	  orderId 订单Id
//	  orderCode 订单编号
//	  orderType 订单类型（1，实物订单，2 服务订单）
//	  sellerId 商家Id
//	  sellerName 商家名称
//	  payMethod 支付方式(<1>支付宝<2>健康点<3>微信<4>银联<5>财付通<6>账号余额)
//	  orderAllPrice 订单总价
//	  payPrice 支付价格
//	  status 订单状态
//	  statusName //订单状态名字
//	  goodsNum 订单商品总数
//	  goodsList //商品列表
//	  {
//	    goodsId 商品编号
//	    goodsImg 商品图片
//	goodsName 商品名称
//	goodsPrice 商品价格
//	goodsSn 商品内码
//	goodsSpec 商品描述
//	goodsSum 商品数量
//	goodsType 商品类型
//	numberLogistics 物流单号
//	logisticsId 物流公司ID
//	payPrice 支付价格
//	  }
//	｝]
//	／／加密处理。
//	serverTime： 服务器时间。
//	key : 加密key
//		
//	}
	
	public OrderItem() {
	}
	
	public OrderItem(String statu) {
		this.status = statu;
	}

	private String payPrice;
	private String orderAllPrice;
	private String status;
	private String orderType;
	private String orderCode;
	private String statusName;
	private String sellerId;
	private String sellerName;
	private String goodsNum;
	private String orderId;
	private String payMethod;

	private List<OrderProduct> goodsList;

	public String getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}

	public String getOrderAllPrice() {
		return orderAllPrice;
	}

	public void setOrderAllPrice(String orderAllPrice) {
		this.orderAllPrice = orderAllPrice;
	}

	public String getStatus() {
		if(TextUtils.isEmpty(status)){
			return "0";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public List<OrderProduct> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<OrderProduct> goodsList) {
		this.goodsList = goodsList;
	}

}
