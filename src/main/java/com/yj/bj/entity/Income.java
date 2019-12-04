package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "t_mp_income")
public class Income implements Serializable {

	private static final long serialVersionUID = -8892596013972471761L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "level")
	private String level;
	@Column(name = "first")
	private BigDecimal first;
	@Column(name = "second")
	private BigDecimal second;
	@Column(name = "brushFirst")
	private BigDecimal brushFirst;
	@Column(name = "brushSecond")
	private BigDecimal brushSecond;
	
	public Income() {
		super();
	}

	public Income(Long id, String level, BigDecimal first, BigDecimal second) {
		super();
		this.id = id;
		this.level = level;
		this.first = first;
		this.second = second;
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

	public BigDecimal getFirst() {
		return first;
	}

	public void setFirst(BigDecimal first) {
		this.first = first;
	}

	public BigDecimal getSecond() {
		return second;
	}

	public void setSecond(BigDecimal second) {
		this.second = second;
	}

	public BigDecimal getBrushFirst() {
		return brushFirst;
	}

	public void setBrushFirst(BigDecimal brushFirst) {
		this.brushFirst = brushFirst;
	}

	public BigDecimal getBrushSecond() {
		return brushSecond;
	}

	public void setBrushSecond(BigDecimal brushSecond) {
		this.brushSecond = brushSecond;
	}
}
