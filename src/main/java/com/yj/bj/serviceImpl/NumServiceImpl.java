package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Num;
import com.yj.bj.mapper.NumMapper;
import com.yj.bj.service.NumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NumServiceImpl extends BaseServiceImpl<Num> implements NumService {
	
	@Autowired
	private NumMapper numMapper;
}
