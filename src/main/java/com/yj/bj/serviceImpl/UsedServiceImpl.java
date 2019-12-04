package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Used;
import com.yj.bj.mapper.UsedMapper;
import com.yj.bj.service.UsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsedServiceImpl extends BaseServiceImpl<Used> implements UsedService {
	
	@Autowired
	private UsedMapper usedMapper;
}
