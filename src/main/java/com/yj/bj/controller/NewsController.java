package com.yj.bj.controller;

import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.News;
import com.yj.bj.service.NewsService;
import com.yj.bj.util.RedisUtils;
import com.yj.bj.util.YJ;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.Map;


@Controller
@RequestMapping("News")
public class NewsController extends BaseController {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = "selectNewsList", method = RequestMethod.POST)
	public @ResponseBody
	YJResult get(String token, Integer rows,Integer page, String merChantId, String appId) {
		Jedis jedis= RedisUtils.getJedis();
		if(jedis.exists(token)){
			Map<String,Object> news=newsService.receive(page,rows,merChantId,appId);
			RedisUtils.returnResource(jedis);
			return YJResult.ok(news);
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "sends", method = RequestMethod.POST)
	public @ResponseBody
	String sends(String merChantId, String msg) {
		YJ.push(merChantId, msg);
		News news=new News(merChantId, msg, System.currentTimeMillis()+"");
		return newsService.save(news);
	}
}
