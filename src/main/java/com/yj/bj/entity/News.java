package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_news")
public class News implements Serializable {

	private static final long serialVersionUID = -3029706525258455845L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "msg")
	private String msg;
	@Column(name = "creatDate")
	private String creatDate;
	
	public News() {
		super();
	}
	
	public News(String merChantId, String msg, String creatDate) {
		super();
		this.merChantId = merChantId;
		this.msg = msg;
		this.creatDate = creatDate;
	}

	public News(Long id, String merChantId, String msg, String creatDate) {
		super();
		this.id = id;
		this.merChantId = merChantId;
		this.msg = msg;
		this.creatDate = creatDate;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
}
