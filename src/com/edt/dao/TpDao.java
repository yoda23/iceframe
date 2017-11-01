package com.edt.dao;

import com.edt.common.bean.FindCondition;
import com.edt.entity.TpInfo;

import java.util.List;

public interface TpDao {
    List<TpInfo> getTpBycondition(FindCondition condition);

    void saveTpInfo(TpInfo tpInfo);

    List<TpInfo> getAllTpInfo();

    void incCount(String id);
}
