package com.edt.entity;

import java.io.Serializable;

public class TpInfo extends Log implements Serializable{
    private static final long serialVersionUID = -1007047941207868375L;

    private String id;
    private String description;
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
