package com.edt.controller.mechanisms;

import com.edt.common.BaseController;
import com.edt.common.bean.ActionResult;
import com.edt.entity.Mechanisms;
import com.edt.entity.User;
import com.edt.service.MechanismsService;
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
import java.util.*;

@Controller
@RequestMapping("/mechanisms")
public class MechanismsController extends BaseController {
	@Resource
	private MechanismsService mechanismsService;
	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;

	/**
	 * 获取机构树形数据
	 *
	 * @author 刘钢 2017年4月3日上午11:55:14
	 */
	@RequestMapping("/getMechanismsForMenuTree")
	@ResponseBody
	public void getMechanismsForMenuTree(MechanismsCondition condition) {
		User user = (User) httpSession.getAttribute("USER");
		condition.setMechanismsId(user.getMechanisms().getId());
		condition.setRows(0);
		condition.setPage(1);
		condition.setAddUser(user.getId());
		List<Mechanisms> listMechanisms = mechanismsService
				.getListMechanismByCondition(condition);
		WriterToPageByJson(listMechanisms);
	}

	/**
	 * 根据UserId获取可显示的组织机构信息
	 *
	 * @author 刘钢 2017年4月3日上午11:55:14
	 */
	@RequestMapping("/getMechanismsForUserMenuTree")
	@ResponseBody
	public void getMechanismsForUserMenuTree() {
		User user = (User) httpSession.getAttribute("USER");
		String userId = httpServletRequest.getParameter("userId");
		List<Mechanisms> listMechanisms = new ArrayList<>();
		// 当前用户所在机构为根机构，具有最高权限
		if (user.getMechanismsId().equals("0")) {
			MechanismsCondition condition = new MechanismsCondition();
			condition.setMechanismsId(user.getMechanisms().getId());
			condition.setRows(0);
			condition.setPage(1);
			condition.setAddUser(user.getId());
			listMechanisms = mechanismsService
					.getListMechanismByCondition(condition);

		} else {
			listMechanisms = mechanismsService
					.listMechanismsByUserId(user.getId());
		}
		WriterToPageByJson(listMechanisms);
	}

	/**
	 * 获取查询条件获取机构信息
	 *
	 * @author 奚艺轩 2017-4-12 下午2:20:40
	 */
	@RequestMapping("getListMechanismByCondition")
	@ResponseBody
	public void getListMechanismByCondition(MechanismsCondition condition) {
		User user = (User) httpSession.getAttribute("USER");
		condition.setMechanismsId(user.getMechanisms().getId());
		condition.setRows(condition.getLength());
		condition.setPage(condition.getStart() / condition.getLength() + 1);
		condition.setUserId(user.getId());
		condition.setAddUser(user.getId());
		// 根据机构名称查询
		if (IceStringUtils.isNotBlank(condition.getMechanismsName())) {
			condition.setMechanismsName(condition.getMechanismsName());
		}
		List<Mechanisms> listMechanisms = mechanismsService
				.getListMechanismByCondition(condition);
		Map<String, Object> map = new HashMap<>();
		map.put("draw", condition.getDraw());
		map.put("recordsTotal", condition.getTotalRows());
		map.put("recordsFiltered", condition.getTotalRows());
		map.put("data", listMechanisms);
		WriterToPageByJson(map);
	}

	/**
	 * 根据机构菜单选择的机构条件进行查询
	 *
	 * @author 奚艺轩 2017-4-12 下午2:21:25
	 */
	@RequestMapping("getListMechanismByMechanismsFlag")
	@ResponseBody
	public void getListMechanismByMechanismsFlag(
			MechanismsCondition condition) {
		condition.setRows(condition.getLength());
		condition.setPage(condition.getStart() / condition.getLength() + 1);
		// 根据机构标示查询
		if (IceStringUtils.isNotBlank(condition.getMechanismsFlag())) {
			condition.setMechanismsFlag(condition.getMechanismsFlag());
		}
		List<Mechanisms> listMechanisms = mechanismsService
				.getListMechanismByCondition(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", condition.getDraw());
		map.put("recordsTotal", condition.getTotalRows());
		map.put("recordsFiltered", condition.getTotalRows());
		map.put("data", listMechanisms);
		WriterToPageByJson(map);
	}

	/**
	 * 保存组织机构信息
	 *
	 * @author 奚艺轩 2017-4-12 下午2:21:44
	 */
	@RequestMapping("saveMechanisms")
	@ResponseBody
	public void saveMechanisms(Mechanisms mechanisms) {
		mechanisms.setId(IceRandomUtils.getLongUUID());
		mechanisms.setAddTime(new Date());
		User user = (User) httpSession.getAttribute("USER");
		mechanisms.setAddUser(user.getId());
		ActionResult actionResult = mechanismsService
				.saveMechanisms(mechanisms);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 修改组织机构
	 *
	 * @author 奚艺轩 2017-4-12 下午2:21:55
	 */
	@RequestMapping("updateMechanisms")
	@ResponseBody
	public void updateMechanisms(Mechanisms mechanisms) {
		ActionResult actionResult = mechanismsService
				.updateMechanisms(mechanisms);
		WriterToPageByJson(actionResult);
	}

	/**
	 * 获得要修改的组织机构信息
	 *
	 * @author 奚艺轩 2017-4-12 下午2:22:03
	 */
	@RequestMapping("toUpdateMechanisms")
	public String toUpdateMechanisms(Mechanisms mechanisms, Model model) {
		// String id = httpServletRequest.getParameter("id");
		mechanisms = mechanismsService.getMechanismsById(mechanisms.getId());
		if (mechanisms != null) {
			model.addAttribute(mechanisms);
			return "mechanisms/mechanismsUpdate";
		} else {
			return "none";
		}
	}

	/**
	 * 删除组织机构信息
	 *
	 * @author 奚艺轩 2017-4-12 下午2:22:13
	 */
	@RequestMapping("deleteMechanisms")
	@ResponseBody
	public void deleteMechanisms(Mechanisms mechanisms) {
		ActionResult actionResult = mechanismsService
				.deleteMechanisms(mechanisms.getId());
		WriterToPageByJson(actionResult);
	}

}
