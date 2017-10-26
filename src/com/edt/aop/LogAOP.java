package com.edt.aop;

import com.edt.common.bean.ActionResult;
import com.edt.entity.Log;
import com.edt.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import javax.annotation.Resource;

@Aspect
public class LogAOP {
	@Resource
	private LogService logService;

	@AfterReturning(value = "execution(* com.edt.service.impl.*.save*(..))", argNames = "joinPoint,ret", returning = "ret")
	public void insertLog(JoinPoint joinPoint, Object ret) throws Throwable {
		ActionResult actionResult = (ActionResult) ret;
		if (actionResult.isSuccess()) {
			for (Object object : joinPoint.getArgs()) {
				if (object instanceof Log) {
					logService.insertLog((Log) object);
				}
			}
		}
	}

	@AfterReturning(value = "execution(* com.edt.service.impl.*.update*(..))", argNames = "joinPoint,ret", returning = "ret")
	public void editLog(JoinPoint joinPoint, Object ret) throws Throwable {
		ActionResult actionResult = (ActionResult) ret;
		if (actionResult.isSuccess()) {
			for (Object object : joinPoint.getArgs()) {
				if (object instanceof Log) {
					logService.insertLog((Log) object);
				}
			}
		}
	}

	@AfterReturning(value = "execution(* com.edt.service.impl.*.delete*(..))", argNames = "joinPoint,ret", returning = "ret")
	public void deleteLog(JoinPoint joinPoint, Object ret) throws Throwable {
		ActionResult actionResult = (ActionResult) ret;
		if (actionResult.isSuccess()) {
			for (Object object : joinPoint.getArgs()) {
				if (object instanceof Log) {
					logService.insertLog((Log) object);
				}
			}
		}
	}
}
