package com.baosteel.qcsh.model.store;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺商品数据
 * Created by kuangyong on 15/9/29.
 */
public class QueryAppStoreNewListBean implements Serializable{

    /**
     * returnMap : [{"goods_sn":"1","price":200,"name":"BOSS 薄毛衣","goods_id":85,"img":"http://d1-img.java.shovesoft.com/flie_upload/image/20150926/20150926163317_883.png","merchant_id":33},{"goods_sn":"1","price":3,"name":"123","goods_id":86,"img":"http://127.0.0.1:8080/baosteel_upload/flie_upload/image/20150926/20150926165143_627.jpeg","merchant_id":33},{"goods_sn":"4","price":250,"name":"双达","goods_id":87,"img":"http://d1-img.java.shovesoft.com/flie_upload/image/20150926/20150926164104_879.png","merchant_id":33},{"goods_sn":"5","price":250,"name":"双达","goods_id":87,"img":"http://d1-img.java.shovesoft.com/flie_upload/image/20150926/20150926164104_879.png","merchant_id":33}]
     * type : 1
     * msg : 成功.
     */

    private String type;
    private String msg;
    private List<ReturnMapEntity> returnMap;

    public void setType(String type) {
        this.type = type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setReturnMap(List<ReturnMapEntity> returnMap) {
        this.returnMap = returnMap;
    }

    public String getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public List<ReturnMapEntity> getReturnMap() {
        return returnMap;
    }

    public static class ReturnMapEntity {
        /**
         * goods_sn : 1
         * price : 200
         * name : BOSS 薄毛衣
         * goods_id : 85
         * img : http://d1-img.java.shovesoft.com/flie_upload/image/20150926/20150926163317_883.png
         * merchant_id : 33
         */

        private String goods_sn;                            //商品规格id
        private String price;                               //价格
        private String name;                                //商品名称
        private String goods_id;                            //商品id
        private String img;                                 //商品图片
        private String merchant_id;                         //店铺id

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public String getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public String getImg() {
            return img;
        }

        public String getMerchant_id() {
            return merchant_id;
        }
    }
}
