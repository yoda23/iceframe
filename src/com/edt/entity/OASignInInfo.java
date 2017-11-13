package com.edt.entity;

import java.io.Serializable;
import java.util.Date;

public class OASignInInfo  implements Serializable{
    private static final long serialVersionUID = -2335203633435937437L;

    private String id;
    private String signInName;
    private int signInState;
    private Date signInDate;
    private Date startDate;
    private Date endDate;
    private String remark;
    private Integer lateMinutes;
    private Integer addMinutes;
    private Integer leaveMinutes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(String signInName) {
        this.signInName = signInName;
    }

    public int getSignInState() {
        return signInState;
    }

    public void setSignInState(int signInState) {
        this.signInState = signInState;
    }

    public Date getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLateMinutes() {
        return lateMinutes;
    }

    public void setLateMinutes(Integer lateMinutes) {
        this.lateMinutes = lateMinutes;
    }

    public Integer getAddMinutes() {
        return addMinutes;
    }

    public void setAddMinutes(Integer addMinutes) {
        this.addMinutes = addMinutes;
    }

    public Integer getLeaveMinutes() {
        return leaveMinutes;
    }

    public void setLeaveMinutes(Integer leaveMinutes) {
        this.leaveMinutes = leaveMinutes;
    }

}