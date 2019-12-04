package com.yj.bj.controller;

import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.Used;
import com.yj.bj.service.UsedService;
import com.yj.bj.util.RedisUtils;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.List;


@Controller
@RequestMapping("Used")
public class UsedController extends BaseController {
	
	@Autowired
	private UsedService usedService;
	
	@RequestMapping(value = "getUsed", method = RequestMethod.POST)
	public @ResponseBody
	YJResult get(String token) {
		Jedis jedis= RedisUtils.getJedis();
		if(jedis.exists(token)){
			List<Used> n=usedService.queryObjectForList();
			RedisUtils.returnResource(jedis);
			return YJResult.ok(n);
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
}
