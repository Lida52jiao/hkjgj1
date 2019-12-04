package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_agent")
public class Agent implements Serializable {

	private static final long serialVersionUID = 7953513582515383004L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merId")
	private String merId;
	@Column(name = "merName")
	private String merName;
	@Column(name = "merMp")
	private String merMp;
	@Column(name = "accountNumber")
	private String accountNumber;
	@Column(name = "password")
	private String password;
	@Column(name = "mailbox")
	private String mailbox;
	@Column(name = "oneMerId")
	private String oneMerId;
	@Column(name = "totalCode")
	private String totalCode;
	@Column(name = "generatedCode")
	private String generatedCode;
	@Column(name = "used")
	private String used;
	@Column(name = "notused")
	private String notused;
	@Column(name = "assign")
	private String assign;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "remarks")
	private String remarks;
	
	public Agent() {
		super();
	}
	
	public Agent(String merName, String merMp,
			String accountNumber, String password, String mailbox,
			String oneMerId, String creatDate) {
		super();
		this.merName = merName;
		this.merMp = merMp;
		this.accountNumber = accountNumber;
		this.password = password;
		this.mailbox = mailbox;
		this.oneMerId = oneMerId;
		this.creatDate = creatDate;
	}
	
	public Agent(String totalCode, String generatedCode, String used,
			String notused, String assign) {
		super();
		this.totalCode = totalCode;
		this.generatedCode = generatedCode;
		this.used = used;
		this.notused = notused;
		this.assign = assign;
	}

	public Agent(Long id, String merId, String merName, String merMp,
			String accountNumber, String password, String mailbox,
			String oneMerId, String totalCode, String generatedCode,
			String used, String notused, String assign, String creatDate,
			String remarks) {
		super();
		this.id = id;
		this.merId = merId;
		this.merName = merName;
		this.merMp = merMp;
		this.accountNumber = accountNumber;
		this.password = password;
		this.mailbox = mailbox;
		this.oneMerId = oneMerId;
		this.totalCode = totalCode;
		this.generatedCode = generatedCode;
		this.used = used;
		this.notused = notused;
		this.assign = assign;
		this.creatDate = creatDate;
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getOneMerId() {
		return oneMerId;
	}

	public void setOneMerId(String oneMerId) {
		this.oneMerId = oneMerId;
	}

	public String getTotalCode() {
		return totalCode;
	}

	public void setTotalCode(String totalCode) {
		this.totalCode = totalCode;
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getNotused() {
		return notused;
	}

	public void setNotused(String notused) {
		this.notused = notused;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
}
