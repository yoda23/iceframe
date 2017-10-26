package com.edt.entity;

import java.io.Serializable;

public class Rights implements Serializable {

	private static final long serialVersionUID = -7810769625187127602L;
	private String id;
	private String name;
	private String parentId;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Rights{" + "id='" + id + '\'' + ", name='" + name + '\''
				+ ", parentId='" + parentId + '\'' + '}';
	}
}
