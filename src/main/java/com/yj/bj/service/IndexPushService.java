package com.yj.bj.service;


import com.yj.bj.entity.IndexPush;

import java.util.List;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 11:01
 */
public interface IndexPushService extends BaseService<IndexPush> {

    List<IndexPush> findNewPush(IndexPush ip);
}
