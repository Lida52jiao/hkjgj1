package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_jidao")
public class JiDao implements Serializable {

	private static final long serialVersionUID = 8171142372680377698L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "merName")
	private String merName;
	@Column(name = "cardNumber")
	private String cardNumber;
	@Column(name = "createDate")
	private String createDate;
	@Column(name = "appId")
	private String appId;
	
	public JiDao() {
		super();
	}

	public JiDao(String merChantId, String merName, String cardNumber,
			String createDate) {
		super();
		this.merChantId = merChantId;
		this.merName = merName;
		this.cardNumber = cardNumber;
		this.createDate = createDate;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
