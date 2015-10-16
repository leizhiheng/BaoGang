package com.baosteel.qcsh.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jws on 2015/10/12.
 * 类型分类
 */
public class TypeProductCategory implements Serializable{
    private String id;
    /** 分类名称 */
    private String name;
    /** 分类图标 */
    private String img_url;
    /** 分类等级 */
    private String level;
    /** 该分类下的商品 */
    private List<TypeProductCategory> list;

    private boolean isSelected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<TypeProductCategory> getList() {
        return list;
    }

    public void setmCatogeryItems(List<TypeProductCategory> list) {
        this.list = list;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
