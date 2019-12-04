package com.yj.bj.service;

import com.yj.bj.entity.MerChants;
import com.yj.bj.entity.MerChantsRate;

import java.util.List;


public interface MerChantsRateService extends BaseService<MerChantsRate> {

	void find(String merChantId, String appId, String institutionId, List<MerChantsRate> list);

	void alter(MerChants h, MerChantsRate merChantsRates);

	void send(MerChants h);
}
