package com.yj.bj.controller;

import com.yj.bj.service.TransactionalService;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Transactional")
public class TransactionalController extends BaseController{

    @Autowired
    private TransactionalService transactionalService;

    @RequestMapping("select")
    public @ResponseBody
    YJResult select(String merchantId, String orderMerchantId, String orderMerchantName, String agentId, String orderNo, String level, Long startTime, Long finshTime){
        return YJResult.ok(transactionalService.queryList(merchantId,orderMerchantId,orderMerchantName,agentId,orderNo,level,startTime,finshTime));
    }
}
