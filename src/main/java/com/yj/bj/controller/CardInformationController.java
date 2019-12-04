package com.yj.bj.controller;

import com.yj.bj.constant.Constaint;
import com.yj.bj.entity.CardInformation;
import com.yj.bj.entity.MerChants;
import com.yj.bj.service.CardInformationService;
import com.yj.bj.service.MerChantsService;
import com.yj.bj.util.RedisUtils;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.List;


@Controller
@RequestMapping("CardInformation")
public class CardInformationController extends BaseController {
	
	@Autowired
	private MerChantsService merChantsService;

	@Autowired
	private CardInformationService cardInformationService;

	@RequestMapping(value = "addCreditCard", method = RequestMethod.POST)
	public @ResponseBody
	YJResult add(String token, String merChantId, String cardNumber, String merMp, String issuingBank, String statementDate, String repaymentDate, String cv2, String effectiveYear, String effectiveMonth, String bindId, String agentId, String institutionId, String appId) {
		Jedis jedis= RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			MerChants m = new MerChants();
			m.setMerChantId(merChantId);
			MerChants h = merChantsService.findByObject(m);
			if(h == null){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.NONE_MERCHAT, "商户不存在");
			}
			CardInformation s = new CardInformation();
			String cardNumbers=cardNumber.replaceAll(" ", "");
			s.setCardNumber(cardNumbers);
			s.setCardType("CC");
			s.setAppId(appId);
			CardInformation cardInformation=cardInformationService.findByObject(s);
			if(cardInformation != null){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.CARDS, "卡已被绑定");
			}
			CardInformation n = new CardInformation(merChantId, "CC", cardNumbers, merMp, issuingBank, statementDate, repaymentDate, cv2, effectiveYear, effectiveMonth, "N", System.currentTimeMillis()+"", "");
			n.setBindId(bindId);
			n.setAgentId(agentId);
			n.setInstitutionId(institutionId);
			n.setAppId(appId);
			n.setSign("N");
			cardInformationService.save(n);
			h.setIsBind("Y");
			merChantsService.update(h);
			RedisUtils.returnResource(jedis);
			return YJResult.ok();
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}

	@RequestMapping(value = "addCard", method = RequestMethod.POST)
	public @ResponseBody
	YJResult addCard(String token, String merChantId, String cardNumber, String merMp, String issuingBank, String bindId, String agentId, String institutionId, String appId) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			MerChants m = new MerChants();
			m.setMerChantId(merChantId);
			MerChants h = merChantsService.findByObject(m);
			if(h == null){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.NONE_MERCHAT, "商户不存在");
			}
			CardInformation s = new CardInformation();
			String cardNumbers=cardNumber.replaceAll(" ", "");
			s.setCardNumber(cardNumbers);
			s.setCardType("SC");
			s.setAppId(appId);
			CardInformation cardInformation=cardInformationService.findByObject(s);
			if(cardInformation != null){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.CARDS, "卡已被绑定");
			}
			CardInformation n = new CardInformation(merChantId, "SC", cardNumbers, "", issuingBank, "", "", "", "", "", "N", System.currentTimeMillis()+"", "");
			n.setMerMp(merMp);
			n.setBindId(bindId);
			n.setAgentId(agentId);
			n.setInstitutionId(institutionId);
			n.setAppId(appId);
			cardInformationService.save(n);
			RedisUtils.returnResource(jedis);
			return YJResult.ok();
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}

	@RequestMapping(value = "getCard", method = RequestMethod.POST)
	public @ResponseBody
	YJResult getCard(String token, String merChantId, String cardType) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			CardInformation n = new CardInformation();
			n.setMerChantId(merChantId);
			n.setCardType(cardType);
			List<CardInformation> h = cardInformationService.queryObjectForList(n);
			RedisUtils.returnResource(jedis);
			return YJResult.ok(h);
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "changeCard", method = RequestMethod.POST)
	public @ResponseBody
	YJResult changeCard(String token, String merChantId, String cardNumber, String cardDefaultNumber, String appId) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			CardInformation n = new CardInformation();
			String cardNumbers=cardNumber.replaceAll(" ", "");
			n.setCardNumber(cardNumbers);
			CardInformation cardInformation=cardInformationService.findByObject(n);
			cardInformation.setCardDefault("Y");
			cardInformationService.update(cardInformation);
			CardInformation t = new CardInformation();
			t.setCardNumber(cardDefaultNumber);
			t.setAppId(appId);
			CardInformation h=cardInformationService.findByObject(t);
			h.setCardDefault("N");
			cardInformationService.update(h);
			RedisUtils.returnResource(jedis);
			return YJResult.ok();
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "getCardById")
	public @ResponseBody CardInformation getCardById(Long cardId) {
			CardInformation n = new CardInformation();
			n.setCardId(cardId);
			return cardInformationService.findByObject(n);
	}
	
	@RequestMapping(value = "alterCards", method = RequestMethod.POST)
	public @ResponseBody
	YJResult alterCards(String token, String merChantId, String cardNumber, String merMp, String statementDate, String repaymentDate, String appId) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			CardInformation n = new CardInformation();
			String cardNumbers=cardNumber.replaceAll(" ", "");
			n.setCardNumber(cardNumbers);
			n.setCardType("CC");
			n.setAppId(appId);
			CardInformation cardInformation=cardInformationService.findByObject(n);
			if(cardInformation != null){
				cardInformation.setStatementDate(statementDate);
				cardInformation.setRepaymentDate(repaymentDate);
				cardInformation.setMerMp(merMp);
				cardInformationService.update(cardInformation);
				RedisUtils.returnResource(jedis);
				return YJResult.ok();
			}
			RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.NOTNE_CARDS, "此信用卡不存在");
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "delCard", method = RequestMethod.POST)
	public @ResponseBody
	YJResult delCard(String token, String merChantId, String cardNumber, String cardType, String appId) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			CardInformation t = new CardInformation();
			String cardNumbers=cardNumber.replaceAll(" ", "");
			t.setCardNumber(cardNumbers);
			t.setCardType(cardType);
			t.setAppId(appId);
			CardInformation cardInformation=cardInformationService.findByObject(t);
			
			/*int n=cardInformationService.find(token,cardInformation);
			if(n > 0){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.NOT_DEL, "此卡在执行计划，不能删除");
			}*/
			cardInformationService.delete(cardInformation);
			RedisUtils.returnResource(jedis);
			return YJResult.ok();
		}
		RedisUtils.returnResource(jedis);
		
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "alterSigns", method = RequestMethod.POST)
	public @ResponseBody
	YJResult alterSigns(String token, String merChantId, String cardNumber, String appId) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			CardInformation n = new CardInformation();
			String cardNumbers=cardNumber.replaceAll(" ", "");
			n.setCardNumber(cardNumbers);
			n.setCardType("CC");
			n.setAppId(appId);
			CardInformation cardInformation=cardInformationService.findByObject(n);
			if(cardInformation != null){
				cardInformation.setSign("");
				cardInformationService.update(cardInformation);
				RedisUtils.returnResource(jedis);
				return YJResult.ok();
			}
			RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.NOTNE_CARDS, "此信用卡不存在");
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "selectCards", method = RequestMethod.POST)
	public @ResponseBody
	YJResult selectCards(String token, String merChantId, String cardNumber, String cardType, String appId) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			CardInformation n = new CardInformation();
			String cardNumbers=cardNumber.replaceAll(" ", "");
			n.setCardNumber(cardNumbers);
			n.setCardType(cardType);
			n.setAppId(appId);
			CardInformation cardInformation=cardInformationService.findByObject(n);
			if(cardInformation != null){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.CARDS, "卡已被绑定");
			}
			RedisUtils.returnResource(jedis);
			return YJResult.ok();
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
	
	@RequestMapping(value = "alterCard", method = RequestMethod.POST)
	public @ResponseBody
	YJResult alterCard(String token, String merChantId, String cardNumber, String merMp, String appId) {
		Jedis jedis=RedisUtils.getJedis();
		if(jedis.exists(token)){
			if(!merChantId.equals(jedis.get(token))){
				RedisUtils.returnResource(jedis);
				return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
			}
			CardInformation n = new CardInformation();
			String cardNumbers=cardNumber.replaceAll(" ", "");
			n.setCardNumber(cardNumbers);
			n.setCardType("SC");
			n.setAppId(appId);
			CardInformation cardInformation=cardInformationService.findByObject(n);
			if(cardInformation != null){
				cardInformation.setMerMp(merMp);
				cardInformationService.update(cardInformation);
				RedisUtils.returnResource(jedis);
				return YJResult.ok();
			}
			RedisUtils.returnResource(jedis);
			return YJResult.build(Constaint.NOTNE_CARDS, "此储蓄卡不存在");
		}
		RedisUtils.returnResource(jedis);
		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
	}
}
