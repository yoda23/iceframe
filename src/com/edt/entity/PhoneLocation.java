package com.edt.entity;

import java.io.Serializable;

public class PhoneLocation implements Serializable {

    private static final long serialVersionUID = 4154792663361271120L;

    private String id;
    private String prefix;
    private String mobileType;
    private String phoneHeader;
    private String province;
    private String city;
    private String areaCode;
    private String postCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPhoneHeader() {
        return phoneHeader;
    }

    public void setPhoneHeader(String phoneHeader) {
        this.phoneHeader = phoneHeader;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    @Override
    public String toString() {
        return "PhoneLocation{" + "id='" + id + '\'' + ", prefix='" + prefix + '\'' + ", mobileType='" + mobileType +
                '\'' + ", phoneHeader='" + phoneHeader + '\'' + ", province='" + province + '\'' + ", city='" + city
                + '\'' + ", areaCode='" + areaCode + '\'' + ", postCode='" + postCode + '\'' + '}';
    }
}
