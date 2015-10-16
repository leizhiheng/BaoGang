package com.baosteel.qcsh.model.store;

import java.io.Serializable;

/**
 * 店铺主要信息
 * Created by kuangyong on 15/9/29.
 */
public class QueryAppMerchantInfoBean implements Serializable{


    /**
     * returnMap : {"id":33,"logo":"http://d1-img.java.shovesoft.com/flie_upload/image/20150925/20150925113039_973.jpg","newGoodsCount":0,"attentionCount":1,"merchant_name":"ccc","goodsCount":0,"background_img_app":"http://d1-img.java.shovesoft.com/flie_upload/image/20150925/20150925111413_304.jpg"}
     * type : 1
     * msg : 成功.
     */

    private ReturnMapEntity returnMap;
    private String type;
    private String msg;

    public void setReturnMap(ReturnMapEntity returnMap) {
        this.returnMap = returnMap;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ReturnMapEntity getReturnMap() {
        return returnMap;
    }

    public String getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public static class ReturnMapEntity implements Serializable{
        /**
         * id : 33
         * logo : http://d1-img.java.shovesoft.com/flie_upload/image/20150925/20150925113039_973.jpg
         * newGoodsCount : 0
         * attentionCount : 1
         * merchant_name : ccc
         * goodsCount : 0
         * background_img_app : http://d1-img.java.shovesoft.com/flie_upload/image/20150925/20150925111413_304.jpg
         */

        private String id;                                  //商家编号
        private String logo;                                //商家LOGO
        private String newGoodsCount;                       //新品数量
        private String attentionCount;                      //关注数量
        private String merchant_name;                       //商家名称
        private String goodsCount;                          //商品总数
        private String background_img_app;                  //背景图片url

        public void setId(String id) {
            this.id = id;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public void setNewGoodsCount(String newGoodsCount) {
            this.newGoodsCount = newGoodsCount;
        }

        public void setAttentionCount(String attentionCount) {
            this.attentionCount = attentionCount;
        }

        public void setMerchant_name(String merchant_name) {
            this.merchant_name = merchant_name;
        }

        public void setGoodsCount(String goodsCount) {
            this.goodsCount = goodsCount;
        }

        public void setBackground_img_app(String background_img_app) {
            this.background_img_app = background_img_app;
        }

        public String getId() {
            return id;
        }

        public String getLogo() {
            return logo;
        }

        public String getNewGoodsCount() {
            return newGoodsCount;
        }

        public String getAttentionCount() {
            return attentionCount;
        }

        public String getMerchant_name() {
            return merchant_name;
        }

        public String getGoodsCount() {
            return goodsCount;
        }

        public String getBackground_img_app() {
            return background_img_app;
        }
    }
}
