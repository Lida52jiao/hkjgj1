package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "t_mp_agentRate")
public class AgentRate implements Serializable {

	private static final long serialVersionUID = -4629247839400849175L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "rate")
	private BigDecimal rate;
	@Column(name = "d0Fee")
	private Long d0Fee;
	@Column(name = "aisleCode")
    private String aisleCode;
	
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
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Long getD0Fee() {
		return d0Fee;
	}
	public void setD0Fee(Long d0Fee) {
		this.d0Fee = d0Fee;
	}
	public String getAisleCode() {
		return aisleCode;
	}
	public void setAisleCode(String aisleCode) {
		this.aisleCode = aisleCode;
	}
}
