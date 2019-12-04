package com.yj.bj.controller;

import com.yj.bj.service.WithdrawalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("Withdrawals")
public class WithdrawalsController extends BaseController {
	
	@Autowired
	private WithdrawalsService withdrawalsService;
}
