package com.yj.bj.entity;

import java.math.BigDecimal;

public class MerChant {
	
	private Long id;
	
	private String merChantId;
	
	private String merName;
	
	private String merMp;
	
	private String certNo;
	
	private String merStat;
	
	private String identifying;
	
	private String merChantFee;
	
	private String generationFee;
	
	private String generationFeeRepayment;
	
	private String status;
	
	private String agentStatus;
	
	private String agentId;
	
	private String institutionId;
	
	private String merType;
	
	private String oneMerId;
	
	private String twoMerId;
	
	private String threeMerId;
	
	private String frozen;
	
	private String faceImgUrl;
	
	private String regDate;
	
	private String merStatTime;
	
	private String statusDate;
	
	private String startDate;
	
	private String finishDate;
	
	private String appId;
	
	private String appName;
	
	private String remarks;
	
	private BigDecimal balance;
	
	private BigDecimal balanceProfit;
	
	private BigDecimal balanceFrozen;
	
	private BigDecimal balanceProfitFrozen;
	
	private int amount;
	
	private int credit;
	
	private int card;

	private Integer isNotUse;//是否可用，1可用，2不可用

	public Integer getIsNotUse() {
		return isNotUse;
	}

	public void setIsNotUse(Integer isNotUse) {
		this.isNotUse = isNotUse;
	}

	public MerChant() {
		super();
	}

	public MerChant(String merChantId, String merName, String merMp,
			String certNo, String merStat, String identifying,
			String merChantFee, String generationFee,
			String generationFeeRepayment, String status, String agentStatus,
			String agentId, String institutionId, String merType,
			String oneMerId, String twoMerId, String threeMerId, String frozen,
			String faceImgUrl, String regDate, String merStatTime,
			String statusDate, String startDate, String finishDate,
			String appId, String remarks, BigDecimal balance,
			BigDecimal balanceProfit, BigDecimal balanceFrozen,
			BigDecimal balanceProfitFrozen) {
		super();
		this.merChantId = merChantId;
		this.merName = merName;
		this.merMp = merMp;
		this.certNo = certNo;
		this.merStat = merStat;
		this.identifying = identifying;
		this.merChantFee = merChantFee;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
		this.status = status;
		this.agentStatus = agentStatus;
		this.agentId = agentId;
		this.institutionId = institutionId;
		this.merType = merType;
		this.oneMerId = oneMerId;
		this.twoMerId = twoMerId;
		this.threeMerId = threeMerId;
		this.frozen = frozen;
		this.faceImgUrl = faceImgUrl;
		this.regDate = regDate;
		this.merStatTime = merStatTime;
		this.statusDate = statusDate;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.appId = appId;
		this.remarks = remarks;
		this.balance = balance;
		this.balanceProfit = balanceProfit;
		this.balanceFrozen = balanceFrozen;
		this.balanceProfitFrozen = balanceProfitFrozen;
	}

	public MerChant(Long id, String merChantId, String merName, String merMp,
			String certNo, String merStat, String identifying,
			String merChantFee, String generationFee,
			String generationFeeRepayment, String status, String agentStatus,
			String agentId, String institutionId, String merType,
			String oneMerId, String twoMerId, String threeMerId, String frozen,
			String faceImgUrl, String regDate, String merStatTime,
			String statusDate, String startDate, String finishDate,
			String appId, String remarks, BigDecimal balance,
			BigDecimal balanceProfit, BigDecimal balanceFrozen,
			BigDecimal balanceProfitFrozen) {
		super();
		this.id = id;
		this.merChantId = merChantId;
		this.merName = merName;
		this.merMp = merMp;
		this.certNo = certNo;
		this.merStat = merStat;
		this.identifying = identifying;
		this.merChantFee = merChantFee;
		this.generationFee = generationFee;
		this.generationFeeRepayment = generationFeeRepayment;
		this.status = status;
		this.agentStatus = agentStatus;
		this.agentId = agentId;
		this.institutionId = institutionId;
		this.merType = merType;
		this.oneMerId = oneMerId;
		this.twoMerId = twoMerId;
		this.threeMerId = threeMerId;
		this.frozen = frozen;
		this.faceImgUrl = faceImgUrl;
		this.regDate = regDate;
		this.merStatTime = merStatTime;
		this.statusDate = statusDate;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.appId = appId;
		this.remarks = remarks;
		this.balance = balance;
		this.balanceProfit = balanceProfit;
		this.balanceFrozen = balanceFrozen;
		this.balanceProfitFrozen = balanceProfitFrozen;
	}

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

	public String getMerStat() {
		return merStat;
	}

	public void setMerStat(String merStat) {
		this.merStat = merStat;
	}

	public String getIdentifying() {
		return identifying;
	}

	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}

	public String getMerChantFee() {
		return merChantFee;
	}

	public void setMerChantFee(String merChantFee) {
		this.merChantFee = merChantFee;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getOneMerId() {
		return oneMerId;
	}

	public void setOneMerId(String oneMerId) {
		this.oneMerId = oneMerId;
	}

	public String getTwoMerId() {
		return twoMerId;
	}

	public void setTwoMerId(String twoMerId) {
		this.twoMerId = twoMerId;
	}

	public String getThreeMerId() {
		return threeMerId;
	}

	public void setThreeMerId(String threeMerId) {
		this.threeMerId = threeMerId;
	}

	public String getFrozen() {
		return frozen;
	}

	public void setFrozen(String frozen) {
		this.frozen = frozen;
	}

	public String getFaceImgUrl() {
		return faceImgUrl;
	}

	public void setFaceImgUrl(String faceImgUrl) {
		this.faceImgUrl = faceImgUrl;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getMerStatTime() {
		return merStatTime;
	}

	public void setMerStatTime(String merStatTime) {
		this.merStatTime = merStatTime;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getBalanceProfit() {
		return balanceProfit;
	}

	public void setBalanceProfit(BigDecimal balanceProfit) {
		this.balanceProfit = balanceProfit;
	}

	public BigDecimal getBalanceFrozen() {
		return balanceFrozen;
	}

	public void setBalanceFrozen(BigDecimal balanceFrozen) {
		this.balanceFrozen = balanceFrozen;
	}

	public BigDecimal getBalanceProfitFrozen() {
		return balanceProfitFrozen;
	}

	public void setBalanceProfitFrozen(BigDecimal balanceProfitFrozen) {
		this.balanceProfitFrozen = balanceProfitFrozen;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}
}
