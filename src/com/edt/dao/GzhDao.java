package com.edt.dao;

import com.edt.common.bean.FindCondition;
import com.edt.entity.AppInfo;

import java.util.List;

public interface GzhDao {
    List<AppInfo> getAppInfoListByCondition(FindCondition condition);
    void saveAppInfo(AppInfo appInfo);
    void updateAppInfo(AppInfo appInfo);
    AppInfo getAppInfoById(String id);
}
