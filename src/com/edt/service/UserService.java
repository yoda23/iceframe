package com.edt.service;

import com.edt.common.bean.ActionResult;
import com.edt.controller.user.UserCondition;
import com.edt.entity.User;
import com.edt.entity.UserMechanismsLinked;

import java.util.List;

public interface UserService {
	/**
	 * 验证用户登录
	 *
	 * @param loginId
	 *            loginId
	 * @return com.edt.entity.User
	 * @author 刘钢 2017-05-18 11:49
	 */

	User login(String loginId);

	/**
	 * 获取所有用户信息
	 *
	 * @param condition
	 *            condition
	 * @return java.util.List<com.edt.entity.User>
	 * @author 刘钢 2017-05-18 11:49
	 */

	List<User> getUserByCondition(UserCondition condition);

	/**
	 * 保存用户
	 *
	 * @param user
	 *            user
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 11:50
	 */

	ActionResult saveUser(User user);

	/**
	 * 根据用户ID查询用户
	 *
	 * @param userId
	 *            userId
	 * @return com.edt.entity.User
	 * @author 刘钢 2017-05-18 11:50
	 */

	User getUserById(String userId);

	/**
	 * 根据登陆账号ID查询用户
	 *
	 * @param loginId
	 *            loginId
	 * @return com.edt.entity.User
	 * @author 刘钢 2017-05-18 11:50
	 */

	User getUserByLoginId(String loginId);

	/**
	 * 修改用户信息
	 *
	 * @param user
	 *            user
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 11:50
	 */

	ActionResult updateUser(User user);

	/**
	 * 删除用户
	 *
	 * @param id
	 *            id
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 11:50
	 */

	ActionResult deleteUser(String id);

	/**
	 * 修改用户状态
	 *
	 * @param user
	 *            user
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 11:50
	 */

	ActionResult updateState(User user);

	/**
	 * 修改登录帐号密码
	 *
	 * @param user
	 *            user
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 11:50
	 */

	ActionResult updateLoginPassword(User user);

	/**
	 * 修改用户个人信息
	 *
	 * @param user
	 *            user
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 11:50
	 */

	ActionResult updateUserByUserInfo(User user);

	/**
	 * 修改用户登录帐号密码
	 *
	 * @param user
	 *            user
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 11:51
	 */

	ActionResult updateUserPassword(User user);

	/**
	 * 修改用户删除状态
	 *
	 * @param user
	 *            user
	 * @return com.edt.common.bean.ActionResult
	 * @author 刘钢 2017-05-18 11:51
	 */

	ActionResult updateUserDeleteFlag(User user);

	/**
	 * 删除所有用户信息
	 *
	 * @author 刘钢 2017-05-18 11:51
	 */

	void deleteAllUserInfo();

	/**
	 * 根据UserId获取用户机构中间表信息
	 *
	 * @param userId
	 *            userId
	 * @return java.util.List<com.edt.entity.UserMechanismsLinked>
	 * @author 刘钢 2017-07-07 11:11
	 */

	List<UserMechanismsLinked> listUserMechanismsLinkedByUserId(String userId);

	/**
	 * 删除用户机构关联表所有数据
	 *
	 * @author 刘钢 2017-07-06 10:26
	 */

	void deleteUserMechanismsLinkedByAll();

	/**
	 * 保存用户机构关联表
	 *
	 * @param userMechanismsLinked
	 *            userMechanismsLinked
	 * @author 刘钢 2017-07-06 10:25
	 */

	void saveUserMechanismsLinked(UserMechanismsLinked userMechanismsLinked);

	/**
	 * 根据机构ID获取所有用户信息
	 *
	 * @param mechanismsId
	 *            mechanismsId
	 * @return java.util.List<com.edt.entity.User>
	 * @author 刘钢 2017-07-10 14:40
	 */

	List<User> listUserByMechanismsId(String mechanismsId);
}
