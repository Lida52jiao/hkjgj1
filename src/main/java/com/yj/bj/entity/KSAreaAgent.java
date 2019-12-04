package com.yj.bj.entity;

import java.io.Serializable;

public class KSAreaAgent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -373288798311895821L;

	private String proviceMerChantId;
	
	private String cityMerChantId;
	
	private String countryMerChantId;

	public String getProviceMerChantId() {
		return proviceMerChantId;
	}

	public void setProviceMerChantId(String proviceMerChantId) {
		this.proviceMerChantId = proviceMerChantId;
	}

	public String getCityMerChantId() {
		return cityMerChantId;
	}

	public void setCityMerChantId(String cityMerChantId) {
		this.cityMerChantId = cityMerChantId;
	}

	public String getCountryMerChantId() {
		return countryMerChantId;
	}

	public void setCountryMerChantId(String countryMerChantId) {
		this.countryMerChantId = countryMerChantId;
	}
	
	

}
