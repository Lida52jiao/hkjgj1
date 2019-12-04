package com.yj.bj.mapper;


import com.yj.bj.entity.Order;
import com.yj.bj.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends MyMapper<Order> {
    List<Order> getList(@Param("merChantId") String merChantId, @Param("merName") String merName, @Param("merMp") String merMp, @Param("agentId") String agentId, @Param("payType") String payType, @Param("merType") String merType, @Param("rank") String rank, @Param("amount") Long amount, @Param("state") String state, @Param("orderNo") String orderNo, @Param("share") String share, @Param("send") String send, @Param("startTime") Long startTime, @Param("finshTime") Long finshTime);
}
