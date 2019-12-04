package com.yj.bj.controller;

import com.yj.bj.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Distribution")
public class DistributionController extends BaseController {

	@Autowired
	private DistributionService distributionService;
}
