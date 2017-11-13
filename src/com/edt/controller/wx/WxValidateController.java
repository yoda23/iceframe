package com.edt.controller.wx;

import com.edt.common.BaseController;
import com.edt.common.constant.CommonConstant;
import com.edt.entity.TpInfo;
import com.edt.entity.wechat.XmlInfo;
import com.edt.service.TpService;
import com.edt.util.JaxbUtil;
import com.iceutils.json.IceJsonStringUtils;
import org.apache.ibatis.reflection.wrapper.CollectionWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/wx")
public class WxValidateController extends BaseController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private TpService tpService;
    private Logger logger = LogManager.getLogger(WxValidateController.class);

    @RequestMapping(value = "validate")
    @ResponseBody
    public void validate(@RequestBody  XmlInfo xmlInfo) throws NoSuchAlgorithmException {

        String signature = request.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        String echostr = request.getParameter("echostr");//echostr
        String sha1 = getSHA1("yoda", timestamp, nonce);
        logger.info(IceJsonStringUtils.toJsonString(xmlInfo));
        logger.info(IceJsonStringUtils.toJsonString(request.getParameterMap()));

        if("孙梁大帅比".equals(xmlInfo.getContent())){
            String s = "\n" +
                    "<xml>\n" +
                    "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                    "<FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                    "<CreateTime>12345678</CreateTime>\n" +
                    "<MsgType><![CDATA[text]]></MsgType>\n" +
                    "<Content><![CDATA[你好]]></Content>\n" +
                    "</xml>";
            s = s.replace("toUser",xmlInfo.getFromUserName()).replace("fromUser",xmlInfo.getToUserName())
            .replace("12345678",xmlInfo.getCreateTime().toString()).replace("你好","你说的对");
            System.out.println("xml:"+s);
            WriterToPageByString(s);
            return;
        }else if("投票".equals(xmlInfo.getContent())){
            String s = "\n" +
                    "<xml>\n" +
                    "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                    "<FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                    "<CreateTime>12345678</CreateTime>\n" +
                    "<MsgType><![CDATA[text]]></MsgType>\n" +
                    "<Content><![CDATA[你好]]></Content>\n" +
                    "</xml>";

            s = s.replace("toUser",xmlInfo.getFromUserName()).replace("fromUser",xmlInfo.getToUserName())
                    .replace("12345678",xmlInfo.getCreateTime().toString()).replace("你好","http://47.95.116.7/base/wx/testPatge");
            System.out.println("xml:"+s);
            WriterToPageByString(s);
            return;
        }
        else{
            if (signature.equals(sha1)) {
                logger.info("validate success!");
                 WriterToPageByString(echostr == null ? "success" : echostr);
            }else{
                WriterToPageByString("validate fail!");
            }

        }
    }


    @RequestMapping("testPatge")
    public String testPage(){
        logger.info(IceJsonStringUtils.toJsonString(request.getParameterMap()));
        List<TpInfo> tpList = tpService.getAllTpInfo();
        request.setAttribute("tpList",tpList);
        return "/webChat/testPage";
    }


    /** 用SHA1算法生成安全签名
     * @param token 票据
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @return 安全签名
     * @throws NoSuchAlgorithmException
     *
     */
    private  String getSHA1(String token, String timestamp, String nonce) throws NoSuchAlgorithmException {
        String[] array = new String[] { token, timestamp, nonce };
        StringBuffer sb = new StringBuffer();
        // 字符串排序
        Arrays.sort(array);
        for (int i = 0; i < 3; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();
        // SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return hexstr.toString();
    }
}
