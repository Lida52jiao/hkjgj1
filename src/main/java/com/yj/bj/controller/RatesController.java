package com.yj.bj.controller;

import com.yj.bj.entity.Agent;
import com.yj.bj.entity.MerChants;
import com.yj.bj.entity.Rates;
import com.yj.bj.entity.Transaction;
import com.yj.bj.service.AgentService;
import com.yj.bj.service.MerChantsService;
import com.yj.bj.service.RatesService;
import com.yj.bj.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("Rates")
public class RatesController extends BaseController {
	
	@Autowired
	private RatesService ratesService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;
	
	@RequestMapping(value = "getRatesList")
	public @ResponseBody
	List<Rates> select(String merChantId, String aisleCode) {
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
			List<Rates> lists = new ArrayList<Rates>();
			for(Transaction transaction : tList){
				Transaction transactions = (Transaction)transaction;
				Rates rate = new Rates();
				rate.setAgentId(transactions.getMerId());
				rate.setAisleCode(aisleCode);
				Rates rates = ratesService.findByObject(rate);
				if(null != rates){
					lists.add(rates);
				}
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
