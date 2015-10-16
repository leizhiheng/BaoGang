package com.baosteel.qcsh.model.store;

import com.baosteel.qcsh.model.ProductCategory;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺分类数据
 * Created by kuangyong on 15/9/30.
 */
public class QueryAppStoreGoodsTypeBean implements Serializable{

    /**
     * returnMap : [{"id":34,"name":"食品超市"},{"id":35,"name":"盘菜"},{"id":40,"name":"宴会"}]
     * type : 1
     * msg : 成功
     */

    private int type;
    private String msg;
    private List<ProductCategory> returnMap;

    public void setType(int type) {
        this.type = type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setReturnMap(List<ProductCategory> returnMap) {
        this.returnMap = returnMap;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public List<ProductCategory> getReturnMap() {
        return returnMap;
    }

}
