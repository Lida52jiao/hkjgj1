package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Agentarea;
import com.yj.bj.mapper.AgentareaMapper;
import com.yj.bj.service.AgentareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentareaServiceImpl extends BaseServiceImpl<Agentarea> implements AgentareaService {
	
	@Autowired
	private AgentareaMapper agentareaMapper;

}
