package com.edt.service;

import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.entity.AppInfo;

import java.util.List;

public interface GzhService {

    List<AppInfo> getAppInfoListByCondition(FindCondition condition);
    ActionResult saveAppInfo(AppInfo appInfo);
    ActionResult updateAppInfo(AppInfo appInfo);
    AppInfo getAppInfoById(String id);
    ActionResult setText(AppInfo appInfo ,String text);
}
