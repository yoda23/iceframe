package com.edt.dao;

import com.edt.common.bean.FindCondition;
import com.edt.entity.Mechanisms;
import com.edt.entity.UserMechanismsLinked;

import java.util.List;

public interface MechanismsDao {

	/**
	 * 保存组织机构
	 *
	 * @param mechanisms
	 *            mechanisms
	 * @author 刘钢 2017/5/17 22:38
	 */
	void saveMechanisms(Mechanisms mechanisms);

	/**
	 * 修改组织机构
	 *
	 * @param mechanisms
	 *            mechanisms
	 * @author 刘钢 2017/5/17 22:38
	 */
	void updateMechanisms(Mechanisms mechanisms);

	/**
	 * 根据父ID修改组织机构
	 *
	 * @param mechanisms
	 *            mechanisms
	 * @author 刘钢 2017/5/17 22:39
	 */
	void updateMechanismsByParentId(Mechanisms mechanisms);

	/**
	 * 删除组织机构
	 *
	 * @param id
	 *            id
	 * @author 刘钢 2017/5/17 22:39
	 */
	void deleteMechanisms(String id);

	/**
	 * 根据机构编码查询机构信息
	 *
	 * @param code
	 *            code
	 * @return com.edt.entity.Mechanisms
	 * @author 刘钢 2017/5/17 22:39
	 */
	Mechanisms getMechanismsByCode(String code);

	/**
	 * 根据机构名称查询机构信息
	 *
	 * @param name
	 *            name
	 * @return com.edt.entity.Mechanisms
	 * @author 刘钢 2017/5/17 22:40
	 */
	Mechanisms getMechanismsByName(String name);

	/**
	 * 分页查询组织机构
	 *
	 * @param condition
	 *            condition
	 * @return java.util.List<com.edt.entity.Mechanisms>
	 * @author 刘钢 2017/5/17 22:40
	 */
	List<Mechanisms> getListMechanismByCondition(FindCondition condition);

	/**
	 * 根据组织机构ID查询机构信息
	 *
	 * @param id
	 *            id
	 * @return com.edt.entity.Mechanisms
	 * @author 刘钢 2017/5/17 22:41
	 */
	Mechanisms getMechanismsById(String id);

	/**
	 * 获取所有父节点信息
	 * 
	 * @param mechanismsId
	 *            mechanismsId
	 * @return java.util.List<com.edt.entity.Mechanisms>
	 * @author 刘钢 2017-07-10 13:17
	 */

	List<Mechanisms> listMechanismsParentById(String mechanismsId);

	/**
	 * 删除所有组织机构
	 *
	 * @author 刘钢 2017/5/17 22:41
	 */
	void deleteAllMechanisms();

	/**
	 * 保存用户机构关联表
	 *
	 * @param userMechanismsLinked
	 *            userMechanismsLinked
	 * @author 刘钢 2017-07-06 10:25
	 */

	void saveUserMechanismsLinked(UserMechanismsLinked userMechanismsLinked);

	/**
	 * 根据机构ID删除用户机构关联表
	 *
	 * @param mechanismsId
	 *            mechanismsId
	 * @author 刘钢 2017-07-06 10:25
	 */

	void deleteUserMechanismsLinked(String mechanismsId);

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
