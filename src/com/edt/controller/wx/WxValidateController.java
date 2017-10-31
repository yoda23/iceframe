package com.edt.controller.wx;

import com.edt.common.BaseController;
import com.edt.entity.TpInfo;
import com.edt.service.TpService;
import com.iceutils.json.IceJsonStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
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
    public void validate() throws NoSuchAlgorithmException {

        String signature = request.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        String echostr = request.getParameter("echostr");//echostr
        String sha1 = getSHA1("yoda", timestamp, nonce);
        logger.info("----------------signature:" + signature);
        logger.info("-------------sha1" + sha1);
        if (signature.equals(sha1)) {
            logger.info("validate success!");
        }
        WriterToPageByString(echostr);
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
