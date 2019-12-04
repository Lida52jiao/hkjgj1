package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_duanxin")
public class DuanXin implements Serializable {

	private static final long serialVersionUID = -5457501506783144904L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "accessKeyId")
	private String accessKeyId;
	@Column(name = "accessKeySecret")
	private String accessKeySecret;
	@Column(name = "product")
	private String product;
	@Column(name = "domain")
	private String domain;
	@Column(name = "autograph")
	private String autograph;
	@Column(name = "templateCode")
	private String templateCode;
	@Column(name = "appId")
	private String appId;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "institutionId")
	private String institutionId;
	
	public DuanXin() {
		super();
	}
	
	public DuanXin(String accessKeyId, String accessKeySecret, String product,
			String domain, String autograph, String templateCode) {
		super();
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.product = product;
		this.domain = domain;
		this.autograph = autograph;
		this.templateCode = templateCode;
	}

	public DuanXin(Long id, String accessKeyId, String accessKeySecret,
			String product, String domain, String autograph,
			String templateCode, String appId, String agentId,
			String institutionId) {
		super();
		this.id = id;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.product = product;
		this.domain = domain;
		this.autograph = autograph;
		this.templateCode = templateCode;
		this.appId = appId;
		this.agentId = agentId;
		this.institutionId = institutionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
}
