package com.baosteel.qcsh.database.bean;

import com.baosteel.qcsh.model.BaseProductData;

/**
 * Created by lenovo on 2015/9/1.
 */
public class TopProduct extends BaseProductData{
    /**商品图片**/
    private int pic;
    /**商品购买数量**/
    private String num;

    public TopProduct(String id, String name, int pic, String price, String num) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.sale_count = num;
    }
    public TopProduct(String name, int pic, String price, String num) {
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.sale_count = num;
    }
	public int getPic() {
		return pic;
	}
	public void setPic(int pic) {
		this.pic = pic;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNum() {
		return sale_count;
	}
	public void setNum(String num) {
		this.sale_count = num;
	}
	public String getFreight() {
		int logistics_style = getLogistics_style();
		if(logistics_style == 1){
			return "运费："+logistics_cost;
		}else{
			return "运费模板";
		}
	}

    
}
