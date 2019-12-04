package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "t_mp_reward")
public class Reward implements Serializable {

	private static final long serialVersionUID = -6524023664755420730L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "institutionId")
	private BigDecimal institutionId;
	@Column(name = "firstAgent")
	private BigDecimal firstAgent;
	@Column(name = "secondAgent")
	private BigDecimal secondAgent;
	@Column(name = "member")
	private BigDecimal member;
	@Column(name = "referee")
	private BigDecimal referee;
	@Column(name = "appId")
	private String appId;
	
	public Reward() {
		super();
	}

	public Reward(String agentId, BigDecimal firstAgent,
			BigDecimal secondAgent, BigDecimal member, BigDecimal referee) {
		super();
		this.agentId = agentId;
		this.firstAgent = firstAgent;
		this.secondAgent = secondAgent;
		this.member = member;
		this.referee = referee;
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

	public BigDecimal getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(BigDecimal institutionId) {
		this.institutionId = institutionId;
	}

	public BigDecimal getFirstAgent() {
		return firstAgent;
	}

	public void setFirstAgent(BigDecimal firstAgent) {
		this.firstAgent = firstAgent;
	}

	public BigDecimal getSecondAgent() {
		return secondAgent;
	}

	public void setSecondAgent(BigDecimal secondAgent) {
		this.secondAgent = secondAgent;
	}

	public BigDecimal getMember() {
		return member;
	}

	public void setMember(BigDecimal member) {
		this.member = member;
	}

	public BigDecimal getReferee() {
		return referee;
	}

	public void setReferee(BigDecimal referee) {
		this.referee = referee;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
