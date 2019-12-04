package com.yj.bj.controller;

import com.yj.bj.entity.Order;
import com.yj.bj.service.OrderService;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("Order")
public class OrderController extends BaseController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("select")
	public @ResponseBody
	YJResult select(String merChantId, String merName, String merMp, String agentId, String payType, String merType, String rank, Long amount, String state, String orderNo, String share, String send, Long startTime, Long finshTime){
		return YJResult.ok(orderService.queryList(merChantId,merName,merMp,agentId,payType,merType,rank,amount,state,orderNo,share,send,startTime,finshTime));
	}

	@RequestMapping("alter")
	public @ResponseBody
	YJResult alter(String merChantId, String send){
		Order order = new Order();
		order.setMerChantId(merChantId);
		order = orderService.findByObject(order);
		order.setSend(send);
		orderService.update(order);
		return YJResult.ok();
	}
}
