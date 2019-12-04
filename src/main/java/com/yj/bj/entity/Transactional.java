package com.yj.bj.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "t_mp_transactional")
public class Transactional implements java.io.Serializable{
    
	private static final long serialVersionUID = -2793147155585068577L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    //创建时间

//			"remarks": null"tlId": 92211765,
//			"tlOrderNo": null,
//			"createTime": "2019-06-03 11:05:48.0",
//			"createDate": null,
//			"module": "repayment",
//			"type": "repSubsidize",
//			"merchantId": "M2018070714274708394",
//			"orderMerchantId": "M2018060315302987883",
//			"orderMerchantName": "刘满兰",
//			"orderMerchantPhone": "13788561070",
//			"agentId": "C2018091409432910025",
//			"institutionId": "T00000009",
//			"appId": "0000",
//			"orderNo": "LP578965423053246466",
//			"aisleCode": "ld05",
//			"planId": 294845,
//			"rate": "0.0002",
//			"level": "cityAgent",
//			"fee": 8,
//			"amount": 40000,
	@Column(name = "createTime")
	private String createTime;
	@Column(name = "module")
	private String module;
    //交易类型
    @Column(name = "type")
    private String type;
    //商户号
    @Column(name = "merchantId")
    private String merchantId;
    @Column(name = "orderMerchantId")
    private String orderMerchantId;
    @Column(name = "orderMerchantName")
    private String orderMerchantName;
    @Column(name = "orderMerchantPhone")
    private String orderMerchantPhone;
    //代理商
    @Column(name = "agentId")
    private String agentId;
    //机构号
    @Column(name = "institutionId")
    private String institutionId;
    @Column(name = "appId")
    private String appId;
    //订单号
    @Column(name = "orderNo")
    private String orderNo;
    //底价
    @Column(name = "rate")
    private String rate;
    @Column(name = "level")
    private String level;
    //分润手续费
    @Column(name = "fee")
    private Long fee;
    //交易金额
    @Column(name = "amount")
    private Long amount;
    @Column(name = "remarks")
    private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getOrderMerchantId() {
		return orderMerchantId;
	}

	public void setOrderMerchantId(String orderMerchantId) {
		this.orderMerchantId = orderMerchantId;
	}

	public String getOrderMerchantName() {
		return orderMerchantName;
	}

	public void setOrderMerchantName(String orderMerchantName) {
		this.orderMerchantName = orderMerchantName;
	}

	public String getOrderMerchantPhone() {
		return orderMerchantPhone;
	}

	public void setOrderMerchantPhone(String orderMerchantPhone) {
		this.orderMerchantPhone = orderMerchantPhone;
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
