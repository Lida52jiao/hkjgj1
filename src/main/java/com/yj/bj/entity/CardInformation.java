package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_cardinformation")
public class CardInformation implements Serializable {

	private static final long serialVersionUID = 95298818389797450L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cardId", unique = true, nullable = false)
	private Long cardId;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "cardType")
	private String cardType;
	@Column(name = "cardNumber")
	private String cardNumber;
	@Column(name = "merMp")
	private String merMp;
	@Column(name = "issuingBank")
	private String issuingBank;
	@Column(name = "statementDate")
	private String statementDate;
	@Column(name = "repaymentDate")
	private String repaymentDate;
	@Column(name = "cv2")
	private String cv2;
	@Column(name = "effectiveYear")
	private String effectiveYear;
	@Column(name = "effectiveMonth")
	private String effectiveMonth;
	@Column(name = "sign")
	private String sign;
	@Column(name = "cardDefault")
	private String cardDefault;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "bindId")
	private String bindId;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "institutionId")
	private String institutionId;
	@Column(name = "appId")
	private String appId;
	@Column(name = "remarks")
	private String remarks;

	public CardInformation() {
		super();
	}

	public CardInformation(String merChantId, String cardType,
			String cardNumber, String merMp, String issuingBank,
			String statementDate, String repaymentDate, String cv2,
			String effectiveYear, String effectiveMonth, String cardDefault,
			String creatDate, String remarks) {
		super();
		this.merChantId = merChantId;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.merMp = merMp;
		this.issuingBank = issuingBank;
		this.statementDate = statementDate;
		this.repaymentDate = repaymentDate;
		this.cv2 = cv2;
		this.effectiveYear = effectiveYear;
		this.effectiveMonth = effectiveMonth;
		this.cardDefault = cardDefault;
		this.creatDate = creatDate;
		this.remarks = remarks;
	}

	public CardInformation(Long cardId, String merChantId, String cardType,
			String cardNumber, String merMp, String issuingBank,
			String statementDate, String repaymentDate, String cv2,
			String effectiveYear, String effectiveMonth, String cardDefault,
			String creatDate, String bindId, String remarks) {
		super();
		this.cardId = cardId;
		this.merChantId = merChantId;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.merMp = merMp;
		this.issuingBank = issuingBank;
		this.statementDate = statementDate;
		this.repaymentDate = repaymentDate;
		this.cv2 = cv2;
		this.effectiveYear = effectiveYear;
		this.effectiveMonth = effectiveMonth;
		this.cardDefault = cardDefault;
		this.creatDate = creatDate;
		this.bindId = bindId;
		this.remarks = remarks;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getMerMp() {
		return merMp;
	}

	public void setMerMp(String merMp) {
		this.merMp = merMp;
	}

	public String getIssuingBank() {
		return issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}

	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getCv2() {
		return cv2;
	}

	public void setCv2(String cv2) {
		this.cv2 = cv2;
	}

	public String getEffectiveYear() {
		return effectiveYear;
	}

	public void setEffectiveYear(String effectiveYear) {
		this.effectiveYear = effectiveYear;
	}

	public String getEffectiveMonth() {
		return effectiveMonth;
	}

	public void setEffectiveMonth(String effectiveMonth) {
		this.effectiveMonth = effectiveMonth;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCardDefault() {
		return cardDefault;
	}

	public void setCardDefault(String cardDefault) {
		this.cardDefault = cardDefault;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
