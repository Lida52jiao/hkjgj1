package com.yj.bj.controller;

import com.yj.bj.entity.*;
import com.yj.bj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("AgentRate")
public class AgentRateController extends BaseController {
	
	@Autowired
	private AgentRateService agentRateService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private ChannelRateService channelRateService;
	
	@RequestMapping(value = "getAgentRateList")
	public @ResponseBody
	List<AgentRate> select(String merChantId, String aisleCode) {
			MerChants m = new MerChants();
			m.setMerChantId(merChantId);
			MerChants h = merChantsService.findByObject(m);
			Agent agent=new Agent();
			agent.setMerId(h.getAgentId());
			Agent t=agentService.findByObject(agent);
			List<Agent> list=new ArrayList<Agent>();
			List<Transaction> tList=new ArrayList<Transaction>();
			List<Agent> agentList=new ArrayList<>();
			agentList.add(t);
			agentList.addAll(get(list,t));
			for(Agent n:agentList){
				Agent s=(Agent)n;
				Transaction transaction=new Transaction();
				transaction.setMerId(s.getMerId());
				Transaction transactions=transactionService.findByObject(transaction);
				if(transactions != null){
					tList.add(transactions);
				}
			}
			List<AgentRate> lists = new ArrayList<AgentRate>();
			for(Transaction transaction : tList){
				Transaction transactions = (Transaction)transaction;
				AgentRate agentRate = new AgentRate();
				agentRate.setAgentId(transactions.getMerId());
				agentRate.setAisleCode(aisleCode);
				AgentRate n = agentRateService.findByObject(agentRate);
				if(n != null){
					lists.add(n);
					continue;
				}
				ChannelRate channelRate = new ChannelRate();
				channelRate.setAisleCode(aisleCode);
				channelRate.setAppId(h.getAppId());
				ChannelRate v = channelRateService.findByObject(channelRate);
				AgentRate s = new AgentRate();
				s.setAgentId(transactions.getMerId());
				s.setMerChantId(transactions.getMerChantId());
				s.setRate(v.getRate());
				s.setD0Fee(v.getD0Fee());
				lists.add(s);
			}
			return lists;
	}
	
	public List<Agent> get(List<Agent> list,Agent t){
		if("".equals(t.getOneMerId()) || null == t.getOneMerId()){
			return list;
		}
		if(!"".equals(t.getOneMerId()) && null != t.getOneMerId()){
			Agent agent=new Agent();
			agent.setMerId(t.getOneMerId());
			Agent n=agentService.findByObject(agent);
			list.add(n);
			get(list,n);
		}
		return list;	
	}
}
