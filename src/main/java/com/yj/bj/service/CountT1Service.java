package com.yj.bj.service;


import com.github.pagehelper.PageInfo;
import com.yj.bj.entity.CountT1Entity;

/**
 * Created by bin on 2018/3/14.
 */
public interface CountT1Service extends BaseService<CountT1Entity> {
    PageInfo<CountT1Entity> queryList(String merchantId, String merName, String startDate, String finishDate);
}
