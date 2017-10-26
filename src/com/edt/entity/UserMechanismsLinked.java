package com.edt.entity;

/**
 * @author 刘钢 2017-07-06
 */
public class UserMechanismsLinked {
	private User user;
	private Mechanisms mechanisms;
	private String userId;
	private String mechanismsId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMechanismsId() {
		return mechanismsId;
	}

	public void setMechanismsId(String mechanismsId) {
		this.mechanismsId = mechanismsId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Mechanisms getMechanisms() {
		return mechanisms;
	}

	public void setMechanisms(Mechanisms mechanisms) {
		this.mechanisms = mechanisms;
	}
}
