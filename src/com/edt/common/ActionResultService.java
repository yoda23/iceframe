package com.edt.common;

import org.springframework.stereotype.Component;

import com.edt.common.bean.ActionResult;

@Component
public class ActionResultService {

	/**
	 * @param success
	 *            success
	 * @param msg
	 *            msg
	 * @param resultList
	 *            resultList
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-08-07 8:47
	 */

	public ActionResult callBackResult(boolean success, String msg,
			Object resultList) {
		ActionResult actionResult = new ActionResult();
		actionResult.setSuccess(success);
		actionResult.setMessage(msg);
		actionResult.setResultList(resultList);
		return actionResult;
	}
}
