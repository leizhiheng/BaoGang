package com.baosteel.qcsh.model;

import java.io.Serializable;
import java.util.List;

/**
 * 商品详情数据
 * Created by kuangyong on 15/9/25.
 */
public class QueryAppGoodsDetailBean implements Serializable{


    /**
     * returnMap : {"imgs":[{"img_url":"/file/image/20150910/20150910085646_122.jpeg","id":449},{"img_url":"/file/image/20150910/20150910085906_872.jpeg","id":450},{"img_url":"/file/image/20150910/20150910085911_231.jpeg","id":451},{"img_url":"/file/image/20150910/20150910085915_597.jpeg","id":452},{"img_url":"/file/image/20150910/20150910085930_409.jpg","id":453}],"description_score":0,"interests":0,"sellerProvince":"广东省","id":52,"month_sale_count":0,"price":1,"logistics_score":0,"pay_type":"","service_score":0,"name":"测试1","ad":"测试1","merchantName":"大四喜","sellerCity":"深圳市","merchantId":37,"goods_count":0}
     * type : 1
     * msg : 成功
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

    public static class ReturnMapEntity {
        /**
         * imgs : [{"img_url":"/file/image/20150910/20150910085646_122.jpeg","id":449},{"img_url":"/file/image/20150910/20150910085906_872.jpeg","id":450},{"img_url":"/file/image/20150910/20150910085911_231.jpeg","id":451},{"img_url":"/file/image/20150910/20150910085915_597.jpeg","id":452},{"img_url":"/file/image/20150910/20150910085930_409.jpg","id":453}]
         * description_score : 0
         * interests : 0
         * sellerProvince : 广东省
         * id : 52
         * month_sale_count : 0
         * price : 1
         * logistics_score : 0
         * pay_type :
         * service_score : 0
         * name : 测试1
         * ad : 测试1
         * merchantName : 大四喜
         * sellerCity : 深圳市
         * merchantId : 37
         * goods_count : 0
         */
        private String description_score;           //描述相符
        private String specValueNames;              //规格值名称
        private String interests;                   //关注人数
        private String snId;						//商品编号id
        private String sellerProvince;              //省
        private String id;                          //商品id
        private String status;                      //商品状态，状态为3时才是正常状态
        private String month_sale_count;            //月销量
        private String price;                       //商品价格
        private String logistics_score;             //发货速度
        private String pay_type;                    //支付方式
        private String service_score;               //服务态度
        private String name;                        //商品名称
        private String ad;                          //广告词
        private String merchantName;                //店铺名称
        private String sellerCity;                  //商家地址
        private String specValueIds;                //规格值id
        private String merchantId;                  //店铺id
        private String goods_count;                 //宝贝总数
        private String merchantLogo ;               //店铺图标
        private String goods_genre ;                //商品类型（1：普通商品；2,服务商品 3：旅游商品；4：邮轮产品；5：酒店产品；6：旅游保险；7：签证；8：wifi；9:旅游专用酒店）
        private List<ImgsEntity> imgs;              //图片

        public String getGoods_genre() {
            return goods_genre;
        }

        public void setGoods_genre(String goods_genre) {
            this.goods_genre = goods_genre;
        }

        public String getSpecValueNames() {
            return specValueNames;
        }

        public void setSpecValueNames(String specValueNames) {
            this.specValueNames = specValueNames;
        }


        public String getMerchantLogo() {
            return merchantLogo;
        }

        public void setMerchantLogo(String merchantLogo) {
            this.merchantLogo = merchantLogo;
        }



        public String getSpecValueIds() {
            return specValueIds;
        }

        public void setSpecValueIds(String specValueIds) {
            this.specValueIds = specValueIds;
        }


        public void setDescription_score(String description_score) {
            this.description_score = description_score;
        }

        public void setInterests(String interests) {
            this.interests = interests;
        }

        public void setSellerProvince(String sellerProvince) {
            this.sellerProvince = sellerProvince;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setMonth_sale_count(String month_sale_count) {
            this.month_sale_count = month_sale_count;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setLogistics_score(String logistics_score) {
            this.logistics_score = logistics_score;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public void setService_score(String service_score) {
            this.service_score = service_score;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAd(String ad) {
            this.ad = ad;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public void setSellerCity(String sellerCity) {
            this.sellerCity = sellerCity;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }

        public void setImgs(List<ImgsEntity> imgs) {
            this.imgs = imgs;
        }

        public String getDescription_score() {
            return description_score;
        }

        public String getInterests() {
            return interests;
        }

        public String getSellerProvince() {
            return sellerProvince;
        }

        public String getId() {
            return id;
        }

        public String getMonth_sale_count() {
            return month_sale_count;
        }

        public double getPrice() {
        	try{
        		return Double.parseDouble(price);
        	}catch(Exception e){
        		e.printStackTrace();
        		return 0.00d;
        	}
        }

        public String getLogistics_score() {
            return logistics_score;
        }

        public String getPay_type() {
            return pay_type;
        }

        public String getService_score() {
            return service_score;
        }

        public String getName() {
            return name;
        }

        public String getAd() {
            return ad;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public String getSellerCity() {
            return sellerCity;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public String getGoods_count() {
            return goods_count;
        }

        public List<ImgsEntity> getImgs() {
            return imgs;
        }

        public String getSnId() {
			return snId;
		}

		public void setSnId(String snId) {
			this.snId = snId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}




		public static class ImgsEntity {
            /**
             * img_url : /file/image/20150910/20150910085646_122.jpeg
             * id : 449
             */

            private String img_url;                 //图片地址
            private String id;

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg_url() {
                return img_url;
            }

            public String getId() {
                return id;
            }
        }
    }
}
