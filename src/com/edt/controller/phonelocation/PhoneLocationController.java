package com.edt.controller.phonelocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.edt.common.BaseController;
import com.edt.entity.PhoneLocation;
import com.edt.entity.PhoneOperator;
import com.edt.service.PhoneLocationService;
import com.iceutils.string.IceStringUtils;

@Controller
@RequestMapping("/phoneLocation")
public class PhoneLocationController extends BaseController {
	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;
	@Resource
	private PhoneLocationService phoneLocationService;

	/**
	 * 按照条件查询运营商号码
	 * 
	 * @param condition
	 *            condition
	 * @author 奚艺轩 2017-6-1上午10:19:05
	 */
	@RequestMapping("getPhoneLocationByCondition")
	@ResponseBody
	public void getPhoneLocationByCondition(PhoneLocationCondition condition) {
		condition.setRows(condition.getLength());
		condition.setPage(condition.getStart() / condition.getLength() + 1);
		if (IceStringUtils.isNotBlank(condition.getPhoneHeader())) {
			condition.setPhoneHeader(condition.getPhoneHeader());
		}
		List<PhoneLocation> listPhoneLocation = phoneLocationService
				.getPhoneLocationByCondition(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", condition.getDraw());
		map.put("recordsTotal", condition.getTotalRows());
		map.put("recordsFiltered", condition.getTotalRows());
		map.put("data", listPhoneLocation);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				PhoneOperator.class, "id", "prefix", "mobileType",
				"phoneHeader", "province", "city", "areaCode", "postCode");
		WriterToPageByJsonByFilter(map, filter);
	}

}
