package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_record")
public class Record implements Serializable {

	private static final long serialVersionUID = -4591926873434703389L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "orderNo")
	private String orderNo;
	@Column(name = "remarks")
	private String remarks;
	
	public Record() {
		super();
	}
	
	public Record(String merChantId, String creatDate, String orderNo,
			String remarks) {
		super();
		this.merChantId = merChantId;
		this.creatDate = creatDate;
		this.orderNo = orderNo;
		this.remarks = remarks;
	}

	public Record(Long id, String merChantId, String creatDate, String orderNo,
			String remarks) {
		super();
		this.id = id;
		this.merChantId = merChantId;
		this.creatDate = creatDate;
		this.orderNo = orderNo;
		this.remarks = remarks;
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

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
