package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "t_mp_channelRate")
public class ChannelRate implements Serializable {
	
	private static final long serialVersionUID = -7841656247552138482L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "rate")
	private BigDecimal rate;
	@Column(name = "d0Fee")
	private Long d0Fee;
	@Column(name = "aisleCode")
    private String aisleCode;
	@Column(name = "appId")
	private String appId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
}
