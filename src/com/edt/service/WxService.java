package com.edt.service;

import com.edt.entity.WxMenu;

import java.util.List;

public interface WxService {

    String getAccessToken(String appId,String appSecret);

     List<String> getOpenIdList(String appId, String appSecret);

     void senTextToAllOpenId(String appId,String appSecret,String text);

     String createMenu(String appId,String appSecret,WxMenu menu);
}
