package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Fee;
import com.yj.bj.mapper.FeeMapper;
import com.yj.bj.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class FeeServiceImpl extends BaseServiceImpl<Fee> implements FeeService {
	
	@Autowired
	private FeeMapper feeMapper;
}
