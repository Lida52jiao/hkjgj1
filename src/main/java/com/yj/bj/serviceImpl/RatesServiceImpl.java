package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Rates;
import com.yj.bj.mapper.RatesMapper;
import com.yj.bj.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RatesServiceImpl extends BaseServiceImpl<Rates> implements RatesService {
	
	@Autowired
	private RatesMapper ratesMapper;
}
