package com.edt.entity.wechat;



import java.io.Serializable;
import java.util.Date;

public class AccessTokenResult implements Serializable {
    private static final long serialVersionUID = 7771403123199270068L;

    private ErrorInfo errorInfo;
    private String access_token;
    private Integer expires_in;
    private Date addTime;

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
