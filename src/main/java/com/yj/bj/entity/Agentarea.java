package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_agentarea")
public class Agentarea implements Serializable {

	private static final long serialVersionUID = 3613413834187792187L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "merchantId")
	private String merchantId;
	@Column(name = "areaCode")
	private String areaCode;
	@Column(name = "areaLevel")
	private String areaLevel;
	@Column(name = "areaName")
	private String areaName;
	@Column(name = "areaId")
	private String areaId;
	@Column(name = "areaParentId")
	private String areaParentId;
	
	public Agentarea() {
		super();
	}

	public Agentarea(Long id, String agentId, String merchantId,
                     String areaCode, String areaLevel, String areaName, String areaId,
                     String areaParentId) {
		super();
		this.id = id;
		this.agentId = agentId;
		this.merchantId = merchantId;
		this.areaCode = areaCode;
		this.areaLevel = areaLevel;
		this.areaName = areaName;
		this.areaId = areaId;
		this.areaParentId = areaParentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaParentId() {
		return areaParentId;
	}

	public void setAreaParentId(String areaParentId) {
		this.areaParentId = areaParentId;
	}
	
}
