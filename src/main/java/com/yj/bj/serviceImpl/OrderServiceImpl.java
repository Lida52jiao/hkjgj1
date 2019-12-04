package com.yj.bj.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.bj.entity.Order;
import com.yj.bj.mapper.OrderMapper;
import com.yj.bj.service.OrderService;
import com.yj.bj.util.MyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Service 
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public PageInfo<Order> queryList(String merChantId, String merName, String merMp, String agentId, String payType, String merType, String rank, Long amount, String state, String orderNo, String share, String send, Long startTime, Long finshTime) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Integer pageNum = MyStringUtil.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = MyStringUtil.valueOf(request.getParameter("pageSize"), 10);
		PageHelper.startPage(pageNum,pageSize);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		if (MyStringUtil.isNotEmpty(orderField)) {
			PageHelper.orderBy(orderField);
			if (MyStringUtil.isNotEmpty(orderDirection)) {
				PageHelper.orderBy(orderField + " " + orderDirection);
			}
		}
		return new PageInfo<Order>(orderMapper.getList(merChantId,merName,merMp,agentId,payType,merType,rank,amount,state,orderNo,share,send,startTime,finshTime));
	}
}
