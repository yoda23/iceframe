package com.edt.dao;

import com.edt.controller.oa.OaCondition;
import com.edt.entity.OASignInInfo;

import java.util.List;

public interface OaDao {

    List<OASignInInfo> selectByCondition(OaCondition condition);

    OASignInInfo selectById(String id);

    void saveSignInfo(OASignInInfo signInInfo);

    void updateSignInfo(OASignInInfo signInInfo);

    void deleteSignInfoById(String id);
}
