package com.edt.common.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 条件查询基础类
 *
 * @author 刘钢
 */
public class FindCondition implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2132944047590456924L;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	// 页数
	private Integer page;
	// 行数
	private Integer rows;
	// jquery datatables 请求次数
	private String draw;
	// jquery datatables 单页长度
	private Integer length;
	// jquery datatables 从第几条记录开始
	private Integer start;
	// 总记录条数
	private Long totalRows;
	// 所属机构标示
	private String mechanismsFlag;
	// 用户查询权限
	private Integer userFind;
	// 添加人ID
	private String addUser;
	// 当前登录账号的主键ID
	private String userId;
	// 所属机构ID
	private String mechanismsId;

	public String getMechanismsId() {
		return mechanismsId;
	}

	public void setMechanismsId(String mechanismsId) {
		this.mechanismsId = mechanismsId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getMechanismsFlag() {
		return mechanismsFlag;
	}

	public void setMechanismsFlag(String mechanismsFlag) {
		this.mechanismsFlag = mechanismsFlag;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getUserFind() {
		return userFind;
	}

	public void setUserFind(Integer userFind) {
		this.userFind = userFind;
	}

}
