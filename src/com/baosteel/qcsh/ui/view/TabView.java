package com.baosteel.qcsh.ui.view;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * 首页tabView 
 * @author liuyuanqi
 * 
 * @todo TODO
 * @date 2015-7-25
 */
public class TabView {
	
	private int iconIn,iconOn;
	
	private int textColorIn,textColorOn;
	
	private ImageView iconImageView;
	
	private TextView nameTextView;
	
	public void setIcon(int iconIn, int iconOn){
		this.iconIn = iconIn;
		this.iconOn = iconOn;
	}
	
	/**
	 * 
	 * @Description 直接切换图标
	 * @Author blue
	 * @Time 2015-9-22
	 */
	public void setIconImage(int iconId) {
		iconImageView.setImageResource(iconId);
	}
	
	public void setTextColor(int textColorIn, int textColorOn){
		this.textColorIn = textColorIn;
		this.textColorOn = textColorOn;
	}
	
	public void setView(ImageView iconImageView, TextView nameTextView){
		this.iconImageView = iconImageView;
		this.nameTextView = nameTextView;
	}
	
	public void clickOn(){
		this.iconImageView.setImageResource(iconOn);
		this.nameTextView.setTextColor(textColorOn);
	}
	
	public void clickIn(){
		this.iconImageView.setImageResource(iconIn);
		this.nameTextView.setTextColor(textColorIn);
	}
	
	public void setTextClickIn(String text) {
		this.nameTextView.setText(text);
		clickIn();
	}
	
	public void setTextClickOn(String text) {
		this.nameTextView.setText(text);
		clickOn();
	}
}
