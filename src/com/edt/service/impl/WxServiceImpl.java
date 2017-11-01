package com.edt.service.impl;

import com.edt.service.WxService;
import com.iceutils.weixin.security.IceWeiXinAccessTokenUtils;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl implements WxService{
    @Override
    public String getAccessToken() {
//        IceWeiXinAccessTokenUtils.createAccessToken();
        return null;
    }
}
