package com.edt.entity;

import java.io.Serializable;

public class PhoneOperator implements Serializable {

    private static final long serialVersionUID = 4370637462208246356L;

    private String id;
    private String prefix;//手机号码前缀
    private String operator;//运营商名称

    //--------前台显示----------
    private String operatorName;


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    @Override
    public String toString() {
        return "PhoneOperator{" + "id='" + id + '\'' + ", prefix='" + prefix + '\'' + ", operator='" + operator +
                '\'' + ", operatorName='" + operatorName + '\'' + '}';
    }
}
