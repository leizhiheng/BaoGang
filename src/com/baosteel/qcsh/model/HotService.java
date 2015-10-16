package com.baosteel.qcsh.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 热门服务
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-9-2
 */
public class HotService {
	
	/**布局模板**/
	public int layoutId;
	
	/**模板里面的图片**/
	public List<LayoutItem> imgList;
	
	
	public HotService(JSONObject obj){
		
		//解析布局ID
		layoutId = obj.optInt("layoutId");
		
		//解析图片数组
		JSONArray array = obj.optJSONArray("layoutDetail");
		if(null != array && array.length() > 0){
			imgList = new ArrayList<HotService.LayoutItem>();
			for(int i=0; i<array.length(); i++){
				imgList.add(new LayoutItem(array.optJSONObject(i)));
			}
		}
	}
	
	
	public class LayoutItem{
		
		//跳转模块id
		public String forwardModuleId;
		
		//跳转模块名称
		public String forwardModuleName;
		
		//广告词
		public String adWords;
		
		//图片URL
		public String imgUrl;
		
		//图片显示在布局中的位置
		public String imgIndex;
		
		public LayoutItem(JSONObject obj){
			forwardModuleId = obj.optString("forwardModuleId");
			forwardModuleName = obj.optString("forwardModuleName");
			adWords = obj.optString("adWords");
			imgUrl = obj.optString("imgUrl");
			imgIndex = obj.optString("imgIndex");
		}
	}
}
