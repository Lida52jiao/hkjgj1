package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "ks_merchants_usedarea")
public class KSMerUsedArea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5911683985952637354L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "merName")
	private String merName;
	@Column(name = "merMp")
	private String merMp;
	@Column(name = "certNo")
	private String certNo;
	@Column(name = "proviceAreaId")
	private String proviceAreaId;
	@Column(name = "proviceAreaName")
	private String proviceAreaName;
	@Column(name = "cityAreaId")
	private String cityAreaId;
	@Column(name = "cityAreaName")
	private String cityAreaName;
	@Column(name = "countryAreaId")
	private String countryAreaId;
	@Column(name = "countryAreaName")
	private String countryAreaName;
	@Column(name = "institutionId")
	private String institutionId;
	@Column(name = "appId")
	private String appId;
	@Column(name = "createdTime")
	private Date createdTime;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "remarksT")
	private String remarksT;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMerChantId() {
		return merChantId;
	}
	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerMp() {
		return merMp;
	}
	public void setMerMp(String merMp) {
		this.merMp = merMp;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getProviceAreaId() {
		return proviceAreaId;
	}
	public void setProviceAreaId(String proviceAreaId) {
		this.proviceAreaId = proviceAreaId;
	}
	public String getProviceAreaName() {
		return proviceAreaName;
	}
	public void setProviceAreaName(String proviceAreaName) {
		this.proviceAreaName = proviceAreaName;
	}
	public String getCityAreaId() {
		return cityAreaId;
	}
	public void setCityAreaId(String cityAreaId) {
		this.cityAreaId = cityAreaId;
	}
	public String getCityAreaName() {
		return cityAreaName;
	}
	public void setCityAreaName(String cityAreaName) {
		this.cityAreaName = cityAreaName;
	}
	public String getCountryAreaId() {
		return countryAreaId;
	}
	public void setCountryAreaId(String countryAreaId) {
		this.countryAreaId = countryAreaId;
	}
	public String getCountryAreaName() {
		return countryAreaName;
	}
	public void setCountryAreaName(String countryAreaName) {
		this.countryAreaName = countryAreaName;
	}
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarksT() {
		return remarksT;
	}
	public void setRemarksT(String remarksT) {
		this.remarksT = remarksT;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
