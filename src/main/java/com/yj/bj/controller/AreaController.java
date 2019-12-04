package com.yj.bj.controller;

import com.yj.bj.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller("Area")
public class AreaController extends BaseController {
	
	@Autowired
	private AreaService areaService;
}
