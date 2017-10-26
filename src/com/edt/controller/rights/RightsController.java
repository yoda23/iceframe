package com.edt.controller.rights;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edt.common.BaseController;
import com.edt.entity.Rights;
import com.edt.service.RightsService;

@Controller
@RequestMapping("/rights")
public class RightsController extends BaseController {

	@Resource
	private RightsService rightsService;

	/**
	 * 获取所有权限
	 * 
	 * @author 奚艺轩 2017-6-1上午10:30:00
	 */
	@RequestMapping("getAllRights")
	@ResponseBody
	public void getAllRights() {
		List<Rights> listRights = rightsService.getListRights();
		WriterToPageByJson(listRights);
	}
}
