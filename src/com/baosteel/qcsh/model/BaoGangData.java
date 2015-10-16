package com.baosteel.qcsh.model;

import android.text.TextUtils;

import com.baosteel.qcsh.utils.JSONParseUtils;
import com.baosteel.qcsh.utils.Preferences;
import com.baosteel.qcsh.utils.Preferences.PreKey;

/**
 * 宝钢本地缓存数据单例
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-8-26
 */
public class BaoGangData {
	
	private static BaoGangData baogangData;
	
	/**登录成功保存的用户信息**/
	private User user;


	/**账户安全中心信息**/
	private UserInfo userInfo;
	
	
	private BaoGangData(){}
	public static BaoGangData getInstance(){
		if(null == baogangData){
			baogangData = new BaoGangData();
		}
		return baogangData;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public User getUser() {
		
		if(null != user){
			return user;
		}
		
		//判断本地用户json数据是否存在(这一步判断也是又必要的，user在缓存有可能被free掉，所以)
    	String json = Preferences.getString(PreKey.USER);
		if(!TextUtils.isEmpty(json)){
			
			User temp = JSONParseUtils.parseUser(json);
			if(null != temp && !TextUtils.isEmpty(temp.userId)){
				
				//登录成功
				user = temp;
			}
		}
    	return user;
	}
	
	public boolean isLogin() {
		//判断缓存用户是否存在
    	if(null != user && !TextUtils.isEmpty(user.userId)){
    		return true;
    	}
    	return false;
	}
	
	/**
	 * 返回用户id
	 * @return
	 */
	public String getUserId() {
		User user = getUser();
		if(null != user){
			return user.userId;
		}
		return null;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
}
