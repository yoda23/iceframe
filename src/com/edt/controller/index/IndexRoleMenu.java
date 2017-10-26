package com.edt.controller.index;

import java.util.List;


public class IndexRoleMenu {

	private String id;
	private String pid;
	private String name;
	private String openUrl;
	private List<IndexRoleMenu> listMenuChild;

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

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public List<IndexRoleMenu> getListMenuChild() {
		return listMenuChild;
	}

	public void setListMenuChild(List<IndexRoleMenu> listMenuChild) {
		this.listMenuChild = listMenuChild;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
