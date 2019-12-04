package com.yj.bj.serviceImpl;

import com.yj.bj.entity.JiDao;
import com.yj.bj.mapper.JiDaoMapper;
import com.yj.bj.service.JiDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiDaoServiceImpl extends BaseServiceImpl<JiDao> implements JiDaoService {
	
	@Autowired
	private JiDaoMapper jiDaoMapper;
}
