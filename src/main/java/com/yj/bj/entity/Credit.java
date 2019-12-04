package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_credit")
public class Credit implements Serializable {

	private static final long serialVersionUID = -9005955915394921174L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "gateId")
	private String gateId;
	@Column(name = "gateName")
	private String gateName;
	@Column(name = "cost")
	private String cost;
	@Column(name = "transaction")
	private String transaction;
	@Column(name = "merchantFee")
	private String merchantFee;
	
	public Credit() {
		super();
	}

	public Credit(Long id, String gateId, String gateName, String cost,
			String transaction, String merchantFee) {
		super();
		this.id = id;
		this.gateId = gateId;
		this.gateName = gateName;
		this.cost = cost;
		this.transaction = transaction;
		this.merchantFee = merchantFee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getGateName() {
		return gateName;
	}

	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getMerchantFee() {
		return merchantFee;
	}

	public void setMerchantFee(String merchantFee) {
		this.merchantFee = merchantFee;
	}
}
