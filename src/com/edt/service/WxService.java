package com.edt.service;

import java.util.List;

public interface WxService {

    String getAccessToken(String appId,String appScerat);

     List<String> getOpenIdList(String appId, String appScerat);

     public void senText(String appId,String appScerat,String text);
}
