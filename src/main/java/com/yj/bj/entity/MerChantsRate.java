package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "t_mp_merchantsRate")
public class MerChantsRate implements Serializable {

	private static final long serialVersionUID = 2413217407972271733L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merType")
	private String merType;
	@Column(name = "rate")
	private BigDecimal rate;
	@Column(name = "d0Fee")
	private Long d0Fee;
	@Column(name = "aisleCode")
    private String aisleCode;
	@Column(name = "appId")
	private String appId;
	@Column(name = "isRepayment")
	private String isRepayment;
	@Column(name = "isShow")
	private String isShow;
	@Column(name = "aisleName")
	private String aisleName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMerType() {
		return merType;
	}
	public void setMerType(String merType) {
		this.merType = merType;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Long getD0Fee() {
		return d0Fee;
	}
	public void setD0Fee(Long d0Fee) {
		this.d0Fee = d0Fee;
	}
	public String getAisleCode() {
		return aisleCode;
	}
	public void setAisleCode(String aisleCode) {
		this.aisleCode = aisleCode;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getIsRepayment() {
		return isRepayment;
	}
	public void setIsRepayment(String isRepayment) {
		this.isRepayment = isRepayment;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getAisleName() {
		return aisleName;
	}
	public void setAisleName(String aisleName) {
		this.aisleName = aisleName;
	}
}
