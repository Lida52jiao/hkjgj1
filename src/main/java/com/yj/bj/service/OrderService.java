package com.yj.bj.service;


import com.github.pagehelper.PageInfo;
import com.yj.bj.entity.Order;

public interface OrderService extends BaseService<Order> {
    PageInfo<Order> queryList(String merChantId, String merName, String merMp, String agentId, String payType, String merType, String rank, Long amount, String state, String orderNo, String share, String send, Long startTime, Long finshTime);
}
