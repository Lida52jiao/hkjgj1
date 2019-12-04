package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_num")
public class Num implements Serializable {
	
	private static final long serialVersionUID = -7319080699351571502L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "level")
	private String level;
	//升级费
	@Column(name = "num")
	private String num;
	//推广人数
	@Column(name = "amount")
	private String amount;
	@Column(name = "payType")
	private String payType;
	@Column(name = "merType")
	private String merType;
	@Column(name = "validTime")
	private Long validTime;
	@Column(name = "levelLogo")
	private String levelLogo;
	@Column(name = "levelBCard")
	private String levelBCard;
	@Column(name = "funcExplain")
	private String funcExplain;
	@Column(name = "agentStatus")
	private String agentStatus;
	@Column(name = "showOrNot")
	private String showOrNot;
	@Column(name = "visitor")
	private Long visitor;
	@Column(name = "vip")
	private Long vip;
	@Column(name = "highVip")
	private Long highVip;
	@Column(name = "channel")
	private Long channel;
	@Column(name = "agent")
	private Long agent;
	@Column(name = "area")
	private Long area;
	@Column(name = "institution")
	private Long institution;
	@Column(name = "appId")
	private String appId;
	
	public Num() {
		super();
	}

	@Override
	public String toString() {
		return "Num{" +
				"id=" + id +
				", level='" + level + '\'' +
				", num='" + num + '\'' +
				", amount='" + amount + '\'' +
				", payType='" + payType + '\'' +
				", merType='" + merType + '\'' +
				", validTime=" + validTime +
				", levelLogo='" + levelLogo + '\'' +
				", levelBCard='" + levelBCard + '\'' +
				", funcExplain='" + funcExplain + '\'' +
				", agentStatus='" + agentStatus + '\'' +
				", showOrNot='" + showOrNot + '\'' +
				", visitor=" + visitor +
				", vip=" + vip +
				", highVip=" + highVip +
				", channel=" + channel +
				", agent=" + agent +
				", area=" + area +
				", institution=" + institution +
				", appId='" + appId + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public Long getValidTime() {
		return validTime;
	}

	public void setValidTime(Long validTime) {
		this.validTime = validTime;
	}

	public String getLevelLogo() {
		return levelLogo;
	}

	public void setLevelLogo(String levelLogo) {
		this.levelLogo = levelLogo;
	}

	public String getLevelBCard() {
		return levelBCard;
	}

	public void setLevelBCard(String levelBCard) {
		this.levelBCard = levelBCard;
	}

	public String getFuncExplain() {
		return funcExplain;
	}

	public void setFuncExplain(String funcExplain) {
		this.funcExplain = funcExplain;
	}

	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}

	public String getShowOrNot() {
		return showOrNot;
	}

	public void setShowOrNot(String showOrNot) {
		this.showOrNot = showOrNot;
	}

	public Long getVisitor() {
		return visitor;
	}

	public void setVisitor(Long visitor) {
		this.visitor = visitor;
	}

	public Long getVip() {
		return vip;
	}

	public void setVip(Long vip) {
		this.vip = vip;
	}

	public Long getHighVip() {
		return highVip;
	}

	public void setHighVip(Long highVip) {
		this.highVip = highVip;
	}

	public Long getChannel() {
		return channel;
	}

	public void setChannel(Long channel) {
		this.channel = channel;
	}

	public Long getAgent() {
		return agent;
	}

	public void setAgent(Long agent) {
		this.agent = agent;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Long getInstitution() {
		return institution;
	}

	public void setInstitution(Long institution) {
		this.institution = institution;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
