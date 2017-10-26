package com.edt.controller.phoneoperator;

import com.edt.common.bean.FindCondition;

import java.io.Serializable;

public class PhoneOperatorCondition extends FindCondition implements Serializable {

	private static final long serialVersionUID = 1296628612240691021L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
