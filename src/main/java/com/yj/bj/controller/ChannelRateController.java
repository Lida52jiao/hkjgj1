package com.yj.bj.controller;

import com.yj.bj.service.ChannelRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("ChannelRate")
public class ChannelRateController extends BaseController {
	
	@Autowired
	private ChannelRateService channelRateService;
}
