package com.yj.bj.controller;

import com.yj.bj.service.IntroduceService;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("Introduce")
public class IntroduceController extends BaseController {
	
	@Autowired
	private IntroduceService introduceService;
	
	@RequestMapping(value = "selectIntroduceList", method = RequestMethod.POST)
	public @ResponseBody
	YJResult get(Integer rows, Integer page) {
		Map<String,Object> introduces=introduceService.receive(page,rows);
		return YJResult.ok(introduces);
	}
}
