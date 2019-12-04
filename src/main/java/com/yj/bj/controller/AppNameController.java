package com.yj.bj.controller;

import com.yj.bj.service.AppNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("AppName")
public class AppNameController extends BaseController {
	
	@Autowired
	private AppNameService appNameService;
}
