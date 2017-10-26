package com.edt.common.constant;

import com.edt.common.bean.Config;

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

}
