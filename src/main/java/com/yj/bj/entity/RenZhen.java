package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_renzhen")
public class RenZhen implements Serializable {

	private static final long serialVersionUID = -3164617941328377503L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "gatewayUrl")
	private String gatewayUrl;
	@Column(name = "appIds")
	private String appIds;
	@Column(name = "privateKey")
	private String privateKey;
	@Column(name = "zhimaPublicKey")
	private String zhimaPublicKey;
	@Column(name = "back")
	private String back;
	@Column(name = "appId")
	private String appId;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "institutionId")
	private String institutionId;
	
	public RenZhen() {
		super();
	}

	public RenZhen(String gatewayUrl, String appIds, String privateKey,
			String zhimaPublicKey, String back) {
		super();
		this.gatewayUrl = gatewayUrl;
		this.appIds = appIds;
		this.privateKey = privateKey;
		this.zhimaPublicKey = zhimaPublicKey;
		this.back = back;
	}

	public RenZhen(Long id, String gatewayUrl, String appIds,
			String privateKey, String zhimaPublicKey, String appId,
			String agentId, String institutionId) {
		super();
		this.id = id;
		this.gatewayUrl = gatewayUrl;
		this.appIds = appIds;
		this.privateKey = privateKey;
		this.zhimaPublicKey = zhimaPublicKey;
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

	public String getGatewayUrl() {
		return gatewayUrl;
	}

	public void setGatewayUrl(String gatewayUrl) {
		this.gatewayUrl = gatewayUrl;
	}

	public String getAppIds() {
		return appIds;
	}

	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getZhimaPublicKey() {
		return zhimaPublicKey;
	}

	public void setZhimaPublicKey(String zhimaPublicKey) {
		this.zhimaPublicKey = zhimaPublicKey;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
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
