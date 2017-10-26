package com.edt.service.impl;

import com.edt.common.bean.SmsValidateCodeResult;
import com.edt.dao.SmsValidateCodeDao;
import com.edt.entity.SmsValidateCode;
import com.edt.service.SmsValidateCodeService;
import com.iceutils.date.IceDateOperationUtils;
import com.iceutils.random.IceRandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SmsValidateCodeServiceImpl implements SmsValidateCodeService {

	@Resource
	private SmsValidateCodeDao smsValidateCodeDao;

	@Transactional
	@Override
	public String getSmsValidateCode(String phone) {
		SmsValidateCode smsValidateCodePhone = smsValidateCodeDao
				.getSmsValidateCodeByPhone(phone);
		if (smsValidateCodePhone == null) {
			// 添加验证码
			smsValidateCodePhone = new SmsValidateCode();
			smsValidateCodePhone.setId(IceRandomUtils.getLongUUID());
			smsValidateCodePhone.setPhone(phone);
			smsValidateCodePhone.setCode(IceRandomUtils.randomNumeric(6));
			smsValidateCodePhone.setValidtime(new Date());
			smsValidateCodeDao.saveSmsValidateCode(smsValidateCodePhone);
		} else {
			// 修改验证码
			smsValidateCodePhone.setCode(IceRandomUtils.randomNumeric(6));
			smsValidateCodePhone.setValidtime(new Date());
			smsValidateCodeDao.updateSmsValidateCode(smsValidateCodePhone);
		}
		return smsValidateCodePhone.getCode();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public SmsValidateCodeResult checkSmsValidateCode(String phone, String code) {
		SmsValidateCodeResult smsValidateCodeResult = new SmsValidateCodeResult();
		SmsValidateCode smsValidateCode = smsValidateCodeDao
				.getSmsValidateCodeByPhone(phone);
		if (smsValidateCode != null) {
			// 输入的验证码和库里验证码是否一致
			if (smsValidateCode.getCode().equals(code)) {
				// 验证码是否失效
				int seconds = IceDateOperationUtils.betweenSecond(
						smsValidateCode.getValidtime(), new Date());
				if (seconds <= 180) {
					smsValidateCodeResult.setSuccess(true);
					smsValidateCodeResult
							.setCode(SmsValidateCodeResult.CODERESULT_SUCCESS);
				} else {
					smsValidateCodeResult.setSuccess(false);
					smsValidateCodeResult
							.setCode(SmsValidateCodeResult.CODERESULT_TIMEOUT);
				}
			} else {
				smsValidateCodeResult.setSuccess(false);
				smsValidateCodeResult
						.setCode(SmsValidateCodeResult.CODERESULT_CODEERROR);
			}
		} else {
			smsValidateCodeResult.setSuccess(false);
			smsValidateCodeResult
					.setCode(SmsValidateCodeResult.CODERESULT_PHONE_NOTEXIST);
		}
		return smsValidateCodeResult;
	}
}
