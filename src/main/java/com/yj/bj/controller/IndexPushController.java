package com.yj.bj.controller;


import com.yj.bj.entity.IndexPush;
import com.yj.bj.service.IndexPushService;
import com.yj.bj.util.YJResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 16:15
 */
@Controller
@RequestMapping("indexPush")
public class IndexPushController extends BaseController {

    @Autowired
    private IndexPushService indexPushService;

    @RequestMapping("getImg")
    @ResponseBody
    public YJResult getImg(String appId){
        IndexPush ip = new IndexPush();
        ip.setAppId(appId);
        List<IndexPush> list = indexPushService.findNewPush(ip);
        System.out.println(list);
        return YJResult.ok(list);
    }
}
