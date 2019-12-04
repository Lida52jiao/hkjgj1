package com.yj.bj.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_image")
public class Image implements Serializable {

	private static final long serialVersionUID = -7346949094430240819L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merChantId")
	private String merChantId;
	@Column(name = "userIDCardA")
	private String userIDCardA;
	@Column(name = "userIDCardB")
	private String userIDCardB;
	@Column(name = "cardImgA")
	private String cardImgA;
	
	public Image() {
		super();
	}

	public Image(Long id, String merChantId, String userIDCardA,
			String userIDCardB, String cardImgA) {
		super();
		this.id = id;
		this.merChantId = merChantId;
		this.userIDCardA = userIDCardA;
		this.userIDCardB = userIDCardB;
		this.cardImgA = cardImgA;
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

	public String getUserIDCardA() {
		return userIDCardA;
	}

	public void setUserIDCardA(String userIDCardA) {
		this.userIDCardA = userIDCardA;
	}

	public String getUserIDCardB() {
		return userIDCardB;
	}

	public void setUserIDCardB(String userIDCardB) {
		this.userIDCardB = userIDCardB;
	}

	public String getCardImgA() {
		return cardImgA;
	}

	public void setCardImgA(String cardImgA) {
		this.cardImgA = cardImgA;
	}
}
