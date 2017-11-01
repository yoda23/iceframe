package com.edt.service;

import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.entity.TpInfo;

import java.util.List;

public interface TpService {
    List<TpInfo> getTpBycondition(FindCondition condition);

    ActionResult saveTpInfo(TpInfo tpInfo);

    List<TpInfo> getAllTpInfo();

    ActionResult incCount(String id);
}
