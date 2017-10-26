package com.edt.service.impl;

import com.edt.common.constant.CommonConstant;
import com.edt.common.bean.ActionResult;
import com.edt.common.ActionResultService;
import com.edt.controller.mechanisms.MechanismsCondition;
import com.edt.dao.MechanismsDao;
import com.edt.entity.Mechanisms;
import com.edt.entity.User;
import com.edt.entity.UserMechanismsLinked;
import com.edt.service.MechanismsService;
import com.edt.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MechanismsServiceImpl implements MechanismsService {

	@Resource
	private MechanismsDao mechanismsDao;
	@Resource
	private HttpSession httpSession;
	@Resource
	private UserService userService;
	@Resource
	private ActionResultService actionResultService;

	@Transactional
	@Override
	public ActionResult saveMechanisms(Mechanisms mechanisms) {
		// 机构名称不能重复
		Mechanisms mechanismsName = getMechanismsByName(mechanisms.getName());
		if (mechanismsName == null) {
			mechanismsDao.saveMechanisms(mechanisms);
			// 给当前用户及当前用户上级所有机构赋予当前添加机构的查询权限
			User user = (User) httpSession.getAttribute("USER");
			// 获取所有上级机构
			List<Mechanisms> listMechanisms = mechanismsDao
					.listMechanismsParentById(user.getMechanisms().getId());
			for (Mechanisms m : listMechanisms) {
				// 根据机构ID获取用户信息
				List<User> listUser = userService
						.listUserByMechanismsId(m.getId());
				for (User u : listUser) {
					UserMechanismsLinked userMechanismsLinked = new UserMechanismsLinked();
					userMechanismsLinked.setMechanisms(mechanisms);
					userMechanismsLinked.setUser(u);
					mechanismsDao
							.saveUserMechanismsLinked(userMechanismsLinked);
				}
			}
			return actionResultService.callBackResult(CommonConstant.ACTIONRESULT_TRUE,
					"机构添加成功", null);
		} else {
			return actionResultService.callBackResult(CommonConstant.ACTIONRESULT_FAIL,
					"机构名称不能重复", null);
		}
	}

	@Transactional
	@Override
	public ActionResult updateMechanisms(Mechanisms mechanisms) {
		ActionResult actionResult;
		// 机构名称不能重复
		Mechanisms mechanismsName = getMechanismsByName(mechanisms.getName());
		if (mechanismsName != null
				&& !mechanismsName.getId().equals(mechanisms.getId())) {
			actionResult = actionResultService.callBackResult(
					CommonConstant.ACTIONRESULT_FAIL, "机构名称不能重复", null);
		} else {
			if (mechanisms.getId().equals(mechanisms.getParentId())) {
				actionResult = actionResultService.callBackResult(
						CommonConstant.ACTIONRESULT_FAIL, "所属机构不能是自己", null);
			} else {
				// 修改当前节点下所有直接子节点的父机构名称
				mechanismsDao.updateMechanismsByParentId(mechanisms);
				// 修改机构名称
				mechanismsDao.updateMechanisms(mechanisms);
				actionResult = actionResultService.callBackResult(
						CommonConstant.ACTIONRESULT_TRUE, "机构修改成功", null);
			}

		}
		return actionResult;
	}

	@Transactional
	@Override
	public ActionResult deleteMechanisms(String id) {
        mechanismsDao.deleteMechanisms(id);
		mechanismsDao.deleteUserMechanismsLinked(id);
		return actionResultService.callBackResult(CommonConstant.ACTIONRESULT_TRUE,
				"机构删除成功", null);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Mechanisms getMechanismsByName(String name) {
		return mechanismsDao.getMechanismsByName(name);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Mechanisms> getListMechanismByCondition(
			MechanismsCondition condition) {
		PageHelper.startPage(condition.getPage(), condition.getRows());
		List<Mechanisms> listMechanisms = mechanismsDao
				.getListMechanismByCondition(condition);
		for (Mechanisms listMechanism : listMechanisms) {
			listMechanism.setUserNameDisplay(listMechanism.getUser().getName());
		}
		PageInfo<Mechanisms> pageInfo = new PageInfo<>(listMechanisms);
		condition.setTotalRows(pageInfo.getTotal());
		return listMechanisms;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Mechanisms getMechanismsById(String id) {
		return mechanismsDao.getMechanismsById(id);
	}

	@Transactional
	@Override
	public void deleteAllMechanisms() {
		mechanismsDao.deleteAllMechanisms();
	}
    @Transactional
	@Override
	public List<Mechanisms> listMechanismsByUserId(String userId) {
		return mechanismsDao.listMechanismsByUserId(userId);
	}

	@Transactional
	@Override
	public void saveMechanismsForInit(Mechanisms mechanisms) {
		mechanismsDao.saveMechanisms(mechanisms);
	}

}
