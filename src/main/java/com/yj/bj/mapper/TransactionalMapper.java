package com.yj.bj.mapper;


import com.yj.bj.entity.CountT1Entity;
import com.yj.bj.entity.Transactional;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TransactionalMapper extends Mapper<Transactional> {
    List<Transactional> getList(@Param("merchantId") String merchantId, @Param("orderMerchantId") String orderMerchantId, @Param("orderMerchantName") String orderMerchantName, @Param("agentId") String agentId, @Param("orderNo") String orderNo, @Param("level") String level, @Param("startTime") Long startTime, @Param("finshTime") Long finshTime);

    List<CountT1Entity> countT1(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
