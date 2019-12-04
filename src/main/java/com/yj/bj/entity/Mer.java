package com.yj.bj.entity;


public class Mer {
	
	private String merChantId;
	
	private String status;

	private Long profit;
	
	public Mer() {
		super();
	}

	public Mer(String merChantId, String status) {
		super();
		this.merChantId = merChantId;
		this.status = status;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getProfit() {
		return profit;
	}

	public void setProfit(Long profit) {
		this.profit = profit;
	}
}
