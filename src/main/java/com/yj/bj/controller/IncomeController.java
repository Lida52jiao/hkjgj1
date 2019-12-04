package com.yj.bj.controller;

import com.yj.bj.service.IncomeService;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("Income")
public class IncomeController extends BaseController {
	
	@Autowired
	private IncomeService incomeService;
	
	@RequestMapping(value = "selectIncome", method = RequestMethod.POST)
	public @ResponseBody
	YJResult get() {
		List<Number> n = incomeService.gain();
		return YJResult.ok(n);
	}
}
