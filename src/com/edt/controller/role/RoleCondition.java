package com.edt.controller.role;

import com.edt.common.bean.FindCondition;

import java.io.Serializable;

public class RoleCondition extends FindCondition implements Serializable {

	private static final long serialVersionUID = -4281989386227853066L;

	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
