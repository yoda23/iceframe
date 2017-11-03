package com.edt.entity;

public class OpenIdResult {
    private int totla;
    private int count;
    private OpenIdData data;
    private String next_openid;

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    public int getTotla() {
        return totla;
    }

    public void setTotla(int totla) {
        this.totla = totla;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OpenIdData getData() {
        return data;
    }

    public void setData(OpenIdData data) {
        this.data = data;
    }
}
