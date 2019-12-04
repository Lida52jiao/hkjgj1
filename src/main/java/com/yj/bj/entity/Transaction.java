package com.yj.bj.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_mp_transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = -8000220212669530507L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merId")
	private String merId;
	@Column(name = "agentName")
	private String agentName;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "floorNumber")
	private String floorNumber;
	@Column(name = "generationFee")
	private String generationFee;
	@Column(name = "generationFeeRepayment")
	private String generationFeeRepayment;
	@Column(name = "fee0")
	private String fee0;
	@Column(name = "d0fee")
	private String d0fee;
	@Column(name = "fee1")
	private String fee1;
	@Column(name = "d1fee")
	private String d1fee;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "agentStatus")
	private String agentStatus;
	@Column(name = "remarks")
	private String remarks;

	public Transaction() {
		super();
	}

	public Transaction(String merId, String agentName, String merChantId,
					   String floorNumber, String generationFee,
					   String generationFeeRepayment, String fee0, String d0fee,
					   String fee1, String d1fee, String creatDate, String agentStatus,
					   String remarks) {
		super();
		this.merId = merId;
		this.agentName = agentName;
		this.merChantId = merChantId;
		this.floorNumber = floorNumber;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
		this.fee0 = fee0;
		this.d0fee = d0fee;
		this.fee1 = fee1;
		this.d1fee = d1fee;
		this.creatDate = creatDate;
		this.agentStatus = agentStatus;
		this.remarks = remarks;
	}

	public Transaction(Long id, String merId, String agentName,
					   String merChantId, String floorNumber, String generationFee,
					   String generationFeeRepayment, String fee0, String d0fee,
					   String fee1, String d1fee, String creatDate, String agentStatus,
					   String remarks) {
		super();
		this.id = id;
		this.merId = merId;
		this.agentName = agentName;
		this.merChantId = merChantId;
		this.floorNumber = floorNumber;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
		this.fee0 = fee0;
		this.d0fee = d0fee;
		this.fee1 = fee1;
		this.d1fee = d1fee;
		this.creatDate = creatDate;
		this.agentStatus = agentStatus;
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
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

	public String getFee0() {
		return fee0;
	}

	public void setFee0(String fee0) {
		this.fee0 = fee0;
	}

	public String getD0fee() {
		return d0fee;
	}

	public void setD0fee(String d0fee) {
		this.d0fee = d0fee;
	}

	public String getFee1() {
		return fee1;
	}

	public void setFee1(String fee1) {
		this.fee1 = fee1;
	}

	public String getD1fee() {
		return d1fee;
	}

	public void setD1fee(String d1fee) {
		this.d1fee = d1fee;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
