package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_introduce")
public class Introduce implements Serializable {

	private static final long serialVersionUID = -2112995802226349713L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "imgURL")
	private String imgURL;
	@Column(name = "titles")
	private String titles;
	@Column(name = "content")
	private String content;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "forwardURL")
	private String forwardURL;
	@Column(name = "remarks")
	private String remarks;
	
	public Introduce() {
		super();
	}

	public Introduce(String imgURL, String titles, String content,
			String creatDate, String forwardURL, String remarks) {
		super();
		this.imgURL = imgURL;
		this.titles = titles;
		this.content = content;
		this.creatDate = creatDate;
		this.forwardURL = forwardURL;
		this.remarks = remarks;
	}

	public Introduce(Long id, String imgURL, String titles, String content,
			String creatDate, String forwardURL, String remarks) {
		super();
		this.id = id;
		this.imgURL = imgURL;
		this.titles = titles;
		this.content = content;
		this.creatDate = creatDate;
		this.forwardURL = forwardURL;
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getForwardURL() {
		return forwardURL;
	}

	public void setForwardURL(String forwardURL) {
		this.forwardURL = forwardURL;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
