package com.edt.entity;

import java.io.Serializable;

public class TpAppGx implements Serializable{

    private static final long serialVersionUID = -2801720502517965266L;

    private String id;
    private String tpId;
    private String appId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpId() {
        return tpId;
    }

    public void setTpId(String tpId) {
        this.tpId = tpId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
