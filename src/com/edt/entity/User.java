package com.edt.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 用户信息
 */
public class User extends Log implements Serializable {

	private static final long serialVersionUID = -8764745528293684510L;
	private String id;
	private String mechanismsId;
	private String roleId;
	private String loginId;
	private String loginPassword;
	@Excel(name = "name")
	private String name;
	private Integer active;
	private Integer find;
	private Integer deleteFlag;
	private String addUser;
	private Date addTime;
	private Role role;
	private Mechanisms mechanisms;
	private String mechanismsIdCheck;
	private List<String> listMechanisms = new ArrayList<>();
	// ------------前台展示-------------
	private String userRoleName;
	private String activeDisplay;
	private String oldPassword;
	private String mechanismsName;
	private String findDisplay;

	public List<String> getListMechanisms() {
		return listMechanisms;
	}

	public void setListMechanisms(List<String> listMechanisms) {
		this.listMechanisms = listMechanisms;
	}

	public String getMechanismsIdCheck() {
		return mechanismsIdCheck;
	}

	public void setMechanismsIdCheck(String mechanismsIdCheck) {
		this.mechanismsIdCheck = mechanismsIdCheck;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getActiveDisplay() {
		return activeDisplay;
	}

	public void setActiveDisplay(String activeDisplay) {
		this.activeDisplay = activeDisplay;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMechanismsId() {
		return mechanismsId;
	}

	public void setMechanismsId(String mechanismsId) {
		this.mechanismsId = mechanismsId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getFind() {
		return find;
	}

	public void setFind(Integer find) {
		this.find = find;
	}

	public String getMechanismsName() {
		return mechanismsName;
	}

	public void setMechanismsName(String mechanismsName) {
		this.mechanismsName = mechanismsName;
	}

	public String getFindDisplay() {
		return findDisplay;
	}

	public void setFindDisplay(String findDisplay) {
		this.findDisplay = findDisplay;
	}

	public Mechanisms getMechanisms() {
		return mechanisms;
	}

	public void setMechanisms(Mechanisms mechanisms) {
		this.mechanisms = mechanisms;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
