package com.yj.bj.entity;

public class Rate {
	
	private String merChantId;
	
	private String merType;
	
	private String rate;
	
	private String brushrate;

	public Rate() {
		super();
	}

	public Rate(String merChantId, String merType, String rate) {
		super();
		this.merChantId = merChantId;
		this.merType = merType;
		this.rate = rate;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getBrushrate() {
		return brushrate;
	}

	public void setBrushrate(String brushrate) {
		this.brushrate = brushrate;
	}
}
