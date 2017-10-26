package com.edt.controller.mechanisms;

import java.io.Serializable;

import com.edt.common.bean.FindCondition;

public class MechanismsCondition extends FindCondition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 170730431738695628L;

	/*---------机构条件----------------*/
	// 机构名称
	private String mechanismsName;
	// 机构父节点名称
	private String mechanismsParentName;
	// 机构父节点ID
	private String mechanismsParentId;
	public String getMechanismsName() {
		return mechanismsName;
	}
	public void setMechanismsName(String mechanismsName) {
		this.mechanismsName = mechanismsName;
	}
	public String getMechanismsParentName() {
		return mechanismsParentName;
	}
	public void setMechanismsParentName(String mechanismsParentName) {
		this.mechanismsParentName = mechanismsParentName;
	}
	public String getMechanismsParentId() {
		return mechanismsParentId;
	}
	public void setMechanismsParentId(String mechanismsParentId) {
		this.mechanismsParentId = mechanismsParentId;
	}
}
