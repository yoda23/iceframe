package com.edt.controller.gzh;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.edt.common.BaseController;
import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.entity.AppInfo;
import com.edt.service.GzhService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gzh")
public class GzhController extends BaseController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private GzhService gzhService;


    @RequestMapping("/getAppInfoByCondition")
    @ResponseBody
    public void getAppInfoByCondition(FindCondition condition){
        condition.setRows(condition.getLength());
        condition.setPage(condition.getStart() / condition.getLength() + 1);
        List<AppInfo> appInfoList = gzhService.getAppInfoListByCondition(condition);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", condition.getDraw());
        map.put("recordsTotal", condition.getTotalRows());
        map.put("recordsFiltered", condition.getTotalRows());
        map.put("data", appInfoList);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                AppInfo.class, "id","appId","appSecret","appName","remark","createDate");
        WriterToPageByJsonByFilter(map, filter);
    }

    @RequestMapping("/saveAppInfo")
    @ResponseBody
    public void saveAppInfo(AppInfo appInfo){
        ActionResult result = gzhService.saveAppInfo(appInfo);
        WriterToPageByJsonNoNull(result);
    }
    @RequestMapping("saveAppInfo")
    public String toUpdatePage(){
        String id = request.getParameter("id");
        AppInfo appInfo = gzhService.getAppInfoById(id);
        request.setAttribute("appInfo",appInfo);
        return "/gzh/updateGzh";
    }

    @RequestMapping("/updateAppInfo")
    @ResponseBody
    public void updateAppInfo(AppInfo appInfo){
        ActionResult result = gzhService.updateAppInfo(appInfo);
        WriterToPageByJsonNoNull(result);
    }
}
