package com.edt.controller.user;

import com.edt.common.bean.FindCondition;

import java.io.Serializable;

public class UserCondition extends FindCondition implements Serializable {

	private static final long serialVersionUID = -8441535925204681321L;
	/*---------用户条件----------------*/
	// 用户姓名
	private String userName;
	// 用户删除标记
	private Integer deleteFlag;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
