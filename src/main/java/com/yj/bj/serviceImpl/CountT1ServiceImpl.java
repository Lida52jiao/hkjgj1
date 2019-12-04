package com.yj.bj.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.bj.entity.CountT1Entity;
import com.yj.bj.entity.Transactional;
import com.yj.bj.mapper.CountT1Mapper;
import com.yj.bj.service.CountT1Service;
import com.yj.bj.service.TransactionalService;
import com.yj.bj.util.MyStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bin on 2018/3/14.
 */
@Service
public class CountT1ServiceImpl extends BaseServiceImpl<CountT1Entity> implements CountT1Service {
    @Autowired
    private TransactionalService transactionalService;
    @Autowired
    private CountT1Mapper countT1Mapper;

    @Override
    public PageInfo<CountT1Entity> queryList(String merchantId, String merName, String startDate, String finishDate) {
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
        return new PageInfo<CountT1Entity>(countT1Mapper.getList(merchantId,merName,startDate,finishDate));
    }
}
