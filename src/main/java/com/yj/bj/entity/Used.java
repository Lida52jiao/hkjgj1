package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_used")
public class Used implements Serializable {

	private static final long serialVersionUID = -7321398010816576900L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "month")
	private String month;
	@Column(name = "year")
	private String year;
	
	public Used() {
		super();
	}

	public Used(Long id, String month, String year) {
		super();
		this.id = id;
		this.month = month;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
