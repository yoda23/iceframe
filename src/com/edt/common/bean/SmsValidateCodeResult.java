package com.edt.common.bean;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SmsValidateCodeResult {
    public static final Integer CODERESULT_SUCCESS = 0;
    public static final Integer CODERESULT_TIMEOUT = 1;
    public static final Integer CODERESULT_CODEERROR = 2;
    public static final Integer CODERESULT_PHONE_NOTEXIST = 3;
    public static final Map<Integer, String> CodeMessage;
    private Boolean success = false;
    private Integer code;

    static {
        Map<Integer, String> temp = new HashMap<Integer, String>();
        temp.put(CODERESULT_SUCCESS, "验证通过");
        temp.put(CODERESULT_TIMEOUT, "验证码已过期");
        temp.put(CODERESULT_CODEERROR, "验证码错误");
        temp.put(CODERESULT_PHONE_NOTEXIST, "手机号码不存在");
        CodeMessage = Collections.unmodifiableMap(temp);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
