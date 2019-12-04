package com.yj.bj.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.MerChants;
import com.yj.bj.entity.MerChantsRate;
import com.yj.bj.mapper.MerChantsMapper;
import com.yj.bj.mapper.MerChantsRateMapper;
import com.yj.bj.service.MerChantsRateService;
import com.yj.bj.util.HttpClientUtils;
import com.yj.bj.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MerChantsRateServiceImpl extends BaseServiceImpl<MerChantsRate> implements MerChantsRateService {
	
	@Autowired
	private MerChantsRateMapper merchantsRateMapper;
	@Autowired
	private MerChantsMapper merChantsMapper;

	@Override
	public void find(String merChantId, String appId, String institutionId, List<MerChantsRate> list) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("merchantId", merChantId);
		param.put("appId", appId);
		param.put("institutionId", institutionId);
		param.put("timestamp", System.currentTimeMillis());
		param.put("jsonStr", JSON.toJSONString(list));
		String aisleSign= SignUtil.createYJSign(param);
		param.put("sign",aisleSign);
		String resultJsonStr = HttpClientUtils.doPosts(Constaint.HOSTS, param);
	}

	@Override
	public void alter(MerChants h, MerChantsRate merChantsRates) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("merchantId", h.getMerChantId());
		param.put("institutionId", h.getInstitutionId());
		param.put("appId", h.getAppId());
		param.put("rate", merChantsRates.getRate());
		param.put("d0Fee", merChantsRates.getD0Fee());
		param.put("aisleCode", merChantsRates.getAisleCode());
		String aisleSign= SignUtil.createYJSign(param);
		param.put("sign",aisleSign);
		String resultJsonStr = HttpClientUtils.doPosts(Constaint.BIND+"/"+merChantsRates.getAisleCode()+ Constaint.BINDS, param);
	}

	@Override
	public void send(MerChants h) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("merchantId", h.getOneMerId());
		param.put("institutionId", h.getInstitutionId());
		String resultJsonStr = HttpClientUtils.doPosts(Constaint.JIFEN, param);
		System.out.println(resultJsonStr);
	}
}
