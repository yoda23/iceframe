package com.edt.controller.oa;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.edt.common.BaseController;
import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.entity.AppInfo;
import com.edt.entity.OASignInInfo;
import com.edt.service.GzhService;
import com.edt.service.OaService;
import com.iceutils.random.IceRandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/oa")
public class OAController extends BaseController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private OaService oaService;


    @RequestMapping("/getSignInfoByCondition")
    @ResponseBody
    public void getSignInfoByCondition(OaCondition condition){
        condition.setRows(condition.getLength());
        condition.setPage(condition.getStart() / condition.getLength() + 1);
        List<OASignInInfo> oaList = oaService.selectByCondition(condition);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", condition.getDraw());
        map.put("recordsTotal", condition.getTotalRows());
        map.put("recordsFiltered", condition.getTotalRows());
        map.put("data", oaList);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                AppInfo.class, "id","signInName","signInState","startDate","endDate","remark","lateMinutes","addMinutes","leaveMinutes");
        WriterToPageByJsonByFilter(map, filter);
    }

    @RequestMapping("/saveSignInInfo")
    @ResponseBody
    public void saveSignInInfo(OASignInInfo info){
        info.setId(IceRandomUtils.getLongUUID());
        info.setSignInDate(new Date());
        //TODO 算迟到 加班，请假 分钟
        ActionResult result = oaService.save(info);
        WriterToPageByJsonNoNull(result);
    }
    @RequestMapping("/toupdateSignInInfo")
    public String toUpdatePage(){
        String id = request.getParameter("id");
        OASignInInfo ifno = oaService.getById(id);
        request.setAttribute("info",ifno);
        return "/gzh/updateGzh";
    }

    @RequestMapping("/updateSignInInfo")
    @ResponseBody
    public void updateAppInfo(OASignInInfo info){
        ActionResult result = oaService.update(info);
        WriterToPageByJsonNoNull(result);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(){
        String id = request.getParameter("id");
        ActionResult result = oaService.deleteById(id);
        WriterToPageByJsonNoNull(result);
    }
}
