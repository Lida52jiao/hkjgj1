package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Credit;
import com.yj.bj.mapper.CreditMapper;
import com.yj.bj.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreditServiceImpl extends BaseServiceImpl<Credit> implements CreditService {
	
	@Autowired
	private CreditMapper creditMapper;
}
