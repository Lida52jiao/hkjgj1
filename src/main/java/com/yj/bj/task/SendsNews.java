package com.yj.bj.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yj.bj.entity.CardInformation;
import com.yj.bj.entity.MerChants;
import com.yj.bj.service.CardInformationService;
import com.yj.bj.service.MerChantsService;
import com.yj.bj.util.HttpClientUtils;
import com.yj.bj.util.YJ;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
@EnableScheduling
@Lazy(false)
public class SendsNews {
	
	@Autowired
	private CardInformationService cardInformationService;
	@Autowired
	private MerChantsService merChantsService;
	
	@Scheduled(cron="0 0 10 * * ?")
	public void task(){
		System.out.println("hhh");
		String s = YJ.formatDate(new Date());
		CardInformation cardInformation = new CardInformation();
		cardInformation.setCardType("CC");
		cardInformation.setStatementDate(s.substring(s.length()-2,s.length()));
		List<CardInformation> list = cardInformationService.queryObjectForList(cardInformation);
		int t = Integer.parseInt(s.substring(s.length()-2,s.length()-1));
		if(t < 1){
			cardInformation.setStatementDate(s.substring(s.length()-1,s.length()));
			List<CardInformation> cardInformationList = cardInformationService.queryObjectForList(cardInformation);
			list.addAll(cardInformationList);
		}
		for(CardInformation card : list){
			MerChants merChants = new MerChants();
			merChants.setMerChantId(card.getMerChantId());
			merChants = merChantsService.findByObject(merChants);
			String msg = "【精彩生活】尊敬的"+merChants.getMerName()+"先生/女士，您尾号为"+card.getCardNumber().substring(card.getCardNumber().length()-4,card.getCardNumber().length())+"的"+ BankCode.getName(card.getIssuingBank())+"的信用卡已经出账单了哦，可以来制定计划了，我们将为您打造完美的账单。";
			Map<String, String > map = new HashedMap();
			map.put("merChantId", merChants.getMerChantId());
			map.put("appId", merChants.getAppId());
			map.put("institutionId", merChants.getInstitutionId());
			map.put("msg", msg);
			map.put("type", "1");
			HttpClientUtils.doPost("http://47.104.25.59/templet/JiGuang/transmission.shtml", map);
			Map<String,String> mmap = new HashedMap();
			mmap.put("merchantId", merChants.getMerChantId());
			mmap.put("mobile", merChants.getMerMp());
			mmap.put("institutionId", merChants.getInstitutionId());
			mmap.put("msg", msg);
			mmap.put("type", "dx");
			HttpClientUtils.doPost("http://47.104.4.155:1172/account/remind", mmap);
		}
	}
	
//	@Scheduled(cron = "0 0/1 * * * ?") // 每5分钟执行一次
//	public void test(){
//		System.out.println(new Date());
//	}
}
