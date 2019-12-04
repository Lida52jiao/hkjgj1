package com.yj.bj.controller;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	static final String SUCCESS = "success";
	static final String ERROR = "error";
	static final String EXCEPTION = "exception";

	Logger logger = Logger.getLogger(BaseController.class);

	/**
	 * 获取页面传递的某一个参数值,
	 */
	public String getParameter(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getParameter(key);
	}

	/**
	 * 获取HttpServletRequest;
	 * 
	 * @return [参数说明]
	 * 
	 * @return HttpServletRequest [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static HttpServletRequest getHttpRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}
}
