package com.edt.service;

import com.edt.common.bean.SmsValidateCodeResult;

public interface SmsValidateCodeService {

    /**
     * 生成短信验证码
     *
     * @param phone phone
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢 2017-05-18 11:54
     */

    String getSmsValidateCode(String phone);

    /**
     * 验证短信验证码
     *
     * @param phone phone
     * @param code  code
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢 2017-05-18 11:55Z
     */

    SmsValidateCodeResult checkSmsValidateCode(String phone, String code);

}
