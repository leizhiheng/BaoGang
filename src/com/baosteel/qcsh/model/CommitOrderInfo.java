package com.baosteel.qcsh.model;

/**
 * Created by lenovo on 2015/10/1.
 */
public class CommitOrderInfo {

    private String seller_id;  //商家id
    private String address_id; //收货地址id
    private String user_id; //用户id
    private String order_type; //订单类型 （1实物订单，2服务订单）
    private String shopping_id; //购物车id（多个，逗号分割）
    private DeliverCommit distributiondist;// 配送方式
    private String serviceTime; //服务时间（订单类型为2时要传）

    public String getSeller_id() {
        return seller_id;
    }

    public CommitOrderInfo setSeller_id(String seller_id) {
        this.seller_id = seller_id;
        return this;
    }

    public String getAddress_id() {
        return address_id;
    }

    public CommitOrderInfo setAddress_id(String address_id) {
        this.address_id = address_id;
        return this;
    }

    public String getUser_id() {
        return user_id;
    }

    public CommitOrderInfo setUser_id(String user_id) {
        this.user_id = user_id;
        return this;
    }

    public String getOrder_type() {
        return order_type;
    }

    public CommitOrderInfo setOrder_type(String order_type) {
        this.order_type = order_type;
        return this;
    }

    public String getShopping_id() {
        return shopping_id;
    }

    public CommitOrderInfo setShopping_id(String shopping_id) {
        this.shopping_id = shopping_id;
        return this;
    }

    public DeliverCommit getDistributiondist() {
        return distributiondist;
    }

    public CommitOrderInfo setDistributiondist(DeliverCommit distributiondist) {
        this.distributiondist = distributiondist;
        return this;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public CommitOrderInfo setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
        return this;
    }
}
