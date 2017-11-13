package com.edt.service.impl;

import com.edt.common.bean.ActionResult;
import com.edt.controller.oa.OaCondition;
import com.edt.dao.OaDao;
import com.edt.entity.AppInfo;
import com.edt.entity.OASignInInfo;
import com.edt.service.OaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OaServiceImpl implements OaService {
    @Resource
    private OaDao oaDao;
    @Override
    public List<OASignInInfo> selectByCondition(OaCondition condition) {
        PageHelper.startPage(condition.getPage(), condition.getRows());
        List<OASignInInfo> oaList = this.oaDao.selectByCondition(condition);
        PageInfo<OASignInInfo> pageInfo = new PageInfo<>(oaList);
        condition.setTotalRows(pageInfo.getTotal());
        return oaList;
    }

    @Override
    public ActionResult save(OASignInInfo info) {
        ActionResult result = new ActionResult();
        oaDao.saveSignInfo(info);
        result.setMessage("成功");
        result.setSuccess(true);
        return result;
    }

    @Override
    public OASignInInfo getById(String id) {
        return oaDao.selectById(id);
    }

    @Override
    public ActionResult update(OASignInInfo info) {
        ActionResult result = new ActionResult();
        oaDao.updateSignInfo(info);
        result.setMessage("成功");
        result.setSuccess(true);
        return result;
    }

    @Override
    public ActionResult deleteById(String id) {
        ActionResult result = new ActionResult();
        oaDao.deleteSignInfoById(id);
        result.setMessage("成功");
        result.setSuccess(true);
        return result;
    }
}
