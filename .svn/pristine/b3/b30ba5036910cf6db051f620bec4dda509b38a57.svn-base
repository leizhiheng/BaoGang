package com.baosteel.qcsh.model;

import android.text.TextUtils;

/**
 * Created by jws on 2015/9/25.
 * 用户信息
 */
public class UserInfo {
    /**
     * 用户名
     **/
    private String userName;
    /**
     * 等级名称
     **/
    private String gradeName;
    /**
     * 用户头像url
     **/
    private String headUrl;
    /**
     * 帐户余额
     **/
    private String accountCurrency;
    /**
     * 礼品卡
     **/
    private String gifCard;
    /**
     * 优惠券
     **/
    private String coupon;
    /**
     * 健康点
     **/
    private String healthValue;
    
    /**
     * 健康点
     **/
    private String score;
    
    /**
     * 收藏的商品
     **/
    private String collectGoods;
    /**
     * 收藏的店铺
     **/
    private String collectShops;
    
    
    /**足迹数量**/
    private String userLookNum;
    
    
    /**
     * 待审核
     **/
    private String waitAudit;
    /**
     * 待付款
     **/
    private String waitPay;
    /**
     * 待发货
     **/
    private String waitDeliver;
    /**
     * 待收货
     **/
    private String waitReceive;
    /**
     * 待评价
     **/
    private String waitEvaluate;
    /**
     * 退款/换货
     **/
    private String refundAndBarter;
    /**
     * 用户手机号
     **/
    private String phone;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 身份证号
     **/
    private String identityCard;
    /**
     * 昵称
     **/
    private String nickName;
    /**
     * 性别
     **/
    private String sex;
    /**
     * 版本更新提示,1表示有可用更新 0 无可用更新
     **/
    private String versionUpdate;

    private int payPasswordGrade;//1.低  2.中  3.高

    public String getVersionUpdate() {
        return versionUpdate;
    }

    public UserInfo setVersionUpdate(String versionUpdate) {
        this.versionUpdate = versionUpdate;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getGradeName() {
        return gradeName;
    }

    public UserInfo setGradeName(String gradeName) {
        this.gradeName = gradeName;
        return this;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public UserInfo setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
        return this;
    }

    public String getAccountCurrency() {
    	if(TextUtils.isEmpty(accountCurrency)){
    		return  "0.00";
    	}
        return accountCurrency;
    }

    public UserInfo setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
        return this;
    }

    public String getGifCard() {
    	if(TextUtils.isEmpty(gifCard)){
    		return  "0.00";
    	}
        return gifCard;
    }

    public UserInfo setGifCard(String gifCard) {
        this.gifCard = gifCard;
        return this;
    }

    public String getCoupon() {
    	if(TextUtils.isEmpty(coupon)){
    		return  "0";
    	}
        return coupon;
    }

    public UserInfo setCoupon(String coupon) {
        this.coupon = coupon;
        return this;
    }

    public String getHealthValue() {
    	if(TextUtils.isEmpty(healthValue)){
    		return "0.00";
    	}
        return healthValue;
    }

    public void setHealthValue(String healthValue) {
        this.healthValue = healthValue;
    }

    public String getCollectGoods() {
    	if(TextUtils.isEmpty(collectGoods)){
    		return "0";
    	}
        return collectGoods;
    }

    public UserInfo setCollectGoods(String collectGoods) {
        this.collectGoods = collectGoods;
        return this;
    }

    public String getCollectShops() {
    	if(TextUtils.isEmpty(collectShops)){
    		return "0";
    	}
        return collectShops;
    }

    public UserInfo setCollectShops(String collectShops) {
        this.collectShops = collectShops;
        return this;
    }

    public int getWaitAudit() {
    	try{
    		return Integer.parseInt(waitAudit);
    	}catch(Exception e){
    		return 0;
    	}
    }

    public UserInfo setWaitAudit(String waitAudit) {
        this.waitAudit = waitAudit;
        return this;
    }

    public int getWaitPay() {
    	try{
    		return Integer.parseInt(waitPay);
    	}catch(Exception e){
    		return 0;
    	}
    }

    public UserInfo setWaitPay(String waitPay) {
        this.waitPay = waitPay;
        return this;
    }

    public int getWaitDeliver() {
    	try{
    		return Integer.parseInt(waitDeliver);
    	}catch(Exception e){
    		return 0;
    	}
    }

    public UserInfo setWaitDeliver(String waitDeliver) {
        this.waitDeliver = waitDeliver;
        return this;
    }

    public int getWaitReceive() {
    	try{
    		return Integer.parseInt(waitReceive);
    	}catch(Exception e){
    		return 0;
    	}
    }

    public UserInfo setWaitReceive(String waitReceive) {
        this.waitReceive = waitReceive;
        return this;
    }

    public int getWaitEvaluate() {
    	try{
    		return Integer.parseInt(waitEvaluate);
    	}catch(Exception e){
    		return 0;
    	}
    }

    public UserInfo setWaitEvaluate(String waitEvaluate) {
        this.waitEvaluate = waitEvaluate;
        return this;
    }

    public int getRefundAndBarter() {
    	try{
    		return Integer.parseInt(refundAndBarter);
    	}catch(Exception e){
    		return 0;
    	}
    }

    public UserInfo setRefundAndBarter(String refundAndBarter) {
        this.refundAndBarter = refundAndBarter;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public UserInfo setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public UserInfo setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public UserInfo setSex(String sex) {
        this.sex = sex;
        return this;
    }

	public String getUserLookNum() {
		return userLookNum;
	}

	public void setUserLookNum(String userLookNum) {
		this.userLookNum = userLookNum;
	}

	public String getScore() {
		if(TextUtils.isEmpty(score)){
			return "0";
		}
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

    public int getPayPasswordGrade() {
        return payPasswordGrade;
    }

    public String getPayPasswordGrade2Chinese(){
        String grade="";
        switch (payPasswordGrade){
            case 1:
                grade="低";
                break;
            case 2:
                grade="中";
                break;
            case 3:
                grade="高";
                break;
        }
        return "".equals(grade)?null:grade;
    }

    public void setPayPasswordGrade(int payPasswordGrade) {
        this.payPasswordGrade = payPasswordGrade;
    }
}
