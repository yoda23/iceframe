package com.edt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {
	private static final long serialVersionUID = -2505372373474014298L;
	private String id;
	private String name;
	private String reMark;
	private String addUser;
	private Date addTime;
	private User user;
	private List<User> listUser = new ArrayList<User>();
	private List<Menu> listMenu = new ArrayList<Menu>();
	private List<Rights> listRights = new ArrayList<Rights>();
	// ------------前台展示-------------
	private String menuId;
	private String rightsId;
	private String addUser_display;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRightsId() {
		return rightsId;
	}

	public void setRightsId(String rightsId) {
		this.rightsId = rightsId;
	}

	public List<Menu> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}

	public List<Rights> getListRights() {
		return listRights;
	}

	public void setListRights(List<Rights> listRights) {
		this.listRights = listRights;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReMark() {
		return reMark;
	}

	public void setReMark(String reMark) {
		this.reMark = reMark;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddUser_display() {
		return addUser_display;
	}

	public void setAddUser_display(String addUser_display) {
		this.addUser_display = addUser_display;
	}

	@Override
	public String toString() {
		return "Role{" + "id='" + id + '\'' + ", name='" + name + '\''
				+ ", reMark='" + reMark + '\'' + ", addUser='" + addUser + '\''
				+ ", addTime=" + addTime + ", user=" + user + ", listUser="
				+ listUser + ", " + "listMenu=" + listMenu + ", listRights="
				+ listRights + ", menuId='" + menuId + '\'' + ", rightsId='"
				+ rightsId + '\'' + ", addUser_display='" + addUser_display
				+ '\'' + '}';
	}
}
