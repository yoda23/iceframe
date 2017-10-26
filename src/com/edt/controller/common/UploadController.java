package com.edt.controller.common;

import com.edt.common.BaseController;
import com.edt.common.bean.ActionResult;
import com.edt.common.constant.CommonConstant;
import com.iceutils.date.IceDateFormatUtils;
import com.iceutils.random.IceRandomUtils;
import com.iceutils.string.IceStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {
	@Resource
	private HttpSession httpSession;
	@Resource
	private HttpServletResponse httpServletResponse;
	@Resource
	private HttpServletRequest httpServletRequest;

	/**
	 * 文件上传方法
	 *
	 * @param file
	 *            file
	 * @author 刘钢 2017/5/28 23:29
	 */
	@RequestMapping("uploadFileAction")
	@ResponseBody
	public void uploadFile(@RequestParam("file") MultipartFile file) {
		ActionResult actionResult = new ActionResult();
		// 文件后缀
		String fileMark = IceStringUtils.substring(file.getOriginalFilename(),
				IceStringUtils.lastIndexOf(file.getOriginalFilename(), "."));
		// 文件名称
		String fileName = IceRandomUtils.getLongUUID();
		// 文件目录
		String fileDir = IceDateFormatUtils.getNowDateFormateOne();
		// 文件磁盘
		String fileDisk = CommonConstant.CONFIG_FILEDISKPATH;
		// 文件全路径
		String filePath = fileDisk + "/" + fileDir + "/" + fileName + fileMark;

		File saveFile = new File(filePath);
		if (!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		try {
			file.transferTo(saveFile);
			actionResult.setSuccess(true);
			actionResult.setResultList(fileDir + "/" + fileName + fileMark);
			WriterToPageByJson(actionResult);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * UE文件上传方法
	 *
	 * @param file
	 *            file
	 * @author 刘钢 2017/5/28 23:31
	 */
	@RequestMapping("uploadUEFile")
	@ResponseBody
	public void uploadUEFile(@RequestParam("file") MultipartFile file) {
		// 文件后缀
		String fileMark = IceStringUtils.substring(file.getOriginalFilename(),
				IceStringUtils.lastIndexOf(file.getOriginalFilename(), "."));
		// 文件名称
		String fileName = IceRandomUtils.getLongUUID();
		// 文件目录
		String fileDir = IceDateFormatUtils.getNowDateFormateOne();
		// 文件磁盘
		String fileDisk = CommonConstant.CONFIG_FILEDISKPATH;
		// 文件全路径
		String filePath = fileDisk + "/" + fileDir + "/" + fileName + fileMark;
		Map<String, Object> params = new HashMap<String, Object>();
		File saveFile = new File(filePath);
		if (!saveFile.getParentFile().exists()) {
			if (saveFile.getParentFile().mkdirs()) {
				try {
					file.transferTo(saveFile);
					params.put("state", "SUCCESS");
					params.put("url", CommonConstant.CONFIG_WEBPATH + "/" + fileDir
							+ "/" + fileName + fileMark);
					params.put("size", file.getSize());
					params.put("original", fileName + fileMark);
					params.put("type", file.getContentType());
					WriterToPageByJson(params);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
