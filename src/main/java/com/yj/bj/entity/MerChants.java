package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "t_mp_merchants")
public class MerChants implements Serializable {

	private static final long serialVersionUID = -6306981216617674634L;
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
	@Column(name = "merStat")
	private String merStat;
	@Column(name = "identifying")
	private String identifying;
	@Column(name = "merChantFee")
	private String merChantFee;
	@Column(name = "generationFee")
	private String generationFee;
	@Column(name = "generationFeeRepayment")
	private String generationFeeRepayment;
	@Column(name = "status")
	private String status;
	@Column(name = "agentStatus")
	private String agentStatus;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "institutionId")
	private String institutionId;
	@Column(name = "merType")
	private String merType;
	@Column(name = "oneMerId")
	private String oneMerId;
	@Column(name = "twoMerId")
	private String twoMerId;
	@Column(name = "threeMerId")
	private String threeMerId;
	@Column(name = "province")
	private String province;
	@Column(name = "city")
	private String city;
	@Column(name = "county")
	private String county;
	@Column(name = "frozen")
	private String frozen;
	@Column(name = "faceImgUrl")
	private String faceImgUrl;
	@Column(name = "regDate")
	private String regDate;
	@Column(name = "merStatTime")
	private String merStatTime;
	@Column(name = "statusDate")
	private String statusDate;
	@Column(name = "startDate")
	private String startDate;
	@Column(name = "finishDate")
	private String finishDate;
	@Column(name = "appId")
	private String appId;
	@Column(name = "appName")
	private String appName;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "balance")
	private BigDecimal balance;
	@Column(name = "balanceProfit")
	private BigDecimal balanceProfit;
	@Column(name = "balanceFrozen")
	private BigDecimal balanceFrozen;
	@Column(name = "balanceProfitFrozen")
	private BigDecimal balanceProfitFrozen;
	@Column(name = "used")
	private String used;
	@Column(name = "isBind")
	private String isBind;
	@Column(name = "isNotUse")
	private Integer isNotUse;  //是否可用，1可用，2不可用

	public Integer getIsNotUse() {
		return isNotUse;
	}

	public void setIsNotUse(Integer isNotUse) {
		this.isNotUse = isNotUse;
	}

	public MerChants() {
		super();
	}

	public MerChants(String merChantId, String merName, String merMp,
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

	public MerChants(Long id, String merChantId, String merName, String merMp,
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
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

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getIsBind() {
		return isBind;
	}

	public void setIsBind(String isBind) {
		this.isBind = isBind;
	}
}
