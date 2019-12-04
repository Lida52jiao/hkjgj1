package com.yj.bj.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.CardInformation;
import com.yj.bj.mapper.CardInformationMapper;
import com.yj.bj.service.CardInformationService;
import com.yj.bj.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardInformationServiceImpl extends
		BaseServiceImpl<CardInformation> implements CardInformationService {

	@Autowired
	private CardInformationMapper cardInformationMapper;

	@Override
	public int find(String token, CardInformation cardInformation) {
		Map<String, String> param = new HashMap<>();
		param.put("token", token);
		param.put("merchantId", cardInformation.getMerChantId());
		param.put("state", "2");
		param.put("cardNo", cardInformation.getCardNumber());
		String resultJsonStr = HttpClientUtils.doPost(Constaint.HOST, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		String respDesc=job.getString("data");
		JSONObject s = JSONObject.parseObject(respDesc);
		String list=s.getString("list");
		JSONArray t = JSONObject.parseArray(list);
		return t.size();
	}

	@Override
	public int gains(String merChantId) {
		int credit = cardInformationMapper.get(merChantId);
		return credit;
	}

	@Override
	public int selectcard(String merChantId) {
		int card = cardInformationMapper.getcard(merChantId);
		return card;
	}
}
