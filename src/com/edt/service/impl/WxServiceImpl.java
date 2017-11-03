package com.edt.service.impl;

import com.alibaba.fastjson.JSON;
import com.edt.entity.OpenIdResult;
import com.edt.service.RedisService;
import com.edt.service.WxService;
import com.iceutils.json.IceJsonStringUtils;
import com.iceutils.net.IceHttpUtils;
import com.iceutils.string.IceStringUtils;
import com.iceutils.weixin.security.IceWeiXinAccessTokenUtils;
import com.iceutils.weixin.security.bean.AccessTokenResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class WxServiceImpl implements WxService{
    @Resource
    private RedisService redisService;
    private Lock lock = new ReentrantLock();
    @Override
    public String getAccessToken(String appId,String appScerat) {
        String token = redisService.opsValue_get("accessToken",String.class);
        if(IceStringUtils.isEmpty(token)){
            lock.lock();
            try{
                AccessTokenResult accessTokenResult = IceWeiXinAccessTokenUtils.createAccessToken(appId,appScerat);
                if(IceStringUtils.isEmpty(accessTokenResult.getAccess_token())){
                    throw new RuntimeException(IceJsonStringUtils.toJsonString(accessTokenResult.getErrorInfo()));
                }
                token = accessTokenResult.getAccess_token();
                redisService.opsValue_set("accessToken",token,accessTokenResult.getExpires_in(), TimeUnit.SECONDS);
            }finally {
                lock.unlock();
            }

        }
        return token;
    }

    @Override
    public List<String> getOpenIdList(String appId, String appScerat) {
        String accessToken = getAccessToken(appId,appScerat);
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken+"&next_openid=";
        String result = IceHttpUtils.doHttpGet(url);
        OpenIdResult openIdResult = JSON.parseObject(result,OpenIdResult.class);
        return openIdResult.getData().getOpenid();
    }

    @Override
    public void senText(String appId,String appScerat,String text) {
        String accessToken = getAccessToken(appId,appScerat);
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+accessToken;
        String json = "{" +
                "   \"touser\":[" ;
        List<String> openid =    getOpenIdList(appId,appScerat);
        for (int i=0;i<openid.size();i++) {
            if(i == 0){
                json += "\""+openid.get(i)+"\"" ;
            }else{
                json += ",\""+openid.get(i)+"\"" ;
            }

        }
        json +=  "], \"msgtype\": \"text\"," +
                "  \"text\": { \"content\": \"http://47.95.116.7/base/wx/testPatge\"}"+
                "}";

        System.out.println(json);
        System.out.println(IceHttpUtils.doHttpPost(url,json,30000));
    }
}
