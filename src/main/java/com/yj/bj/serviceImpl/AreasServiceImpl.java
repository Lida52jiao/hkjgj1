package com.yj.bj.serviceImpl;

import com.yj.bj.entity.Areas;
import com.yj.bj.mapper.AreasMapper;
import com.yj.bj.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AreasServiceImpl extends BaseServiceImpl<Areas> implements AreasService {
	
	@Autowired
	private AreasMapper areasMapper;

}
