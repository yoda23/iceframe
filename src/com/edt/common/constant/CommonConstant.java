package com.edt.common.constant;

import com.edt.common.bean.Config;
import com.iceutils.date.IceDateOperationUtils;

import java.io.Serializable;

/**
 * 系统常用常量
 *
 * @author 刘钢
 */
public class CommonConstant implements Serializable {

	private static final long serialVersionUID = -7828091876232562024L;
	// 前台界面返回
	public final static boolean ACTIONRESULT_TRUE = true;
	public final static boolean ACTIONRESULT_FAIL = false;
	// 系统配置常量
	public final static String CONFIG_FILEDISKPATH = Config.mapConfig
			.get("uploadpath");
	public final static String CONFIG_SAVEBATCH = Config.mapConfig
			.get("savebatch");
	public final static String CONFIG_UPDATEBATCH = Config.mapConfig
			.get("updatebatch");
	public final static String DELETEBATCH = Config.mapConfig
			.get("deletebatch");
	public final static String CONFIG_WEBPATH = Config.mapConfig.get("webpath");
	public final static String CONFIG_ERRORMSG = Config.mapConfig
			.get("errormsg");

	public final static String APPID_DEFAULT = "wx4575425ce2e4090b";
	public final static String APPSECRET_DEFAULT = "2bdceffee6b0062019660289be552687";

	                  /*  <option value="0">正常上班</option>
                        <option value="1">迟到</option>
                        <option value="2">请假</option>
                        <option value="3">休息</option>
                        <option value="4">加班</option>*/

	public final static  int OA_SATTE_ZC = 0;
	public final static  int OA_SATTE_CD = 1;
	public final static  int OA_SATTE_QJ = 2;
	public final static  int OA_SATTE_XX = 3;
	public final static  int OA_SATTE_JB = 4;

	public final static String STARTTIME = "08:30";
	public final static String ENDTIME = "05:30";
	public final static int LATETIMES = 3;


	public final static String FORMATE = "hh:mm";

}
