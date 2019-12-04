package com.yj.bj.controller;

import com.yj.bj.entity.Num;
import com.yj.bj.service.NumService;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("Num")
public class NumController extends BaseController {
	
	@Autowired
	private NumService numService;
	
	@RequestMapping(value = "selectNum", method = RequestMethod.POST)
	public @ResponseBody
	YJResult get(@RequestParam(value="appId",defaultValue = "0000") String appId) {
		Num num = new Num();
		num.setAppId(appId);
		List<Num> n=numService.queryObjectForList(num);
		return YJResult.ok(n);
	}
}
