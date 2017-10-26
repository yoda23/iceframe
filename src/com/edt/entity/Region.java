package com.edt.entity;

import java.io.Serializable;

/**
 * 行政区域实体
 *
 * @author 刘钢
 */
public class Region implements Serializable {

	private static final long serialVersionUID = -7375616931233902615L;
	private String id;
	private String code;
	private String province;
	private String city;
	private String area;
	private String pId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	@Override
	public String toString() {
		return "Region{" + "id='" + id + '\'' + ", code='" + code + '\''
				+ ", province='" + province + '\'' + ", " + "city='" + city
				+ '\'' + ", area='" + area + '\'' + ", pId='" + pId + '\''
				+ '}';
	}
}
