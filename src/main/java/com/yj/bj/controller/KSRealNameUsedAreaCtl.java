package com.yj.bj.controller;

import com.yj.bj.entity.KSMerUsedArea;
import com.yj.bj.service.MerchantsUsedAreaService;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 卡神实名环节
 * 存储常用地区
 * 及其他信息的表
 * @author N
 *
 */
@Controller
@RequestMapping("/KSUsedArea/")
public class KSRealNameUsedAreaCtl extends BaseController {

	@Autowired
	private MerchantsUsedAreaService merchantsUsedAreaService;
	
	@RequestMapping("areaMerMessage")
	@ResponseBody
	public YJResult insertRecord(KSMerUsedArea ksMerUsedArea){
		ksMerUsedArea.setCreatedTime(new Date());
		String count = merchantsUsedAreaService.save(ksMerUsedArea);
		if("SUCCESS".equals(count)){
			return new YJResult("0000", "常用地区保存成功", "");
		}
		return new YJResult("0001", "常用地区保存失败", "");
	}
}
