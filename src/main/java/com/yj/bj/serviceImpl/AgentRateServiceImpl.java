package com.yj.bj.serviceImpl;

import com.yj.bj.entity.AgentRate;
import com.yj.bj.mapper.AgentRateMapper;
import com.yj.bj.service.AgentRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AgentRateServiceImpl extends BaseServiceImpl<AgentRate> implements AgentRateService {
	
	@Autowired
	private AgentRateMapper agentRateMapper;
}
