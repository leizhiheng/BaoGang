package com.baosteel.qcsh.utils;

import java.util.Random;
import java.util.UUID;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.baosteel.qcsh.AppContext;

/**
 * 存储xml键值对
 * 
 * @author liuyuanqi
 */
public class Preferences {

	/**
	 * 键值对保存到sunsacred.xml里面
	 */
	private static final String SHAREDNAME 	= "baogang";
	
	public static final String UUID 		= "uuid";
	public static final String HAS_UUID 	= "has_uuid";
	
	public static final String TOKEN 		= "token";
	
	/**所有Key值**/
	public static final class PreKey{
		
		//登录设置
		
		/**用户登录信息**/
		public static final String USER						= "user";
		
		/**用户名**/
		public static final String USERNAME 				= "username";
		
		/**密码**/
		public static final String PASSWORD 				= "password";
	
		/**记住登录信息**/
		public static final String REMEMBER_INFO 			= "remember_info";

		/**购物车ID集合    以逗号分隔的字符串  如 id,id,id **/
		public static final String SHOPPINGIDS 			= "shopping_ids";
		
		
		
	}
	
	/**
	 * put一个string键值对
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean putString(String key, String value) {

		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);

		Editor eidtor = prefence.edit();
		eidtor.putString(key, value);
		return eidtor.commit();
	}

	/**
	 * put一个string键值对
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean removeKey(String key) {

		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);

		Editor eidtor = prefence.edit();
		eidtor.remove(key);
		return eidtor.commit();
	}
	
	/**
	 * put一个string键值对
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean putBoolean(String key, boolean value) {

		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);

		Editor eidtor = prefence.edit();
		eidtor.putBoolean(key, value);
		return eidtor.commit();
	}

	/**
	 * 获取string 值
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);
		return prefence.getString(key, "");
	}
	
	/**
	 * 获取int 值
	 * @param key
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);
		return prefence.getInt(key, defaultValue);
	}
	
	/**
	 * put一个Int键值对
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean putInt(String key, int value) {

		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);

		Editor eidtor = prefence.edit();
		eidtor.putInt(key, value);
		return eidtor.commit();
	}
	
	/**
	 * 获取string 值
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key) {
		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);
		return prefence.getBoolean(key, false);
	}
	
	/**
	 * 保存UUID至本地
	 */
	private static void saveUUID() {
		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);
		
		String uuid = getUUID(app);
		Editor editor = prefence.edit();
		editor.putString(UUID, uuid);
		editor.putBoolean(HAS_UUID, true);
		editor.commit();
	}

	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);
		
		if (hasUUID()) {
			return prefence.getString(UUID, "");
		} else {
			saveUUID();
			return prefence.getString(UUID, "");
		}
	}
	
	private static String getUUID(Context context)
	{
		String UUID = null;
        TelephonyManager telephoneManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephoneManager.getDeviceId();
        String androidId = Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        if ((androidId!=null && androidId.length()>0) || (deviceId!=null && deviceId.length()>0))
        {
        	if(null == androidId){
        		androidId = new Random().nextInt(10000000)+999999 +"";
        	}
        	if(null == deviceId){
        		deviceId = new Random().nextInt(10000000)+999999 +"";
        	}
        	
        	Log.i("Prefrence", "android_id:"+androidId);
        	Log.i("Prefrence", "device_id:"+deviceId);
        	
            UUID uuid = new UUID(androidId.hashCode(), (long)deviceId.hashCode() << 32 );
            UUID = uuid.toString();
            
            Log.i("Prefrence", "uuid:"+UUID);
        }
        return UUID;
	}
	
	/**
	 * UUID 是否已经生成
	 * 
	 * @return
	 */
	public static boolean hasUUID() {
		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);
		return prefence.getBoolean(HAS_UUID, false);
	}
	
	/**
	 * 获取登录token
	 * 
	 * @return
	 */
	public static String getToken() {
		AppContext app = AppContext.appContext;
		SharedPreferences prefence = app.getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE);
		return prefence.getString(TOKEN, "");
	}

	/**
	 * 字符串累加
	 * @param key
	 * @param value
	 * @param split
	 */
	public static void appendString(String key, String value, String split) {
		String val = getString(key);
		if(TextUtils.isEmpty(val)){
			
			//首次插入值
			putString(key, value);
			
		}else{
			
			//累加值
			val = (val + split + value);
			putString(key, val);
			
		}
		
	}
}
