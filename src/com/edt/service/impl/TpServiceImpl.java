package com.edt.service.impl;

import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.dao.TpDao;
import com.edt.entity.PhoneOperator;
import com.edt.entity.TpInfo;
import com.edt.service.TpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.List;

@Service
public class TpServiceImpl implements TpService {
    @Resource
    TpDao tpDao;
    @Override
    public List<TpInfo> getTpBycondition(FindCondition condition) {
        PageHelper.startPage(condition.getPage(), condition.getRows());
        List<TpInfo> tpList = this.tpDao.getTpBycondition(condition);
        PageInfo<TpInfo> pageInfo = new PageInfo<>(tpList);
        condition.setTotalRows(pageInfo.getTotal());
        return tpList;
    }

    @Override
    public ActionResult saveTpInfo(TpInfo tpInfo) {
        ActionResult result = new ActionResult();
        tpDao.saveTpInfo(tpInfo);
        result.setSuccess(true);
        result.setMessage("添加成功");
        return result;
    }

    @Override
    public List<TpInfo> getAllTpInfo() {
        return tpDao.getAllTpInfo();
    }

    @Override
    public ActionResult incCount(String id) {
        ActionResult result = new ActionResult();
        tpDao.incCount(id);
        result.setSuccess(true);
        result.setMessage("投票成功");
        return result;
    }
}
