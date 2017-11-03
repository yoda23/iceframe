package com.edt.service.impl;

import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.dao.GzhDao;
import com.edt.entity.AppInfo;
import com.edt.service.GzhService;
import com.edt.service.WxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GzhServiceImpl implements GzhService {
    @Resource
    GzhDao gzhDao;
    @Resource
    WxService wxService;
    @Override
    public List<AppInfo> getAppInfoListByCondition(FindCondition condition) {
        PageHelper.startPage(condition.getPage(), condition.getRows());
        List<AppInfo> gzhList = this.gzhDao.getAppInfoListByCondition(condition);
        PageInfo<AppInfo> pageInfo = new PageInfo<>(gzhList);
        condition.setTotalRows(pageInfo.getTotal());
        return gzhList;
    }

    @Override
    public ActionResult saveAppInfo(AppInfo appInfo) {
        ActionResult result = new ActionResult();
        gzhDao.saveAppInfo(appInfo);
        result.setSuccess(true);
        result.setMessage("添加成功");
        return  result;
    }

    @Override
    public ActionResult updateAppInfo(AppInfo appInfo) {
        ActionResult result = new ActionResult();
        gzhDao.updateAppInfo(appInfo);
        result.setSuccess(true);
        result.setMessage("添加成功");
        return  result;
    }

    @Override
    public AppInfo getAppInfoById(String id) {
        return gzhDao.getAppInfoById(id);
    }

    @Override
    public ActionResult setText(AppInfo appInfo, String text) {
        wxService.senText(appInfo.getAppId(),appInfo.getAppSecret(),text);
        ActionResult result = new ActionResult();
        result.setMessage("提交群发成功");
        result.setSuccess(true);
        return result;
    }
}
