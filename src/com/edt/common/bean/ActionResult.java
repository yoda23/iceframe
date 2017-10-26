package com.edt.common.bean;

/**
 * 前台页面返回结果类
 *
 * @author 刘钢
 */
public class ActionResult {

    private boolean success = false;
    private String message;
    private Object resultList;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResultList() {
        return resultList;
    }

    public void setResultList(Object resultList) {
        this.resultList = resultList;
    }

}
