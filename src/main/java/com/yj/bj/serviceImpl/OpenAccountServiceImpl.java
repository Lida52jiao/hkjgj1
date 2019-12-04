package com.yj.bj.serviceImpl;


import com.yj.bj.entity.*;
import com.yj.bj.mapper.MerChantsMapper;
import com.yj.bj.service.*;
import com.yj.bj.util.HttpClientUtils;
import com.yj.bj.util.PasswordHelper;
import com.yj.bj.util.YJ;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OpenAccountServiceImpl implements OpenAccountService {

	private static final Log logger = LogFactory.getLog(OpenAccountServiceImpl.class);
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private MerChantsMapper merChantsMapper;
	@Autowired
	private AgentService agentService;
	@Autowired
	private UserService userService;
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private AgentRateService agentRateService;
	@Autowired
	private RatesService ratesService;
	@Autowired
	private MerChantsRateService merchantsRateService;

	public void openAccount(MerChants mer, String agentId, String oldAgentId) {
		
		//省级代理商、进行开户、绑定关系操作
		UserEntity userFormMap = new UserEntity();
		userFormMap.setAccountName(mer.getMerName());
		userFormMap.setPassWord("12345");
		UserEntity r= PasswordHelper.encryptPassword(userFormMap);
		/*Agent n=new Agent(newMer.getMerName(), newMer.getMerMp(), newMer.getMerName(), r.getPassWord(), ".com", oneMerId, System.currentTimeMillis()+"");
		agentService.save(n);
		Agent h=agentService.findByObject(n);
		String sagentId = "C" + YJ.formatDate(new Date()) + YJ.formattime(new Date());
		long id = h.getId() + 10000;
		String merId = sagentId + id;
		h.setMerId(merId);
		h.setTotalCode("0");
		h.setAssign("0");
		h.setGeneratedCode("0");
		h.setUsed("0");
		h.setNotused("0");
		agentService.update(h);*/
		UserEntity k=new UserEntity();
		k.setAccountName(mer.getMerMp());
		k.setDate(YJ.formatDate(new Date())+ YJ.formattime(new Date()));
		k.setEmail(".com");
		k.setPassWord("12345");
		k.setMerId(agentId);
		k.setTel(mer.getMerMp());
		k.setUserName(mer.getMerMp());
		k.setLocked("1");
		PasswordHelper.encryptPassword(k);
		userService.save(k);
		//代理商绑定关系
		/*Transaction savetransation=new Transaction();
		savetransation.setMerId(merId);
		savetransation.setAgentName(newMer.getMerName());
		savetransation.setMerChantId(merChantId);
		savetransation.setCreatDate(System.currentTimeMillis()+"");
		savetransation.setAgentStatus("1");
		String message = transactionService.save(savetransation);*/
		//修改商户的归属代理商号为自己
		/*MerChants mert = new MerChants();
		mert.setMerChantId(merChantId);
		MerChants mer = merChantsService.findByObject(mert);
		mer.setAgentId(merId);
		merChantsService.update(mer);
		logger.info("绑定商户关系条数："+message);*/

		MerChantsRate merRate=new MerChantsRate();
		merRate.setAppId("0000");
		merRate.setMerType(mer.getMerType());
		List<MerChantsRate> rateList =  merchantsRateService.queryObjectForList(merRate);

		for(MerChantsRate rate:rateList){
			//无卡的
			if(rate.getIsRepayment().equals("N")){
				AgentRate agentRate = new AgentRate();
				agentRate.setMerChantId(mer.getMerChantId());
				agentRate.setAgentId(agentId);
				agentRate.setRate(rate.getRate());
				agentRate.setD0Fee(rate.getD0Fee());
				agentRate.setAisleCode(rate.getAisleCode());
				agentRateService.save(agentRate);

			} else if(rate.getIsRepayment().equals("Y")){//还款的
				Rates rates = new Rates();
				rates.setMerChantId(mer.getMerChantId());
				rates.setAgentId(agentId);
				rates.setRate(rate.getRate());
				rates.setD0Fee(rate.getD0Fee());
				rates.setAisleCode(rate.getAisleCode());
				ratesService.save(rates);
			}
		}

		List<Agent> upAgentList=agentService.getUpAgentListByMerId(mer.getMerChantId());
		Map<String,String> upAgentMap=new HashedMap();
		for (Agent agent:upAgentList){
			upAgentMap.put(agent.getMerId(),agent.getMerId());
		}
		//查询此商户是不是别人的直推、如果是更改这些商户的代理商号为此商户的
		MerChants onem = new MerChants();
		onem.setOneMerId(mer.getMerChantId());
		List<MerChants> onelist = merChantsService.queryObjectForList(onem);
		//查询此商户是不是别人的间推、如果是更改这些商户的代理商号为此商户的
		MerChants twom = new MerChants();
		twom.setTwoMerId(mer.getMerChantId());
		List<MerChants> twolist = merChantsService.queryObjectForList(twom);
		//查询此商户是不是别人的间间推。如果是更改这些商户的代理商号为此商户的
		MerChants threem = new MerChants();
		threem.setThreeMerId(mer.getMerChantId());
		List<MerChants> threelist = merChantsService.queryObjectForList(threem);

		List<MerChants> allList=new ArrayList<>();
		allList.addAll(onelist);
		allList.addAll(twolist);
		allList.addAll(threelist);
		for(MerChants mer123: allList){
			if ("N".equals(mer123.getAgentStatus())){//不是代理
				if (null!=upAgentMap.get(mer123.getAgentId())){
					//本商户的上级代理是升级人上级所有代理中的一个  就带走
					mer123.setAgentId(agentId);
					merChantsService.update(mer123);
				}
			} else {//是代理
				Agent agent=new Agent();
				agent.setMerId(mer123.getAgentId());
				agent=agentService.findByObject(agent);
				if (oldAgentId.equals(agent.getOneMerId())){
					//本代理的上级代理跟升级人的上级代理相同就带走
					agent.setOneMerId(agentId);
					agentService.update(agent);
				}
			}

		}
	}
		
}
