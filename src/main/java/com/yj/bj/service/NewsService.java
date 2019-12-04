package com.yj.bj.service;

import com.yj.bj.entity.News;

import java.util.Map;


public interface NewsService extends BaseService<News> {

	Map<String, Object> receive(Integer page, Integer rows, String merChantId, String appId);
}
