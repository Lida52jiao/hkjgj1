package com.yj.bj.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.*;
import com.yj.bj.entity.Transactional;
import com.yj.bj.mapper.MerChantsMapper;
import com.yj.bj.mapper.RecordMapper;
import com.yj.bj.mapper.UsedMapper;
import com.yj.bj.service.MerChantsService;
import com.yj.bj.service.OrderService;
import com.yj.bj.service.TransactionalService;
import com.yj.bj.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerChantsServiceImpl extends BaseServiceImpl<MerChants> implements
        MerChantsService {

	@Autowired
	private MerChantsMapper merChantsMapper;
	@Autowired
	private RecordMapper recordMapper;
	@Autowired
	private UsedMapper usedMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private TransactionalService transactionalService;
	@Autowired
	private SnowflakeIdWorker snowflakeIdWorker;

	@Override
	public int statistics(MerChants v) {
		int n=merChantsMapper.gain(v);
		return n;
	}

	@Override
	public int receive(String merChantId) {
		int n=merChantsMapper.selectCode(merChantId);
		return n;
	}

	@Override
	public int find(String merChantId) {
		int n=merChantsMapper.get(merChantId);
		return n;
	}

	@Override
	public DuanXin getduanXin(String institutionId, String appId) {
		Map<String, String> param = new HashMap<>();
		param.put("institutionId", institutionId);
		param.put("appId", appId);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.DuanXin, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		DuanXin duanXin=new DuanXin(job.get("accessKeyId").toString(), job.get("accessKeySecret").toString(), job.get("product").toString(), job.get("domain").toString(), job.get("autograph").toString(), job.get("templateCode").toString());
		return duanXin;
	}

	@Override
	public RenZhen getrenZhen(String institutionId, String appId) {
		Map<String, String> param = new HashMap<>();
		param.put("institutionId", institutionId);
		param.put("appId", appId);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.RenZhen, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		RenZhen renZhen=new RenZhen(job.get("gatewayUrl").toString(), job.get("appIds").toString(), job.get("privateKey").toString(), job.get("zhimaPublicKey").toString(), job.getString("back").toString());
		return renZhen;
	}

	@Override
	public MerChant gain(MerChant t) {
		MerChant s = merChantsMapper.receive(t);
		return s;
	}

	@Override
	public void send(MerChants h) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("merchantId", h.getMerChantId());
		param.put("oneMerId", h.getOneMerId());
		param.put("appId", h.getAppId());
		param.put("institutionId", h.getInstitutionId());
		param.put("timestamp", System.currentTimeMillis());
		String aisleSign= SignUtil.createYJSign(param);
		param.put("sign",aisleSign);
		String resultJsonStr = HttpClientUtils.doPosts(Constaint.S, param);
	}

	@Override
	public void bind(MerChants k) {
		Map<String, String> param = new HashMap<>();
		param.put("merchantId", k.getOneMerId());
		param.put("msg", k.getMerMp());
		param.put("type", "3");
		param.put("style", "1");
		param.put("agentId", k.getAgentId());
		param.put("institutionId", k.getInstitutionId());
		param.put("appId", k.getAppId());
		String resultJsonStr = HttpClientUtils.doPost(Constaint.Bind, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		System.out.println(job);
	}

	@Override
	public void sends(MerChants h) {
		Map<String, String> param = new HashMap<>();
		param.put("merchantId", h.getOneMerId());
		param.put("msg", h.getMerName());
		param.put("type", "3");
		param.put("style", "2");
		param.put("agentId", h.getAgentId());
		param.put("institutionId", h.getInstitutionId());
		param.put("appId", h.getAppId());
		String resultJsonStr = HttpClientUtils.doPost(Constaint.Bind, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		System.out.println(job);
	}

	@org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public YJResult share(MerChants h, Num num, List<Mer> merList, String orderNo) {
//		if(h.getMerType().equals(num.getMerType())){
//			return SUCCESS;
//		}
//		String orderNo = "T" + snowflakeIdWorker.nextId();
//		Order order = new Order();
//		order.setMerChantId(h.getMerChantId());
//		order.setMerName(h.getMerName());
//		order.setMerMp(h.getMerMp());
//		order.setAgentId(h.getAgentId());
//		order.setMerType(h.getMerType());
//		order.setRank(num.getMerType());
//		order.setCreatDate(String.valueOf(System.currentTimeMillis()));
//		order.setAmount(Long.parseLong(num.getNum()));
//		order.setState(SUCCESS);
//		order.setOrderNo(orderNo);
//		orderService.save(order);
		Order order = new Order();
		order.setOrderNo(orderNo);
		order = orderService.findByObject(order);
		order.setShare("Y");
		orderService.update(order);
		for(Mer mer : merList){
			Transactional transactional = new Transactional();
			transactional.setFee(mer.getProfit());
			transactional.setAmount(Long.parseLong(num.getNum()));
			transactional.setOrderNo(orderNo);
			transactional.setRate("0");
			transactional.setMerchantId(mer.getMerChantId());
			transactional.setOrderMerchantId(h.getMerChantId());
			transactional.setOrderMerchantName(h.getMerName());
			transactional.setOrderMerchantPhone(h.getMerMp());
			transactional.setType(num.getPayType());
			transactional.setAgentId(h.getAgentId());
			transactional.setInstitutionId(h.getInstitutionId());
			transactional.setLevel(mer.getStatus());
			transactional.setAppId(h.getAppId());
			transactionalService.save(transactional);
		}
		return YJResult.ok();
	}
}
