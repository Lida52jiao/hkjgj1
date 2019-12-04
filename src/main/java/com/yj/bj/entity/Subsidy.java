package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_subsidy")
public class Subsidy implements Serializable {

	private static final long serialVersionUID = 1415199291176185086L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "twoAgent")
	private String twoAgent;
	@Column(name = "countryAgent")
	private String countryAgent;
	@Column(name = "cityAgent")
	private String cityAgent;
	@Column(name = "provinceAgent")
	private String provinceAgent;
	@Column(name = "oneMerchant")
	private String oneMerchant;
	@Column(name = "twoMerchant")
	private String twoMerchant;
	@Column(name = "topAgent")
	private String topAgent;
	
	public Subsidy() {
		super();
	}

	public Subsidy(Long id, String twoAgent, String countryAgent,
                   String cityAgent, String provinceAgent) {
		super();
		this.id = id;
		this.twoAgent = twoAgent;
		this.countryAgent = countryAgent;
		this.cityAgent = cityAgent;
		this.provinceAgent = provinceAgent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTwoAgent() {
		return twoAgent;
	}

	public void setTwoAgent(String twoAgent) {
		this.twoAgent = twoAgent;
	}

	public String getCountryAgent() {
		return countryAgent;
	}

	public void setCountryAgent(String countryAgent) {
		this.countryAgent = countryAgent;
	}

	public String getCityAgent() {
		return cityAgent;
	}

	public void setCityAgent(String cityAgent) {
		this.cityAgent = cityAgent;
	}

	public String getProvinceAgent() {
		return provinceAgent;
	}

	public void setProvinceAgent(String provinceAgent) {
		this.provinceAgent = provinceAgent;
	}

	public String getOneMerchant() {
		return oneMerchant;
	}

	public void setOneMerchant(String oneMerchant) {
		this.oneMerchant = oneMerchant;
	}

	public String getTwoMerchant() {
		return twoMerchant;
	}

	public void setTwoMerchant(String twoMerchant) {
		this.twoMerchant = twoMerchant;
	}

	public String getTopAgent() {
		return topAgent;
	}

	public void setTopAgent(String topAgent) {
		this.topAgent = topAgent;
	}

}
