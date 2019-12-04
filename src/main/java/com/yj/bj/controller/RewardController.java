package com.yj.bj.controller;

import com.yj.bj.entity.Reward;
import com.yj.bj.service.AgentService;
import com.yj.bj.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("Reward")
public class RewardController extends BaseController {
	
	@Autowired
	private RewardService rewardService;
	@Autowired
	private AgentService agentService;

	
	@RequestMapping(value = "getActivateReward")
	public @ResponseBody
	Reward get(@RequestParam(defaultValue = "0000")String appId) {
			Reward reward = new Reward();
			reward.setAppId(appId);
			return rewardService.find(reward);
	}
}
