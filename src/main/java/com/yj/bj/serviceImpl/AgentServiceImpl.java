package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Agent;
import com.yj.bj.entity.MerChants;
import com.yj.bj.entity.Transaction;
import com.yj.bj.mapper.AgentMapper;
import com.yj.bj.service.AgentService;
import com.yj.bj.service.MerChantsService;
import com.yj.bj.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AgentServiceImpl extends BaseServiceImpl<Agent> implements
        AgentService {

	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private MerChantsService merChantsService;

	@Override
	public List<Agent> getUpAgentListByMerId(String merchantId) {
		List<Agent> agentList=new ArrayList<>();

		Transaction transaction=new Transaction();
		transaction.setMerChantId(merchantId);
		transaction=transactionService.findByObject(transaction);
		if (transaction==null){
			MerChants mer=new MerChants();
			mer.setMerChantId(merchantId);
			mer=merChantsService.findByObject(mer);
			Agent agent=new Agent();
			agent.setMerId(mer.getAgentId());
			agent=agentMapper.selectOne(agent);
			agentList.add(agent);
			agentList=get(agentList,agent);
		}else {
			Agent agent=new Agent();
			agent.setMerId(transaction.getMerId());
			agent=agentMapper.selectOne(agent);
			agentList.add(agent);
			agentList=get(agentList,agent);
		}
		return agentList;
	}

	public List<Agent> get(List<Agent> list, Agent t){
		if("".equals(t.getOneMerId()) || null == t.getOneMerId()){
			return list;
		}
		if(!"".equals(t.getOneMerId()) && null != t.getOneMerId()){
			Agent agent=new Agent();
			agent.setMerId(t.getOneMerId());
			Agent n=agentMapper.selectOne(agent);
			list.add(n);
			get(list,n);
		}
		return list;
	}
}
