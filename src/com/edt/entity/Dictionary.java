package com.edt.entity;

import java.io.Serializable;

public class Dictionary implements Serializable {

    private static final long serialVersionUID = -5469750764180534241L;
    private String dKey;
    private String dValue;

    public String getdKey() {
        return dKey;
    }

    public void setdKey(String dKey) {
        this.dKey = dKey;
    }

    public String getdValue() {
        return dValue;
    }

    public void setdValue(String dValue) {
        this.dValue = dValue;
    }

    @Override
    public String toString() {
        return "Dictionary{" + "dKey='" + dKey + '\'' + ", dValue='" + dValue + '\'' + '}';
    }
}
