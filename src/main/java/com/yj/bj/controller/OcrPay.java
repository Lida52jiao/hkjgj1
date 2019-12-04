package com.yj.bj.controller;

import com.alibaba.fastjson.JSONObject;

import com.yj.bj.service.MerChantsService;
import com.yj.bj.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Create By DaDa
 * @Date: 2019/4/24 15:49
 */
@Controller
@RequestMapping("OCRPay")
public class OcrPay  {
    @Autowired
    private MerChantsService merChantsService;

    @RequestMapping("/charging")
    @ResponseBody
    public JSONObject charging(String merchantId, String institutionId){
        Map<String,String> map = new HashMap<>();
        map.put("merchantId",merchantId);
        map.put("institutionId",institutionId);
        map.put("type","ocr");
        String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/charging", map);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
        return job;
    }
}
