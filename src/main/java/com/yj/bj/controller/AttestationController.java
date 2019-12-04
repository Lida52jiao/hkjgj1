package com.yj.bj.controller;

import com.alibaba.fastjson.JSONObject;
import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.*;
import com.yj.bj.service.*;
import com.yj.bj.util.HttpClientUtils;
import com.yj.bj.util.RedisUtils;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("Attestation")
public class AttestationController extends BaseController {

	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private MerChantsRateService merchantsRateService;
	@Autowired
	private JiDaoService jiDaoService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "attestation", method = RequestMethod.POST)
	public @ResponseBody
	YJResult attestation(String token, String merChantId, String merName, String certNo, String cardNumber, String institutionId, String appId) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			MerChants m=new MerChants();
			m.setMerChantId(merChantId); 
			MerChants t=merChantsService.findByObject(m);
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("merchantId", merChantId);
			hashMap.put("institutionId", t.getInstitutionId());
			hashMap.put("type", "sys");
			hashMap.put("name", URLDecoder.decode(merName));
			hashMap.put("card", cardNumber);
			hashMap.put("certNo", certNo);
			String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/check", hashMap);
			JSONObject job = JSONObject.parseObject(resultJsonStr);
			if("0000".equals(job.getString("respCode"))){
				String data = job.getString("data");
				JSONObject jobs = JSONObject.parseObject(data);
				String result = jobs.getString("result");
				JSONObject s = JSONObject.parseObject(result);
				String status = s.getString("status");
			if("S".equals(status)){
				MerChants merChant = new MerChants();
				merChant.setMerChantId(merChantId);
				MerChants h = merChantsService.findByObject(merChant);
				h.setMerName(merName);
				h.setCertNo(certNo);
				h.setMerStat("Y");
				h.setMerStatTime(System.currentTimeMillis()+"");
				h.setUsed("N");
				List<Area> tList = getArea(h);
				if(!tList.isEmpty()){
					for(Area area : tList){
						Area areas = (Area)area;
						if(null != areas){
							if("3".equals(areas.getLevel())){
								h.setCounty(areas.getCode());
							}else if("2".equals(areas.getLevel())){
								h.setCity(areas.getCode());
							}else if("1".equals(areas.getLevel())){
								h.setProvince(areas.getCode());
							}
						}
					}
				}
				merChantsService.update(h);
				try{
					merchantsRateService.send(h);
				}catch(Exception e){
					
				}
				try{
					merChantsService.sends(h);
				}catch(Exception e){
					
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("merChantId", merChantId);
				map.put("merName", merName);
				map.put("certNo", certNo);
				map.put("cardNumber", cardNumber);
				map.put("status", status);
				RedisUtils.returnResource(jedis);
				return YJResult.ok(map);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("merChantId", merChantId);
			map.put("merName", merName);
			map.put("certNo", certNo);
			map.put("cardNumber", cardNumber);
			map.put("status", status);
			RedisUtils.returnResource(jedis);
			return YJResult.ok(map);
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(job.getString("respCode"), job.getString("respDesc"));
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	//获取省市县
	List<Area> getArea(MerChants h) {
		String s = h.getCertNo().substring(0, 6);
		Area area = new Area();
		area.setCode(s);
		Area t = areaService.findByObject(area);
		List<Area> list = new ArrayList<Area>();
		if(t != null){
			list.add(t);
			Area k = new Area();
			k.setId(Long.parseLong(t.getParent_id()));
			Area n = areaService.findByObject(k);
			list.add(n);
			if(n != null){
				Area b = new Area(); 
				b.setId(Long.parseLong(n.getParent_id()));
				Area tt = areaService.findByObject(b);
				list.add(tt);
			}
		}
		return list;
		}

	@RequestMapping(value = "test", method = RequestMethod.POST)
	public @ResponseBody
	YJResult test(String merChantId, String merName, String certNo, String cardNumber, String institutionId, String appId) {
			MerChants m=new MerChants();
			m.setMerChantId(merChantId);
			MerChants t=merChantsService.findByObject(m);
			if(null != t){
				JiDao jidao = new JiDao(merChantId, merName, cardNumber, System.currentTimeMillis()+"");
				jidao.setAppId(t.getAppId());
				jiDaoService.save(jidao);
			}
			String url="http://www.jidaoyouxin.com/api/v1/id_check";
			JSONObject data=new JSONObject();
			data.put("name", merName);
			data.put("id_card", certNo);
			String resultJsonStr = HttpClientUtils.doPostJson(url, data.toJSONString());
			JSONObject job = JSONObject.parseObject(resultJsonStr);
			String result = job.getString("result");
			JSONObject s = JSONObject.parseObject(result);
			String status = s.getString("status");
			if("一致".equals(status)){
				MerChants merChant = new MerChants();
				merChant.setMerChantId(merChantId);
				MerChants h = merChantsService.findByObject(merChant);
				h.setMerName(merName);
				h.setCertNo(certNo);
				h.setMerStat("Y");
				h.setMerStatTime(System.currentTimeMillis()+"");
				h.setUsed("N");
				List<Area> tList = getArea(h);
				if(!tList.isEmpty()){
					for(Area area : tList){
						Area areas = (Area)area;
						if(null != areas){
							if("3".equals(areas.getLevel())){
								h.setCounty(areas.getCode());
							}else if("2".equals(areas.getLevel())){
								h.setCity(areas.getCode());
							}else if("1".equals(areas.getLevel())){
								h.setProvince(areas.getCode());
							}
						}
					}
				}
				merChantsService.update(h);
				try{
					merchantsRateService.send(h);
				}catch(Exception e){

				}
				try{
					merChantsService.sends(h);
				}catch(Exception e){

				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("merChantId", merChantId);
				map.put("merName", merName);
				map.put("certNo", certNo);
				map.put("cardNumber", cardNumber);
				map.put("status", status);
				return YJResult.ok(map);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("merChantId", merChantId);
			map.put("merName", merName);
			map.put("certNo", certNo);
			map.put("cardNumber", cardNumber);
			map.put("status", status);
			return YJResult.ok(map);
	}
}
