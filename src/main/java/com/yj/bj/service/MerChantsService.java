package com.yj.bj.service;

import com.yj.bj.entity.*;
import com.yj.bj.util.YJResult;

import java.math.BigDecimal;
import java.util.List;


public interface MerChantsService extends BaseService<MerChants> {
	
	int statistics(MerChants v);

	int receive(String merChantId);

	int find(String merChantId);

	DuanXin getduanXin(String institutionId, String appId);

	RenZhen getrenZhen(String institutionId, String appId);

	MerChant gain(MerChant t);

	void send(MerChants h);

	void bind(MerChants k);

	void sends(MerChants h);

	YJResult share(MerChants h, Num num, List<Mer> merList, String orderNo);
}
