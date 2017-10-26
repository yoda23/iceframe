package com.edt.service.impl;

import com.edt.common.ActionResultService;
import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.common.constant.CommonConstant;
import com.edt.dao.PhoneOperatorDao;
import com.edt.entity.PhoneOperator;
import com.edt.service.PhoneOperatorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iceutils.string.IceStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PhoneOperatorServiceImpl implements PhoneOperatorService {

	@Resource
	private PhoneOperatorDao phoneOperatorDao;
	@Resource
	private ActionResultService actionResultService;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<PhoneOperator> getListPhoneOperatorByCondition(
			FindCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getRows());

		List<PhoneOperator> phoneOperatorList = this.phoneOperatorDao
				.getPhoneOperatorByCondition(condition);
		for (PhoneOperator phoneOperator : phoneOperatorList) {
			switch (phoneOperator.getOperator()) {
			case "1":
				phoneOperator.setOperatorName("中国联通");
				break;
			case "2":
				phoneOperator.setOperatorName("中国移动");
				break;
			case "3":
				phoneOperator.setOperatorName("中国电信");
				break;
			default:
				phoneOperator.setOperatorName("");
			}
		}
		PageInfo<PhoneOperator> pageInfo = new PageInfo<>(phoneOperatorList);
		condition.setTotalRows(pageInfo.getTotal());
		return phoneOperatorList;
	}

	@Transactional
	@Override
	public ActionResult deletePhoneOperator(String id) {
		this.phoneOperatorDao.deletePhoneOperator(id);
		return actionResultService.callBackResult(
				CommonConstant.ACTIONRESULT_TRUE, "运营商号码识别信息删除成功", null);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public PhoneOperator getPhoneOperatorById(String id) {
		return this.phoneOperatorDao.getPhoneOperatorById(id);
	}

	@Transactional
	@Override
	public ActionResult savePhoneOperator(PhoneOperator phoneOperator) {
		ActionResult actionResult;
		PhoneOperator checkPhoneOperator = getPhoneOperatorByPrefix(
				phoneOperator.getPrefix());
		if (checkPhoneOperator == null) {
			this.phoneOperatorDao.savePhoneOperator(phoneOperator);
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "运营商号码识别信息保存成功", null);
		} else {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "号段重复", null);
		}
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult updatePhoneOperator(PhoneOperator phoneOperator) {
		ActionResult actionResult;
		PhoneOperator checkPhoneOperator = getPhoneOperatorByPrefix(
				phoneOperator.getPrefix());
		if (checkPhoneOperator != null && !IceStringUtils
				.equals(phoneOperator.getId(), checkPhoneOperator.getId())) {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "号段重复", null);
		} else {
			this.phoneOperatorDao.updatePhoneOperator(phoneOperator);
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_TRUE, "运营商号码识别信息修改成功", null);
			actionResult.setSuccess(CommonConstant.ACTIONRESULT_TRUE);
		}
		return actionResult;
	}

	/**
	 * 验证运营商号码识别信息号码前缀是否重复
	 *
	 * @param prefix
	 *            prefix
	 * @return com.edt.entity.PhoneOperator
	 * @author 刘钢 2017/5/21 14:50
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PhoneOperator getPhoneOperatorByPrefix(String prefix) {
		if (IceStringUtils.isBlank(prefix)
				|| IceStringUtils.length(prefix) < 3) {
			return null;
		}
		PhoneOperator phoneOperator = this.phoneOperatorDao
				.getPhoneOperatorByPrefix(
						IceStringUtils.substring(prefix, 0, 3));
		if (phoneOperator != null) {

			switch (phoneOperator.getOperator()) {
			case "1":
				phoneOperator.setOperatorName("中国联通");
				break;
			case "2":
				phoneOperator.setOperatorName("中国移动");
				break;
			case "3":
				phoneOperator.setOperatorName("中国电信");
				break;
			default:
				phoneOperator.setOperatorName("未知数据");
				break;
			}
		}
		return phoneOperator;
	}

}
