package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_withdrawals")
public class Withdrawals implements Serializable {

	private static final long serialVersionUID = 5169790091511621037L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "generationFee")
	private String generationFee;
	@Column(name = "generationFeeRepayment")
	private String generationFeeRepayment;
	@Column(name = "appId")
	private String appId;
	
	public Withdrawals() {
		super();
	}
	
	public Withdrawals(Long id, String generationFee,
			String generationFeeRepayment) {
		super();
		this.id = id;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGenerationFee() {
		return generationFee;
	}
	
	public void setGenerationFee(String generationFee) {
		this.generationFee = generationFee;
	}
	
	public String getGenerationFeeRepayment() {
		return generationFeeRepayment;
	}
	
	public void setGenerationFeeRepayment(String generationFeeRepayment) {
		this.generationFeeRepayment = generationFeeRepayment;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
