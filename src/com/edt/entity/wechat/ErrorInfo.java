package com.edt.entity.wechat;

import java.io.Serializable;

public class ErrorInfo  implements Serializable{

    private static final long serialVersionUID = 5958327508795160215L;
    private String errCode;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private String errMsg;

}
