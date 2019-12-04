package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Withdrawals;
import com.yj.bj.mapper.WithdrawalsMapper;
import com.yj.bj.service.WithdrawalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service 
public class WithdrawalsServiceImpl extends BaseServiceImpl<Withdrawals> implements WithdrawalsService {
	
	@Autowired
	private WithdrawalsMapper withdrawalsMapper;
}
