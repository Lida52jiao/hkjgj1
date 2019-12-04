package com.yj.bj.service;


import com.github.pagehelper.PageInfo;
import com.yj.bj.entity.Transactional;


public interface TransactionalService extends BaseService<Transactional> {
    PageInfo<Transactional> queryList(String merchantId, String orderMerchantId, String orderMerchantName, String agentId, String orderNo, String level, Long startTime, Long finshTime);

    void t1();
}
