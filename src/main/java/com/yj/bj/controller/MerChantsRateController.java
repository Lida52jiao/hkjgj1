package com.yj.bj.controller;

import com.yj.bj.entity.ChannelRate;
import com.yj.bj.entity.MerChants;
import com.yj.bj.entity.MerChantsRate;
import com.yj.bj.service.ChannelRateService;
import com.yj.bj.service.MerChantsRateService;
import com.yj.bj.service.MerChantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("MerChantsRate")
public class MerChantsRateController extends BaseController {
	
	@Autowired
	private MerChantsRateService merchantsRateService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private ChannelRateService channelRateService;
	
	/*@RequestMapping(value = "selectMerChantsRate", method = RequestMethod.POST)
	public @ResponseBody
	MerChantsRate selectAgent(String merChantId, String aisleCode) {
		MerChants merChants = new MerChants();
		merChants.setMerChantId(merChantId);
		MerChants t = merChantsService.findByObject(merChants);
		MerChantsRate merChantsRate = new MerChantsRate();
		merChantsRate.setMerType(t.getMerType());
		merChantsRate.setAisleCode(aisleCode);
		merChantsRate.setAppId(t.getAppId());
		MerChantsRate h = merchantsRateService.findByObject(merChantsRate);
		if(h != null){
			return h;
		}
		ChannelRate channelRate = new ChannelRate();
		channelRate.setAisleCode(aisleCode);
		channelRate.setAppId(t.getAppId());
		ChannelRate v = channelRateService.findByObject(channelRate);
		MerChantsRate n = new MerChantsRate();
		n.setRate(v.getRate());
		n.setD0Fee(v.getD0Fee());
		return n;
	}*/
	
//	@RequestMapping(value = "selectRateList", method = RequestMethod.POST)
//	public @ResponseBody
//	List<MerChantsRate> selects(String merChantId, String aisleCodes) {
//		String[] string = aisleCodes.split(",");
//		List<String> list = Arrays.asList(string);
//		MerChants merChants = new MerChants();
//		merChants.setMerChantId(merChantId);
//		MerChants t = merChantsService.findByObject(merChants);
//		List<MerChantsRate> tList = new ArrayList<MerChantsRate>();
//
//
//		return tList;
//	}
	@RequestMapping(value = "selectMerChantsRate", method = RequestMethod.POST)
	public @ResponseBody
	MerChantsRate selectAgent(String merChantId, String aisleCode) {
		MerChants merChants = new MerChants();
		merChants.setMerChantId(merChantId);
		MerChants t = merChantsService.findByObject(merChants);
		MerChantsRate merChantsRate = new MerChantsRate();
		merChantsRate.setMerType(t.getMerType());
		merChantsRate.setAisleCode(aisleCode);
		merChantsRate.setAppId(t.getAppId());
		MerChantsRate h = merchantsRateService.findByObject(merChantsRate);
		if(h != null){
			return h;
		}
		ChannelRate channelRate = new ChannelRate();
		channelRate.setAisleCode(aisleCode);
		channelRate.setAppId(t.getAppId());
		ChannelRate v = channelRateService.findByObject(channelRate);
		MerChantsRate n = new MerChantsRate();
		n.setRate(v.getRate());
		n.setD0Fee(v.getD0Fee());
		return n;
	}

	@RequestMapping(value = "selectRateList", method = RequestMethod.POST)
	public @ResponseBody
	List<MerChantsRate> selects(String merChantId, String aisleCodes) {
		String[] string = aisleCodes.split(",");
		List<String> list = Arrays.asList(string);
		MerChants merChants = new MerChants();
		merChants.setMerChantId(merChantId);
		MerChants t = merChantsService.findByObject(merChants);
		List<MerChantsRate> tList = new ArrayList<MerChantsRate>();
		for(String n : list){
			MerChantsRate merChantsRate = new MerChantsRate();
			merChantsRate.setMerType(t.getMerType());
			merChantsRate.setAisleCode(n);
			merChantsRate.setAppId(t.getAppId());
			MerChantsRate h = merchantsRateService.findByObject(merChantsRate);
			if(h != null){
				tList.add(h);
				continue;
			}
			ChannelRate channelRate = new ChannelRate();
			channelRate.setAisleCode(n);
			channelRate.setAppId(t.getAppId());
			ChannelRate v = channelRateService.findByObject(channelRate);
			MerChantsRate s = new MerChantsRate();
			s.setRate(v.getRate());
			s.setD0Fee(v.getD0Fee());
			s.setAisleCode(n);
			tList.add(s);
		}
		return tList;
	}
}
