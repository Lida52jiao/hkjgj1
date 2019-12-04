package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_fee")
public class Fee implements Serializable {
	
	private static final long serialVersionUID = -3216436928531520734L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "floorNumber")
	private String floorNumber;
	@Column(name = "merchantFee")
	private String merchantFee;
	
	public Fee() {
		super();
	}

	public Fee(String agentId, String floorNumber, String merchantFee) {
		super();
		this.agentId = agentId;
		this.floorNumber = floorNumber;
		this.merchantFee = merchantFee;
	}

	public Fee(Long id, String agentId, String floorNumber, String merchantFee) {
		super();
		this.id = id;
		this.agentId = agentId;
		this.floorNumber = floorNumber;
		this.merchantFee = merchantFee;
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

	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getMerchantFee() {
		return merchantFee;
	}

	public void setMerchantFee(String merchantFee) {
		this.merchantFee = merchantFee;
	}
}
