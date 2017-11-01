package com.edt.controller.tp;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.edt.common.BaseController;
import com.edt.common.bean.ActionResult;
import com.edt.common.bean.FindCondition;
import com.edt.entity.PhoneLocation;
import com.edt.entity.PhoneOperator;
import com.edt.entity.TpInfo;
import com.edt.service.TpService;
import com.iceutils.random.IceRandomUtils;
import com.iceutils.string.IceStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tp/")
public class TpController extends BaseController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private TpService tpService;
    private Logger logger = LogManager.getLogger(TpController.class);
    @RequestMapping("tpList")
    @ResponseBody
    public void tpList(FindCondition condition ){
        condition.setRows(condition.getLength());
        condition.setPage(condition.getStart() / condition.getLength() + 1);
        List<TpInfo> tpList = tpService.getTpBycondition(condition);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", condition.getDraw());
        map.put("recordsTotal", condition.getTotalRows());
        map.put("recordsFiltered", condition.getTotalRows());
        map.put("data", tpList);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
                TpInfo.class, "description","count");
        WriterToPageByJsonByFilter(map, filter);
    }

    @ResponseBody
    @RequestMapping("saveTp")
    public void saveTp(TpInfo tpInfo){
        tpInfo.setId(IceRandomUtils.getLongUUID());
        tpInfo.setLogId(IceRandomUtils.getLongUUID());
        ActionResult result = tpService.saveTpInfo(tpInfo);
        WriterToPageByJsonNoNull(result);
    }
    @ResponseBody
    @RequestMapping("incCount")
    public void incCount(){
        String id = request.getParameter("id");
        ActionResult resutl = tpService.incCount(id);
        WriterToPageByJsonNoNull(resutl);
    }


}
