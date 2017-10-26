package com.edt.entity;

import java.io.Serializable;

public class RoleRightsLinked implements Serializable {

	private static final long serialVersionUID = -6656506076247277659L;
	private Rights rights;
	private Role role;

	public Rights getRights() {
		return rights;
	}

	public void setRights(Rights rights) {
		this.rights = rights;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "RoleRightsLinked{" + "rights=" + rights + ", role=" + role
				+ '}';
	}
}
