package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "stb_area")
public class Area implements Serializable {

	private static final long serialVersionUID = -2362329501221054210L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "parent_id")
	private String parent_id;
	@Column(name = "area_name")
	private String area_name;
	@Column(name = "code")
	private String code;
	@Column(name = "level")
	private String level;
	@Column(name = "is_last")
	private String is_last;
	
	public Area() {
		super();
	}

	public Area(Long id, String parent_id, String area_name, String code,
			String level, String is_last) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.area_name = area_name;
		this.code = code;
		this.level = level;
		this.is_last = is_last;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIs_last() {
		return is_last;
	}

	public void setIs_last(String is_last) {
		this.is_last = is_last;
	}
}
