package com.baosteel.qcsh.model;

import java.io.Serializable;

public class UpdateInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String downloadAddress;// downloadAddress 下载地址
	private String versionNum;// versionNum 版本号
	private String qrCode;// qrCode 二维码
	private String updateTip;// updateTip 更新提示
	private String versionName;// versionName 版本名称
	private String versionUpdateTime;// versionUpdateTime 版本更新时间
	private String forceUpdate;// forceUpdate 是否强制更新(1:强制)

	public String getDownloadAddress() {
		return downloadAddress;
	}

	public void setDownloadAddress(String downloadAddress) {
		this.downloadAddress = downloadAddress;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getUpdateTip() {
		return updateTip;
	}

	public void setUpdateTip(String updateTip) {
		this.updateTip = updateTip;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionUpdateTime() {
		return versionUpdateTime;
	}

	public void setVersionUpdateTime(String versionUpdateTime) {
		this.versionUpdateTime = versionUpdateTime;
	}

	public String getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

}
