package com.edt.controller.phoneoperator;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.edt.common.BaseController;
import com.edt.common.bean.ActionResult;
import com.edt.entity.PhoneOperator;
import com.edt.service.PhoneOperatorService;
import com.iceutils.random.IceRandomUtils;
import com.iceutils.string.IceStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/phoneOperator")
public class PhoneOperatorController extends BaseController {
	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;

	/**
	 * 按照条件查询运营商号码
	 *
	 * @param condition
	 *            condition
	 * @author 刘钢 2017/6/11 22:07
	 */
	@RequestMapping("getPhoneOperatorByCondition")
	@ResponseBody
	public void getPhoneOperatorByRowsByAll(PhoneOperatorCondition condition) {
		condition.setRows(condition.getLength());
		condition.setPage(condition.getStart() / condition.getLength() + 1);
		if (IceStringUtils.isNotBlank(condition.getName())) {
			condition.setName(condition.getName());
		}

		List<PhoneOperator> listPhoneOperator = phoneOperatorService
				.getListPhoneOperatorByCondition(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", condition.getDraw());
		map.put("recordsTotal", condition.getTotalRows());
		map.put("recordsFiltered", condition.getTotalRows());
		map.put("data", listPhoneOperator);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				PhoneOperator.class, "id", "prefix", "operatorName");
		WriterToPageByJsonByFilter(map, filter);
	}

	@Resource
	private PhoneOperatorService phoneOperatorService;

	/**
	 * 删除运营商号码识别信息
	 *
	 * @param phoneOperator
	 *            phoneOperator
	 * @author 刘钢 2017/6/11 22:08
	 */
	@RequestMapping("deletePhoneOperator")
	@ResponseBody
	public void deletePhoneOperator(PhoneOperator phoneOperator) {
		ActionResult actionResult = phoneOperatorService
				.deletePhoneOperator(phoneOperator.getId());
		WriterToPageByJson(actionResult);
	}

	/**
	 * 保存运营商号码识别信息
	 *
	 * @param phoneOperator
	 *            phoneOperator
	 * @author 刘钢 2017/6/11 22:08
	 */
	@RequestMapping("savePhoneOperator")
	@ResponseBody
	public void savePhoneOperator(PhoneOperator phoneOperator) {
		phoneOperator.setId(IceRandomUtils.getLongUUID());
		ActionResult actionResult = phoneOperatorService
				.savePhoneOperator(phoneOperator);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 根据ID查询运营商号码识别信息，跳转修改页面
	 *
	 * @param phoneOperator
	 *            phoneOperator
	 * @return java.lang.String
	 * @author 刘钢 2017/6/11 22:08
	 */
	@RequestMapping("toUpdatePhoneOperator")
	public String toUpdatePhoneOperator(PhoneOperator phoneOperator,
			Model model) {
		phoneOperator = phoneOperatorService
				.getPhoneOperatorById(phoneOperator.getId());
		if (phoneOperator != null) {
			model.addAttribute(phoneOperator);
			return "phoneOperator/phoneOperatorUpdate";
		} else {
			return "none";
		}
	}

	/**
	 * 修改运营商号码识别信息
	 *
	 * @param phoneOperator
	 *            phoneOperator
	 * @author 刘钢 2017/6/11 22:08
	 */
	@RequestMapping("updatePhoneOperator")
	@ResponseBody
	public void updatePhoneOperator(PhoneOperator phoneOperator) {
		ActionResult actionResult = phoneOperatorService
				.updatePhoneOperator(phoneOperator);
		WriterToPageByJson(actionResult);
	}
}
