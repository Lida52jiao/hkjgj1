package com.yj.bj.controller;

import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.*;
import com.yj.bj.service.*;
import com.yj.bj.util.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("test")
public class TestController extends BaseController {

    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private NumService numService;
    @Autowired
    private TransactionService transactionService;

    //卡友注册
    @RequestMapping(value = "test")
    public @ResponseBody
    YJResult test(String phone,String name,String idCardNo,String oneMerPhone,String level,String agentLevel,@RequestParam(defaultValue = Constaint.AGENT)String institutionId,@RequestParam(defaultValue = "0000") String appId,@RequestParam(defaultValue = "精彩生活") String appName){
        MerChants m = new MerChants();
        m.setMerMp(phone);
        m=merChantsService.findByObject(m);
        if (m!=null){
            return YJResult.build("1234","cunzai");
        }
        MerChants oneMer = new MerChants();
        oneMer.setMerMp(oneMerPhone);
        oneMer=merChantsService.findByObject(oneMer);
        if (oneMer==null){
            return YJResult.build("1234","找不到直推");
        }
        Agent oneAgent=new Agent();
        oneAgent.setMerMp(oneMerPhone);
        oneAgent=agentService.findByObject(oneAgent);
        if (oneAgent==null){
            return YJResult.build("4321","找不到代理");
        }
        String merType=level;
        String agentStatus=agentLevel;

        MerChants h = new MerChants();
        h.setMerName(name);
        h.setCertNo(idCardNo);
        h.setOneMerId(oneMer.getMerChantId());
        h.setTwoMerId(oneMer.getOneMerId());
        h.setThreeMerId(oneMer.getTwoMerId());

        h.setMerMp(phone);
        h.setIdentifying(null);
        h.setInstitutionId(institutionId);
        h.setMerType(merType);
        h.setMerStat("Y");
        h.setStatus("Y");
        h.setAgentStatus(agentStatus);
        h.setFrozen("N");
        h.setAppId(appId);
        h.setAppName(URLDecoder.decode(appName));
        h.setRegDate(System.currentTimeMillis() + "");
        h.setStartDate(System.currentTimeMillis() + "");
        h.setFinishDate(System.currentTimeMillis() + "");
        h.setBalance(BigDecimal.ZERO);
        h.setBalanceFrozen(BigDecimal.ZERO);
        h.setBalanceProfit(BigDecimal.ZERO);
        h.setBalanceProfitFrozen(BigDecimal.ZERO);

        merChantsService.save(h);
        MerChants v = new MerChants();
        v.setMerMp(phone);
        v.setAppId(appId);
        MerChants t = merChantsService.findByObject(v);
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        String s = "M" + idWorker.nextId();
        long id = t.getId() + 10000;
        String merChantId = s + id;
        t.setMerChantId(merChantId);
        merChantsService.update(t);

        Agent agent=new Agent();
        agent.setMerMp(phone);
        agent.setMerName(name);
        agent.setAccountNumber(phone);
        agent.setPassword("e40adc0f052b048addfdbff74355817b");
        agent.setOneMerId(oneAgent.getMerId());
        agent.setCreatDate(new Date().getTime()+"");

        agentService.save(agent);
        Agent agent1=new Agent();
        agent1.setMerMp(phone);
        agent1=agentService.findByObject(agent1);
        String s1 = "C" + idWorker.nextId();
        long id1 = t.getId() + 10000;
        String agentId = s1 + id1;
        agent1.setMerId(agentId);
        agentService.update(agent1);

        Transaction transaction=new Transaction();
        transaction.setCreatDate(new Date().getTime()+"");
        transaction.setMerId(agentId);
        transaction.setAgentName(name);
        transaction.setMerChantId(merChantId);
        transaction.setAgentStatus(agentLevel);
        transactionService.save(transaction);
        return YJResult.ok();
    }


}
