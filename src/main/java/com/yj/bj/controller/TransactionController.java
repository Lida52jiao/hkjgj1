package com.yj.bj.controller;

import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.Agent;
import com.yj.bj.entity.MerChants;
import com.yj.bj.entity.Transaction;
import com.yj.bj.service.AgentService;
import com.yj.bj.service.MerChantsService;
import com.yj.bj.service.TransactionService;
import com.yj.bj.util.RedisUtils;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("Transaction")
public class TransactionController extends BaseController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;

	@RequestMapping(value = "whetherAgent", method = RequestMethod.POST)
	public @ResponseBody
	YJResult whetherAgent(String token, String merChantId) {
		Jedis jedis= RedisUtils.getJedis();
		if(jedis.exists(token)){
			Transaction n = new Transaction();
			n.setMerChantId(merChantId);
			Transaction h=transactionService.findByObject(n);
			if(h != null){
				RedisUtils.returnResource(jedis);
				return YJResult.ok(h.getMerId());
			}
			RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.NONE_AGENT, "商户不是代理商");
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "getTransactionList")
	public @ResponseBody
	List<Transaction> select(String merChantId) {
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
			return tList;
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
	
	@RequestMapping(value = "selectInstitutionId", method = RequestMethod.POST)
	public @ResponseBody
	Transaction get() {
		Transaction n = new Transaction();
		n.setMerId(Constaint.AGENT);
		return transactionService.findByObject(n);
	}

	@RequestMapping(value = "test")
	public @ResponseBody
	YJResult select() {
		List<Transaction> list=transactionService.queryObjectForList();
		for(Transaction transaction : list){
			MerChants merChants = new MerChants();
			merChants.setMerChantId(transaction.getMerChantId());
			merChants = merChantsService.findByObject(merChants);
			if(merChants != null){
				merChants.setAgentId(transaction.getMerId());
				merChantsService.update(merChants);
			}
			System.out.println(transaction.getMerChantId());
		}
		return YJResult.ok();
	}
}
