package com.edt.service;

import com.edt.common.bean.ActionResult;
import com.edt.controller.mechanisms.MechanismsCondition;
import com.edt.entity.Mechanisms;

import java.util.List;

public interface MechanismsService {
	/**
	 * 保存机构信息
	 *
	 * @param mechanisms
	 *            mechanisms
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 9:22
	 */

	ActionResult saveMechanisms(Mechanisms mechanisms);

	/**
	 * 初始化保存机构根
	 *
	 * @param mechanisms
	 *            mechanisms
	 * @author 刘钢 2017-05-18 9:22
	 */

	void saveMechanismsForInit(Mechanisms mechanisms);

	/**
	 * 修改机构信息
	 *
	 * @param mechanisms
	 *            mechanisms
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 9:22
	 */

	ActionResult updateMechanisms(Mechanisms mechanisms);

	/**
	 * 删除机构信息
	 *
	 * @param id
	 *            id
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 9:22
	 */

	ActionResult deleteMechanisms(String id);

	/**
	 * 根据机构名称查询机构信息
	 *
	 * @param name
	 *            name
	 * @return com.edt.entity.Mechanisms
	 * @author 刘钢 2017-05-18 9:23
	 */

	Mechanisms getMechanismsByName(String name);

	/**
	 * 分页查询组织机构
	 *
	 * @param condition
	 *            condition
	 * @return java.util.List<com.edt.entity.Mechanisms>
	 * @author 刘钢 2017-05-18 9:23
	 */

	List<Mechanisms> getListMechanismByCondition(MechanismsCondition condition);

	/**
	 * 根据组织机构ID查询机构信息
	 *
	 * @param id
	 *            id
	 * @return com.edt.entity.Mechanisms
	 * @author 刘钢 2017-05-18 9:23
	 */

	Mechanisms getMechanismsById(String id);

	/**
	 * 删除所有组织机构
	 *
	 * @author 刘钢 2017-05-18 9:23
	 */

	void deleteAllMechanisms();

	/**
	 * 根据UserId获取可显示的组织机构信息
	 *
	 * @param userId
	 *            userId
	 * @return java.util.List<com.edt.entity.Mechanisms>
	 * @author 刘钢 2017/7/8 21:49
	 */
	List<Mechanisms> listMechanismsByUserId(String userId);
}
