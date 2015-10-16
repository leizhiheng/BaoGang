package com.baosteel.qcsh.model;

/**
 * Created by kuangyong on 15/9/9.
 */
public class CarInfoBean {
//    private String carnum;//车牌号码
//    private String frame_number;//车架号
//    private String motor_number;//发动机号
//    private String time;//登记时间
    private boolean isSelected;//是否选中

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
