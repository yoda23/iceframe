package com.edt.controller.region;

import com.edt.common.BaseController;
import com.edt.entity.Region;
import com.edt.service.RegionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/region")
public class RegionController extends BaseController {

	@Resource
	private RegionService regionService;

	/**
	 * 根据查询条件获取行政区域数据
	 * 
	 * @param condition
	 *            condition
	 * @author 奚艺轩 2017-6-1上午10:20:34
	 */
	@RequestMapping("getRegionByCondition")
	@ResponseBody
	public void getRegionByCondition(RegionCondition condition) {
		condition.setRows(condition.getLength());
		condition.setPage(condition.getStart() / condition.getLength() + 1);
		List<Region> listRegion = regionService.getRegionByConditon(condition);
		Map<String, Object> map = new HashMap<>();
		map.put("draw", condition.getDraw());
		map.put("recordsTotal", condition.getTotalRows());
		map.put("recordsFiltered", condition.getTotalRows());
		map.put("data", listRegion);
		WriterToPageByJson(map);
	}
}
