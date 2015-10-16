package com.common.zxing.scan.activity;

import java.io.Serializable;

import android.util.Log;

import com.google.gson.Gson;

public class ScanningCodeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	public String commodityId;// 商品ID

	public static ScanningCodeBean explainJson(String json) {

		Gson gson = new Gson();
		try {
			ScanningCodeBean experLightBean = gson.fromJson(json, ScanningCodeBean.class);
			return experLightBean;
		} catch (Exception e) {
			Log.d("ScanningCodeBean", e.toString());
			return null;
		}
	}
}
