package com.histudio.base;

/**
 * 基础信息的封装
 * 
 * @author ljh
 * 
 * @data 2014-10-22
 */
public class ContextConfig {

	// 版本
	private String version;
	// 版本号
	private int versionCode;
	// 固件
	private String firmware;
	// 固件对应的sdk版本
	private String sdkVersion;
	// 分辨率
	private String resolution;
	// 屏幕密度
	private String density;
	// 宽度
	private float screenWidth;
	// 可视区域高度
	private float maxVisibleWidth;
	// 高度
	private float screenHeight;
	// 硬件架构
	private String abi;
	// 设备名
	private String model;
	// 设备id(手机本身参数IMEI)
	private String imei;
	// 卡id
	private String simId;
	// 是不是debug模式
	private boolean isDebug;
	// 渠道号
	private String channelId;
	// 客户端类型
	private int clientType;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getAbi() {
		return abi;
	}

	public void setAbi(String abi) {
		this.abi = abi;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	public float getScreenHeight() {
		return screenHeight;
	}

	public float getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(float screenWidth) {
		this.screenWidth = screenWidth;
	}

	public void setScreenHeight(float screenHeight) {
		this.screenHeight = screenHeight;
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public float getMaxVisibleWidth() {
		return maxVisibleWidth;
	}

	public void setMaxVisibleWidth(float maxVisibleWidth) {
		this.maxVisibleWidth = maxVisibleWidth;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public int getClientType() {
		return clientType;
	}

	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

}
