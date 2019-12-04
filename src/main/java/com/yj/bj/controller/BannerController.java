package com.yj.bj.controller;

import com.yj.bj.entity.Banner;
import com.yj.bj.service.BannerService;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("Banner")
public class BannerController extends BaseController {
	
	@Autowired
	private BannerService bannerService;
	
	@RequestMapping(value = "selectBannerList")
	public @ResponseBody
	YJResult get(String appId, String status) {
		Banner banner=new Banner();
		banner.setAppId(appId);
		banner.setStatus(status);
		List<Banner> bannerList=bannerService.queryForList(banner);
		return YJResult.ok(bannerList);
	}
}
