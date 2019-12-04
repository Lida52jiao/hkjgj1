package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_banner")
public class Banner implements Serializable {

	private static final long serialVersionUID = -4676207171548084750L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "imgURL")
	private String imgURL;
	@Column(name = "name")
	private String name;
	@Column(name = "forwardURL")
	private String forwardURL;
	@Column(name = "creatDate")
	private String creatDate;
	@Column(name = "status")
	private String status;
	@Column(name = "appId")
	private String appId;
	@Column(name = "remarks")
	private String remarks;
	
	public Banner() {
		super();
	}

	public Banner(String imgURL, String name, String forwardURL,
			String creatDate, String status, String remarks) {
		super();
		this.imgURL = imgURL;
		this.name = name;
		this.forwardURL = forwardURL;
		this.creatDate = creatDate;
		this.status = status;
		this.remarks = remarks;
	}

	public Banner(Long id, String imgURL, String name, String forwardURL,
			String creatDate, String status, String remarks) {
		super();
		this.id = id;
		this.imgURL = imgURL;
		this.name = name;
		this.forwardURL = forwardURL;
		this.creatDate = creatDate;
		this.status = status;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForwardURL() {
		return forwardURL;
	}

	public void setForwardURL(String forwardURL) {
		this.forwardURL = forwardURL;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
