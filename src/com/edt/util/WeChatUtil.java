package com.edt.util;

import com.alibaba.fastjson.JSON;
import com.edt.entity.wechat.AccessTokenResult;
import com.edt.entity.wechat.ErrorInfo;
import com.edt.entity.wechat.OpenIdResult;
import com.iceutils.net.IceHttpUtils;
import com.iceutils.weixin.error.IceWeiXinErrorCodeUtils;

import java.util.Date;

public class WeChatUtil {

    public static AccessTokenResult getAccessToken(String appID , String appSecret){
        AccessTokenResult accessTokenResult = new AccessTokenResult();
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appSecret;
        String result = IceHttpUtils.doHttpGet(accessTokenUrl);
        ErrorInfo errorInfo = JSON.parseObject(result, ErrorInfo.class);
        if (errorInfo.getErrCode() == null) {
            errorInfo.setErrCode("0");
            accessTokenResult = JSON.parseObject(result, AccessTokenResult.class);
            accessTokenResult.setAddTime(new Date());
        }
        errorInfo.setErrMsg(ErrorCodeUtil.getErrorMsg(errorInfo.getErrCode()));
        accessTokenResult.setErrorInfo(errorInfo);
        return accessTokenResult;
    }

    public static OpenIdResult getOpenIdList(String accesstoken,String nextOpenId){
        OpenIdResult openIdResult = new OpenIdResult();
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accesstoken+"&next_openid="+nextOpenId;
        String result = IceHttpUtils.doHttpGet(url);
        System.out.println(result);
        ErrorInfo errorInfo = JSON.parseObject(result, ErrorInfo.class);
        if (errorInfo.getErrCode() == null) {
            errorInfo.setErrCode("0");
            openIdResult = JSON.parseObject(result,OpenIdResult.class);
        }
        errorInfo.setErrMsg(ErrorCodeUtil.getErrorMsg(errorInfo.getErrCode()));
        openIdResult.setErrorInfo(errorInfo);
        return openIdResult;
    }
}
