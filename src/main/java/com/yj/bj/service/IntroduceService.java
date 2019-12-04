package com.yj.bj.service;

import com.yj.bj.entity.Introduce;

import java.util.Map;


public interface IntroduceService extends BaseService<Introduce> {

	Map<String, Object> receive(Integer page, Integer rows);
}
