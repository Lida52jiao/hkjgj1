package com.yj.bj.controller;

import com.yj.bj.entity.*;
import com.yj.bj.service.*;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡神的缴费模式表
 * @author N
 *
 */
@Controller
@RequestMapping("/KSgetPaymentNum/")
public class KSPaymentNumCtl {

//	@Autowired
//	private KSPaymentNumService ksPaymentNumService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentareaService agentareaService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private MerchantsUsedAreaService merchantsUsedAreaService;
	
//	@RequestMapping("num")
//	@ResponseBody
//	public YJResult getAllMessage(String paymentidentity){
//		List<PaymentNum> list = ksPaymentNumService.getAll(paymentidentity);
//		return new YJResult("0000", "数据返回成功", list);
//	}
	//查询某一个商户的省市县代理是谁
//	@RequestMapping("getArea")
//	@ResponseBody
//	public YJResult getAllArea(String merChantId){
//		MerChants merChant = new MerChants();
//		merChant.setMerChantId(merChantId);
//		MerChants merChants = merChantsService.findByObject(merChant);
//		List<Area> list = getArea(merChants);
//		List<Agentarea> areaList = new ArrayList<Agentarea>();
//		if(merChants == null){
//			return new YJResult("0001", "查无此商户", areaList);
//		}
//		KSAreaAgent areaAgent = new KSAreaAgent();
//		for(Area area:list){
//			Agentarea agentarea = new Agentarea();
//			agentarea.setAreaCode(area.getCode());
//			Agentarea h = agentareaService.findByObject(agentarea);
//			if(h!=null){
//				if(h.getAreaLevel().equals("1")){
//					areaAgent.setProviceMerChantId(h.getMerchantId());
//				}
//				if(h.getAreaLevel().equals("2")){
//					areaAgent.setCityMerChantId(h.getMerchantId());			
//								}
//				if(h.getAreaLevel().equals("3")){
//					areaAgent.setCountryMerChantId(h.getMerchantId());
//				}
//			}
//		}
//		return new YJResult("0000", "数据返回成功", areaAgent);
//	}
//	//获取省市县
//	List<Area> getArea(MerChants merChants) {
//		String s = merChants.getCertNo().substring(0, 6);
//		Area area = new Area();
//		area.setCode(s);
//		Area t = areaService.findByObject(area);
//		List<Area> list = new ArrayList<Area>();
//		if(t != null){
//			list.add(t);
//			Area k = new Area();
//			k.setId(Long.parseLong(t.getParent_id()));
//			Area n = areaService.findByObject(k);
//			list.add(n);
//			Area b = new Area(); 
//			b.setId(Long.parseLong(n.getParent_id()));
//			Area tt = areaService.findByObject(b);
//			list.add(tt);
//		}
//		return list;
//	}
	@RequestMapping("getArea")
	@ResponseBody
	public YJResult getAllArea(String merChantId){
		MerChants merChant = new MerChants();
		merChant.setMerChantId(merChantId);
		MerChants merChants = merChantsService.findByObject(merChant);
		List<Area> list = getArea(merChants);
		List<Agentarea> areaList = new ArrayList<Agentarea>();
		if(merChants == null){
			return new YJResult("0001", "查无此商户", areaList);
		}
		KSAreaAgent areaAgent = new KSAreaAgent();
		for(Area area:list){
			Agentarea agentarea = new Agentarea();
			agentarea.setAreaCode(area.getCode());
			Agentarea h = agentareaService.findByObject(agentarea);
			if(h!=null){
				if(h.getAreaLevel().equals("1")){
					areaAgent.setProviceMerChantId(h.getMerchantId());
				}
				if(h.getAreaLevel().equals("2")){
					areaAgent.setCityMerChantId(h.getMerchantId());			
								}
				if(h.getAreaLevel().equals("3")){
					areaAgent.setCountryMerChantId(h.getMerchantId());
				}
			}
		}
		return new YJResult("0000", "数据返回成功", areaAgent);
	}
	//获取省市县
	List<Area> getArea(MerChants merChants) {
		String allSixCode = merChants.getCertNo().substring(0, 6);//商户身份证前六位
		String beforeTwoCode = allSixCode.substring(0,2)+"0000";//商户身份证前两位+0000找省
		String beforeFourCode = allSixCode.substring(0,4)+"00";//商户身份证前四位+00找市
		List<Area> list = new ArrayList<Area>();
		Areas areas = new Areas();
		areas.setCode(beforeFourCode);
		areas = areasService.findByObject(areas);
		if(null != areas){
			KSMerUsedArea ksMerUsedArea = new KSMerUsedArea();
			ksMerUsedArea.setMerChantId(merChants.getMerChantId());
			List<KSMerUsedArea> ksMerUsedAreaList = merchantsUsedAreaService.queryObjectForList(ksMerUsedArea);
			if(ksMerUsedAreaList.isEmpty()){
				return list;
			}
			Area area = new Area();
			area.setCode(ksMerUsedAreaList.get(0).getCountryAreaId());
			Area country = areaService.findByObject(area);//身份证前六位找县
			if(country!=null){
				list.add(country);
			}
			Area k = new Area();
			k.setCode(ksMerUsedAreaList.get(0).getProviceAreaId());
			Area provice = areaService.findByObject(k);//身份证前两位找省
			if(provice!=null){
				list.add(provice);
			}
			Area b = new Area(); 
			b.setCode(ksMerUsedAreaList.get(0).getCityAreaId());
			Area city = areaService.findByObject(b);//身份证前四位找市
			if(city!=null){
				list.add(city);
			}
			return list;
		}
		Areas t = new Areas();
		t.setCode(allSixCode);
		t = areasService.findByObject(t);
		if(t != null){
			KSMerUsedArea ksMerUsedArea = new KSMerUsedArea();
			ksMerUsedArea.setMerChantId(merChants.getMerChantId());
			List<KSMerUsedArea> ksMerUsedAreaList = merchantsUsedAreaService.queryObjectForList(ksMerUsedArea);
			if(ksMerUsedAreaList.isEmpty()){
				return list;
			}
			Area area = new Area();
			area.setCode(ksMerUsedAreaList.get(0).getCountryAreaId());
			Area country = areaService.findByObject(area);//身份证前六位找县
			if(country!=null){
				list.add(country);
			}
			Area k = new Area();
			k.setCode(ksMerUsedAreaList.get(0).getProviceAreaId());
			Area provice = areaService.findByObject(k);//身份证前两位找省
			if(provice!=null){
				list.add(provice);
			}
			Area b = new Area(); 
			b.setCode(ksMerUsedAreaList.get(0).getCityAreaId());
			Area city = areaService.findByObject(b);//身份证前四位找市
			if(city!=null){
				list.add(city);
			}
			return list;
		}
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
}
