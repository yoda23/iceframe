package com.edt.service;

import com.edt.common.bean.ActionResult;
import com.edt.controller.oa.OaCondition;
import com.edt.entity.OASignInInfo;

import java.util.List;

public interface OaService {
    List<OASignInInfo> selectByCondition(OaCondition condition);

    ActionResult save(OASignInInfo info);

    OASignInInfo getById(String id);

    ActionResult update(OASignInInfo info);

    ActionResult deleteById(String id);
}
