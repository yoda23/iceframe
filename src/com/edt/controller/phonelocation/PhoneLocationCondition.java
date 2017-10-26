package com.edt.controller.phonelocation;

import com.edt.common.bean.FindCondition;

import java.io.Serializable;

public class PhoneLocationCondition extends FindCondition
		implements Serializable {
	private static final long serialVersionUID = 1178457355143383995L;
	private String phoneHeader;

	public String getPhoneHeader() {
		return phoneHeader;
	}

	public void setPhoneHeader(String phoneHeader) {
		this.phoneHeader = phoneHeader;
	}

}
