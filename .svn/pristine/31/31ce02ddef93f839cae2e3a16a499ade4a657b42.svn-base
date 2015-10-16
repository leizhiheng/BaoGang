package com.baosteel.qcsh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baosteel.qcsh.model.QueryAppGoodsSpecListBean.ReturnMapEntity.SpecEntity.SpecValueListEntity;

/**
 * 商品属性列表
 * Created by kuangyong on 15/9/28.
 */
public class QueryAppGoodsSpecListBean implements Serializable{

    /**
     * returnMap : {"spec":[{"specValueList":[{"id":185,"spec_value_id":46,"goods_id":85,"value":"S","spec_id":67,"sn_id":187},{"id":187,"spec_value_id":47,"goods_id":85,"value":"M","spec_id":67,"sn_id":188}],"id":67,"sort":1,"name":"尺码"},{"specValueList":[{"id":186,"spec_value_id":50,"goods_id":85,"value":"黑色","spec_id":68,"sn_id":187},{"id":188,"spec_value_id":50,"goods_id":85,"value":"黑色","spec_id":68,"sn_id":188}],"id":68,"sort":2,"name":"颜色"}],"goodsSpecDe":[{"id":185,"sort":0,"spec_value_id":46,"goods_id":85,"value":"S","spec_id":67,"seller_id":53,"sn_id":187},{"id":187,"sort":0,"spec_value_id":47,"goods_id":85,"value":"M","spec_id":67,"seller_id":53,"sn_id":188}],"goodsimg":"http://d1-img.java.shovesoft.com/flie_upload/image/20150926/20150926163317_883.png","goodsSnSpec":{"goodsCommonPrice":200,"goodsCount":10,"goodsDesc":"","goodsId":85,"goodsSn":"1","goodsSteelPrice":180,"goodsWeight":0,"id":187,"sellerId":53,"status":1}}
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
         * spec : [{"specValueList":[{"id":185,"spec_value_id":46,"goods_id":85,"value":"S","spec_id":67,"sn_id":187},{"id":187,"spec_value_id":47,"goods_id":85,"value":"M","spec_id":67,"sn_id":188}],"id":67,"sort":1,"name":"尺码"},{"specValueList":[{"id":186,"spec_value_id":50,"goods_id":85,"value":"黑色","spec_id":68,"sn_id":187},{"id":188,"spec_value_id":50,"goods_id":85,"value":"黑色","spec_id":68,"sn_id":188}],"id":68,"sort":2,"name":"颜色"}]
         * goodsSpecDe : [{"id":185,"sort":0,"spec_value_id":46,"goods_id":85,"value":"S","spec_id":67,"seller_id":53,"sn_id":187},{"id":187,"sort":0,"spec_value_id":47,"goods_id":85,"value":"M","spec_id":67,"seller_id":53,"sn_id":188}]
         * goodsimg : http://d1-img.java.shovesoft.com/flie_upload/image/20150926/20150926163317_883.png
         * goodsSnSpec : {"goodsCommonPrice":200,"goodsCount":10,"goodsDesc":"","goodsId":85,"goodsSn":"1","goodsSteelPrice":180,"goodsWeight":0,"id":187,"sellerId":53,"status":1}
         */

        private String goodsimg;                                    //商品图片
        private GoodsSnSpecEntity goodsSnSpec;                      //商品编号Map
        private List<SpecEntity> spec;                              //规格list
        private List<GoodsSpecDeEntity> goodsSpecDe;                //商品属性

        /**
         * 选中默认的规格
         */
        public void checkDefaultSpec(){
        	int selectIndex = -1;
        	for(int i=0; i<spec.size(); i++){
        		selectIndex = getIndexInDataList(spec.get(i), goodsSpecDe);
        		if(selectIndex >= 0){
        			spec.get(i).selectIndex = selectIndex;
        			spec.get(i).specValueList.get(selectIndex).setIsSelected(true);
        		}
        	}
        }
        
        /**
         * 获取默认规格的索引
         * @param spec
         * @param goodsSpecDe
         * @return
         */
        private int getIndexInDataList(SpecEntity spec, List<GoodsSpecDeEntity> goodsSpecDe){
        	try{
	        	SpecValueListEntity entity = null;
	        	for(int i=0; i<spec.specValueList.size(); i++){
	        		entity = spec.specValueList.get(i);
	        		for(int j=0; j< goodsSpecDe.size(); j++){
	        			
	        			//规格id相同，如 颜色对应的id，或尺码对应的id
	        			if(spec.getId().equals(goodsSpecDe.get(j).spec_id)){
	        				if(entity.getSpec_value_id().equals(goodsSpecDe.get(j).spec_value_id)){
	        					
	        					//返回默认选中的规格索引
	        					return i;
	        				}
	        			}
	        		}
	        	}
        	}catch(Exception e){
        		return -1;
        	}
        	return -1;
        }
        
        public void setGoodsimg(String goodsimg) {
            this.goodsimg = goodsimg;
        }

        public void setGoodsSnSpec(GoodsSnSpecEntity goodsSnSpec) {
            this.goodsSnSpec = goodsSnSpec;
        }

        public void setSpec(List<SpecEntity> spec) {
            this.spec = spec;
        }

        public void setGoodsSpecDe(List<GoodsSpecDeEntity> goodsSpecDe) {
            this.goodsSpecDe = goodsSpecDe;
        }

        public String getGoodsimg() {
            return goodsimg;
        }

        public GoodsSnSpecEntity getGoodsSnSpec() {
            return goodsSnSpec;
        }

        public List<SpecEntity> getSpec() {
            return spec;
        }

        public List<GoodsSpecDeEntity> getGoodsSpecDe() {
            return goodsSpecDe;
        }

        public static class GoodsSnSpecEntity {
            /**
             * goodsCommonPrice : 200
             * goodsCount : 10
             * goodsDesc :
             * goodsId : 85
             * goodsSn : 1
             * goodsSteelPrice : 180
             * goodsWeight : 0
             * id : 187
             * sellerId : 53
             * status : 1
             */
            private String goodsCommonPrice;                        //普通会员价
            private String goodsCount;                              //商品库存量
            private String goodsDesc;                               //商品规格简单描述
            private String goodsId;                                 //商品id
            private String goodsSn;                                 //商品编号
            private String goodsSteelPrice;                         //宝钢会员价
            private String goodsWeight;                             //商品重量
            private String id;                                      //商品编号id
            private String sellerId;                                //商家id
            private String status;                                  //状态

            public void setGoodsCommonPrice(String goodsCommonPrice) {
                this.goodsCommonPrice = goodsCommonPrice;
            }

            public void setGoodsCount(String goodsCount) {
                this.goodsCount = goodsCount;
            }

            public void setGoodsDesc(String goodsDesc) {
                this.goodsDesc = goodsDesc;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public void setGoodsSn(String goodsSn) {
                this.goodsSn = goodsSn;
            }

            public void setGoodsSteelPrice(String goodsSteelPrice) {
                this.goodsSteelPrice = goodsSteelPrice;
            }

            public void setGoodsWeight(String goodsWeight) {
                this.goodsWeight = goodsWeight;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getGoodsCommonPrice() {
                return goodsCommonPrice;
            }

            public String getGoodsCount() {
                return goodsCount;
            }

            public String getGoodsDesc() {
                return goodsDesc;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public String getGoodsSn() {
                return goodsSn;
            }

            public String getGoodsSteelPrice() {
                return goodsSteelPrice;
            }

            public String getGoodsWeight() {
                return goodsWeight;
            }

            public String getId() {
                return id;
            }

            public String getSellerId() {
                return sellerId;
            }

            public String getStatus() {
                return status;
            }
        }

        public static class SpecEntity {
            /**
             * specValueList : [{"id":185,"spec_value_id":46,"goods_id":85,"value":"S","spec_id":67,"sn_id":187},{"id":187,"spec_value_id":47,"goods_id":85,"value":"M","spec_id":67,"sn_id":188}]
             * id : 67
             * sort : 1
             * name : 尺码
             */

            private String id;                                  //规格id
            private String name;                                //规格名
            private List<SpecValueListEntity> specValueList;    //规格值list

            /**选中的属性索引**/
            private int selectIndex = -1;

            public SpecEntity(String id, String name){
            	this.id = id;
            	this.name = name;
            	this.specValueList = new ArrayList<SpecValueListEntity>();
            	this.specValueList.add(new SpecValueListEntity("1", "黑色"));
            	this.specValueList.add(new SpecValueListEntity("2", "棕色"));
            	this.specValueList.add(new SpecValueListEntity("3", "咖啡色"));
            	this.specValueList.add(new SpecValueListEntity("4", "乌鲁木齐色"));
            	this.specValueList.add(new SpecValueListEntity("5", "中华人民共和国"));
            	this.specValueList.add(new SpecValueListEntity("6", "哈哈色"));
            }

            public boolean isRepeat(int index){
                return index==selectIndex;
            }

            public String getSelectedSpecId(){
                try{
                    return specValueList.get(selectIndex).spec_value_id;
                }catch(Exception e){
                    return null;

                }
            }


            private int getDataSize(){
            	return null != specValueList ? specValueList.size() : 0;
            }
            
            public String getSelectSpec(){
            	if(selectIndex >= 0){
            		return specValueList.get(selectIndex).spec_value_id;
            	}
            	return "";
            }
            
            public void selectIndex(int index){
            	
            	//选中状态没有发生改变
            	if(index == selectIndex){
            		return;
            	}
            	
            	//上一次选中的属性-反选
            	if(selectIndex >= 0 && selectIndex < getDataSize()){
            		specValueList.get(selectIndex).setIsSelected(false);
            	}
            	
            	//当前选中的属性-选中
            	if(index >= 0 && index < getDataSize()){
            		specValueList.get(index).setIsSelected(true);
            		selectIndex = index;
            	}
            }
            
            public SpecEntity(){
            	
            }
            
            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setSpecValueList(List<SpecValueListEntity> specValueList) {
                this.specValueList = specValueList;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public List<SpecValueListEntity> getSpecValueList() {
                return specValueList;
            }

            public static class SpecValueListEntity {
                /**
                 * id : 185
                 * spec_value_id : 46
                 * goods_id : 85
                 * value : S
                 * spec_id : 67
                 * sn_id : 187
                 */

                private String spec_value_id;                   //规格值id
                private String goods_id;                        //商品id
                private String value;                           // 规格值
                private String spec_id;                         //规格id
                private String sn_id;                           //商品编号id
                private boolean isSelected;                     //是否选中

                public SpecValueListEntity(String id, String name){
                	this.spec_id = id;
                	this.value = name;
                }
                
                public SpecValueListEntity(){
                	
                }
                
                public boolean isSelected() {
                    return isSelected;
                }

                public void setIsSelected(boolean isSelected) {
                    this.isSelected = isSelected;
                }

                public void setSpec_value_id(String spec_value_id) {
                    this.spec_value_id = spec_value_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public void setSpec_id(String spec_id) {
                    this.spec_id = spec_id;
                }

                public void setSn_id(String sn_id) {
                    this.sn_id = sn_id;
                }

                public String getSpec_value_id() {
                    return spec_value_id;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public String getValue() {
                    return value;
                }

                public String getSpec_id() {
                    return spec_id;
                }

                public String getSn_id() {
                    return sn_id;
                }
            }
        }

        public static class GoodsSpecDeEntity {
            /**
             * id : 185
             * sort : 0
             * spec_value_id : 46
             * goods_id : 85
             * value : S
             * spec_id : 67
             * seller_id : 53
             * sn_id : 187
             */

            private String spec_value_id;                           //规格值id
            private String goods_id;                                //商品id
            private String value;                                   // 规格值
            private String spec_id;                                 //规格id
            private String sn_id;                                   //商品编号id

            public void setSpec_value_id(String spec_value_id) {
                this.spec_value_id = spec_value_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public void setSpec_id(String spec_id) {
                this.spec_id = spec_id;
            }

            public void setSn_id(String sn_id) {
                this.sn_id = sn_id;
            }

            public String getSpec_value_id() {
                return spec_value_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getValue() {
                return value;
            }

            public String getSpec_id() {
                return spec_id;
            }

            public String getSn_id() {
                return sn_id;
            }
        }
    }
}
