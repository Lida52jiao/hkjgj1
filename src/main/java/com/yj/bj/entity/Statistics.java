package com.yj.bj.entity;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
	
	private String merChantId;
	
	private String merName;
	
	private String merMp;
	
	private String merType;
	
	private String agentStatus;
	
	private String statusDate;
	
	private int first;
	
	private int second;
	
	private List<Statistics> children = new ArrayList<>();

	public Statistics() {
		super();
	}

	public Statistics(String merChantId, int first, int second) {
		super();
		this.merChantId = merChantId;
		this.first = first;
		this.second = second;
	}

	public Statistics(String merChantId, String merName, String merMp,
			String merType, String agentStatus, String statusDate, int first,
			int second) {
		super();
		this.merChantId = merChantId;
		this.merName = merName;
		this.merMp = merMp;
		this.merType = merType;
		this.agentStatus = agentStatus;
		this.statusDate = statusDate;
		this.first = first;
		this.second = second;
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

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public List<Statistics> getChildren() {
		return children;
	}

	public void setChildren(List<Statistics> children) {
		this.children = children;
	}
}
