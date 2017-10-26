package com.edt.dao;

import com.edt.entity.SmsValidateCode;

public interface SmsValidateCodeDao {
    /**
     * 保存验证码
     *
     * @param smsValidateCode smsValidateCode
     * @author 刘钢 2017/5/17 22:52
     */
    void saveSmsValidateCode(SmsValidateCode smsValidateCode);

    /**
     * 修改验证码
     *
     * @param smsValidateCode smsValidateCode
     * @author 刘钢 2017/5/17 22:53
     */
    void updateSmsValidateCode(SmsValidateCode smsValidateCode);

    /**
     * 根据手机号码查询验证码
     *
     * @param phone phone
     * @return com.edt.entity.ValidateCode
     * @author 刘钢 2017/5/17 22:53
     */
    SmsValidateCode getSmsValidateCodeByPhone(String phone);

}
