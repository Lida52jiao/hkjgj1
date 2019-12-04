package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

//在城市联动中存省用的
@Table(name = "t_mp_area")
public class Areas implements Serializable {

	private static final long serialVersionUID = -2362329501221054210L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "code")
	private String code;
	public Areas() {
		super();
	}

	public Areas(Long id, String code) {
		super();
		this.id = id;
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
