package com.yj.bj.controller;

import com.yj.bj.service.CountT1Service;
import com.yj.bj.util.YJResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by bin on 2018/3/8.
 */
@RestController
@RequestMapping("/count/")
public class CountController {
    private final Logger log=Logger.getLogger(getClass());

    @Autowired
    private CountT1Service countT1Service;

    @RequestMapping("/select")
    public YJResult select(String merchantId, String merName, String startDate, String finishDate){
        return YJResult.ok(countT1Service.queryList(merchantId,merName,startDate,finishDate));
    }
}
