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
@RequestMapping("Subsidy")
public class SubsidyController extends BaseController {
	
	@Autowired
	private SubsidyService subsidyService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentareaService agentareaService;
	@Autowired
	private AreaService areaService;
	
	//获取补贴的信息
	@RequestMapping(value = "selectSubsidy")
	public @ResponseBody
	Subsidize select(String merChantId) {
		Subsidy subsidy = new Subsidy();
		subsidy.setId((long)1);
		Subsidy t = subsidyService.findByObject(subsidy);
		MerChants m = new MerChants();
		m.setMerChantId(merChantId);
		MerChants h = merChantsService.findByObject(m);
		Agent s = new Agent();
		s.setMerId(h.getAgentId());
		Agent n = agentService.findByObject(s);
		
		List<Area> tList = getArea(h);
		if(h.getInstitutionId().equals(h.getAgentId())){
			Subsidize subsidize = new Subsidize();
			subsidize.setTwoAgent(t.getTwoAgent());
			subsidize.setCountryAgent(t.getCountryAgent());
			subsidize.setCityAgent(t.getCityAgent());
			subsidize.setProvinceAgent(t.getProvinceAgent());
			subsidize.setOneMerchant(t.getOneMerchant());
			subsidize.setTwoMerchant(t.getTwoMerchant());
			subsidize.setTopAgent(t.getTopAgent());
			if(!tList.isEmpty()){
				for(Area area:tList){
					Agentarea agentarea = new Agentarea();
					agentarea.setAreaCode(area.getCode());
					Agentarea hhh = agentareaService.findByObject(agentarea);
					if(hhh!=null){
						if(hhh.getAreaLevel().equals("1")){
							subsidize.setProvinceAgentId(hhh.getMerchantId());
						}
						if(hhh.getAreaLevel().equals("2")){
							subsidize.setCityAgentId(hhh.getMerchantId());			
										}
						if(hhh.getAreaLevel().equals("3")){
							subsidize.setCountryAgentId(hhh.getMerchantId());
						}
					}
				}
			}
			subsidize.setOneMerchantId(h.getOneMerId());
			subsidize.setTwoMerchantId(h.getTwoMerId());
			return subsidize;
		}
		Transaction transaction = new Transaction(); 
		transaction.setMerId(n.getOneMerId());
		Transaction k = transactionService.findByObject(transaction);
		List<Agent> list = selects(merChantId);
		List<Agent> lists = new ArrayList<Agent>();
		for(Agent agents : list){
			Agent agent = (Agent)agents;
			if(h.getInstitutionId().equals(agent.getOneMerId())){
				lists.add(agent);
			}
		}
		Transaction transactions = new Transaction();
		transactions.setMerId(lists.get(0).getMerId());
		Transaction b = transactionService.findByObject(transactions);
		
		Subsidize subsidize = new Subsidize();
		subsidize.setTwoAgent(t.getTwoAgent());
		subsidize.setCountryAgent(t.getCountryAgent());
		subsidize.setCityAgent(t.getCityAgent());
		subsidize.setProvinceAgent(t.getProvinceAgent());
		subsidize.setOneMerchant(t.getOneMerchant());
		subsidize.setTwoMerchant(t.getTwoMerchant());
		subsidize.setTopAgent(t.getTopAgent());
		subsidize.setTwoAgentId(k.getMerChantId());
		if(!tList.isEmpty()){
			for(Area area:tList){
				Agentarea agentarea = new Agentarea();
				agentarea.setAreaCode(area.getCode());
				Agentarea hhh = agentareaService.findByObject(agentarea);
				if(hhh!=null){
					if(hhh.getAreaLevel().equals("1")){
						subsidize.setProvinceAgentId(hhh.getMerchantId());
					}
					if(hhh.getAreaLevel().equals("2")){
						subsidize.setCityAgentId(hhh.getMerchantId());			
									}
					if(hhh.getAreaLevel().equals("3")){
						subsidize.setCountryAgentId(hhh.getMerchantId());
					}
				}
			}
		}
		subsidize.setOneMerchantId(h.getOneMerId());
		subsidize.setTwoMerchantId(h.getTwoMerId());
		subsidize.setTopAgentId(b.getMerChantId());
		return subsidize;
	}

	//查询上级代理商
	List<Agent> selects(String merChantId) {
		MerChants m = new MerChants();
		m.setMerChantId(merChantId);
		MerChants h = merChantsService.findByObject(m);
		Agent agent=new Agent();
		agent.setMerId(h.getAgentId());
		Agent t=agentService.findByObject(agent);
		List<Agent> list=new ArrayList<Agent>();
		List<Agent> agentList=new ArrayList<Agent>();
		agentList.add(t);
		agentList.addAll(get(list,t));
		return agentList;
	}
	
	//递归
	public List<Agent> get(List<Agent> list, Agent t){
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
	
	//获取省市县
	List<Area> getArea(MerChants merChants) {
		String allSixCode = merChants.getCertNo().substring(0, 6);//商户身份证前六位
		String beforeTwoCode = allSixCode.substring(0,2)+"0000";//商户身份证前两位+0000找省
		String beforeFourCode = allSixCode.substring(0,4)+"00";//商户身份证前四位+00找市
		List<Area> list = new ArrayList<Area>();
		Area area = new Area();
		area.setCode(allSixCode);
		Area country = areaService.findByObject(area);//身份证前六位找县
		if(country!=null){
			list.add(country);
		}
		Area k = new Area();
		k.setCode(beforeTwoCode);
		Area provice = areaService.findByObject(k);//身份证前两位找省
		if(provice!=null){
			list.add(provice);
		}
		Area b = new Area(); 
		b.setCode(beforeFourCode);
		Area city = areaService.findByObject(b);//身份证前四位找市
		if(city!=null){
			list.add(city);
		}
		return list;
	}

	//获取省市县的代理商
	Agentarea getAgentarea(String code) {
		Agentarea agentarea = new Agentarea();
		agentarea.setAreaCode(code);
		Agentarea h = agentareaService.findByObject(agentarea);
		return h;
	}

}
